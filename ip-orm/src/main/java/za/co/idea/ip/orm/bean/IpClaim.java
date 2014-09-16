package za.co.idea.ip.orm.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 * IpClaim entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_claim", catalog = "lpdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "getClaimByUser", query = "select clm.* from ip_claim clm where clm.user_id=:id", resultClass = IpClaim.class), @NamedNativeQuery(name = "getClaimByStatus", query = "select clm.* from ip_claim clm where clm.claim_status=:id", resultClass = IpClaim.class) })
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
	@Id
	@Column(name = "claim_id", unique = true, nullable = false)
	public Long getClaimId() {
		return this.claimId;
	}

	public void setClaimId(Long claimId) {
		this.claimId = claimId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public IpUser getIpUser() {
		return this.ipUser;
	}

	public void setIpUser(IpUser ipUser) {
		this.ipUser = ipUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "claim_status")
	public IpClaimStatus getIpClaimStatus() {
		return this.ipClaimStatus;
	}

	public void setIpClaimStatus(IpClaimStatus ipClaimStatus) {
		this.ipClaimStatus = ipClaimStatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reward_id")
	public IpRewards getIpRewards() {
		return this.ipRewards;
	}

	public void setIpRewards(IpRewards ipRewards) {
		this.ipRewards = ipRewards;
	}

	@Column(name = "claim_desc", length = 65535)
	public String getClaimDesc() {
		return this.claimDesc;
	}

	public void setClaimDesc(String claimDesc) {
		this.claimDesc = claimDesc;
	}

	@Column(name = "claim_crtd_dt", nullable = false, length = 19)
	public Date getClaimCrtdDt() {
		return this.claimCrtdDt;
	}

	public void setClaimCrtdDt(Date claimCrtdDt) {
		this.claimCrtdDt = claimCrtdDt;
	}

	@Column(name = "claim_comment", length = 450)
	public String getClaimComment() {
		return this.claimComment;
	}

	public void setClaimComment(String claimComment) {
		this.claimComment = claimComment;
	}

}