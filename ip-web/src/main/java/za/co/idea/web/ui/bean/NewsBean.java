package za.co.idea.web.ui.bean;

import java.util.Date;

public class NewsBean {
	private Long nId;
	private Date nStartDate;
	private Date nEndDate;
	private String nTitle;
	private String nContent;
	private String newsUrl;
	private boolean nwImgAvail;

	public Long getnId() {
		return nId;
	}

	public void setnId(Long nId) {
		this.nId = nId;
	}

	public Date getnStartDate() {
		return nStartDate;
	}

	public void setnStartDate(Date nStartDate) {
		this.nStartDate = nStartDate;
	}

	public Date getnEndDate() {
		return nEndDate;
	}

	public void setnEndDate(Date nEndDate) {
		this.nEndDate = nEndDate;
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

}
