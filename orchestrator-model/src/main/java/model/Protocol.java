package model;

public class Protocol {
	private String value;
	
	public Protocol() {
		this.value = "";
	}

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}
	
	public boolean hasValue() {
		return value != null;
	}
	
	public Protocol copy() {
		Protocol protocol = new Protocol();
		protocol.setValue(value);
		return protocol;
	}

	@Override
	public String toString() {
		return "Protocol: " + value;
	}
}