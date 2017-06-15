package model;

import exception.StringValidationException;

public abstract class StringValueObject extends ValueObject<String> {

	public StringValueObject(String value) {
		if (value == null || value.trim().equals("")) {
			throw new StringValidationException();
		}
	}
}
