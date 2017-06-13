package model.entity;

public class Container extends ContainerBase {

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Container other = (Container) obj;
		return getIdentity() == null && other.getIdentity() == null
				|| getIdentity() != null && getIdentity().equals(other.getIdentity());
	}

	@Override
	public String asFormattedString() {
		return "Container [id = " + getId() + ", status=" + getStatus() + ", node=" + getNode() + ", service=" + getService() + "]";
	}

	@Override
	public Object getIdentity() {
		return getId();
	}
}
