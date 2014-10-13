package za.co.idea.ip.ws.bean.report;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "reportQueryMessage")
public class ReportQueryMessage {
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
