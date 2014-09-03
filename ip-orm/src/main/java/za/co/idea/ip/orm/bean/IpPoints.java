package za.co.idea.ip.orm.bean;

import java.util.Date;

/**
 * IpPoints entity. @author MyEclipse Persistence Tools
 */

public class IpPoints implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6760834692255975288L;
	private Long pointId;
	private IpAllocation ipAllocation;
	private IpUser ipUser;
	private Integer pointValue;
	private String comments;
	private Long entityId;
	private Date pointCrtdDt;

	// Constructors

	/** default constructor */
	public IpPoints() {
	}

	/** minimal constructor */
	public IpPoints(Long pointId, Date pointCrtdDt) {
		this.pointId = pointId;
		this.pointCrtdDt = pointCrtdDt;
	}

	/** full constructor */
	public IpPoints(Long pointId, IpAllocation ipAllocation, IpUser ipUser, Integer pointValue, String comments, Long entityId, Date pointCrtdDt) {
		this.pointId = pointId;
		this.ipAllocation = ipAllocation;
		this.ipUser = ipUser;
		this.pointValue = pointValue;
		this.comments = comments;
		this.entityId = entityId;
		this.pointCrtdDt = pointCrtdDt;
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

	public Date getPointCrtdDt() {
		return this.pointCrtdDt;
	}

	public void setPointCrtdDt(Date pointCrtdDt) {
		this.pointCrtdDt = pointCrtdDt;
	}

}