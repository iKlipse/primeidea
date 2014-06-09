package za.co.idea.ip.jaxws.document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>
 * Java class for document complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="document">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entityId" type="{http://za.co.idea/document/}entityId"/>
 *         &lt;element name="entityTableName" type="{http://za.co.idea/document/}entityTableName"/>
 *         &lt;element name="fileName" type="{http://za.co.idea/document/}fileName" minOccurs="0"/>
 *         &lt;element name="contentType" type="{http://za.co.idea/document/}contentType" minOccurs="0"/>
 *         &lt;element name="fileContent" type="{http://za.co.idea/document/}fileContent"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "document", propOrder = { "entityId", "entityTableName", "fileName", "contentType", "fileContent" })
public class Document {

	@XmlElement(required = true)
	protected String entityId;
	@XmlElement(required = true)
	protected String entityTableName;
	protected String fileName;
	protected String contentType;
	@XmlElement(required = true, type = String.class)
	@XmlJavaTypeAdapter(HexBinaryAdapter.class)
	protected byte[] fileContent;

	/**
	 * Gets the value of the entityId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEntityId() {
		return entityId;
	}

	/**
	 * Sets the value of the entityId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEntityId(String value) {
		this.entityId = value;
	}

	/**
	 * Gets the value of the entityTableName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEntityTableName() {
		return entityTableName;
	}

	/**
	 * Sets the value of the entityTableName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEntityTableName(String value) {
		this.entityTableName = value;
	}

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

}
