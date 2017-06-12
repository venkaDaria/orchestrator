package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import exception.NodeException;

public class Node {
	private Set<Role> roles;
	private transient List<Container> containers;

	public Node() {
		this.containers = new ArrayList<>();
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

	public List<Container> getContainers() {
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

	public void addContainers(Iterable<Container> collection) {
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

	public void removeContainers(Iterable<Container> collection) {
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
		result = prime * result + ((containers == null) ? 0 : containers.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Node other = (Node) obj;

		if (!hasContainers() && other.hasContainers() || hasContainers() && !containers.equals(other.containers))
			return false;
		if (!hasRoles() && other.hasRoles() || hasRoles() && !roles.equals(other.roles))
			return false;

		return true;
	}
}
