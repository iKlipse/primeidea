package za.co.idea.ip.portal.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import org.primefaces.model.tagcloud.TagCloudModel;

import za.co.idea.ip.portal.bean.FileBean;
import za.co.idea.ip.portal.bean.GroupBean;
import za.co.idea.ip.portal.bean.IdeaBean;
import za.co.idea.ip.portal.bean.ListSelectorBean;
import za.co.idea.ip.portal.bean.ReviewBean;
import za.co.idea.ip.portal.bean.TagBean;
import za.co.idea.ip.portal.bean.UserBean;
import za.co.idea.ip.portal.util.FacesUtil;
import za.co.idea.ip.portal.util.IdNumberGen;
import za.co.idea.ip.portal.util.RESTServiceHelper;
import za.co.idea.ip.ws.bean.AttachmentMessage;
import za.co.idea.ip.ws.bean.IdeaMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;
import za.co.idea.ip.ws.bean.ReviewMessage;
import za.co.idea.ip.ws.bean.TagMessage;
import za.co.idea.ip.ws.bean.UserMessage;
import za.co.idea.ip.ws.util.CustomObjectMapper;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;

@ManagedBean(name = "randomIdeaController")
@SessionScoped
public class RandomIdeaController implements Serializable {

	private static final long serialVersionUID = 2409570255777650709L;
	private static final Logger logger = Logger.getLogger(RandomIdeaController.class);
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-portal");
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
	private List<GroupBean> cGrps;
	private String[] selGrpId;
	private Long selGroupId;
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
	private boolean showViewOpenIdea;
	private boolean showViewIdea;
	private boolean showCrtIdea;
	private boolean showViewReviewIdea;
	private String returnView;
	private String toView;
	private long userId;
	private static final IdNumberGen COUNTER = new IdNumberGen();
	private List<ReviewBean> rvIds;
	private Integer rvIdCnt;
	private AccessController controller;
	private List<FileBean> uploadFiles;

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
		try {
			PortletRequest request = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			User user = (User) request.getAttribute(WebKeys.USER);
			WebClient client = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/verify/" + user.getScreenName());
			UserMessage message = client.accept(MediaType.APPLICATION_JSON).get(UserMessage.class);
			userId = message.getuId();
			if (toView != null && Integer.valueOf(toView) != -1) {
				switch (Integer.valueOf(toView)) {
				case 1:
					viewIdeas = RESTServiceHelper.fetchAllIdeasByStatusIdUserId(2, userId);
					showViewOpenIdea = true;
					showViewIdea = false;
					showCrtIdea = false;
					showViewReviewIdea = false;
					break;
				case 2:
					viewIdeas = RESTServiceHelper.fetchAllIdeasByUser(userId);
					showViewOpenIdea = false;
					showViewIdea = true;
					showCrtIdea = false;
					showViewReviewIdea = false;
					break;
				case 3:
					saveAsOpen = false;
					ideaBean = new IdeaBean();
					pGrps = RESTServiceHelper.fetchActiveGroups();
					admUsers = RESTServiceHelper.fetchActiveUsers();
					showViewOpenIdea = false;
					showViewIdea = false;
					showCrtIdea = true;
					showViewReviewIdea = false;
					break;
				case 4:
					viewIdeas = RESTServiceHelper.fetchAllReviewIdeasByUserId(userId);
					ideaStatuses = RESTServiceHelper.fetchAllReviewIdeaStatuses();
					showViewOpenIdea = false;
					showViewIdea = false;
					showCrtIdea = false;
					showViewReviewIdea = true;
					break;
				}
			} else {
				viewIdeas = RESTServiceHelper.fetchAllIdeasByStatusIdUserId(2, userId);
				ideaCats = RESTServiceHelper.fetchAllIdeaCat();
				admUsers = RESTServiceHelper.fetchAllUsers();
				ideaStatuses = RESTServiceHelper.fetchAllIdeaStatuses();
				showViewOpenIdea = false;
				showViewIdea = true;
				showCrtIdea = false;
				showViewReviewIdea = false;
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
			return "ideav";
		default:
			return "";
		}
	}

	public void showViewOpenIdeas() {
		try {
			viewIdeas = RESTServiceHelper.fetchAllIdeasByStatusIdUserId(2, userId);
			ideaCats = RESTServiceHelper.fetchAllIdeaCat();
			admUsers = RESTServiceHelper.fetchAllUsers();
			ideaStatuses = RESTServiceHelper.fetchAllIdeaStatuses();
			showViewOpenIdea = true;
			showViewIdea = false;
			showCrtIdea = false;
			showViewReviewIdea = false;
			// return "ideav";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			// return "";
		}
	}

	public void showViewReviewIdeas() {
		try {
			viewIdeas = RESTServiceHelper.fetchAllReviewIdeasByUserId(userId);
			ideaCats = RESTServiceHelper.fetchAllIdeaCat();
			admUsers = RESTServiceHelper.fetchAllUsers();
			viewIdeas = RESTServiceHelper.fetchAllReviewIdeasByUserId(userId);
			ideaStatuses = RESTServiceHelper.fetchAllIdeaStatuses();
			showViewOpenIdea = false;
			showViewIdea = false;
			showCrtIdea = false;
			showViewReviewIdea = true;
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			// return "";
		}
	}

	public void showViewIdeas() {
		try {
			viewIdeas = RESTServiceHelper.fetchAllIdeasByUser(userId);
			ideaCats = RESTServiceHelper.fetchAllIdeaCat();
			admUsers = RESTServiceHelper.fetchAllUsers();
			ideaStatuses = RESTServiceHelper.fetchAllIdeaStatuses();
			showViewOpenIdea = false;
			showViewIdea = true;
			showCrtIdea = false;
			showViewReviewIdea = false;
			// return "ideav";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			// return "";
		}
	}

	public void showCreateIdea() {
		try {
			ideaCats = RESTServiceHelper.fetchAllIdeaCat();
			saveAsOpen = false;
			admUsers = RESTServiceHelper.fetchActiveUsers();
			ideaStatuses = RESTServiceHelper.fetchAllIdeaStatuses();
			ideaBean = new IdeaBean();
			pGrps = RESTServiceHelper.fetchActiveGroups();
			selGrpId = null;
			showViewOpenIdea = false;
			showViewIdea = false;
			showCrtIdea = true;
			showViewReviewIdea = false;
			rvIdCnt = 1;
			rvIds = null;
			// return "ideav";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create request", "System error occurred, cannot perform create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			// return "";
		}
	}

	public String showEditIdea() {
		try {
			ideaCats = RESTServiceHelper.fetchAllIdeaCat();
			admUsers = RESTServiceHelper.fetchActiveUsers();
			if (userId == 0l) {
				ideaStatuses = RESTServiceHelper.fetchAllIdeaStatuses();
			} else {
				ideaStatuses = RESTServiceHelper.fetchNextIdeaStatuses(ideaBean.getSetStatusId());
			}
			pGrps = RESTServiceHelper.fetchActiveGroups();
			rvIds = RESTServiceHelper.fetchReviews(ideaBean.getIdeaId(), "ip_idea");
			rvIdCnt = rvIds.size();
			List<Long> grpList = ideaBean.getGroupIdList();
			if (grpList != null && !grpList.isEmpty()) {
				selGroupId = (Long) grpList.get(0);
			}
			return "ideae";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			fileAvail = true;
			fileContent = null;
			return "";
		}
	}

	public String showEditReviewIdea() {
		try {
			ideaCats = RESTServiceHelper.fetchAllIdeaCat();
			admUsers = RESTServiceHelper.fetchActiveUsers();
			ideaStatuses = RESTServiceHelper.fetchAllReviewIdeaStatuses();
			pGrps = RESTServiceHelper.fetchActiveGroups();
			rvIds = RESTServiceHelper.fetchReviews(ideaBean.getIdeaId(), "ip_idea");
			rvIdCnt = rvIds.size();
			List<Long> grpList = ideaBean.getGroupIdList();
			if (grpList != null && !grpList.isEmpty()) {
				selGroupId = (Long) grpList.get(0);
			}
			if (userId == 0l) {
				ideaStatuses = RESTServiceHelper.fetchAllIdeaStatuses();
			} else {
				ideaStatuses = RESTServiceHelper.fetchAllReviewIdeaStatuses();
			}
			return "ideaer";
		} catch (Exception e) {
			logger.error(e, e);

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
			admUsers = RESTServiceHelper.fetchActiveUsers();
			if (userId == 0l) {
				ideaStatuses = RESTServiceHelper.fetchAllIdeaStatuses();
			} else {
				ideaStatuses = RESTServiceHelper.fetchNextIdeaStatuses(ideaBean.getSetStatusId());
			}
			pGrps = RESTServiceHelper.fetchActiveGroups();
			rvIds = RESTServiceHelper.fetchReviews(ideaBean.getIdeaId(), "ip_idea");
			rvIdCnt = rvIds.size();
			List<Long> grpList = ideaBean.getGroupIdList();
			if (grpList != null && !grpList.isEmpty()) {
				selGroupId = (Long) grpList.get(0);
			}
			return "ideaeo";
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			fileAvail = true;
			fileContent = null;
			return "";
		}
	}

	public String showSummaryIdea() {
		try {
			likes = RESTServiceHelper.fetchAllBuildonLikes(ideaBean.getIdeaId(), 1);
			comments = RESTServiceHelper.fetchAllBuildonComments(ideaBean.getIdeaId(), 1);
			buildOns = RESTServiceHelper.fetchAllBuildOns(ideaBean.getIdeaId(), 1);
			likeCnt = "(" + likes.getTags().size() + ")	";
			commentCnt = "(" + comments.size() + ")	";
			buildOnCnt = "(" + buildOns.size() + ")	";
			showIdeaComments = false;
			showIdeaLikes = false;
			showIdeaBuildOns = false;
			ideaBean.setTaggable(ideaBean.getSetStatusId() != 2);
			return "ideas";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform summary request", "System error occurred, cannot perform summary request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			fileAvail = true;
			fileContent = null;
			return "";
		}
	}

	public String showSummaryReviewIdea() {
		try {
			likes = RESTServiceHelper.fetchAllBuildonLikes(ideaBean.getIdeaId(), 1);
			comments = RESTServiceHelper.fetchAllBuildonComments(ideaBean.getIdeaId(), 1);
			buildOns = RESTServiceHelper.fetchAllBuildOns(ideaBean.getIdeaId(), 1);
			likeCnt = "(" + likes.getTags().size() + ")	";
			commentCnt = "(" + comments.size() + ")	";
			buildOnCnt = "(" + buildOns.size() + ")	";
			showIdeaComments = false;
			showIdeaLikes = false;
			showIdeaBuildOns = false;
			ideaBean.setTaggable(ideaBean.getSetStatusId() != 2);
			return "ideasr";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform summary request", "System error occurred, cannot perform summary request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			fileAvail = true;
			fileContent = null;
			return "";
		}
	}

	public String showSummaryOpenIdea() {
		try {
			likes = RESTServiceHelper.fetchAllBuildonLikes(ideaBean.getIdeaId(), 1);
			comments = RESTServiceHelper.fetchAllBuildonComments(ideaBean.getIdeaId(), 1);
			buildOns = RESTServiceHelper.fetchAllBuildOns(ideaBean.getIdeaId(), 1);
			likeCnt = "(" + likes.getTags().size() + ")	";
			commentCnt = "(" + comments.size() + ")	";
			buildOnCnt = "(" + buildOns.size() + ")	";
			showIdeaComments = false;
			showIdeaLikes = false;
			showIdeaBuildOns = false;
			ideaBean.setTaggable(ideaBean.getSetStatusId() != 2);
			return "ideaso";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform summary request", "System error occurred, cannot perform summary request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			fileAvail = true;
			fileContent = null;
			return "";
		}
	}

	public void showCommentIdea() {
		comments = RESTServiceHelper.fetchAllBuildonComments(ideaBean.getIdeaId(), 1);
		commentText = "";
		showIdeaComments = true;
		showIdeaLikes = false;
		showIdeaBuildOns = false;
	}

	public void showBuildOnIdea() {
		buildOns = RESTServiceHelper.fetchAllBuildOns(ideaBean.getIdeaId(), 1);
		showIdeaComments = false;
		showIdeaLikes = false;
		showIdeaBuildOns = true;
	}

	public void likeIdea() {
		WebClient addTagClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ts/tag/add");
		TagMessage message = new TagMessage();
		message.setEntityId(ideaBean.getIdeaId());
		message.setTagId(COUNTER.getNextId("IpTag"));
		message.setTeId(1);
		message.setTtId(1);
		message.setUserId(userId);
		message.setDuplicate(false);
		ResponseMessage response = addTagClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		addTagClient.close();
		if (response.getStatusCode() != 0 && response.getStatusCode() != 2)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Like", "Error While Saving Like"));
		likes = RESTServiceHelper.fetchAllBuildonLikes(ideaBean.getIdeaId(), 1);
		likeCnt = "(" + likes.getTags().size() + ")	";
		showIdeaComments = false;
		showIdeaLikes = true;
		showIdeaBuildOns = false;
	}

	public void commentIdea() {
		WebClient addTagClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ts/tag/add");
		TagMessage message = new TagMessage();
		message.setEntityId(ideaBean.getIdeaId());
		message.setTagId(COUNTER.getNextId("IpTag"));
		message.setTagText(commentText);
		message.setTeId(1);
		message.setTtId(2);
		message.setDuplicate(true);
		message.setUserId(userId);
		ResponseMessage response = addTagClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		addTagClient.close();
		if (response.getStatusCode() != 0)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Comment", "Error While Saving Comment"));
		comments = RESTServiceHelper.fetchAllBuildonComments(ideaBean.getIdeaId(), 1);
		commentCnt = "(" + comments.size() + ")	";
		commentText = "";
		showIdeaComments = true;
		showIdeaLikes = false;
		showIdeaBuildOns = false;
	}

	public void buildOnIdea() {
		WebClient addTagClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ts/tag/add");
		TagMessage message = new TagMessage();
		message.setEntityId(ideaBean.getIdeaId());
		message.setTagId(COUNTER.getNextId("IpTag"));
		message.setTagText(buildOnText);
		message.setTeId(1);
		message.setTtId(3);
		message.setDuplicate(true);
		message.setUserId(userId);
		ResponseMessage response = addTagClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		addTagClient.close();
		if (response.getStatusCode() != 0)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Build-On", "Error While Saving Build-On"));
		buildOns = RESTServiceHelper.fetchAllBuildOns(ideaBean.getIdeaId(), 1);
		buildOnCnt = "(" + buildOns.size() + ")	";
		buildOnText = "";
		showIdeaComments = false;
		showIdeaLikes = false;
		showIdeaBuildOns = true;
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
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
		WebClient checkAvailablityClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/is/idea/check/title/" + ideaBean.getIdeaTitle());
		Boolean avail = checkAvailablityClient.accept(MediaType.APPLICATION_JSON).get(Boolean.class);
		checkAvailablityClient.close();
		titleAvail = avail.booleanValue();
		if (titleAvail) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Title Not Available", "Title Not Available");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		} else {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Title Available", "Title Available");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void saveIdea() {
		try {
			if (titleAvail) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Title Not Available", "Title Not Available");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			} else {
				List<String> errors = validateIdea();
				if (errors.size() > 0) {
					for (String error : errors) {
						FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
						FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
					}
				} else {
					WebClient addIdeaClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/is/idea/add");
					IdeaMessage ideaMessage = new IdeaMessage();
					ideaMessage.setCrtdById(userId);
					ideaMessage.setCrtdDate(new Date());
					ideaMessage.setIdeaBa(ideaBean.getIdeaBa());
					ideaMessage.setIdeaDesc(ideaBean.getIdeaDesc());
					ideaMessage.setIdeaTag(ideaBean.getIdeaTag());
					ideaMessage.setIdeaId(COUNTER.getNextId("IpIdea"));
					ideaMessage.setIdeaTitle(ideaBean.getIdeaTitle());
					ideaMessage.setSelCatId(ideaBean.getSelCatId());
					ideaMessage.setGroupIdList(getLongArray(selGroupId));
					ideaMessage.setRvIdCnt(rvIdCnt);
					if (saveAsOpen) {
						ideaMessage.setSetStatusId(2l);
					} else {
						ideaMessage.setSetStatusId(1l);
					}
					ResponseMessage response = addIdeaClient.accept(MediaType.APPLICATION_JSON).post(ideaMessage, ResponseMessage.class);
					addIdeaClient.close();
					if (response.getStatusCode() == 0) {
						if (getUploadFiles().size() > 0) {
							int i = 0;
							for (FileBean bean : getUploadFiles()) {
								WebClient createBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/createMulti");
								AttachmentMessage message = new AttachmentMessage();
								message.setBlobContentType(bean.getType());
								message.setBlobEntityId(ideaMessage.getIdeaId());
								message.setBlobEntityTblNm("ip_idea");
								message.setBlobName(bean.getName());
								message.setBlobSize(bean.getSize());
								message.setBlobId(COUNTER.getNextId("IpBlob"));
								Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(message);
								createBlobClient.close();
								if (crtRes.getStatus() == 200) {
									WebClient client = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/multiUpload/" + message.getBlobId() + "/" + ((i == 0) ? "true" : "false"), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
									client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
									client.header("Accept", "application/json");
									client.accept(MediaType.APPLICATION_JSON).post(new Attachment(message.getBlobId().toString(), bean.getContent().getStream(), new ContentDisposition("attachment;filename=" + bean.getName())));
									client.close();
								}
								i++;
							}
						}
						rvIds = null;
						rvIdCnt = 1;
						selGroupId = -1l;
						uploadContent = null;
						saveAsOpen = false;
						uploadFiles = null;
						if (ideaMessage.getSetStatusId().longValue() == 2l)
							showViewOpenIdeas();
						else
							showViewIdeas();
						ideaBean = new IdeaBean();
						FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Idea Saved", "Idea Saved");
						FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
					} else {
						FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
						FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create Idea request", "System error occurred, cannot perform create Idea request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public String updateIdea() {
		try {
			List<String> errors = validateIdea();
			if (errors.size() > 0) {
				for (String error : errors) {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
				return "";
			}
			WebClient updateIdeaClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/is/idea/modify");
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
			if (selGroupId != null && selGroupId.toString().length() != 0)
				ideaMessage.setGroupIdList(getLongArray(selGroupId));
			ideaMessage.setRvIdCnt(rvIdCnt);
			ResponseMessage response = updateIdeaClient.accept(MediaType.APPLICATION_JSON).put(ideaMessage, ResponseMessage.class);
			updateIdeaClient.close();
			if (response.getStatusCode() == 0) {
				if (getUploadFiles().size() > 0) {
					WebClient deleteBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/delete/" + ideaBean.getIdeaId() + "/ip_idea");
					Response res = deleteBlobClient.accept(MediaType.APPLICATION_JSON).get();
					if (res.getStatus() == 0) {
						int i = 0;
						for (FileBean bean : getUploadFiles()) {
							WebClient createBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/createMulti");
							AttachmentMessage message = new AttachmentMessage();
							message.setBlobContentType(bean.getType());
							message.setBlobEntityId(ideaMessage.getIdeaId());
							message.setBlobEntityTblNm("ip_idea");
							message.setBlobName(bean.getName());
							message.setBlobSize(bean.getSize());
							message.setBlobId(COUNTER.getNextId("IpBlob"));
							Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(message);
							createBlobClient.close();
							if (crtRes.getStatus() == 200) {
								WebClient client = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/multiUpload/" + message.getBlobId() + "/" + ((i == 0) ? "true" : "false"), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
								client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
								client.header("Accept", "application/json");
								client.accept(MediaType.APPLICATION_JSON).post(new Attachment(message.getBlobId().toString(), bean.getContent().getStream(), new ContentDisposition("attachment;filename=" + bean.getName())));
								client.close();
							}
							i++;
						}
					}
				}
				uploadContent = null;
				saveAsOpen = false;
				selGroupId = -1l;
				rvIds = null;
				rvIdCnt = 0;
				if (ideaMessage.getSetStatusId().longValue() == 2l)
					showViewOpenIdeas();
				else
					showViewIdeas();
				return redirectMain();
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform update Idea request", "System error occurred, cannot perform update Idea request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public void updateReviewer() {
		WebClient updateReviewerClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/is/idea/rev/user/" + ideaBean.getIdeaId() + "/" + ideaBean.getRevUserId());
		ResponseMessage message = updateReviewerClient.accept(MediaType.APPLICATION_JSON).get(ResponseMessage.class);
		if (message.getStatusCode() == 200) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Update Reviewer Request Success", "Update Reviewer Request Success");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		} else {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Update Reviewer Request Failed", "Update Reviewer Request Failed");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void initializeAssignReviews() {
		logger.info("in initializeAssignReviews method ");
		pGrps = RESTServiceHelper.fetchReviewGroups();
		logger.info("Review Groups : " + pGrps);
		rvIds = new ArrayList<ReviewBean>();
		if (rvIdCnt != null) {
			for (int i = 0; i < rvIdCnt; i++) {
				ReviewBean bean = new ReviewBean();
				bean.setEntityId(null);
				bean.setStatusId(i + 1);
				bean.setTblNm("ip_idea");
				rvIds.add(bean);
			}
		}
		if (selGroupId != null && selGroupId != -999) {
			rvIds = new ArrayList<ReviewBean>();
			cGrps = RESTServiceHelper.fetchSubGroups(selGroupId);
		}
		logger.info("after getting assign reviewers");
	}

	public void changeGroup() {
		if (selGroupId != null && selGroupId != -999) {
			rvIds = new ArrayList<ReviewBean>();
			cGrps = RESTServiceHelper.fetchSubGroups(selGroupId);
		} else
			rvIds = new ArrayList<ReviewBean>();
		rvIdCnt = -999;
	}

	public void changeCount() {
		if (rvIdCnt != null && rvIdCnt != -999) {
			rvIds = new ArrayList<ReviewBean>();
			for (int i = 0; i < rvIdCnt; i++) {
				ReviewBean bean = new ReviewBean();
				bean.setEntityId(null);
				bean.setStatusId(i + 1);
				bean.setTblNm("ip_idea");
				rvIds.add(bean);
			}
		} else
			rvIds = new ArrayList<ReviewBean>();

	}

	public void assignReviews() {
		logger.info("Control handled in assign reviews");
		for (ReviewBean bean : rvIds) {
			ReviewMessage message = new ReviewMessage();
			message.setEntityId(bean.getEntityId());
			message.setGroupId(toLongArray(bean.getGroupId()));
			message.setStatusId(bean.getStatusId());
			message.setTblNm(bean.getTblNm());
			WebClient reviewClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rvs/review/modify");
			ResponseMessage res = reviewClient.accept(MediaType.APPLICATION_JSON).put(message, ResponseMessage.class);
			if (res.getStatusCode() != 0) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Assign Reviewer Request Failed", "Assign Reviewer Request Failed");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Assign Reviewer Request completed successfully", "Assign Reviewer Request completed successfully");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			}
			reviewClient.close();
		}
		pGrps = RESTServiceHelper.fetchReviewGroups();
		rvIds = new ArrayList<ReviewBean>();
		selGroupId = -999l;
	}

	protected Long[] toLongArray(String[] val) {
		Long[] ret = new Long[val.length];
		for (int i = 0; i < val.length; i++) {
			if (val[i] != null)
				ret[i] = Long.valueOf(val[i]);
		}
		return ret;
	}

	protected Long[] getLongArray(Long groupId) {
		Long[] ret = new Long[1];
		ret[0] = groupId;
		return ret;
	}

	public void fileUploadHandle(FileUploadEvent fue) {
		try {
			UploadedFile file = fue.getFile();
			FileBean bean = new FileBean();
			bean.setContent(new DefaultStreamedContent(file.getInputstream()));
			bean.setName(file.getFileName());
			bean.setSize(file.getSize());
			bean.setType(file.getContentType());
			getUploadFiles().add(bean);
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform upload request", "System error occurred, cannot perform upload request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
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
		if (commentText == null)
			commentText = "";
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public String getBuildOnText() {
		if (buildOnText == null)
			buildOnText = "";
		return buildOnText;
	}

	public void setBuildOnText(String buildOnText) {
		this.buildOnText = buildOnText;
	}

	public String getLikeCnt() {
		if (likeCnt == null)
			likeCnt = "";
		return likeCnt;
	}

	public void setLikeCnt(String likeCnt) {
		this.likeCnt = likeCnt;
	}

	public String getCommentCnt() {
		if (commentCnt == null)
			commentCnt = "";
		return commentCnt;
	}

	public void setCommentCnt(String commentCnt) {
		this.commentCnt = commentCnt;
	}

	public String getBuildOnCnt() {
		if (buildOnCnt == null)
			buildOnCnt = "";
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

	public List<GroupBean> getpGrps() {
		if (pGrps == null)
			pGrps = new ArrayList<GroupBean>();
		return pGrps;
	}

	public void setpGrps(List<GroupBean> pGrps) {
		this.pGrps = pGrps;
	}

	public List<GroupBean> getcGrps() {
		if (cGrps == null)
			cGrps = new ArrayList<GroupBean>();
		return cGrps;
	}

	public void setcGrps(List<GroupBean> cGrps) {
		this.cGrps = cGrps;
	}

	public String[] getSelGrpId() {
		if (selGrpId == null)
			selGrpId = new String[] {};
		return selGrpId;
	}

	public void setSelGrpId(String[] selGrpId) {
		this.selGrpId = selGrpId;
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

	public boolean isShowViewOpenIdea() {
		return showViewOpenIdea;
	}

	public void setShowViewOpenIdea(boolean showViewOpenIdea) {
		this.showViewOpenIdea = showViewOpenIdea;
	}

	public boolean isShowViewIdea() {
		return showViewIdea;
	}

	public void setShowViewIdea(boolean showViewIdea) {
		this.showViewIdea = showViewIdea;
	}

	public boolean isShowCrtIdea() {
		return showCrtIdea;
	}

	public void setShowCrtIdea(boolean showCrtIdea) {
		this.showCrtIdea = showCrtIdea;
	}

	public String getReturnView() {
		if (returnView == null) {
			returnView = "";
		}
		return returnView;
	}

	public void setReturnView(String returnView) {
		this.returnView = returnView;
	}

	public String getToView() {
		if (toView == null)
			toView = "";
		return toView;
	}

	public void setToView(String toView) {
		this.toView = toView;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public boolean isShowViewReviewIdea() {
		return showViewReviewIdea;
	}

	public void setShowViewReviewIdea(boolean showViewReviewIdea) {
		this.showViewReviewIdea = showViewReviewIdea;
	}

	public List<ReviewBean> getRvIds() {
		if (rvIds == null)
			rvIds = new ArrayList<ReviewBean>();
		return rvIds;
	}

	public void setRvIds(List<ReviewBean> rvIds) {
		this.rvIds = rvIds;
	}

	public Integer getRvIdCnt() {
		return rvIdCnt;
	}

	public void setRvIdCnt(Integer rvIdCnt) {
		this.rvIdCnt = rvIdCnt;
	}

	public Long getSelGroupId() {
		return selGroupId;
	}

	public void setSelGroupId(Long selGroupId) {
		this.selGroupId = selGroupId;
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

	public List<FileBean> getUploadFiles() {
		if (uploadFiles == null)
			uploadFiles = new ArrayList<FileBean>();
		return uploadFiles;
	}

	public void setUploadFiles(List<FileBean> uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

}
