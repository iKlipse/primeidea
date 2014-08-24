package za.co.idea.ip.portal.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class IdeaBean implements Serializable {

	private static final long serialVersionUID = -8721231413352705770L;

	private Long ideaId;
	private String ideaTitle;
	private String ideaDesc;
	private Long selCatId;
	private Long setStatusId;
	private String ideaBa;
	private String ideaTag;
	private String fileUpload;
	private String contentType;
	private String fileName;
	private boolean imgAvail;
	private Long fileSize;
	private Long crtdById;
	private String crtdByName;
	private boolean crtByImgAvail;
	private String crtByImgPath;
	private Date crtdDate;
	private List<Long> groupIdList;
	private boolean taggable;
	private String blobUrl;
	private Long revUserId;
	private String statusName;

	public Long getIdeaId() {
		return ideaId;
	}

	public void setIdeaId(Long ideaId) {
		this.ideaId = ideaId;
	}

	public String getIdeaTitle() {
		return ideaTitle;
	}

	public void setIdeaTitle(String ideaTitle) {
		this.ideaTitle = ideaTitle;
	}

	public String getIdeaDesc() {
		return ideaDesc;
	}

	public void setIdeaDesc(String ideaDesc) {
		this.ideaDesc = ideaDesc;
	}

	public Long getSelCatId() {
		return selCatId;
	}

	public void setSelCatId(Long selCatId) {
		this.selCatId = selCatId;
	}

	public Long getSetStatusId() {
		return setStatusId;
	}

	public void setSetStatusId(Long setStatusId) {
		this.setStatusId = setStatusId;
	}

	public String getIdeaBa() {
		return ideaBa;
	}

	public void setIdeaBa(String ideaBa) {
		this.ideaBa = ideaBa;
	}

	public String getIdeaTag() {
		return ideaTag;
	}

	public void setIdeaTag(String ideaTag) {
		this.ideaTag = ideaTag;
	}

	public String getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(String fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isImgAvail() {
		return imgAvail;
	}

	public void setImgAvail(boolean imgAvail) {
		this.imgAvail = imgAvail;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public Long getCrtdById() {
		return crtdById;
	}

	public void setCrtdById(Long crtdById) {
		this.crtdById = crtdById;
	}

	public String getCrtdByName() {
		return crtdByName;
	}

	public void setCrtdByName(String crtdByName) {
		this.crtdByName = crtdByName;
	}

	public boolean isCrtByImgAvail() {
		return crtByImgAvail;
	}

	public void setCrtByImgAvail(boolean crtByImgAvail) {
		this.crtByImgAvail = crtByImgAvail;
	}

	public String getCrtByImgPath() {
		return crtByImgPath;
	}

	public void setCrtByImgPath(String crtByImgPath) {
		this.crtByImgPath = crtByImgPath;
	}

	public Date getCrtdDate() {
		return crtdDate;
	}

	public void setCrtdDate(Date crtdDate) {
		this.crtdDate = crtdDate;
	}

	public List<Long> getGroupIdList() {
		return groupIdList;
	}

	public void setGroupIdList(List<Long> groupIdList) {
		this.groupIdList = groupIdList;
	}

	public boolean isTaggable() {
		return taggable;
	}

	public void setTaggable(boolean taggable) {
		this.taggable = taggable;
	}

	public String getBlobUrl() {
		return blobUrl;
	}

	public void setBlobUrl(String blobUrl) {
		this.blobUrl = blobUrl;
	}

	public Long getRevUserId() {
		return revUserId;
	}

	public void setRevUserId(Long revUserId) {
		this.revUserId = revUserId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
