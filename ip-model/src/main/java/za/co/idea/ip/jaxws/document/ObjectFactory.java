
package za.co.idea.ip.jaxws.document;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the za.co.idea.ip.jaxws.document package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ListDocumentRsMsg_QNAME = new QName("http://za.co.idea/document/", "listDocumentRsMsg");
    private final static QName _DownloadDocumentRqMsg_QNAME = new QName("http://za.co.idea/document/", "downloadDocumentRqMsg");
    private final static QName _DownloadDocumentRsMsg_QNAME = new QName("http://za.co.idea/document/", "downloadDocumentRsMsg");
    private final static QName _ListDocumentRqMsg_QNAME = new QName("http://za.co.idea/document/", "listDocumentRqMsg");
    private final static QName _UploadDocumentRsMsg_QNAME = new QName("http://za.co.idea/document/", "uploadDocumentRsMsg");
    private final static QName _UploadDocumentRqMsg_QNAME = new QName("http://za.co.idea/document/", "uploadDocumentRqMsg");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: za.co.idea.ip.jaxws.document
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListDocumentRs }
     * 
     */
    public ListDocumentRs createListDocumentRs() {
        return new ListDocumentRs();
    }

    /**
     * Create an instance of {@link DownloadDocumentRq }
     * 
     */
    public DownloadDocumentRq createDownloadDocumentRq() {
        return new DownloadDocumentRq();
    }

    /**
     * Create an instance of {@link DownloadDocumentRs }
     * 
     */
    public DownloadDocumentRs createDownloadDocumentRs() {
        return new DownloadDocumentRs();
    }

    /**
     * Create an instance of {@link ListDocumentRq }
     * 
     */
    public ListDocumentRq createListDocumentRq() {
        return new ListDocumentRq();
    }

    /**
     * Create an instance of {@link UploadDocumentRs }
     * 
     */
    public UploadDocumentRs createUploadDocumentRs() {
        return new UploadDocumentRs();
    }

    /**
     * Create an instance of {@link UploadDocumentRq }
     * 
     */
    public UploadDocumentRq createUploadDocumentRq() {
        return new UploadDocumentRq();
    }

    /**
     * Create an instance of {@link Documents }
     * 
     */
    public Documents createDocuments() {
        return new Documents();
    }

    /**
     * Create an instance of {@link Document }
     * 
     */
    public Document createDocument() {
        return new Document();
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListDocumentRs }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://za.co.idea/document/", name = "listDocumentRsMsg")
    public JAXBElement<ListDocumentRs> createListDocumentRsMsg(ListDocumentRs value) {
        return new JAXBElement<ListDocumentRs>(_ListDocumentRsMsg_QNAME, ListDocumentRs.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DownloadDocumentRq }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://za.co.idea/document/", name = "downloadDocumentRqMsg")
    public JAXBElement<DownloadDocumentRq> createDownloadDocumentRqMsg(DownloadDocumentRq value) {
        return new JAXBElement<DownloadDocumentRq>(_DownloadDocumentRqMsg_QNAME, DownloadDocumentRq.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DownloadDocumentRs }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://za.co.idea/document/", name = "downloadDocumentRsMsg")
    public JAXBElement<DownloadDocumentRs> createDownloadDocumentRsMsg(DownloadDocumentRs value) {
        return new JAXBElement<DownloadDocumentRs>(_DownloadDocumentRsMsg_QNAME, DownloadDocumentRs.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListDocumentRq }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://za.co.idea/document/", name = "listDocumentRqMsg")
    public JAXBElement<ListDocumentRq> createListDocumentRqMsg(ListDocumentRq value) {
        return new JAXBElement<ListDocumentRq>(_ListDocumentRqMsg_QNAME, ListDocumentRq.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadDocumentRs }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://za.co.idea/document/", name = "uploadDocumentRsMsg")
    public JAXBElement<UploadDocumentRs> createUploadDocumentRsMsg(UploadDocumentRs value) {
        return new JAXBElement<UploadDocumentRs>(_UploadDocumentRsMsg_QNAME, UploadDocumentRs.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadDocumentRq }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://za.co.idea/document/", name = "uploadDocumentRqMsg")
    public JAXBElement<UploadDocumentRq> createUploadDocumentRqMsg(UploadDocumentRq value) {
        return new JAXBElement<UploadDocumentRq>(_UploadDocumentRqMsg_QNAME, UploadDocumentRq.class, null, value);
    }

}
