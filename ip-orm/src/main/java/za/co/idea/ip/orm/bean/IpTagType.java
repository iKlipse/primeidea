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
 * IpTagType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_tag_type", catalog = "lpdb")
public class IpTagType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4368860406018432934L;
	private Integer ttId;
	private String ttDesc;
	private Set<IpTag> ipTags = new HashSet<IpTag>(0);

	// Constructors

	/** default constructor */
	public IpTagType() {
	}

	/** minimal constructor */
	public IpTagType(Integer ttId) {
		this.ttId = ttId;
	}

	/** full constructor */
	public IpTagType(Integer ttId, String ttDesc, Set<IpTag> ipTags) {
		this.ttId = ttId;
		this.ttDesc = ttDesc;
		this.ipTags = ipTags;
	}

	// Property accessors
	@Id
	@Column(name = "tt_id", unique = true, nullable = false)
	public Integer getTtId() {
		return this.ttId;
	}

	public void setTtId(Integer ttId) {
		this.ttId = ttId;
	}

	@Column(name = "tt_desc", length = 45)
	public String getTtDesc() {
		return this.ttDesc;
	}

	public void setTtDesc(String ttDesc) {
		this.ttDesc = ttDesc;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipTagType")
	public Set<IpTag> getIpTags() {
		return this.ipTags;
	}

	public void setIpTags(Set<IpTag> ipTags) {
		this.ipTags = ipTags;
	}

}