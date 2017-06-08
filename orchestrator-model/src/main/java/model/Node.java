package model;
import java.util.ArrayList;
import java.util.List;

public class Node {
	private List<Role> roles;
	private List<Container> containers;
	
	public Node(List<Role> roles) {
		this.roles = new ArrayList<>(roles);
		this.containers = new ArrayList<>();
	}
	
	public Node(List<Role> roles, List<Container> containers) {
		this(roles);
		this.containers = new ArrayList<>(containers);
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = new ArrayList<>(roles);
	}

	public void addContainer(Container container) throws ContainerException {
		if (!container.getNode().equals(this))
			throw new ContainerException("This container don't belong this node");
		containers.add(container);
	}
	
	public void removeContainer(Container container) {
		containers.remove(container);
	}
	
	public void clearContainers() {
		containers.clear();
	}

	@Override
	public int hashCode() {
		return roles.hashCode() * 2 + containers.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Node))
			return false;
		Node node = (Node)obj;
		return roles.equals(node.roles) && containers.equals(node.containers);
	}
}
