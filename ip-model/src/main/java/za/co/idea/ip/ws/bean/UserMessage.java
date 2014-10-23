package za.co.idea.ip.ws.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "userMessage")
public class UserMessage {
	private Long uId;
	private String fName;
	private String mName;
	private String lName;
	private Long idNum;
	private String eMail;
	private String contact;
	private String skills;
	private String bio;
	private String fbHandle;
	private String twHandle;
	private String scName;
	private String pwd;
	private Integer secQ;
	private String secA;
	private Boolean isActive;
	private Date lastLoginDt;
	private String employeeId;
	private Long groupId;
	private String priGroupName;
	private String imgPath;
	private boolean imgAvail;
	private String grpImgPath;
	private boolean grpImgAvail;
	private String blobUrl;
	private Date uCrtdDate;

	public Long getuId() {
		return uId;
	}

	public void setuId(Long uId) {
		this.uId = uId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public Long getIdNum() {
		return idNum;
	}

	public void setIdNum(Long idNum) {
		this.idNum = idNum;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getFbHandle() {
		return fbHandle;
	}

	public void setFbHandle(String fbHandle) {
		this.fbHandle = fbHandle;
	}

	public String getTwHandle() {
		return twHandle;
	}

	public void setTwHandle(String twHandle) {
		this.twHandle = twHandle;
	}

	public String getScName() {
		return scName;
	}

	public void setScName(String scName) {
		this.scName = scName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Date getLastLoginDt() {
		return lastLoginDt;
	}

	public void setLastLoginDt(Date lastLoginDt) {
		this.lastLoginDt = lastLoginDt;
	}

	public Integer getSecQ() {
		return secQ;
	}

	public void setSecQ(Integer secQ) {
		this.secQ = secQ;
	}

	public String getSecA() {
		return secA;
	}

	public void setSecA(String secA) {
		this.secA = secA;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getPriGroupName() {
		return priGroupName;
	}

	public void setPriGroupName(String priGroupName) {
		this.priGroupName = priGroupName;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public boolean isImgAvail() {
		return imgAvail;
	}

	public void setImgAvail(boolean imgAvail) {
		this.imgAvail = imgAvail;
	}

	public String getGrpImgPath() {
		return grpImgPath;
	}

	public void setGrpImgPath(String grpImgPath) {
		this.grpImgPath = grpImgPath;
	}

	public boolean isGrpImgAvail() {
		return grpImgAvail;
	}

	public void setGrpImgAvail(boolean grpImgAvail) {
		this.grpImgAvail = grpImgAvail;
	}

	public String getBlobUrl() {
		return blobUrl;
	}

	public void setBlobUrl(String blobUrl) {
		this.blobUrl = blobUrl;
	}

	public Date getuCrtdDate() {
		return uCrtdDate;
	}

	public void setuCrtdDate(Date uCrtdDate) {
		this.uCrtdDate = uCrtdDate;
	}

}