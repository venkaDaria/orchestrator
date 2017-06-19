package com.globallogic.orchestrator.dao.model.valueobject;

import com.globallogic.orchestrator.dao.model.StringValueObject;

public final class Role extends StringValueObject {
	private final String value;

	public Role(final String value) {
		super(value);
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String asString() {
		return "Role [value=" + value + "]";
	}

}
