package za.co.idea.ip.portal.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GroupBean implements Serializable {

	private static final long serialVersionUID = 7914098858832070407L;
	private Long gId;
	private String gName;
	private String geMail;
	private Long selPGrp;
	private String selPGrpName;
	private Long selAdmUser;
	private String selAdmUserName;
	private Boolean isActive;
	private List<Long> userIdList;
	private String contentType;
	private String fileName;
	private boolean imgAvl;
	private String blobUrl;
	private Date gCrtdDate;

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

	public Long getSelPGrp() {
		return selPGrp;
	}

	public void setSelPGrp(Long selPGrp) {
		this.selPGrp = selPGrp;
	}

	public Long getSelAdmUser() {
		return selAdmUser;
	}

	public void setSelAdmUser(Long selAdmUser) {
		this.selAdmUser = selAdmUser;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public List<Long> getUserIdList() {
		if (userIdList == null)
			userIdList = new ArrayList<Long>();
		return userIdList;
	}

	public void setUserIdList(List<Long> userIdList) {
		this.userIdList = userIdList;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public String getSelPGrpName() {
		return selPGrpName;
	}

	public void setSelPGrpName(String selPGrpName) {
		this.selPGrpName = selPGrpName;
	}

	public String getSelAdmUserName() {
		return selAdmUserName;
	}

	public void setSelAdmUserName(String selAdmUserName) {
		this.selAdmUserName = selAdmUserName;
	}

	public Date getgCrtdDate() {
		return gCrtdDate;
	}

	public void setgCrtdDate(Date gCrtdDate) {
		this.gCrtdDate = gCrtdDate;
	}

}
