package model;

public class Role {
	private String value;
	
	public Role(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Role [value=" + value + "]";
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Role))
			return false;
		Role role = (Role)obj;
		return value.equals(role.value);
	}
}
