package za.co.idea.web.ui.bean;

import java.io.Serializable;
import java.util.Date;

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
	private String contentType;
	private String fileName;

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

}
