package model.valueobject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.ImageReferenceValidationException;
import model.ValueObject;

public final class ImageReference extends ValueObject<String> {
	private final String server;
	private final String name;
	private final String digestTag;
	private final String tag;

	public ImageReference(final String path) {
		Pattern p = Pattern.compile("^(.+?)\\/(.+?)(:(.+?))?(@(.+))?$");
		Matcher m = p.matcher(path);

		boolean isMatch = m.matches();

		if (!isMatch || m.groupCount() < 4 || m.group(1) == null || m.group(2) == null
				|| m.group(4) == null && m.group(6) == null) {
			throw new ImageReferenceValidationException();
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
		result = prime * result + name.hashCode();
		result = prime * result + server.hashCode();
		result = prime * result + (!hasTag() ? 0 : tag.hashCode());
		result = prime * result + (!hasDigestTag() ? 0 : digestTag.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		ImageReference other = (ImageReference) obj;
		boolean isEqual = name.equals(other.name) && server.equals(other.server);

		if (!hasDigestTag() && other.hasDigestTag()) {
			return false;
		} else if (!hasTag() && other.hasTag()) {
			return false;
		} else if (!other.hasDigestTag()) {
			return isEqual && tag.equals(other.tag);
		} else if (!other.hasTag()) {
			return isEqual && digestTag.equals(other.digestTag);
		} else {
			return isEqual && tag.equals(other.tag) && digestTag.equals(other.digestTag);
		}
	}

	@Override
	public String asFormattedString() {
		return "ImageReference [server=" + server + ", name=" + name + ", digestTag=" + digestTag + ", tag=" + tag
				+ "]";
	}

	@Override
	public String getValue() {
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
