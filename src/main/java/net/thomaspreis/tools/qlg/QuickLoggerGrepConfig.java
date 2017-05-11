package net.thomaspreis.tools.qlg;

import java.util.ArrayList;
import java.util.List;

import net.thomaspreis.tools.qlg.exp.QLGExpressionSet;

public class QuickLoggerGrepConfig {

	private String basePath;
	private String targetPath;
	private List<QLGExpressionSet> expressionSetList;

	public QuickLoggerGrepConfig() {
		this.expressionSetList = new ArrayList<>();
	}

	public void addExpression(QLGExpressionSet expSet) {
		this.expressionSetList.add(expSet);
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	public List<QLGExpressionSet> getExpressionSetList() {
		return expressionSetList;
	}

	public void setExpressionSetList(List<QLGExpressionSet> expressionSetList) {
		this.expressionSetList = expressionSetList;
	}

	@Override
	public String toString() {
		return "QuickLoggerGrepConfig [basePath=" + basePath + ", targetPath=" + targetPath + ", expressionSetList="
				+ expressionSetList + "]";
	}
}
