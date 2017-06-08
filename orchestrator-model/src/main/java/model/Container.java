package model;
import exception.ContainerException;

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
