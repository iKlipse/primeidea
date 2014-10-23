package za.co.idea.ip.portal.bean;

import java.io.Serializable;

public class ReviewBean implements Serializable {
	private static final long serialVersionUID = -8436592149241584186L;
	private Integer statusId;
	private String[] groupId;
	private Long entityId;
	private String tblNm;
	private String reviewers;

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String[] getGroupId() {
		return groupId;
	}

	public void setGroupId(String[] groupId) {
		this.groupId = groupId;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getTblNm() {
		return tblNm;
	}

	public void setTblNm(String tblNm) {
		this.tblNm = tblNm;
	}

	public String getReviewers() {
		return reviewers;
	}

	public void setReviewers(String reviewers) {
		this.reviewers = reviewers;
	}

}
