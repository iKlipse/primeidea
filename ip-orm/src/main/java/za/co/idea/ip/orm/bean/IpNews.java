package za.co.idea.ip.orm.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IpNews entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_news", catalog = "lpdb")
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
	public IpNews(Long newsId, String newsTitle, Date newsStartDate, Date newsEndDate, String newsContent, Date newsCrtdDt) {
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.newsStartDate = newsStartDate;
		this.newsEndDate = newsEndDate;
		this.newsContent = newsContent;
		this.newsCrtdDt = newsCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "news_id", unique = true, nullable = false)
	public Long getNewsId() {
		return this.newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	@Column(name = "news_title", length = 500)
	public String getNewsTitle() {
		return this.newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	@Column(name = "news_startDate", length = 19)
	public Date getNewsStartDate() {
		return this.newsStartDate;
	}

	public void setNewsStartDate(Date newsStartDate) {
		this.newsStartDate = newsStartDate;
	}

	@Column(name = "news_endDate", length = 19)
	public Date getNewsEndDate() {
		return this.newsEndDate;
	}

	public void setNewsEndDate(Date newsEndDate) {
		this.newsEndDate = newsEndDate;
	}

	@Column(name = "news_content", length = 9999)
	public String getNewsContent() {
		return this.newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	@Column(name = "news_crtd_dt", nullable = false, length = 19)
	public Date getNewsCrtdDt() {
		return this.newsCrtdDt;
	}

	public void setNewsCrtdDt(Date newsCrtdDt) {
		this.newsCrtdDt = newsCrtdDt;
	}

}