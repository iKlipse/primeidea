package za.co.idea.ip.portal.bean;

import java.io.Serializable;
import java.util.Date;

public class TagBean implements Serializable {
	private static final long serialVersionUID = 3679231894527770044L;
	private String usrScreenName;
	private String userFullName;
	private Integer teId;
	private Integer ttId;
	private String tagText;
	private Long entityId;
	private Long userId;
	private Long tagId;
	private Date tagDate;
	private boolean duplicate;
	private String blobUrl;

	public String getUsrScreenName() {
		return usrScreenName;
	}

	public void setUsrScreenName(String usrScreenName) {
		this.usrScreenName = usrScreenName;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public Integer getTeId() {
		return teId;
	}

	public void setTeId(Integer teId) {
		this.teId = teId;
	}

	public Integer getTtId() {
		return ttId;
	}

	public void setTtId(Integer ttId) {
		this.ttId = ttId;
	}

	public String getTagText() {
		return tagText;
	}

	public void setTagText(String tagText) {
		this.tagText = tagText;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public boolean isDuplicate() {
		return duplicate;
	}

	public void setDuplicate(boolean duplicate) {
		this.duplicate = duplicate;
	}

	public Date getTagDate() {
		return tagDate;
	}

	public void setTagDate(Date tagDate) {
		this.tagDate = tagDate;
	}

	public String getBlobUrl() {
		return blobUrl;
	}

	public void setBlobUrl(String blobUrl) {
		this.blobUrl = blobUrl;
	}
}
