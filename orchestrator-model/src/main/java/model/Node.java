package model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import exception.NodeValidationException;

public class Node {
	private String name;
	private Set<Role> roles;
	private transient Set<Container> containers;

	public Node() {
		this.containers = new HashSet<>();
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}
	
	public boolean hasName() {
		return name != null;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public boolean hasRoles() {
		return roles != null && !roles.isEmpty();
	}

	public void setRoles(final Collection<Role> roles) {
		this.roles = new HashSet<>(roles);
	}

	public Set<Container> getContainers() {
		return containers;
	}

	public boolean hasContainers() {
		return containers != null && !containers.isEmpty();
	}

	public void addContainer(final Container container) {
		if (container != null && (!container.hasNode() || !container.getNode().equals(this))) {
			container.setNode(this);
		} else {
			throw new NodeValidationException("Can't add container");
		}
	}

	public void addContainers(final Container[] collection) {
		for (Container cont : collection) {
			addContainer(cont);
		}
	}

	public void addContainers(final Collection<Container> collection) {
		for (Container cont : collection) {
			addContainer(cont);
		}
	}

	public void removeContainer(final Container container) {
		if (container != null && container.hasNode() && container.getNode().equals(this)) {
			container.setNode(null);
		} else {
			throw new NodeValidationException("Can't remove container");
		}
	}

	public void removeContainers(final Container[] collection) {
		for (Container cont : collection) {
			removeContainer(cont);
		}
	}

	public void removeContainers(final Collection<Container> collection) {
		for (Container cont : collection.toArray(new Container[] {})) {
			removeContainer(cont);
		}
	}

	public void clearContainers() {
		removeContainers(containers);
	}

	public Node copy() {
		Node node = new Node();

		node.setRoles(roles);
		node.setName(name);
		
		for (final Container cont : containers) {
			Container container = new Container();
			container.setId(cont.getId());
			container.setService(cont.getService());
			container.setNode(node);
		}
		return node;
	}

	@Override
	public String toString() {
		return "Node [name=" + name + ", roles=" + roles + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (!hasName() ? 0 : name.hashCode());
		result = prime * result + (!hasRoles() ? 0 : roles.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Node other = (Node) obj;

		if (!hasRoles() && other.hasRoles() || hasRoles() && !roles.equals(other.roles))
			return false;
		if (!hasName() && other.hasName() || hasName() && !name.equals(other.name))
			return false;
		
		return true;
	}
}
