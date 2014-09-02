package za.co.idea.ip.orm.bean;

import java.util.Date;

/**
 * IpFuncGroup entity. @author MyEclipse Persistence Tools
 */

public class IpFuncGroup implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3247235635196806866L;
	private Long fgId;
	private IpGroup ipGroup;
	private IpFunction ipFunction;
	private Date fgCrtdDt;

	// Constructors

	/** default constructor */
	public IpFuncGroup() {
	}

	/** minimal constructor */
	public IpFuncGroup(Long fgId, IpGroup ipGroup, Date fgCrtdDt) {
		this.fgId = fgId;
		this.ipGroup = ipGroup;
		this.fgCrtdDt = fgCrtdDt;
	}

	/** full constructor */
	public IpFuncGroup(Long fgId, IpGroup ipGroup, IpFunction ipFunction,
			Date fgCrtdDt) {
		this.fgId = fgId;
		this.ipGroup = ipGroup;
		this.ipFunction = ipFunction;
		this.fgCrtdDt = fgCrtdDt;
	}

	// Property accessors

	public Long getFgId() {
		return this.fgId;
	}

	public void setFgId(Long fgId) {
		this.fgId = fgId;
	}

	public IpGroup getIpGroup() {
		return this.ipGroup;
	}

	public void setIpGroup(IpGroup ipGroup) {
		this.ipGroup = ipGroup;
	}

	public IpFunction getIpFunction() {
		return this.ipFunction;
	}

	public void setIpFunction(IpFunction ipFunction) {
		this.ipFunction = ipFunction;
	}

	public Date getFgCrtdDt() {
		return this.fgCrtdDt;
	}

	public void setFgCrtdDt(Date fgCrtdDt) {
		this.fgCrtdDt = fgCrtdDt;
	}

}