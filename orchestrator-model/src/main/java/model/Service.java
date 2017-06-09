package model;
import java.util.ArrayList;
import java.util.List;

import exception.ContainerException;

public class Service {
	private String name;
	private ImageReference image;
	private List<Volume> volumes;
	private List<Port> ports;
	private List<Role> roles;
	private List<Container> containers;

	public Service() {
		this.volumes = new ArrayList<>();
		this.ports = new ArrayList<>();
		this.roles = new ArrayList<>();
		this.containers = new ArrayList<>();
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ImageReference getImage() {
		return image;
	}
	
	public void setImage(ImageReference image) {
		this.image = image.copy();
	}
	
	public List<Volume> getVolumes() {
		return volumes;
	}
	
	public void setVolumes(List<Volume> volumes) {
		this.volumes = new ArrayList<>(volumes);
	}
	
	public List<Port> getPorts() {
		return ports;
	}
	
	public void setPorts(List<Port> ports) {
		this.ports = new ArrayList<>(ports);
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = new ArrayList<>(roles);
	}
	
	public void addContainer(Container container) throws ContainerException {
		if (!container.getService().equals(this)) {
			throw new ContainerException("This container don't belong to this service");
		} if (!containers.contains(container)) {
			containers.add(container);
			container.setStatus(Status.STOPPED);
		}
	}
	
	public void removeContainer(Container container) throws ContainerException {
		if (!container.getService().equals(this)) {
			throw new ContainerException("This container don't belong to this service");
		} 
		if (containers.contains(container)) {
			container.setStatus(Status.NONE);
			containers.remove(container);
		}
	}
	
	public void clearContainers() throws ContainerException {
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
	
	public Service copy() throws ContainerException {
		Service service = new Service();
		service.setName(name);
		service.setImage(image.copy());
		
		List<Port> ports = new ArrayList<>();
		for (final Port port : this.ports) {
			ports.add(port.copy());
		}
		service.setPorts(ports);

		List<Volume> volumes = new ArrayList<>();
		for (final Volume volume : this.volumes) {
			volumes.add(volume.copy());
		}
		service.setVolumes(volumes);
		
		List<Role> roles = new ArrayList<>();
		for (final Role role : this.roles) {
			roles.add(role.copy());
		}
		service.setRoles(roles);
		
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
	public boolean equals(Object obj) {
		return this == obj;
	}
}
