package model.valueobject;

import exception.RoleValidationException;
import model.ValueObject;

public final class Role extends ValueObject<String> {
	private final String value;

	public Role(final String value) {
		if (value == null || value.trim().equals("")) {
			throw new RoleValidationException();
		}

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
