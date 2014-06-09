package za.co.idea.ip.orm.bean;

import java.sql.Blob;

/**
 * IpBlob entity. @author MyEclipse Persistence Tools
 */

public class IpBlob implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 7784214388286520032L;
	private Long blobId;
	private String blobName;
	private String blobContentType;
	private Blob blobContent;
	private Long blobEntityId;
	private String blobEntityTblNm;

	// Constructors

	/** default constructor */
	public IpBlob() {
	}

	/** minimal constructor */
	public IpBlob(Long blobId) {
		this.blobId = blobId;
	}

	/** full constructor */
	public IpBlob(Long blobId, String blobName, String blobContentType, Blob blobContent, Long blobEntityId, String blobEntityTblNm) {
		this.blobId = blobId;
		this.blobName = blobName;
		this.blobContentType = blobContentType;
		this.blobContent = blobContent;
		this.blobEntityId = blobEntityId;
		this.blobEntityTblNm = blobEntityTblNm;
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

	public Blob getBlobContent() {
		return this.blobContent;
	}

	public void setBlobContent(Blob blobContent) {
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

}