package za.co.idea.ip.orm.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 * IpNotifGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_notif_group", catalog = "lpdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "deleteNGByNotifId", query = "delete from ip_notif_group where ing_notif_id=:id"), @NamedNativeQuery(name = "fetchNGByNotifId", query = "select ng.* from ip_notif_group ng where ng.ing_notif_id=:id", resultClass = IpNotifGroup.class) })
public class IpNotifGroup implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4830611793867122100L;
	private Long ingId;
	private String ingNotifId;
	private Long ingGrpId;
	private Date ingCrtdDt;

	// Constructors

	/** default constructor */
	public IpNotifGroup() {
	}

	/** minimal constructor */
	public IpNotifGroup(Long ingId, Date ingCrtdDt) {
		this.ingId = ingId;
		this.ingCrtdDt = ingCrtdDt;
	}

	/** full constructor */
	public IpNotifGroup(Long ingId, String ingNotifId, Long ingGrpId, Date ingCrtdDt) {
		this.ingId = ingId;
		this.ingNotifId = ingNotifId;
		this.ingGrpId = ingGrpId;
		this.ingCrtdDt = ingCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "ing_id", unique = true, nullable = false)
	public Long getIngId() {
		return this.ingId;
	}

	public void setIngId(Long ingId) {
		this.ingId = ingId;
	}

	@Column(name = "ing_notif_id", length = 36)
	public String getIngNotifId() {
		return this.ingNotifId;
	}

	public void setIngNotifId(String ingNotifId) {
		this.ingNotifId = ingNotifId;
	}

	@Column(name = "ing_grp_id")
	public Long getIngGrpId() {
		return this.ingGrpId;
	}

	public void setIngGrpId(Long ingGrpId) {
		this.ingGrpId = ingGrpId;
	}

	@Column(name = "ing_crtd_dt", nullable = false, length = 19)
	public Date getIngCrtdDt() {
		return this.ingCrtdDt;
	}

	public void setIngCrtdDt(Date ingCrtdDt) {
		this.ingCrtdDt = ingCrtdDt;
	}

}