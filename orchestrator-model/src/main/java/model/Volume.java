package model;

import exception.VolumeException;

public final class Volume {
	private final String local;
	private final String remote;

	public Volume(final String volumeLine) {
		String[] volumes = volumeLine.split(":");
		if (volumes.length != 2 || volumes[0].trim().equals("") || volumes[1].trim().equals("")) {
			throw new VolumeException("Volume must be: \"string:string\"");
		}
		local = volumes[0];
		remote = volumes[1];
	}

	public String getLocal() {
		return local;
	}

	public String getRemote() {
		return remote;
	}

	@Override
	public String toString() {
		return local + ":" + remote;
	}

	@Override
	public int hashCode() {
		return local.hashCode() * 2 + remote.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null || !(obj instanceof Volume))
			return false;
		Volume volume = (Volume) obj;
		return local.equals(volume.getLocal()) && remote.equals(volume.getRemote());
	}
}
