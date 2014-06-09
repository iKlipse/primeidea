package za.co.idea.ip.orm.bean;

import java.sql.Timestamp;

/**
 * IpTag entity. @author MyEclipse Persistence Tools
 */

public class IpTag implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7062420850700464640L;
	private Long tagId;
	private IpUser ipUser;
	private IpTagEntityType ipTagEntityType;
	private IpTagType ipTagType;
	private Long tagEntityId;
	private String tagText;
	private Timestamp tagDate;

	// Constructors

	/** default constructor */
	public IpTag() {
	}

	/** minimal constructor */
	public IpTag(Long tagId, IpTagEntityType ipTagEntityType, IpTagType ipTagType, Long tagEntityId) {
		this.tagId = tagId;
		this.ipTagEntityType = ipTagEntityType;
		this.ipTagType = ipTagType;
		this.tagEntityId = tagEntityId;
	}

	/** full constructor */
	public IpTag(Long tagId, IpUser ipUser, IpTagEntityType ipTagEntityType, IpTagType ipTagType, Long tagEntityId, String tagText, Timestamp tagDate) {
		this.tagId = tagId;
		this.ipUser = ipUser;
		this.ipTagEntityType = ipTagEntityType;
		this.ipTagType = ipTagType;
		this.tagEntityId = tagEntityId;
		this.tagText = tagText;
		this.tagDate = tagDate;
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

	public Timestamp getTagDate() {
		return tagDate;
	}

	public void setTagDate(Timestamp tagDate) {
		this.tagDate = tagDate;
	}

}