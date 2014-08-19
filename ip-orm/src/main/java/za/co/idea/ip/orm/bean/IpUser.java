package za.co.idea.ip.orm.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * IpUser entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("rawtypes")
public class IpUser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 367921902056908014L;
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
	private Set ipGroups = new HashSet(0);
	private Set ipGroupUsers = new HashSet(0);
	private Set ipTags = new HashSet(0);
	private Set ipFunctions = new HashSet(0);
	private Set ipClaims = new HashSet(0);
	private Set ipPointses = new HashSet(0);
	private Set ipChallenges = new HashSet(0);
	private Set ipIdeas = new HashSet(0);
	private Set ipLogins = new HashSet(0);
	private Set ipSolutions = new HashSet(0);

	// Constructors

	/** default constructor */
	public IpUser() {
	}

	/** minimal constructor */
	public IpUser(Long userId, String userFName, String userLName, String userScreenName, String userStatus) {
		this.userId = userId;
		this.userFName = userFName;
		this.userLName = userLName;
		this.userScreenName = userScreenName;
		this.userStatus = userStatus;
	}

	/** full constructor */
	public IpUser(Long userId, IpGroup ipGroup, String userFName, String userLName, String userMName, Long userIdNum, String userScreenName, String userEmail, String userContact, String userSkills, String userBio, String userFbHandle, String userTwHandle, String userStatus, String userEmployeeId, Set ipGroups, Set ipGroupUsers, Set ipTags, Set ipFunctions, Set ipClaims, Set ipPointses, Set ipChallenges, Set ipIdeas, Set ipLogins, Set ipSolutions) {
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

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public IpGroup getIpGroup() {
		return this.ipGroup;
	}

	public void setIpGroup(IpGroup ipGroup) {
		this.ipGroup = ipGroup;
	}

	public String getUserFName() {
		return this.userFName;
	}

	public void setUserFName(String userFName) {
		this.userFName = userFName;
	}

	public String getUserLName() {
		return this.userLName;
	}

	public void setUserLName(String userLName) {
		this.userLName = userLName;
	}

	public String getUserMName() {
		return this.userMName;
	}

	public void setUserMName(String userMName) {
		this.userMName = userMName;
	}

	public Long getUserIdNum() {
		return this.userIdNum;
	}

	public void setUserIdNum(Long userIdNum) {
		this.userIdNum = userIdNum;
	}

	public String getUserScreenName() {
		return this.userScreenName;
	}

	public void setUserScreenName(String userScreenName) {
		this.userScreenName = userScreenName;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserContact() {
		return this.userContact;
	}

	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}

	public String getUserSkills() {
		return this.userSkills;
	}

	public void setUserSkills(String userSkills) {
		this.userSkills = userSkills;
	}

	public String getUserBio() {
		return this.userBio;
	}

	public void setUserBio(String userBio) {
		this.userBio = userBio;
	}

	public String getUserFbHandle() {
		return this.userFbHandle;
	}

	public void setUserFbHandle(String userFbHandle) {
		this.userFbHandle = userFbHandle;
	}

	public String getUserTwHandle() {
		return this.userTwHandle;
	}

	public void setUserTwHandle(String userTwHandle) {
		this.userTwHandle = userTwHandle;
	}

	public String getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserEmployeeId() {
		return this.userEmployeeId;
	}

	public void setUserEmployeeId(String userEmployeeId) {
		this.userEmployeeId = userEmployeeId;
	}

	public Set getIpGroups() {
		return this.ipGroups;
	}

	public void setIpGroups(Set ipGroups) {
		this.ipGroups = ipGroups;
	}

	public Set getIpGroupUsers() {
		return this.ipGroupUsers;
	}

	public void setIpGroupUsers(Set ipGroupUsers) {
		this.ipGroupUsers = ipGroupUsers;
	}

	public Set getIpTags() {
		return this.ipTags;
	}

	public void setIpTags(Set ipTags) {
		this.ipTags = ipTags;
	}

	public Set getIpFunctions() {
		return this.ipFunctions;
	}

	public void setIpFunctions(Set ipFunctions) {
		this.ipFunctions = ipFunctions;
	}

	public Set getIpClaims() {
		return this.ipClaims;
	}

	public void setIpClaims(Set ipClaims) {
		this.ipClaims = ipClaims;
	}

	public Set getIpPointses() {
		return this.ipPointses;
	}

	public void setIpPointses(Set ipPointses) {
		this.ipPointses = ipPointses;
	}

	public Set getIpChallenges() {
		return this.ipChallenges;
	}

	public void setIpChallenges(Set ipChallenges) {
		this.ipChallenges = ipChallenges;
	}

	public Set getIpIdeas() {
		return this.ipIdeas;
	}

	public void setIpIdeas(Set ipIdeas) {
		this.ipIdeas = ipIdeas;
	}

	public Set getIpLogins() {
		return this.ipLogins;
	}

	public void setIpLogins(Set ipLogins) {
		this.ipLogins = ipLogins;
	}

	public Set getIpSolutions() {
		return this.ipSolutions;
	}

	public void setIpSolutions(Set ipSolutions) {
		this.ipSolutions = ipSolutions;
	}

}