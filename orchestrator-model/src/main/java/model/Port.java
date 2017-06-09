package model;

public class Port {
	private int local;
	private int remote;
	
	public Port(final String portLine) {
		String[] ports = portLine.split(":");
		if (ports.length != 2) {
			throw new IllegalArgumentException("Port must be: \"int:int\"");
		}
		local = Integer.valueOf(ports[0]);
		remote = Integer.valueOf(ports[1]);
	}
	
	private Port(final int local, final int remote) {
		this.local = local;
		this.remote = remote;
	}

	public int getLocal() {
		return local;
	}
	
	public int getRemote() {
		return remote;
	}

	public Port copy() {
		return new Port(local, remote);
	}

	@Override
	public String toString() {
		return local + ":" + remote;
	}
}
