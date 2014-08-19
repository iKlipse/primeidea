package za.co.idea.ip.orm.bean;

/**
 * IpNotif entity. @author MyEclipse Persistence Tools
 */

public class IpNotif implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7903303376692793911L;
	private String notifId;
	private Long notifEntityId;
	private String notifEntityTblName;
	private String notifSubject;
	private String notifBody;
	private String notifAttach;
	private String notifStatus;
	private String notifCrtdDate;
	private String notifList;

	// Constructors

	/** default constructor */
	public IpNotif() {
	}

	/** minimal constructor */
	public IpNotif(String notifId) {
		this.notifId = notifId;
	}

	/** full constructor */
	public IpNotif(String notifId, Long notifEntityId, String notifEntityTblName, String notifSubject, String notifBody, String notifAttach, String notifStatus, String notifCrtdDate, String notifList) {
		this.notifId = notifId;
		this.notifEntityId = notifEntityId;
		this.notifEntityTblName = notifEntityTblName;
		this.notifSubject = notifSubject;
		this.notifBody = notifBody;
		this.notifAttach = notifAttach;
		this.notifStatus = notifStatus;
		this.notifCrtdDate = notifCrtdDate;
		this.notifList = notifList;
	}

	// Property accessors

	public String getNotifId() {
		return this.notifId;
	}

	public void setNotifId(String notifId) {
		this.notifId = notifId;
	}

	public Long getNotifEntityId() {
		return this.notifEntityId;
	}

	public void setNotifEntityId(Long notifEntityId) {
		this.notifEntityId = notifEntityId;
	}

	public String getNotifEntityTblName() {
		return this.notifEntityTblName;
	}

	public void setNotifEntityTblName(String notifEntityTblName) {
		this.notifEntityTblName = notifEntityTblName;
	}

	public String getNotifSubject() {
		return this.notifSubject;
	}

	public void setNotifSubject(String notifSubject) {
		this.notifSubject = notifSubject;
	}

	public String getNotifBody() {
		return this.notifBody;
	}

	public void setNotifBody(String notifBody) {
		this.notifBody = notifBody;
	}

	public String getNotifAttach() {
		return this.notifAttach;
	}

	public void setNotifAttach(String notifAttach) {
		this.notifAttach = notifAttach;
	}

	public String getNotifStatus() {
		return this.notifStatus;
	}

	public void setNotifStatus(String notifStatus) {
		this.notifStatus = notifStatus;
	}

	public String getNotifCrtdDate() {
		return this.notifCrtdDate;
	}

	public void setNotifCrtdDate(String notifCrtdDate) {
		this.notifCrtdDate = notifCrtdDate;
	}

	public String getNotifList() {
		return this.notifList;
	}

	public void setNotifList(String notifList) {
		this.notifList = notifList;
	}

}