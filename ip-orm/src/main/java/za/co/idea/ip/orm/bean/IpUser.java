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
import javax.persistence.UniqueConstraint;

/**
 * IpUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_user", catalog = "lpdb", uniqueConstraints = @UniqueConstraint(columnNames = "user_screen_name"))
@NamedNativeQueries({ @NamedNativeQuery(name = "updateImage", query = "update ip_user set user_avatar=:avatar where user_id=:id"), @NamedNativeQuery(name = "sortListByPrimaryGrp", query = "select iu.* from ip_user iu, ip_group ig where iu.pri_grp_id=ig.group_id order by ig.group_name", resultClass = IpUser.class), @NamedNativeQuery(name = "getUsersByStatus", query = "select iu.* from ip_user iu where iu.user_status=:status", resultClass = IpUser.class), @NamedNativeQuery(name = "getSolCountByUserId", query = "select sol.* from ip_solution sol where sol.sol_crtd_by=:id", resultClass = IpSolution.class), @NamedNativeQuery(name = "getChalCountByUserId", query = "select chal.* from ip_challenge chal where chal.chal_crtd_by=:id", resultClass = IpChallenge.class),
		@NamedNativeQuery(name = "getIdeasCountByUserId", query = "select idea.* from ip_idea idea where idea.idea_user_id=:id", resultClass = IpIdea.class), @NamedNativeQuery(name = "getWhishListCountByUserId", query = "select * from ip_tag t where t.tag_user_id=:id and t.tag_entity_type=4 and t.tag_type=4 and t.tag_entity_id in (select rw_id from ip_review where current_Date between rw_launch_dt and rw_expiry_dt)", resultClass = IpTag.class) })
public class IpUser implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 3436783696029905208L;
	private Long userId;
	private IpGroup ipGroup;
	private String userFName;
	private String userLName;
	private String userMName;
	private Long userIdNum;
	private String userScreenName;
	private String userEmail;
	private String userContact;
	private String userSkills;
	private String userBio;
	private String userFbHandle;
	private String userTwHandle;
	private String userStatus;
	private String userEmployeeId;
	private Date userCrtdDt;
	private Set<IpGroup> ipGroups = new HashSet<IpGroup>(0);
	private Set<IpGroupUser> ipGroupUsers = new HashSet<IpGroupUser>(0);
	private Set<IpTag> ipTags = new HashSet<IpTag>(0);
	private Set<IpFunction> ipFunctions = new HashSet<IpFunction>(0);
	private Set<IpClaim> ipClaims = new HashSet<IpClaim>(0);
	private Set<IpPoints> ipPointses = new HashSet<IpPoints>(0);
	private Set<IpChallenge> ipChallenges = new HashSet<IpChallenge>(0);
	private Set<IpIdea> ipIdeas = new HashSet<IpIdea>(0);
	private Set<IpLogin> ipLogins = new HashSet<IpLogin>(0);
	private Set<IpSolution> ipSolutions = new HashSet<IpSolution>(0);

	// Constructors

	/** default constructor */
	public IpUser() {
	}

	/** minimal constructor */
	public IpUser(Long userId, String userFName, String userLName, String userScreenName, String userStatus, Date userCrtdDt) {
		this.userId = userId;
		this.userFName = userFName;
		this.userLName = userLName;
		this.userScreenName = userScreenName;
		this.userStatus = userStatus;
		this.userCrtdDt = userCrtdDt;
	}

	/** full constructor */
	public IpUser(Long userId, IpGroup ipGroup, String userFName, String userLName, String userMName, Long userIdNum, String userScreenName, String userEmail, String userContact, String userSkills, String userBio, String userFbHandle, String userTwHandle, String userStatus, String userEmployeeId, Date userCrtdDt, Set<IpGroup> ipGroups, Set<IpGroupUser> ipGroupUsers, Set<IpTag> ipTags, Set<IpFunction> ipFunctions, Set<IpClaim> ipClaims, Set<IpPoints> ipPointses, Set<IpChallenge> ipChallenges, Set<IpIdea> ipIdeas, Set<IpLogin> ipLogins, Set<IpSolution> ipSolutions) {
		this.userId = userId;
		this.ipGroup = ipGroup;
		this.userFName = userFName;
		this.userLName = userLName;
		this.userMName = userMName;
		this.userIdNum = userIdNum;
		this.userScreenName = userScreenName;
		this.userEmail = userEmail;
		this.userContact = userContact;
		this.userSkills = userSkills;
		this.userBio = userBio;
		this.userFbHandle = userFbHandle;
		this.userTwHandle = userTwHandle;
		this.userStatus = userStatus;
		this.userEmployeeId = userEmployeeId;
		this.userCrtdDt = userCrtdDt;
		this.ipGroups = ipGroups;
		this.ipGroupUsers = ipGroupUsers;
		this.ipTags = ipTags;
		this.ipFunctions = ipFunctions;
		this.ipClaims = ipClaims;
		this.ipPointses = ipPointses;
		this.ipChallenges = ipChallenges;
		this.ipIdeas = ipIdeas;
		this.ipLogins = ipLogins;
		this.ipSolutions = ipSolutions;
	}

	// Property accessors
	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_pri_grp_id")
	public IpGroup getIpGroup() {
		return this.ipGroup;
	}

	public void setIpGroup(IpGroup ipGroup) {
		this.ipGroup = ipGroup;
	}

	@Column(name = "user_f_name", nullable = false, length = 75)
	public String getUserFName() {
		return this.userFName;
	}

	public void setUserFName(String userFName) {
		this.userFName = userFName;
	}

	@Column(name = "user_l_name", nullable = false, length = 75)
	public String getUserLName() {
		return this.userLName;
	}

	public void setUserLName(String userLName) {
		this.userLName = userLName;
	}

	@Column(name = "user_m_name", length = 45)
	public String getUserMName() {
		return this.userMName;
	}

	public void setUserMName(String userMName) {
		this.userMName = userMName;
	}

	@Column(name = "user_id_num")
	public Long getUserIdNum() {
		return this.userIdNum;
	}

	public void setUserIdNum(Long userIdNum) {
		this.userIdNum = userIdNum;
	}

	@Column(name = "user_screen_name", unique = true, nullable = false, length = 50)
	public String getUserScreenName() {
		return this.userScreenName;
	}

	public void setUserScreenName(String userScreenName) {
		this.userScreenName = userScreenName;
	}

	@Column(name = "user_email", length = 65535)
	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Column(name = "user_contact", length = 20)
	public String getUserContact() {
		return this.userContact;
	}

	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}

	@Column(name = "user_skills", length = 65535)
	public String getUserSkills() {
		return this.userSkills;
	}

	public void setUserSkills(String userSkills) {
		this.userSkills = userSkills;
	}

	@Column(name = "user_bio", length = 65535)
	public String getUserBio() {
		return this.userBio;
	}

	public void setUserBio(String userBio) {
		this.userBio = userBio;
	}

	@Column(name = "user_fb_handle", length = 65535)
	public String getUserFbHandle() {
		return this.userFbHandle;
	}

	public void setUserFbHandle(String userFbHandle) {
		this.userFbHandle = userFbHandle;
	}

	@Column(name = "user_tw_handle", length = 65535)
	public String getUserTwHandle() {
		return this.userTwHandle;
	}

	public void setUserTwHandle(String userTwHandle) {
		this.userTwHandle = userTwHandle;
	}

	@Column(name = "user_status", nullable = false, length = 1)
	public String getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	@Column(name = "user_employeeId", length = 20)
	public String getUserEmployeeId() {
		return this.userEmployeeId;
	}

	public void setUserEmployeeId(String userEmployeeId) {
		this.userEmployeeId = userEmployeeId;
	}

	@Column(name = "user_crtd_dt", nullable = false, length = 19)
	public Date getUserCrtdDt() {
		return this.userCrtdDt;
	}

	public void setUserCrtdDt(Date userCrtdDt) {
		this.userCrtdDt = userCrtdDt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipUser")
	public Set<IpGroup> getIpGroups() {
		return this.ipGroups;
	}

	public void setIpGroups(Set<IpGroup> ipGroups) {
		this.ipGroups = ipGroups;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipUser")
	public Set<IpGroupUser> getIpGroupUsers() {
		return this.ipGroupUsers;
	}

	public void setIpGroupUsers(Set<IpGroupUser> ipGroupUsers) {
		this.ipGroupUsers = ipGroupUsers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipUser")
	public Set<IpTag> getIpTags() {
		return this.ipTags;
	}

	public void setIpTags(Set<IpTag> ipTags) {
		this.ipTags = ipTags;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipUser")
	public Set<IpFunction> getIpFunctions() {
		return this.ipFunctions;
	}

	public void setIpFunctions(Set<IpFunction> ipFunctions) {
		this.ipFunctions = ipFunctions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipUser")
	public Set<IpClaim> getIpClaims() {
		return this.ipClaims;
	}

	public void setIpClaims(Set<IpClaim> ipClaims) {
		this.ipClaims = ipClaims;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipUser")
	public Set<IpPoints> getIpPointses() {
		return this.ipPointses;
	}

	public void setIpPointses(Set<IpPoints> ipPointses) {
		this.ipPointses = ipPointses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipUser")
	public Set<IpChallenge> getIpChallenges() {
		return this.ipChallenges;
	}

	public void setIpChallenges(Set<IpChallenge> ipChallenges) {
		this.ipChallenges = ipChallenges;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipUser")
	public Set<IpIdea> getIpIdeas() {
		return this.ipIdeas;
	}

	public void setIpIdeas(Set<IpIdea> ipIdeas) {
		this.ipIdeas = ipIdeas;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipUser")
	public Set<IpLogin> getIpLogins() {
		return this.ipLogins;
	}

	public void setIpLogins(Set<IpLogin> ipLogins) {
		this.ipLogins = ipLogins;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipUser")
	public Set<IpSolution> getIpSolutions() {
		return this.ipSolutions;
	}

	public void setIpSolutions(Set<IpSolution> ipSolutions) {
		this.ipSolutions = ipSolutions;
	}

}