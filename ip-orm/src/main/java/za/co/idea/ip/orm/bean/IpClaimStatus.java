package za.co.idea.ip.orm.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * IpClaimStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_claim_status", catalog = "lpdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "getNextClmStatus", query = "select iis.* from ip_claim_status iis where (iis.cs_id=:curr or iis.cs_id in (select st.tran_next_state from ip_state_tran st where	st.tran_curr_state=:curr and lower(st.tran_entity)='ip_claim_status'))", resultClass = IpClaimStatus.class), @NamedNativeQuery(name = "getNonAllocClaimStatus", query = "select iis.* from ip_claim_status iis where iis.cs_id not in (select ia.alloc_status_id from ip_allocation ia where lower(ia.alloc_entity)='ip_claim_status')", resultClass = IpClaimStatus.class) })
public class IpClaimStatus implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5951907640114110917L;
	private Integer csId;
	private String csDesc;
	private Set<IpClaim> ipClaims = new HashSet<IpClaim>(0);

	// Constructors

	/** default constructor */
	public IpClaimStatus() {
	}

	/** minimal constructor */
	public IpClaimStatus(Integer csId) {
		this.csId = csId;
	}

	/** full constructor */
	public IpClaimStatus(Integer csId, String csDesc, Set<IpClaim> ipClaims) {
		this.csId = csId;
		this.csDesc = csDesc;
		this.ipClaims = ipClaims;
	}

	// Property accessors
	@Id
	@Column(name = "cs_id", unique = true, nullable = false)
	public Integer getCsId() {
		return this.csId;
	}

	public void setCsId(Integer csId) {
		this.csId = csId;
	}

	@Column(name = "cs_desc", length = 45)
	public String getCsDesc() {
		return this.csDesc;
	}

	public void setCsDesc(String csDesc) {
		this.csDesc = csDesc;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ipClaimStatus")
	public Set<IpClaim> getIpClaims() {
		return this.ipClaims;
	}

	public void setIpClaims(Set<IpClaim> ipClaims) {
		this.ipClaims = ipClaims;
	}

}