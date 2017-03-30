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
			String basePath = "C:\\Users\\f0fp352\\Desktop\\Jira\\TRUCKS-1681\\logs\\";
			String targetPath = "C:\\Users\\f0fp352\\Desktop\\Jira\\TRUCKS-1681\\grepped-logs\\";

			qlg.addExpression(QLGExpressionEnum.STRING_CONTAINS_IGNORE_CASE, "96277A");
			qlg.addExpression(QLGExpressionEnum.STRING_CONTAINS_IGNORE_CASE, "EPEP.PERSISTENCE.ESKOORD.MD17.0001");

			qlg.process(basePath, targetPath);

			assertTrue("Sucess!!", Boolean.TRUE);
		} catch (Exception e) {
			fail("Error during the execution: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
