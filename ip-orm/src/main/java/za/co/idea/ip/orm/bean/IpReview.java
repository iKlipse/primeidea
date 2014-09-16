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
 * IpReview entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_review", catalog = "lpdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "getByEntityIdEntityNameStatus", query = "select ib.* from ip_review ib where ib.rev_entity_id=:id and lower(ib.rev_entity_name)=lower(:tblNm) and rev_entity_status_id=:status", resultClass = IpReview.class), @NamedNativeQuery(name = "getByEntityIdEntityName", query = "select ib.* from ip_review ib where ib.rev_entity_id=:id and lower(ib.rev_entity_name)=lower(:tblNm)", resultClass = IpReview.class), @NamedNativeQuery(name = "deleteByEntityIdEntityNameStatus", query = "delete from ip_review where rev_entity_id=:id and lower(rev_entity_name)=lower(:tblNm) and rev_entity_status_id=:status"), @NamedNativeQuery(name = "getUnallocatedReview", query = "select ib.* from ip_review ib where ib.rev_sel_user_id is null", resultClass = IpReview.class),
		@NamedNativeQuery(name = "getReviewByUserId", query = "select ib.* from ip_review ib where ib.rev_sel_user_id=:id", resultClass = IpReview.class), @NamedNativeQuery(name = "updateReviewer", query = "update ip_review set rev_sel_user_id=:uid where rev_entity_id=:id and rev_entity_name=:tblNm") })
public class IpReview implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6710862376235029379L;
	private Long revId;
	private IpGroup ipGroup;
	private Long revEntityId;
	private String revEntityName;
	private Integer revEntityStatusId;
	private Date revCrtdDt;
	private Long revSelUserId;

	// Constructors

	/** default constructor */
	public IpReview() {
	}

	/** minimal constructor */
	public IpReview(Long revId, Date revCrtdDt) {
		this.revId = revId;
		this.revCrtdDt = revCrtdDt;
	}

	/** full constructor */
	public IpReview(Long revId, IpGroup ipGroup, Long revEntityId, String revEntityName, Integer revEntityStatusId, Date revCrtdDt, Long revSelUserId) {
		this.revId = revId;
		this.ipGroup = ipGroup;
		this.revEntityId = revEntityId;
		this.revEntityName = revEntityName;
		this.revEntityStatusId = revEntityStatusId;
		this.revCrtdDt = revCrtdDt;
		this.revSelUserId = revSelUserId;
	}

	// Property accessors
	@Id
	@Column(name = "rev_id", unique = true, nullable = false)
	public Long getRevId() {
		return this.revId;
	}

	public void setRevId(Long revId) {
		this.revId = revId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rev_grp_id")
	public IpGroup getIpGroup() {
		return this.ipGroup;
	}

	public void setIpGroup(IpGroup ipGroup) {
		this.ipGroup = ipGroup;
	}

	@Column(name = "rev_entity_id")
	public Long getRevEntityId() {
		return this.revEntityId;
	}

	public void setRevEntityId(Long revEntityId) {
		this.revEntityId = revEntityId;
	}

	@Column(name = "rev_entity_name", length = 100)
	public String getRevEntityName() {
		return this.revEntityName;
	}

	public void setRevEntityName(String revEntityName) {
		this.revEntityName = revEntityName;
	}

	@Column(name = "rev_entity_status_id")
	public Integer getRevEntityStatusId() {
		return this.revEntityStatusId;
	}

	public void setRevEntityStatusId(Integer revEntityStatusId) {
		this.revEntityStatusId = revEntityStatusId;
	}

	@Column(name = "rev_crtd_dt", nullable = false, length = 19)
	public Date getRevCrtdDt() {
		return this.revCrtdDt;
	}

	public void setRevCrtdDt(Date revCrtdDt) {
		this.revCrtdDt = revCrtdDt;
	}

	@Column(name = "rev_sel_user_id")
	public Long getRevSelUserId() {
		return this.revSelUserId;
	}

	public void setRevSelUserId(Long revSelUserId) {
		this.revSelUserId = revSelUserId;
	}

}