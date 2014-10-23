package za.co.idea.ip.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 * IpCategory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_category", catalog = "lpdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "checkDependency", query = "select cat.* from ip_category cat where cat.cat_id=:id and ((cat.cat_id in (select distinct chal_cat from ip_challenge)) or (cat.cat_id in (select distinct sol_cat from ip_solution)) or (cat.cat_id in (select distinct idea_cat from ip_idea)))", resultClass = IpCategory.class) })
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
	@Id
	@Column(name = "cat_id", unique = true, nullable = false)
	public Integer getCatId() {
		return this.catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	@Column(name = "cat_desc", length = 450)
	public String getCatDesc() {
		return this.catDesc;
	}

	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}

}