package za.co.idea.ip.portal.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.primefaces.model.StreamedContent;

public class SolutionBean implements Serializable {

	private static final long serialVersionUID = 5175633384603270214L;
	private Long id;
	private Long chalId;
	private String title;
	private String desc;
	private Integer catId;
	private Integer statusId;
	private String tags;
	private String blob;
	private Date crtdDt;
	private Long crtdById;
	private boolean crtByImgAvail;
	private String crtByImgPath;
	private String contentType;
	private String fileName;
	private Long fileSize;
	private String crtByName;
	private boolean taggable;
	private boolean solImgAvl;
	private String solImg;
	private StreamedContent solStream;
	private String solLikeCnt;
	private String solCommentCnt;
	private String buildOnCnt;
	private List<TagBean> solComments;
	private String blobUrl;
	private Long revUserId;
	private String catName;
	private String statusName;
	private boolean disableEdit;
	private boolean disableField;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getChalId() {
		return chalId;
	}

	public void setChalId(Long chalId) {
		this.chalId = chalId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getBlob() {
		return blob;
	}

	public void setBlob(String blob) {
		this.blob = blob;
	}

	public Date getCrtdDt() {
		return crtdDt;
	}

	public void setCrtdDt(Date crtdDt) {
		this.crtdDt = crtdDt;
	}

	public Long getCrtdById() {
		return crtdById;
	}

	public void setCrtdById(Long crtdById) {
		this.crtdById = crtdById;
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

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getCrtByName() {
		return crtByName;
	}

	public void setCrtByName(String crtByName) {
		this.crtByName = crtByName;
	}

	public boolean isTaggable() {
		return taggable;
	}

	public void setTaggable(boolean taggable) {
		this.taggable = taggable;
	}

	public boolean isSolImgAvl() {
		return solImgAvl;
	}

	public String getSolLikeCnt() {
		return solLikeCnt;
	}

	public void setSolLikeCnt(String solLikeCnt) {
		this.solLikeCnt = solLikeCnt;
	}

	public String getSolCommentCnt() {
		return solCommentCnt;
	}

	public void setSolCommentCnt(String solCommentCnt) {
		this.solCommentCnt = solCommentCnt;
	}

	public void setSolImgAvl(boolean solImgAvl) {
		this.solImgAvl = solImgAvl;
	}

	public String getSolImg() {
		return solImg;
	}

	public void setSolImg(String solImg) {
		this.solImg = solImg;
	}

	public StreamedContent getSolStream() {
		return solStream;
	}

	public void setSolStream(StreamedContent solStream) {
		this.solStream = solStream;
	}

	public String getBuildOnCnt() {
		return buildOnCnt;
	}

	public void setBuildOnCnt(String buildOnCnt) {
		this.buildOnCnt = buildOnCnt;
	}

	public List<TagBean> getSolComments() {
		return solComments;
	}

	public void setSolComments(List<TagBean> solComments) {
		this.solComments = solComments;
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

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public boolean isDisableEdit() {
		return disableEdit;
	}

	public void setDisableEdit(boolean disableEdit) {
		this.disableEdit = disableEdit;
	}

	public boolean isDisableField() {
		if (statusId > 2)
			disableField = true;
		else
			disableField = false;
		return disableField;
	}

	public void setDisableField(boolean disableField) {
		this.disableField = disableField;
	}
}
