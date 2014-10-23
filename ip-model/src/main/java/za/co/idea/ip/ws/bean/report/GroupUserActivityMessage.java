package za.co.idea.ip.ws.bean.report;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "groupUserActivityMessage")
public class GroupUserActivityMessage {
	private String groupName;
	private Long cnta;
	private Long cntb;
	private BigDecimal cntc;
	private Long totcnt;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Long getCnta() {
		return cnta;
	}

	public void setCnta(Long cnta) {
		this.cnta = cnta;
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

	public Long getTotcnt() {
		return totcnt;
	}

	public void setTotcnt(Long totcnt) {
		this.totcnt = totcnt;
	}

}
