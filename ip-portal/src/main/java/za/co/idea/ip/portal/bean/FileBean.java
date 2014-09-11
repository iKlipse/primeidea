package za.co.idea.ip.portal.bean;

import java.io.Serializable;

import org.primefaces.model.DefaultStreamedContent;

public class FileBean implements Serializable {
	private static final long serialVersionUID = 4573167400085349293L;
	private String url;
	private long size;
	private long id;
	private String name;
	private String type;
	private String loc;
	private DefaultStreamedContent content;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public DefaultStreamedContent getContent() {
		return content;
	}

	public void setContent(DefaultStreamedContent content) {
		this.content = content;
	}

}
