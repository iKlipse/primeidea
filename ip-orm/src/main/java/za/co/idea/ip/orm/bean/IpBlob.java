package za.co.idea.ip.orm.bean;

import java.util.Date;

/**
 * IpBlob entity. @author MyEclipse Persistence Tools
 */

public class IpBlob implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3569760958966375220L;
	private Long blobId;
	private String blobName;
	private String blobContentType;
	private String blobContent;
	private Long blobEntityId;
	private String blobEntityTblNm;
	private Long blobSize;
	private Date blobCrtdDt;

	// Constructors

	/** default constructor */
	public IpBlob() {
	}

	/** minimal constructor */
	public IpBlob(Long blobId, Date blobCrtdDt) {
		this.blobId = blobId;
		this.blobCrtdDt = blobCrtdDt;
	}

	/** full constructor */
	public IpBlob(Long blobId, String blobName, String blobContentType, String blobContent, Long blobEntityId, String blobEntityTblNm, Long blobSize, Date blobCrtdDt) {
		this.blobId = blobId;
		this.blobName = blobName;
		this.blobContentType = blobContentType;
		this.blobContent = blobContent;
		this.blobEntityId = blobEntityId;
		this.blobEntityTblNm = blobEntityTblNm;
		this.blobSize = blobSize;
		this.blobCrtdDt = blobCrtdDt;
	}

	// Property accessors

	public Long getBlobId() {
		return this.blobId;
	}

	public void setBlobId(Long blobId) {
		this.blobId = blobId;
	}

	public String getBlobName() {
		return this.blobName;
	}

	public void setBlobName(String blobName) {
		this.blobName = blobName;
	}

	public String getBlobContentType() {
		return this.blobContentType;
	}

	public void setBlobContentType(String blobContentType) {
		this.blobContentType = blobContentType;
	}

	public String getBlobContent() {
		return this.blobContent;
	}

	public void setBlobContent(String blobContent) {
		this.blobContent = blobContent;
	}

	public Long getBlobEntityId() {
		return this.blobEntityId;
	}

	public void setBlobEntityId(Long blobEntityId) {
		this.blobEntityId = blobEntityId;
	}

	public String getBlobEntityTblNm() {
		return this.blobEntityTblNm;
	}

	public void setBlobEntityTblNm(String blobEntityTblNm) {
		this.blobEntityTblNm = blobEntityTblNm;
	}

	public Long getBlobSize() {
		return this.blobSize;
	}

	public void setBlobSize(Long blobSize) {
		this.blobSize = blobSize;
	}

	public Date getBlobCrtdDt() {
		return this.blobCrtdDt;
	}

	public void setBlobCrtdDt(Date blobCrtdDt) {
		this.blobCrtdDt = blobCrtdDt;
	}

}