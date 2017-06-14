package model;

import java.util.Objects;

import exception.ValueObjectValidationException;

public abstract class ValueObject<T> extends BusinessObject {
	public abstract String asString();

	public abstract T getValue();

	public ValueObject(T value) {
		if (value == null || value instanceof String && ((String) value).trim().equals("")) {
			throw new ValueObjectValidationException();
		}
	}

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
