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
 * IpSecqList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_secq_list", catalog = "lpdb")
public class IpSecqList implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 9152255946581695278L;
	private Integer islId;
	private String islDesc;
	private Set<IpLogin> ipLogins = new HashSet<IpLogin>(0);

	// Constructors

	/** default constructor */
	public IpSecqList() {
	}

	/** minimal constructor */
	public IpSecqList(Integer islId, String islDesc) {
		this.islId = islId;
		this.islDesc = islDesc;
	}

	/** full constructor */
	public IpSecqList(Integer islId, String islDesc, Set<IpLogin> ipLogins) {
		this.islId = islId;
		this.islDesc = islDesc;
		this.ipLogins = ipLogins;
	}

	// Property accessors
	@Id
	@Column(name = "isl_id", unique = true, nullable = false)
	public Integer getIslId() {
		return this.islId;
	}

	public void setIslId(Integer islId) {
		this.islId = islId;
	}

	@Column(name = "isl_desc", nullable = false, length = 450)
	public String getIslDesc() {
		return this.islDesc;
	}

	public void setIslDesc(String islDesc) {
		this.islDesc = islDesc;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipSecqList")
	public Set<IpLogin> getIpLogins() {
		return this.ipLogins;
	}

	public void setIpLogins(Set<IpLogin> ipLogins) {
		this.ipLogins = ipLogins;
	}

}