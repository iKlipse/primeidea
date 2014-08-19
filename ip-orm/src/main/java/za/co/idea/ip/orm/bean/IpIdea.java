package za.co.idea.ip.orm.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * IpIdea entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("rawtypes")
public class IpIdea implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7941230595289352567L;
	private Long ideaId;
	private IpIdeaStatus ipIdeaStatus;
	private IpUser ipUser;
	private IpIdeaCat ipIdeaCat;
	private String ideaTitle;
	private String ideaDesc;
	private String ideaBa;
	private Date ideaDate;
	private String ideaTag;
	private Set ipIdeaGroups = new HashSet(0);

	// Constructors

	/** default constructor */
	public IpIdea() {
	}

	/** minimal constructor */
	public IpIdea(Long ideaId, IpIdeaStatus ipIdeaStatus, IpUser ipUser, String ideaTitle, String ideaDesc, Date ideaDate, String ideaTag) {
		this.ideaId = ideaId;
		this.ipIdeaStatus = ipIdeaStatus;
		this.ipUser = ipUser;
		this.ideaTitle = ideaTitle;
		this.ideaDesc = ideaDesc;
		this.ideaDate = ideaDate;
		this.ideaTag = ideaTag;
	}

	/** full constructor */
	public IpIdea(Long ideaId, IpIdeaStatus ipIdeaStatus, IpUser ipUser, IpIdeaCat ipIdeaCat, String ideaTitle, String ideaDesc, String ideaBa, Date ideaDate, String ideaTag, Set ipIdeaGroups) {
		this.ideaId = ideaId;
		this.ipIdeaStatus = ipIdeaStatus;
		this.ipUser = ipUser;
		this.ipIdeaCat = ipIdeaCat;
		this.ideaTitle = ideaTitle;
		this.ideaDesc = ideaDesc;
		this.ideaBa = ideaBa;
		this.ideaDate = ideaDate;
		this.ideaTag = ideaTag;
		this.ipIdeaGroups = ipIdeaGroups;
	}

	// Property accessors

	public Long getIdeaId() {
		return this.ideaId;
	}

	public void setIdeaId(Long ideaId) {
		this.ideaId = ideaId;
	}

	public IpIdeaStatus getIpIdeaStatus() {
		return this.ipIdeaStatus;
	}

	public void setIpIdeaStatus(IpIdeaStatus ipIdeaStatus) {
		this.ipIdeaStatus = ipIdeaStatus;
	}

	public IpUser getIpUser() {
		return this.ipUser;
	}

	public void setIpUser(IpUser ipUser) {
		this.ipUser = ipUser;
	}

	public IpIdeaCat getIpIdeaCat() {
		return this.ipIdeaCat;
	}

	public void setIpIdeaCat(IpIdeaCat ipIdeaCat) {
		this.ipIdeaCat = ipIdeaCat;
	}

	public String getIdeaTitle() {
		return this.ideaTitle;
	}

	public void setIdeaTitle(String ideaTitle) {
		this.ideaTitle = ideaTitle;
	}

	public String getIdeaDesc() {
		return this.ideaDesc;
	}

	public void setIdeaDesc(String ideaDesc) {
		this.ideaDesc = ideaDesc;
	}

	public String getIdeaBa() {
		return this.ideaBa;
	}

	public void setIdeaBa(String ideaBa) {
		this.ideaBa = ideaBa;
	}

	public Date getIdeaDate() {
		return this.ideaDate;
	}

	public void setIdeaDate(Date ideaDate) {
		this.ideaDate = ideaDate;
	}

	public String getIdeaTag() {
		return this.ideaTag;
	}

	public void setIdeaTag(String ideaTag) {
		this.ideaTag = ideaTag;
	}

	public Set getIpIdeaGroups() {
		return this.ipIdeaGroups;
	}

	public void setIpIdeaGroups(Set ipIdeaGroups) {
		this.ipIdeaGroups = ipIdeaGroups;
	}

}