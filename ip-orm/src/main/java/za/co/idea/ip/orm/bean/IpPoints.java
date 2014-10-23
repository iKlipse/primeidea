package za.co.idea.ip.orm.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 * IpPoints entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_points", catalog = "lpdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "getPointsByUser", query = "select pts.* from ip_points pts where pts.user_id=:id", resultClass = IpPoints.class) })
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
	@Id
	@Column(name = "point_id", unique = true, nullable = false)
	public Long getPointId() {
		return this.pointId;
	}

	public void setPointId(Long pointId) {
		this.pointId = pointId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "alloc_id")
	public IpAllocation getIpAllocation() {
		return this.ipAllocation;
	}

	public void setIpAllocation(IpAllocation ipAllocation) {
		this.ipAllocation = ipAllocation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public IpUser getIpUser() {
		return this.ipUser;
	}

	public void setIpUser(IpUser ipUser) {
		this.ipUser = ipUser;
	}

	@Column(name = "point_value")
	public Integer getPointValue() {
		return this.pointValue;
	}

	public void setPointValue(Integer pointValue) {
		this.pointValue = pointValue;
	}

	@Column(name = "comments", length = 450)
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "entity_id")
	public Long getEntityId() {
		return this.entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	@Column(name = "point_crtd_dt", nullable = false, length = 19)
	public Date getPointCrtdDt() {
		return this.pointCrtdDt;
	}

	public void setPointCrtdDt(Date pointCrtdDt) {
		this.pointCrtdDt = pointCrtdDt;
	}

}