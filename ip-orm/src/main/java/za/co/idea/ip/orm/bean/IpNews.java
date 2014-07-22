package za.co.idea.ip.orm.bean;

import java.util.Date;

public class IpNews implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7537349540081108745L;
	private Long newsId;
	private String nTitle;
	private Date nStartDate;
	private Date nEndDate;
	private String nContent;

	/** default constructor */
	public IpNews() {
	}

	/** full constructor */
	public IpNews(Long newsId, String nTitle, Date nStartDate, Date nEndDate, String nContent) {
		this.newsId = newsId;
		this.nTitle = nTitle;
		this.nStartDate = nStartDate;
		this.nEndDate = nEndDate;
		this.nContent = nContent;
	}

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public String getnTitle() {
		return nTitle;
	}

	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
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

	public String getnContent() {
		return nContent;
	}

	public void setnContent(String nContent) {
		this.nContent = nContent;
	}

}
