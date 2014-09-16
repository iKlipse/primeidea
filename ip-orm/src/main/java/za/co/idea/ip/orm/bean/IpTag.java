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

/**
 * IpTag entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_tag", catalog = "lpdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "getTagByFilterA", query = "select tag.* from ip_tag tag where tag.tag_type=:ttId and tag.tag_entity_type=:teId and tag.tag_entity_id=:entityId", resultClass = IpTag.class), @NamedNativeQuery(name = "getTagByFilterB", query = "select tag.* from ip_tag tag where tag.tag_type=:ttId and tag.tag_entity_type=:teId and tag.tag_entity_id=:entityId and tag.tag_user_id=:userId", resultClass = IpTag.class) })
public class IpTag implements java.io.Serializable {

	// Fields

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
	@Id
	@Column(name = "tag_id", unique = true, nullable = false)
	public Long getTagId() {
		return this.tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tag_user_id")
	public IpUser getIpUser() {
		return this.ipUser;
	}

	public void setIpUser(IpUser ipUser) {
		this.ipUser = ipUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tag_entity_type")
	public IpTagEntityType getIpTagEntityType() {
		return this.ipTagEntityType;
	}

	public void setIpTagEntityType(IpTagEntityType ipTagEntityType) {
		this.ipTagEntityType = ipTagEntityType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tag_type")
	public IpTagType getIpTagType() {
		return this.ipTagType;
	}

	public void setIpTagType(IpTagType ipTagType) {
		this.ipTagType = ipTagType;
	}

	@Column(name = "tag_entity_id")
	public Long getTagEntityId() {
		return this.tagEntityId;
	}

	public void setTagEntityId(Long tagEntityId) {
		this.tagEntityId = tagEntityId;
	}

	@Column(name = "tag_text", length = 65535)
	public String getTagText() {
		return this.tagText;
	}

	public void setTagText(String tagText) {
		this.tagText = tagText;
	}

	@Column(name = "tag_crtd_dt", nullable = false, length = 19)
	public Date getTagCrtdDt() {
		return this.tagCrtdDt;
	}

	public void setTagCrtdDt(Date tagCrtdDt) {
		this.tagCrtdDt = tagCrtdDt;
	}

}