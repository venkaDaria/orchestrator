package model.entity;

import model.base.ContainerBase;

public class Container extends ContainerBase {
	@Override
	public String asFormattedString() {
		return "Container [id = " + getId() + ", status=" + getStatus() + ", node=" + getNode() + ", service="
				+ getService() + "]";
	}

	@Override
	public Object getIdentity() {
		return getId();
	}

	@Override
	public void setNode(Node node) {
		if (hasNode() && getNode().containsContainer(this)) {
			getNode().removeContainer(this);
		}

		super.setNode(node);

		if (hasNode()) {
			getNode().addContainer(this);
		}
	}

	@Override
	public void setService(final Service service) {
		if (hasService()) {
			getService().removeContainer(this);
		}

		super.setService(service);

		if (hasService()) {
			getService().addContainer(this);
		}
	}
}
