package net.thomaspreis.tools.qlg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import net.thomaspreis.tools.qlg.exp.QLGExpressionEnum;
import net.thomaspreis.tools.qlg.exp.QLGExpressionException;
import net.thomaspreis.tools.qlg.exp.QLGExpressionFactory;
import net.thomaspreis.tools.qlg.exp.QLGExpressionOperatorEnum;
import net.thomaspreis.tools.qlg.exp.QLGExpressionSet;
import net.thomaspreis.tools.qlg.helper.FileHelper;

public class QuickLoggerGrep {

	Logger logger = Logger.getLogger(QuickLoggerGrep.class);

	static String logSuffix = ".log";
	static String pathPattern = "yyyy.MM.dd.HH.mm.ss";
	static String lineBreakProperty = "line.separator";
	static String lineBreak = System.getProperty(lineBreakProperty);
	static String defaultEncoding = "UTF-8";

	String basePath = "";
	String targetPath = "";
	String targetCurrentExecutionPath = "";
	long currentExecutionTime = 0l;
	SimpleDateFormat sdf = null;
	QLGExpressionFactory qlgExpressionFactory;
	Set<String> generationResumeSet = new HashSet<>();

	public QuickLoggerGrep() {
		this.qlgExpressionFactory = new QLGExpressionFactory();
	}
	

	void process(QuickLoggerGrepConfig qlgc) throws InstantiationException, IllegalAccessException, IOException {
		for (QLGExpressionSet expSet : qlgc.getExpressionSetList()) {
			this.addExpression(expSet);
		}
		this.process(qlgc.getBasePath(), qlgc.getTargetPath());
	}

	void process(String basePath, String targetPath)
			throws InstantiationException, IllegalAccessException, IOException {
		long t0 = System.currentTimeMillis();
		try {
			this.basePath = basePath;
			this.targetPath = targetPath;

			FileHelper fileHelper = new FileHelper(QuickLoggerGrep.logSuffix);
			List<String> filesList = fileHelper.getLogsFiles(this.basePath);
			if (!filesList.isEmpty()) {
				logger.info("Files found: " + filesList.size() + " staring the processing...");
				for (String sourceFileName : filesList) {
					String targetFileName = this.getTargetFileName(sourceFileName);
					this.grep(sourceFileName, targetFileName);
				}

				for (String emptyPath : fileHelper.getEmptyFolders(this.targetCurrentExecutionPath)) {
					new File(emptyPath).deleteOnExit();
				}

				printGenerationResume();
			} else {
				logger.warn("There is no files to be grepped");
			}
		} finally {
			logger.info("Grepping process finished, time spent: " + (System.currentTimeMillis() - t0) + " ms.");
		}
	}

	void printGenerationResume() {
		logger.info("--------------");
		logger.info("Process summary:");
		if (!generationResumeSet.isEmpty()) {
			for (String logEntry : generationResumeSet) {
				logger.info(logEntry);
			}
		} else {
			logger.warn("No information found in the files");
		}
		logger.info("--------------");
	}

	void reset() {
		this.currentExecutionTime = 0l;
		this.qlgExpressionFactory.reset();
	}

	void grep(String sourceFileName, String targetFileName) throws IOException {
		logger.info("Reading file: " + sourceFileName);
		logger.info("Target file: " + targetFileName);
		CharsetDecoder decoder = Charset.forName(defaultEncoding).newDecoder();
		decoder.onMalformedInput(CodingErrorAction.IGNORE);
		FileInputStream input = null;
		BufferedReader bufferedReader = null;
		long t0 = System.currentTimeMillis();
		try {
			input = new FileInputStream(new File(sourceFileName));
			InputStreamReader reader = new InputStreamReader(input, decoder);
			bufferedReader = new BufferedReader(reader);
			String line = bufferedReader.readLine();
			long totalLines = 0l;
			long totalFound = 0l;
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileName))) {
				while (line != null) {
					totalLines = totalLines + 1l;
					if (isValid(line)) {
						bw.write(line + lineBreak);
						totalFound = totalFound + 1l;
					}
					line = bufferedReader.readLine();
				}
				bw.flush();
				bw.close();
			} catch (Exception e) {
				logger.error("Error writing/reading file: " + sourceFileName, e);
			} finally {
				logger.info("Lines read: " + totalLines + ", items found: " + totalFound);
				if (totalFound == 0l) {
					logger.info("Deleting empty file: " + targetFileName);
					if (!new File(targetFileName).delete()) {
						logger.error("Unable to delete file: " + targetFileName);
					}
				} else {
					logger.info("Target file written: " + targetFileName);
					generationResumeSet.add("Total Entries found: " + totalFound + ", file: " + targetFileName);
				}
				logger.info("Finishing processing file, time: " + (System.currentTimeMillis() - t0) + " ms");
			}
		} finally {
			if (null != input) {
				input.close();
			}
			if (null != bufferedReader) {
				bufferedReader.close();
			}
		}
	}

	public final void addExpression(List<QLGExpressionSet> expSetList)
			throws InstantiationException, IllegalAccessException {
		if (!expSetList.isEmpty()) {
			for (QLGExpressionSet expSet : expSetList) {
				this.qlgExpressionFactory.add(expSet);
			}
		}
	}

	public final void addExpression(QLGExpressionSet expSet) throws InstantiationException, IllegalAccessException {
		this.qlgExpressionFactory.add(expSet);
	}

	public final void addExpression(QLGExpressionEnum expType, String value, QLGExpressionOperatorEnum operator)
			throws InstantiationException, IllegalAccessException {
		QLGExpressionSet expSet = new QLGExpressionSet();
		expSet.setOperator(operator);
		expSet.add(expType, value);
		this.qlgExpressionFactory.add(expSet);
	}

	String getTargetFileName(String fileName) {
		if (null == sdf) {
			sdf = new SimpleDateFormat(pathPattern);
		}
		if (currentExecutionTime == 0l) {
			currentExecutionTime = System.currentTimeMillis();
			targetCurrentExecutionPath = targetPath + sdf.format(new Date(currentExecutionTime));
		}
		String targetFileName = fileName.substring(fileName.lastIndexOf(File.separatorChar) + 1, fileName.length());
		String targetBasePath = targetCurrentExecutionPath + File.separatorChar + fileName.substring(
				fileName.indexOf(basePath) + basePath.length(), fileName.lastIndexOf(File.separatorChar) + 1);
		new File(targetBasePath).mkdirs();
		return targetBasePath + targetFileName;
	}

	boolean isValid(String line) throws QLGExpressionException {
		return this.qlgExpressionFactory.isValid(line);
	}
}
