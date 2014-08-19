package za.co.idea.ip.orm.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * IpGroup entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("rawtypes")
public class IpGroup implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2098764774664380317L;
	private Long groupId;
	private IpUser ipUser;
	private IpGroup ipGroup;
	private String groupName;
	private String groupStatus;
	private String groupEmail;
	private String groupIsCore;
	private Set ipUsers = new HashSet(0);
	private Set ipIdeaGroups = new HashSet(0);
	private Set ipGroupUsers = new HashSet(0);
	private Set ipFuncGroups = new HashSet(0);
	private Set ipRewardsGroups = new HashSet(0);
	private Set ipGroups = new HashSet(0);
	private Set ipChallengeGroups = new HashSet(0);

	// Constructors

	/** default constructor */
	public IpGroup() {
	}

	/** minimal constructor */
	public IpGroup(Long groupId, String groupName) {
		this.groupId = groupId;
		this.groupName = groupName;
	}

	/** full constructor */
	public IpGroup(Long groupId, IpUser ipUser, IpGroup ipGroup, String groupName, String groupStatus, String groupEmail, String groupIsCore, Set ipUsers, Set ipIdeaGroups, Set ipGroupUsers, Set ipFuncGroups, Set ipRewardsGroups, Set ipGroups, Set ipChallengeGroups) {
		this.groupId = groupId;
		this.ipUser = ipUser;
		this.ipGroup = ipGroup;
		this.groupName = groupName;
		this.groupStatus = groupStatus;
		this.groupEmail = groupEmail;
		this.groupIsCore = groupIsCore;
		this.ipUsers = ipUsers;
		this.ipIdeaGroups = ipIdeaGroups;
		this.ipGroupUsers = ipGroupUsers;
		this.ipFuncGroups = ipFuncGroups;
		this.ipRewardsGroups = ipRewardsGroups;
		this.ipGroups = ipGroups;
		this.ipChallengeGroups = ipChallengeGroups;
	}

	// Property accessors

	public Long getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public IpUser getIpUser() {
		return this.ipUser;
	}

	public void setIpUser(IpUser ipUser) {
		this.ipUser = ipUser;
	}

	public IpGroup getIpGroup() {
		return this.ipGroup;
	}

	public void setIpGroup(IpGroup ipGroup) {
		this.ipGroup = ipGroup;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupStatus() {
		return this.groupStatus;
	}

	public void setGroupStatus(String groupStatus) {
		this.groupStatus = groupStatus;
	}

	public String getGroupEmail() {
		return this.groupEmail;
	}

	public void setGroupEmail(String groupEmail) {
		this.groupEmail = groupEmail;
	}

	public String getGroupIsCore() {
		return this.groupIsCore;
	}

	public void setGroupIsCore(String groupIsCore) {
		this.groupIsCore = groupIsCore;
	}

	public Set getIpUsers() {
		return this.ipUsers;
	}

	public void setIpUsers(Set ipUsers) {
		this.ipUsers = ipUsers;
	}

	public Set getIpIdeaGroups() {
		return this.ipIdeaGroups;
	}

	public void setIpIdeaGroups(Set ipIdeaGroups) {
		this.ipIdeaGroups = ipIdeaGroups;
	}

	public Set getIpGroupUsers() {
		return this.ipGroupUsers;
	}

	public void setIpGroupUsers(Set ipGroupUsers) {
		this.ipGroupUsers = ipGroupUsers;
	}

	public Set getIpFuncGroups() {
		return this.ipFuncGroups;
	}

	public void setIpFuncGroups(Set ipFuncGroups) {
		this.ipFuncGroups = ipFuncGroups;
	}

	public Set getIpRewardsGroups() {
		return this.ipRewardsGroups;
	}

	public void setIpRewardsGroups(Set ipRewardsGroups) {
		this.ipRewardsGroups = ipRewardsGroups;
	}

	public Set getIpGroups() {
		return this.ipGroups;
	}

	public void setIpGroups(Set ipGroups) {
		this.ipGroups = ipGroups;
	}

	public Set getIpChallengeGroups() {
		return this.ipChallengeGroups;
	}

	public void setIpChallengeGroups(Set ipChallengeGroups) {
		this.ipChallengeGroups = ipChallengeGroups;
	}

}