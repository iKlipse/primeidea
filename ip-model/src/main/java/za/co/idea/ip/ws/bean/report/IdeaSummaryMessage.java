package za.co.idea.ip.ws.bean.report;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ideaSummaryMessage")
public class IdeaSummaryMessage {
	private String groupName;
	private Long cnta;
	private Long cntb;
	private Long cntc;
	private Long cntd;

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

	public Long getCntc() {
		return cntc;
	}

	public void setCntc(Long cntc) {
		this.cntc = cntc;
	}

	public Long getCntd() {
		return cntd;
	}

	public void setCntd(Long cntd) {
		this.cntd = cntd;
	}

}
