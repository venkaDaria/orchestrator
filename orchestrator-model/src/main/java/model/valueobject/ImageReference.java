package model.valueobject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import exception.ImageReferenceValidationException;
import model.StringValueObject;

public final class ImageReference extends StringValueObject {
	private final String server;
	private final String name;
	private final String digestTag;
	private final String tag;

	public ImageReference(final String path) {
		super(path);

		Pattern p = Pattern.compile("^(.+?)\\/(.+?)(:(.+?))?(@(.+))?$");
		Matcher m = p.matcher(path);

		boolean isMatch = m.matches();

		if (!isMatch || m.groupCount() < 4 || StringUtils.isBlank(m.group(1)) || StringUtils.isBlank(m.group(2))
				|| StringUtils.isBlank(m.group(4)) && StringUtils.isBlank(m.group(6))) {
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
		return StringUtils.isNotBlank(digestTag);
	}

	public String getTag() {
		return tag;
	}

	public boolean hasTag() {
		return StringUtils.isNotBlank(tag);
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
