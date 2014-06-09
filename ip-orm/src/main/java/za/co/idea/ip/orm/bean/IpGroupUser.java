package za.co.idea.ip.orm.bean;

/**
 * IpGroupUser entity. @author MyEclipse Persistence Tools
 */

public class IpGroupUser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6334119771242371557L;
	private Long guId;
	private IpUser ipUser;
	private IpGroup ipGroup;

	// Constructors

	/** default constructor */
	public IpGroupUser() {
	}

	/** full constructor */
	public IpGroupUser(Long guId, IpUser ipUser, IpGroup ipGroup) {
		this.guId = guId;
		this.ipUser = ipUser;
		this.ipGroup = ipGroup;
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

}