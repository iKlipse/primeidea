package za.co.idea.ip.orm.bean;

/**
 * IpFuncGroup entity. @author MyEclipse Persistence Tools
 */

public class IpFuncGroup implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6659702280428866806L;
	private Long fgId;
	private IpGroup ipGroup;
	private IpFunction ipFunction;

	// Constructors

	/** default constructor */
	public IpFuncGroup() {
	}

	/** minimal constructor */
	public IpFuncGroup(Long fgId, IpGroup ipGroup) {
		this.fgId = fgId;
		this.ipGroup = ipGroup;
	}

	/** full constructor */
	public IpFuncGroup(Long fgId, IpGroup ipGroup, IpFunction ipFunction) {
		this.fgId = fgId;
		this.ipGroup = ipGroup;
		this.ipFunction = ipFunction;
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

}