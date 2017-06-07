
public class Volume {
	private String local;
	private String remote;
	
	public Volume(String volumeLine) {
		String[] volumes = volumeLine.split(":");
		if (volumes.length != 2) {
			throw new IllegalArgumentException();
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
	
	public Volume clone() {
		return new Volume(local, remote);
	}
}
