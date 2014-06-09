package za.co.idea.web.ui.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GroupBean implements Serializable {

	private static final long serialVersionUID = 7914098858832070407L;
	private Long gId;
	private String gName;
	private String geMail;
	private Long selPGrp;
	private Long selAdmUser;
	private Boolean isActive;
	private List<Long> userIdList;

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

}
