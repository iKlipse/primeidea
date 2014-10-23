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
import javax.persistence.UniqueConstraint;

/**
 * IpLogin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_login", catalog = "lpdb", uniqueConstraints = @UniqueConstraint(columnNames = "login_user_id"))
@NamedNativeQueries({ @NamedNativeQuery(name = "verifyLogin", query = "select login.* from ip_login login, ip_user iu where iu.user_id=login.login_user_id and login.login_name=:login and	login.login_pwd=:pwd", resultClass = IpLogin.class), @NamedNativeQuery(name = "fetchLogin", query = "select login.* from ip_login login where login.login_name=:login", resultClass = IpLogin.class), @NamedNativeQuery(name = "fetchLoginById", query = "select login.* from ip_login login where login.login_user_id=:login", resultClass = IpLogin.class), @NamedNativeQuery(name = "updatePassword", query = "update ip_login set login_pwd=:pwd where login_name=:login"), @NamedNativeQuery(name = "updateSecurity", query = "update ip_login set login_sec_q=:secq, login_sec_a=:seca where login_name=:login") })
public class IpLogin implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8570433674808289109L;
	private Long loginId;
	private IpUser ipUser;
	private IpSecqList ipSecqList;
	private String loginName;
	private String loginPwd;
	private Date loginLastDt;
	private String loginSecA;
	private Date loginCrtdDt;

	// Constructors

	/** default constructor */
	public IpLogin() {
	}

	/** minimal constructor */
	public IpLogin(Long loginId, IpUser ipUser, IpSecqList ipSecqList, String loginName, String loginPwd, String loginSecA, Date loginCrtdDt) {
		this.loginId = loginId;
		this.ipUser = ipUser;
		this.ipSecqList = ipSecqList;
		this.loginName = loginName;
		this.loginPwd = loginPwd;
		this.loginSecA = loginSecA;
		this.loginCrtdDt = loginCrtdDt;
	}

	/** full constructor */
	public IpLogin(Long loginId, IpUser ipUser, IpSecqList ipSecqList, String loginName, String loginPwd, Date loginLastDt, String loginSecA, Date loginCrtdDt) {
		this.loginId = loginId;
		this.ipUser = ipUser;
		this.ipSecqList = ipSecqList;
		this.loginName = loginName;
		this.loginPwd = loginPwd;
		this.loginLastDt = loginLastDt;
		this.loginSecA = loginSecA;
		this.loginCrtdDt = loginCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "login_id", unique = true, nullable = false)
	public Long getLoginId() {
		return this.loginId;
	}

	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "login_user_id", unique = true, nullable = false)
	public IpUser getIpUser() {
		return this.ipUser;
	}

	public void setIpUser(IpUser ipUser) {
		this.ipUser = ipUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "login_sec_q", nullable = false)
	public IpSecqList getIpSecqList() {
		return this.ipSecqList;
	}

	public void setIpSecqList(IpSecqList ipSecqList) {
		this.ipSecqList = ipSecqList;
	}

	@Column(name = "login_name", nullable = false, length = 65535)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "login_pwd", nullable = false, length = 65535)
	public String getLoginPwd() {
		return this.loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	@Column(name = "login_last_dt", length = 19)
	public Date getLoginLastDt() {
		return this.loginLastDt;
	}

	public void setLoginLastDt(Date loginLastDt) {
		this.loginLastDt = loginLastDt;
	}

	@Column(name = "login_sec_a", nullable = false, length = 450)
	public String getLoginSecA() {
		return this.loginSecA;
	}

	public void setLoginSecA(String loginSecA) {
		this.loginSecA = loginSecA;
	}

	@Column(name = "login_crtd_dt", nullable = false, length = 19)
	public Date getLoginCrtdDt() {
		return this.loginCrtdDt;
	}

	public void setLoginCrtdDt(Date loginCrtdDt) {
		this.loginCrtdDt = loginCrtdDt;
	}

}