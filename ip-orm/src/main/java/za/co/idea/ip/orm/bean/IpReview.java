package za.co.idea.ip.orm.bean;

/**
 * IpReview entity. @author MyEclipse Persistence Tools
 */

public class IpReview implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = -5430584336659391560L;
	private Long revId;
	private IpUser ipUser;
	private Long revEntityId;
	private String revEntityName;

	// Constructors

	/** default constructor */
	public IpReview() {
	}

	/** minimal constructor */
	public IpReview(Long revId) {
		this.revId = revId;
	}

	/** full constructor */
	public IpReview(Long revId, IpUser ipUser, Long revEntityId,
			String revEntityName) {
		this.revId = revId;
		this.ipUser = ipUser;
		this.revEntityId = revEntityId;
		this.revEntityName = revEntityName;
	}

	// Property accessors

	public Long getRevId() {
		return this.revId;
	}

	public void setRevId(Long revId) {
		this.revId = revId;
	}

	public IpUser getIpUser() {
		return this.ipUser;
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

}