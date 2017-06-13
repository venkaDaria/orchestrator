package model.base;

import model.Status;
import model.entity.Container;
import model.entity.Entity;

public abstract class ContainerBase extends Entity {
	private String id;
	private Status status;
	private NodeBase node;
	private ServiceBase service;

	public ContainerBase() {
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

	public NodeBase getNode() {
		return node;
	}

	public void setNode(final NodeBase node) {
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

	public ServiceBase getService() {
		return service;
	}

	public void setService(final ServiceBase service) {
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
}
