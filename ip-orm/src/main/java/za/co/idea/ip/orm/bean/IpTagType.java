package za.co.idea.ip.orm.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * IpTagType entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("rawtypes")
public class IpTagType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4368860406018432934L;
	private Integer ttId;
	private String ttDesc;
	private Set ipTags = new HashSet(0);

	// Constructors

	/** default constructor */
	public IpTagType() {
	}

	/** minimal constructor */
	public IpTagType(Integer ttId) {
		this.ttId = ttId;
	}

	/** full constructor */
	public IpTagType(Integer ttId, String ttDesc, Set ipTags) {
		this.ttId = ttId;
		this.ttDesc = ttDesc;
		this.ipTags = ipTags;
	}

	// Property accessors

	public Integer getTtId() {
		return this.ttId;
	}

	public void setTtId(Integer ttId) {
		this.ttId = ttId;
	}

	public String getTtDesc() {
		return this.ttDesc;
	}

	public void setTtDesc(String ttDesc) {
		this.ttDesc = ttDesc;
	}

	public Set getIpTags() {
		return this.ipTags;
	}

	public void setIpTags(Set ipTags) {
		this.ipTags = ipTags;
	}

}