package za.co.idea.ip.orm.bean;

import java.util.Date;

/**
 * IpSolution entity. @author MyEclipse Persistence Tools
 */

public class IpSolution implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2820875215291609936L;
	private Long solId;
	private IpSolutionStatus ipSolutionStatus;
	private IpSolutionCat ipSolutionCat;
	private IpUser ipUserBySolCrtdBy;
	private IpChallenge ipChallenge;
	private String solTitle;
	private String solDesc;
	private String solTags;
	private Date solCrtdDt;
	private Integer solReviewCnt;

	// Constructors

	/** default constructor */
	public IpSolution() {
	}

	/** minimal constructor */
	public IpSolution(Long solId, IpSolutionStatus ipSolutionStatus, IpUser ipUserBySolCrtdBy, IpChallenge ipChallenge, String solTitle, String solDesc, Date solCrtdDt, Integer solReviewCnt) {
		this.solId = solId;
		this.ipSolutionStatus = ipSolutionStatus;
		this.ipUserBySolCrtdBy = ipUserBySolCrtdBy;
		this.ipChallenge = ipChallenge;
		this.solTitle = solTitle;
		this.solDesc = solDesc;
		this.solCrtdDt = solCrtdDt;
		this.solReviewCnt = solReviewCnt;
	}

	/** full constructor */
	public IpSolution(Long solId, IpSolutionStatus ipSolutionStatus, IpSolutionCat ipSolutionCat, IpUser ipUserBySolCrtdBy, IpChallenge ipChallenge, String solTitle, String solDesc, String solTags, Date solCrtdDt, Integer solReviewCnt) {
		this.solId = solId;
		this.ipSolutionStatus = ipSolutionStatus;
		this.ipSolutionCat = ipSolutionCat;
		this.ipUserBySolCrtdBy = ipUserBySolCrtdBy;
		this.ipChallenge = ipChallenge;
		this.solTitle = solTitle;
		this.solDesc = solDesc;
		this.solTags = solTags;
		this.solCrtdDt = solCrtdDt;
		this.solReviewCnt = solReviewCnt;
	}

	// Property accessors

	public Long getSolId() {
		return this.solId;
	}

	public void setSolId(Long solId) {
		this.solId = solId;
	}

	public IpSolutionStatus getIpSolutionStatus() {
		return this.ipSolutionStatus;
	}

	public void setIpSolutionStatus(IpSolutionStatus ipSolutionStatus) {
		this.ipSolutionStatus = ipSolutionStatus;
	}

	public IpSolutionCat getIpSolutionCat() {
		return this.ipSolutionCat;
	}

	public void setIpSolutionCat(IpSolutionCat ipSolutionCat) {
		this.ipSolutionCat = ipSolutionCat;
	}

	public IpUser getIpUserBySolCrtdBy() {
		return this.ipUserBySolCrtdBy;
	}

	public void setIpUserBySolCrtdBy(IpUser ipUserBySolCrtdBy) {
		this.ipUserBySolCrtdBy = ipUserBySolCrtdBy;
	}

	public IpChallenge getIpChallenge() {
		return this.ipChallenge;
	}

	public void setIpChallenge(IpChallenge ipChallenge) {
		this.ipChallenge = ipChallenge;
	}

	public String getSolTitle() {
		return this.solTitle;
	}

	public void setSolTitle(String solTitle) {
		this.solTitle = solTitle;
	}

	public String getSolDesc() {
		return this.solDesc;
	}

	public void setSolDesc(String solDesc) {
		this.solDesc = solDesc;
	}

	public String getSolTags() {
		return this.solTags;
	}

	public void setSolTags(String solTags) {
		this.solTags = solTags;
	}

	public Date getSolCrtdDt() {
		return this.solCrtdDt;
	}

	public void setSolCrtdDt(Date solCrtdDt) {
		this.solCrtdDt = solCrtdDt;
	}

	public Integer getSolReviewCnt() {
		return solReviewCnt;
	}

	public void setSolReviewCnt(Integer solReviewCnt) {
		this.solReviewCnt = solReviewCnt;
	}

}