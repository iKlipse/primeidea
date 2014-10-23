package za.co.idea.ip.portal.bean;

import java.io.Serializable;

public class MetaDataBean implements Serializable {

	private static final long serialVersionUID = 4574782901477480432L;
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
