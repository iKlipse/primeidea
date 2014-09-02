package za.co.idea.ip.ws.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "solutionMessage")
public class SolutionMessage {
	private Long id;
	private Long chalId;
	private String title;
	private String desc;
	private Integer catId;
	private Integer statusId;
	private String tags;
	private Date crtdDt;
	private Long crtdById;
	private String crtByName;
	private boolean crtByImgAvail;
	private String crtByImgPath;
	private boolean solImgAvl;
	private String solImg;
	private String contentType;
	private String fileName;
	private Long fileSize;
	private String blobUrl;
	private Long revUserId;
	private String catName;
	private String statusName;
	private Integer rvIdCnt;

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

	public String getCrtByName() {
		return crtByName;
	}

	public void setCrtByName(String crtByName) {
		this.crtByName = crtByName;
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

	public boolean isSolImgAvl() {
		return solImgAvl;
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

	public Integer getRvIdCnt() {
		return rvIdCnt;
	}

	public void setRvIdCnt(Integer rvIdCnt) {
		this.rvIdCnt = rvIdCnt;
	}

}
