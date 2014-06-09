package za.co.idea.ip.orm.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * IpChallengeCat entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("rawtypes")
public class IpChallengeCat implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3578341323792747205L;
	private Integer ccId;
	private String ccDesc;
	private Set ipChallenges = new HashSet(0);

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
	public IpChallengeCat(Integer ccId, String ccDesc, Set ipChallenges) {
		this.ccId = ccId;
		this.ccDesc = ccDesc;
		this.ipChallenges = ipChallenges;
	}

	// Property accessors

	public Integer getCcId() {
		return this.ccId;
	}

	public void setCcId(Integer ccId) {
		this.ccId = ccId;
	}

	public String getCcDesc() {
		return this.ccDesc;
	}

	public void setCcDesc(String ccDesc) {
		this.ccDesc = ccDesc;
	}

	public Set getIpChallenges() {
		return this.ipChallenges;
	}

	public void setIpChallenges(Set ipChallenges) {
		this.ipChallenges = ipChallenges;
	}

}