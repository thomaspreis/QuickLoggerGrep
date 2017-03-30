package net.thomaspreis.tools.qlg.helper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

	private String logSuffix;

	public FileHelper(String logSuffix) {
		this.logSuffix = logSuffix;
	}

	public List<String> getLogsFiles(String path) {
		List<String> filesList = new ArrayList<>();
		File fileBase = new File(path);
		addLogsFiles(fileBase, filesList);
		return filesList;
	}

	public List<String> getEmptyFolders(String path) {
		List<String> filesList = new ArrayList<>();
		File fileBase = new File(path);
		addEmptyFolders(fileBase, filesList);
		return filesList;
	}

	private void addLogsFiles(File fileBase, List<String> filesList) {
		for (File f : fileBase.listFiles()) {
			if (f.isDirectory()) {
				addLogsFiles(f, filesList);
			} else {
				String name = f.getName().toLowerCase();
				if (name.contains(logSuffix) && name.lastIndexOf(logSuffix) == (name.length() - logSuffix.length())) {
					filesList.add(f.getAbsolutePath());
				}
			}
		}
	}

	private void addEmptyFolders(File fileBase, List<String> filesList) {
		for (File f : fileBase.listFiles()) {
			if (f.isDirectory()) {
				addEmptyFolders(f, filesList);
			}
		}
		if (fileBase.isDirectory() && (null == fileBase.listFiles() || fileBase.listFiles().length == 0)) {
			filesList.add(fileBase.getAbsolutePath());
		}
	}
}
