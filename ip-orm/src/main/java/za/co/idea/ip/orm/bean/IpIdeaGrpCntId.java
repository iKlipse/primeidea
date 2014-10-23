package za.co.idea.ip.orm.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * IpIdeaGrpCntId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class IpIdeaGrpCntId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7275848944654807588L;
	private String groupName;
	private Long cnta;
	private Long cntb;
	private Long cntc;
	private Long cntd;

	// Constructors

	/** default constructor */
	public IpIdeaGrpCntId() {
	}

	/** minimal constructor */
	public IpIdeaGrpCntId(String groupName) {
		this.groupName = groupName;
	}

	/** full constructor */
	public IpIdeaGrpCntId(String groupName, Long cnta, Long cntb, Long cntc,
			Long cntd) {
		this.groupName = groupName;
		this.cnta = cnta;
		this.cntb = cntb;
		this.cntc = cntc;
		this.cntd = cntd;
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

	@Column(name = "cntc")
	public Long getCntc() {
		return this.cntc;
	}

	public void setCntc(Long cntc) {
		this.cntc = cntc;
	}

	@Column(name = "cntd")
	public Long getCntd() {
		return this.cntd;
	}

	public void setCntd(Long cntd) {
		this.cntd = cntd;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof IpIdeaGrpCntId))
			return false;
		IpIdeaGrpCntId castOther = (IpIdeaGrpCntId) other;

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
				&& ((this.getCntd() == castOther.getCntd()) || (this.getCntd() != null
						&& castOther.getCntd() != null && this.getCntd()
						.equals(castOther.getCntd())));
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
				+ (getCntd() == null ? 0 : this.getCntd().hashCode());
		return result;
	}

}