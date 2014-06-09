package za.co.idea.ip.orm.bean;

import java.sql.Timestamp;

/**
 * IpLogin entity. @author MyEclipse Persistence Tools
 */

public class IpLogin implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -8779292447681305257L;
	private Long loginId;
	private IpUser ipUser;
	private String loginName;
	private String loginPwd;
	private Timestamp loginLastDt;
	private String loginSecQ;
	private String loginSecA;

	// Constructors

	/** default constructor */
	public IpLogin() {
	}

	/** minimal constructor */
	public IpLogin(Long loginId, IpUser ipUser, String loginName, String loginPwd, String loginSecQ, String loginSecA) {
		this.loginId = loginId;
		this.ipUser = ipUser;
		this.loginName = loginName;
		this.loginPwd = loginPwd;
		this.loginSecQ = loginSecQ;
		this.loginSecA = loginSecA;
	}

	/** full constructor */
	public IpLogin(Long loginId, IpUser ipUser, String loginName, String loginPwd, Timestamp loginLastDt, String loginSecQ, String loginSecA) {
		this.loginId = loginId;
		this.ipUser = ipUser;
		this.loginName = loginName;
		this.loginPwd = loginPwd;
		this.loginLastDt = loginLastDt;
		this.loginSecQ = loginSecQ;
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

	public Timestamp getLoginLastDt() {
		return this.loginLastDt;
	}

	public void setLoginLastDt(Timestamp loginLastDt) {
		this.loginLastDt = loginLastDt;
	}

	public String getLoginSecQ() {
		return this.loginSecQ;
	}

	public void setLoginSecQ(String loginSecQ) {
		this.loginSecQ = loginSecQ;
	}

	public String getLoginSecA() {
		return this.loginSecA;
	}

	public void setLoginSecA(String loginSecA) {
		this.loginSecA = loginSecA;
	}

}