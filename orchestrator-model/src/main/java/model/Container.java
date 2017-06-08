package model;
import exception.ContainerException;

public class Container {
	private Status status = Status.NONE;
	private Node node;
	private Service service;

	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Node getNode() {
		return node;
	}
	
	public void setNode(Node node) throws ContainerException {
		this.node = node;
		node.addContainer(this);
	}
	
	public Service getService() {
		return service;
	}
	
	public void setService(Service service) throws ContainerException {
		this.service = service;
		service.addContainer(this);
	}
	
	public Container copy() throws ContainerException {
		Container cont = new Container();
		cont.setNode(node);
		cont.setService(service);
		return cont;
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
