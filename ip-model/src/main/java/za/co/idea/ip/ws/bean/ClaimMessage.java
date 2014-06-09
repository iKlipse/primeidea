package za.co.idea.ip.ws.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "claimMessage")
public class ClaimMessage {
	private Long claimId;
	private Long userId;
	private Integer cStatusId;
	private Long rewardsId;
	private String claimDesc;
	private Date claimCrtdDt;

	public Long getClaimId() {
		return claimId;
	}

	public void setClaimId(Long claimId) {
		this.claimId = claimId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getcStatusId() {
		return cStatusId;
	}

	public void setcStatusId(Integer cStatusId) {
		this.cStatusId = cStatusId;
	}

	public Long getRewardsId() {
		return rewardsId;
	}

	public void setRewardsId(Long rewardsId) {
		this.rewardsId = rewardsId;
	}

	public String getClaimDesc() {
		return claimDesc;
	}

	public void setClaimDesc(String claimDesc) {
		this.claimDesc = claimDesc;
	}

	public Date getClaimCrtdDt() {
		return claimCrtdDt;
	}

	public void setClaimCrtdDt(Date claimCrtdDt) {
		this.claimCrtdDt = claimCrtdDt;
	}

}
