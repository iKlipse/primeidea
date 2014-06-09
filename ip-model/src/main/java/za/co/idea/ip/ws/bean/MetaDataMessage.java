package za.co.idea.ip.ws.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "metaDataMessage")
public class MetaDataMessage {

	private Integer id;
	private String desc;
	private String table;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

}
