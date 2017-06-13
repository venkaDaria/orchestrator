package model.entity;

public class Service extends ServiceBase {
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
