package net.thomaspreis.tools.qlg;

import org.junit.Test;

import junit.framework.TestCase;
import net.thomaspreis.tools.qlg.exp.QLGExpressionEnum;

/**
 * Test case example
 */
public class QuickLoggerGrepTest extends TestCase {

	@Test
	public void testProcess() {
		try {
			QuickLoggerGrep quickLoggerGrep = new QuickLoggerGrep();
			String basePath = "C:\\temp\\logs\\source\\";
			String targetPath = "C:\\temp\\logs\\grepped-logs\\";

			quickLoggerGrep.addExpression(QLGExpressionEnum.STRING_CONTAINS_IGNORE_CASE, "GROUp cache for application");
			quickLoggerGrep.addExpression(QLGExpressionEnum.STRING_CONTAINS_EQUAL_CASE, "FILESYSTEM");
			quickLoggerGrep.addExpression(QLGExpressionEnum.REGULAR_EXPRESSION, "\\d{4}-\\d{2}-\\d{2}");

			quickLoggerGrep.process(basePath, targetPath);

			assertTrue("Sucess!!", Boolean.TRUE);
		} catch (Exception e) {
			fail("Error during the execution: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
