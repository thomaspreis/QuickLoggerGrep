package net.thomaspreis.tools.qlg.exp.impl;

import org.apache.commons.lang3.StringUtils;

import net.thomaspreis.tools.qlg.exp.QLGExpression;

public class ExpStringContainsIgnoreCaseImpl extends QLGExpression {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7753717872640091560L;

	@Override
	public boolean isValid(String line) {
		if (!StringUtils.isEmpty(line)) {
			return line.toLowerCase().contains(getValue().toLowerCase());
		}
		return false;
	}

}
