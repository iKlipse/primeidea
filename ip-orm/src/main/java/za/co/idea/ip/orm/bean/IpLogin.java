package za.co.idea.ip.orm.bean;

import java.util.Date;

/**
 * IpLogin entity. @author MyEclipse Persistence Tools
 */

public class IpLogin implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -413850731547950827L;
	private Long loginId;
	private IpUser ipUser;
	private IpSecqList ipSecqList;
	private String loginName;
	private String loginPwd;
	private Date loginLastDt;
	private String loginSecA;

	// Constructors

	/** default constructor */
	public IpLogin() {
	}

	/** minimal constructor */
	public IpLogin(Long loginId, IpUser ipUser, IpSecqList ipSecqList, String loginName, String loginPwd, String loginSecA) {
		this.loginId = loginId;
		this.ipUser = ipUser;
		this.ipSecqList = ipSecqList;
		this.loginName = loginName;
		this.loginPwd = loginPwd;
		this.loginSecA = loginSecA;
	}

	/** full constructor */
	public IpLogin(Long loginId, IpUser ipUser, IpSecqList ipSecqList, String loginName, String loginPwd, Date loginLastDt, String loginSecA) {
		this.loginId = loginId;
		this.ipUser = ipUser;
		this.ipSecqList = ipSecqList;
		this.loginName = loginName;
		this.loginPwd = loginPwd;
		this.loginLastDt = loginLastDt;
		this.loginSecA = loginSecA;
	}

	// Property accessors

	public Long getLoginId() {
		return this.loginId;
	}

	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	public IpUser getIpUser() {
		return this.ipUser;
	}

	public void setIpUser(IpUser ipUser) {
		this.ipUser = ipUser;
	}

	public IpSecqList getIpSecqList() {
		return this.ipSecqList;
	}

	public void setIpSecqList(IpSecqList ipSecqList) {
		this.ipSecqList = ipSecqList;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return this.loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public Date getLoginLastDt() {
		return this.loginLastDt;
	}

	public void setLoginLastDt(Date loginLastDt) {
		this.loginLastDt = loginLastDt;
	}

	public String getLoginSecA() {
		return this.loginSecA;
	}

	public void setLoginSecA(String loginSecA) {
		this.loginSecA = loginSecA;
	}

}