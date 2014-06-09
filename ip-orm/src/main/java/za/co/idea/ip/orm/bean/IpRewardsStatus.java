package za.co.idea.ip.orm.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * IpRewardsStatus entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("rawtypes")
public class IpRewardsStatus implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2246385947352864270L;
	private Integer rsId;
	private String rsDesc;
	private Set ipRewardses = new HashSet(0);

	// Constructors

	/** default constructor */
	public IpRewardsStatus() {
	}

	/** minimal constructor */
	public IpRewardsStatus(Integer rsId) {
		this.rsId = rsId;
	}

	/** full constructor */
	public IpRewardsStatus(Integer rsId, String rsDesc, Set ipRewardses) {
		this.rsId = rsId;
		this.rsDesc = rsDesc;
		this.ipRewardses = ipRewardses;
	}

	// Property accessors

	public Integer getRsId() {
		return this.rsId;
	}

	public void setRsId(Integer rsId) {
		this.rsId = rsId;
	}

	public String getRsDesc() {
		return this.rsDesc;
	}

	public void setRsDesc(String rsDesc) {
		this.rsDesc = rsDesc;
	}

	public Set getIpRewardses() {
		return this.ipRewardses;
	}

	public void setIpRewardses(Set ipRewardses) {
		this.ipRewardses = ipRewardses;
	}

}