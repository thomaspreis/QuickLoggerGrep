package net.thomaspreis.tools.qlg;

import org.junit.Test;

import junit.framework.TestCase;
import net.thomaspreis.tools.qlg.exp.QLGExpressionEnum;
import net.thomaspreis.tools.qlg.exp.QLGExpressionOperatorEnum;
import net.thomaspreis.tools.qlg.exp.QLGExpressionSet;

/**
 * Test case example
 */
public class QuickLoggerGrepTest extends TestCase {

	@Test
	public void testProcess() {
		try {
			// Initiate the Logger Grep
			QuickLoggerGrep quickLoggerGrep = new QuickLoggerGrep();
			// Source folder (where the original logs files are located)
			String basePath = "C:\\temp\\logs\\source\\";
			// Target path (where the modified files will be stored)
			String targetPath = "C:\\temp\\logs\\grepped-logs\\";

			// Expression set - Operator AND to be used between the expressions
			QLGExpressionSet expSet1 = new QLGExpressionSet();
			expSet1.setOperator(QLGExpressionOperatorEnum.AND);
			// Expression 1 - Line contains the String 'cache' - ignore case
			expSet1.add(QLGExpressionEnum.STRING_CONTAINS_IGNORE_CASE, "cache");
			// Expression 2 - Line contains the String '[INFO]' - case sensitive
			expSet1.add(QLGExpressionEnum.STRING_CONTAINS_EQUAL_CASE, "[INFO]");
			// Expression 3 - Regular expression (date)
			expSet1.add(QLGExpressionEnum.REGULAR_EXPRESSION, "\\d{4}-\\d{2}-\\d{2}");			
			
			// Include the expression set to the logger grep
			quickLoggerGrep.addExpression(expSet1);

			// Process the grep
			// The files containing .log in the source folder, 
			// will be read line by line
			// Each line that matches the Expression set content (with the operator), 
			// will be written in the target path/file
			quickLoggerGrep.process(basePath, targetPath);

			assertTrue("Sucess!!", Boolean.TRUE);
		} catch (Exception e) {
			fail("Error during the execution: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
