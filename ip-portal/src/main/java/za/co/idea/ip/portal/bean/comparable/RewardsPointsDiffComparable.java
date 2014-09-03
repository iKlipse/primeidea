package za.co.idea.ip.portal.bean.comparable;

import za.co.idea.ip.portal.bean.RewardsBean;

public class RewardsPointsDiffComparable extends RewardsBean implements Comparable<RewardsPointsDiffComparable> {

	private static final long serialVersionUID = 1214438004800690831L;
	private int pointsDiff;

	public int getPointsDiff() {
		return pointsDiff;
	}

	public void setPointsDiff(int pointsDiff) {
		this.pointsDiff = pointsDiff;
	}

	public int compareTo(RewardsPointsDiffComparable o) {
		if (this.getPointsDiff() < o.getPointsDiff())
			return -1;
		else if (this.getPointsDiff() > o.getPointsDiff())
			return 1;
		else
			return 0;
	}
}
