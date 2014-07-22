package za.co.idea.web.ui.bean;

import java.io.Serializable;
import java.util.List;

public class NotificationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3504001051490637639L;
	private String notifId;
	private Long notifEntityId;
	private String notifEntityTblName;
	private String notifSubject;
	private String notifBody;
	private String notifAttach;
	private String notifStatus;
	private String notifCrtdDate;
	private List<Long> groupIdList;

	public String getNotifId() {
		return notifId;
	}

	public void setNotifId(String notifId) {
		this.notifId = notifId;
	}

	public Long getNotifEntityId() {
		return notifEntityId;
	}

	public void setNotifEntityId(Long notifEntityId) {
		this.notifEntityId = notifEntityId;
	}

	public String getNotifEntityTblName() {
		return notifEntityTblName;
	}

	public void setNotifEntityTblName(String notifEntityTblName) {
		this.notifEntityTblName = notifEntityTblName;
	}

	public String getNotifSubject() {
		return notifSubject;
	}

	public void setNotifSubject(String notifSubject) {
		this.notifSubject = notifSubject;
	}

	public String getNotifBody() {
		return notifBody;
	}

	public void setNotifBody(String notifBody) {
		this.notifBody = notifBody;
	}

	public String getNotifAttach() {
		return notifAttach;
	}

	public void setNotifAttach(String notifAttach) {
		this.notifAttach = notifAttach;
	}

	public String getNotifStatus() {
		return notifStatus;
	}

	public void setNotifStatus(String notifStatus) {
		this.notifStatus = notifStatus;
	}

	public String getNotifCrtdDate() {
		return notifCrtdDate;
	}

	public void setNotifCrtdDate(String notifCrtdDate) {
		this.notifCrtdDate = notifCrtdDate;
	}

	public List<Long> getGroupIdList() {
		return groupIdList;
	}

	public void setGroupIdList(List<Long> groupIdList) {
		this.groupIdList = groupIdList;
	}

}
