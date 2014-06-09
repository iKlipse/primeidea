package za.co.idea.ip.orm.bean;

/**
 * IpRewardsGroup entity. @author MyEclipse Persistence Tools
 */

public class IpRewardsGroup implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4010058583423881692L;
	private Long rgId;
	private IpRewards ipRewards;
	private IpGroup ipGroup;

	// Constructors

	/** default constructor */
	public IpRewardsGroup() {
	}

	/** minimal constructor */
	public IpRewardsGroup(Long rgId) {
		this.rgId = rgId;
	}

	/** full constructor */
	public IpRewardsGroup(Long rgId, IpRewards ipRewards, IpGroup ipGroup) {
		this.rgId = rgId;
		this.ipRewards = ipRewards;
		this.ipGroup = ipGroup;
	}

	// Property accessors

	public Long getRgId() {
		return this.rgId;
	}

	public void setRgId(Long rgId) {
		this.rgId = rgId;
	}

	public IpRewards getIpRewards() {
		return this.ipRewards;
	}

	public void setIpRewards(IpRewards ipRewards) {
		this.ipRewards = ipRewards;
	}

	public IpGroup getIpGroup() {
		return this.ipGroup;
	}

	public void setIpGroup(IpGroup ipGroup) {
		this.ipGroup = ipGroup;
	}

}