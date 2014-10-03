package za.co.idea.ip.portal.bean;

import java.io.Serializable;
import java.util.ArrayList;
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
	private Long crtdById;
	private String crtdByName;
	private boolean crtByImgAvail;
	private String crtByImgPath;
	private Date crtdDate;
	private List<Long> groupIdList;
	private boolean taggable;
	private Long revUserId;
	private String statusName;
	private boolean disableEdit;
	private boolean disableField;
	private List<FileBean> files;
	private boolean attachsAvail;

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

	public boolean isDisableEdit() {
		return disableEdit;
	}

	public void setDisableEdit(boolean disableEdit) {
		this.disableEdit = disableEdit;
	}

	public boolean isDisableField() {
		if (setStatusId > 2)
			disableField = true;
		else
			disableField = false;
		return disableField;
	}

	public void setDisableField(boolean disableField) {
		this.disableField = disableField;
	}

	public List<FileBean> getFiles() {
		if (files == null)
			files = new ArrayList<FileBean>();
		return files;
	}

	public void setFiles(List<FileBean> files) {
		this.files = files;
	}

	public boolean isAttachsAvail() {
		return attachsAvail;
	}

	public void setAttachsAvail(boolean attachsAvail) {
		this.attachsAvail = attachsAvail;
	}
}
