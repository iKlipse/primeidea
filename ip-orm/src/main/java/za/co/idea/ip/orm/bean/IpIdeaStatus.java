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
 * IpIdeaStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_idea_status", catalog = "lpdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "getNextIdeaStatus", query = "select iis.* from ip_idea_status iis where (iis.is_id=:curr or iis.is_id in (select st.tran_next_state from ip_state_tran st where	st.tran_curr_state=:curr and lower(st.tran_entity)='ip_idea_status'))", resultClass = IpIdeaStatus.class), @NamedNativeQuery(name = "getNonAllocIdeaStatus", query = "select iis.* from ip_idea_status iis where iis.is_id not in (select ia.alloc_status_id from ip_allocation ia where lower(ia.alloc_entity)='ip_idea_status')", resultClass = IpIdeaStatus.class) })
public class IpIdeaStatus implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2183703925197685802L;
	private Integer isId;
	private String isDesc;
	private Set<IpIdea> ipIdeas = new HashSet<IpIdea>(0);

	// Constructors

	/** default constructor */
	public IpIdeaStatus() {
	}

	/** minimal constructor */
	public IpIdeaStatus(Integer isId) {
		this.isId = isId;
	}

	/** full constructor */
	public IpIdeaStatus(Integer isId, String isDesc, Set<IpIdea> ipIdeas) {
		this.isId = isId;
		this.isDesc = isDesc;
		this.ipIdeas = ipIdeas;
	}

	// Property accessors
	@Id
	@Column(name = "is_id", unique = true, nullable = false)
	public Integer getIsId() {
		return this.isId;
	}

	public void setIsId(Integer isId) {
		this.isId = isId;
	}

	@Column(name = "is_desc", length = 45)
	public String getIsDesc() {
		return this.isDesc;
	}

	public void setIsDesc(String isDesc) {
		this.isDesc = isDesc;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipIdeaStatus")
	public Set<IpIdea> getIpIdeas() {
		return this.ipIdeas;
	}

	public void setIpIdeas(Set<IpIdea> ipIdeas) {
		this.ipIdeas = ipIdeas;
	}

}