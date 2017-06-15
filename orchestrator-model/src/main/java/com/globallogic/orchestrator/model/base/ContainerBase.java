package com.globallogic.orchestrator.model.base;

import org.apache.commons.lang3.StringUtils;

import com.globallogic.orchestrator.model.Entity;
import com.globallogic.orchestrator.model.Status;
import com.globallogic.orchestrator.model.entity.Container;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.entity.Service;

public abstract class ContainerBase extends Entity {
	private String id;
	private Status status;
	private Node node;
	private Service service;

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
		return StringUtils.isNotBlank(id);
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
		this.node = node;
	}

	public boolean hasNode() {
		return node != null;
	}

	public Service getService() {
		return service;
	}

	public void setService(final Service service) {
		this.service = service;
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
