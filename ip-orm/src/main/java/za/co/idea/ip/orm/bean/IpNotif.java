package za.co.idea.ip.orm.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 * IpNotif entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_notif", catalog = "lpdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "deleteNotifById", query = "delete from ip_notif where notif_id=:id") })
public class IpNotif implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7936758635097342188L;
	private String notifId;
	private Long notifEntityId;
	private String notifEntityTblName;
	private String notifSubject;
	private String notifBody;
	private String notifAttach;
	private String notifStatus;
	private String notifList;
	private Date notifCrtdDate;

	// Constructors

	/** default constructor */
	public IpNotif() {
	}

	/** minimal constructor */
	public IpNotif(String notifId, Date notifCrtdDate) {
		this.notifId = notifId;
		this.notifCrtdDate = notifCrtdDate;
	}

	/** full constructor */
	public IpNotif(String notifId, Long notifEntityId, String notifEntityTblName, String notifSubject, String notifBody, String notifAttach, String notifStatus, String notifList, Date notifCrtdDate) {
		this.notifId = notifId;
		this.notifEntityId = notifEntityId;
		this.notifEntityTblName = notifEntityTblName;
		this.notifSubject = notifSubject;
		this.notifBody = notifBody;
		this.notifAttach = notifAttach;
		this.notifStatus = notifStatus;
		this.notifList = notifList;
		this.notifCrtdDate = notifCrtdDate;
	}

	// Property accessors
	@Id
	@Column(name = "notif_id", unique = true, nullable = false, length = 36)
	public String getNotifId() {
		return this.notifId;
	}

	public void setNotifId(String notifId) {
		this.notifId = notifId;
	}

	@Column(name = "notif_entity_id")
	public Long getNotifEntityId() {
		return this.notifEntityId;
	}

	public void setNotifEntityId(Long notifEntityId) {
		this.notifEntityId = notifEntityId;
	}

	@Column(name = "notif_entity_tbl_name", length = 450)
	public String getNotifEntityTblName() {
		return this.notifEntityTblName;
	}

	public void setNotifEntityTblName(String notifEntityTblName) {
		this.notifEntityTblName = notifEntityTblName;
	}

	@Column(name = "notif_subject", length = 5000)
	public String getNotifSubject() {
		return this.notifSubject;
	}

	public void setNotifSubject(String notifSubject) {
		this.notifSubject = notifSubject;
	}

	@Column(name = "notif_body", length = 9999)
	public String getNotifBody() {
		return this.notifBody;
	}

	public void setNotifBody(String notifBody) {
		this.notifBody = notifBody;
	}

	@Column(name = "notif_attach")
	public String getNotifAttach() {
		return this.notifAttach;
	}

	public void setNotifAttach(String notifAttach) {
		this.notifAttach = notifAttach;
	}

	@Column(name = "notif_status", length = 1)
	public String getNotifStatus() {
		return this.notifStatus;
	}

	public void setNotifStatus(String notifStatus) {
		this.notifStatus = notifStatus;
	}

	@Column(name = "notif_list")
	public String getNotifList() {
		return this.notifList;
	}

	public void setNotifList(String notifList) {
		this.notifList = notifList;
	}

	@Column(name = "notif_crtd_date", nullable = false, length = 19)
	public Date getNotifCrtdDate() {
		return this.notifCrtdDate;
	}

	public void setNotifCrtdDate(Date notifCrtdDate) {
		this.notifCrtdDate = notifCrtdDate;
	}

}