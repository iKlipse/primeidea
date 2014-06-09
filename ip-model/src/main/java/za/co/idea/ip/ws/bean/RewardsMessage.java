package za.co.idea.ip.ws.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rewardsMessage")
public class RewardsMessage {
	private Long rwId;
	private Integer rCatId;
	private Integer rStatusId;
	private String rwTitle;
	private String rwDesc;
	private Integer rwValue;
	private Integer rwStockCodeNum;
	private String rwHoverText;
	private Date rwLaunchDt;
	private Date rwExpiryDt;
	private String rwTag;
	private Date rwCrtdDt;

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

	public Integer getrStatusId() {
		return rStatusId;
	}

	public void setrStatusId(Integer rStatusId) {
		this.rStatusId = rStatusId;
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

	public Integer getRwStockCodeNum() {
		return rwStockCodeNum;
	}

	public void setRwStockCodeNum(Integer rwStockCodeNum) {
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

}
