package za.co.idea.ip.orm.bean;

import java.util.Date;

/**
 * IpIdeaGroup entity. @author MyEclipse Persistence Tools
 */

public class IpIdeaGroup implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8409889379001065296L;
	private Long igId;
	private IpIdea ipIdea;
	private IpGroup ipGroup;
	private Date igCrtdDt;

	// Constructors

	/** default constructor */
	public IpIdeaGroup() {
	}

	/** minimal constructor */
	public IpIdeaGroup(Long igId, Date igCrtdDt) {
		this.igId = igId;
		this.igCrtdDt = igCrtdDt;
	}

	/** full constructor */
	public IpIdeaGroup(Long igId, IpIdea ipIdea, IpGroup ipGroup,
			Date igCrtdDt) {
		this.igId = igId;
		this.ipIdea = ipIdea;
		this.ipGroup = ipGroup;
		this.igCrtdDt = igCrtdDt;
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

	public Date getIgCrtdDt() {
		return this.igCrtdDt;
	}

	public void setIgCrtdDt(Date igCrtdDt) {
		this.igCrtdDt = igCrtdDt;
	}

}