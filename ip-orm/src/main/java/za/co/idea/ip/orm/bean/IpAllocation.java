package za.co.idea.ip.orm.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * IpAllocation entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("rawtypes")
public class IpAllocation implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8070850045304717359L;
	private Integer allocId;
	private String allocDesc;
	private Integer allocVal;
	private String allocEntity;
	private Integer allocStatusId;
	private Date allocCrtdDt;
	private Set ipPointses = new HashSet(0);

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
	public IpAllocation(Integer allocId, String allocDesc, Integer allocVal,
			String allocEntity, Integer allocStatusId, Date allocCrtdDt,
			Set ipPointses) {
		this.allocId = allocId;
		this.allocDesc = allocDesc;
		this.allocVal = allocVal;
		this.allocEntity = allocEntity;
		this.allocStatusId = allocStatusId;
		this.allocCrtdDt = allocCrtdDt;
		this.ipPointses = ipPointses;
	}

	// Property accessors

	public Integer getAllocId() {
		return this.allocId;
	}

	public void setAllocId(Integer allocId) {
		this.allocId = allocId;
	}

	public String getAllocDesc() {
		return this.allocDesc;
	}

	public void setAllocDesc(String allocDesc) {
		this.allocDesc = allocDesc;
	}

	public Integer getAllocVal() {
		return this.allocVal;
	}

	public void setAllocVal(Integer allocVal) {
		this.allocVal = allocVal;
	}

	public String getAllocEntity() {
		return this.allocEntity;
	}

	public void setAllocEntity(String allocEntity) {
		this.allocEntity = allocEntity;
	}

	public Integer getAllocStatusId() {
		return this.allocStatusId;
	}

	public void setAllocStatusId(Integer allocStatusId) {
		this.allocStatusId = allocStatusId;
	}

	public Date getAllocCrtdDt() {
		return this.allocCrtdDt;
	}

	public void setAllocCrtdDt(Date allocCrtdDt) {
		this.allocCrtdDt = allocCrtdDt;
	}

	public Set getIpPointses() {
		return this.ipPointses;
	}

	public void setIpPointses(Set ipPointses) {
		this.ipPointses = ipPointses;
	}

}