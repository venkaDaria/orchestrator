package com.globallogic.orchestrator.model;

import org.apache.commons.lang.StringUtils;

import com.globallogic.orchestrator.base.StringValidationException;

public abstract class StringValueObject extends ValueObject<String> {

	public StringValueObject(String value) {
		if (StringUtils.isBlank(value)) {
			throw new StringValidationException();
		}
	}
}
