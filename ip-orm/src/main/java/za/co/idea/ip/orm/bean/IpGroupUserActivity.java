package za.co.idea.ip.orm.bean;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * IpGroupUserActivity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_group_user_activity", catalog = "lpdb")
public class IpGroupUserActivity implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -427945857045966156L;
	private IpGroupUserActivityId id;

	// Constructors

	/** default constructor */
	public IpGroupUserActivity() {
	}

	/** full constructor */
	public IpGroupUserActivity(IpGroupUserActivityId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "groupName", column = @Column(name = "group_name", nullable = false, length = 65535)),
			@AttributeOverride(name = "cnta", column = @Column(name = "cnta")),
			@AttributeOverride(name = "cntb", column = @Column(name = "cntb")),
			@AttributeOverride(name = "cntc", column = @Column(name = "cntc", precision = 32, scale = 0)),
			@AttributeOverride(name = "totcnt", column = @Column(name = "totcnt")) })
	public IpGroupUserActivityId getId() {
		return this.id;
	}

	public void setId(IpGroupUserActivityId id) {
		this.id = id;
	}

}