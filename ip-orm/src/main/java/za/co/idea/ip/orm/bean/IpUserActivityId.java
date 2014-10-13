package za.co.idea.ip.orm.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * IpUserActivityId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class IpUserActivityId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7287812490996503490L;
	private String userFName;
	private String userLName;
	private String groupName;
	private Timestamp dta;
	private Timestamp dtb;
	private Long cntb;
	private BigDecimal cntc;

	// Constructors

	/** default constructor */
	public IpUserActivityId() {
	}

	/** minimal constructor */
	public IpUserActivityId(String userFName, String userLName, String groupName) {
		this.userFName = userFName;
		this.userLName = userLName;
		this.groupName = groupName;
	}

	/** full constructor */
	public IpUserActivityId(String userFName, String userLName,
			String groupName, Timestamp dta, Timestamp dtb, Long cntb,
			BigDecimal cntc) {
		this.userFName = userFName;
		this.userLName = userLName;
		this.groupName = groupName;
		this.dta = dta;
		this.dtb = dtb;
		this.cntb = cntb;
		this.cntc = cntc;
	}

	// Property accessors

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

	@Column(name = "group_name", nullable = false, length = 65535)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "dta", length = 19)
	public Timestamp getDta() {
		return this.dta;
	}

	public void setDta(Timestamp dta) {
		this.dta = dta;
	}

	@Column(name = "dtb", length = 19)
	public Timestamp getDtb() {
		return this.dtb;
	}

	public void setDtb(Timestamp dtb) {
		this.dtb = dtb;
	}

	@Column(name = "cntb")
	public Long getCntb() {
		return this.cntb;
	}

	public void setCntb(Long cntb) {
		this.cntb = cntb;
	}

	@Column(name = "cntc", precision = 32, scale = 0)
	public BigDecimal getCntc() {
		return this.cntc;
	}

	public void setCntc(BigDecimal cntc) {
		this.cntc = cntc;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof IpUserActivityId))
			return false;
		IpUserActivityId castOther = (IpUserActivityId) other;

		return ((this.getUserFName() == castOther.getUserFName()) || (this
				.getUserFName() != null && castOther.getUserFName() != null && this
				.getUserFName().equals(castOther.getUserFName())))
				&& ((this.getUserLName() == castOther.getUserLName()) || (this
						.getUserLName() != null
						&& castOther.getUserLName() != null && this
						.getUserLName().equals(castOther.getUserLName())))
				&& ((this.getGroupName() == castOther.getGroupName()) || (this
						.getGroupName() != null
						&& castOther.getGroupName() != null && this
						.getGroupName().equals(castOther.getGroupName())))
				&& ((this.getDta() == castOther.getDta()) || (this.getDta() != null
						&& castOther.getDta() != null && this.getDta().equals(
						castOther.getDta())))
				&& ((this.getDtb() == castOther.getDtb()) || (this.getDtb() != null
						&& castOther.getDtb() != null && this.getDtb().equals(
						castOther.getDtb())))
				&& ((this.getCntb() == castOther.getCntb()) || (this.getCntb() != null
						&& castOther.getCntb() != null && this.getCntb()
						.equals(castOther.getCntb())))
				&& ((this.getCntc() == castOther.getCntc()) || (this.getCntc() != null
						&& castOther.getCntc() != null && this.getCntc()
						.equals(castOther.getCntc())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserFName() == null ? 0 : this.getUserFName().hashCode());
		result = 37 * result
				+ (getUserLName() == null ? 0 : this.getUserLName().hashCode());
		result = 37 * result
				+ (getGroupName() == null ? 0 : this.getGroupName().hashCode());
		result = 37 * result
				+ (getDta() == null ? 0 : this.getDta().hashCode());
		result = 37 * result
				+ (getDtb() == null ? 0 : this.getDtb().hashCode());
		result = 37 * result
				+ (getCntb() == null ? 0 : this.getCntb().hashCode());
		result = 37 * result
				+ (getCntc() == null ? 0 : this.getCntc().hashCode());
		return result;
	}

}