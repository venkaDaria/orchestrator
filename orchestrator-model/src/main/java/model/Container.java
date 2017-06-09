package model;
import exception.ContainerException;

public class Container {
	private Status status;
	private Node node;
	private Service service;
	
	public Container() {
		status = Status.NONE;
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
	
	public void setNode(Node node) throws ContainerException {
		if (this.node != null) {
			this.node.removeContainer(this);
		}
		this.node = node;
		if (node != null) {	
			node.addContainer(this);
		}		
	}
	
	public Service getService() {
		return service;
	}
	
	public void setService(Service service) throws ContainerException {
		if (this.service != null) {
			this.service.removeContainer(this);
		}
		this.service = service;
		if (service != null) {	
			service.addContainer(this);
		}		
	}
	
	public Container copy() throws ContainerException {
		Container cont = new Container();
		cont.setNode(node.copy());
		cont.setService(service.copy());
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
