package model;

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
	
	public void setStatus(final Status status) {
		this.status = status;
	}
	
	public Node getNode() {
		return node;
	}
	
	public void setNode(final Node node) {
		if (this.node != null) {
			this.node.getContainers().remove(this);
		}
		this.node = node;
		if (this.node != null) {	
			this.node.getContainers().add(this);
		}		
	}
	
	public Service getService() {
		return service;
	}
	
	public void setService(final Service service) {
		if (this.service != null) {
			this.service.getContainers().remove(this);
		}
		this.service = service;
		if (service != null) {	
			service.getContainers().add(this);
		}		
	}
	
	public Container copy() {
		Container cont = new Container();
		cont.setNode(node.copy());
		cont.setService(service.copy());
		cont.setStatus(status);
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
	public boolean equals(final Object obj) {
		return this == obj;
	}
}
