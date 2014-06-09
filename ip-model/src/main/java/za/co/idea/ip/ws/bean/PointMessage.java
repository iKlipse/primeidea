package za.co.idea.ip.ws.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pointMessage")
public class PointMessage {
	private long userId;
	private int allocId;
	private int pointValue;
	private long pointId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getAllocId() {
		return allocId;
	}

	public void setAllocId(int allocId) {
		this.allocId = allocId;
	}

	public int getPointValue() {
		return pointValue;
	}

	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}

	public long getPointId() {
		return pointId;
	}

	public void setPointId(long pointId) {
		this.pointId = pointId;
	}
}
