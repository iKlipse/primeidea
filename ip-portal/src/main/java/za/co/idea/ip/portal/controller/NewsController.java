package za.co.idea.ip.portal.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;
import org.apache.log4j.Logger;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import za.co.idea.ip.portal.bean.NewsBean;
import za.co.idea.ip.portal.util.IdNumberGen;
import za.co.idea.ip.portal.util.RESTServiceHelper;
import za.co.idea.ip.ws.bean.AttachmentMessage;
import za.co.idea.ip.ws.bean.NewsMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;
import za.co.idea.ip.ws.bean.UserMessage;
import za.co.idea.ip.ws.util.CustomObjectMapper;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;

@ManagedBean(name = "newsController")
@SessionScoped
public class NewsController implements Serializable {

	private static final long serialVersionUID = 8898258487836934087L;
	private static final IdNumberGen COUNTER = new IdNumberGen();
	private static final Logger logger = Logger.getLogger(NewsController.class);
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-portal");
	private NewsBean newsBean;
	private StreamedContent image;
	private String fileName;
	private String contentType;
	private StreamedContent fileContent;
	private boolean fileAvail;
	private List<NewsBean> viewNewsBeans;
	private boolean showCreateNews;
	private boolean showViewNews;
	private String returnView;
	private String toView;
	private AccessController controller;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private WebClient createCustomClient(String url) {
		logger.debug("Control handled in createCustomClient()");
		List providers = new ArrayList();
		providers.add(new JacksonJaxbJsonProvider(new CustomObjectMapper(), JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS));
		WebClient client = WebClient.create(url, providers);
		client.header("Content-Type", "application/json");
		client.header("Accept", "application/json");
		logger.info("Returning the web client from this method " + client);
		return client;
	}

	public void initializePage() {
		try {
			viewNewsBeans = RESTServiceHelper.fetchAllNews();
			showCreateNews = false;
			showViewNews = true;
			if (toView != null && Integer.valueOf(toView) != -1) {
				switch (Integer.valueOf(toView)) {
				case 1:
					viewNewsBeans = RESTServiceHelper.fetchAllNews();
					showCreateNews = false;
					showViewNews = true;
					break;
				case 2:
					showCreateNews = true;
					showViewNews = false;
				}
			}

		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public String redirectMain() {
		switch (Integer.valueOf(returnView)) {
		case 1:
			return "lani";
		case 2:
			return "adminv";
		default:
			return "";
		}
	}

	public void showCreateNews() {
		try {
			logger.debug("Control handled in createCustomClient()");
			newsBean = new NewsBean();
			showCreateNews = true;
			showViewNews = false;
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform news create request", "System error occurred, cannot perform news create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void showViewNews() {
		try {
			logger.debug("Control handled in createCustomClient()");
			viewNewsBeans = RESTServiceHelper.fetchAllNews();
			showCreateNews = false;
			showViewNews = true;
		} catch (Exception e) {
			logger.error(e, e);

			logger.error("Error while displaying show view news form: " + e.getMessage());
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform news view request", "System error occurred, cannot perform news view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public String showEditNews() {
		try {
			logger.debug("Control handled in showEditNews() method");
			return "newse";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform edit request", "System error occurred, cannot perform edit request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			fileAvail = true;
			fileContent = null;
			return "";
		}
		
	}

	public String showSummaryNews() {
		try {
			logger.debug("In showSummaryNews method");
			viewNewsBeans = RESTServiceHelper.fetchAllNews();
			logger.info("News details after fetching data from fetchALLNews(): " + viewNewsBeans);
			return "nssc";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform news view request", "System error occurred, cannot perform news view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String saveNews() {
		try {
			logger.debug("Control handled in saveNews method");
			WebClient addNewsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ns/news/add");
			NewsMessage message = new NewsMessage();
			message.setnTitle(newsBean.getnTitle());
			message.setContent(newsBean.getnContent());
			message.setnId(COUNTER.getNextId("IpNews"));
			message.setStartDate(newsBean.getStartDate());
			message.setEndDate(newsBean.getEndDate());
			ResponseMessage response = addNewsClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
			addNewsClient.close();
			logger.info("dispalying response of save news action : " + response.getStatusCode());
			if (response.getStatusCode() == 0) {
				if (image != null) {
					logger.info("Before adding file content details");
					WebClient createBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/create");
					AttachmentMessage attach = new AttachmentMessage();
					attach.setBlobContentType(contentType);
					attach.setBlobEntityId(message.getnId());
					attach.setBlobEntityTblNm("ip_news");
					attach.setBlobName(fileName);
					attach.setBlobId(COUNTER.getNextId("IpBlob"));
					Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(attach);
					logger.info("Reponse code of file attachment - " + crtRes.getStatus());
					if (crtRes.getStatus() == 200) {
						WebClient client = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/upload/" + attach.getBlobId().toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
						client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
						client.header("Accept", "application/json");
						Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(attach.getBlobId().toString(), image.getStream(), new ContentDisposition("attachment;;filename=sample.png")));
						if (docRes.getStatus() != 200) {
							FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "News Attachment Not Uploaded", "News Attachment Not Uploaded");
							FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
						}
						client.close();
					} else {
						FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "News Attachment Not Uploaded", "News Attachment Not Uploaded");
						FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
					}
					createBlobClient.close();
				}
				return redirectMain();
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform news create request", "System error occurred, cannot perform news create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String updateNews() {
		try {
			logger.debug("Control handled in updateNews()");
			WebClient updateNewsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ns/news/modify");
			NewsMessage message = new NewsMessage();
			message.setnId(newsBean.getnId());
			message.setnTitle(newsBean.getnTitle());
			message.setContent(newsBean.getnContent());
			message.setStartDate(newsBean.getStartDate());
			message.setEndDate(newsBean.getEndDate());
			ResponseMessage response = updateNewsClient.accept(MediaType.APPLICATION_JSON).put(message, ResponseMessage.class);
			updateNewsClient.close();
			if (response.getStatusCode() == 0) {
				if (image != null) {
					WebClient getBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/getId/" + newsBean.getnId() + "/ip_news");
					Long blobId = getBlobClient.accept(MediaType.APPLICATION_JSON).get(Long.class);
					if (blobId == -999) {
						WebClient createBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/create");
						AttachmentMessage attach = new AttachmentMessage();
						attach.setBlobContentType(contentType);
						attach.setBlobEntityId(newsBean.getnId());
						attach.setBlobEntityTblNm("ip_news");
						attach.setBlobName(fileName);
						attach.setBlobId(COUNTER.getNextId("IpBlob"));
						Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(attach);
						if (crtRes.getStatus() == 200) {
							WebClient client = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/upload/" + attach.getBlobId().toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
							client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
							client.header("Accept", "application/json");
							Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(attach.getBlobId().toString(), image.getStream(), new ContentDisposition("attachment;;filename=sample.png")));
							if (docRes.getStatus() != 0) {
								FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "News Attachment Not Uploaded", "News Attachment Not Uploaded");
								FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
							}
							client.close();
						} else {
							FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "News Attachment Not Uploaded", "News Attachment Not Uploaded");
							FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
						}
						createBlobClient.close();
					} else {
						WebClient updateBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/update");
						AttachmentMessage attach = new AttachmentMessage();
						attach.setBlobContentType(contentType);
						attach.setBlobEntityId(newsBean.getnId());
						attach.setBlobEntityTblNm("ip_news");
						attach.setBlobName(fileName);
						attach.setBlobId(blobId);
						Response updRes = updateBlobClient.accept(MediaType.APPLICATION_JSON).put(attach);
						updateBlobClient.close();
						if (updRes.getStatus() == 200) {
							WebClient client = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/upload/" + blobId.toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
							client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
							client.header("Accept", "application/json");
							Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(blobId.toString(), image.getStream(), new ContentDisposition("attachment;filename=" + fileName)));
							if (docRes.getStatus() != 200) {
								FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Document Upload Failed", "Document Upload Failed");
								FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
							}
							client.close();
						} else {
							FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Document Upload Failed", "Document Upload Failed");
							FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
						}
					}
				}
				image = null;
				return redirectMain();
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform update request", "System error occurred, cannot perform update request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public void fileUploadHandle(FileUploadEvent fue) {
		try {
			logger.debug("Control handled in fileUploadHandle()");
			UploadedFile file = fue.getFile();
			this.image = new DefaultStreamedContent(file.getInputstream());
			this.fileName = file.getFileName();
			this.contentType = file.getContentType();
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public NewsBean getNewsBean() {
		return newsBean;
	}

	public void setNewsBean(NewsBean newsBean) {
		this.newsBean = newsBean;
	}

	public List<NewsBean> getViewNewsBeans() {
		return viewNewsBeans;
	}

	public void setViewNewsBeans(List<NewsBean> viewNewsBeans) {
		this.viewNewsBeans = viewNewsBeans;
	}

	public StreamedContent getImage() {
		return image;
	}

	public void setImage(StreamedContent image) {
		this.image = image;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public StreamedContent getFileContent() {
		return fileContent;
	}

	public void setFileContent(StreamedContent fileContent) {
		this.fileContent = fileContent;
	}

	public boolean isFileAvail() {
		return fileAvail;
	}

	public void setFileAvail(boolean fileAvail) {
		this.fileAvail = fileAvail;
	}

	public boolean isShowCreateNews() {
		return showCreateNews;
	}

	public void setShowCreateNews(boolean showCreateNews) {
		this.showCreateNews = showCreateNews;
	}

	public boolean isShowViewNews() {
		return showViewNews;
	}

	public void setShowViewNews(boolean showViewNews) {
		this.showViewNews = showViewNews;
	}

	public String getReturnView() {
		return returnView;
	}

	public void setReturnView(String returnView) {
		this.returnView = returnView;
	}

	public String getToView() {
		return toView;
	}

	public void setToView(String toView) {
		this.toView = toView;
	}

	public AccessController getController() {
		if (controller == null || controller.getFunctions() == null) {
			PortletRequest request = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			User user = (User) request.getAttribute(WebKeys.USER);
			WebClient client = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/verify/" + user.getScreenName());
			UserMessage message = client.accept(MediaType.APPLICATION_JSON).get(UserMessage.class);
			controller = new AccessController(message.getuId());
		}
		return controller;
	}

	public void setController(AccessController controller) {
		this.controller = controller;
	}

}
