package model.entity;

public class Service extends ServiceBase {

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Service other = (Service) obj;
		return getIdentity() == null && other.getIdentity() == null
				|| getIdentity() != null && getIdentity().equals(other.getIdentity());
	}

	@Override
	public String asFormattedString() {
		return "Service [name=" + getName() + ", image=" + getImage() + ", volumes=" + getVolumes() + ", ports="
				+ getPorts() + ", roles=" + getRoles() + "]";
	}

	@Override
	public Object getIdentity() {
		return getName();
	}
}
