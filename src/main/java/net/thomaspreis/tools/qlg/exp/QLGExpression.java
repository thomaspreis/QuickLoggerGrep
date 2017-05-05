package net.thomaspreis.tools.qlg.exp;

import java.io.Serializable;

/**
 * QLGExpression
 */
public abstract class QLGExpression implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3138730466514429743L;
	
	
	private String value;

	public QLGExpression() {
	}
	
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
