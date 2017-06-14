package model.base;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import exception.NodeValidationException;
import model.Entity;
import model.entity.Container;
import model.entity.Node;
import model.entity.Service;
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

	public abstract void addContainer(final Container container);

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
		return copy(null);
	}

	public Node copy(Service service) {
		Node node = new Node();

		node.setRoles(roles);
		node.setName(name);

		for (final Container cont : containers) {
			Container container = new Container();
			container.setId(cont.getId());
			container.setService((service == null) ? cont.getService().copy(node) : service);
			container.setNode(node);
		}
		return node;
	}
}
