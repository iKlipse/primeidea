package za.co.idea.ip.orm.bean;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * IpIdeaGrpCnt entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_idea_grp_cnt", catalog = "lpdb")
public class IpIdeaGrpCnt implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -213512163670643053L;
	private IpIdeaGrpCntId id;

	// Constructors

	/** default constructor */
	public IpIdeaGrpCnt() {
	}

	/** full constructor */
	public IpIdeaGrpCnt(IpIdeaGrpCntId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "groupName", column = @Column(name = "group_name", nullable = false, length = 65535)),
			@AttributeOverride(name = "cnta", column = @Column(name = "cnta")),
			@AttributeOverride(name = "cntb", column = @Column(name = "cntb")),
			@AttributeOverride(name = "cntc", column = @Column(name = "cntc")),
			@AttributeOverride(name = "cntd", column = @Column(name = "cntd")) })
	public IpIdeaGrpCntId getId() {
		return this.id;
	}

	public void setId(IpIdeaGrpCntId id) {
		this.id = id;
	}

}