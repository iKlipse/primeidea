package za.co.idea.ip.orm.bean;

import java.util.Date;

/**
 * IpTag entity. @author MyEclipse Persistence Tools
 */

public class IpTag implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 961833067697641035L;
	private Long tagId;
	private IpUser ipUser;
	private IpTagEntityType ipTagEntityType;
	private IpTagType ipTagType;
	private Long tagEntityId;
	private String tagText;
	private Date tagCrtdDt;

	// Constructors

	/** default constructor */
	public IpTag() {
	}

	/** minimal constructor */
	public IpTag(Long tagId, Date tagCrtdDt) {
		this.tagId = tagId;
		this.tagCrtdDt = tagCrtdDt;
	}

	/** full constructor */
	public IpTag(Long tagId, IpUser ipUser, IpTagEntityType ipTagEntityType, IpTagType ipTagType, Long tagEntityId, String tagText, Date tagCrtdDt) {
		this.tagId = tagId;
		this.ipUser = ipUser;
		this.ipTagEntityType = ipTagEntityType;
		this.ipTagType = ipTagType;
		this.tagEntityId = tagEntityId;
		this.tagText = tagText;
		this.tagCrtdDt = tagCrtdDt;
	}

	// Property accessors

	public Long getTagId() {
		return this.tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public IpUser getIpUser() {
		return this.ipUser;
	}

	public void setIpUser(IpUser ipUser) {
		this.ipUser = ipUser;
	}

	public IpTagEntityType getIpTagEntityType() {
		return this.ipTagEntityType;
	}

	public void setIpTagEntityType(IpTagEntityType ipTagEntityType) {
		this.ipTagEntityType = ipTagEntityType;
	}

	public IpTagType getIpTagType() {
		return this.ipTagType;
	}

	public void setIpTagType(IpTagType ipTagType) {
		this.ipTagType = ipTagType;
	}

	public Long getTagEntityId() {
		return this.tagEntityId;
	}

	public void setTagEntityId(Long tagEntityId) {
		this.tagEntityId = tagEntityId;
	}

	public String getTagText() {
		return this.tagText;
	}

	public void setTagText(String tagText) {
		this.tagText = tagText;
	}

	public Date getTagCrtdDt() {
		return this.tagCrtdDt;
	}

	public void setTagCrtdDt(Date tagCrtdDt) {
		this.tagCrtdDt = tagCrtdDt;
	}

}