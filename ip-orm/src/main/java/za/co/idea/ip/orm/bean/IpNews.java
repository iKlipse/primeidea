package za.co.idea.ip.orm.bean;

import java.util.Date;

/**
 * IpNews entity. @author MyEclipse Persistence Tools
 */

public class IpNews implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3859708826750803775L;
	private Long newsId;
	private String newsTitle;
	private Date newsStartDate;
	private Date newsEndDate;
	private String newsContent;
	private Date newsCrtdDt;

	// Constructors

	/** default constructor */
	public IpNews() {
	}

	/** minimal constructor */
	public IpNews(Long newsId, Date newsCrtdDt) {
		this.newsId = newsId;
		this.newsCrtdDt = newsCrtdDt;
	}

	/** full constructor */
	public IpNews(Long newsId, String newsTitle, Date newsStartDate,
			Date newsEndDate, String newsContent, Date newsCrtdDt) {
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.newsStartDate = newsStartDate;
		this.newsEndDate = newsEndDate;
		this.newsContent = newsContent;
		this.newsCrtdDt = newsCrtdDt;
	}

	// Property accessors

	public Long getNewsId() {
		return this.newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public String getNewsTitle() {
		return this.newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public Date getNewsStartDate() {
		return this.newsStartDate;
	}

	public void setNewsStartDate(Date newsStartDate) {
		this.newsStartDate = newsStartDate;
	}

	public Date getNewsEndDate() {
		return this.newsEndDate;
	}

	public void setNewsEndDate(Date newsEndDate) {
		this.newsEndDate = newsEndDate;
	}

	public String getNewsContent() {
		return this.newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public Date getNewsCrtdDt() {
		return this.newsCrtdDt;
	}

	public void setNewsCrtdDt(Date newsCrtdDt) {
		this.newsCrtdDt = newsCrtdDt;
	}

}