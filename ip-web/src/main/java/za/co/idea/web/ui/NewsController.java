package za.co.idea.web.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

import za.co.idea.ip.ws.bean.AttachmentMessage;
import za.co.idea.ip.ws.bean.NewsMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;
import za.co.idea.ip.ws.util.CustomObjectMapper;
import za.co.idea.web.ui.bean.NewsBean;
import za.co.idea.web.util.IdNumberGen;

public class NewsController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8898258487836934087L;
	private NewsBean newsBean;
	private StreamedContent image;
	private String fileName;
	private String contentType;
	private StreamedContent fileContent;
	private boolean fileAvail;
	private List<NewsBean> viewNewsBeans;
	private static final IdNumberGen COUNTER = new IdNumberGen();
	private static final Logger logger = Logger.getLogger(NewsController.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private WebClient createCustomClient(String url) {
		List providers = new ArrayList();
		providers.add(new JacksonJaxbJsonProvider(new CustomObjectMapper(), JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS));
		WebClient client = WebClient.create(url, providers);
		client.header("Content-Type", "application/json");
		client.header("Accept", "application/json");
		return client;
	}

	public String showCreateNews() {
		try {
			newsBean = new NewsBean();
			return "nscc";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform news create request", "System error occurred, cannot perform news create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showViewNews() {
		try {
			viewNewsBeans = fetchAllNews();
			return "nsvc";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform news view request", "System error occurred, cannot perform news view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showEditNews() {
		try {
			logger.debug("In showEditNew method");
			logger.info("Sending request to NewsService");
			WebClient getBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getId/" + newsBean.getnId() + "/ip_news");
			Long blobId = getBlobClient.accept(MediaType.APPLICATION_JSON).get(Long.class);
			getBlobClient.close();
			logger.info("After gettin response from NewsService");
			if (blobId != -999l) {
				WebClient getBlobNameClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getName/" + blobId);
				String blobName = getBlobNameClient.accept(MediaType.APPLICATION_JSON).get(String.class);
				getBlobNameClient.close();
				WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/download/" + blobId + "/" + blobName, Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
				client.header("Content-Type", "application/json");
				client.header("Accept", MediaType.MULTIPART_FORM_DATA);
				Attachment attachment = client.accept(MediaType.MULTIPART_FORM_DATA).get(Attachment.class);
				if (attachment != null) {
					fileAvail = false;
					fileContent = new DefaultStreamedContent(attachment.getDataHandler().getInputStream());
				} else {
					fileAvail = true;
					fileContent = null;
				}
			} else {
				fileAvail = true;
				fileContent = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Error while displaying edit news form: " + e.getMessage());
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			fileAvail = true;
			fileContent = null;
		}
		return "nsec";
	}

	public String showSummaryNews() {
		try {
			logger.debug("In showSummaryNews method");
			viewNewsBeans = fetchAllNews();
			logger.info("News details after fetching data from fetchALLNews(): " + viewNewsBeans);
			return "nssc";
		} catch (Exception e) {
			logger.debug("Error while displaying news summary details: " + e.getMessage());
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform news view request", "System error occurred, cannot perform news view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String saveNews() {
		try {
			logger.debug("Control handled in saveNews method");
			WebClient addNewsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ns/news/add");
			NewsMessage message = new NewsMessage();
			message.setnTitle(newsBean.getnTitle());
			message.setContent(newsBean.getnContent());
			message.setnId(COUNTER.getNextId("IpNews"));
			message.setStartDate(newsBean.getnStartDate());
			message.setEndDate(newsBean.getnEndDate());
			ResponseMessage response = addNewsClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
			addNewsClient.close();
			logger.info("dispalying response of save news action : " + response.getStatusCode());
			if (response.getStatusCode() == 0) {
				if (image != null) {
					logger.info("Before adding file content details");
					WebClient createBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/create");
					AttachmentMessage attach = new AttachmentMessage();
					attach.setBlobContentType(contentType);
					attach.setBlobEntityId(message.getnId());
					attach.setBlobEntityTblNm("ip_news");
					attach.setBlobName(fileName);
					attach.setBlobId(COUNTER.getNextId("IpBlob"));
					Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(attach);
					logger.info("Reponse code of file attachment - " + crtRes.getStatus());
					if (crtRes.getStatus() == 200) {
						WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/upload/" + attach.getBlobId().toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
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
				return showViewNews();
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform news create request", "System error occurred, cannot perform news create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String updateNews() {
		try {
			WebClient updateNewsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ns/news/modify");
			NewsMessage message = new NewsMessage();
			message.setnId(newsBean.getnId());
			message.setnTitle(newsBean.getnTitle());
			message.setContent(newsBean.getnContent());
			message.setStartDate(newsBean.getnStartDate());
			message.setEndDate(newsBean.getnEndDate());
			ResponseMessage response = updateNewsClient.accept(MediaType.APPLICATION_JSON).put(message, ResponseMessage.class);
			updateNewsClient.close();
			if (response.getStatusCode() == 0) {
				if (image != null) {
					WebClient getBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getId/" + newsBean.getnId() + "/ip_news");
					Long blobId = getBlobClient.accept(MediaType.APPLICATION_JSON).get(Long.class);
					if (blobId == -999) {
						WebClient createBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/create");
						AttachmentMessage attach = new AttachmentMessage();
						attach.setBlobContentType(contentType);
						attach.setBlobEntityId(newsBean.getnId());
						attach.setBlobEntityTblNm("ip_news");
						attach.setBlobName(fileName);
						attach.setBlobId(COUNTER.getNextId("IpBlob"));
						Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(attach);
						if (crtRes.getStatus() == 200) {
							WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/upload/" + attach.getBlobId().toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
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
						WebClient updateBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/update");
						AttachmentMessage attach = new AttachmentMessage();
						attach.setBlobContentType(contentType);
						attach.setBlobEntityId(newsBean.getnId());
						attach.setBlobEntityTblNm("ip_news");
						attach.setBlobName(fileName);
						attach.setBlobId(blobId);
						Response updRes = updateBlobClient.accept(MediaType.APPLICATION_JSON).put(attach);
						updateBlobClient.close();
						if (updRes.getStatus() == 200) {
							WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/upload/" + blobId.toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
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
				return showViewNews();
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform update request", "System error occurred, cannot perform update request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	private List<NewsBean> fetchAllNews() {
		logger.debug("Control handled in fetchAllNews method of NewsController ");
		List<NewsBean> ret = new ArrayList<NewsBean>();
		WebClient fetchNewsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ns/news/list");
		Collection<? extends NewsMessage> news = new ArrayList<NewsMessage>(fetchNewsClient.accept(MediaType.APPLICATION_JSON).getCollection(NewsMessage.class));
		fetchNewsClient.close();
		logger.info("News data adding to List: ");
		for (NewsMessage message : news) {
			NewsBean bean = new NewsBean();
			bean.setnId(message.getnId());
			bean.setnContent(message.getContent());
			bean.setnEndDate(message.getEndDate());
			bean.setnStartDate(message.getStartDate());
			bean.setnTitle(message.getnTitle());
			bean.setNewsUrl(message.getNewsUrl());
			bean.setNwImgAvail(message.isNwImgAvail());
			ret.add(bean);
		}
		logger.info("News data displaying from List: " + ret);
		return ret;
	}

	public void fileUploadHandle(FileUploadEvent fue) {
		try {
			UploadedFile file = fue.getFile();
			this.image = new DefaultStreamedContent(file.getInputstream());
			this.fileName = file.getFileName();
			this.contentType = file.getContentType();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error occured while uploading file : " + e.getMessage());
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
}
