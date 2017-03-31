package net.thomaspreis.tools.qlg.exp;

/**
 * QLGExpression
 */
public abstract class QLGExpression {
	
	private String value;

	public abstract boolean isValid(String line);

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "QLGExpression [value=" + value + "]";
	}
}
