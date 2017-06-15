package com.globallogic.orchestrator.model.base;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.globallogic.orchestrator.model.BusinessObject;
import com.globallogic.orchestrator.model.entity.Configuration;
import com.globallogic.orchestrator.model.entity.Node;
import com.globallogic.orchestrator.model.entity.Service;

public abstract class ConfigurationBase extends BusinessObject {
	private Set<Node> nodes;
	private Set<Service> services;

	public Set<Node> getNodes() {
		return getUnmodifiableSet(nodes);
	}

	public void setNodes(final Collection<Node> nodes) {
		this.nodes = new HashSet<>(nodes);
	}

	public boolean hasNodes() {
		return CollectionUtils.isNotEmpty(nodes);
	}

	public Set<Service> getServices() {
		return getUnmodifiableSet(services);
	}

	public void setServices(final Collection<Service> services) {
		this.services = new HashSet<>(services);
	}

	public boolean hasServices() {
		return CollectionUtils.isNotEmpty(services);
	}

	public Configuration copy() {
		Configuration con = new Configuration();

		con.setNodes(nodes.stream().map(Node::copy).collect(Collectors.toSet()));
		con.setServices(services.stream().map(Service::copy).collect(Collectors.toSet()));

		return con;
	}
}
