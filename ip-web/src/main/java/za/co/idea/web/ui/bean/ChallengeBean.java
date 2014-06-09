package za.co.idea.web.ui.bean;

import java.io.Serializable;
import java.util.Date;

public class ChallengeBean implements Serializable {

	private static final long serialVersionUID = -6822465162763556812L;
	private Long id;
	private String title;
	private String desc;
	private String hoverText;
	private Integer catId;
	private Integer statusId;
	private Date launchDt;
	private Date exprDt;
	private String tag;
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

	public String getHoverText() {
		return hoverText;
	}

	public void setHoverText(String hoverText) {
		this.hoverText = hoverText;
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

	public Date getLaunchDt() {
		return launchDt;
	}

	public void setLaunchDt(Date launchDt) {
		this.launchDt = launchDt;
	}

	public Date getExprDt() {
		return exprDt;
	}

	public void setExprDt(Date exprDt) {
		this.exprDt = exprDt;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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
