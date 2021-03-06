package za.co.idea.ip.ws.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "userStatisticsMessage")
public class UserStatisticsMessage implements Comparable<UserStatisticsMessage> {

	private Long userId;
	private Long challengesCount;
	private Long solutionsCount;
	private Long whishListCount;
	private Long ideasCount;
	private Long totalCount;
	private Long pointsCount;
	private String imgPath;
	private boolean imgAvail;
	private String grpImgPath;
	private boolean grpImgAvail;
	private String userScrNm;
	private String userPriGrpName;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getChallengesCount() {
		return challengesCount;
	}

	public void setChallengesCount(Long challengesCount) {
		this.challengesCount = challengesCount;
	}

	public Long getSolutionsCount() {
		return solutionsCount;
	}

	public void setSolutionsCount(Long solutionsCount) {
		this.solutionsCount = solutionsCount;
	}

	public Long getWhishListCount() {
		return whishListCount;
	}

	public void setWhishListCount(Long whishListCount) {
		this.whishListCount = whishListCount;
	}

	public Long getIdeasCount() {
		return ideasCount;
	}

	public void setIdeasCount(Long ideasCount) {
		this.ideasCount = ideasCount;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
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

	public int compareTo(UserStatisticsMessage o) {
		if (this.getTotalCount() > o.getTotalCount())
			return -1;
		else if (this.getTotalCount() < o.getTotalCount())
			return 1;
		else
			return 0;
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

	public String getUserScrNm() {
		return userScrNm;
	}

	public void setUserScrNm(String userScrNm) {
		this.userScrNm = userScrNm;
	}

	public String getUserPriGrpName() {
		return userPriGrpName;
	}

	public void setUserPriGrpName(String userPriGrpName) {
		this.userPriGrpName = userPriGrpName;
	}

	public Long getPointsCount() {
		return pointsCount;
	}

	public void setPointsCount(Long pointsCount) {
		this.pointsCount = pointsCount;
	}

}
