package model.base;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import exception.ServiceValidationException;
import model.Entity;
import model.Status;
import model.entity.Container;
import model.entity.Node;
import model.entity.Service;
import model.valueobject.ImageReference;
import model.valueobject.Port;
import model.valueobject.Role;
import model.valueobject.Volume;

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
		return name != null;
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
		return volumes != null && !volumes.isEmpty();
	}

	public Set<Port> getPorts() {
		return getUnmodifiableSet(ports);
	}

	public void setPorts(final Collection<Port> ports) {
		this.ports = new HashSet<>(ports);
	}

	public boolean hasPorts() {
		return ports != null && !ports.isEmpty();
	}

	public Set<Role> getRoles() {
		return getUnmodifiableSet(roles);
	}

	public void setRoles(final Collection<Role> roles) {
		this.roles = new HashSet<>(roles);
	}

	public boolean hasRoles() {
		return roles != null && !roles.isEmpty();
	}

	public Set<Container> getContainers() {
		return getUnmodifiableSet(containers);
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
			if (container.hasService() && container.getService().equals(this)) {
				containers.remove(container);
				container.setStatus(Status.NONE);
				container.setService(null);
			}
		} else {
			throw new ServiceValidationException("Can't add container");
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
			if (cont.hasService()) {
				node = cont.getNode().copy();

				for (Container c : cont.getNode().getContainers()) {
					Container conts = new Container();
					conts.setId(c.getId());
					conts.setNode(node);
					if (!c.hasService()) {
						conts.setService(null);
					} else if (c.getService().equals(service)) {
						conts.setService(service);
					} else {
						conts.setService(service.copyWithContainers());
					}
				}
			}
			container.setNode(node);
		}
		return service;
	}
}
