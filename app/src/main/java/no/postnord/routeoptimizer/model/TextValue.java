package no.postnord.routeoptimizer.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class TextValue implements Serializable {
	private String text;
	private long value;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("text", text)
				.append("value", value)
				.toString();
	}
}
