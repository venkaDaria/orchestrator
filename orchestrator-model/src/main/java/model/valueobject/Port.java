package model.valueobject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.PortValidationException;
import model.StringValueObject;

public final class Port extends StringValueObject {
	private final Protocol protocol;
	private final Integer local;
	private final Integer remote;

	public Port(final String portLine) {
		super(portLine);

		Pattern p = Pattern.compile("^(.+?)(:(.+?))?(\\/(.+)?)?$");
		Matcher m = p.matcher(portLine);

		boolean isMatch = m.matches();

		if (!isMatch || m.group(1) == null || m.group(1).trim().equals("")) {
			throw new PortValidationException();
		}

		protocol = new Protocol((m.group(5) != null) ? m.group(5) : "tcp");
		local = Integer.valueOf(m.group(1));
		remote = (m.group(3) != null) ? Integer.valueOf(m.group(3)) : null;
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