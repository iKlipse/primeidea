package za.co.idea.ip.jaxws.document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for downloadDocumentRs complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="downloadDocumentRs">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fileName" type="{http://za.co.idea/document/}fileName"/>
 *         &lt;element name="contentType" type="{http://za.co.idea/document/}contentType"/>
 *         &lt;element name="fileContent" type="{http://za.co.idea/document/}fileContent"/>
 *         &lt;element name="response" type="{http://za.co.idea/document/}response"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "downloadDocumentRs", propOrder = { "fileName", "contentType", "fileContent", "response" })
public class DownloadDocumentRs {

	@XmlElement(required = true)
	protected String fileName;
	@XmlElement(required = true)
	protected String contentType;
	@XmlElement(required = true)
	protected byte[] fileContent;
	@XmlElement(required = true)
	protected Response response;

	/**
	 * Gets the value of the fileName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the value of the fileName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFileName(String value) {
		this.fileName = value;
	}

	/**
	 * Gets the value of the contentType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * Sets the value of the contentType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContentType(String value) {
		this.contentType = value;
	}

	/**
	 * Gets the value of the fileContent property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public byte[] getFileContent() {
		return fileContent;
	}

	/**
	 * Sets the value of the fileContent property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFileContent(byte[] value) {
		this.fileContent = value;
	}

	/**
	 * Gets the value of the response property.
	 * 
	 * @return possible object is {@link Response }
	 * 
	 */
	public Response getResponse() {
		return response;
	}

	/**
	 * Sets the value of the response property.
	 * 
	 * @param value
	 *            allowed object is {@link Response }
	 * 
	 */
	public void setResponse(Response value) {
		this.response = value;
	}

}
