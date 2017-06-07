
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

	public boolean start(Service service) {
		if (status == Status.ACTIVE || status == Status.NONE)
			return false;
		status = Status.ACTIVE;
		System.out.println("Server started with" + service.getName());
		return true;
	}
	
	public boolean stop(Service service) {
		if (status == Status.STOPPED || status == Status.NONE)
			return false;
		status = Status.STOPPED;
		System.out.println("Server stopped with" + service.getName());
		return true;
	}
}
