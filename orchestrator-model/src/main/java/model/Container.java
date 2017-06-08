package model;
import java.util.ArrayList;
import java.util.List;

import exception.ContainerException;
import exception.ServiceException;

public class Container {
	private Status status = Status.NONE;
	private Node node;
	private Service service;
	
	public Container(Node node, Service service) throws ContainerException {
		this.node = node;
		this.service = service;
		node.addContainer(this);
		service.addContainer(this);
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
	
	public Service getService() {
		return service;
	}
	
	public boolean setService(Service service) {
		if (status == Status.NONE) {
			this.service = service;
			return true;
		}
		return false;
	}

	public boolean start() throws ServiceException {
		List<Role> roles = new ArrayList<>(service.getRoles()); 
		roles.retainAll(node.getRoles());
		if (roles.size() == 0) {
			throw new ServiceException("Can't start this service");
		}
		if (status == Status.ACTIVE || status == Status.NONE) {
			return false;
		}
		status = Status.ACTIVE;
		System.out.println("Service started: " + service.getName());
		return true;
	}
	
	public boolean stop() {
		if (status == Status.STOPPED || status == Status.NONE)
			return false;
		status = Status.STOPPED;
		System.out.println("Service stopped: " + service.getName());
		return true;
	}

	@Override
	public String toString() {
		return "Container [status=" + status + ", node=" + node + ", service=" + service + "]";
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
