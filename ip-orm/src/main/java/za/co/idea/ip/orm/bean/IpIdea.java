package za.co.idea.ip.orm.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * IpIdea entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_idea", catalog = "lpdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "getIdeaCreatedByUser", query = "select idea.* from ip_idea idea where idea.idea_user_id=:id", resultClass = IpIdea.class), @NamedNativeQuery(name = "getIdeaByUser", query = "select idea.* from ip_idea idea where idea.idea_user_id=:id union select idea.* from ip_idea idea, ip_group_user igu, ip_idea_group iig where igu.gu_usr_id=:id and igu.gu_grp_id=iig.ig_grp_id and iig.ig_idea_id=idea.idea_id union select idea.* from ip_idea idea where idea.idea_id not in (select iig.ig_idea_id from ip_idea_group iig)", resultClass = IpIdea.class), @NamedNativeQuery(name = "getIdeaByStatus", query = "select idea.* from ip_idea idea where idea.idea_status=:status", resultClass = IpIdea.class),
		@NamedNativeQuery(name = "getIdeaByStatusUser", query = "select idea.* from ip_idea idea where idea.idea_user_id=:id and idea.idea_status=:sid union select idea.* from ip_idea idea, ip_group_user igu, ip_idea_group iig where igu.gu_usr_id=:id and igu.gu_grp_id=iig.ig_grp_id and iig.ig_idea_id=idea.idea_id and idea.idea_status=:sid union select idea.* from ip_idea idea where idea.idea_id not in (select iig.ig_idea_id from ip_idea_group iig) and idea.idea_status=:sid", resultClass = IpIdea.class),
		@NamedNativeQuery(name = "getReviewsIdeasByUser", query = "select distinct idea.* from ip_idea idea, ip_review rv where idea.idea_id=rv.rev_entity_id and idea.idea_status in (3,4,5,6,7) and rv.rev_sel_user_id=:id and rv.rev_entity_name='ip_idea' and (idea.idea_status = (select is_id from ip_idea_status where lower(is_desc) = lower(concat('In-Review ',rv.rev_entity_status_id))))", resultClass = IpIdea.class) })
public class IpIdea implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 973220354582688000L;
	private Long ideaId;
	private IpIdeaStatus ipIdeaStatus;
	private IpUser ipUser;
	private IpIdeaCat ipIdeaCat;
	private String ideaTitle;
	private String ideaDesc;
	private String ideaBa;
	private Date ideaDate;
	private String ideaTag;
	private Integer ideaReviewCnt;
	private Set<IpIdeaGroup> ipIdeaGroups = new HashSet<IpIdeaGroup>(0);

	// Constructors

	/** default constructor */
	public IpIdea() {
	}

	/** minimal constructor */
	public IpIdea(Long ideaId, IpIdeaStatus ipIdeaStatus, IpUser ipUser, String ideaTitle, String ideaDesc, Date ideaDate, String ideaTag) {
		this.ideaId = ideaId;
		this.ipIdeaStatus = ipIdeaStatus;
		this.ipUser = ipUser;
		this.ideaTitle = ideaTitle;
		this.ideaDesc = ideaDesc;
		this.ideaDate = ideaDate;
		this.ideaTag = ideaTag;
	}

	/** full constructor */
	public IpIdea(Long ideaId, IpIdeaStatus ipIdeaStatus, IpUser ipUser, IpIdeaCat ipIdeaCat, String ideaTitle, String ideaDesc, String ideaBa, Date ideaDate, String ideaTag, Integer ideaReviewCnt, Set<IpIdeaGroup> ipIdeaGroups) {
		this.ideaId = ideaId;
		this.ipIdeaStatus = ipIdeaStatus;
		this.ipUser = ipUser;
		this.ipIdeaCat = ipIdeaCat;
		this.ideaTitle = ideaTitle;
		this.ideaDesc = ideaDesc;
		this.ideaBa = ideaBa;
		this.ideaDate = ideaDate;
		this.ideaTag = ideaTag;
		this.ideaReviewCnt = ideaReviewCnt;
		this.ipIdeaGroups = ipIdeaGroups;
	}

	// Property accessors
	@Id
	@Column(name = "idea_id", unique = true, nullable = false)
	public Long getIdeaId() {
		return this.ideaId;
	}

	public void setIdeaId(Long ideaId) {
		this.ideaId = ideaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idea_status", nullable = false)
	public IpIdeaStatus getIpIdeaStatus() {
		return this.ipIdeaStatus;
	}

	public void setIpIdeaStatus(IpIdeaStatus ipIdeaStatus) {
		this.ipIdeaStatus = ipIdeaStatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idea_user_id", nullable = false)
	public IpUser getIpUser() {
		return this.ipUser;
	}

	public void setIpUser(IpUser ipUser) {
		this.ipUser = ipUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idea_cat")
	public IpIdeaCat getIpIdeaCat() {
		return this.ipIdeaCat;
	}

	public void setIpIdeaCat(IpIdeaCat ipIdeaCat) {
		this.ipIdeaCat = ipIdeaCat;
	}

	@Column(name = "idea_title", nullable = false, length = 100)
	public String getIdeaTitle() {
		return this.ideaTitle;
	}

	public void setIdeaTitle(String ideaTitle) {
		this.ideaTitle = ideaTitle;
	}

	@Column(name = "idea_desc", nullable = false, length = 65535)
	public String getIdeaDesc() {
		return this.ideaDesc;
	}

	public void setIdeaDesc(String ideaDesc) {
		this.ideaDesc = ideaDesc;
	}

	@Column(name = "idea_ba", length = 65535)
	public String getIdeaBa() {
		return this.ideaBa;
	}

	public void setIdeaBa(String ideaBa) {
		this.ideaBa = ideaBa;
	}

	@Column(name = "idea_date", nullable = false, length = 19)
	public Date getIdeaDate() {
		return this.ideaDate;
	}

	public void setIdeaDate(Date ideaDate) {
		this.ideaDate = ideaDate;
	}

	@Column(name = "idea_tag", nullable = false, length = 65535)
	public String getIdeaTag() {
		return this.ideaTag;
	}

	public void setIdeaTag(String ideaTag) {
		this.ideaTag = ideaTag;
	}

	@Column(name = "idea_review_cnt")
	public Integer getIdeaReviewCnt() {
		return this.ideaReviewCnt;
	}

	public void setIdeaReviewCnt(Integer ideaReviewCnt) {
		this.ideaReviewCnt = ideaReviewCnt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipIdea")
	public Set<IpIdeaGroup> getIpIdeaGroups() {
		return this.ipIdeaGroups;
	}

	public void setIpIdeaGroups(Set<IpIdeaGroup> ipIdeaGroups) {
		this.ipIdeaGroups = ipIdeaGroups;
	}

}