package net.thomaspreis.tools.qlg.exp.impl;

import org.apache.commons.lang3.StringUtils;

import net.thomaspreis.tools.qlg.exp.QLGExpression;

public class ExpStringContainsEqualCaseImpl extends QLGExpression {

	@Override
	public boolean isValid(String line) {
		if (!StringUtils.isEmpty(line)) {
			return line.contains(getValue());
		}
		return false;
	}
}
