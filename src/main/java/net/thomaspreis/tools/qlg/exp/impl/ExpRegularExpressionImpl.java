package net.thomaspreis.tools.qlg.exp.impl;

import java.util.regex.Pattern;

import net.thomaspreis.tools.qlg.exp.QLGExpression;

public class ExpRegularExpressionImpl extends QLGExpression {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 65984333011835641L;
	
	private Pattern pattern = null;

	@Override
	public boolean isValid(String line) {
		if (null == pattern) {
			this.pattern = Pattern.compile(getValue());
		}
		return pattern.matcher(line).find();
	}
}
