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
			String basePath = "C:\\temp\\logs\\";
			String targetPath = "C:\\temp\\logs\\grepped-logs\\";

			quickLoggerGrep.addExpression(QLGExpressionEnum.STRING_CONTAINS_IGNORE_CASE, "Log entry");
			quickLoggerGrep.addExpression(QLGExpressionEnum.STRING_CONTAINS_EQUAL_CASE, "Log Entry Exact");

			quickLoggerGrep.process(basePath, targetPath);

			assertTrue("Sucess!!", Boolean.TRUE);
		} catch (Exception e) {
			fail("Error during the execution: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
