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
 * IpFuncGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_func_group", catalog = "lpdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "deleteFGByFuncId", query = "delete from ip_func_group where	fg_func_id=:id"), @NamedNativeQuery(name = "fetchFGByFuncId", query = "select fg.* from ip_func_group fg where fg.fg_func_id=:id", resultClass = IpFuncGroup.class), @NamedNativeQuery(name = "fetchFGByGroupId", query = "select fg.* from ip_func_group fg where fg.fg_grp_id=:id", resultClass = IpFuncGroup.class) })
public class IpFuncGroup implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3247235635196806866L;
	private Long fgId;
	private IpGroup ipGroup;
	private IpFunction ipFunction;
	private Date fgCrtdDt;

	// Constructors

	/** default constructor */
	public IpFuncGroup() {
	}

	/** minimal constructor */
	public IpFuncGroup(Long fgId, IpGroup ipGroup, Date fgCrtdDt) {
		this.fgId = fgId;
		this.ipGroup = ipGroup;
		this.fgCrtdDt = fgCrtdDt;
	}

	/** full constructor */
	public IpFuncGroup(Long fgId, IpGroup ipGroup, IpFunction ipFunction, Date fgCrtdDt) {
		this.fgId = fgId;
		this.ipGroup = ipGroup;
		this.ipFunction = ipFunction;
		this.fgCrtdDt = fgCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "fg_id", unique = true, nullable = false)
	public Long getFgId() {
		return this.fgId;
	}

	public void setFgId(Long fgId) {
		this.fgId = fgId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fg_grp_id", nullable = false)
	public IpGroup getIpGroup() {
		return this.ipGroup;
	}

	public void setIpGroup(IpGroup ipGroup) {
		this.ipGroup = ipGroup;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fg_func_id")
	public IpFunction getIpFunction() {
		return this.ipFunction;
	}

	public void setIpFunction(IpFunction ipFunction) {
		this.ipFunction = ipFunction;
	}

	@Column(name = "fg_crtd_dt", nullable = false, length = 19)
	public Date getFgCrtdDt() {
		return this.fgCrtdDt;
	}

	public void setFgCrtdDt(Date fgCrtdDt) {
		this.fgCrtdDt = fgCrtdDt;
	}

}