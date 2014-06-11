package za.co.idea.ip.orm.bean;

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
	private static final long serialVersionUID = 6204940908807135390L;
	private Integer allocId;
	private String allocDesc;
	private Integer allocVal;
	private String allocEntity;
	private Integer allocStatusId;
	private Set ipPointses = new HashSet(0);

	// Constructors

	/** default constructor */
	public IpAllocation() {
	}

	/** minimal constructor */
	public IpAllocation(Integer allocId) {
		this.allocId = allocId;
	}

	/** full constructor */
	public IpAllocation(Integer allocId, String allocDesc, String allocEntity, Integer allocStatusId, Integer allocVal, Set ipPointses) {
		this.allocId = allocId;
		this.allocDesc = allocDesc;
		this.allocVal = allocVal;
		this.allocEntity = allocEntity;
		this.allocStatusId = allocStatusId;
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

	public Set getIpPointses() {
		return this.ipPointses;
	}

	public void setIpPointses(Set ipPointses) {
		this.ipPointses = ipPointses;
	}

	public String getAllocEntity() {
		return allocEntity;
	}

	public Integer getAllocStatusId() {
		return allocStatusId;
	}

	public void setAllocEntity(String allocEntity) {
		this.allocEntity = allocEntity;
	}

	public void setAllocStatusId(Integer allocStatusId) {
		this.allocStatusId = allocStatusId;
	}

}