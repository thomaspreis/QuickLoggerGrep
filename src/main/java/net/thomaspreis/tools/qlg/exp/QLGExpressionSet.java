package net.thomaspreis.tools.qlg.exp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * QLGExpressionSet
 */
public class QLGExpressionSet implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7710122135907784208L;

	private List<QLGExpressionItem> expressionItemsList;
	private QLGExpressionOperatorEnum operator;

	public QLGExpressionSet() {
		this.operator = QLGExpressionOperatorEnum.OR;
		this.expressionItemsList = new ArrayList<>();
	}

	public void add(QLGExpressionEnum expType, String value) throws InstantiationException, IllegalAccessException {
		QLGExpressionItem exp = new QLGExpressionItem();
		exp.setValue(value);
		exp.setExpressionEnum(expType);
		expressionItemsList.add(exp);
	}

	public List<QLGExpression> getExpressionList() throws QLGExpressionException {
		List<QLGExpression> expList = new ArrayList<>();
		try {
			if (null != expressionItemsList && !expressionItemsList.isEmpty()) {
				for (QLGExpressionItem item : expressionItemsList) {
					QLGExpression exp;
					exp = item.getExpressionEnum().getInstance();
					exp.setValue(item.getValue());
					expList.add(exp);
				}
			}
		} catch (InstantiationException | IllegalAccessException e) {
			throw new QLGExpressionException("Error creating QLGExpression list", e);
		}
		return expList;
	}

	public QLGExpressionOperatorEnum getOperator() {
		return operator;
	}

	public void setOperator(QLGExpressionOperatorEnum operator) {
		this.operator = operator;
	}

	public List<QLGExpressionItem> getExpressionItemsList() {
		return expressionItemsList;
	}

	public void setExpressionItemsList(List<QLGExpressionItem> expressionItemsList) {
		this.expressionItemsList = expressionItemsList;
	}

	@Override
	public String toString() {
		return "QLGExpressionSet [expressionItemsList=" + expressionItemsList + ", operator=" + operator + "]";
	}
}
