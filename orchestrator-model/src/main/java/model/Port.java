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
		return local.hashCode() * 2 + remote.hashCode() + protocol.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null || !(obj instanceof Volume))
			return false;
		Port port = (Port) obj;
		return local.equals(port.getLocal()) && remote.equals(port.getRemote()) && protocol.equals(port.getProtocol());
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