package za.co.idea.ip.orm.bean;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * IpUserActivity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_user_activity", catalog = "lpdb")
public class IpUserActivity implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2861841699970495689L;
	private IpUserActivityId id;

	// Constructors

	/** default constructor */
	public IpUserActivity() {
	}

	/** full constructor */
	public IpUserActivity(IpUserActivityId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userFName", column = @Column(name = "user_f_name", nullable = false, length = 75)),
			@AttributeOverride(name = "userLName", column = @Column(name = "user_l_name", nullable = false, length = 75)),
			@AttributeOverride(name = "groupName", column = @Column(name = "group_name", nullable = false, length = 65535)),
			@AttributeOverride(name = "dta", column = @Column(name = "dta", length = 19)),
			@AttributeOverride(name = "dtb", column = @Column(name = "dtb", length = 19)),
			@AttributeOverride(name = "cntb", column = @Column(name = "cntb")),
			@AttributeOverride(name = "cntc", column = @Column(name = "cntc", precision = 32, scale = 0)) })
	public IpUserActivityId getId() {
		return this.id;
	}

	public void setId(IpUserActivityId id) {
		this.id = id;
	}

}