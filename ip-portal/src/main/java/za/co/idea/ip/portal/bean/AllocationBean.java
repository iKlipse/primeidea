package za.co.idea.ip.portal.bean;

import java.io.Serializable;

public class AllocationBean implements Serializable {

	private static final long serialVersionUID = -660908810088957902L;
	private Integer allocId;
	private String allocDesc;
	private Integer allocVal;
	private String allocEntity;
	private Integer allocStatusId;

	public Integer getAllocId() {
		return allocId;
	}

	public String getAllocDesc() {
		return allocDesc;
	}

	public Integer getAllocVal() {
		return allocVal;
	}

	public String getAllocEntity() {
		return allocEntity;
	}

	public Integer getAllocStatusId() {
		return allocStatusId;
	}

	public void setAllocId(Integer allocId) {
		this.allocId = allocId;
	}

	public void setAllocDesc(String allocDesc) {
		this.allocDesc = allocDesc;
	}

	public void setAllocVal(Integer allocVal) {
		this.allocVal = allocVal;
	}

	public void setAllocEntity(String allocEntity) {
		this.allocEntity = allocEntity;
	}

	public void setAllocStatusId(Integer allocStatusId) {
		this.allocStatusId = allocStatusId;
	}

}
