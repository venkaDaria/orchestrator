package model;

public abstract class Entity extends BusinessObject {
	public abstract Object getIdentity();

	@Override
	public int hashCode() {
		final int prime = 31;
		return prime + (getIdentity() == null ? 0 : getIdentity().hashCode());
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		return getIdentity() != null && getIdentity().equals(((Entity) obj).getIdentity());
	}
}
