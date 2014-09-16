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
 * IpRewardsGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_rewards_group", catalog = "lpdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "deleteRGByRwId", query = "delete from ip_rewards_group where rg_rewards_id=:id"), @NamedNativeQuery(name = "fetchRGByRwId", query = "select fg.* from ip_rewards_group fg where fg.rg_rewards_id=:id", resultClass = IpRewardsGroup.class), @NamedNativeQuery(name = "fetchRGByGroupId", query = "select fg.* from ip_rewards_group fg where fg.rg_group_id=:id", resultClass = IpRewardsGroup.class) })
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
	public IpRewardsGroup(Long rgId, IpRewards ipRewards, IpGroup ipGroup, Date rgCrtdDt) {
		this.rgId = rgId;
		this.ipRewards = ipRewards;
		this.ipGroup = ipGroup;
		this.rgCrtdDt = rgCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "rg_id", unique = true, nullable = false)
	public Long getRgId() {
		return this.rgId;
	}

	public void setRgId(Long rgId) {
		this.rgId = rgId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rg_rewards_id")
	public IpRewards getIpRewards() {
		return this.ipRewards;
	}

	public void setIpRewards(IpRewards ipRewards) {
		this.ipRewards = ipRewards;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rg_group_id")
	public IpGroup getIpGroup() {
		return this.ipGroup;
	}

	public void setIpGroup(IpGroup ipGroup) {
		this.ipGroup = ipGroup;
	}

	@Column(name = "rg_crtd_dt", nullable = false, length = 19)
	public Date getRgCrtdDt() {
		return this.rgCrtdDt;
	}

	public void setRgCrtdDt(Date rgCrtdDt) {
		this.rgCrtdDt = rgCrtdDt;
	}

}