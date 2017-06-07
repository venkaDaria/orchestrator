
public class ImageReference {
	private String server;
	private String name;
	private String digestTag;
	private String tag;
	
	public ImageReference(String path) {	
		// TODO: parse string	
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
	
	public ImageReference clone() {
		return new ImageReference(server, name, digestTag, tag);
	}
}
