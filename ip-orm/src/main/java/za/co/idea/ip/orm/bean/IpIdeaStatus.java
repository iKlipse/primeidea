package za.co.idea.ip.orm.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * IpIdeaStatus entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("rawtypes")
public class IpIdeaStatus implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2183703925197685802L;
	private Integer isId;
	private String isDesc;
	private Set ipIdeas = new HashSet(0);

	// Constructors

	/** default constructor */
	public IpIdeaStatus() {
	}

	/** minimal constructor */
	public IpIdeaStatus(Integer isId) {
		this.isId = isId;
	}

	/** full constructor */
	public IpIdeaStatus(Integer isId, String isDesc, Set ipIdeas) {
		this.isId = isId;
		this.isDesc = isDesc;
		this.ipIdeas = ipIdeas;
	}

	// Property accessors

	public Integer getIsId() {
		return this.isId;
	}

	public void setIsId(Integer isId) {
		this.isId = isId;
	}

	public String getIsDesc() {
		return this.isDesc;
	}

	public void setIsDesc(String isDesc) {
		this.isDesc = isDesc;
	}

	public Set getIpIdeas() {
		return this.ipIdeas;
	}

	public void setIpIdeas(Set ipIdeas) {
		this.ipIdeas = ipIdeas;
	}

}