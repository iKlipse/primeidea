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
 * IpSolution entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_solution", catalog = "lpdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "getSolutionCreatedByUser", query = "select sol.* from ip_solution sol where sol.sol_crtd_by=:id", resultClass = IpSolution.class), @NamedNativeQuery(name = "getSolutionByUser", query = "select sol.* from ip_solution sol where sol.sol_crtd_by=:id union select sol.* from ip_solution sol where sol.sol_chal_id in (select icg.cg_chal_id from ip_challenge_group icg, ip_group_user igu where igu.gu_usr_id=:id and igu.gu_grp_id=icg.cg_group_id) union select sol.* from ip_solution sol where sol.sol_chal_id in (select ic.chal_id from ip_challenge ic where ic.chal_id not in (select icg.cg_chal_id from ip_challenge_group icg))", resultClass = IpSolution.class),
		@NamedNativeQuery(name = "getSolutionByUserStatus", query = "select sol.* from ip_solution sol where sol.sol_crtd_by=:id and	sol.sol_status=:sid union select sol.* from ip_solution sol where sol.sol_chal_id in (select icg.cg_chal_id from ip_challenge_group icg, ip_group_user igu where igu.gu_usr_id=:id and igu.gu_grp_id=icg.cg_group_id) and sol.sol_status=:sid union select sol.* from ip_solution sol where sol.sol_chal_id in (select ic.chal_id from ip_challenge ic where ic.chal_id not in (select icg.cg_chal_id from ip_challenge_group icg)) and sol.sol_status=:sid", resultClass = IpSolution.class),
		@NamedNativeQuery(name = "getSolutionByChallenge", query = "select sol.* from ip_solution sol where sol.sol_chal_id=:id and sol.sol_status != 1 order by sol.sol_crtd_dt desc", resultClass = IpSolution.class), @NamedNativeQuery(name = "getSolutionByStatus", query = "select sol.* from ip_solution sol where sol.sol_status=:id", resultClass = IpSolution.class), @NamedNativeQuery(name = "getReviewSolutionByStatus", query = "select distinct sol.* from ip_solution sol, ip_review rv where sol.sol_id=rv.rev_entity_id and sol.sol_status in (3,4,5,6,7) and rv.rev_sel_user_id=:id and rv.rev_entity_name='ip_solution' and (sol.sol_status = (select ss_id from ip_solution_status where lower(ss_desc) = lower(concat('In-Review ',rv.rev_entity_status_id))))", resultClass = IpSolution.class),
		@NamedNativeQuery(name = "updateSolStatusOnExpiry", query = "update ip_solution set sol_status=8 where sol_chal_id in (select chal_id from ip_challenge where chal_status=7)") })
public class IpSolution implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -753806292527257847L;
	private Long solId;
	private IpSolutionStatus ipSolutionStatus;
	private IpSolutionCat ipSolutionCat;
	private IpUser ipUser;
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
	public IpSolution(Long solId, IpSolutionStatus ipSolutionStatus, IpSolutionCat ipSolutionCat, IpUser ipUser, IpChallenge ipChallenge, String solTitle, String solDesc, Date solCrtdDt) {
		this.solId = solId;
		this.ipSolutionStatus = ipSolutionStatus;
		this.ipSolutionCat = ipSolutionCat;
		this.ipUser = ipUser;
		this.ipChallenge = ipChallenge;
		this.solTitle = solTitle;
		this.solDesc = solDesc;
		this.solCrtdDt = solCrtdDt;
	}

	/** full constructor */
	public IpSolution(Long solId, IpSolutionStatus ipSolutionStatus, IpSolutionCat ipSolutionCat, IpUser ipUser, IpChallenge ipChallenge, String solTitle, String solDesc, String solTags, Date solCrtdDt, Integer solReviewCnt) {
		this.solId = solId;
		this.ipSolutionStatus = ipSolutionStatus;
		this.ipSolutionCat = ipSolutionCat;
		this.ipUser = ipUser;
		this.ipChallenge = ipChallenge;
		this.solTitle = solTitle;
		this.solDesc = solDesc;
		this.solTags = solTags;
		this.solCrtdDt = solCrtdDt;
		this.solReviewCnt = solReviewCnt;
	}

	// Property accessors
	@Id
	@Column(name = "sol_id", unique = true, nullable = false)
	public Long getSolId() {
		return this.solId;
	}

	public void setSolId(Long solId) {
		this.solId = solId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sol_status", nullable = false)
	public IpSolutionStatus getIpSolutionStatus() {
		return this.ipSolutionStatus;
	}

	public void setIpSolutionStatus(IpSolutionStatus ipSolutionStatus) {
		this.ipSolutionStatus = ipSolutionStatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sol_cat", nullable = false)
	public IpSolutionCat getIpSolutionCat() {
		return this.ipSolutionCat;
	}

	public void setIpSolutionCat(IpSolutionCat ipSolutionCat) {
		this.ipSolutionCat = ipSolutionCat;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sol_crtd_by", nullable = false)
	public IpUser getIpUser() {
		return this.ipUser;
	}

	public void setIpUser(IpUser ipUser) {
		this.ipUser = ipUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sol_chal_id", nullable = false)
	public IpChallenge getIpChallenge() {
		return this.ipChallenge;
	}

	public void setIpChallenge(IpChallenge ipChallenge) {
		this.ipChallenge = ipChallenge;
	}

	@Column(name = "sol_title", nullable = false, length = 100)
	public String getSolTitle() {
		return this.solTitle;
	}

	public void setSolTitle(String solTitle) {
		this.solTitle = solTitle;
	}

	@Column(name = "sol_desc", nullable = false, length = 65535)
	public String getSolDesc() {
		return this.solDesc;
	}

	public void setSolDesc(String solDesc) {
		this.solDesc = solDesc;
	}

	@Column(name = "sol_tags", length = 65535)
	public String getSolTags() {
		return this.solTags;
	}

	public void setSolTags(String solTags) {
		this.solTags = solTags;
	}

	@Column(name = "sol_crtd_dt", nullable = false, length = 19)
	public Date getSolCrtdDt() {
		return this.solCrtdDt;
	}

	public void setSolCrtdDt(Date solCrtdDt) {
		this.solCrtdDt = solCrtdDt;
	}

	@Column(name = "sol_review_cnt")
	public Integer getSolReviewCnt() {
		return this.solReviewCnt;
	}

	public void setSolReviewCnt(Integer solReviewCnt) {
		this.solReviewCnt = solReviewCnt;
	}

}