package za.co.idea.ip.ws.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "newsMessage")
public class NewsMessage {

	private Long nId;
	private Date startDate;
	private Date endDate;
	private String nTitle;
	private String content;
	private String newsUrl;
	private boolean nwImgAvail;
	private String blobUrl;
	private String fileName;
	private Date newsCrtdDt;

	public String getNewsUrl() {
		return newsUrl;
	}

	public void setNewsUrl(String newsUrl) {
		this.newsUrl = newsUrl;
	}

	public Long getnId() {
		return nId;
	}

	public void setnId(Long nId) {
		this.nId = nId;
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

	public String getnTitle() {
		return nTitle;
	}

	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isNwImgAvail() {
		return nwImgAvail;
	}

	public void setNwImgAvail(boolean nwImgAvail) {
		this.nwImgAvail = nwImgAvail;
	}

	public String getBlobUrl() {
		return blobUrl;
	}

	public Date getNewsCrtdDt() {
		return newsCrtdDt;
	}

	public void setNewsCrtdDt(Date newsCrtdDt) {
		this.newsCrtdDt = newsCrtdDt;
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
