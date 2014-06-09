package za.co.idea.ip.orm.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * IpRewardsCat entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("rawtypes")
public class IpRewardsCat implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3698376466771933497L;
	private Integer rcId;
	private String rcDesc;
	private Set ipRewardses = new HashSet(0);

	// Constructors

	/** default constructor */
	public IpRewardsCat() {
	}

	/** minimal constructor */
	public IpRewardsCat(Integer rcId, String rcDesc) {
		this.rcId = rcId;
		this.rcDesc = rcDesc;
	}

	/** full constructor */
	public IpRewardsCat(Integer rcId, String rcDesc, Set ipRewardses) {
		this.rcId = rcId;
		this.rcDesc = rcDesc;
		this.ipRewardses = ipRewardses;
	}

	// Property accessors

	public Integer getRcId() {
		return this.rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}

	public String getRcDesc() {
		return this.rcDesc;
	}

	public void setRcDesc(String rcDesc) {
		this.rcDesc = rcDesc;
	}

	public Set getIpRewardses() {
		return this.ipRewardses;
	}

	public void setIpRewardses(Set ipRewardses) {
		this.ipRewardses = ipRewardses;
	}

}