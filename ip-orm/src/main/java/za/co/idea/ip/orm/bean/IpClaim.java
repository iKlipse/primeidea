package za.co.idea.ip.orm.bean;

import java.util.Date;

/**
 * IpClaim entity. @author MyEclipse Persistence Tools
 */

public class IpClaim implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6156158923203310450L;
	private Long claimId;
	private IpUser ipUser;
	private IpClaimStatus ipClaimStatus;
	private IpRewards ipRewards;
	private String claimDesc;
	private Date claimCrtdDt;
	private String claimComment;

	// Constructors

	/** default constructor */
	public IpClaim() {
	}

	/** minimal constructor */
	public IpClaim(Long claimId, Date claimCrtdDt) {
		this.claimId = claimId;
		this.claimCrtdDt = claimCrtdDt;
	}

	/** full constructor */
	public IpClaim(Long claimId, IpUser ipUser, IpClaimStatus ipClaimStatus, IpRewards ipRewards, String claimDesc, Date claimCrtdDt, String claimComment) {
		this.claimId = claimId;
		this.ipUser = ipUser;
		this.ipClaimStatus = ipClaimStatus;
		this.ipRewards = ipRewards;
		this.claimDesc = claimDesc;
		this.claimCrtdDt = claimCrtdDt;
		this.claimComment = claimComment;
	}

	// Property accessors

	public Long getClaimId() {
		return this.claimId;
	}

	public void setClaimId(Long claimId) {
		this.claimId = claimId;
	}

	public IpUser getIpUser() {
		return this.ipUser;
	}

	public void setIpUser(IpUser ipUser) {
		this.ipUser = ipUser;
	}

	public IpClaimStatus getIpClaimStatus() {
		return this.ipClaimStatus;
	}

	public void setIpClaimStatus(IpClaimStatus ipClaimStatus) {
		this.ipClaimStatus = ipClaimStatus;
	}

	public IpRewards getIpRewards() {
		return this.ipRewards;
	}

	public void setIpRewards(IpRewards ipRewards) {
		this.ipRewards = ipRewards;
	}

	public String getClaimDesc() {
		return this.claimDesc;
	}

	public void setClaimDesc(String claimDesc) {
		this.claimDesc = claimDesc;
	}

	public Date getClaimCrtdDt() {
		return this.claimCrtdDt;
	}

	public void setClaimCrtdDt(Date claimCrtdDt) {
		this.claimCrtdDt = claimCrtdDt;
	}

	public String getClaimComment() {
		return this.claimComment;
	}

	public void setClaimComment(String claimComment) {
		this.claimComment = claimComment;
	}

}