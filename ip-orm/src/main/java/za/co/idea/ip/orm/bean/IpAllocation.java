package za.co.idea.ip.orm.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * IpAllocation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_allocation", catalog = "lpdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "getAllocationByEntity", query = "select alloc.* from ip_allocation alloc where alloc.alloc_entity=:entity", resultClass = IpAllocation.class), @NamedNativeQuery(name = "getUsedAllocation", query = "select alloc.* from ip_allocation alloc, ip_points pnt where	alloc.alloc_entity=:entity and alloc.alloc_id=pnt.alloc_id", resultClass = IpAllocation.class) })
public class IpAllocation implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = -8070850045304717359L;
	private Integer allocId;
	private String allocDesc;
	private Integer allocVal;
	private String allocEntity;
	private Integer allocStatusId;
	private Date allocCrtdDt;
	private Set<IpPoints> ipPointses = new HashSet<IpPoints>(0);

	// Constructors

	/** default constructor */
	public IpAllocation() {
	}

	/** minimal constructor */
	public IpAllocation(Integer allocId, Date allocCrtdDt) {
		this.allocId = allocId;
		this.allocCrtdDt = allocCrtdDt;
	}

	/** full constructor */
	public IpAllocation(Integer allocId, String allocDesc, Integer allocVal, String allocEntity, Integer allocStatusId, Date allocCrtdDt, Set<IpPoints> ipPointses) {
		this.allocId = allocId;
		this.allocDesc = allocDesc;
		this.allocVal = allocVal;
		this.allocEntity = allocEntity;
		this.allocStatusId = allocStatusId;
		this.allocCrtdDt = allocCrtdDt;
		this.ipPointses = ipPointses;
	}

	// Property accessors
	@Id
	@Column(name = "alloc_id", unique = true, nullable = false)
	public Integer getAllocId() {
		return this.allocId;
	}

	public void setAllocId(Integer allocId) {
		this.allocId = allocId;
	}

	@Column(name = "alloc_desc", length = 45)
	public String getAllocDesc() {
		return this.allocDesc;
	}

	public void setAllocDesc(String allocDesc) {
		this.allocDesc = allocDesc;
	}

	@Column(name = "alloc_val")
	public Integer getAllocVal() {
		return this.allocVal;
	}

	public void setAllocVal(Integer allocVal) {
		this.allocVal = allocVal;
	}

	@Column(name = "alloc_entity", length = 300)
	public String getAllocEntity() {
		return this.allocEntity;
	}

	public void setAllocEntity(String allocEntity) {
		this.allocEntity = allocEntity;
	}

	@Column(name = "alloc_status_id")
	public Integer getAllocStatusId() {
		return this.allocStatusId;
	}

	public void setAllocStatusId(Integer allocStatusId) {
		this.allocStatusId = allocStatusId;
	}

	@Column(name = "alloc_crtd_dt", nullable = false, length = 19)
	public Date getAllocCrtdDt() {
		return this.allocCrtdDt;
	}

	public void setAllocCrtdDt(Date allocCrtdDt) {
		this.allocCrtdDt = allocCrtdDt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipAllocation")
	public Set<IpPoints> getIpPointses() {
		return this.ipPointses;
	}

	public void setIpPointses(Set<IpPoints> ipPointses) {
		this.ipPointses = ipPointses;
	}

}