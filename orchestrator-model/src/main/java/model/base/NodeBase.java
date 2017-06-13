package model.base;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import exception.NodeValidationException;
import model.entity.Container;
import model.entity.Entity;
import model.entity.Node;
import model.valueobject.Role;

public abstract class NodeBase extends Entity {
	private String name;
	private Set<Role> roles;
	private transient Set<ContainerBase> containers;

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

	public Set<ContainerBase> getContainers() {
		return containers;
	}

	public boolean hasContainers() {
		return containers != null && !containers.isEmpty();
	}

	public void addContainer(final ContainerBase container) {
		if (container != null && (!container.hasNode() || !container.getNode().equals(this))) {
			container.setNode(this);
		} else {
			throw new NodeValidationException("Can't add container");
		}
	}

	public void addContainers(final ContainerBase[] collection) {
		for (ContainerBase cont : collection) {
			addContainer(cont);
		}
	}

	public void addContainers(final Collection<ContainerBase> collection) {
		for (ContainerBase cont : collection) {
			addContainer(cont);
		}
	}

	public void removeContainer(final ContainerBase container) {
		if (container != null && container.hasNode() && container.getNode().equals(this)) {
			container.setNode(null);
		} else {
			throw new NodeValidationException("Can't remove container");
		}
	}

	public void removeContainers(final ContainerBase[] collection) {
		for (ContainerBase cont : collection) {
			removeContainer(cont);
		}
	}

	public void removeContainers(final Collection<ContainerBase> collection) {
		for (ContainerBase cont : collection.toArray(new ContainerBase[] {})) {
			removeContainer(cont);
		}
	}

	public void clearContainers() {
		removeContainers(containers);
	}
	
	public Node copy() {
		return copy(null);
	}

	public Node copy(ServiceBase service) {
		Node node = new Node();

		node.setRoles(roles);
		node.setName(name);
		
		for (final ContainerBase cont : containers) {
			ContainerBase container = new Container();
			container.setId(cont.getId());
			container.setService((service == null) ? cont.getService().copy(node) : service);
			container.setNode(node);
		}
		return node;
	}
}
