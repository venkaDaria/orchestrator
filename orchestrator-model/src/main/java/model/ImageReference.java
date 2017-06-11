package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.ImageReferenceException;

public final class ImageReference {
	private final String server;
	private final String name;
	private final String digestTag;
	private final String tag;

	public ImageReference(final String path) {
		Pattern p = Pattern.compile("^(.+?)\\/(.+?)(:(.+?))?(@(.+))?$");  
        Matcher m = p.matcher(path);  
        m.matches();
		
        if (m.groupCount() < 4 || m.group(1) == null || m.group(2) == null) {
        	throw new ImageReferenceException("ImageReference must be: \"server/name:tag@digestTag\"");
        }
        	
		server = m.group(1);
		name = m.group(2);
		tag = m.group(4);
		digestTag = m.group(6);
	}

	public String getServer() {
		return server;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDigestTag() {
		return digestTag;
	}

	public boolean hasDigestTag() {
		return digestTag != null;
	}

	public String getTag() {
		return tag;
	}

	public boolean hasTag() {
		return tag != null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + digestTag.hashCode();
		result = prime * result + name.hashCode();
		result = prime * result + server.hashCode();
		result = prime * result + tag.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		ImageReference other = (ImageReference) obj;
		return name.equals(other.name) && server.equals(other.server) && tag.equals(other.tag)
				&& digestTag.equals(other.digestTag);
	}

	@Override
	public String toString() {
		String line = server + "/" + name;
		if (hasTag()) {
			line += ":" + tag;
		}
		if (hasDigestTag()) {
			line += "@" + digestTag;
		}
		return line;
	}
}
