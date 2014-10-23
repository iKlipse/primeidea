package za.co.idea.ip.orm.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * IpSolutionCat entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_solution_cat", catalog = "lpdb")
public class IpSolutionCat implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1234536972201912010L;
	private Integer scId;
	private String scDesc;
	private Set<IpSolution> ipSolutions = new HashSet<IpSolution>(0);

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
	public IpSolutionCat(Integer scId, String scDesc, Set<IpSolution> ipSolutions) {
		this.scId = scId;
		this.scDesc = scDesc;
		this.ipSolutions = ipSolutions;
	}

	// Property accessors
	@Id
	@Column(name = "sc_id", unique = true, nullable = false)
	public Integer getScId() {
		return this.scId;
	}

	public void setScId(Integer scId) {
		this.scId = scId;
	}

	@Column(name = "sc_desc", nullable = false, length = 45)
	public String getScDesc() {
		return this.scDesc;
	}

	public void setScDesc(String scDesc) {
		this.scDesc = scDesc;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipSolutionCat")
	public Set<IpSolution> getIpSolutions() {
		return this.ipSolutions;
	}

	public void setIpSolutions(Set<IpSolution> ipSolutions) {
		this.ipSolutions = ipSolutions;
	}

}