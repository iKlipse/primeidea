package za.co.idea.ip.orm.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 * IpIdeaGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_idea_group", catalog = "lpdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "deleteIGByIdeaId", query = "delete from ip_idea_group where	ig_idea_id=:id"), @NamedNativeQuery(name = "fetchIGByIdeaId", query = "select fg.* from ip_idea_group fg where fg.ig_idea_id=:id", resultClass = IpIdeaGroup.class), @NamedNativeQuery(name = "fetchIGByGroupId", query = "select fg.* from ip_idea_group fg where fg.ig_grp_id=:id", resultClass = IpIdeaGroup.class) })
public class IpIdeaGroup implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8409889379001065296L;
	private Long igId;
	private IpIdea ipIdea;
	private IpGroup ipGroup;
	private Date igCrtdDt;

	// Constructors

	/** default constructor */
	public IpIdeaGroup() {
	}

	/** minimal constructor */
	public IpIdeaGroup(Long igId, Date igCrtdDt) {
		this.igId = igId;
		this.igCrtdDt = igCrtdDt;
	}

	/** full constructor */
	public IpIdeaGroup(Long igId, IpIdea ipIdea, IpGroup ipGroup, Date igCrtdDt) {
		this.igId = igId;
		this.ipIdea = ipIdea;
		this.ipGroup = ipGroup;
		this.igCrtdDt = igCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "ig_id", unique = true, nullable = false)
	public Long getIgId() {
		return this.igId;
	}

	public void setIgId(Long igId) {
		this.igId = igId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ig_idea_id")
	public IpIdea getIpIdea() {
		return this.ipIdea;
	}

	public void setIpIdea(IpIdea ipIdea) {
		this.ipIdea = ipIdea;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ig_grp_id")
	public IpGroup getIpGroup() {
		return this.ipGroup;
	}

	public void setIpGroup(IpGroup ipGroup) {
		this.ipGroup = ipGroup;
	}

	@Column(name = "ig_crtd_dt", nullable = false, length = 19)
	public Date getIgCrtdDt() {
		return this.igCrtdDt;
	}

	public void setIgCrtdDt(Date igCrtdDt) {
		this.igCrtdDt = igCrtdDt;
	}

}