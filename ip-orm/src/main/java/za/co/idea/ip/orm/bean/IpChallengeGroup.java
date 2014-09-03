package za.co.idea.ip.orm.bean;

import java.util.Date;

/**
 * IpChallengeGroup entity. @author MyEclipse Persistence Tools
 */

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

	public Long getCgId() {
		return this.cgId;
	}

	public void setCgId(Long cgId) {
		this.cgId = cgId;
	}

	public IpChallenge getIpChallenge() {
		return this.ipChallenge;
	}

	public void setIpChallenge(IpChallenge ipChallenge) {
		this.ipChallenge = ipChallenge;
	}

	public IpGroup getIpGroup() {
		return this.ipGroup;
	}

	public void setIpGroup(IpGroup ipGroup) {
		this.ipGroup = ipGroup;
	}

	public Date getCgCrtdDt() {
		return this.cgCrtdDt;
	}

	public void setCgCrtdDt(Date cgCrtdDt) {
		this.cgCrtdDt = cgCrtdDt;
	}

}