package za.co.idea.ip.orm.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * IpIdea entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("rawtypes")
public class IpIdea implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6324862742266044635L;
	private Long ideaId;
	private IpIdeaStatus ipIdeaStatus;
	private IpUser ipUserByIdeaUserId;
	private IpIdeaCat ipIdeaCat;
	private String ideaTitle;
	private String ideaDesc;
	private String ideaBa;
	private Date ideaDate;
	private String ideaTag;
	private Integer ideaReviewCnt;
	private Set ipIdeaGroups = new HashSet(0);

	// Constructors

	/** default constructor */
	public IpIdea() {
	}

	/** minimal constructor */
	public IpIdea(Long ideaId, IpIdeaStatus ipIdeaStatus, IpUser ipUserByIdeaUserId, String ideaTitle, String ideaDesc, Date ideaDate, String ideaTag, Integer ideaReviewCnt) {
		this.ideaId = ideaId;
		this.ipIdeaStatus = ipIdeaStatus;
		this.ipUserByIdeaUserId = ipUserByIdeaUserId;
		this.ideaTitle = ideaTitle;
		this.ideaDesc = ideaDesc;
		this.ideaDate = ideaDate;
		this.ideaTag = ideaTag;
		this.ideaReviewCnt = ideaReviewCnt;
	}

	/** full constructor */
	public IpIdea(Long ideaId, IpIdeaStatus ipIdeaStatus, IpUser ipUserByIdeaUserId, IpIdeaCat ipIdeaCat, String ideaTitle, String ideaDesc, String ideaBa, Date ideaDate, String ideaTag, Integer ideaReviewCnt, Set ipIdeaGroups) {
		this.ideaId = ideaId;
		this.ipIdeaStatus = ipIdeaStatus;
		this.ipUserByIdeaUserId = ipUserByIdeaUserId;
		this.ipIdeaCat = ipIdeaCat;
		this.ideaTitle = ideaTitle;
		this.ideaDesc = ideaDesc;
		this.ideaBa = ideaBa;
		this.ideaDate = ideaDate;
		this.ideaTag = ideaTag;
		this.ipIdeaGroups = ipIdeaGroups;
		this.ideaReviewCnt = ideaReviewCnt;
	}

	// Property accessors

	public Long getIdeaId() {
		return this.ideaId;
	}

	public void setIdeaId(Long ideaId) {
		this.ideaId = ideaId;
	}

	public IpIdeaStatus getIpIdeaStatus() {
		return this.ipIdeaStatus;
	}

	public void setIpIdeaStatus(IpIdeaStatus ipIdeaStatus) {
		this.ipIdeaStatus = ipIdeaStatus;
	}

	public IpUser getIpUserByIdeaUserId() {
		return this.ipUserByIdeaUserId;
	}

	public void setIpUserByIdeaUserId(IpUser ipUserByIdeaUserId) {
		this.ipUserByIdeaUserId = ipUserByIdeaUserId;
	}

	public IpIdeaCat getIpIdeaCat() {
		return this.ipIdeaCat;
	}

	public void setIpIdeaCat(IpIdeaCat ipIdeaCat) {
		this.ipIdeaCat = ipIdeaCat;
	}

	public String getIdeaTitle() {
		return this.ideaTitle;
	}

	public void setIdeaTitle(String ideaTitle) {
		this.ideaTitle = ideaTitle;
	}

	public String getIdeaDesc() {
		return this.ideaDesc;
	}

	public void setIdeaDesc(String ideaDesc) {
		this.ideaDesc = ideaDesc;
	}

	public String getIdeaBa() {
		return this.ideaBa;
	}

	public void setIdeaBa(String ideaBa) {
		this.ideaBa = ideaBa;
	}

	public Date getIdeaDate() {
		return this.ideaDate;
	}

	public void setIdeaDate(Date ideaDate) {
		this.ideaDate = ideaDate;
	}

	public String getIdeaTag() {
		return this.ideaTag;
	}

	public void setIdeaTag(String ideaTag) {
		this.ideaTag = ideaTag;
	}

	public Set getIpIdeaGroups() {
		return this.ipIdeaGroups;
	}

	public void setIpIdeaGroups(Set ipIdeaGroups) {
		this.ipIdeaGroups = ipIdeaGroups;
	}

	public Integer getIdeaReviewCnt() {
		return ideaReviewCnt;
	}

	public void setIdeaReviewCnt(Integer ideaReviewCnt) {
		this.ideaReviewCnt = ideaReviewCnt;
	}

}