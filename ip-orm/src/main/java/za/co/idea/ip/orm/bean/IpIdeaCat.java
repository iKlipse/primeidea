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
 * IpIdeaCat entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_idea_cat", catalog = "lpdb")
public class IpIdeaCat implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6302309942670555070L;
	private Integer icId;
	private String icDesc;
	private Set<IpIdea> ipIdeas = new HashSet<IpIdea>(0);

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
	public IpIdeaCat(Integer icId, String icDesc, Set<IpIdea> ipIdeas) {
		this.icId = icId;
		this.icDesc = icDesc;
		this.ipIdeas = ipIdeas;
	}

	// Property accessors
	@Id
	@Column(name = "ic_id", unique = true, nullable = false)
	public Integer getIcId() {
		return this.icId;
	}

	public void setIcId(Integer icId) {
		this.icId = icId;
	}

	@Column(name = "ic_desc", nullable = false, length = 45)
	public String getIcDesc() {
		return this.icDesc;
	}

	public void setIcDesc(String icDesc) {
		this.icDesc = icDesc;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipIdeaCat")
	public Set<IpIdea> getIpIdeas() {
		return this.ipIdeas;
	}

	public void setIpIdeas(Set<IpIdea> ipIdeas) {
		this.ipIdeas = ipIdeas;
	}

}