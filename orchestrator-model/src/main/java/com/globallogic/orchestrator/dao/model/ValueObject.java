package com.globallogic.orchestrator.dao.model;

import java.util.Objects;

public abstract class ValueObject<T> extends BusinessObject {
	public abstract String asString();

	public abstract T getValue();

	@Override
	public int hashCode() {
		return Objects.hashCode(getValue());
	}

	@Override
	public boolean equals(final Object obj) {
		if (getClass() != obj.getClass())
			return false;
		return Objects.equals(getValue(), ((ValueObject<T>) obj).getValue());
	}

	@Override
	public String asFormattedString() {
		return asString();
	}
}
