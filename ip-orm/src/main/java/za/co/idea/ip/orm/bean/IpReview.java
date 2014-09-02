package za.co.idea.ip.orm.bean;

import java.util.Date;

/**
 * IpReview entity. @author MyEclipse Persistence Tools
 */

public class IpReview implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4222906687325264647L;
	private Long revId;
	private IpGroup ipGroup;
	private IpUser ipUser;
	private Long revEntityId;
	private String revEntityName;
	private Integer revEntityStatusId;
	private Date revCrtdDt;

	// Constructors

	/** default constructor */
	public IpReview() {
	}

	/** minimal constructor */
	public IpReview(Long revId, Date revCrtdDt) {
		this.revId = revId;
		this.revCrtdDt = revCrtdDt;
	}

	/** full constructor */
	public IpReview(Long revId, IpGroup ipGroup, IpUser ipUser, Long revEntityId, String revEntityName, Integer revEntityStatusId, Date revCrtdDt) {
		this.revId = revId;
		this.ipGroup = ipGroup;
		this.ipUser = ipUser;
		this.revEntityId = revEntityId;
		this.revEntityName = revEntityName;
		this.revEntityStatusId = revEntityStatusId;
		this.revCrtdDt = revCrtdDt;
	}

	// Property accessors

	public Long getRevId() {
		return this.revId;
	}

	public void setRevId(Long revId) {
		this.revId = revId;
	}

	public IpGroup getIpGroup() {
		return this.ipGroup;
	}

	public void setIpGroup(IpGroup ipGroup) {
		this.ipGroup = ipGroup;
	}

	public IpUser getIpUser() {
		return ipUser;
	}

	public void setIpUser(IpUser ipUser) {
		this.ipUser = ipUser;
	}

	public Long getRevEntityId() {
		return this.revEntityId;
	}

	public void setRevEntityId(Long revEntityId) {
		this.revEntityId = revEntityId;
	}

	public String getRevEntityName() {
		return this.revEntityName;
	}

	public void setRevEntityName(String revEntityName) {
		this.revEntityName = revEntityName;
	}

	public Integer getRevEntityStatusId() {
		return this.revEntityStatusId;
	}

	public void setRevEntityStatusId(Integer revEntityStatusId) {
		this.revEntityStatusId = revEntityStatusId;
	}

	public Date getRevCrtdDt() {
		return this.revCrtdDt;
	}

	public void setRevCrtdDt(Date revCrtdDt) {
		this.revCrtdDt = revCrtdDt;
	}

}