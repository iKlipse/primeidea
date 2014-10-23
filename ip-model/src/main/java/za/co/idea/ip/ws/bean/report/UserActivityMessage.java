package za.co.idea.ip.ws.bean.report;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "userActivityMessage")
public class UserActivityMessage {
	private String userFName;
	private String userLName;
	private String groupName;
	private Timestamp dta;
	private Timestamp dtb;
	private Long cntb;
	private BigDecimal cntc;

	public String getUserFName() {
		return userFName;
	}

	public void setUserFName(String userFName) {
		this.userFName = userFName;
	}

	public String getUserLName() {
		return userLName;
	}

	public void setUserLName(String userLName) {
		this.userLName = userLName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Timestamp getDta() {
		return dta;
	}

	public void setDta(Timestamp dta) {
		this.dta = dta;
	}

	public Timestamp getDtb() {
		return dtb;
	}

	public void setDtb(Timestamp dtb) {
		this.dtb = dtb;
	}

	public Long getCntb() {
		return cntb;
	}

	public void setCntb(Long cntb) {
		this.cntb = cntb;
	}

	public BigDecimal getCntc() {
		return cntc;
	}

	public void setCntc(BigDecimal cntc) {
		this.cntc = cntc;
	}

}
