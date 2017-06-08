package model;

import java.util.ArrayList;
import java.util.List;

import exception.ContainerException;

public class Node {
	private List<Role> roles;
	private List<Container> containers;
	
	public Node() {
		this.roles = new ArrayList<>();
		this.containers = new ArrayList<>();
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = new ArrayList<>(roles);
	}

	public void addContainer(Container container) throws ContainerException {
		if (!container.getNode().equals(this)) {
			throw new ContainerException("This container don't belong to this node");
		} if (!containers.contains(container)) {
			containers.add(container);
		}
	}
	
	public void removeContainer(Container container) {
		containers.remove(container);
	}
	
	public void clearContainers() {
		containers.clear();
	}

	public void printContainers() {
		System.out.println(containers);
	}
	
	public Node copy() throws ContainerException {
		Node node = new Node();
		node.setRoles(roles);
		for (Container cont : containers) {
			cont.setNode(node);
		}
		return node;
	}

	@Override
	public String toString() {
		return "Node [roles=" + roles + "]";
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj;
	}
}
