import java.util.ArrayList;
import java.util.List;

public class Node {
	private List<Role> roles;
	private List<Container> containers;
	
	public Node() {
		roles = new ArrayList<>();
		containers = new ArrayList<>();
	}
	
	public Node(List<Role> roles, List<Container> containers) {
		roles = new ArrayList<>(roles);
		containers = new ArrayList<>(containers);
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

	public void setServices(List<Container> containers) {
		this.containers = new ArrayList<>(containers);
	}
}
