package za.co.idea.ip.orm.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * IpTagEntityType entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("rawtypes")
public class IpTagEntityType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4250843679878888337L;
	private Integer teId;
	private String teDesc;
	private Set ipTags = new HashSet(0);

	// Constructors

	/** default constructor */
	public IpTagEntityType() {
	}

	/** minimal constructor */
	public IpTagEntityType(Integer teId) {
		this.teId = teId;
	}

	/** full constructor */
	public IpTagEntityType(Integer teId, String teDesc, Set ipTags) {
		this.teId = teId;
		this.teDesc = teDesc;
		this.ipTags = ipTags;
	}

	// Property accessors

	public Integer getTeId() {
		return this.teId;
	}

	public void setTeId(Integer teId) {
		this.teId = teId;
	}

	public String getTeDesc() {
		return this.teDesc;
	}

	public void setTeDesc(String teDesc) {
		this.teDesc = teDesc;
	}

	public Set getIpTags() {
		return this.ipTags;
	}

	public void setIpTags(Set ipTags) {
		this.ipTags = ipTags;
	}

}