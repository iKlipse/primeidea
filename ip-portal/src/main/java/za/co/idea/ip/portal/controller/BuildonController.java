package za.co.idea.ip.portal.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;
import org.apache.log4j.Logger;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudModel;

import za.co.idea.ip.portal.bean.TagBean;
import za.co.idea.ip.portal.util.IdNumberGen;
import za.co.idea.ip.portal.util.RESTServiceHelper;
import za.co.idea.ip.ws.bean.AttachmentMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;
import za.co.idea.ip.ws.bean.TagMessage;
import za.co.idea.ip.ws.util.CustomObjectMapper;

@ManagedBean(name = "buildonController")
@SessionScoped
public class BuildonController implements Serializable {
	private static final Logger logger = Logger.getLogger(BuildonController.class);
	private static final long serialVersionUID = -6184166952920477924L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-portal");
	private static final IdNumberGen COUNTER = new IdNumberGen();
	private List<TagBean> buildons;
	private TagBean buildon;
	private Long entityId;
	private int entityType;
	private TagCloudModel buildonLikes;
	private List<TagBean> buildonComments;
	private String buildonLikeCnt;
	private String buildonCommentCnt;
	private boolean showBuildonComments;
	private boolean showBuildonLikes;
	private String commentText;
	private StreamedContent image;
	private String fileName;
	private String contentType;
	private StreamedContent fileContent;
	private boolean taggable;
	private boolean fileAvail;
	private Long userId;
	private String returnView;

	private WebClient createCustomClient(String url) {
		WebClient client = WebClient.create(url, Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
		client.header("Content-Type", "application/json");
		client.header("Accept", "application/json");
		return client;
	}

	public String showBuildon() {
		Map<String, String> reqMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		entityId = Long.valueOf(reqMap.get("entityId"));
		entityType = Integer.parseInt(reqMap.get("entityType"));
		taggable = Boolean.parseBoolean(reqMap.get("taggable"));
		buildons = RESTServiceHelper.fetchAllBuildOns(entityId, entityType);
		buildon = new TagBean();
		commentText = "";
		return "bovw";
	}

	public String redirectView() {
		switch (Integer.valueOf(returnView)) {
		case 1:
			return "ideas";
		case 2:
			return "ideaso";
		case 3:
			return "ideasr";
		case 4:
			return "sols";
		case 5:
			return "solso";
		case 6:
			return "solsr";
		default:
			return "";
		}
	}

	public String showSummaryBuildon() {
		buildonLikes = fetchAllBuildonLikes();
		buildonComments = fetchAllBuildonComments();
		buildonLikeCnt = "(" + buildonLikes.getTags().size() + ")	";
		buildonCommentCnt = "(" + buildonComments.size() + ")	";
		showBuildonComments = false;
		showBuildonLikes = false;
		try {
			WebClient getBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/getId/" + buildon.getTagId() + "/ip_tag");
			Long blobId = getBlobClient.accept(MediaType.APPLICATION_JSON).get(Long.class);
			getBlobClient.close();
			if (blobId != -999l) {
				WebClient getBlobNameClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/getName/" + blobId);
				String blobName = getBlobNameClient.accept(MediaType.APPLICATION_JSON).get(String.class);
				getBlobNameClient.close();
				WebClient client = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/download/" + blobId + "/" + blobName, Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
				client.header("Content-Type", "application/json");
				client.header("Accept", MediaType.MULTIPART_FORM_DATA);
				Attachment attachment = client.accept(MediaType.MULTIPART_FORM_DATA).get(Attachment.class);
				if (attachment != null) {
					fileAvail = false;
					WebClient getBlobTypeClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/getContentType/" + blobId);
					String blobType = getBlobTypeClient.accept(MediaType.APPLICATION_JSON).get(String.class);
					getBlobTypeClient.close();
					fileContent = new DefaultStreamedContent(attachment.getDataHandler().getInputStream(), blobType, blobName);
				} else {
					fileAvail = true;
					fileContent = null;
				}
			} else {
				fileAvail = true;
				fileContent = null;
			}
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			fileAvail = true;
			fileContent = null;
		}
		return "bosw";
	}

	public String likeBuildon() {
		WebClient addTagClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ts/tag/add");
		TagMessage message = new TagMessage();
		message.setEntityId(buildon.getTagId());
		message.setTagId(COUNTER.getNextId("IpTag"));
		message.setTeId(5);
		message.setTtId(1);
		message.setUserId(userId);
		message.setDuplicate(false);
		ResponseMessage response = addTagClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		addTagClient.close();
		if (response.getStatusCode() != 0 && response.getStatusCode() != 2)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Like", "Error While Saving Like"));
		buildonLikes = fetchAllBuildonLikes();
		buildonLikeCnt = "(" + buildonLikes.getTags().size() + ")	";
		showBuildonComments = false;
		showBuildonLikes = true;
		return "";
	}

	public void commentBuildon() {
		WebClient addTagClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ts/tag/add");
		TagMessage message = new TagMessage();
		message.setEntityId(buildon.getTagId());
		message.setTagId(COUNTER.getNextId("IpTag"));
		message.setTagText(commentText);
		message.setTeId(5);
		message.setTtId(2);
		message.setDuplicate(true);
		message.setUserId(userId);
		ResponseMessage response = addTagClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		addTagClient.close();
		if (response.getStatusCode() != 0)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Comment", "Error While Saving Comment"));
		buildonComments = fetchAllBuildonComments();
		buildonCommentCnt = "(" + buildonComments.size() + ")	";
		commentText = "";
		showBuildonComments = true;
		showBuildonLikes = false;
	}

	public String createBuildOn() {
		WebClient addTagClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ts/tag/add");
		TagMessage message = new TagMessage();
		message.setEntityId(entityId);
		message.setTagId(COUNTER.getNextId("IpTag"));
		message.setTagText(buildon.getTagText());
		message.setTeId(entityType);
		message.setTtId(3);
		message.setDuplicate(true);
		message.setUserId(userId);
		ResponseMessage response = addTagClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		addTagClient.close();
		if (response.getStatusCode() == 0) {
			if (image != null) {
				WebClient createBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/create");
				AttachmentMessage attach = new AttachmentMessage();
				attach.setBlobContentType(contentType);
				attach.setBlobEntityId(message.getTagId());
				attach.setBlobEntityTblNm("ip_tag");
				attach.setBlobName(fileName);
				attach.setBlobId(COUNTER.getNextId("IpBlob"));
				Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(attach);
				if (crtRes.getStatus() == 200) {
					WebClient client = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/upload/" + attach.getBlobId().toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
					client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
					client.header("Accept", "application/json");
					Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(attach.getBlobId().toString(), image.getStream(), new ContentDisposition("attachment;;filename=sample.png")));
					if (docRes.getStatus() != 200) {
						FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attachment Upload has Failed.", "Attachment Upload has Failed.");
						FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
					}
					client.close();
				} else {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Attachment Upload has Failed.", "Attachment Upload has Failed.");
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
				createBlobClient.close();
			}
		} else
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Build-On", "Error While Saving Build-On"));
		buildon.setTagText("");
		fileContent = null;
		buildons = RESTServiceHelper.fetchAllBuildOns(entityId, entityType);
		return "";
	}

	private TagCloudModel fetchAllBuildonLikes() {
		TagCloudModel likes = new DefaultTagCloudModel();
		WebClient fetchBuildonLikesClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ts/tag/get/" + buildon.getTagId() + "/5/1");
		Collection<? extends TagMessage> likeList = new ArrayList<TagMessage>(fetchBuildonLikesClient.accept(MediaType.APPLICATION_JSON).getCollection(TagMessage.class));
		fetchBuildonLikesClient.close();
		for (TagMessage tagMessage : likeList)
			likes.addTag(new DefaultTagCloudItem(tagMessage.getUsrScreenName(), 1));
		return likes;
	}

	private List<TagBean> fetchAllBuildonComments() {
		WebClient fetchBuildonCommentsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ts/tag/get/" + buildon.getTagId() + "/5/2");
		Collection<? extends TagMessage> msgs = new ArrayList<TagMessage>(fetchBuildonCommentsClient.accept(MediaType.APPLICATION_JSON).getCollection(TagMessage.class));
		fetchBuildonCommentsClient.close();
		List<TagBean> ret = new ArrayList<TagBean>();
		for (TagMessage msg : msgs) {
			TagBean bean = new TagBean();
			bean.setTagText(msg.getTagText());
			bean.setUsrScreenName(msg.getUsrScreenName());
			bean.setTagDate(msg.getTagDate());
			ret.add(bean);
		}
		return ret;
	}

	public void fileUploadHandle(FileUploadEvent fue) {
		try {
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

	public List<TagBean> getBuildons() {
		if (buildons == null)
			buildons = new ArrayList<TagBean>();
		return buildons;
	}

	public void setBuildons(List<TagBean> buildons) {
		this.buildons = buildons;
	}

	public TagBean getBuildon() {
		if (buildon == null)
			buildon = new TagBean();
		return buildon;
	}

	public void setBuildon(TagBean buildon) {
		this.buildon = buildon;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public int getEntityType() {
		return entityType;
	}

	public void setEntityType(int entityType) {
		this.entityType = entityType;
	}

	public TagCloudModel getBuildonLikes() {
		return buildonLikes;
	}

	public void setBuildonLikes(TagCloudModel buildonLikes) {
		this.buildonLikes = buildonLikes;
	}

	public List<TagBean> getBuildonComments() {
		if (buildonComments == null)
			buildonComments = new ArrayList<TagBean>();
		return buildonComments;
	}

	public void setBuildonComments(List<TagBean> buildonComments) {
		this.buildonComments = buildonComments;
	}

	public String getBuildonLikeCnt() {
		if (buildonLikeCnt == null)
			buildonLikeCnt = "";
		return buildonLikeCnt;
	}

	public void setBuildonLikeCnt(String buildonLikeCnt) {
		this.buildonLikeCnt = buildonLikeCnt;
	}

	public String getBuildonCommentCnt() {
		if (buildonCommentCnt == null)
			buildonCommentCnt = "";
		return buildonCommentCnt;
	}

	public void setBuildonCommentCnt(String buildonCommentCnt) {
		this.buildonCommentCnt = buildonCommentCnt;
	}

	public boolean isShowBuildonComments() {
		return showBuildonComments;
	}

	public void setShowBuildonComments(boolean showBuildonComments) {
		this.showBuildonComments = showBuildonComments;
	}

	public boolean isShowBuildonLikes() {
		return showBuildonLikes;
	}

	public void setShowBuildonLikes(boolean showBuildonLikes) {
		this.showBuildonLikes = showBuildonLikes;
	}

	public String getCommentText() {
		if (commentText == null)
			commentText = "";
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public StreamedContent getImage() {
		return image;
	}

	public void setImage(StreamedContent image) {
		this.image = image;
	}

	public String getFileName() {
		if (fileName == null)
			fileName = "";
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		if (contentType == null)
			contentType = "";
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

	public boolean isTaggable() {
		return taggable;
	}

	public void setTaggable(boolean taggable) {
		this.taggable = taggable;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getReturnView() {
		return returnView;
	}

	public void setReturnView(String returnView) {
		this.returnView = returnView;
	}

}
