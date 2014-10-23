package za.co.idea.ip.orm.bean;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * IpGroupUserActivityId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class IpGroupUserActivityId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1318156242759405848L;
	private String groupName;
	private Long cnta;
	private Long cntb;
	private BigDecimal cntc;
	private Long totcnt;

	// Constructors

	/** default constructor */
	public IpGroupUserActivityId() {
	}

	/** minimal constructor */
	public IpGroupUserActivityId(String groupName) {
		this.groupName = groupName;
	}

	/** full constructor */
	public IpGroupUserActivityId(String groupName, Long cnta, Long cntb,
			BigDecimal cntc, Long totcnt) {
		this.groupName = groupName;
		this.cnta = cnta;
		this.cntb = cntb;
		this.cntc = cntc;
		this.totcnt = totcnt;
	}

	// Property accessors

	@Column(name = "group_name", nullable = false, length = 65535)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "cnta")
	public Long getCnta() {
		return this.cnta;
	}

	public void setCnta(Long cnta) {
		this.cnta = cnta;
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

	@Column(name = "totcnt")
	public Long getTotcnt() {
		return this.totcnt;
	}

	public void setTotcnt(Long totcnt) {
		this.totcnt = totcnt;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof IpGroupUserActivityId))
			return false;
		IpGroupUserActivityId castOther = (IpGroupUserActivityId) other;

		return ((this.getGroupName() == castOther.getGroupName()) || (this
				.getGroupName() != null && castOther.getGroupName() != null && this
				.getGroupName().equals(castOther.getGroupName())))
				&& ((this.getCnta() == castOther.getCnta()) || (this.getCnta() != null
						&& castOther.getCnta() != null && this.getCnta()
						.equals(castOther.getCnta())))
				&& ((this.getCntb() == castOther.getCntb()) || (this.getCntb() != null
						&& castOther.getCntb() != null && this.getCntb()
						.equals(castOther.getCntb())))
				&& ((this.getCntc() == castOther.getCntc()) || (this.getCntc() != null
						&& castOther.getCntc() != null && this.getCntc()
						.equals(castOther.getCntc())))
				&& ((this.getTotcnt() == castOther.getTotcnt()) || (this
						.getTotcnt() != null && castOther.getTotcnt() != null && this
						.getTotcnt().equals(castOther.getTotcnt())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getGroupName() == null ? 0 : this.getGroupName().hashCode());
		result = 37 * result
				+ (getCnta() == null ? 0 : this.getCnta().hashCode());
		result = 37 * result
				+ (getCntb() == null ? 0 : this.getCntb().hashCode());
		result = 37 * result
				+ (getCntc() == null ? 0 : this.getCntc().hashCode());
		result = 37 * result
				+ (getTotcnt() == null ? 0 : this.getTotcnt().hashCode());
		return result;
	}

}