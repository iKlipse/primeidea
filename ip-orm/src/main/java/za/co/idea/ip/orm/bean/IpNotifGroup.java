package za.co.idea.ip.orm.bean;

import java.util.Date;

/**
 * IpNotifGroup entity. @author MyEclipse Persistence Tools
 */

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

	public Long getIngId() {
		return this.ingId;
	}

	public void setIngId(Long ingId) {
		this.ingId = ingId;
	}

	public String getIngNotifId() {
		return this.ingNotifId;
	}

	public void setIngNotifId(String ingNotifId) {
		this.ingNotifId = ingNotifId;
	}

	public Long getIngGrpId() {
		return this.ingGrpId;
	}

	public void setIngGrpId(Long ingGrpId) {
		this.ingGrpId = ingGrpId;
	}

	public Date getIngCrtdDt() {
		return this.ingCrtdDt;
	}

	public void setIngCrtdDt(Date ingCrtdDt) {
		this.ingCrtdDt = ingCrtdDt;
	}

}