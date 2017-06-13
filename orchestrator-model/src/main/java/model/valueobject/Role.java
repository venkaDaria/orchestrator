package model.valueobject;

import exception.RoleValidationException;

public final class Role extends ValueObject<String>{
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

    @Override
    public String asFormattedString() {
        return "Role [value=" + value + "]";
    }

}
