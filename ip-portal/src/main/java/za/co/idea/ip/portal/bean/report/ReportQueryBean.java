package za.co.idea.ip.portal.bean.report;

import java.io.Serializable;
import java.util.Date;

public class ReportQueryBean implements Serializable {

	private static final long serialVersionUID = -6585380502930909325L;
	private Long grpId;
	private Long usrId;
	private Date startDate;
	private Date endDate;

	public Long getGrpId() {
		return grpId;
	}

	public void setGrpId(Long grpId) {
		this.grpId = grpId;
	}

	public Long getUsrId() {
		return usrId;
	}

	public void setUsrId(Long usrId) {
		this.usrId = usrId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
