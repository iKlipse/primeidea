package za.co.idea.ip.ws.bean.report;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rewardsReportMessage")
public class RewardsReportMessage {
	private String fName;
	private String lName;
	private String group;
	private String rDesc;
	private String clmDt;
	private String clmStatus;

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getrDesc() {
		return rDesc;
	}

	public void setrDesc(String rDesc) {
		this.rDesc = rDesc;
	}

	public String getClmDt() {
		return clmDt;
	}

	public void setClmDt(String clmDt) {
		this.clmDt = clmDt;
	}

	public String getClmStatus() {
		return clmStatus;
	}

	public void setClmStatus(String clmStatus) {
		this.clmStatus = clmStatus;
	}
}
