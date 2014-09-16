package za.co.idea.ip.orm.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 * IpGroupUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_group_user", catalog = "lpdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "deleteGUByGroupId", query = "delete from ip_group_user where gu_grp_id=:id"), @NamedNativeQuery(name = "fetchGUByGroupId", query = "select gu.* from ip_group_user gu where gu.gu_grp_id=:id", resultClass = IpGroupUser.class), @NamedNativeQuery(name = "fetchGUByUserId", query = "select gu.* from ip_group_user gu where gu.gu_usr_id=:id", resultClass = IpGroupUser.class) })
public class IpGroupUser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7359504595620983103L;
	private Long guId;
	private IpUser ipUser;
	private IpGroup ipGroup;
	private Date guCrtdDt;

	// Constructors

	/** default constructor */
	public IpGroupUser() {
	}

	/** full constructor */
	public IpGroupUser(Long guId, IpUser ipUser, IpGroup ipGroup, Date guCrtdDt) {
		this.guId = guId;
		this.ipUser = ipUser;
		this.ipGroup = ipGroup;
		this.guCrtdDt = guCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "gu_id", unique = true, nullable = false)
	public Long getGuId() {
		return this.guId;
	}

	public void setGuId(Long guId) {
		this.guId = guId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gu_usr_id", nullable = false)
	public IpUser getIpUser() {
		return this.ipUser;
	}

	public void setIpUser(IpUser ipUser) {
		this.ipUser = ipUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gu_grp_id", nullable = false)
	public IpGroup getIpGroup() {
		return this.ipGroup;
	}

	public void setIpGroup(IpGroup ipGroup) {
		this.ipGroup = ipGroup;
	}

	@Column(name = "gu_crtd_dt", nullable = false, length = 19)
	public Date getGuCrtdDt() {
		return this.guCrtdDt;
	}

	public void setGuCrtdDt(Date guCrtdDt) {
		this.guCrtdDt = guCrtdDt;
	}

}