package za.co.idea.ip.ws.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rewardsMessage")
public class RewardsMessage {
	private Long rwId;
	private Integer rCatId;
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
	private Long[] groupIdList;
	private String rwUrl;
	private boolean rwTaggable;
	private boolean rwClaimable;
	private boolean rwImgAvail;
	private String blobUrl;
	private String rCatName;

	public Long getRwId() {
		return rwId;
	}

	public void setRwId(Long rwId) {
		this.rwId = rwId;
	}

	public Integer getrCatId() {
		return rCatId;
	}

	public void setrCatId(Integer rCatId) {
		this.rCatId = rCatId;
	}

	public String getRwTitle() {
		return rwTitle;
	}

	public void setRwTitle(String rwTitle) {
		this.rwTitle = rwTitle;
	}

	public String getRwDesc() {
		return rwDesc;
	}

	public void setRwDesc(String rwDesc) {
		this.rwDesc = rwDesc;
	}

	public Integer getRwValue() {
		return rwValue;
	}

	public void setRwValue(Integer rwValue) {
		this.rwValue = rwValue;
	}

	public String getRwStockCodeNum() {
		return rwStockCodeNum;
	}

	public void setRwStockCodeNum(String rwStockCodeNum) {
		this.rwStockCodeNum = rwStockCodeNum;
	}

	public String getRwHoverText() {
		return rwHoverText;
	}

	public void setRwHoverText(String rwHoverText) {
		this.rwHoverText = rwHoverText;
	}

	public Date getRwLaunchDt() {
		return rwLaunchDt;
	}

	public void setRwLaunchDt(Date rwLaunchDt) {
		this.rwLaunchDt = rwLaunchDt;
	}

	public Date getRwExpiryDt() {
		return rwExpiryDt;
	}

	public void setRwExpiryDt(Date rwExpiryDt) {
		this.rwExpiryDt = rwExpiryDt;
	}

	public String getRwTag() {
		return rwTag;
	}

	public void setRwTag(String rwTag) {
		this.rwTag = rwTag;
	}

	public Date getRwCrtdDt() {
		return rwCrtdDt;
	}

	public void setRwCrtdDt(Date rwCrtdDt) {
		this.rwCrtdDt = rwCrtdDt;
	}

	public Double getRwPrice() {
		return rwPrice;
	}

	public void setRwPrice(Double rwPrice) {
		this.rwPrice = rwPrice;
	}

	public Long getRwQuantity() {
		return rwQuantity;
	}

	public void setRwQuantity(Long rwQuantity) {
		this.rwQuantity = rwQuantity;
	}

	public Long[] getGroupIdList() {
		return groupIdList;
	}

	public void setGroupIdList(Long[] groupIdList) {
		this.groupIdList = groupIdList;
	}

	public boolean isRwImgAvail() {
		return rwImgAvail;
	}

	public void setRwImgAvail(boolean rwImgAvail) {
		this.rwImgAvail = rwImgAvail;
	}

	public String getRwUrl() {
		return rwUrl;
	}

	public void setRwUrl(String rwUrl) {
		this.rwUrl = rwUrl;
	}

	public boolean isRwTaggable() {
		return rwTaggable;
	}

	public void setRwTaggable(boolean rwTaggable) {
		this.rwTaggable = rwTaggable;
	}

	public boolean isRwClaimable() {
		return rwClaimable;
	}

	public void setRwClaimable(boolean rwClaimable) {
		this.rwClaimable = rwClaimable;
	}

	public String getBlobUrl() {
		return blobUrl;
	}

	public void setBlobUrl(String blobUrl) {
		this.blobUrl = blobUrl;
	}

	public String getrCatName() {
		return rCatName;
	}

	public void setrCatName(String rCatName) {
		this.rCatName = rCatName;
	}

}