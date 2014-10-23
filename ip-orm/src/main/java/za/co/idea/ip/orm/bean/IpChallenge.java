package za.co.idea.ip.orm.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * IpChallenge entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_challenge", catalog = "lpdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "getChallengeCreatedByUser", query = "select chal.* from ip_challenge chal where chal.chal_crtd_by=:id", resultClass = IpChallenge.class), @NamedNativeQuery(name = "getChallengeByUser", query = "select chal.* from ip_challenge chal where chal.chal_crtd_by=:id union select chal.* from ip_challenge chal,ip_challenge_group icg,	ip_group_user igu where igu.gu_usr_id=:id and igu.gu_grp_id=icg.cg_group_id and icg.cg_chal_id=chal.chal_id union select chal.* from ip_challenge chal where chal.chal_id not in (select icg.cg_chal_id from ip_challenge_group icg)", resultClass = IpChallenge.class),
		@NamedNativeQuery(name = "getChallengeByStatusUser", query = "select chal.* from ip_challenge chal where chal.chal_crtd_by=:id and chal.chal_status=:sid union select chal.* from ip_challenge chal,ip_challenge_group icg, ip_group_user igu where igu.gu_usr_id=:id and igu.gu_grp_id=icg.cg_group_id and icg.cg_chal_id=chal.chal_id and	chal.chal_status=:sid union select chal.* from ip_challenge chal where chal.chal_id not in (select icg.cg_chal_id from ip_challenge_group icg) and chal.chal_status=:sid", resultClass = IpChallenge.class), @NamedNativeQuery(name = "getChallengeByStatus", query = "select chal.* from ip_challenge chal where chal.chal_status=:id", resultClass = IpChallenge.class),
		@NamedNativeQuery(name = "updateStatusOnExpiry", query = "update ip_challenge set chal_status=7 where current_Date > chal_expiry_dt"), @NamedNativeQuery(name = "getReviewChallengesByUser", query = "select distinct chal.* from ip_challenge chal, ip_review rv where chal.chal_id=rv.rev_entity_id and chal.chal_status in (2,3,4,5,6) and rv.rev_sel_user_id=:id and rv.rev_entity_name='ip_challenge' and (chal.chal_status = (select cs_id from ip_challenge_status where lower(cs_desc) = lower(concat('Review ',rv.rev_entity_status_id))))", resultClass = IpChallenge.class) })
public class IpChallenge implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5447644711932616241L;
	private Long chalId;
	private IpChallengeStatus ipChallengeStatus;
	private IpChallengeCat ipChallengeCat;
	private IpUser ipUser;
	private String chalTitle;
	private String chalDesc;
	private String chalHoverTxt;
	private Date chalLaunchDt;
	private Date chalExpiryDt;
	private String chalTags;
	private Date chalCrtdDt;
	private Integer chalReviewCnt;
	private Set<IpSolution> ipSolutions = new HashSet<IpSolution>(0);
	private Set<IpChallengeGroup> ipChallengeGroups = new HashSet<IpChallengeGroup>(0);

	// Constructors

	/** default constructor */
	public IpChallenge() {
	}

	/** minimal constructor */
	public IpChallenge(Long chalId, IpChallengeStatus ipChallengeStatus, IpChallengeCat ipChallengeCat, IpUser ipUser, String chalTitle, String chalDesc, Date chalLaunchDt, Date chalExpiryDt, Date chalCrtdDt) {
		this.chalId = chalId;
		this.ipChallengeStatus = ipChallengeStatus;
		this.ipChallengeCat = ipChallengeCat;
		this.ipUser = ipUser;
		this.chalTitle = chalTitle;
		this.chalDesc = chalDesc;
		this.chalLaunchDt = chalLaunchDt;
		this.chalExpiryDt = chalExpiryDt;
		this.chalCrtdDt = chalCrtdDt;
	}

	/** full constructor */
	public IpChallenge(Long chalId, IpChallengeStatus ipChallengeStatus, IpChallengeCat ipChallengeCat, IpUser ipUser, String chalTitle, String chalDesc, String chalHoverTxt, Date chalLaunchDt, Date chalExpiryDt, String chalTags, Date chalCrtdDt, Integer chalReviewCnt, Set<IpSolution> ipSolutions, Set<IpChallengeGroup> ipChallengeGroups) {
		this.chalId = chalId;
		this.ipChallengeStatus = ipChallengeStatus;
		this.ipChallengeCat = ipChallengeCat;
		this.ipUser = ipUser;
		this.chalTitle = chalTitle;
		this.chalDesc = chalDesc;
		this.chalHoverTxt = chalHoverTxt;
		this.chalLaunchDt = chalLaunchDt;
		this.chalExpiryDt = chalExpiryDt;
		this.chalTags = chalTags;
		this.chalCrtdDt = chalCrtdDt;
		this.chalReviewCnt = chalReviewCnt;
		this.ipSolutions = ipSolutions;
		this.ipChallengeGroups = ipChallengeGroups;
	}

	// Property accessors
	@Id
	@Column(name = "chal_id", unique = true, nullable = false)
	public Long getChalId() {
		return this.chalId;
	}

	public void setChalId(Long chalId) {
		this.chalId = chalId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chal_status", nullable = false)
	public IpChallengeStatus getIpChallengeStatus() {
		return this.ipChallengeStatus;
	}

	public void setIpChallengeStatus(IpChallengeStatus ipChallengeStatus) {
		this.ipChallengeStatus = ipChallengeStatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chal_cat", nullable = false)
	public IpChallengeCat getIpChallengeCat() {
		return this.ipChallengeCat;
	}

	public void setIpChallengeCat(IpChallengeCat ipChallengeCat) {
		this.ipChallengeCat = ipChallengeCat;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chal_crtd_by", nullable = false)
	public IpUser getIpUser() {
		return this.ipUser;
	}

	public void setIpUser(IpUser ipUser) {
		this.ipUser = ipUser;
	}

	@Column(name = "chal_title", nullable = false, length = 100)
	public String getChalTitle() {
		return this.chalTitle;
	}

	public void setChalTitle(String chalTitle) {
		this.chalTitle = chalTitle;
	}

	@Column(name = "chal_desc", nullable = false, length = 65535)
	public String getChalDesc() {
		return this.chalDesc;
	}

	public void setChalDesc(String chalDesc) {
		this.chalDesc = chalDesc;
	}

	@Column(name = "chal_hover_txt", length = 65535)
	public String getChalHoverTxt() {
		return this.chalHoverTxt;
	}

	public void setChalHoverTxt(String chalHoverTxt) {
		this.chalHoverTxt = chalHoverTxt;
	}

	@Column(name = "chal_launch_dt", nullable = false, length = 19)
	public Date getChalLaunchDt() {
		return this.chalLaunchDt;
	}

	public void setChalLaunchDt(Date chalLaunchDt) {
		this.chalLaunchDt = chalLaunchDt;
	}

	@Column(name = "chal_expiry_dt", nullable = false, length = 19)
	public Date getChalExpiryDt() {
		return this.chalExpiryDt;
	}

	public void setChalExpiryDt(Date chalExpiryDt) {
		this.chalExpiryDt = chalExpiryDt;
	}

	@Column(name = "chal_tags", length = 65535)
	public String getChalTags() {
		return this.chalTags;
	}

	public void setChalTags(String chalTags) {
		this.chalTags = chalTags;
	}

	@Column(name = "chal_crtd_dt", nullable = false, length = 19)
	public Date getChalCrtdDt() {
		return this.chalCrtdDt;
	}

	public void setChalCrtdDt(Date chalCrtdDt) {
		this.chalCrtdDt = chalCrtdDt;
	}

	@Column(name = "chal_review_cnt")
	public Integer getChalReviewCnt() {
		return this.chalReviewCnt;
	}

	public void setChalReviewCnt(Integer chalReviewCnt) {
		this.chalReviewCnt = chalReviewCnt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipChallenge")
	public Set<IpSolution> getIpSolutions() {
		return this.ipSolutions;
	}

	public void setIpSolutions(Set<IpSolution> ipSolutions) {
		this.ipSolutions = ipSolutions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipChallenge")
	public Set<IpChallengeGroup> getIpChallengeGroups() {
		return this.ipChallengeGroups;
	}

	public void setIpChallengeGroups(Set<IpChallengeGroup> ipChallengeGroups) {
		this.ipChallengeGroups = ipChallengeGroups;
	}

}