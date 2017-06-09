package model;

public class Volume {
	private String local;
	private String remote;
	
	public Volume(final String volumeLine) {
		String[] volumes = volumeLine.split(":");
		if (volumes.length != 2) {
			throw new IllegalArgumentException("Volume must be: \"string:string\"");
		}
		local = volumes[0];
		remote = volumes[1];
	}
	
	private Volume(final String local, final String remote) {
		this.local = local;
		this.remote = remote;
	}
	
	public String getLocal() {
		return local;
	}
	
	public boolean hasLocal() {
		return local != null;
	}

	public String getRemote() {
		return remote;
	}
	
	public boolean hasRemote() {
		return remote != null;
	}
	
	public Volume copy() {
		return new Volume(local, remote);
	}

	@Override
	public String toString() {
		return local + ":" + remote;
	}
}
