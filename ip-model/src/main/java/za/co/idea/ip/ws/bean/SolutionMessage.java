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

}
