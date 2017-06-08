package model;
import java.util.ArrayList;
import java.util.List;

import exception.ContainerException;
import exception.ServiceException;

public class Container {
	private Status status;
	private Node node;
	
	public Container(Node node) throws ContainerException {
		this.node = node;
		status = Status.NONE;
		node.addContainer(this);
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Node getNode() {
		return node;
	}

	public boolean start(Service service) throws ServiceException {
		List<Role> roles = new ArrayList<>(service.getRoles()); 
		roles.retainAll(node.getRoles());
		if (roles.size() == 0) {
			throw new ServiceException("Can't start this service");
		}
		if (status == Status.ACTIVE) {
			return false;
		}
		if (status == Status.NONE) {
			service.getContainers().add(this);
		}
		status = Status.ACTIVE;
		System.out.println("Service started: " + service.getName());
		return true;
	}
	
	public boolean stop(Service service) {
		if (status == Status.STOPPED || status == Status.NONE)
			return false;
		status = Status.STOPPED;
		System.out.println("Service stopped: " + service.getName());
		return true;
	}
	
	public boolean release(Service service) {
		List<Container> containers = service.getContainers();
		if (!containers.contains(this))
			return false;
		status = Status.NONE;
		service.getContainers().remove(this);
		System.out.println("Service released: " + service.getName());
		return true;
	}

	@Override
	public String toString() {
		return "Container [status=" + status + ", node=" + node + "]";
	}
}
