package model.entity;

import model.base.NodeBase;

public class Node extends NodeBase {

	@Override
	public String asFormattedString() {
		return "Node [name=" + getName() + ", roles=" + getRoles() + "]";
	}

	@Override
	public Object getIdentity() {
		return getName();
	}
}
