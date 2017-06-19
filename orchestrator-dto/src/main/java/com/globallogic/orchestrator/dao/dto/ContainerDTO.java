package com.globallogic.orchestrator.dao.dto;

public class ContainerDTO {
	private String id;
	private String status;
	private String nodeName;
	private String serviceName;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(final String nodeName) {
		this.nodeName = nodeName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(final String serviceName) {
		this.serviceName = serviceName;
	}
}
