package com.globallogic.orchestrator.model.valueobject;

import org.apache.commons.lang3.StringUtils;

import com.globallogic.orchestrator.exception.VolumeValidationException;
import com.globallogic.orchestrator.model.StringValueObject;

public final class Volume extends StringValueObject {
	private final String local;
	private final String remote;

	public Volume(final String volumeLine) {
		super(volumeLine);
		String[] volumes = volumeLine.split(":");

		if (volumes.length != 2 || StringUtils.isBlank(volumes[0]) || StringUtils.isBlank(volumes[1])) {
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
	public String asString() {
		return "Volume [local=" + local + ", remote=" + remote + "]";
	}

	@Override
	public String getValue() {
		return local + ":" + remote;
	}
}
