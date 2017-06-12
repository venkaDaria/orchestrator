package model;

import exception.RoleException;

public final class Role {
    private final String value;
    
    public Role(final String value) {
    	if (value == null || value.trim().equals("")) {
    		throw new RoleException("Value can't be null or empty");
    	}
    	
    	this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Role: " + value;
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
    	
        Role role = (Role)obj;
        return value.equals(role.getValue());
    }
}
