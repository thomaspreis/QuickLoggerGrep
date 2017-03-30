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
			QuickLoggerGrep qlg = new QuickLoggerGrep();
			String basePath = "C:\\temp\\logs\\";
			String targetPath = "C:\\temp\\logs\\grepped-logs\\";

			qlg.addExpression(QLGExpressionEnum.STRING_CONTAINS_IGNORE_CASE, "Log entry");
			qlg.addExpression(QLGExpressionEnum.STRING_CONTAINS_IGNORE_CASE, "Log Entry Exact");

			qlg.process(basePath, targetPath);

			assertTrue("Sucess!!", Boolean.TRUE);
		} catch (Exception e) {
			fail("Error during the execution: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
