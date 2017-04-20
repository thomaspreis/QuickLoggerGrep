package net.thomaspreis.tools.qlg.exp.impl;

import org.apache.commons.lang3.StringUtils;

import net.thomaspreis.tools.qlg.exp.QLGExpression;

public class ExpStringStartWithImpl extends QLGExpression {

	@Override
	public boolean isValid(String line) {
		return StringUtils.isNotEmpty(line) && line.startsWith(getValue());
	}
}
