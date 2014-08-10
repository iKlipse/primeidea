package za.co.idea.ip.ws.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "userStatisticsMessage")
public class UserStatisticsMessage {
	
	private Long userId;
	private Long challengesCount;
	private Long solutionsCount;
	private Long whishListCount;
	private Long ideasCount;
	private Long totalCount;
	
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
	
	
	

}
