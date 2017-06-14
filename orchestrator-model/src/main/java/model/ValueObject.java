package model;

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
		return getValue().hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		return getValue().equals(((ValueObject<T>) obj).getValue());
	}

	@Override
	public String asFormattedString() {
		return asString();
	}
}
