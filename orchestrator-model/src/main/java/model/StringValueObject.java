package model;

import org.apache.commons.lang3.StringUtils;

import exception.StringValidationException;

public abstract class StringValueObject extends ValueObject<String> {

	public StringValueObject(String value) {
		if (StringUtils.isBlank(value)) {
			throw new StringValidationException();
		}
	}
}
