package za.co.idea.ip.orm.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * IpRewards entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("rawtypes")
public class IpRewards implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6833158177169162762L;
	private Long rwId;
	private IpRewardsCat ipRewardsCat;
	private IpRewardsStatus ipRewardsStatus;
	private String rwTitle;
	private String rwDesc;
	private Integer rwValue;
	private Integer rwStockCodeNum;
	private String rwHoverText;
	private Date rwLaunchDt;
	private Date rwExpiryDt;
	private String rwTag;
	private Date rwCrtdDt;
	private Set ipRewardsGroups = new HashSet(0);
	private Set ipClaims = new HashSet(0);

	// Constructors

	/** default constructor */
	public IpRewards() {
	}

	/** minimal constructor */
	public IpRewards(Long rwId, IpRewardsCat ipRewardsCat, IpRewardsStatus ipRewardsStatus, String rwTitle, String rwDesc, Integer rwValue, Integer rwStockCodeNum, Date rwLaunchDt, Date rwExpiryDt, Date rwCrtdDt) {
		this.rwId = rwId;
		this.ipRewardsCat = ipRewardsCat;
		this.ipRewardsStatus = ipRewardsStatus;
		this.rwTitle = rwTitle;
		this.rwDesc = rwDesc;
		this.rwValue = rwValue;
		this.rwStockCodeNum = rwStockCodeNum;
		this.rwLaunchDt = rwLaunchDt;
		this.rwExpiryDt = rwExpiryDt;
		this.rwCrtdDt = rwCrtdDt;
	}

	/** full constructor */
	public IpRewards(Long rwId, IpRewardsCat ipRewardsCat, IpRewardsStatus ipRewardsStatus, String rwTitle, String rwDesc, Integer rwValue, Integer rwStockCodeNum, String rwHoverText, Date rwLaunchDt, Date rwExpiryDt, String rwTag, Date rwCrtdDt, Set ipRewardsGroups, Set ipClaims) {
		this.rwId = rwId;
		this.ipRewardsCat = ipRewardsCat;
		this.ipRewardsStatus = ipRewardsStatus;
		this.rwTitle = rwTitle;
		this.rwDesc = rwDesc;
		this.rwValue = rwValue;
		this.rwStockCodeNum = rwStockCodeNum;
		this.rwHoverText = rwHoverText;
		this.rwLaunchDt = rwLaunchDt;
		this.rwExpiryDt = rwExpiryDt;
		this.rwTag = rwTag;
		this.rwCrtdDt = rwCrtdDt;
		this.ipRewardsGroups = ipRewardsGroups;
		this.ipClaims = ipClaims;
	}

	// Property accessors

	public Long getRwId() {
		return this.rwId;
	}

	public void setRwId(Long rwId) {
		this.rwId = rwId;
	}

	public IpRewardsCat getIpRewardsCat() {
		return this.ipRewardsCat;
	}

	public void setIpRewardsCat(IpRewardsCat ipRewardsCat) {
		this.ipRewardsCat = ipRewardsCat;
	}

	public IpRewardsStatus getIpRewardsStatus() {
		return this.ipRewardsStatus;
	}

	public void setIpRewardsStatus(IpRewardsStatus ipRewardsStatus) {
		this.ipRewardsStatus = ipRewardsStatus;
	}

	public String getRwTitle() {
		return this.rwTitle;
	}

	public void setRwTitle(String rwTitle) {
		this.rwTitle = rwTitle;
	}

	public String getRwDesc() {
		return this.rwDesc;
	}

	public void setRwDesc(String rwDesc) {
		this.rwDesc = rwDesc;
	}

	public Integer getRwValue() {
		return this.rwValue;
	}

	public void setRwValue(Integer rwValue) {
		this.rwValue = rwValue;
	}

	public Integer getRwStockCodeNum() {
		return this.rwStockCodeNum;
	}

	public void setRwStockCodeNum(Integer rwStockCodeNum) {
		this.rwStockCodeNum = rwStockCodeNum;
	}

	public String getRwHoverText() {
		return this.rwHoverText;
	}

	public void setRwHoverText(String rwHoverText) {
		this.rwHoverText = rwHoverText;
	}

	public Date getRwLaunchDt() {
		return this.rwLaunchDt;
	}

	public void setRwLaunchDt(Date rwLaunchDt) {
		this.rwLaunchDt = rwLaunchDt;
	}

	public Date getRwExpiryDt() {
		return this.rwExpiryDt;
	}

	public void setRwExpiryDt(Date rwExpiryDt) {
		this.rwExpiryDt = rwExpiryDt;
	}

	public String getRwTag() {
		return this.rwTag;
	}

	public void setRwTag(String rwTag) {
		this.rwTag = rwTag;
	}

	public Date getRwCrtdDt() {
		return this.rwCrtdDt;
	}

	public void setRwCrtdDt(Date rwCrtdDt) {
		this.rwCrtdDt = rwCrtdDt;
	}

	public Set getIpRewardsGroups() {
		return this.ipRewardsGroups;
	}

	public void setIpRewardsGroups(Set ipRewardsGroups) {
		this.ipRewardsGroups = ipRewardsGroups;
	}

	public Set getIpClaims() {
		return this.ipClaims;
	}

	public void setIpClaims(Set ipClaims) {
		this.ipClaims = ipClaims;
	}

}