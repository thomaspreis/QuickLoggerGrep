package net.thomaspreis.tools.qlg.exp;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * QLGExpressionFactory
 */
public class QLGExpressionFactory {

	Logger logger = Logger.getLogger(QLGExpressionFactory.class);

	private List<QLGExpression> expressionList;

	public QLGExpressionFactory() {
		this.expressionList = new ArrayList<>();
	}

	public void add(QLGExpressionEnum expType, String value) throws InstantiationException, IllegalAccessException {
		QLGExpression exp = expType.getInstance();
		exp.setValue(value);
		expressionList.add(exp);
	}

	public boolean isValid(String line) throws QLGExpressionException {
		if (expressionList.isEmpty()) {
			logger.error("There is no expressions to be verified");
			throw new QLGExpressionException("There is no expressions to be verified");
		} else {
			for (QLGExpression exp : expressionList) {
				if (exp.isValid(line)) {
					return true;
				}
			}
		}
		return false;
	}

	public void reset() {
		expressionList = new ArrayList<>();
	}
}
