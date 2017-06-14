package model.valueobject;

import exception.ProtocolValidationException;
import model.ValueObject;

public final class Protocol extends ValueObject<String> {
	private final String value;

	public Protocol(final String value) {
		super(value, new ProtocolValidationException());
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String asString() {
		return "Protocol [value=" + value + "]";
	}
}