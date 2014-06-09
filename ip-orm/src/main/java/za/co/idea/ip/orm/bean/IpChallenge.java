package za.co.idea.ip.orm.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * IpChallenge entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("rawtypes")
public class IpChallenge implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4320312728640281892L;
	private Long chalId;
	private IpChallengeStatus ipChallengeStatus;
	private IpChallengeCat ipChallengeCat;
	private IpUser ipUser;
	private String chalTitle;
	private String chalDesc;
	private String chalHoverTxt;
	private Date chalLaunchDt;
	private Date chalExpiryDt;
	private String chalTags;
	private Date chalCrtdDt;
	private Set ipChallengeGroups = new HashSet(0);
	private Set ipSolutions = new HashSet(0);

	// Constructors

	/** default constructor */
	public IpChallenge() {
	}

	/** minimal constructor */
	public IpChallenge(Long chalId, IpChallengeStatus ipChallengeStatus, IpChallengeCat ipChallengeCat, IpUser ipUser, String chalTitle, String chalDesc, Date chalLaunchDt, Date chalExpiryDt, Date chalCrtdDt) {
		this.chalId = chalId;
		this.ipChallengeStatus = ipChallengeStatus;
		this.ipChallengeCat = ipChallengeCat;
		this.ipUser = ipUser;
		this.chalTitle = chalTitle;
		this.chalDesc = chalDesc;
		this.chalLaunchDt = chalLaunchDt;
		this.chalExpiryDt = chalExpiryDt;
		this.chalCrtdDt = chalCrtdDt;
	}

	/** full constructor */
	public IpChallenge(Long chalId, IpChallengeStatus ipChallengeStatus, IpChallengeCat ipChallengeCat, IpUser ipUser, String chalTitle, String chalDesc, String chalHoverTxt, Date chalLaunchDt, Date chalExpiryDt, String chalTags, Date chalCrtdDt, Set ipChallengeGroups, Set ipSolutions) {
		this.chalId = chalId;
		this.ipChallengeStatus = ipChallengeStatus;
		this.ipChallengeCat = ipChallengeCat;
		this.ipUser = ipUser;
		this.chalTitle = chalTitle;
		this.chalDesc = chalDesc;
		this.chalHoverTxt = chalHoverTxt;
		this.chalLaunchDt = chalLaunchDt;
		this.chalExpiryDt = chalExpiryDt;
		this.chalTags = chalTags;
		this.chalCrtdDt = chalCrtdDt;
		this.ipChallengeGroups = ipChallengeGroups;
		this.ipSolutions = ipSolutions;
	}

	// Property accessors

	public Long getChalId() {
		return this.chalId;
	}

	public void setChalId(Long chalId) {
		this.chalId = chalId;
	}

	public IpChallengeStatus getIpChallengeStatus() {
		return this.ipChallengeStatus;
	}

	public void setIpChallengeStatus(IpChallengeStatus ipChallengeStatus) {
		this.ipChallengeStatus = ipChallengeStatus;
	}

	public IpChallengeCat getIpChallengeCat() {
		return this.ipChallengeCat;
	}

	public void setIpChallengeCat(IpChallengeCat ipChallengeCat) {
		this.ipChallengeCat = ipChallengeCat;
	}

	public IpUser getIpUser() {
		return this.ipUser;
	}

	public void setIpUser(IpUser ipUser) {
		this.ipUser = ipUser;
	}

	public String getChalTitle() {
		return this.chalTitle;
	}

	public void setChalTitle(String chalTitle) {
		this.chalTitle = chalTitle;
	}

	public String getChalDesc() {
		return this.chalDesc;
	}

	public void setChalDesc(String chalDesc) {
		this.chalDesc = chalDesc;
	}

	public String getChalHoverTxt() {
		return this.chalHoverTxt;
	}

	public void setChalHoverTxt(String chalHoverTxt) {
		this.chalHoverTxt = chalHoverTxt;
	}

	public Date getChalLaunchDt() {
		return this.chalLaunchDt;
	}

	public void setChalLaunchDt(Date chalLaunchDt) {
		this.chalLaunchDt = chalLaunchDt;
	}

	public Date getChalExpiryDt() {
		return this.chalExpiryDt;
	}

	public void setChalExpiryDt(Date chalExpiryDt) {
		this.chalExpiryDt = chalExpiryDt;
	}

	public String getChalTags() {
		return this.chalTags;
	}

	public void setChalTags(String chalTags) {
		this.chalTags = chalTags;
	}

	public Date getChalCrtdDt() {
		return this.chalCrtdDt;
	}

	public void setChalCrtdDt(Date chalCrtdDt) {
		this.chalCrtdDt = chalCrtdDt;
	}

	public Set getIpChallengeGroups() {
		return this.ipChallengeGroups;
	}

	public void setIpChallengeGroups(Set ipChallengeGroups) {
		this.ipChallengeGroups = ipChallengeGroups;
	}

	public Set getIpSolutions() {
		return this.ipSolutions;
	}

	public void setIpSolutions(Set ipSolutions) {
		this.ipSolutions = ipSolutions;
	}

}