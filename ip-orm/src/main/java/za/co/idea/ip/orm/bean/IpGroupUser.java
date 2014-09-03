package za.co.idea.ip.orm.bean;

import java.util.Date;

/**
 * IpGroupUser entity. @author MyEclipse Persistence Tools
 */

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

	public Long getGuId() {
		return this.guId;
	}

	public void setGuId(Long guId) {
		this.guId = guId;
	}

	public IpUser getIpUser() {
		return this.ipUser;
	}

	public void setIpUser(IpUser ipUser) {
		this.ipUser = ipUser;
	}

	public IpGroup getIpGroup() {
		return this.ipGroup;
	}

	public void setIpGroup(IpGroup ipGroup) {
		this.ipGroup = ipGroup;
	}

	public Date getGuCrtdDt() {
		return this.guCrtdDt;
	}

	public void setGuCrtdDt(Date guCrtdDt) {
		this.guCrtdDt = guCrtdDt;
	}

}