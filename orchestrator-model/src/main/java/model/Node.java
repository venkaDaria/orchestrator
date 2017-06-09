package model;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<Container> getContainers() {
		return containers;
	}

	public void addContainer(Container container) {
		if (container.getNode() == null || !container.getNode().equals(this)) {
			container.setNode(this);
		} if (!containers.contains(container)) {
			containers.add(container);
		}
	}
	
	public void removeContainer(Container container) {
		if (container.getNode() != null && container.getNode().equals(this)) {
			container.setNode(null);
		} if (containers.contains(container)) {
			containers.remove(container);
		}
	}
	
	public void clearContainers() {
		for (Container cont : containers) {
			removeContainer(cont);
		}
	}

	public Node copy() {
		Node node = new Node();
		List<Role> roles = new ArrayList<>();
		for (final Role role : this.roles) {
			roles.add(role.copy());
		}
		node.setRoles(roles);
		for (final Container cont : containers) {
			Container container = new Container();
			container.setService(cont.getService().copy());
			container.setNode(node);
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
