package za.co.idea.ip.orm.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * IpChallengeStatus entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("rawtypes")
public class IpChallengeStatus implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1471019467173378045L;
	private Integer csId;
	private String csDesc;
	private Set ipChallenges = new HashSet(0);

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
	public IpChallengeStatus(Integer csId, String csDesc, Set ipChallenges) {
		this.csId = csId;
		this.csDesc = csDesc;
		this.ipChallenges = ipChallenges;
	}

	// Property accessors

	public Integer getCsId() {
		return this.csId;
	}

	public void setCsId(Integer csId) {
		this.csId = csId;
	}

	public String getCsDesc() {
		return this.csDesc;
	}

	public void setCsDesc(String csDesc) {
		this.csDesc = csDesc;
	}

	public Set getIpChallenges() {
		return this.ipChallenges;
	}

	public void setIpChallenges(Set ipChallenges) {
		this.ipChallenges = ipChallenges;
	}

}