package model;

public class Port {
	private Integer local;
	private Integer remote;
	
	public Port(final String portLine) {
		String[] ports = portLine.split(":");
		if (ports.length != 2 && ports.length != 1) {
			throw new IllegalArgumentException("Port must be: \"int:int\" or \"int\"");
		}
		local = Integer.valueOf(ports[0]);
		if (ports.length == 2) {
			remote = Integer.valueOf(ports[1]);
		}
	}
	
	private Port(final Integer local, final Integer remote) {
		this.local = local;
		this.remote = remote;
	}

	public Integer getLocal() {
		return local;
	}
	
	public boolean hasLocal() {
		return local != null;
	}

	public Integer getRemote() {
		return remote;
	}
	
	public boolean hasRemote() {
		return remote != null;
	}
	
	public Port copy() {
		return new Port(local, remote);
	}

	@Override
	public String toString() {
		return local + ":" + remote;
	}
}