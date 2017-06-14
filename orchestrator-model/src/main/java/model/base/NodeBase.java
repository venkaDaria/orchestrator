package model.base;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import exception.NodeValidationException;
import model.Entity;
import model.entity.Container;
import model.entity.Node;
import model.valueobject.Role;

public abstract class NodeBase extends Entity {
	private String name;
	private Set<Role> roles;
	private transient Set<Container> containers;

	public NodeBase() {
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
		return Collections.unmodifiableSet(roles);
	}

	public boolean hasRoles() {
		return roles != null && !roles.isEmpty();
	}

	public void setRoles(final Collection<Role> roles) {
		this.roles = new HashSet<>(roles);
	}

	public Set<Container> getContainers() {
		return Collections.unmodifiableSet(containers);
	}

	public boolean hasContainers() {
		return containers != null && !containers.isEmpty();
	}

	public void addContainer(final Container container) {
		containers.add(container);
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

	public boolean containsContainer(Container container) {
		return containers.contains(container);
	}

	public void removeContainer(final Container container) {
		if (container != null) {
			if (container.hasNode() && container.getNode().equals(this)) {
				containers.remove(container);
				container.setNode(null);
			}
		} else {
			throw new NodeValidationException("Can't add container");
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

		// TODO: ?
		return node;
	}
}
