package za.co.idea.ip.orm.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * IpFunction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_function", catalog = "lpdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "getAllFunction", query = "select func.* from ip_function func", resultClass = IpFunction.class), @NamedNativeQuery(name = "getFunctionById", query = "select func.* from ip_function func where func.func_id=:id", resultClass = IpFunction.class), @NamedNativeQuery(name = "getFunctionByUserId", query = "select func.* from ip_function func, ip_func_group grp, ip_group_user igu where igu.gu_usr_id=:id and igu.gu_grp_id=grp.fg_grp_id and grp.fg_func_id=func.func_id and func.func_is_core in ('y','Y')", resultClass = IpFunction.class) })
public class IpFunction implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4636381273429968254L;
	private Long funcId;
	private IpUser ipUser;
	private String funcName;
	private String funcIsCore;
	private Date funcCrtdDt;
	private Set<IpFuncGroup> ipFuncGroups = new HashSet<IpFuncGroup>(0);

	// Constructors

	/** default constructor */
	public IpFunction() {
	}

	/** minimal constructor */
	public IpFunction(Long funcId, String funcName, String funcIsCore, Date funcCrtdDt) {
		this.funcId = funcId;
		this.funcName = funcName;
		this.funcIsCore = funcIsCore;
		this.funcCrtdDt = funcCrtdDt;
	}

	/** full constructor */
	public IpFunction(Long funcId, IpUser ipUser, String funcName, String funcIsCore, Date funcCrtdDt, Set<IpFuncGroup> ipFuncGroups) {
		this.funcId = funcId;
		this.ipUser = ipUser;
		this.funcName = funcName;
		this.funcIsCore = funcIsCore;
		this.funcCrtdDt = funcCrtdDt;
		this.ipFuncGroups = ipFuncGroups;
	}

	// Property accessors
	@Id
	@Column(name = "func_id", unique = true, nullable = false)
	public Long getFuncId() {
		return this.funcId;
	}

	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "func_crtd_by")
	public IpUser getIpUser() {
		return this.ipUser;
	}

	public void setIpUser(IpUser ipUser) {
		this.ipUser = ipUser;
	}

	@Column(name = "func_name", nullable = false, length = 65535)
	public String getFuncName() {
		return this.funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	@Column(name = "func_is_core", nullable = false, length = 1)
	public String getFuncIsCore() {
		return this.funcIsCore;
	}

	public void setFuncIsCore(String funcIsCore) {
		this.funcIsCore = funcIsCore;
	}

	@Column(name = "func_crtd_dt", nullable = false, length = 19)
	public Date getFuncCrtdDt() {
		return this.funcCrtdDt;
	}

	public void setFuncCrtdDt(Date funcCrtdDt) {
		this.funcCrtdDt = funcCrtdDt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipFunction")
	public Set<IpFuncGroup> getIpFuncGroups() {
		return this.ipFuncGroups;
	}

	public void setIpFuncGroups(Set<IpFuncGroup> ipFuncGroups) {
		this.ipFuncGroups = ipFuncGroups;
	}

}