package model.base;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import exception.ServiceValidationException;
import model.Entity;
import model.Status;
import model.entity.Container;
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
	private transient Set<ContainerBase> containers;

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
		return volumes;
	}

	public void setVolumes(final Collection<Volume> volumes) {
		this.volumes = new HashSet<>(volumes);
	}

	public boolean hasVolumes() {
		return volumes != null && !volumes.isEmpty();
	}

	public Set<Port> getPorts() {
		return ports;
	}

	public void setPorts(final Collection<Port> ports) {
		this.ports = new HashSet<>(ports);
	}

	public boolean hasPorts() {
		return ports != null && !ports.isEmpty();
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(final Collection<Role> roles) {
		this.roles = new HashSet<>(roles);
	}

	public boolean hasRoles() {
		return roles != null && !roles.isEmpty();
	}

	public Set<ContainerBase> getContainers() {
		return containers;
	}

	public boolean hasContainers() {
		return containers != null && !containers.isEmpty();
	}

	public void addContainer(final ContainerBase container) {
		if (container != null && (!container.hasService() || !container.getService().equals(this))) {
			container.setStatus(Status.STOPPED);
			container.setService(this);
		} else {
        	throw new ServiceValidationException("Can't add container");
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
		if (container != null && container.hasService() && container.getService().equals(this)) {
			container.setService(null);
			container.setStatus(Status.NONE);
		} else {
        	throw new ServiceValidationException("Can't remove container");
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

	public Set<NodeBase> getNodes() {
		Set<NodeBase> nodes = new HashSet<>();
		for (ContainerBase cont : containers) {
			nodes.add(cont.getNode());
		}
		return nodes;
	}
	
	public Service copy() {
		return copy(null);
	}

	public Service copy(NodeBase node) {
		Service service = new Service();
		service.setName(name);
		service.setImage(image);

		service.setPorts(ports);
		service.setRoles(roles);
		service.setVolumes(volumes);

		for (final ContainerBase cont : containers) {
			ContainerBase container = new Container();
			container.setId(cont.getId());
			container.setService(service);
			container.setNode((node == null) ? cont.getNode().copy(service) : node);
		}
		return service;
	}
}
