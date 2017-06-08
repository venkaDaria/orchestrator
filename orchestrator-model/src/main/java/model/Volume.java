package model;

public class Volume {
	private String local;
	private String remote;
	
	public Volume(String volumeLine) {
		String[] volumes = volumeLine.split(":");
		if (volumes.length != 2) {
			throw new IllegalArgumentException("Volume must be: \"string:string\"");
		}
		local = volumes[0];
		remote = volumes[1];
	}
	
	private Volume(String local, String remote) {
		this.local = local;
		this.remote = remote;
	}
	
	public String getLocal() {
		return local;
	}

	public String getRemote() {
		return remote;
	}
	
	@Override
	public Volume clone() {
		return new Volume(local, remote);
	}

	@Override
	public String toString() {
		return local + ":" + remote;
	}
}
