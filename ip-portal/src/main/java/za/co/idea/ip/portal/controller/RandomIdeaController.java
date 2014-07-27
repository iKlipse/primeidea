package za.co.idea.ip.portal.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.primefaces.component.tabview.Tab;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudModel;

import za.co.idea.ip.portal.bean.GroupBean;
import za.co.idea.ip.portal.bean.IdeaBean;
import za.co.idea.ip.portal.bean.ListSelectorBean;
import za.co.idea.ip.portal.bean.TagBean;
import za.co.idea.ip.portal.bean.UserBean;
import za.co.idea.ip.portal.util.FacesUtil;
import za.co.idea.ip.portal.util.IdNumberGen;
import za.co.idea.ip.portal.util.RESTServiceHelper;
import za.co.idea.ip.ws.bean.AttachmentMessage;
import za.co.idea.ip.ws.bean.IdeaMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;
import za.co.idea.ip.ws.bean.TagMessage;
import za.co.idea.ip.ws.util.CustomObjectMapper;

@ManagedBean(name = "randomIdeaController")
@SessionScoped
public class RandomIdeaController implements Serializable {

	private static final long serialVersionUID = 2409570255777650709L;
	private IdeaBean ideaBean;
	private List<UserBean> admUsers;
	private List<ListSelectorBean> ideaCats;
	private List<ListSelectorBean> ideaStatuses;
	private List<IdeaBean> viewIdeas;
	private TagCloudModel likes;
	private List<TagBean> comments;
	private List<TagBean> buildOns;
	private StreamedContent fileContent;
	private StreamedContent uploadContent;
	private List<GroupBean> pGrps;
	private DualListModel<GroupBean> groupTwinSelect;
	private String commentText;
	private String buildOnText;
	private boolean fileAvail;
	private boolean showIdeaComments;
	private boolean showIdeaLikes;
	private boolean showIdeaBuildOns;
	private String likeCnt;
	private String commentCnt;
	private String buildOnCnt;
	private boolean saveAsOpen;
	private boolean titleAvail;
	private int activeIndex;
	private static final IdNumberGen COUNTER = new IdNumberGen();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private WebClient createCustomClient(String url) {
		List providers = new ArrayList();
		providers.add(new JacksonJaxbJsonProvider(new CustomObjectMapper(), JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS));
		WebClient client = WebClient.create(url, providers);
		client.header("Content-Type", "application/json");
		client.header("Accept", "application/json");
		return client;
	}

	public void initializePage() {
		showViewIdeas();
		activeIndex = 0;
	}

	public void onTabChange(TabChangeEvent event) {
		Tab tab = event.getTab();
		if (tab.getId().equalsIgnoreCase("tbViewIdeas")) {
			showViewIdeas();
		} else if (tab.getId().equalsIgnoreCase("tbViewOpenIdeas")) {
			showViewOpenIdeas();
		} else if (tab.getId().equalsIgnoreCase("tbCreateIdeas")) {
			showCreateIdea();
		}
	}

	public void showViewIdeas() {
		try {
			viewIdeas = RESTServiceHelper.fetchAllIdeasByUser();
			ideaCats = RESTServiceHelper.fetchAllIdeaCat();
			admUsers = RESTServiceHelper.fetchAllUsers();
			ideaStatuses = RESTServiceHelper.fetchAllIdeaStatuses();
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void showViewOpenIdeas() {
		try {
			viewIdeas = RESTServiceHelper.fetchAllIdeasByStatusIdUserId(2);
			ideaCats = RESTServiceHelper.fetchAllIdeaCat();
			admUsers = RESTServiceHelper.fetchAllUsers();
			ideaStatuses = RESTServiceHelper.fetchAllIdeaStatuses();
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public String showViewIdeasByUser() {
		try {
			viewIdeas = RESTServiceHelper.fetchAllIdeasCreatedByUser();
			ideaCats = RESTServiceHelper.fetchAllIdeaCat();
			admUsers = RESTServiceHelper.fetchAllUsers();
			ideaStatuses = RESTServiceHelper.fetchAllIdeaStatuses();
			return "ideaui";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showEditIdea() {
		try {
			ideaCats = RESTServiceHelper.fetchAllIdeaCat();
			admUsers = RESTServiceHelper.fetchAllUsers();
			if (((Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId")).longValue() == 0l) {
				ideaStatuses = RESTServiceHelper.fetchAllIdeaStatuses();
			} else {
				ideaStatuses = RESTServiceHelper.fetchNextIdeaStatuses(ideaBean.getSetStatusId().intValue());
			}
			pGrps = RESTServiceHelper.fetchAllGroups();
			groupTwinSelect = initializeSelectedGroups(pGrps);
			try {
				WebClient getBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getId/" + ideaBean.getIdeaId() + "/ip_idea");
				Long blobId = getBlobClient.accept(MediaType.APPLICATION_JSON).get(Long.class);
				getBlobClient.close();
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
						ideaBean.setFileName(attachment.getContentDisposition().toString().replace("attachment;filename=", ""));
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
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				fileAvail = true;
				fileContent = null;
			}
			return "ideaei";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			fileAvail = true;
			fileContent = null;
			return "";
		}
	}

	public String showEditOpenIdea() {
		try {
			ideaCats = RESTServiceHelper.fetchAllIdeaCat();
			admUsers = RESTServiceHelper.fetchAllUsers();
			ideaStatuses = RESTServiceHelper.fetchNextIdeaStatuses(ideaBean.getSetStatusId().intValue());
			pGrps = RESTServiceHelper.fetchAllGroups();
			groupTwinSelect = initializeSelectedGroups(pGrps);
			try {
				WebClient getBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getId/" + ideaBean.getIdeaId() + "/ip_idea");
				Long blobId = getBlobClient.accept(MediaType.APPLICATION_JSON).get(Long.class);
				getBlobClient.close();
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
						ideaBean.setFileName(attachment.getContentDisposition().toString().replace("attachment;filename=", ""));
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
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				fileAvail = true;
				fileContent = null;
			}
			return "ideaeoi";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			fileAvail = true;
			fileContent = null;
			return "";
		}
	}

	public String showSummaryIdea() {
		likes = fetchAllLikes();
		comments = fetchAllComments();
		buildOns = fetchAllBuildOns();
		likeCnt = "(" + likes.getTags().size() + ")	";
		commentCnt = "(" + comments.size() + ")	";
		buildOnCnt = "(" + buildOns.size() + ")	";
		showIdeaComments = false;
		showIdeaLikes = false;
		showIdeaBuildOns = false;
		ideaBean.setTaggable(ideaBean.getSetStatusId() != 2);
		try {
			WebClient getBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getId/" + ideaBean.getIdeaId() + "/ip_idea");
			Long blobId = getBlobClient.accept(MediaType.APPLICATION_JSON).get(Long.class);
			getBlobClient.close();
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
					ideaBean.setFileName(attachment.getContentDisposition().toString().replace("attachment;filename=", ""));
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
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform summary request", "System error occurred, cannot perform summary request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			fileAvail = true;
			fileContent = null;
		}
		return "ideasi";
	}

	public String showSummaryOpenIdea() {
		likes = fetchAllLikes();
		comments = fetchAllComments();
		buildOns = fetchAllBuildOns();
		likeCnt = "(" + likes.getTags().size() + ")	";
		commentCnt = "(" + comments.size() + ")	";
		buildOnCnt = "(" + buildOns.size() + ")	";
		showIdeaComments = false;
		showIdeaLikes = false;
		showIdeaBuildOns = false;
		ideaBean.setTaggable(ideaBean.getSetStatusId() != 2);
		try {
			WebClient getBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getId/" + ideaBean.getIdeaId() + "/ip_idea");
			Long blobId = getBlobClient.accept(MediaType.APPLICATION_JSON).get(Long.class);
			getBlobClient.close();
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
					ideaBean.setFileName(attachment.getContentDisposition().toString().replace("attachment;filename=", ""));
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
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform summary request", "System error occurred, cannot perform summary request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			fileAvail = true;
			fileContent = null;
		}
		return "ideasoi";
	}

	public String showCommentIdea() {
		comments = fetchAllComments();
		showIdeaComments = true;
		showIdeaLikes = false;
		showIdeaBuildOns = false;
		return "";
	}

	public String showBuildOnIdea() {
		buildOns = fetchAllBuildOns();
		showIdeaComments = false;
		showIdeaLikes = false;
		showIdeaBuildOns = true;
		return "";
	}

	public void likeIdea() {
		WebClient addTagClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ts/tag/add");
		TagMessage message = new TagMessage();
		message.setEntityId(ideaBean.getIdeaId());
		message.setTagId(COUNTER.getNextId("IpTag"));
		message.setTeId(1);
		message.setTtId(1);
		// message.setUserId((Long)
		// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId"));
		message.setUserId(0l);
		message.setDuplicate(false);
		ResponseMessage response = addTagClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		addTagClient.close();
		if (response.getStatusCode() != 0 && response.getStatusCode() != 2)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Like", "Error While Saving Like"));
		likes = fetchAllLikes();
		likeCnt = "(" + likes.getTags().size() + ")	";
		showIdeaComments = false;
		showIdeaLikes = true;
		showIdeaBuildOns = false;
	}

	public void commentIdea() {
		WebClient addTagClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ts/tag/add");
		TagMessage message = new TagMessage();
		message.setEntityId(ideaBean.getIdeaId());
		message.setTagId(COUNTER.getNextId("IpTag"));
		message.setTagText(commentText);
		message.setTeId(1);
		message.setTtId(2);
		message.setDuplicate(true);
		// message.setUserId((Long)
		// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId"));
		message.setUserId(0l);
		ResponseMessage response = addTagClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		addTagClient.close();
		if (response.getStatusCode() != 0)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Comment", "Error While Saving Comment"));
		comments = fetchAllComments();
		commentCnt = "(" + comments.size() + ")	";
		commentText = "";
		showIdeaComments = true;
		showIdeaLikes = false;
		showIdeaBuildOns = false;
	}

	public String buildOnIdea() {
		WebClient addTagClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ts/tag/add");
		TagMessage message = new TagMessage();
		message.setEntityId(ideaBean.getIdeaId());
		message.setTagId(COUNTER.getNextId("IpTag"));
		message.setTagText(buildOnText);
		message.setTeId(1);
		message.setTtId(3);
		message.setDuplicate(true);
		// message.setUserId((Long)
		// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId"));
		message.setUserId(0l);
		ResponseMessage response = addTagClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		addTagClient.close();
		if (response.getStatusCode() != 0)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Build-On", "Error While Saving Build-On"));
		buildOns = fetchAllBuildOns();
		buildOnCnt = "(" + buildOns.size() + ")	";
		buildOnText = "";
		showIdeaComments = false;
		showIdeaLikes = false;
		showIdeaBuildOns = true;
		return "";
	}

	public void showCreateIdea() {
		try {
			ideaCats = RESTServiceHelper.fetchAllIdeaCat();
			saveAsOpen = false;
			admUsers = RESTServiceHelper.fetchAllUsers();
			ideaStatuses = RESTServiceHelper.fetchAllIdeaStatuses();
			ideaBean = new IdeaBean();
			pGrps = RESTServiceHelper.fetchAllGroups();
			groupTwinSelect = new DualListModel<GroupBean>(pGrps, new ArrayList<GroupBean>());
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create request", "System error occurred, cannot perform create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	// This method is used to validate Idea create and update form-Sharanya
	private List<String> validateIdea() {
		ArrayList<String> ret = new ArrayList<String>();
		if (ideaBean.getIdeaTitle() == null || ideaBean.getIdeaTitle().length() == 0) {
			ret.add("Title is Mandatory");
		} else if (!FacesUtil.lengthValidation(ideaBean.getIdeaTitle(), 1, 100)) {
			ret.add("Title sholud not exceed 100 characters");
		}
		if (ideaBean.getIdeaDesc() == null || ideaBean.getIdeaDesc().length() == 0) {
			ret.add("Description is Mandatory");
		}
		return ret;
	}

	// This method is used to check length validation-Sharanya
	public void checkTitleAvailability() {
		if (ideaBean.getIdeaTitle() == null || ideaBean.getIdeaTitle().length() == 0) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Enter Title to Check Availability", "Enter Title to Check Availability");
			FacesContext.getCurrentInstance().addMessage("txtITitle", exceptionMessage);
		}
		WebClient checkAvailablityClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/is/idea/check/title/" + ideaBean.getIdeaTitle());
		Boolean avail = checkAvailablityClient.accept(MediaType.APPLICATION_JSON).get(Boolean.class);
		checkAvailablityClient.close();
		titleAvail = avail.booleanValue();
		if (titleAvail) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Title Not Available", "Title Not Available");
			FacesContext.getCurrentInstance().addMessage("txtITitle", exceptionMessage);
		} else {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Title Available", "Title Available");
			FacesContext.getCurrentInstance().addMessage("txtITitle", exceptionMessage);
		}
	}

	public void saveIdea() {
		try {
			if (titleAvail) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Title Not Available", "Title Not Available");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			}
			List<String> errors = validateIdea();
			if (errors.size() > 0) {
				for (String error : errors) {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
			}
			WebClient addIdeaClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/is/idea/add");
			IdeaMessage ideaMessage = new IdeaMessage();
			// ideaMessage.setCrtdById((Long)
			// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId"));
			ideaMessage.setCrtdById(0l);
			ideaMessage.setCrtdDate(new Date());
			ideaMessage.setIdeaBa(ideaBean.getIdeaBa());
			ideaMessage.setIdeaDesc(ideaBean.getIdeaDesc());
			ideaMessage.setIdeaTag(ideaBean.getIdeaTag());
			ideaMessage.setIdeaId(COUNTER.getNextId("IpIdea"));
			ideaMessage.setIdeaTitle(ideaBean.getIdeaTitle());
			ideaMessage.setSelCatId(ideaBean.getSelCatId());
			ideaMessage.setGroupIdList(getSelGroupIds());
			if (saveAsOpen) {
				ideaMessage.setSetStatusId(2l);
			} else {
				ideaMessage.setSetStatusId(1l);
			}
			ResponseMessage response = addIdeaClient.accept(MediaType.APPLICATION_JSON).post(ideaMessage, ResponseMessage.class);
			addIdeaClient.close();
			if (response.getStatusCode() == 0) {
				if (uploadContent != null) {
					WebClient createBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/create");
					AttachmentMessage message = new AttachmentMessage();
					message.setBlobContentType(ideaBean.getContentType());
					message.setBlobEntityId(ideaMessage.getIdeaId());
					message.setBlobEntityTblNm("ip_idea");
					message.setBlobName(ideaBean.getFileName());
					message.setBlobId(COUNTER.getNextId("IpBlob"));
					Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(message);
					createBlobClient.close();
					if (crtRes.getStatus() == 200) {
						WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/upload/" + message.getBlobId(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
						client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
						client.header("Accept", "application/json");
						Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(message.getBlobId().toString(), uploadContent.getStream(), new ContentDisposition("attachment;filename=" + ideaBean.getFileName())));
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
				uploadContent = null;
				saveAsOpen = false;
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create Idea request", "System error occurred, cannot perform create Idea request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void updateIdea() {
		try {
			List<String> errors = validateIdea();
			if (errors.size() > 0) {
				for (String error : errors) {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
			}
			WebClient updateIdeaClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/is/idea/modify");
			IdeaMessage ideaMessage = new IdeaMessage();
			ideaMessage.setCrtdById(ideaBean.getCrtdById());
			ideaMessage.setCrtdDate(new Date());
			ideaMessage.setIdeaBa(ideaBean.getIdeaBa());
			ideaMessage.setIdeaDesc(ideaBean.getIdeaDesc());
			ideaMessage.setIdeaTag(ideaBean.getIdeaTag());
			ideaMessage.setIdeaId(ideaBean.getIdeaId());
			ideaMessage.setIdeaTitle(ideaBean.getIdeaTitle());
			ideaMessage.setSelCatId(ideaBean.getSelCatId());
			ideaMessage.setSetStatusId(ideaBean.getSetStatusId());
			ideaMessage.setGroupIdList(getSelGroupIds());
			ResponseMessage response = updateIdeaClient.accept(MediaType.APPLICATION_JSON).put(ideaMessage, ResponseMessage.class);
			updateIdeaClient.close();
			if (response.getStatusCode() == 0) {
				if (uploadContent != null) {
					WebClient getBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getId/" + ideaBean.getIdeaId() + "/ip_idea");
					Long blobId = getBlobClient.accept(MediaType.APPLICATION_JSON).get(Long.class);
					if (blobId == -999) {
						WebClient createBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/create");
						AttachmentMessage message = new AttachmentMessage();
						message.setBlobContentType(ideaBean.getContentType());
						message.setBlobEntityId(ideaBean.getIdeaId());
						message.setBlobEntityTblNm("ip_idea");
						message.setBlobName(ideaBean.getFileName());
						message.setBlobId(COUNTER.getNextId("IpBlob"));
						Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(message);
						if (crtRes.getStatus() == 200) {
							WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/upload/" + message.getBlobId(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
							client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
							client.header("Accept", "application/json");
							Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(message.getBlobId().toString(), uploadContent.getStream(), new ContentDisposition("attachment;filename=" + ideaBean.getFileName())));
							if (docRes.getStatus() != 200) {
								FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Document Upload Failed", "Document Upload Failed");
								FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
							}
						} else {
							FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Document Upload Failed", "Document Upload Failed");
							FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
						}
					} else {
						WebClient updateBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/update");
						AttachmentMessage message = new AttachmentMessage();
						message.setBlobContentType(ideaBean.getContentType());
						message.setBlobEntityId(ideaBean.getIdeaId());
						message.setBlobEntityTblNm("ip_idea");
						message.setBlobName(ideaBean.getFileName());
						message.setBlobId(blobId);
						Response updRes = updateBlobClient.accept(MediaType.APPLICATION_JSON).put(message);
						updateBlobClient.close();
						if (updRes.getStatus() == 200) {
							WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/upload/" + blobId.toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
							client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
							client.header("Accept", "application/json");
							Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(blobId.toString(), uploadContent.getStream(), new ContentDisposition("attachment;filename=" + ideaBean.getFileName())));
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
				uploadContent = null;
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform update Idea request", "System error occurred, cannot perform update Idea request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	private TagCloudModel fetchAllLikes() {
		TagCloudModel likes = new DefaultTagCloudModel();
		WebClient fetchIdeaLikesClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ts/tag/get/" + ideaBean.getIdeaId() + "/1/1");
		Collection<? extends TagMessage> likeList = new ArrayList<TagMessage>(fetchIdeaLikesClient.accept(MediaType.APPLICATION_JSON).getCollection(TagMessage.class));
		fetchIdeaLikesClient.close();
		for (TagMessage tagMessage : likeList)
			likes.addTag(new DefaultTagCloudItem(tagMessage.getUsrScreenName(), 1));
		return likes;
	}

	private List<TagBean> fetchAllComments() {
		WebClient fetchIdeaCommentsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ts/tag/get/" + ideaBean.getIdeaId() + "/1/2");
		Collection<? extends TagMessage> msgs = new ArrayList<TagMessage>(fetchIdeaCommentsClient.accept(MediaType.APPLICATION_JSON).getCollection(TagMessage.class));
		fetchIdeaCommentsClient.close();
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

	private List<TagBean> fetchAllBuildOns() {
		WebClient fetchIdeaBuildOnsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ts/tag/get/" + ideaBean.getIdeaId() + "/1/3");
		Collection<? extends TagMessage> msgs = new ArrayList<TagMessage>(fetchIdeaBuildOnsClient.accept(MediaType.APPLICATION_JSON).getCollection(TagMessage.class));
		fetchIdeaBuildOnsClient.close();
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
			this.uploadContent = new DefaultStreamedContent(file.getInputstream());
			this.ideaBean.setContentType(file.getContentType());
			this.ideaBean.setFileName(file.getFileName());
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform upload request", "System error occurred, cannot perform upload request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	private DualListModel<GroupBean> initializeSelectedGroups(List<GroupBean> grps) {
		List<Long> selGrps = ideaBean.getGroupIdList();
		DualListModel<GroupBean> ret = new DualListModel<GroupBean>(new ArrayList<GroupBean>(), new ArrayList<GroupBean>());
		if (selGrps != null)
			for (Long grpId : selGrps)
				ret.getTarget().add(RESTServiceHelper.getGroupById(grpId));
		if (grps != null)
			for (GroupBean bean : grps)
				if (selGrps != null && selGrps.contains(bean.getgId()))
					continue;
				else
					ret.getSource().add(bean);
		return ret;
	}

	private Long[] getSelGroupIds() {
		Long[] ret = new Long[groupTwinSelect.getTarget().size()];
		int i = 0;
		for (GroupBean bean : groupTwinSelect.getTarget()) {
			ret[i] = bean.getgId();
			i++;
		}
		return ret;
	}

	public IdeaBean getIdeaBean() {
		if (ideaBean == null)
			ideaBean = new IdeaBean();
		return ideaBean;
	}

	public void setIdeaBean(IdeaBean ideaBean) {
		this.ideaBean = ideaBean;
	}

	public List<IdeaBean> getViewIdeas() {
		if (viewIdeas == null)
			viewIdeas = new ArrayList<IdeaBean>();
		return viewIdeas;
	}

	public void setViewIdeas(List<IdeaBean> viewIdeas) {
		this.viewIdeas = viewIdeas;
	}

	public TagCloudModel getLikes() {
		return likes;
	}

	public void setLikes(TagCloudModel likes) {
		this.likes = likes;
	}

	public List<TagBean> getComments() {
		if (comments == null)
			comments = new ArrayList<TagBean>();
		return comments;
	}

	public void setComments(List<TagBean> comments) {
		this.comments = comments;
	}

	public List<TagBean> getBuildOns() {
		if (buildOns == null)
			buildOns = new ArrayList<TagBean>();
		return buildOns;
	}

	public void setBuildOns(List<TagBean> buildOns) {
		this.buildOns = buildOns;
	}

	public List<UserBean> getAdmUsers() {
		if (admUsers == null)
			admUsers = new ArrayList<UserBean>();
		return admUsers;
	}

	public void setAdmUsers(List<UserBean> admUsers) {
		this.admUsers = admUsers;
	}

	public List<ListSelectorBean> getIdeaCats() {
		if (ideaCats == null)
			ideaCats = new ArrayList<ListSelectorBean>();
		return ideaCats;
	}

	public void setIdeaCats(List<ListSelectorBean> ideaCats) {
		this.ideaCats = ideaCats;
	}

	public List<ListSelectorBean> getIdeaStatuses() {
		if (ideaStatuses == null)
			ideaStatuses = new ArrayList<ListSelectorBean>();
		return ideaStatuses;
	}

	public void setIdeaStatuses(List<ListSelectorBean> ideaStatuses) {
		this.ideaStatuses = ideaStatuses;
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

	public boolean isShowIdeaComments() {
		return showIdeaComments;
	}

	public void setShowIdeaComments(boolean showIdeaComments) {
		this.showIdeaComments = showIdeaComments;
	}

	public boolean isShowIdeaLikes() {
		return showIdeaLikes;
	}

	public void setShowIdeaLikes(boolean showIdeaLikes) {
		this.showIdeaLikes = showIdeaLikes;
	}

	public boolean isShowIdeaBuildOns() {
		return showIdeaBuildOns;
	}

	public void setShowIdeaBuildOns(boolean showIdeaBuildOns) {
		this.showIdeaBuildOns = showIdeaBuildOns;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getBuildOnText() {
		return buildOnText;
	}

	public void setBuildOnText(String buildOnText) {
		this.buildOnText = buildOnText;
	}

	public String getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(String likeCnt) {
		this.likeCnt = likeCnt;
	}

	public String getCommentCnt() {
		return commentCnt;
	}

	public void setCommentCnt(String commentCnt) {
		this.commentCnt = commentCnt;
	}

	public String getBuildOnCnt() {
		return buildOnCnt;
	}

	public void setBuildOnCnt(String buildOnCnt) {
		this.buildOnCnt = buildOnCnt;
	}

	public StreamedContent getUploadContent() {
		return uploadContent;
	}

	public void setUploadContent(StreamedContent uploadContent) {
		this.uploadContent = uploadContent;
	}

	public DualListModel<GroupBean> getGroupTwinSelect() {
		return groupTwinSelect;
	}

	public void setGroupTwinSelect(DualListModel<GroupBean> groupTwinSelect) {
		this.groupTwinSelect = groupTwinSelect;
	}

	public List<GroupBean> getpGrps() {
		return pGrps;
	}

	public void setpGrps(List<GroupBean> pGrps) {
		this.pGrps = pGrps;
	}

	public boolean isSaveAsOpen() {
		return saveAsOpen;
	}

	public void setSaveAsOpen(boolean saveAsOpen) {
		this.saveAsOpen = saveAsOpen;
	}

	public boolean isTitleAvail() {
		return titleAvail;
	}

	public void setTitleAvail(boolean titleAvail) {
		this.titleAvail = titleAvail;
	}

	public int getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}
}
