package model;

public class Protocol {
    private String value;
    
    public Protocol(final String value) {
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