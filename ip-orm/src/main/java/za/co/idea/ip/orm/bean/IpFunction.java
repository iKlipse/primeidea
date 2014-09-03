package za.co.idea.ip.orm.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * IpFunction entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("rawtypes")
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
	private Set ipFuncGroups = new HashSet(0);

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
	public IpFunction(Long funcId, IpUser ipUser, String funcName, String funcIsCore, Date funcCrtdDt, Set ipFuncGroups) {
		this.funcId = funcId;
		this.ipUser = ipUser;
		this.funcName = funcName;
		this.funcIsCore = funcIsCore;
		this.funcCrtdDt = funcCrtdDt;
		this.ipFuncGroups = ipFuncGroups;
	}

	// Property accessors

	public Long getFuncId() {
		return this.funcId;
	}

	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}

	public IpUser getIpUser() {
		return this.ipUser;
	}

	public void setIpUser(IpUser ipUser) {
		this.ipUser = ipUser;
	}

	public String getFuncName() {
		return this.funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getFuncIsCore() {
		return this.funcIsCore;
	}

	public void setFuncIsCore(String funcIsCore) {
		this.funcIsCore = funcIsCore;
	}

	public Date getFuncCrtdDt() {
		return this.funcCrtdDt;
	}

	public void setFuncCrtdDt(Date funcCrtdDt) {
		this.funcCrtdDt = funcCrtdDt;
	}

	public Set getIpFuncGroups() {
		return this.ipFuncGroups;
	}

	public void setIpFuncGroups(Set ipFuncGroups) {
		this.ipFuncGroups = ipFuncGroups;
	}

}