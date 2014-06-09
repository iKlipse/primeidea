package za.co.idea.ip.ws.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "groupMessage")
public class GroupMessage {
	private Long gId;
	private String gName;
	private String geMail;
	private Long pGrpId;
	private Long admUserId;
	private Boolean isActive;
	private Long[] userIdList;

	public Long getgId() {
		return gId;
	}

	public void setgId(Long gId) {
		this.gId = gId;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public String getGeMail() {
		return geMail;
	}

	public void setGeMail(String geMail) {
		this.geMail = geMail;
	}

	public Long getpGrpId() {
		return pGrpId;
	}

	public void setpGrpId(Long pGrpId) {
		this.pGrpId = pGrpId;
	}

	public Long getAdmUserId() {
		return admUserId;
	}

	public void setAdmUserId(Long admUserId) {
		this.admUserId = admUserId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long[] getUserIdList() {
		if (userIdList == null)
			userIdList = new Long[] {};
		return userIdList;
	}

	public void setUserIdList(Long[] userIdList) {
		this.userIdList = userIdList;
	}

}
