package za.co.idea.ip.orm.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * IpSecqList entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("rawtypes")
public class IpSecqList implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 9152255946581695278L;
	private Integer islId;
	private String islDesc;
	private Set ipLogins = new HashSet(0);

	// Constructors

	/** default constructor */
	public IpSecqList() {
	}

	/** minimal constructor */
	public IpSecqList(Integer islId, String islDesc) {
		this.islId = islId;
		this.islDesc = islDesc;
	}

	/** full constructor */
	public IpSecqList(Integer islId, String islDesc, Set ipLogins) {
		this.islId = islId;
		this.islDesc = islDesc;
		this.ipLogins = ipLogins;
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

	public Set getIpLogins() {
		return this.ipLogins;
	}

	public void setIpLogins(Set ipLogins) {
		this.ipLogins = ipLogins;
	}

}