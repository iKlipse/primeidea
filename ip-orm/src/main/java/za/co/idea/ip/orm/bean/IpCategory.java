package za.co.idea.ip.orm.bean;

import java.io.Serializable;

public class IpCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7952105848212129374L;
	private Integer categoryId;
	private String categoryDesc;

	/** default constructor */
	public IpCategory() {
	}

	/** minimal constructor */
	public IpCategory(Integer categoryId, String categoryDesc) {
		this.categoryId = categoryId;
		this.categoryDesc = categoryDesc;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

}
