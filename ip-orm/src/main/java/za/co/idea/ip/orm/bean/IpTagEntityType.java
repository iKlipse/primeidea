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
 * IpTagEntityType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_tag_entity_type", catalog = "lpdb")
public class IpTagEntityType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4250843679878888337L;
	private Integer teId;
	private String teDesc;
	private Set<IpTag> ipTags = new HashSet<IpTag>(0);

	// Constructors

	/** default constructor */
	public IpTagEntityType() {
	}

	/** minimal constructor */
	public IpTagEntityType(Integer teId) {
		this.teId = teId;
	}

	/** full constructor */
	public IpTagEntityType(Integer teId, String teDesc, Set<IpTag> ipTags) {
		this.teId = teId;
		this.teDesc = teDesc;
		this.ipTags = ipTags;
	}

	// Property accessors
	@Id
	@Column(name = "te_id", unique = true, nullable = false)
	public Integer getTeId() {
		return this.teId;
	}

	public void setTeId(Integer teId) {
		this.teId = teId;
	}

	@Column(name = "te_desc", length = 45)
	public String getTeDesc() {
		return this.teDesc;
	}

	public void setTeDesc(String teDesc) {
		this.teDesc = teDesc;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipTagEntityType")
	public Set<IpTag> getIpTags() {
		return this.ipTags;
	}

	public void setIpTags(Set<IpTag> ipTags) {
		this.ipTags = ipTags;
	}

}