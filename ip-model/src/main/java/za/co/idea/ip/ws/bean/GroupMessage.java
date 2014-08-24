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
	private String blobUrl;
	private boolean imgAvl;
	private String fileName;
	private String pGrpName;
	private String admUserName;

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
		return userIdList;
	}

	public void setUserIdList(Long[] userIdList) {
		this.userIdList = userIdList;
	}

	public String getBlobUrl() {
		return blobUrl;
	}

	public void setBlobUrl(String blobUrl) {
		this.blobUrl = blobUrl;
	}

	public boolean isImgAvl() {
		return imgAvl;
	}

	public void setImgAvl(boolean imgAvl) {
		this.imgAvl = imgAvl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getpGrpName() {
		return pGrpName;
	}

	public void setpGrpName(String pGrpName) {
		this.pGrpName = pGrpName;
	}

	public String getAdmUserName() {
		return admUserName;
	}

	public void setAdmUserName(String admUserName) {
		this.admUserName = admUserName;
	}

}
