package model;
import java.util.ArrayList;
import java.util.List;

public class Configuration {
	private List<Node> nodes;
	private List<Service> services;
	
	public Configuration() {
		nodes = new ArrayList<>();
		services = new ArrayList<>();
	}
	
	public Configuration(final List<Node> nodes, final List<Service> services) {
		this.nodes = new ArrayList<>(nodes);
		this.services = new ArrayList<>(services);
	}
	
	public List<Node> getNodes() {
		return nodes;
	}
	
	public void setNodes(List<Node> nodes) {
		this.nodes = new ArrayList<>(nodes);
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = new ArrayList<>(services);
	}
}
