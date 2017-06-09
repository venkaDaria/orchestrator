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
	
	public Configuration copy() {
		Configuration con = new Configuration();
		List<Node> nodes = new ArrayList<>();
		for (final Node node : this.nodes) {
			nodes.add(node.copy());
		}
		con.setNodes(nodes);
		List<Service> services = new ArrayList<>();
		for (final Service service : this.services) {
			services.add(service.copy());
		}
		con.setServices(services);
		return con;
	}
}