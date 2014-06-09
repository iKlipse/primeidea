package za.co.idea.ip.orm.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * IpClaimStatus entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("rawtypes")
public class IpClaimStatus implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5951907640114110917L;
	private Integer csId;
	private String csDesc;
	private Set ipClaims = new HashSet(0);

	// Constructors

	/** default constructor */
	public IpClaimStatus() {
	}

	/** minimal constructor */
	public IpClaimStatus(Integer csId) {
		this.csId = csId;
	}

	/** full constructor */
	public IpClaimStatus(Integer csId, String csDesc, Set ipClaims) {
		this.csId = csId;
		this.csDesc = csDesc;
		this.ipClaims = ipClaims;
	}

	// Property accessors

	public Integer getCsId() {
		return this.csId;
	}

	public void setCsId(Integer csId) {
		this.csId = csId;
	}

	public String getCsDesc() {
		return this.csDesc;
	}

	public void setCsDesc(String csDesc) {
		this.csDesc = csDesc;
	}

	public Set getIpClaims() {
		return this.ipClaims;
	}

	public void setIpClaims(Set ipClaims) {
		this.ipClaims = ipClaims;
	}

}