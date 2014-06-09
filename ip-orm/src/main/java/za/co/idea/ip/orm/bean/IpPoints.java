package za.co.idea.ip.orm.bean;

/**
 * IpPoints entity. @author MyEclipse Persistence Tools
 */

public class IpPoints implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6456518924772050061L;
	private Long pointId;
	private IpAllocation ipAllocation;
	private IpUser ipUser;
	private Integer pointValue;

	// Constructors

	/** default constructor */
	public IpPoints() {
	}

	/** minimal constructor */
	public IpPoints(Long pointId, Integer pointValue) {
		this.pointId = pointId;
		this.pointValue = pointValue;
	}

	/** full constructor */
	public IpPoints(Long pointId, IpAllocation ipAllocation, IpUser ipUser, Integer pointValue) {
		this.pointId = pointId;
		this.ipAllocation = ipAllocation;
		this.ipUser = ipUser;
		this.pointValue = pointValue;
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
		return pointValue;
	}

	public void setPointValue(Integer pointValue) {
		this.pointValue = pointValue;
	}

}