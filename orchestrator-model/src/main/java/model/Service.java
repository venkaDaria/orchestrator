package model;
import java.util.ArrayList;
import java.util.List;

public class Service {
	private String name;
	private ImageReference image;
	private List<Volume> volumes;
	private List<Port> ports;
	private List<Role> roles;
	private List<Container> containers;

	public Service(String name, ImageReference image, List<Volume> volumes, List<Port> ports, List<Role> roles) {
		this.name = name;
		this.image = image;
		this.volumes = new ArrayList<>(volumes);
		this.ports = new ArrayList<>(ports);
		this.roles = new ArrayList<>(roles);
		this.containers = new ArrayList<>();
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ImageReference getImage() {
		return image;
	}
	
	public void setImage(ImageReference image) {
		this.image = image.clone();
	}
	
	public List<Volume> getVolumes() {
		return volumes;
	}
	
	public void setVolumes(List<Volume> volumes) {
		this.volumes = new ArrayList<>(volumes);
	}
	
	public List<Port> getPorts() {
		return ports;
	}
	
	public void setPorts(List<Port> ports) {
		this.ports = new ArrayList<>(ports);
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = new ArrayList<>(roles);
	}
	
	public List<Container> getContainers() {
		return containers;
	}
	
	public void setContainers(List<Container> containers) {
		this.containers = new ArrayList<>(containers);
	}
	
	public List<Node> getNodes() {
		List<Node> nodes = new ArrayList<>();
		for (Container cont : containers) {
			if (!nodes.contains(cont.getNode())) {
				nodes.add(cont.getNode());
			}
		}
		return nodes;
	}
}
