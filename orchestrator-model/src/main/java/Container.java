import java.util.ArrayList;
import java.util.List;

public class Container {
	private Status status;
	private Node node;
	
	public Container(Node node) throws ContainerException {
		this.node = node;
		status = Status.NONE;
		node.addContainer(this);
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Node getNode() {
		return node;
	}

	public boolean start(Service service) throws ServiceException {
		List<Role> roles = new ArrayList<>(service.getRoles()); 
		roles.retainAll(node.getRoles());
		if (roles.size() == 0)
			throw new ServiceException("Can't start this service");
		if (status == Status.ACTIVE)
			return false;
		status = Status.ACTIVE;
		System.out.println("Server started with " + service.getName());
		return true;
	}
	
	public boolean stop(Service service) {
		if (status == Status.STOPPED)
			return false;
		status = Status.STOPPED;
		System.out.println("Server stopped with " + service.getName());
		return true;
	}
}
