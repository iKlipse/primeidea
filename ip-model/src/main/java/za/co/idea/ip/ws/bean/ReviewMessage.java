package za.co.idea.ip.ws.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "reviewMessage")
public class ReviewMessage {
	private Integer statusId;
	private Long[] groupId;
	private Long entityId;
	private String tblNm;

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Long[] getGroupId() {
		return groupId;
	}

	public void setGroupId(Long[] groupId) {
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

}
