package za.co.idea.ip.orm.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * IpSolutionCat entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("rawtypes")
public class IpSolutionCat implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1234536972201912010L;
	private Integer scId;
	private String scDesc;
	private Set ipSolutions = new HashSet(0);

	// Constructors

	/** default constructor */
	public IpSolutionCat() {
	}

	/** minimal constructor */
	public IpSolutionCat(Integer scId, String scDesc) {
		this.scId = scId;
		this.scDesc = scDesc;
	}

	/** full constructor */
	public IpSolutionCat(Integer scId, String scDesc, Set ipSolutions) {
		this.scId = scId;
		this.scDesc = scDesc;
		this.ipSolutions = ipSolutions;
	}

	// Property accessors

	public Integer getScId() {
		return this.scId;
	}

	public void setScId(Integer scId) {
		this.scId = scId;
	}

	public String getScDesc() {
		return this.scDesc;
	}

	public void setScDesc(String scDesc) {
		this.scDesc = scDesc;
	}

	public Set getIpSolutions() {
		return this.ipSolutions;
	}

	public void setIpSolutions(Set ipSolutions) {
		this.ipSolutions = ipSolutions;
	}

}