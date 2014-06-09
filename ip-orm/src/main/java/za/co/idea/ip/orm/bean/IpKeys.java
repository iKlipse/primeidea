package za.co.idea.ip.orm.bean;

/**
 * IpKeys entity. @author MyEclipse Persistence Tools
 */

public class IpKeys implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5491131017859225725L;
	private Integer id;
	private String table;
	private Long val;

	// Constructors

	/** default constructor */
	public IpKeys() {
	}

	/** minimal constructor */
	public IpKeys(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public IpKeys(Integer id, String table, Long val) {
		this.id = id;
		this.table = table;
		this.val = val;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTable() {
		return this.table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public Long getVal() {
		return this.val;
	}

	public void setVal(Long val) {
		this.val = val;
	}

}