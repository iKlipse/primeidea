package za.co.idea.ip.portal.bean;

import java.io.Serializable;

public class UserStatisticsBean implements Serializable {

	private static final long serialVersionUID = 6021071377594408019L;
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
