package model;

public class Container {
	private String id;
	private Status status;
	private Node node;
	private Service service;

	public Container() {
		status = Status.NONE;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}
	
	public boolean hasId() {
		return id != null;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(final Status status) {
		this.status = status;
	}

	public boolean hasStatus() {
		return status != null;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(final Node node) {
		if (hasNode()) {
			this.node.getContainers().remove(this);
		}

		this.node = node;

		if (hasNode()) {
			this.node.getContainers().add(this);
		}
	}

	public boolean hasNode() {
		return node != null;
	}

	public Service getService() {
		return service;
	}

	public void setService(final Service service) {
		if (hasService()) {
			this.service.getContainers().remove(this);
		}

		this.service = service;

		if (hasService()) {
			this.service.getContainers().add(this);
		}
	}

	public boolean hasService() {
		return service != null;
	}

	public Container copy() {
		Container cont = new Container();
		cont.setId(id);
		cont.setNode(node.copy());
		cont.setService(service.copy());
		cont.setStatus(status);
		return cont;
	}

	@Override
	public String toString() {
		return "Container [id = " + id + ", status=" + status + ", node=" + node + ", service=" + service + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (!hasId() ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Container other = (Container) obj;
		if (!hasService() && other.hasService() || hasService() && !service.equals(other.service))
			return false;
		if (!hasNode() && other.hasNode() || hasNode() && !node.equals(other.node))
			return false;
		if (!hasId() && other.hasId() || hasId() && !id.equals(other.id))
			return false;
		
		return status == other.status;
	}
}
