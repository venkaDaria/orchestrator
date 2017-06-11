package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Service {
	private String name;
	private ImageReference image;
	private Set<Volume> volumes;
	private Set<Port> ports;
	private Set<Role> roles;
	private List<Container> containers;

	public Service() {
		this.containers = new ArrayList<>();
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

	public List<Container> getContainers() {
		return containers;
	}

	public boolean hasContainers() {
		return containers != null && !containers.isEmpty();
	}

	public void addContainer(final Container container) {
		if (container != null && (!container.hasService() || !container.getService().equals(this))) {
			container.setStatus(Status.STOPPED);
			container.setService(this);
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
		if (container != null && container.hasService() && container.getService().equals(this)) {
			container.setStatus(Status.NONE);
			container.setService(null);
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

	public List<Node> getNodes() {
		List<Node> nodes = new ArrayList<>();
		for (Container cont : containers) {
			if (!nodes.contains(cont.getNode())) {
				nodes.add(cont.getNode());
			}
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

		for (final Container cont : containers) {
			Container container = new Container();
			container.setService(service);
			container.setNode(cont.getNode().copy());
		}
		return service;
	}

	@Override
	public String toString() {
		return "Service [name=" + name + ", image=" + image + ", volumes=" + volumes + ", ports=" + ports + ", roles="
				+ roles + "]";
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		return this == obj;
	}
}
