package za.co.idea.ip.orm.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 * IpBlob entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_blob", catalog = "lpdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "getBlobByEntity", query = "select ib.* from ip_blob ib where ib.blob_entity_id=:id and lower(ib.blob_entity_tbl_nm)=lower(:tblNm)", resultClass = IpBlob.class), @NamedNativeQuery(name = "deleteBlobByEntity", query = "delete from ip_blob where blob_entity_id=:id and lower(blob_entity_tbl_nm)=lower(:tblNm)", resultClass = IpBlob.class), @NamedNativeQuery(name = "getBlobIdByEntity", query = "select ib.* from ip_blob ib where ib.blob_entity_id=:id and lower(ib.blob_entity_tbl_nm)=lower(:tblNm)", resultClass = IpBlob.class) })
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
	@Id
	@Column(name = "blob_id", unique = true, nullable = false)
	public Long getBlobId() {
		return this.blobId;
	}

	public void setBlobId(Long blobId) {
		this.blobId = blobId;
	}

	@Column(name = "blob_name", length = 999)
	public String getBlobName() {
		return this.blobName;
	}

	public void setBlobName(String blobName) {
		this.blobName = blobName;
	}

	@Column(name = "blob_content_type", length = 999)
	public String getBlobContentType() {
		return this.blobContentType;
	}

	public void setBlobContentType(String blobContentType) {
		this.blobContentType = blobContentType;
	}

	@Column(name = "blob_content")
	public String getBlobContent() {
		return this.blobContent;
	}

	public void setBlobContent(String blobContent) {
		this.blobContent = blobContent;
	}

	@Column(name = "blob_entity_id")
	public Long getBlobEntityId() {
		return this.blobEntityId;
	}

	public void setBlobEntityId(Long blobEntityId) {
		this.blobEntityId = blobEntityId;
	}

	@Column(name = "blob_entity_tbl_nm", length = 450)
	public String getBlobEntityTblNm() {
		return this.blobEntityTblNm;
	}

	public void setBlobEntityTblNm(String blobEntityTblNm) {
		this.blobEntityTblNm = blobEntityTblNm;
	}

	@Column(name = "blob_size")
	public Long getBlobSize() {
		return this.blobSize;
	}

	public void setBlobSize(Long blobSize) {
		this.blobSize = blobSize;
	}

	@Column(name = "blob_crtd_dt", nullable = false, length = 19)
	public Date getBlobCrtdDt() {
		return this.blobCrtdDt;
	}

	public void setBlobCrtdDt(Date blobCrtdDt) {
		this.blobCrtdDt = blobCrtdDt;
	}

}