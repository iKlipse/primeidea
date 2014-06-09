package za.co.idea.ip.orm.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * IpIdeaCat entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("rawtypes")
public class IpIdeaCat implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6302309942670555070L;
	private Integer icId;
	private String icDesc;
	private Set ipIdeas = new HashSet(0);

	// Constructors

	/** default constructor */
	public IpIdeaCat() {
	}

	/** minimal constructor */
	public IpIdeaCat(Integer icId, String icDesc) {
		this.icId = icId;
		this.icDesc = icDesc;
	}

	/** full constructor */
	public IpIdeaCat(Integer icId, String icDesc, Set ipIdeas) {
		this.icId = icId;
		this.icDesc = icDesc;
		this.ipIdeas = ipIdeas;
	}

	// Property accessors

	public Integer getIcId() {
		return this.icId;
	}

	public void setIcId(Integer icId) {
		this.icId = icId;
	}

	public String getIcDesc() {
		return this.icDesc;
	}

	public void setIcDesc(String icDesc) {
		this.icDesc = icDesc;
	}

	public Set getIpIdeas() {
		return this.ipIdeas;
	}

	public void setIpIdeas(Set ipIdeas) {
		this.ipIdeas = ipIdeas;
	}

}