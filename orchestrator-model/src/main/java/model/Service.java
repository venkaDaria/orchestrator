package model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import exception.ServiceException;

public class Service {
	private String name;
	private ImageReference image;
	private Set<Volume> volumes;
	private Set<Port> ports;
	private Set<Role> roles;
	private transient Set<Container> containers;

	public Service() {
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

	public Set<Container> getContainers() {
		return containers;
	}

	public boolean hasContainers() {
		return containers != null && !containers.isEmpty();
	}

	public void addContainer(final Container container) {
		if (container != null && (!container.hasService() || !container.getService().equals(this))) {
			container.setStatus(Status.STOPPED);
			container.setService(this);
		} else {
        	throw new ServiceException("Can't add container");
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
		if (container != null && container.hasService() && container.getService().equals(this)) {
			container.setService(null);
			container.setStatus(Status.NONE);
		} else {
        	throw new ServiceException("Can't remove container");
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
		final int prime = 31;
		int result = 1;
		result = prime * result + (!hasImage() ? 0 : image.hashCode());
		result = prime * result + (!hasName() ? 0 : name.hashCode());
		result = prime * result + (!hasPorts() ? 0 : ports.hashCode());
		result = prime * result + (!hasRoles() ? 0 : roles.hashCode());
		result = prime * result + (!hasVolumes() ? 0 : volumes.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Service other = (Service) obj;

		if (!hasImage() && other.hasImage() || hasImage() && !image.equals(other.image))
			return false;
		if (!hasName() && other.hasName() || hasName() && !name.equals(other.name))
			return false;
		if (!hasPorts() && other.hasPorts() || hasPorts() && !ports.equals(other.ports))
			return false;
		if (!hasRoles() && other.hasRoles() || hasRoles() && !roles.equals(other.roles))
			return false;
		if (!hasVolumes() && other.hasVolumes() || hasVolumes() && !volumes.equals(other.volumes))
			return false;
		
		return true;
	}
}
