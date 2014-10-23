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
import javax.persistence.UniqueConstraint;

/**
 * IpRewardsCat entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_rewards_cat", catalog = "lpdb", uniqueConstraints = @UniqueConstraint(columnNames = "rc_desc"))
@NamedNativeQueries({ @NamedNativeQuery(name = "checkRwDependency", query = "select cat.* from ip_rewards_cat cat where cat.rc_id in (select distinct rw_cat from ip_rewards)", resultClass = IpRewardsCat.class) })
public class IpRewardsCat implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3698376466771933497L;
	private Integer rcId;
	private String rcDesc;
	private Set<IpRewards> ipRewardses = new HashSet<IpRewards>(0);

	// Constructors

	/** default constructor */
	public IpRewardsCat() {
	}

	/** minimal constructor */
	public IpRewardsCat(Integer rcId, String rcDesc) {
		this.rcId = rcId;
		this.rcDesc = rcDesc;
	}

	/** full constructor */
	public IpRewardsCat(Integer rcId, String rcDesc, Set<IpRewards> ipRewardses) {
		this.rcId = rcId;
		this.rcDesc = rcDesc;
		this.ipRewardses = ipRewardses;
	}

	// Property accessors
	@Id
	@Column(name = "rc_id", unique = true, nullable = false)
	public Integer getRcId() {
		return this.rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}

	@Column(name = "rc_desc", unique = true, nullable = false, length = 45)
	public String getRcDesc() {
		return this.rcDesc;
	}

	public void setRcDesc(String rcDesc) {
		this.rcDesc = rcDesc;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipRewardsCat")
	public Set<IpRewards> getIpRewardses() {
		return this.ipRewardses;
	}

	public void setIpRewardses(Set<IpRewards> ipRewardses) {
		this.ipRewardses = ipRewardses;
	}

}