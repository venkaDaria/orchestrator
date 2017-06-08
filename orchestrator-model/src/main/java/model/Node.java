package model;

import java.util.ArrayList;
import java.util.List;

import exception.ContainerException;

public class Node {
	private List<Role> roles;
	private List<Container> containers;
	
	public Node(List<Role> roles) {
		this(roles, null);
	}
	
	public Node(List<Role> roles, List<Container> containers) {
		this.roles = new ArrayList<>(roles);
		if (containers != null) {
			this.containers = new ArrayList<>(containers);
		} else {
			this.containers = new ArrayList<>();
		}
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

	@Override
	public String toString() {
		return "Node [roles=" + roles + "]";
	}
}
