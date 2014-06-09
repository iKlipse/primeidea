package za.co.idea.ip.orm.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * IpSolutionStatus entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("rawtypes")
public class IpSolutionStatus implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4146896209543935818L;
	private Integer ssId;
	private String ssDesc;
	private Set ipSolutions = new HashSet(0);

	// Constructors

	/** default constructor */
	public IpSolutionStatus() {
	}

	/** minimal constructor */
	public IpSolutionStatus(Integer ssId) {
		this.ssId = ssId;
	}

	/** full constructor */
	public IpSolutionStatus(Integer ssId, String ssDesc, Set ipSolutions) {
		this.ssId = ssId;
		this.ssDesc = ssDesc;
		this.ipSolutions = ipSolutions;
	}

	// Property accessors

	public Integer getSsId() {
		return this.ssId;
	}

	public void setSsId(Integer ssId) {
		this.ssId = ssId;
	}

	public String getSsDesc() {
		return this.ssDesc;
	}

	public void setSsDesc(String ssDesc) {
		this.ssDesc = ssDesc;
	}

	public Set getIpSolutions() {
		return this.ipSolutions;
	}

	public void setIpSolutions(Set ipSolutions) {
		this.ipSolutions = ipSolutions;
	}

}