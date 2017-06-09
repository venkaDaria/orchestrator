package model;

public class ImageReference {
	private String server;
	private String name;
	private String digestTag;
	private String tag;
	
	public ImageReference(final String path) {	
		String[] tokens = path.split("/");
		if (tokens.length != 2) {
			throw new IllegalArgumentException("ImageReference must be: \"server/name:tag@digestTag\"");
		}
		server = tokens[0];
		
		String token = tokens[1];
		int colon = token.indexOf(':');
		int at = token.indexOf('@');
		
		if (colon == -1) {
			throw new IllegalArgumentException("ImageReference must be: \"server/name:tag@digestTag\"");
		}
		if (colon < at)	{
			name = token.substring(0, colon - 1);
			tag = token.substring(colon + 1, at);
			digestTag = token.substring(at + 1);
		} else if (at != -1){
			name = token.substring(0, at - 1);
			digestTag = token.substring(at + 1);
		} else {
			name = token.substring(0, colon - 1);
			tag = token.substring(colon + 1);
		}
	}

	private ImageReference(final String server, final String name, final String digestTag, final String tag) {
		this.server = server;
		this.name = name;
		this.digestTag = digestTag;
		this.tag = tag;
	}
	
	public String getServer() {
		return server;
	}
	
	public boolean hasServer() {
		return server != null;
	}

	public String getName() {
		return name;
	}
	
	public boolean hasName() {
		return name != null;
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

	public ImageReference copy() {
		return new ImageReference(server, name, digestTag, tag);
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
