package net.thomaspreis.tools.qlg.exp;

import java.io.Serializable;

public class QLGExpressionItem implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8233400753520008353L;

	private QLGExpressionEnum expressionEnum;

	private String value;

	public QLGExpressionEnum getExpressionEnum() {
		return expressionEnum;
	}

	public void setExpressionEnum(QLGExpressionEnum expressionEnum) {
		this.expressionEnum = expressionEnum;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expressionEnum == null) ? 0 : expressionEnum.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QLGExpressionItem other = (QLGExpressionItem) obj;
		if (expressionEnum != other.expressionEnum)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "QLGExpressionItem [expressionEnum=" + expressionEnum + ", value=" + value + "]";
	}

}
