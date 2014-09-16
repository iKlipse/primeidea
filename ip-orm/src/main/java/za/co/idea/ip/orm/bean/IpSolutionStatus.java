package za.co.idea.ip.orm.bean;

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
 * IpSolutionStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_solution_status", catalog = "lpdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "getNextSolStatus", query = "select iis.* from ip_solution_status iis where (iis.ss_id=:curr or	iis.ss_id in (select st.tran_next_state from ip_state_tran st where	st.tran_curr_state=:curr and lower(st.tran_entity)='ip_solution_status'))", resultClass = IpSolutionStatus.class), @NamedNativeQuery(name = "getNonAllocSolStatus", query = "select iis.* from ip_solution_status iis where iis.ss_id not in (select ia.alloc_status_id from ip_allocation ia where lower(ia.alloc_entity)='ip_solution_status')", resultClass = IpSolutionStatus.class) })
public class IpSolutionStatus implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = -4146896209543935818L;
	private Integer ssId;
	private String ssDesc;
	private Set<IpSolution> ipSolutions = new HashSet<IpSolution>(0);

	// Constructors

	/** default constructor */
	public IpSolutionStatus() {
	}

	/** minimal constructor */
	public IpSolutionStatus(Integer ssId) {
		this.ssId = ssId;
	}

	/** full constructor */
	public IpSolutionStatus(Integer ssId, String ssDesc, Set<IpSolution> ipSolutions) {
		this.ssId = ssId;
		this.ssDesc = ssDesc;
		this.ipSolutions = ipSolutions;
	}

	// Property accessors
	@Id
	@Column(name = "ss_id", unique = true, nullable = false)
	public Integer getSsId() {
		return this.ssId;
	}

	public void setSsId(Integer ssId) {
		this.ssId = ssId;
	}

	@Column(name = "ss_desc", length = 45)
	public String getSsDesc() {
		return this.ssDesc;
	}

	public void setSsDesc(String ssDesc) {
		this.ssDesc = ssDesc;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipSolutionStatus")
	public Set<IpSolution> getIpSolutions() {
		return this.ipSolutions;
	}

	public void setIpSolutions(Set<IpSolution> ipSolutions) {
		this.ipSolutions = ipSolutions;
	}

}