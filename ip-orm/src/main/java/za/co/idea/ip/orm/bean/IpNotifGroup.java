package za.co.idea.ip.orm.bean;

/**
 * IpNotifGroup entity. @author MyEclipse Persistence Tools
 */

public class IpNotifGroup implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5793739506556690923L;
	private Long ingId;
	private String ingNotifId;
	private Long ingGrpId;

	// Constructors

	/** default constructor */
	public IpNotifGroup() {
	}

	/** minimal constructor */
	public IpNotifGroup(Long ingId) {
		this.ingId = ingId;
	}

	/** full constructor */
	public IpNotifGroup(Long ingId, String ingNotifId, Long ingGrpId) {
		this.ingId = ingId;
		this.ingNotifId = ingNotifId;
		this.ingGrpId = ingGrpId;
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

}