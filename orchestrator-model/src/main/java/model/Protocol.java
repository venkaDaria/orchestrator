package model;

import exception.ProtocolValidationException;

public final class Protocol {
    private final String value;
    
    public Protocol(final String value) {
    	if (value == null || value.trim().equals("")) {
    		throw new ProtocolValidationException();
    	}
    	
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Protocol: " + value;
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
}