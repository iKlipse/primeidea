package za.co.idea.ip.orm.bean;

/**
 * IpCategory entity. @author MyEclipse Persistence Tools
 */

public class IpCategory implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7905307845706790850L;
	private Integer catId;
	private String catDesc;

	// Constructors

	/** default constructor */
	public IpCategory() {
	}

	/** minimal constructor */
	public IpCategory(Integer catId) {
		this.catId = catId;
	}

	/** full constructor */
	public IpCategory(Integer catId, String catDesc) {
		this.catId = catId;
		this.catDesc = catDesc;
	}

	// Property accessors

	public Integer getCatId() {
		return this.catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getCatDesc() {
		return this.catDesc;
	}

	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}

}