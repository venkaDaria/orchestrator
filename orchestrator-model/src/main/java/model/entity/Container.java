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
}
