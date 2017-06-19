package com.globallogic.orchestrator.dao.model.base;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.globallogic.orchestrator.dao.model.Entity;
import com.globallogic.orchestrator.dao.model.valueobject.ImageReference;
import com.globallogic.orchestrator.dao.model.valueobject.Port;
import com.globallogic.orchestrator.dao.model.valueobject.Volume;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.globallogic.orchestrator.dao.exception.ServiceValidationException;
import com.globallogic.orchestrator.dao.model.Status;
import com.globallogic.orchestrator.dao.model.entity.Container;
import com.globallogic.orchestrator.dao.model.entity.Node;
import com.globallogic.orchestrator.dao.model.entity.Service;
import com.globallogic.orchestrator.dao.model.valueobject.Role;

public abstract class ServiceBase extends Entity {
	private String name;
	private ImageReference image;
	private Set<Volume> volumes;
	private Set<Port> ports;
	private Set<Role> roles;
	private transient Set<Container> containers;

	public ServiceBase() {
		this.containers = new HashSet<>();
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public boolean hasName() {
		return StringUtils.isNotEmpty(name);
	}

	public ImageReference getImage() {
		return image;
	}

	public void setImage(final ImageReference image) {
		this.image = image;
	}

	public boolean hasImage() {
		return image != null;
	}

	public Set<Volume> getVolumes() {
		return getUnmodifiableSet(volumes);
	}

	public void setVolumes(final Collection<Volume> volumes) {
		this.volumes = new HashSet<>(volumes);
	}

	public boolean hasVolumes() {
		return CollectionUtils.isNotEmpty(volumes);
	}

	public Set<Port> getPorts() {
		return getUnmodifiableSet(ports);
	}

	public void setPorts(final Collection<Port> ports) {
		this.ports = new HashSet<>(ports);
	}

	public boolean hasPorts() {
		return CollectionUtils.isNotEmpty(ports);
	}

	public Set<Role> getRoles() {
		return getUnmodifiableSet(roles);
	}

	public void setRoles(final Collection<Role> roles) {
		this.roles = new HashSet<>(roles);
	}

	public boolean hasRoles() {
		return CollectionUtils.isNotEmpty(roles);
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
			throw new ServiceValidationException("Can't remove container");
		}
		if (container.hasService() && container.getService().equals(this)) {
			containers.remove(container);
			container.setStatus(Status.NONE);
			container.setService(null);
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

	public Set<Node> getNodes() {
		Set<Node> nodes = new HashSet<>();
		for (Container cont : containers) {
			nodes.add(cont.getNode());
		}
		return nodes;
	}

	public Service copy() {
		Service service = new Service();
		service.setName(name);
		service.setImage(image);

		service.setPorts(ports);
		service.setRoles(roles);
		service.setVolumes(volumes);

		return service;
	}

	public Service copyWithContainers() {
		Service service = this.copy();

		for (Container cont : containers) {
			Container container = new Container();
			container.setId(cont.getId());
			container.setService(service);

			Node node = null;
			if (cont.hasNode()) {
				node = cont.getNode().copy();

				Set<Service> services = new HashSet<>();
				for (Container c : cont.getNode().getContainers()) {
					Container conts = new Container();
					conts.setId(c.getId());
					conts.setNode(node);
					if (!c.hasService()) {
						conts.setService(null);
					} else if (c.getService().equals(service)) {
						conts.setService(service);
					} else {
						Service s = services.stream().filter(c.getService()::equals).findAny()
								.orElse(service.copyWithContainers());
						conts.setService(s);
						services.add(s);
					}
				}
			}
			container.setNode(node);
		}
		return service;
	}
}
