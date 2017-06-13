package model.entity;

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
