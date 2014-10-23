package za.co.idea.ip.orm.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * IpChallengeStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_challenge_status", catalog = "lpdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "getNextChalStatus", query = "select iis.* from ip_challenge_status iis where (iis.cs_id=:curr or iis.cs_id in (select st.tran_next_state from ip_state_tran st where st.tran_curr_state=:curr and lower(st.tran_entity)='ip_challenge_status'))", resultClass = IpChallengeStatus.class), @NamedNativeQuery(name = "getNonAllocChalStatus", query = "select iis.* from ip_challenge_status iis where iis.cs_id not in (select ia.alloc_status_id from ip_allocation ia where lower(ia.alloc_entity)='ip_challenge_status')", resultClass = IpChallengeStatus.class) })
public class IpChallengeStatus implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1471019467173378045L;
	private Integer csId;
	private String csDesc;
	private Set<IpChallenge> ipChallenges = new HashSet<IpChallenge>(0);

	// Constructors

	/** default constructor */
	public IpChallengeStatus() {
	}

	/** minimal constructor */
	public IpChallengeStatus(Integer csId, String csDesc) {
		this.csId = csId;
		this.csDesc = csDesc;
	}

	/** full constructor */
	public IpChallengeStatus(Integer csId, String csDesc, Set<IpChallenge> ipChallenges) {
		this.csId = csId;
		this.csDesc = csDesc;
		this.ipChallenges = ipChallenges;
	}

	// Property accessors
	@Id
	@Column(name = "cs_id", unique = true, nullable = false)
	public Integer getCsId() {
		return this.csId;
	}

	public void setCsId(Integer csId) {
		this.csId = csId;
	}

	@Column(name = "cs_desc", nullable = false, length = 45)
	public String getCsDesc() {
		return this.csDesc;
	}

	public void setCsDesc(String csDesc) {
		this.csDesc = csDesc;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipChallengeStatus")
	public Set<IpChallenge> getIpChallenges() {
		return this.ipChallenges;
	}

	public void setIpChallenges(Set<IpChallenge> ipChallenges) {
		this.ipChallenges = ipChallenges;
	}

}