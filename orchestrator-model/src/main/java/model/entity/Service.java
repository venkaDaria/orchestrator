package model.entity;

import exception.ServiceValidationException;
import model.Status;
import model.base.ServiceBase;

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

	@Override
	public void addContainer(final Container container) {
		if (container != null) {
			if (!container.hasService() || !container.getService().equals(this)) {
				container.setStatus(Status.STOPPED);
				container.setService(this);
			} else {
				super.addContainer(container);
			}
		} else {
			throw new ServiceValidationException("Can't add container");
		}
	}
}
