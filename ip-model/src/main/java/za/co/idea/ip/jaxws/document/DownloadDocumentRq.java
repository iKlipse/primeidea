
package za.co.idea.ip.jaxws.document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for downloadDocumentRq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="downloadDocumentRq">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entityId" type="{http://za.co.idea/document/}entityId"/>
 *         &lt;element name="entityTableName" type="{http://za.co.idea/document/}entityTableName"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "downloadDocumentRq", propOrder = {
    "entityId",
    "entityTableName"
})
public class DownloadDocumentRq {

    @XmlElement(required = true)
    protected String entityId;
    @XmlElement(required = true)
    protected String entityTableName;

    /**
     * Gets the value of the entityId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityId() {
        return entityId;
    }

    /**
     * Sets the value of the entityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityId(String value) {
        this.entityId = value;
    }

    /**
     * Gets the value of the entityTableName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityTableName() {
        return entityTableName;
    }

    /**
     * Sets the value of the entityTableName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityTableName(String value) {
        this.entityTableName = value;
    }

}
