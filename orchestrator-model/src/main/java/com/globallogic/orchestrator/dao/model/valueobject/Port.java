package com.globallogic.orchestrator.dao.model.valueobject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.globallogic.orchestrator.dao.exception.PortValidationException;
import com.globallogic.orchestrator.dao.model.StringValueObject;

public final class Port extends StringValueObject {
	private final Protocol protocol;
	private final Integer local;
	private final Integer remote;

	public Port(final String portLine) {
		super(portLine);

		Pattern p = Pattern.compile("^(.+?)(:(.+?))?(\\/(.+)?)?$");
		Matcher m = p.matcher(portLine);

		boolean isMatch = m.matches();

		if (!isMatch || StringUtils.isBlank(m.group(1))) {
			throw new PortValidationException();
		}

		protocol = new Protocol(StringUtils.isNotBlank(m.group(5)) ? m.group(5) : "tcp");
		local = Integer.valueOf(m.group(1));
		remote = StringUtils.isNotBlank(m.group(3)) ? Integer.valueOf(m.group(3)) : null;
	}

	public Protocol getProtocol() {
		return protocol;
	}

	public Integer getLocal() {
		return local;
	}

	public Integer getRemote() {
		return remote;
	}

	public boolean hasRemote() {
		return remote != null;
	}

	@Override
	public String asString() {
		return "Port [protocol=" + protocol + ", local=" + local + ", remote=" + remote + "]";
	}

	@Override
	public String getValue() {
		String line = local.toString();

		if (hasRemote()) {
			line += ":" + remote;
		}

		line += "/" + protocol.getValue();
		return line;
	}
}