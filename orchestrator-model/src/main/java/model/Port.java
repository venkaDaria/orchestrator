package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Port {
	private final Protocol protocol;
	private final Integer local;
	private final Integer remote;

	public Port(final String portLine) {
		Pattern p = Pattern.compile("^(.+?)(:(.+?))?(\\/(.+)?)?$");
		Matcher m = p.matcher(portLine);
		m.matches();

		protocol = new Protocol(m.group(5) != null ? m.group(5) : "tcp");
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + local.hashCode();
		result = prime * result + protocol.hashCode();
		result = prime * result + ((remote == null) ? 0 : remote.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		Port port = (Port) obj;
		boolean isEqual = local.equals(port.getLocal()) && protocol.equals(port.getProtocol());
		if (remote == null) {
			if (remote != null) {
				return false;
			} else {
				return isEqual;
			}
		}
		return isEqual && remote.equals(port.getRemote());
	}

	@Override
	public String toString() {
		String line = local.toString();
		if (hasRemote()) {
			line += ":" + remote;
		}
		line += "/" + protocol.getValue();
		return line;
	}
}