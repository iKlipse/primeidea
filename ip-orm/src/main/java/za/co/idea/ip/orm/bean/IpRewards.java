package za.co.idea.ip.orm.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * IpRewards entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_rewards", catalog = "lpdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "getRewardsByUser", query = "select rw.* from ip_rewards rw, ip_tag t where t.tag_entity_id=rw.rw_id and t.tag_user_id=:id and t.tag_entity_type=4 and t.tag_type=4", resultClass = IpRewards.class), @NamedNativeQuery(name = "getRewardsByAvail", query = "select rw.* from ip_rewards rw where current_Date between rw.rw_launch_dt and rw.rw_expiry_dt order by rw.rw_value", resultClass = IpRewards.class), @NamedNativeQuery(name = "getRewardsByCatId", query = "select rw.* from ip_rewards rw where current_Date between rw.rw_launch_dt and rw.rw_expiry_dt and rw.rw_cat=:id", resultClass = IpRewards.class) })
public class IpRewards implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = -1505845607910378840L;
	private Long rwId;
	private IpRewardsCat ipRewardsCat;
	private String rwTitle;
	private String rwDesc;
	private Integer rwValue;
	private String rwStockCodeNum;
	private String rwHoverText;
	private Date rwLaunchDt;
	private Date rwExpiryDt;
	private String rwTag;
	private Date rwCrtdDt;
	private Double rwPrice;
	private Long rwQuantity;
	private Set<IpRewardsGroup> ipRewardsGroups = new HashSet<IpRewardsGroup>(0);
	private Set<IpClaim> ipClaims = new HashSet<IpClaim>(0);

	// Constructors

	/** default constructor */
	public IpRewards() {
	}

	/** minimal constructor */
	public IpRewards(Long rwId, IpRewardsCat ipRewardsCat, String rwTitle, String rwDesc, Integer rwValue, String rwStockCodeNum, Date rwLaunchDt, Date rwExpiryDt, Date rwCrtdDt) {
		this.rwId = rwId;
		this.ipRewardsCat = ipRewardsCat;
		this.rwTitle = rwTitle;
		this.rwDesc = rwDesc;
		this.rwValue = rwValue;
		this.rwStockCodeNum = rwStockCodeNum;
		this.rwLaunchDt = rwLaunchDt;
		this.rwExpiryDt = rwExpiryDt;
		this.rwCrtdDt = rwCrtdDt;
	}

	/** full constructor */
	public IpRewards(Long rwId, IpRewardsCat ipRewardsCat, String rwTitle, String rwDesc, Integer rwValue, String rwStockCodeNum, String rwHoverText, Date rwLaunchDt, Date rwExpiryDt, String rwTag, Date rwCrtdDt, Double rwPrice, Long rwQuantity, Set<IpRewardsGroup> ipRewardsGroups, Set<IpClaim> ipClaims) {
		this.rwId = rwId;
		this.ipRewardsCat = ipRewardsCat;
		this.rwTitle = rwTitle;
		this.rwDesc = rwDesc;
		this.rwValue = rwValue;
		this.rwStockCodeNum = rwStockCodeNum;
		this.rwHoverText = rwHoverText;
		this.rwLaunchDt = rwLaunchDt;
		this.rwExpiryDt = rwExpiryDt;
		this.rwTag = rwTag;
		this.rwCrtdDt = rwCrtdDt;
		this.rwPrice = rwPrice;
		this.rwQuantity = rwQuantity;
		this.ipRewardsGroups = ipRewardsGroups;
		this.ipClaims = ipClaims;
	}

	// Property accessors
	@Id
	@Column(name = "rw_id", unique = true, nullable = false)
	public Long getRwId() {
		return this.rwId;
	}

	public void setRwId(Long rwId) {
		this.rwId = rwId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rw_cat", nullable = false)
	public IpRewardsCat getIpRewardsCat() {
		return this.ipRewardsCat;
	}

	public void setIpRewardsCat(IpRewardsCat ipRewardsCat) {
		this.ipRewardsCat = ipRewardsCat;
	}

	@Column(name = "rw_title", nullable = false, length = 100)
	public String getRwTitle() {
		return this.rwTitle;
	}

	public void setRwTitle(String rwTitle) {
		this.rwTitle = rwTitle;
	}

	@Column(name = "rw_desc", nullable = false, length = 65535)
	public String getRwDesc() {
		return this.rwDesc;
	}

	public void setRwDesc(String rwDesc) {
		this.rwDesc = rwDesc;
	}

	@Column(name = "rw_value", nullable = false)
	public Integer getRwValue() {
		return this.rwValue;
	}

	public void setRwValue(Integer rwValue) {
		this.rwValue = rwValue;
	}

	@Column(name = "rw_stock_code_num", nullable = false, length = 50)
	public String getRwStockCodeNum() {
		return this.rwStockCodeNum;
	}

	public void setRwStockCodeNum(String rwStockCodeNum) {
		this.rwStockCodeNum = rwStockCodeNum;
	}

	@Column(name = "rw_hover_text", length = 65535)
	public String getRwHoverText() {
		return this.rwHoverText;
	}

	public void setRwHoverText(String rwHoverText) {
		this.rwHoverText = rwHoverText;
	}

	@Column(name = "rw_launch_dt", nullable = false, length = 19)
	public Date getRwLaunchDt() {
		return this.rwLaunchDt;
	}

	public void setRwLaunchDt(Date rwLaunchDt) {
		this.rwLaunchDt = rwLaunchDt;
	}

	@Column(name = "rw_expiry_dt", nullable = false, length = 19)
	public Date getRwExpiryDt() {
		return this.rwExpiryDt;
	}

	public void setRwExpiryDt(Date rwExpiryDt) {
		this.rwExpiryDt = rwExpiryDt;
	}

	@Column(name = "rw_tag", length = 65535)
	public String getRwTag() {
		return this.rwTag;
	}

	public void setRwTag(String rwTag) {
		this.rwTag = rwTag;
	}

	@Column(name = "rw_crtd_dt", nullable = false, length = 19)
	public Date getRwCrtdDt() {
		return this.rwCrtdDt;
	}

	public void setRwCrtdDt(Date rwCrtdDt) {
		this.rwCrtdDt = rwCrtdDt;
	}

	@Column(name = "rw_price", precision = 10, scale = 3)
	public Double getRwPrice() {
		return this.rwPrice;
	}

	public void setRwPrice(Double rwPrice) {
		this.rwPrice = rwPrice;
	}

	@Column(name = "rw_quantity")
	public Long getRwQuantity() {
		return this.rwQuantity;
	}

	public void setRwQuantity(Long rwQuantity) {
		this.rwQuantity = rwQuantity;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipRewards")
	public Set<IpRewardsGroup> getIpRewardsGroups() {
		return this.ipRewardsGroups;
	}

	public void setIpRewardsGroups(Set<IpRewardsGroup> ipRewardsGroups) {
		this.ipRewardsGroups = ipRewardsGroups;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipRewards")
	public Set<IpClaim> getIpClaims() {
		return this.ipClaims;
	}

	public void setIpClaims(Set<IpClaim> ipClaims) {
		this.ipClaims = ipClaims;
	}

}