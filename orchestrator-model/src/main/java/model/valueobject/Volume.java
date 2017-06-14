package model.valueobject;

import exception.VolumeValidationException;
import model.ValueObject;

public final class Volume extends ValueObject<String> {
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
	public String asFormattedString() {
		return "Volume [local=" + local + ", remote=" + remote + "]";
	}

	@Override
	public String getValue() {
		return local + ":" + remote;
	}
}
