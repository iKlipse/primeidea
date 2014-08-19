package za.co.idea.ip.orm.bean;

/**
 * IpIdeaGroup entity. @author MyEclipse Persistence Tools
 */

public class IpIdeaGroup implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -9093634632757238855L;
	private Long igId;
	private IpIdea ipIdea;
	private IpGroup ipGroup;

	// Constructors

	/** default constructor */
	public IpIdeaGroup() {
	}

	/** minimal constructor */
	public IpIdeaGroup(Long igId) {
		this.igId = igId;
	}

	/** full constructor */
	public IpIdeaGroup(Long igId, IpIdea ipIdea, IpGroup ipGroup) {
		this.igId = igId;
		this.ipIdea = ipIdea;
		this.ipGroup = ipGroup;
	}

	// Property accessors

	public Long getIgId() {
		return this.igId;
	}

	public void setIgId(Long igId) {
		this.igId = igId;
	}

	public IpIdea getIpIdea() {
		return this.ipIdea;
	}

	public void setIpIdea(IpIdea ipIdea) {
		this.ipIdea = ipIdea;
	}

	public IpGroup getIpGroup() {
		return this.ipGroup;
	}

	public void setIpGroup(IpGroup ipGroup) {
		this.ipGroup = ipGroup;
	}

}