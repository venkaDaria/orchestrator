package model;

public class Role {
    private String value;
    
    public Role(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
    public boolean hasValue() {
        return value != null;
    }
    
    public Role copy() {
        return new Role(value);
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
        if (obj == null || !(obj instanceof Role))
            return false;
        Role role = (Role)obj;
        return value.equals(role.value);
    }
}
