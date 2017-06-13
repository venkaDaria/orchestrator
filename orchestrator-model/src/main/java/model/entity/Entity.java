package model.entity;

import model.BusinessObject;

public abstract class Entity extends BusinessObject {
	public abstract Object getIdentity();

	@Override
	public int hashCode() {
		final int prime = 31;	
		return prime + (getIdentity() == null  ? 0 : getIdentity().hashCode());
	}
}
