package za.co.idea.ip.orm.bean;

/**
 * IpChallengeGroup entity. @author MyEclipse Persistence Tools
 */

public class IpChallengeGroup implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1342586774590729934L;
	private Long cgId;
	private IpChallenge ipChallenge;
	private IpGroup ipGroup;

	// Constructors

	/** default constructor */
	public IpChallengeGroup() {
	}

	/** minimal constructor */
	public IpChallengeGroup(Long cgId) {
		this.cgId = cgId;
	}

	/** full constructor */
	public IpChallengeGroup(Long cgId, IpChallenge ipChallenge, IpGroup ipGroup) {
		this.cgId = cgId;
		this.ipChallenge = ipChallenge;
		this.ipGroup = ipGroup;
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

}