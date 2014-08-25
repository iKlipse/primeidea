package za.co.idea.ip.portal.bean;

import java.io.Serializable;
import java.util.Date;

public class NewsBean implements Serializable {

	private static final long serialVersionUID = 4972233633168449553L;
	private Long nId;
	private String nTitle;
	private String nContent;
	private String newsUrl;
	private boolean nwImgAvail;
	private Date startDate;
	private Date endDate;
	private String blobUrl;
	private String fileName;

	public Long getnId() {
		return nId;
	}

	public void setnId(Long nId) {
		this.nId = nId;
	}

	public String getnTitle() {
		return nTitle;
	}

	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}

	public String getnContent() {
		return nContent;
	}

	public void setnContent(String nContent) {
		this.nContent = nContent;
	}

	public String getNewsUrl() {
		return newsUrl;
	}

	public void setNewsUrl(String newsUrl) {
		this.newsUrl = newsUrl;
	}

	public boolean isNwImgAvail() {
		return nwImgAvail;
	}

	public void setNwImgAvail(boolean nwImgAvail) {
		this.nwImgAvail = nwImgAvail;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getBlobUrl() {
		return blobUrl;
	}

	public void setBlobUrl(String blobUrl) {
		this.blobUrl = blobUrl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
