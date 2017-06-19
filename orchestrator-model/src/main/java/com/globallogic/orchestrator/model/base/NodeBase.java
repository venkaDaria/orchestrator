package com.globallogic.orchestrator.model.base;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.globallogic.orchestrator.exception.NodeValidationException;
import com.globallogic.orchestrator.model.Entity;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.entity.Service;
import com.globallogic.orchestrator.model.valueobject.Role;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

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
		return StringUtils.isNotBlank(name);
	}

	public Set<Role> getRoles() {
		return getUnmodifiableSet(roles);
	}

	public boolean hasRoles() {
		return CollectionUtils.isNotEmpty(roles);
	}

	public void setRoles(final Collection<Role> roles) {
		this.roles = new HashSet<>(roles);
	}

	public Set<Container> getContainers() {
		return getUnmodifiableSet(containers);
	}

	public boolean hasContainers() {
		return CollectionUtils.isNotEmpty(containers);
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
		if (container == null) {
			throw new NodeValidationException("Can't remove container");
		}
		if (container.hasNode() && container.getNode().equals(this)) {
			containers.remove(container);
			container.setNode(null);
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

		return node;
	}

	public Node copyWithContainers() {
		Node node = this.copy();

		for (Container cont : containers) {
			Container container = new Container();
			container.setId(cont.getId());
			container.setNode(node);

			Service service = null;
			if (cont.hasService()) {
				service = cont.getService().copy();

				Set<Node> nodes = new HashSet<>();
				for (Container c : cont.getService().getContainers()) {
					Container conts = new Container();
					conts.setId(c.getId());
					conts.setService(service);
					if (!c.hasNode()) {
						conts.setNode(null);
					} else if (c.getNode().equals(node)) {
						conts.setNode(node);
					} else {
						Node n = nodes.stream().filter(c.getNode()::equals).findAny().orElse(node.copyWithContainers());
						conts.setNode(n);
						nodes.add(n);
					}
				}
			}
			container.setService(service);
		}
		return node;
	}
}
