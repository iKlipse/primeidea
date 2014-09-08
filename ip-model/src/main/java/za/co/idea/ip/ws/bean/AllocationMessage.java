package za.co.idea.ip.ws.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "allocationMessage")
public class AllocationMessage {
	private Integer allocId;
	private String allocDesc;
	private Integer allocVal;
	private String allocEntity;
	private Integer allocStatusId;
	private Date allocCrtdDt;

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

	public Date getAllocCrtdDt() {
		return allocCrtdDt;
	}

	public void setAllocCrtdDt(Date allocCrtdDt) {
		this.allocCrtdDt = allocCrtdDt;
	}

}
