
package za.co.idea.ip.jaxws.document;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="response">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respCode" type="{http://za.co.idea/document/}respCode"/>
 *         &lt;element name="respDesc" type="{http://za.co.idea/document/}respDesc"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "response", propOrder = {
    "respCode",
    "respDesc"
})
public class Response {

    @XmlElement(required = true)
    protected String respCode;
    @XmlElement(required = true)
    protected String respDesc;

    /**
     * Gets the value of the respCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRespCode() {
        return respCode;
    }

    /**
     * Sets the value of the respCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRespCode(String value) {
        this.respCode = value;
    }

    /**
     * Gets the value of the respDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRespDesc() {
        return respDesc;
    }

    /**
     * Sets the value of the respDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRespDesc(String value) {
        this.respDesc = value;
    }

}
