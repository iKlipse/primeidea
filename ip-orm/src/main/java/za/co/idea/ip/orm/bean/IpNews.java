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
	private static final long serialVersionUID = -8306751663989862760L;
	private Long newsId;
	private String newsTitle;
	private Date newsStartDate;
	private Date newsEndDate;
	private String newsContent;

	// Constructors

	/** default constructor */
	public IpNews() {
	}

	/** minimal constructor */
	public IpNews(Long newsId) {
		this.newsId = newsId;
	}

	/** full constructor */
	public IpNews(Long newsId, String newsTitle, Date newsStartDate, Date newsEndDate, String newsContent) {
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.newsStartDate = newsStartDate;
		this.newsEndDate = newsEndDate;
		this.newsContent = newsContent;
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

}