package za.co.idea.ip.orm.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * IpChallengeCat entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_challenge_cat", catalog = "lpdb", uniqueConstraints = @UniqueConstraint(columnNames = "cc_desc"))
public class IpChallengeCat implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3578341323792747205L;
	private Integer ccId;
	private String ccDesc;
	private Set<IpChallenge> ipChallenges = new HashSet<IpChallenge>(0);

	// Constructors

	/** default constructor */
	public IpChallengeCat() {
	}

	/** minimal constructor */
	public IpChallengeCat(Integer ccId, String ccDesc) {
		this.ccId = ccId;
		this.ccDesc = ccDesc;
	}

	/** full constructor */
	public IpChallengeCat(Integer ccId, String ccDesc, Set<IpChallenge> ipChallenges) {
		this.ccId = ccId;
		this.ccDesc = ccDesc;
		this.ipChallenges = ipChallenges;
	}

	// Property accessors
	@Id
	@Column(name = "cc_id", unique = true, nullable = false)
	public Integer getCcId() {
		return this.ccId;
	}

	public void setCcId(Integer ccId) {
		this.ccId = ccId;
	}

	@Column(name = "cc_desc", unique = true, nullable = false, length = 45)
	public String getCcDesc() {
		return this.ccDesc;
	}

	public void setCcDesc(String ccDesc) {
		this.ccDesc = ccDesc;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipChallengeCat")
	public Set<IpChallenge> getIpChallenges() {
		return this.ipChallenges;
	}

	public void setIpChallenges(Set<IpChallenge> ipChallenges) {
		this.ipChallenges = ipChallenges;
	}

}