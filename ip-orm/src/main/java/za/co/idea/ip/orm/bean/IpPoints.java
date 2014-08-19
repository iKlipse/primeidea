package za.co.idea.ip.orm.bean;

/**
 * IpPoints entity. @author MyEclipse Persistence Tools
 */

public class IpPoints implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4877949694625004644L;
	private Long pointId;
	private IpAllocation ipAllocation;
	private IpUser ipUser;
	private Integer pointValue;
	private String comments;
	private Long entityId;

	// Constructors

	/** default constructor */
	public IpPoints() {
	}

	/** minimal constructor */
	public IpPoints(Long pointId) {
		this.pointId = pointId;
	}

	/** full constructor */
	public IpPoints(Long pointId, IpAllocation ipAllocation, IpUser ipUser, Integer pointValue, String comments, Long entityId) {
		this.pointId = pointId;
		this.ipAllocation = ipAllocation;
		this.ipUser = ipUser;
		this.pointValue = pointValue;
		this.comments = comments;
		this.entityId = entityId;
	}

	// Property accessors

	public Long getPointId() {
		return this.pointId;
	}

	public void setPointId(Long pointId) {
		this.pointId = pointId;
	}

	public IpAllocation getIpAllocation() {
		return this.ipAllocation;
	}

	public void setIpAllocation(IpAllocation ipAllocation) {
		this.ipAllocation = ipAllocation;
	}

	public IpUser getIpUser() {
		return this.ipUser;
	}

	public void setIpUser(IpUser ipUser) {
		this.ipUser = ipUser;
	}

	public Integer getPointValue() {
		return this.pointValue;
	}

	public void setPointValue(Integer pointValue) {
		this.pointValue = pointValue;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Long getEntityId() {
		return this.entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

}