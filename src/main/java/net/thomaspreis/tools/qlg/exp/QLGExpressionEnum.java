package net.thomaspreis.tools.qlg.exp;

import net.thomaspreis.tools.qlg.exp.impl.ExpStringContainsEqualCaseImpl;
import net.thomaspreis.tools.qlg.exp.impl.ExpStringContainsIgnoreCaseImpl;

public enum QLGExpressionEnum {
	
	STRING_CONTAINS_EQUAL_CASE(ExpStringContainsEqualCaseImpl.class), 
	STRING_CONTAINS_IGNORE_CASE(ExpStringContainsIgnoreCaseImpl.class);

	Class<? extends QLGExpression> implClazz;

	/**
	 * QLGExpressionEnum
	 * 
	 * @param implClazz
	 *            impl class
	 */
	QLGExpressionEnum(Class<? extends QLGExpression> implClazz) {
		this.implClazz = implClazz;
	}

	public QLGExpression getInstance() throws InstantiationException, IllegalAccessException {
		return implClazz.newInstance();
	}
}
