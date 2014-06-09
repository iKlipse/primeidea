package za.co.idea.ip.ws.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "attachmentMessage")
public class AttachmentMessage {
	private Long blobId;
	private String blobName;
	private String blobContentType;
	private Long blobEntityId;
	private String blobEntityTblNm;

	public Long getBlobId() {
		return blobId;
	}

	public String getBlobName() {
		return blobName;
	}

	public String getBlobContentType() {
		return blobContentType;
	}

	public Long getBlobEntityId() {
		return blobEntityId;
	}

	public String getBlobEntityTblNm() {
		return blobEntityTblNm;
	}

	public void setBlobId(Long blobId) {
		this.blobId = blobId;
	}

	public void setBlobName(String blobName) {
		this.blobName = blobName;
	}

	public void setBlobContentType(String blobContentType) {
		this.blobContentType = blobContentType;
	}

	public void setBlobEntityId(Long blobEntityId) {
		this.blobEntityId = blobEntityId;
	}

	public void setBlobEntityTblNm(String blobEntityTblNm) {
		this.blobEntityTblNm = blobEntityTblNm;
	}

}
