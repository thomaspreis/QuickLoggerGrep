package net.thomaspreis.tools.qlg.exp;

import net.thomaspreis.tools.qlg.exp.impl.ExpRegularExpressionImpl;
import net.thomaspreis.tools.qlg.exp.impl.ExpStringContainsEqualCaseImpl;
import net.thomaspreis.tools.qlg.exp.impl.ExpStringContainsIgnoreCaseImpl;
import net.thomaspreis.tools.qlg.exp.impl.ExpStringStartWithImpl;

public enum QLGExpressionEnum {
	
	STRING_CONTAINS_EQUAL_CASE(ExpStringContainsEqualCaseImpl.class), 
	STRING_CONTAINS_IGNORE_CASE(ExpStringContainsIgnoreCaseImpl.class),
	STRING_STARTS_WITH(ExpStringStartWithImpl.class),
	REGULAR_EXPRESSION(ExpRegularExpressionImpl.class);

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
