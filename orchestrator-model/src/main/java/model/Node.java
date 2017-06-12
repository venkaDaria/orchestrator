package model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import exception.NodeException;

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

	public void setName(String name) {
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
			throw new NodeException("Can't add container");
		}
	}

	public void addContainers(Container[] collection) {
		for (Container cont : collection) {
			addContainer(cont);
		}
	}

	public void addContainers(Collection<Container> collection) {
		for (Container cont : collection) {
			addContainer(cont);
		}
	}

	public void removeContainer(final Container container) {
		if (container != null && container.hasNode() && container.getNode().equals(this)) {
			container.setNode(null);
		} else {
			throw new NodeException("Can't remove container");
		}
	}

	public void removeContainers(Container[] collection) {
		for (Container cont : collection) {
			removeContainer(cont);
		}
	}

	public void removeContainers(Collection<Container> collection) {
		for (Container cont : collection) {
			removeContainer(cont);
		}
	}

	public void clearContainers() {
		for (Container cont : containers) {
			removeContainer(cont);
		}
	}

	public Node copy() {
		Node node = new Node();

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
		final int prime = 31;
		int result = 1;
		result = prime * result + (!hasName() ? 0 : name.hashCode());
		result = prime * result + (!hasRoles() ? 0 : roles.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
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
