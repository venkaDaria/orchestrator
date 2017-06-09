package model;

public class Protocol {
    private String value;
    
    public Protocol(final String value) {
    	if (value == null)
    		throw new IllegalArgumentException("Value can't be null");
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    public boolean hasValue() {
        return value != null;
    }
    
    public Protocol copy() {
        return new Protocol(value);
    }

    @Override
    public String toString() {
        return "Protocol: " + value;
    }
}