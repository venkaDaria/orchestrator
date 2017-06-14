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
		super(path, new ImageReferenceValidationException());

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
	public String asString() {
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
