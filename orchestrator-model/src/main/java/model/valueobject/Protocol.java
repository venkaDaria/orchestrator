package model.valueobject;

import exception.ProtocolValidationException;
import model.ValueObject;

public final class Protocol extends ValueObject<String>{
    private final String value;
    
    public Protocol(final String value) {
    	if (value == null || value.trim().equals("")) {
    		throw new ProtocolValidationException();
    	}
    	
        this.value = value;
    }
    
    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
    	if (this == obj)
    		return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Protocol protocol = (Protocol)obj;
        return value.equals(protocol.value);
    }

	@Override
	public String asFormattedString() {
		return "Protocol [value=" + value + "]";
	}
}