package model.valueobject;

import exception.VolumeValidationException;

public final class Volume extends ValueObject<String>{
	private final String local;
	private final String remote;

	public Volume(final String volumeLine) {
		String[] volumes = volumeLine.split(":");
		
		if (volumes.length != 2 || volumes[0].trim().equals("") || volumes[1].trim().equals("")) {
			throw new VolumeValidationException();
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + local.hashCode();
		result = prime * result + remote.hashCode();
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		
		Volume other = (Volume) obj;
		return local.equals(other.local) && remote.equals(other.remote);
	}

	@Override
	public String asFormattedString() {
		return "Volume [local=" + local + ", remote=" + remote + "]";
	}

	@Override
	public String getValue() {
		return local + ":" + remote;
	}
}
