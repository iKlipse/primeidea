package za.co.idea.ip.portal.bean;

public class PointBean {
	private Long userId;
	private Integer allocId;
	private Integer pointValue;
	private Long pointId;
	private String comments;
	private Long entityId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getAllocId() {
		return allocId;
	}

	public void setAllocId(Integer allocId) {
		this.allocId = allocId;
	}

	public Integer getPointValue() {
		return pointValue;
	}

	public void setPointValue(Integer pointValue) {
		this.pointValue = pointValue;
	}

	public Long getPointId() {
		return pointId;
	}

	public void setPointId(Long pointId) {
		this.pointId = pointId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

}
