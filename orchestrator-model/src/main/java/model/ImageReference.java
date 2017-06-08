package model;

public class ImageReference {
	private String server;
	private String name;
	private String digestTag;
	private String tag;
	
	public ImageReference(String path) {	
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

	private ImageReference(String server, String name, String digestTag, String tag) {
		this.server = server;
		this.name = name;
		this.digestTag = digestTag;
		this.tag = tag;
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
	
	public String getTag() {
		return tag;
	}

	public ImageReference copy() {
		return new ImageReference(server, name, digestTag, tag);
	}

	@Override
	public String toString() {
		String line = server + "/" + name;
		if (tag != null) {
			line += ":" + tag;
		}
		if (digestTag != null) {
			line += "@" + digestTag;
		}
		return line;
	}
}
