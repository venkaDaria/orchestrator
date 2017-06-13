package model.base;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import model.entity.Configuration;
import model.entity.Entity;
import model.entity.Node;
import model.entity.Service;

public abstract class ConfigurationBase extends Entity {
	private Set<Node> nodes;
	private Set<Service> services;

	public Set<Node> getNodes() {
		return nodes;
	}

	public void setNodes(final Collection<Node> nodes) {
		this.nodes = new HashSet<>(nodes);
	}

	public boolean hasNodes() {
		return nodes != null && !nodes.isEmpty();
	}

	public Set<Service> getServices() {
		return services;
	}

	public void setServices(final Collection<Service> services) {
		this.services = new HashSet<>(services);
	}

	public boolean hasServices() {
		return services != null && !services.isEmpty();
	}

	public Configuration copy() {
		Configuration con = new Configuration();
		con.setNodes(nodes.stream().map(Node::copy).collect(Collectors.toSet()));
		con.setServices(services.stream().map(Service::copy).collect(Collectors.toSet()));
		return con;
	}
}
