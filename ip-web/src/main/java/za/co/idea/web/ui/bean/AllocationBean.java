package za.co.idea.web.ui.bean;

public class AllocationBean {
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
