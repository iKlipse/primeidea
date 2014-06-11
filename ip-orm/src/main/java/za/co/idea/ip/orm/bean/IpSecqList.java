package za.co.idea.ip.orm.bean;

/**
 * IpSecqList entity. @author MyEclipse Persistence Tools
 */

public class IpSecqList implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 4017972177397865428L;
	private Integer islId;
	private String islDesc;

	// Constructors

	/** default constructor */
	public IpSecqList() {
	}

	/** full constructor */
	public IpSecqList(Integer islId, String islDesc) {
		this.islId = islId;
		this.islDesc = islDesc;
	}

	// Property accessors

	public Integer getIslId() {
		return this.islId;
	}

	public void setIslId(Integer islId) {
		this.islId = islId;
	}

	public String getIslDesc() {
		return this.islDesc;
	}

	public void setIslDesc(String islDesc) {
		this.islDesc = islDesc;
	}

}