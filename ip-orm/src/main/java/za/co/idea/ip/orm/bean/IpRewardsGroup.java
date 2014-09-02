package za.co.idea.ip.orm.bean;

import java.util.Date;

/**
 * IpRewardsGroup entity. @author MyEclipse Persistence Tools
 */

public class IpRewardsGroup implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6919984233652320996L;
	private Long rgId;
	private IpRewards ipRewards;
	private IpGroup ipGroup;
	private Date rgCrtdDt;

	// Constructors

	/** default constructor */
	public IpRewardsGroup() {
	}

	/** minimal constructor */
	public IpRewardsGroup(Long rgId, Date rgCrtdDt) {
		this.rgId = rgId;
		this.rgCrtdDt = rgCrtdDt;
	}

	/** full constructor */
	public IpRewardsGroup(Long rgId, IpRewards ipRewards, IpGroup ipGroup,
			Date rgCrtdDt) {
		this.rgId = rgId;
		this.ipRewards = ipRewards;
		this.ipGroup = ipGroup;
		this.rgCrtdDt = rgCrtdDt;
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

	public Date getRgCrtdDt() {
		return this.rgCrtdDt;
	}

	public void setRgCrtdDt(Date rgCrtdDt) {
		this.rgCrtdDt = rgCrtdDt;
	}

}