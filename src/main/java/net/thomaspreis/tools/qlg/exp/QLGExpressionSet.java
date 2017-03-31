package net.thomaspreis.tools.qlg.exp;

import java.util.ArrayList;
import java.util.List;

/**
 * QLGExpressionSet
 */
public class QLGExpressionSet {

	private List<QLGExpression> expressionList;
	private QLGExpressionOperatorEnum operator;

	public QLGExpressionSet(QLGExpressionOperatorEnum operator) {
		this.operator = operator;
		this.expressionList = new ArrayList<>();
	}

	public void add(QLGExpressionEnum expType, String value) throws InstantiationException, IllegalAccessException {
		QLGExpression exp = expType.getInstance();
		exp.setValue(value);
		expressionList.add(exp);
	}

	public List<QLGExpression> getExpressionList() {
		return expressionList;
	}

	public void setExpressionList(List<QLGExpression> expressionList) {
		this.expressionList = expressionList;
	}

	public QLGExpressionOperatorEnum getOperator() {
		return operator;
	}

	public void setOperator(QLGExpressionOperatorEnum operator) {
		this.operator = operator;
	}
}
