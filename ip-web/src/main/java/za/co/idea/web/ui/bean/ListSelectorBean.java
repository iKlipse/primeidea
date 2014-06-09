package za.co.idea.web.ui.bean;

import java.io.Serializable;

public class ListSelectorBean implements Serializable {

	private static final long serialVersionUID = -753190990005408777L;

	private Integer id;
	private String desc;

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

}
