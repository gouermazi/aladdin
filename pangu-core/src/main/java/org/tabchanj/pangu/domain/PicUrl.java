package org.tabchanj.pangu.domain;

public class PicUrl {
	private int id;
	private String url;
	private String localurl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLocalurl() {
		return localurl;
	}

	public void setLocalurl(String localurl) {
		this.localurl = localurl;
	}

	@Override
	public String toString() {
		return "PicUrl [id=" + id + ", url=" + url + ", localurl=" + localurl + "]";
	}

}
