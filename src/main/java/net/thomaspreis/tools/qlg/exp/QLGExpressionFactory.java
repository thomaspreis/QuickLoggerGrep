package net.thomaspreis.tools.qlg.exp;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * QLGExpressionFactory
 */
public class QLGExpressionFactory {

	Logger logger = Logger.getLogger(QLGExpressionFactory.class);

	private List<QLGExpressionSet> expressionSetList;

	public QLGExpressionFactory() {
		this.expressionSetList = new ArrayList<>();
	}

	public void add(QLGExpressionSet expSet) throws InstantiationException, IllegalAccessException {
		this.expressionSetList.add(expSet);
	}

	public boolean isValid(String line) throws QLGExpressionException {
		if (expressionSetList.isEmpty()) {
			logger.error("There is no expressions to be verified");
			throw new QLGExpressionException("There is no expressions to be verified");
		} else {
			for (QLGExpressionSet expSet : expressionSetList) {
				if (isValid(expSet, line)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isValid(QLGExpressionSet expSet, String line) throws QLGExpressionException {
		boolean isValid = false;
		if (expSet.getOperator().equals(QLGExpressionOperatorEnum.OR)) {
			isValid = isValidOrOperator(expSet, line);
		} else if (expSet.getOperator().equals(QLGExpressionOperatorEnum.AND)) {
			isValid = isValidAndOperator(expSet, line);
		} else {
			logger.error("Unknown operator: " + expSet.getOperator());
			throw new QLGExpressionException("Unknown operator: " + expSet.getOperator());
		}
		return isValid;
	}

	private boolean isValidAndOperator(QLGExpressionSet expSet, String line) {
		int totValid = 0;
		for (QLGExpression exp : expSet.getExpressionList()) {
			if (exp.isValid(line)) {
				totValid++;
			}
		}
		return totValid == expSet.getExpressionList().size();
	}

	private boolean isValidOrOperator(QLGExpressionSet expSet, String line) {
		for (QLGExpression exp : expSet.getExpressionList()) {
			if (exp.isValid(line)) {
				return true;
			}
		}
		return false;
	}

	public void reset() {
		this.expressionSetList = new ArrayList<>();
	}
}
