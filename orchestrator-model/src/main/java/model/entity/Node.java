package model.entity;

public class Node extends NodeBase {
	
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Node other = (Node) obj;
		return getIdentity() == null && other.getIdentity() == null
				|| getIdentity() != null && getIdentity().equals(other.getIdentity());
	}

	@Override
	public String asFormattedString() {
		return "Node [name=" + getName() + ", roles=" + getRoles() + "]";
	}

	@Override
	public Object getIdentity() {
		return getName();
	}
}
