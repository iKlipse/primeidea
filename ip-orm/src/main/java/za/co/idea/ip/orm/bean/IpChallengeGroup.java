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
 * IpChallengeGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_challenge_group", catalog = "lpdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "fetchCGByChalId", query = "select fg.* from ip_challenge_group fg where fg.cg_chal_id=:id", resultClass = IpChallengeGroup.class), @NamedNativeQuery(name = "fetchCGByGroupId", query = "select fg.* from ip_challenge_group fg where fg.cg_group_id=:id", resultClass = IpChallengeGroup.class) })
public class IpChallengeGroup implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7386012031383343996L;
	private Long cgId;
	private IpChallenge ipChallenge;
	private IpGroup ipGroup;
	private Date cgCrtdDt;

	// Constructors

	/** default constructor */
	public IpChallengeGroup() {
	}

	/** minimal constructor */
	public IpChallengeGroup(Long cgId, Date cgCrtdDt) {
		this.cgId = cgId;
		this.cgCrtdDt = cgCrtdDt;
	}

	/** full constructor */
	public IpChallengeGroup(Long cgId, IpChallenge ipChallenge, IpGroup ipGroup, Date cgCrtdDt) {
		this.cgId = cgId;
		this.ipChallenge = ipChallenge;
		this.ipGroup = ipGroup;
		this.cgCrtdDt = cgCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "cg_id", unique = true, nullable = false)
	public Long getCgId() {
		return this.cgId;
	}

	public void setCgId(Long cgId) {
		this.cgId = cgId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cg_chal_id")
	public IpChallenge getIpChallenge() {
		return this.ipChallenge;
	}

	public void setIpChallenge(IpChallenge ipChallenge) {
		this.ipChallenge = ipChallenge;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cg_group_id")
	public IpGroup getIpGroup() {
		return this.ipGroup;
	}

	public void setIpGroup(IpGroup ipGroup) {
		this.ipGroup = ipGroup;
	}

	@Column(name = "cg_crtd_dt", nullable = false, length = 19)
	public Date getCgCrtdDt() {
		return this.cgCrtdDt;
	}

	public void setCgCrtdDt(Date cgCrtdDt) {
		this.cgCrtdDt = cgCrtdDt;
	}

}