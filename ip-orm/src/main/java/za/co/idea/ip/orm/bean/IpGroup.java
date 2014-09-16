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
 * IpGroup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_group", catalog = "lpdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "getGroupsByStatus", query = "select ig.* from ip_group ig where ig.group_status=:status", resultClass = IpGroup.class), @NamedNativeQuery(name = "getGroupsByParent", query = "select ig.* from ip_group ig where ig.group_parent_id=:id", resultClass = IpGroup.class), @NamedNativeQuery(name = "getGroupsForReview", query = "select distinct ig.* from ip_group ig, ip_group_user igu where ig.group_id=igu.gu_grp_id", resultClass = IpGroup.class) })
public class IpGroup implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8951839931983658432L;
	private Long groupId;
	private IpUser ipUser;
	private IpGroup ipGroup;
	private String groupName;
	private String groupStatus;
	private String groupEmail;
	private String groupIsCore;
	private Date groupCrtdDt;
	private Set<IpUser> ipUsers = new HashSet<IpUser>(0);
	private Set<IpReview> ipReviews = new HashSet<IpReview>(0);
	private Set<IpIdeaGroup> ipIdeaGroups = new HashSet<IpIdeaGroup>(0);
	private Set<IpGroupUser> ipGroupUsers = new HashSet<IpGroupUser>(0);
	private Set<IpFuncGroup> ipFuncGroups = new HashSet<IpFuncGroup>(0);
	private Set<IpRewardsGroup> ipRewardsGroups = new HashSet<IpRewardsGroup>(0);
	private Set<IpGroup> ipGroups = new HashSet<IpGroup>(0);
	private Set<IpChallengeGroup> ipChallengeGroups = new HashSet<IpChallengeGroup>(0);

	// Constructors

	/** default constructor */
	public IpGroup() {
	}

	/** minimal constructor */
	public IpGroup(Long groupId, String groupName, Date groupCrtdDt) {
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupCrtdDt = groupCrtdDt;
	}

	/** full constructor */
	public IpGroup(Long groupId, IpUser ipUser, IpGroup ipGroup, String groupName, String groupStatus, String groupEmail, String groupIsCore, Date groupCrtdDt, Set<IpUser> ipUsers, Set<IpReview> ipReviews, Set<IpIdeaGroup> ipIdeaGroups, Set<IpGroupUser> ipGroupUsers, Set<IpFuncGroup> ipFuncGroups, Set<IpRewardsGroup> ipRewardsGroups, Set<IpGroup> ipGroups, Set<IpChallengeGroup> ipChallengeGroups) {
		this.groupId = groupId;
		this.ipUser = ipUser;
		this.ipGroup = ipGroup;
		this.groupName = groupName;
		this.groupStatus = groupStatus;
		this.groupEmail = groupEmail;
		this.groupIsCore = groupIsCore;
		this.groupCrtdDt = groupCrtdDt;
		this.ipUsers = ipUsers;
		this.ipReviews = ipReviews;
		this.ipIdeaGroups = ipIdeaGroups;
		this.ipGroupUsers = ipGroupUsers;
		this.ipFuncGroups = ipFuncGroups;
		this.ipRewardsGroups = ipRewardsGroups;
		this.ipGroups = ipGroups;
		this.ipChallengeGroups = ipChallengeGroups;
	}

	// Property accessors
	@Id
	@Column(name = "group_id", unique = true, nullable = false)
	public Long getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_admin_id")
	public IpUser getIpUser() {
		return this.ipUser;
	}

	public void setIpUser(IpUser ipUser) {
		this.ipUser = ipUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_parent_id")
	public IpGroup getIpGroup() {
		return this.ipGroup;
	}

	public void setIpGroup(IpGroup ipGroup) {
		this.ipGroup = ipGroup;
	}

	@Column(name = "group_name", nullable = false, length = 65535)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "group_status", length = 45)
	public String getGroupStatus() {
		return this.groupStatus;
	}

	public void setGroupStatus(String groupStatus) {
		this.groupStatus = groupStatus;
	}

	@Column(name = "group_email", length = 65535)
	public String getGroupEmail() {
		return this.groupEmail;
	}

	public void setGroupEmail(String groupEmail) {
		this.groupEmail = groupEmail;
	}

	@Column(name = "group_is_core", length = 1)
	public String getGroupIsCore() {
		return this.groupIsCore;
	}

	public void setGroupIsCore(String groupIsCore) {
		this.groupIsCore = groupIsCore;
	}

	@Column(name = "group_crtd_dt", nullable = false, length = 19)
	public Date getGroupCrtdDt() {
		return this.groupCrtdDt;
	}

	public void setGroupCrtdDt(Date groupCrtdDt) {
		this.groupCrtdDt = groupCrtdDt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipGroup")
	public Set<IpUser> getIpUsers() {
		return this.ipUsers;
	}

	public void setIpUsers(Set<IpUser> ipUsers) {
		this.ipUsers = ipUsers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipGroup")
	public Set<IpReview> getIpReviews() {
		return this.ipReviews;
	}

	public void setIpReviews(Set<IpReview> ipReviews) {
		this.ipReviews = ipReviews;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipGroup")
	public Set<IpIdeaGroup> getIpIdeaGroups() {
		return this.ipIdeaGroups;
	}

	public void setIpIdeaGroups(Set<IpIdeaGroup> ipIdeaGroups) {
		this.ipIdeaGroups = ipIdeaGroups;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipGroup")
	public Set<IpGroupUser> getIpGroupUsers() {
		return this.ipGroupUsers;
	}

	public void setIpGroupUsers(Set<IpGroupUser> ipGroupUsers) {
		this.ipGroupUsers = ipGroupUsers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipGroup")
	public Set<IpFuncGroup> getIpFuncGroups() {
		return this.ipFuncGroups;
	}

	public void setIpFuncGroups(Set<IpFuncGroup> ipFuncGroups) {
		this.ipFuncGroups = ipFuncGroups;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipGroup")
	public Set<IpRewardsGroup> getIpRewardsGroups() {
		return this.ipRewardsGroups;
	}

	public void setIpRewardsGroups(Set<IpRewardsGroup> ipRewardsGroups) {
		this.ipRewardsGroups = ipRewardsGroups;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipGroup")
	public Set<IpGroup> getIpGroups() {
		return this.ipGroups;
	}

	public void setIpGroups(Set<IpGroup> ipGroups) {
		this.ipGroups = ipGroups;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipGroup")
	public Set<IpChallengeGroup> getIpChallengeGroups() {
		return this.ipChallengeGroups;
	}

	public void setIpChallengeGroups(Set<IpChallengeGroup> ipChallengeGroups) {
		this.ipChallengeGroups = ipChallengeGroups;
	}

}