package za.co.idea.ip.portal.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
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

import za.co.idea.ip.portal.bean.ChallengeBean;
import za.co.idea.ip.portal.bean.GroupBean;
import za.co.idea.ip.portal.bean.ListSelectorBean;
import za.co.idea.ip.portal.bean.ReviewBean;
import za.co.idea.ip.portal.bean.SolutionBean;
import za.co.idea.ip.portal.bean.TagBean;
import za.co.idea.ip.portal.bean.UserBean;
import za.co.idea.ip.portal.util.IdNumberGen;
import za.co.idea.ip.portal.util.RESTServiceHelper;
import za.co.idea.ip.ws.bean.AttachmentMessage;
import za.co.idea.ip.ws.bean.ChallengeMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;
import za.co.idea.ip.ws.bean.ReviewMessage;
import za.co.idea.ip.ws.bean.SolutionMessage;
import za.co.idea.ip.ws.bean.TagMessage;
import za.co.idea.ip.ws.bean.UserMessage;
import za.co.idea.ip.ws.util.CustomObjectMapper;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;

@ManagedBean(name = "challengeController")
@SessionScoped
public class ChallengeController implements Serializable {
	private static final long serialVersionUID = -4791485398536884291L;
	private static final Logger logger = Logger.getLogger(ChallengeController.class);
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-portal");
	private ChallengeBean challengeBean;
	private SolutionBean solutionBean;
	private List<ChallengeBean> viewChallenges;
	private List<SolutionBean> viewSolutions;
	private List<UserBean> admUsers;
	private List<ListSelectorBean> challengeCats;
	private List<ListSelectorBean> challengeStatuses;
	private List<ListSelectorBean> solutionCats;
	private List<ListSelectorBean> solutionStatuses;
	private List<GroupBean> pGrps;
	private String[] selGrpId;
	private TagCloudModel chalLikes;
	private List<TagBean> chalComments;
	private TagCloudModel solLikes;
	private List<TagBean> solComments;
	private List<TagBean> buildOns;
	private String buildOnText;
	private boolean showSolBuildOns;
	private String buildOnCnt;
	private String chalLikeCnt;
	private String chalCommentCnt;
	private String solLikeCnt;
	private String solCommentCnt;
	private String commentText;
	private boolean chalFileAvail;
	private StreamedContent chalFileContent;
	private boolean solFileAvail;
	private StreamedContent solFileContent;
	private StreamedContent chalUploadContent;
	private StreamedContent solUploadContent;
	private boolean showChallengeComments;
	private boolean showChallengeLikes;
	private boolean showSolutionComments;
	private boolean showSolutionLikes;
	private Date selLaunchDate;
	private boolean saveAsOpen;
	private boolean titleAvail;
	private boolean chalTitleAvail;
	private Long chalId;
	private int activeIndex;
	private static final IdNumberGen COUNTER = new IdNumberGen();
	private boolean showPubChal;
	private boolean showViewChal;
	private boolean showCrtChal;
	private boolean showPubSol;
	private boolean showViewSol;
	private boolean showCrtSol;
	private String returnView;
	private String toView;
	private long userId;
	private boolean showReviewChal;
	private boolean showReviewSol;
	private List<ReviewBean> rvIds;
	private Integer rvIdCnt;
	private AccessController controller;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private WebClient createCustomClient(String url) {
		List providers = new ArrayList();
		providers.add(new JacksonJaxbJsonProvider(new CustomObjectMapper(), JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS));
		WebClient client = WebClient.create(url, providers);
		client.header("Content-Type", "application/json");
		client.header("Accept", "application/json");
		return client;
	}

	public void initializeChalPage() {
		try {
			PortletRequest request = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			User user = (User) request.getAttribute(WebKeys.USER);
			WebClient client = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/verify/" + user.getScreenName());
			UserMessage message = client.accept(MediaType.APPLICATION_JSON).get(UserMessage.class);
			userId = message.getuId();
			challengeCats = RESTServiceHelper.fetchAllChallengeCat();
			admUsers = RESTServiceHelper.fetchAllUsers();
			viewChallenges = RESTServiceHelper.fetchAllChallengesByStatusIdUserId(userId, 8);
			showPubChal = true;
			showViewChal = false;
			showCrtChal = false;
			if (toView != null && Integer.valueOf(toView) != -1) {
				switch (Integer.valueOf(toView)) {
				case 1:
					viewChallenges = RESTServiceHelper.fetchAllChallengesByStatusIdUserId(userId, 8);
					challengeStatuses = RESTServiceHelper.fetchAllChallengeStatuses();
					showPubChal = true;
					showViewChal = false;
					showCrtChal = false;
					showReviewChal = false;
					break;
				case 2:
					viewChallenges = RESTServiceHelper.fetchAllChallengesByUser(userId);
					challengeStatuses = RESTServiceHelper.fetchAllChallengeStatuses();
					showPubChal = false;
					showViewChal = true;
					showCrtChal = false;
					showReviewChal = false;
					break;
				case 3:
					pGrps = RESTServiceHelper.fetchActiveGroups();
					challengeStatuses = RESTServiceHelper.fetchAllChallengeStatuses();
					challengeBean = new ChallengeBean();
					admUsers = RESTServiceHelper.fetchActiveUsers();
					showPubChal = false;
					showViewChal = false;
					showCrtChal = true;
					showReviewChal = false;
					break;
				case 4:
					viewChallenges = RESTServiceHelper.fetchAllReviewChallengesByUser(userId);
					challengeStatuses = RESTServiceHelper.fetchAllReviewChallengeNextStatuses();
					showPubChal = false;
					showViewChal = false;
					showCrtChal = false;
					showReviewChal = true;
					break;
				}
			}
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void initializeSolPage() {
		try {
			PortletRequest request = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			User user = (User) request.getAttribute(WebKeys.USER);
			WebClient client = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/verify/" + user.getScreenName());
			UserMessage message = client.accept(MediaType.APPLICATION_JSON).get(UserMessage.class);
			userId = message.getuId();
			admUsers = RESTServiceHelper.fetchAllUsers();
			solutionCats = RESTServiceHelper.fetchAllSolutionCat();
			solutionStatuses = RESTServiceHelper.fetchAllSolutionStatuses();
			viewChallenges = RESTServiceHelper.fetchAllChallengesByStatusIdUserId(userId, 8);
			solutionBean = new SolutionBean();
			saveAsOpen = false;
			showPubSol = true;
			showViewSol = false;
			showCrtSol = false;
			if (toView != null && Integer.valueOf(toView) != -1) {
				switch (Integer.valueOf(toView)) {
				case 1:
					viewChallenges = RESTServiceHelper.fetchAllChallengesByStatusIdUserId(userId, 8);
					admUsers = RESTServiceHelper.fetchActiveUsers();
					solutionBean = new SolutionBean();
					saveAsOpen = false;
					showPubSol = false;
					showViewSol = false;
					showCrtSol = true;
					showReviewSol = false;
					break;
				case 2:
					solutionBean = new SolutionBean();
					viewChallenges = RESTServiceHelper.fetchAllChallengesByUser(userId);
					viewSolutions = RESTServiceHelper.fetchAllSolutionsByStatusIdUserId(userId, 2);
					showPubSol = true;
					showViewSol = false;
					showCrtSol = false;
					showReviewSol = false;
					break;
				case 3:
					solutionBean = new SolutionBean();
					viewChallenges = RESTServiceHelper.fetchAllChallengesByUser(userId);
					viewSolutions = RESTServiceHelper.fetchAllSolutionsByUser(userId);
					showPubSol = false;
					showViewSol = true;
					showCrtSol = false;
					showReviewSol = false;
					break;
				case 4:
					solutionBean = new SolutionBean();
					viewChallenges = RESTServiceHelper.fetchAllChallengesByUser(userId);
					viewSolutions = RESTServiceHelper.fetchAllSolutionsByStatusIdUserId(userId, 2);
					showPubSol = false;
					showViewSol = false;
					showCrtSol = false;
					showReviewSol = true;
					break;
				}
			}
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create request", "System error occurred, cannot perform create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public String redirectMain() {
		switch (Integer.valueOf(returnView)) {
		case 1:
			return "lani";
		case 2:
			return "chalv";
		case 3:
			return "solv";
		case 4:
			return showSummaryChallenge();
		case 5:
			return showSummaryOpenChallenge();
		case 6:
			return showSummaryReviewChallenge();
		default:
			return "";
		}
	}

	public void showPublishedChallenges() {
		try {
			viewChallenges = RESTServiceHelper.fetchAllChallengesByStatusIdUserId(userId, 8);
			challengeCats = RESTServiceHelper.fetchAllChallengeCat();
			admUsers = RESTServiceHelper.fetchAllUsers();
			challengeStatuses = RESTServiceHelper.fetchAllChallengeStatuses();
			showPubChal = true;
			showViewChal = false;
			showCrtChal = false;
			showReviewChal = false;
			// return "chalv";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			// return "";
		}
	}

	public void showViewChallenges() {
		try {
			viewChallenges = RESTServiceHelper.fetchAllChallengesByUser(userId);
			challengeCats = RESTServiceHelper.fetchAllChallengeCat();
			admUsers = RESTServiceHelper.fetchAllUsers();
			challengeStatuses = RESTServiceHelper.fetchAllChallengeStatuses();
			showPubChal = false;
			showViewChal = true;
			showCrtChal = false;
			showReviewChal = false;
			// return "chalv";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			// return "";
		}
	}

	public void showCreateChallenges() {
		try {
			admUsers = RESTServiceHelper.fetchActiveUsers();
			challengeCats = RESTServiceHelper.fetchAllChallengeCat();
			challengeStatuses = RESTServiceHelper.fetchAllChallengeStatuses();
			pGrps = RESTServiceHelper.fetchActiveGroups();
			challengeBean = new ChallengeBean();
			selGrpId = null;
			showPubChal = false;
			showViewChal = false;
			showCrtChal = true;
			showReviewChal = false;
			rvIdCnt = 1;
			rvIds = null;
			// return "chalv";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create request", "System error occurred, cannot perform create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			// return "";
		}
	}

	public void showReviewChallenges() {
		try {
			viewChallenges = RESTServiceHelper.fetchAllReviewChallengesByUser(userId);
			challengeCats = RESTServiceHelper.fetchAllChallengeCat();
			admUsers = RESTServiceHelper.fetchAllUsers();
			challengeStatuses = RESTServiceHelper.fetchAllReviewChallengeNextStatuses();
			showPubChal = false;
			showReviewChal = true;
			showViewChal = false;
			showCrtChal = false;
			// return "chalv";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			// return "";
		}
	}

	public String showEditChallenge() {
		try {
			challengeCats = RESTServiceHelper.fetchAllChallengeCat();
			admUsers = RESTServiceHelper.fetchActiveUsers();
			challengeStatuses = RESTServiceHelper.fetchAllChallengeStatuses();
			pGrps = RESTServiceHelper.fetchActiveGroups();
			rvIds = RESTServiceHelper.fetchReviews(challengeBean.getId(), "ip_challenge");
			rvIdCnt = rvIds.size();
			return "chale";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform edit request", "System error occurred, cannot perform edit request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showEditOpenChallenge() {
		try {
			challengeCats = RESTServiceHelper.fetchAllChallengeCat();
			admUsers = RESTServiceHelper.fetchActiveUsers();
			challengeStatuses = RESTServiceHelper.fetchNextChallengeStatuses(challengeBean.getStatusId());
			pGrps = RESTServiceHelper.fetchActiveGroups();
			rvIds = RESTServiceHelper.fetchReviews(challengeBean.getId(), "ip_challenge");
			rvIdCnt = rvIds.size();
			return "chaleo";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform edit request", "System error occurred, cannot perform edit request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showEditReviewChallenge() {
		try {
			challengeCats = RESTServiceHelper.fetchAllChallengeCat();
			admUsers = RESTServiceHelper.fetchActiveUsers();
			challengeStatuses = RESTServiceHelper.fetchAllReviewChallengeNextStatuses();
			pGrps = RESTServiceHelper.fetchActiveGroups();
			rvIds = RESTServiceHelper.fetchReviews(challengeBean.getId(), "ip_challenge");
			rvIdCnt = rvIds.size();
			return "chaler";
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform edit request", "System error occurred, cannot perform edit request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showSummaryChallenge() {
		logger.debug("Control handled in showSummaryChallenge method");
		chalLikes = RESTServiceHelper.fetchAllBuildonLikes(challengeBean.getId(), 2);
		chalComments = RESTServiceHelper.fetchAllBuildonComments(challengeBean.getId(), 2);
		chalLikeCnt = "(" + chalLikes.getTags().size() + ")	";
		chalCommentCnt = "(" + chalComments.size() + ")	";
		commentText = "";
		showChallengeComments = false;
		showChallengeLikes = false;
		viewSolutions = RESTServiceHelper.fetchAllSolutionsByChal(challengeBean.getId());
		return "chals";
	}

	public String showSummaryOpenChallenge() {
		chalLikes = RESTServiceHelper.fetchAllBuildonLikes(challengeBean.getId(), 2);
		chalComments = RESTServiceHelper.fetchAllBuildonComments(challengeBean.getId(), 2);
		chalLikeCnt = "(" + chalLikes.getTags().size() + ")	";
		chalCommentCnt = "(" + chalComments.size() + ")	";
		showChallengeComments = false;
		showChallengeLikes = false;
		commentText = "";
		viewSolutions = RESTServiceHelper.fetchAllSolutionsByChal(challengeBean.getId());
		return "chalso";
	}

	public String showSummaryReviewChallenge() {
		chalLikes = RESTServiceHelper.fetchAllBuildonLikes(challengeBean.getId(), 2);
		chalComments = RESTServiceHelper.fetchAllBuildonComments(challengeBean.getId(), 2);
		chalLikeCnt = "(" + chalLikes.getTags().size() + ")	";
		chalCommentCnt = "(" + chalComments.size() + ")	";
		showChallengeComments = false;
		commentText = "";
		showChallengeLikes = false;
		viewSolutions = RESTServiceHelper.fetchAllSolutionsByChal(challengeBean.getId());
		return "chalsr";
	}

	public void saveChallenge() {
		try {
			if (chalTitleAvail) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Title Not Available", "Title Not Available");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				// return "";
			} else {
				List<String> errors = validateChallenge();
				if (errors.size() > 0) {
					for (String error : errors) {
						FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
						FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
					}
					// return "";
				} else {
					WebClient addChallengeClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/cs/challenge/add");
					ChallengeMessage message = new ChallengeMessage();
					message.setCatId(challengeBean.getCatId());
					message.setCrtdById(userId);
					message.setCrtdDt(new Date());
					message.setDesc(challengeBean.getDesc());
					message.setExprDt(challengeBean.getExprDt());
					message.setHoverText(challengeBean.getHoverText());
					message.setId(COUNTER.getNextId("IpChallenge"));
					message.setLaunchDt(challengeBean.getLaunchDt());
					message.setStatusId(1);
					message.setTag(challengeBean.getTag());
					message.setTitle(challengeBean.getTitle());
					message.setGroupIdList(toLongArray(selGrpId));
					message.setRvIdCnt(rvIdCnt);
					ResponseMessage response = addChallengeClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
					addChallengeClient.close();
					if (response.getStatusCode() == 0) {
						if (chalUploadContent != null) {
							WebClient createBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/create");
							AttachmentMessage attach = new AttachmentMessage();
							attach.setBlobContentType(challengeBean.getContentType());
							attach.setBlobEntityId(message.getId());
							attach.setBlobEntityTblNm("ip_challenge");
							attach.setBlobName(challengeBean.getFileName());
							attach.setBlobId(COUNTER.getNextId("IpBlob"));
							Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(attach);
							if (crtRes.getStatus() == 200) {
								WebClient client = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/upload/" + attach.getBlobId(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
								client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
								client.header("Accept", "application/json");
								Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(attach.getBlobId().toString(), chalUploadContent.getStream(), new ContentDisposition("attachment;filename=" + challengeBean.getFileName())));
								if (docRes.getStatus() != 200) {
									FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Document Upload Failed", "Document Upload Failed");
									FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
								}
							} else {
								FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Document Upload Failed", "Document Upload Failed");
								FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
							}
						}
						rvIds = null;
						rvIdCnt = 0;
						FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Challenge Saved", "Challenge Saved");
						FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
						showViewChallenges();
						// return redirectMain();
					} else {
						FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
						FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
						// return "";
					}
				}
			}
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create request", "System error occurred, cannot perform create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			// return "";
		}
	}

	public String updateChallenge() {
		try {
			if (chalTitleAvail) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Title Not Available", "Title Not Available");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
			List<String> errors = validateChallenge();
			if (errors.size() > 0) {
				for (String error : errors) {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
				return "";
			}
			WebClient updateChallengeClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/cs/challenge/modify");
			ChallengeMessage message = new ChallengeMessage();
			message.setCatId(challengeBean.getCatId());
			message.setCrtdById(userId);
			message.setCrtdDt(challengeBean.getCrtdDt());
			message.setDesc(challengeBean.getDesc());
			message.setExprDt(challengeBean.getExprDt());
			message.setHoverText(challengeBean.getHoverText());
			message.setId(challengeBean.getId());
			message.setLaunchDt(challengeBean.getLaunchDt());
			message.setStatusId(challengeBean.getStatusId());
			message.setTag(challengeBean.getTag());
			message.setTitle(challengeBean.getTitle());
			message.setGroupIdList(toLongArray(selGrpId));
			message.setRvIdCnt(rvIdCnt);
			ResponseMessage response = updateChallengeClient.accept(MediaType.APPLICATION_JSON).put(message, ResponseMessage.class);
			updateChallengeClient.close();
			if (response.getStatusCode() == 0) {
				if (chalUploadContent != null) {
					WebClient getBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/getId/" + challengeBean.getId() + "/ip_challenge");
					Long blobId = getBlobClient.accept(MediaType.APPLICATION_JSON).get(Long.class);
					if (blobId == -999) {
						WebClient createBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/create");
						AttachmentMessage attach = new AttachmentMessage();
						attach.setBlobContentType(challengeBean.getContentType());
						attach.setBlobEntityId(challengeBean.getId());
						attach.setBlobEntityTblNm("ip_challenge");
						attach.setBlobName(challengeBean.getFileName());
						attach.setBlobId(COUNTER.getNextId("IpBlob"));
						Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(attach);
						if (crtRes.getStatus() == 200) {
							WebClient chalUploadClient = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/upload/" + attach.getBlobId(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
							chalUploadClient.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
							chalUploadClient.header("Accept", "application/json");
							Response docRes = chalUploadClient.accept(MediaType.APPLICATION_JSON).post(new Attachment(attach.getBlobId().toString(), chalUploadContent.getStream(), new ContentDisposition("attachment;filename=" + challengeBean.getFileName())));
							if (docRes.getStatus() != 200) {
								FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Document Upload Failed", "Document Upload Failed");
								FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
							}
						} else {
							FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Document Upload Failed", "Document Upload Failed");
							FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
						}
					} else {
						WebClient updateBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/update");
						AttachmentMessage attach = new AttachmentMessage();
						attach.setBlobContentType(challengeBean.getContentType());
						attach.setBlobEntityId(challengeBean.getId());
						attach.setBlobEntityTblNm("ip_challenge");
						attach.setBlobName(challengeBean.getFileName());
						attach.setBlobId(blobId);
						Response updRes = updateBlobClient.accept(MediaType.APPLICATION_JSON).put(attach);
						updateBlobClient.close();
						if (updRes.getStatus() == 200) {
							WebClient chalUploadClient = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/upload/" + blobId.toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
							chalUploadClient.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
							chalUploadClient.header("Accept", "application/json");
							Response docRes = chalUploadClient.accept(MediaType.APPLICATION_JSON).post(new Attachment(blobId.toString(), chalUploadContent.getStream(), new ContentDisposition("attachment;filename=" + challengeBean.getFileName())));
							if (docRes.getStatus() != 200) {
								FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Document Upload Failed", "Document Upload Failed");
								FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
							}
							chalUploadClient.close();
						} else {
							FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Document Upload Failed", "Document Upload Failed");
							FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
						}
					}
				}
				chalUploadContent = null;
				rvIdCnt = 1;
				rvIds = null;
				showViewChallenges();
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

	protected Long[] toLongArray(String[] val) {
		Long[] ret = new Long[val.length];
		for (int i = 0; i < val.length; i++)
			ret[i] = Long.valueOf(val[i]);
		return ret;
	}

	public void commentChallenge() {
		WebClient addTagClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ts/tag/add");
		TagMessage message = new TagMessage();
		message.setEntityId(challengeBean.getId());
		message.setTagId(COUNTER.getNextId("IpTag"));
		message.setTagText(commentText);
		message.setTeId(2);
		message.setTtId(2);
		message.setDuplicate(true);
		message.setUserId(userId);
		ResponseMessage response = addTagClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		addTagClient.close();
		if (response.getStatusCode() != 0)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Comment", "Error While Saving Comment"));
		chalComments = RESTServiceHelper.fetchAllBuildonComments(challengeBean.getId(), 2);
		chalCommentCnt = "(" + chalComments.size() + ")	";
		commentText = "";
		showChallengeComments = true;
		showChallengeLikes = false;
	}

	public void likeChallenge() {
		WebClient addTagClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ts/tag/add");
		TagMessage message = new TagMessage();
		message.setEntityId(challengeBean.getId());
		message.setTagId(COUNTER.getNextId("IpTag"));
		message.setTeId(2);
		message.setTtId(1);
		message.setUserId(userId);
		message.setDuplicate(false);
		ResponseMessage response = addTagClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		addTagClient.close();
		if (response.getStatusCode() != 0 && response.getStatusCode() != 2)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Like", "Error While Saving Like"));
		chalLikes = RESTServiceHelper.fetchAllBuildonLikes(challengeBean.getId(), 2);
		chalLikeCnt = "(" + chalLikes.getTags().size() + ")	";
		showChallengeComments = false;
		showChallengeLikes = true;
	}

	public void showCommentChallenge() {
		chalComments = RESTServiceHelper.fetchAllBuildonComments(challengeBean.getId(), 2);
		showChallengeComments = true;
		showChallengeLikes = false;
	}

	public void showCreateSolution() {
		try {
			admUsers = RESTServiceHelper.fetchActiveUsers();
			viewChallenges = RESTServiceHelper.fetchAllChallengesByStatusIdUserId(userId, 8);
			solutionCats = RESTServiceHelper.fetchAllSolutionCat();
			solutionStatuses = RESTServiceHelper.fetchAllSolutionStatuses();
			solutionBean = new SolutionBean();
			selGrpId = null;
			saveAsOpen = false;
			showPubSol = false;
			showViewSol = false;
			showCrtSol = true;
			showReviewSol = false;
			rvIdCnt = 1;
			rvIds = null;
			// return "solv";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create request", "System error occurred, cannot perform create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			// return "";
		}
	}

	public String showSubmitSolution() {
		try {
			Map<String, String> reqMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			admUsers = RESTServiceHelper.fetchActiveUsers();
			viewChallenges = RESTServiceHelper.fetchAllAvailableChallenges();
			solutionCats = RESTServiceHelper.fetchAllSolutionCat();
			solutionStatuses = RESTServiceHelper.fetchAllSolutionStatuses();
			solutionBean = new SolutionBean();
			solutionBean.setChalId(Long.valueOf(reqMap.get("chalId")));
			saveAsOpen = false;
			rvIdCnt = 1;
			rvIds = null;
			return "chalss";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create request", "System error occurred, cannot perform create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public void showViewSolution() {
		try {
			admUsers = RESTServiceHelper.fetchAllUsers();
			viewChallenges = RESTServiceHelper.fetchAllChallengesByUser(userId);
			solutionCats = RESTServiceHelper.fetchAllSolutionCat();
			solutionStatuses = RESTServiceHelper.fetchAllSolutionStatuses();
			solutionBean = new SolutionBean();
			viewSolutions = RESTServiceHelper.fetchAllSolutionsByUser(userId);
			showPubSol = false;
			showViewSol = true;
			showCrtSol = false;
			showReviewSol = false;
			// return "solv";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			// return "";
		}
	}

	public void showViewOpenSolution() {
		try {
			admUsers = RESTServiceHelper.fetchAllUsers();
			viewChallenges = RESTServiceHelper.fetchAllChallengesByUser(userId);
			solutionCats = RESTServiceHelper.fetchAllSolutionCat();
			solutionStatuses = RESTServiceHelper.fetchAllSolutionStatuses();
			solutionBean = new SolutionBean();
			viewSolutions = RESTServiceHelper.fetchAllSolutionsByStatusIdUserId(userId, 2);
			showPubSol = true;
			showViewSol = false;
			showCrtSol = false;
			showReviewSol = false;
			// return "solv";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			// return "";
		}
	}

	public String showViewReviewSolution() {
		try {
			admUsers = RESTServiceHelper.fetchAllUsers();
			viewChallenges = RESTServiceHelper.fetchAllChallengesByUser(userId);
			solutionCats = RESTServiceHelper.fetchAllSolutionCat();
			solutionStatuses = RESTServiceHelper.fetchAllSolutionStatuses();
			solutionBean = new SolutionBean();
			viewSolutions = RESTServiceHelper.fetchAllReviewSolutionsByUser(userId);
			showPubSol = false;
			showViewSol = false;
			showCrtSol = false;
			showReviewSol = true;
			return "solv";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showEditSolution() {
		try {
			admUsers = RESTServiceHelper.fetchActiveUsers();
			viewChallenges = RESTServiceHelper.fetchAllChallengesByUser(userId);
			solutionCats = RESTServiceHelper.fetchAllSolutionCat();
			solutionStatuses = RESTServiceHelper.fetchAllSolutionStatuses();
			rvIds = RESTServiceHelper.fetchReviews(solutionBean.getId(), "ip_solution");
			rvIdCnt = rvIds.size();
			return "sole";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform updated view request", "System error occurred, cannot perform updated view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showEditOpenSolution() {
		try {
			admUsers = RESTServiceHelper.fetchActiveUsers();
			viewChallenges = RESTServiceHelper.fetchAllChallengesByUser(userId);
			solutionCats = RESTServiceHelper.fetchAllSolutionCat();
			solutionStatuses = RESTServiceHelper.fetchNextSolutionStatuses(solutionBean.getStatusId());
			rvIds = RESTServiceHelper.fetchReviews(solutionBean.getId(), "ip_solution");
			rvIdCnt = rvIds.size();
			return "soleo";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform updated view request", "System error occurred, cannot perform updated view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showEditReviewSolution() {
		try {
			admUsers = RESTServiceHelper.fetchActiveUsers();
			viewChallenges = RESTServiceHelper.fetchAllChallengesByUser(userId);
			solutionCats = RESTServiceHelper.fetchAllSolutionCat();
			solutionStatuses = RESTServiceHelper.fetchAllReviewSolutionStatuses();
			rvIds = RESTServiceHelper.fetchReviews(solutionBean.getId(), "ip_solution");
			rvIdCnt = rvIds.size();
			return "soler";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform updated view request", "System error occurred, cannot perform updated view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showSummarySolution() {
		try {
			solLikes = RESTServiceHelper.fetchAllBuildonLikes(solutionBean.getId(), 3);
			solComments = RESTServiceHelper.fetchAllBuildonComments(solutionBean.getId(), 3);
			solLikeCnt = "(" + solLikes.getTags().size() + ")	";
			solCommentCnt = "(" + solComments.size() + ")	";
			buildOns = RESTServiceHelper.fetchAllBuildOns(solutionBean.getId(), 3);
			buildOnCnt = "(" + buildOns.size() + ")	";
			buildOnText = "";
			commentText = "";
			showSolutionComments = false;
			showSolBuildOns = true;
			showSolutionLikes = false;
			solutionBean.setTaggable(solutionBean.getStatusId() != 2);
			return "sols";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create request", "System error occurred, cannot perform create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			solFileAvail = false;
			solFileContent = null;
			return "";
		}
	}

	public String showSummaryOpenSolution() {
		try {
			solLikes = RESTServiceHelper.fetchAllBuildonLikes(solutionBean.getId(), 3);
			solComments = RESTServiceHelper.fetchAllBuildonComments(solutionBean.getId(), 3);
			solLikeCnt = "(" + solLikes.getTags().size() + ")	";
			solCommentCnt = "(" + solComments.size() + ")	";
			buildOns = RESTServiceHelper.fetchAllBuildOns(solutionBean.getId(), 3);
			buildOnCnt = "(" + buildOns.size() + ")	";
			buildOnText = "";
			commentText = "";
			showSolutionComments = false;
			showSolBuildOns = true;
			showSolutionLikes = false;
			solutionBean.setTaggable(solutionBean.getStatusId() != 2);
			return "solso";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create request", "System error occurred, cannot perform create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			solFileAvail = false;
			solFileContent = null;
			return "";
		}
	}

	public String showSummaryReviewSolution() {
		try {
			solLikes = RESTServiceHelper.fetchAllBuildonLikes(solutionBean.getId(), 3);
			solComments = RESTServiceHelper.fetchAllBuildonComments(solutionBean.getId(), 3);
			solLikeCnt = "(" + solLikes.getTags().size() + ")	";
			solCommentCnt = "(" + solComments.size() + ")	";
			buildOns = RESTServiceHelper.fetchAllBuildOns(solutionBean.getId(), 3);
			buildOnCnt = "(" + buildOns.size() + ")	";
			commentText = "";
			buildOnText = "";
			showSolutionComments = false;
			showSolBuildOns = true;
			showSolutionLikes = false;
			solutionBean.setTaggable(solutionBean.getStatusId() != 2);
			return "solsr";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create request", "System error occurred, cannot perform create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			solFileAvail = false;
			solFileContent = null;
			return "";
		}
	}

	public void checkChalTitleAvailability() {
		if (challengeBean.getTitle() == null || challengeBean.getTitle().length() == 0) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Enter Title to Check Availability", "Enter Title to Check Availability");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
		WebClient checkAvailablityClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/cs/challenge/check/title/" + challengeBean.getTitle());
		Boolean avail = checkAvailablityClient.accept(MediaType.APPLICATION_JSON).get(Boolean.class);
		checkAvailablityClient.close();
		chalTitleAvail = avail.booleanValue();
		if (chalTitleAvail) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Title Not Available", "Title Not Available");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		} else {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Title Available", "Title Available");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void checkTitleAvailability() {
		if (solutionBean.getTitle() == null || solutionBean.getTitle().length() == 0) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Enter Title to Check Availability", "Enter Title to Check Availability");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
		WebClient checkAvailablityClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ss/solution/check/title/" + solutionBean.getTitle());
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

	public void saveSolution() {
		try {
			if (titleAvail) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Title Not Available", "Title Not Available");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				// return "";
			} else {
				List<String> errors = validateSolution();
				if (errors.size() > 0) {
					for (String error : errors) {
						FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
						FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
					}
					// return "";
				} else {
					WebClient addSolutionClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ss/solution/add");
					SolutionMessage message = new SolutionMessage();
					message.setCatId(solutionBean.getCatId());
					message.setChalId(solutionBean.getChalId());
					message.setCrtdById(userId);
					message.setCrtdDt(new Date());
					message.setDesc(solutionBean.getDesc());
					message.setId(COUNTER.getNextId("IpSolution"));
					if (saveAsOpen) {
						message.setStatusId(2);
					} else {
						message.setStatusId(1);
					}
					message.setTags(solutionBean.getTags());
					message.setTitle(solutionBean.getTitle());
					message.setRvIdCnt(rvIdCnt);
					ResponseMessage response = addSolutionClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
					addSolutionClient.close();
					if (response.getStatusCode() == 0) {
						if (solUploadContent != null) {
							WebClient createBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/create");
							AttachmentMessage attach = new AttachmentMessage();
							attach.setBlobContentType(solutionBean.getContentType());
							attach.setBlobEntityId(message.getId());
							attach.setBlobEntityTblNm("ip_solution");
							attach.setBlobName(solutionBean.getFileName());
							attach.setBlobId(COUNTER.getNextId("IpBlob"));
							Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(attach);
							createBlobClient.close();
							if (crtRes.getStatus() == 200) {
								WebClient client = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/upload/" + attach.getBlobId(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
								client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
								client.header("Accept", "application/json");
								Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(attach.getBlobId().toString(), solUploadContent.getStream(), new ContentDisposition("attachment;filename=" + solutionBean.getFileName())));
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
						solUploadContent = null;
						rvIdCnt = 1;
						rvIds = null;
						saveAsOpen = false;
						if (saveAsOpen) {
							showViewOpenSolution();
						} else {
							showViewSolution();
						}
						FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Solution Saved", "Solution Saved");
						FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
						// return redirectMain();
					} else {
						FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
						FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
						// return "";
					}
				}
			}
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create request", "System error occurred, cannot perform create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			// return "";
		}
	}

	public String updateSolution() {
		try {
			List<String> errors = validateSolution();
			if (errors.size() > 0) {
				for (String error : errors) {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
				return "";
			}
			WebClient updateSolutionClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ss/solution/modify");
			SolutionMessage message = new SolutionMessage();
			message.setCatId(solutionBean.getCatId());
			message.setChalId(solutionBean.getChalId());
			message.setCrtdById(solutionBean.getCrtdById());
			message.setCrtdDt(new Date());
			message.setDesc(solutionBean.getDesc());
			message.setId(solutionBean.getId());
			message.setStatusId(solutionBean.getStatusId());
			message.setTags(solutionBean.getTags());
			message.setTitle(solutionBean.getTitle());
			message.setRvIdCnt(rvIdCnt);
			ResponseMessage response = updateSolutionClient.accept(MediaType.APPLICATION_JSON).put(message, ResponseMessage.class);
			updateSolutionClient.close();
			if (response.getStatusCode() == 0) {
				if (solUploadContent != null) {
					WebClient getBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/getId/" + solutionBean.getId() + "/ip_solution");
					Long blobId = getBlobClient.accept(MediaType.APPLICATION_JSON).get(Long.class);
					if (blobId == -999) {
						WebClient createBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/create");
						AttachmentMessage attach = new AttachmentMessage();
						attach.setBlobContentType(solutionBean.getContentType());
						attach.setBlobEntityId(solutionBean.getId());
						attach.setBlobEntityTblNm("ip_solution");
						attach.setBlobName(solutionBean.getFileName());
						attach.setBlobId(COUNTER.getNextId("IpBlob"));
						Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(attach);
						if (crtRes.getStatus() == 200) {
							WebClient client = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/upload/" + attach.getBlobId(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
							client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
							client.header("Accept", "application/json");
							Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(attach.getBlobId().toString(), solUploadContent.getStream(), new ContentDisposition("attachment;filename=" + solutionBean.getFileName())));
							if (docRes.getStatus() != 200) {
								FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Document Upload Failed", "Document Upload Failed");
								FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
							}
						} else {
							FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Document Upload Failed", "Document Upload Failed");
							FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
						}
					} else {
						WebClient updateBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/update");
						AttachmentMessage attach = new AttachmentMessage();
						attach.setBlobContentType(solutionBean.getContentType());
						attach.setBlobEntityId(solutionBean.getId());
						attach.setBlobEntityTblNm("ip_solution");
						attach.setBlobName(solutionBean.getFileName());
						attach.setBlobId(blobId);
						Response updRes = updateBlobClient.accept(MediaType.APPLICATION_JSON).put(attach);
						updateBlobClient.close();
						if (updRes.getStatus() == 200) {
							WebClient client = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/upload/" + blobId.toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
							client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
							client.header("Accept", "application/json");
							Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(blobId.toString(), solUploadContent.getStream(), new ContentDisposition("attachment;filename=" + solutionBean.getFileName())));
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
				solUploadContent = null;
				rvIdCnt = 1;
				rvIds = null;
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

	public void likeSolution() {
		WebClient addTagClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ts/tag/add");
		TagMessage message = new TagMessage();
		logger.info("In likeSolution()----Solution ID: " + solutionBean.getId());
		message.setEntityId(solutionBean.getId());
		message.setTagId(COUNTER.getNextId("IpTag"));
		message.setTeId(3);
		message.setTtId(1);
		message.setUserId(userId);
		message.setDuplicate(false);
		ResponseMessage response = addTagClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		addTagClient.close();
		if (response.getStatusCode() != 0 && response.getStatusCode() != 2)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Like", "Error While Saving Like"));
		solLikes = RESTServiceHelper.fetchAllBuildonLikes(solutionBean.getId(), 3);
		solLikeCnt = "(" + solLikes.getTags().size() + ")	";
		showSolutionComments = false;
		showSolutionLikes = true;
	}

	public void likeSolutionFromChalSummary() {
		logger.debug("Control handled in likeSolutionFromChalSummary() ");
		Map<String, String> reqMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		Long chalId = Long.valueOf(reqMap.get("chalId"));
		logger.info("challenge id ==" + chalId);
		WebClient addTagClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ts/tag/add");
		TagMessage message = new TagMessage();
		logger.info("Solution ID: " + solutionBean.getId());
		message.setEntityId(solutionBean.getId());
		message.setTagId(COUNTER.getNextId("IpTag"));
		message.setTeId(3);
		message.setTtId(1);
		message.setUserId(userId);
		message.setDuplicate(false);
		ResponseMessage response = addTagClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		addTagClient.close();
		if (response.getStatusCode() != 0 && response.getStatusCode() != 2)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Like", "Error While Saving Like"));
		solLikeCnt = "(" + RESTServiceHelper.fetchAllBuildonLikes(solutionBean.getId(), 3).getTags().size() + ")	";
		showSolutionComments = false;
		showSolutionLikes = true;
		challengeBean = RESTServiceHelper.fetchChallengeById(chalId);
	}

	public void commentSolution() {
		WebClient addTagClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ts/tag/add");
		TagMessage message = new TagMessage();
		message.setEntityId(solutionBean.getId());
		message.setTagId(COUNTER.getNextId("IpTag"));
		message.setTagText(commentText);
		message.setTeId(3);
		message.setTtId(2);
		message.setDuplicate(true);
		message.setUserId(userId);
		ResponseMessage response = addTagClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		addTagClient.close();
		if (response.getStatusCode() != 0)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Comment", "Error While Saving Comment"));
		solComments = RESTServiceHelper.fetchAllBuildonComments(solutionBean.getId(), 3);
		solCommentCnt = "(" + solComments.size() + ")	";
		commentText = "";
		showSolutionComments = true;
		showSolutionLikes = false;
	}

	public void buildOnSolution() {
		WebClient addTagClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ts/tag/add");
		TagMessage message = new TagMessage();
		message.setEntityId(solutionBean.getId());
		message.setTagId(COUNTER.getNextId("IpTag"));
		message.setTagText(buildOnText);
		message.setTeId(3);
		message.setTtId(3);
		message.setDuplicate(true);
		message.setUserId(userId);
		ResponseMessage response = addTagClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		addTagClient.close();
		if (response.getStatusCode() != 0)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Build-On", "Error While Saving Build-On"));
		buildOns = RESTServiceHelper.fetchAllBuildOns(solutionBean.getId(), 3);
		buildOnCnt = "(" + buildOns.size() + ")	";
		buildOnText = "";
		showSolutionComments = false;
		showSolBuildOns = true;
		showSolutionLikes = false;
	}

	public void showCommentSolution() {
		solComments = RESTServiceHelper.fetchAllBuildonComments(solutionBean.getId(), 3);
		showSolutionComments = true;
		showSolutionLikes = false;
	}

	public void updateChallengeReviewer() {
		WebClient updateReviewerClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/cs/challenge/rev/user/" + challengeBean.getId() + "/" + challengeBean.getRevUserId());
		ResponseMessage message = updateReviewerClient.accept(MediaType.APPLICATION_JSON).get(ResponseMessage.class);
		if (message.getStatusCode() == 200) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Update Reviewer Request Success", "Update Reviewer Request Success");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		} else {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Update Reviewer Request Failed", "Update Reviewer Request Failed");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void updateSolutionReviewer() {
		WebClient updateReviewerClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ss/solution/rev/user/" + solutionBean.getId() + "/" + solutionBean.getRevUserId());
		ResponseMessage message = updateReviewerClient.accept(MediaType.APPLICATION_JSON).get(ResponseMessage.class);
		if (message.getStatusCode() == 200) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Update Reviewer Request Success", "Update Reviewer Request Success");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		} else {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Update Reviewer Request Failed", "Update Reviewer Request Failed");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void initializeChalAssignReviews() {
		pGrps = RESTServiceHelper.fetchReviewGroups();
		rvIds = new ArrayList<ReviewBean>();
		if (rvIdCnt != null)
			for (int i = 0; i < rvIdCnt; i++) {
				ReviewBean bean = new ReviewBean();
				bean.setEntityId(COUNTER.getNextId("IpChallenge"));
				bean.setStatusId(i + 1);
				bean.setTblNm("ip_challenge");
				rvIds.add(bean);
			}
	}

	public void assignChalReviews() {
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
			}
			reviewClient.close();
		}
	}

	public void initializeSolAssignReviews() {
		pGrps = RESTServiceHelper.fetchReviewGroups();
		rvIds = new ArrayList<ReviewBean>();
		if (rvIdCnt != null)
			for (int i = 0; i < rvIdCnt; i++) {
				ReviewBean bean = new ReviewBean();
				bean.setEntityId(COUNTER.getNextId("IpSolution"));
				bean.setStatusId(i + 1);
				bean.setTblNm("ip_solution");
				rvIds.add(bean);
			}
	}

	public void assignSolReviews() {
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
			}
			reviewClient.close();
		}
	}

	public void chalFileUploadHandle(FileUploadEvent fue) {
		try {
			UploadedFile file = fue.getFile();
			this.chalUploadContent = new DefaultStreamedContent(file.getInputstream());
			this.challengeBean.setContentType(file.getContentType());
			this.challengeBean.setFileName(file.getFileName());
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void solFileUploadHandle(FileUploadEvent fue) {
		try {
			UploadedFile file = fue.getFile();
			this.solUploadContent = new DefaultStreamedContent(file.getInputstream());
			this.solutionBean.setContentType(file.getContentType());
			this.solutionBean.setFileName(file.getFileName());
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	private List<String> validateSolution() {
		ArrayList<String> ret = new ArrayList<String>();
		if (solutionBean.getChalId() == null || solutionBean.getChalId().toString().length() == 0) {
			ret.add("Challenges is Mandatory");
		}
		if (solutionBean.getTitle() == null || solutionBean.getTitle().length() == 0) {
			ret.add("Title is Mandatory");
		} else if (!lengthValidation(solutionBean.getTitle(), 1, 100)) {
			ret.add("Title sholud not exceed 100 characters");
		}
		if (solutionBean.getDesc() == null || solutionBean.getDesc().length() == 0) {
			ret.add("Description is Mandatory");
		}
		return ret;
	}

	private List<String> validateChallenge() {
		ArrayList<String> ret = new ArrayList<String>();
		if (challengeBean.getTitle() == null || challengeBean.getTitle().length() == 0) {
			ret.add("Title is Mandatory");
		} else if (!lengthValidation(challengeBean.getTitle(), 1, 100)) {
			ret.add("Title sholud not exceed 100 characters");
		}
		if (challengeBean.getCatId() == null || challengeBean.getCatId().toString().length() == 0) {
			ret.add("Category is Mandatory");
		}
		if (challengeBean.getDesc() == null || challengeBean.getDesc().length() == 0) {
			ret.add("Description is Mandatory");
		}
		if (challengeBean.getLaunchDt() == null || challengeBean.getLaunchDt().toString().length() == 0) {
			ret.add("Launch Date is Mandatory");
		}
		if (challengeBean.getExprDt() == null || challengeBean.getExprDt().toString().length() == 0) {
			ret.add("Expiry Date is Mandatory");
		}
		if (rvIdCnt == 0 || rvIds == null || rvIds.size() == 0 || rvIds.get(0).getGroupId() == null || rvIds.get(0).getGroupId().length == 0) {
			ret.add("Please assign reviewers");
		}
		return ret;
	}

	// This method is used to check length validation
	public boolean lengthValidation(String str, int minLimit, int maxLimit) {
		int intLength = str.length();
		if (intLength >= minLimit && intLength <= maxLimit) {
			return true;
		} else {
			return false;
		}
	}

	public ChallengeBean getChallengeBean() {
		if (challengeBean == null)
			challengeBean = new ChallengeBean();
		return challengeBean;
	}

	public void setChallengeBean(ChallengeBean challengeBean) {
		this.challengeBean = challengeBean;
	}

	public List<ChallengeBean> getViewChallenges() {
		if (viewChallenges == null)
			viewChallenges = new ArrayList<ChallengeBean>();
		return viewChallenges;
	}

	public void setViewChallenges(List<ChallengeBean> viewChallenges) {
		this.viewChallenges = viewChallenges;
	}

	public List<UserBean> getAdmUsers() {
		if (admUsers == null)
			admUsers = new ArrayList<UserBean>();
		return admUsers;
	}

	public void setAdmUsers(List<UserBean> admUsers) {
		this.admUsers = admUsers;
	}

	public List<ListSelectorBean> getChallengeCats() {
		if (challengeCats == null)
			challengeCats = new ArrayList<ListSelectorBean>();
		return challengeCats;
	}

	public void setChallengeCats(List<ListSelectorBean> challengeCats) {
		this.challengeCats = challengeCats;
	}

	public List<ListSelectorBean> getChallengeStatuses() {
		if (challengeStatuses == null)
			challengeStatuses = new ArrayList<ListSelectorBean>();
		return challengeStatuses;
	}

	public void setChallengeStatuses(List<ListSelectorBean> challengeStatuses) {
		this.challengeStatuses = challengeStatuses;
	}

	public String getCommentText() {
		if (commentText == null)
			commentText = new String();
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public boolean isChalFileAvail() {
		return chalFileAvail;
	}

	public void setChalFileAvail(boolean chalFileAvail) {
		this.chalFileAvail = chalFileAvail;
	}

	public boolean isShowChallengeComments() {
		return showChallengeComments;
	}

	public void setShowChallengeComments(boolean showChallengeComments) {
		this.showChallengeComments = showChallengeComments;
	}

	public boolean isShowChallengeLikes() {
		return showChallengeLikes;
	}

	public void setShowChallengeLikes(boolean showChallengeLikes) {
		this.showChallengeLikes = showChallengeLikes;
	}

	public StreamedContent getChalFileContent() {
		return chalFileContent;
	}

	public void setChalFileContent(StreamedContent chalFileContent) {
		this.chalFileContent = chalFileContent;
	}

	public TagCloudModel getChalLikes() {
		return chalLikes;
	}

	public void setChalLikes(TagCloudModel chalLikes) {
		this.chalLikes = chalLikes;
	}

	public List<TagBean> getChalComments() {
		if (chalComments == null)
			chalComments = new ArrayList<TagBean>();
		return chalComments;
	}

	public void setChalComments(List<TagBean> chalComments) {
		this.chalComments = chalComments;
	}

	public TagCloudModel getSolLikes() {
		return solLikes;
	}

	public void setSolLikes(TagCloudModel solLikes) {
		this.solLikes = solLikes;
	}

	public List<TagBean> getSolComments() {
		if (solComments == null)
			solComments = new ArrayList<TagBean>();
		return solComments;
	}

	public void setSolComments(List<TagBean> solComments) {
		this.solComments = solComments;
	}

	public String getChalLikeCnt() {
		if (chalLikeCnt == null)
			chalLikeCnt = "";
		return chalLikeCnt;
	}

	public void setChalLikeCnt(String chalLikeCnt) {
		this.chalLikeCnt = chalLikeCnt;
	}

	public String getChalCommentCnt() {
		if (chalCommentCnt == null)
			chalCommentCnt = "";
		return chalCommentCnt;
	}

	public void setChalCommentCnt(String chalCommentCnt) {
		this.chalCommentCnt = chalCommentCnt;
	}

	public String getSolLikeCnt() {
		if (solLikeCnt == null)
			solLikeCnt = "";
		return solLikeCnt;
	}

	public void setSolLikeCnt(String solLikeCnt) {
		this.solLikeCnt = solLikeCnt;
	}

	public String getSolCommentCnt() {
		if (solCommentCnt == null)
			solCommentCnt = "";
		return solCommentCnt;
	}

	public void setSolCommentCnt(String solCommentCnt) {
		this.solCommentCnt = solCommentCnt;
	}

	public SolutionBean getSolutionBean() {
		if (solutionBean == null)
			solutionBean = new SolutionBean();
		return solutionBean;
	}

	public void setSolutionBean(SolutionBean solutionBean) {
		this.solutionBean = solutionBean;
	}

	public List<SolutionBean> getViewSolutions() {
		if (viewSolutions == null)
			viewSolutions = new ArrayList<SolutionBean>();
		return viewSolutions;
	}

	public void setViewSolutions(List<SolutionBean> viewSolutions) {
		this.viewSolutions = viewSolutions;
	}

	public List<ListSelectorBean> getSolutionCats() {
		if (solutionCats == null)
			solutionCats = new ArrayList<ListSelectorBean>();
		return solutionCats;
	}

	public void setSolutionCats(List<ListSelectorBean> solutionCats) {
		this.solutionCats = solutionCats;
	}

	public List<ListSelectorBean> getSolutionStatuses() {
		if (solutionStatuses == null)
			solutionStatuses = new ArrayList<ListSelectorBean>();
		return solutionStatuses;
	}

	public void setSolutionStatuses(List<ListSelectorBean> solutionStatuses) {
		this.solutionStatuses = solutionStatuses;
	}

	public boolean isShowSolutionComments() {
		return showSolutionComments;
	}

	public void setShowSolutionComments(boolean showSolutionComments) {
		this.showSolutionComments = showSolutionComments;
	}

	public boolean isShowSolutionLikes() {
		return showSolutionLikes;
	}

	public void setShowSolutionLikes(boolean showSolutionLikes) {
		this.showSolutionLikes = showSolutionLikes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static IdNumberGen getCounter() {
		return COUNTER;
	}

	public Date getSelLaunchDate() {
		return selLaunchDate;
	}

	public void setSelLaunchDate(Date selLaunchDate) {
		this.selLaunchDate = selLaunchDate;
	}

	public StreamedContent getChalUploadContent() {
		return chalUploadContent;
	}

	public StreamedContent getSolUploadContent() {
		return solUploadContent;
	}

	public void setChalUploadContent(StreamedContent chalUploadContent) {
		this.chalUploadContent = chalUploadContent;
	}

	public void setSolUploadContent(StreamedContent solUploadContent) {
		this.solUploadContent = solUploadContent;
	}

	public boolean isSolFileAvail() {
		return solFileAvail;
	}

	public StreamedContent getSolFileContent() {
		return solFileContent;
	}

	public void setSolFileAvail(boolean solFileAvail) {
		this.solFileAvail = solFileAvail;
	}

	public void setSolFileContent(StreamedContent solFileContent) {
		this.solFileContent = solFileContent;
	}

	public List<TagBean> getBuildOns() {
		if (buildOns == null)
			buildOns = new ArrayList<TagBean>();
		return buildOns;
	}

	public String getBuildOnText() {
		if (buildOnText == null)
			buildOnText = "";
		return buildOnText;
	}

	public boolean isShowSolBuildOns() {
		return showSolBuildOns;
	}

	public String getBuildOnCnt() {
		if (buildOnCnt == null)
			buildOnCnt = "";
		return buildOnCnt;
	}

	public void setBuildOns(List<TagBean> buildOns) {
		this.buildOns = buildOns;
	}

	public void setBuildOnText(String buildOnText) {
		this.buildOnText = buildOnText;
	}

	public void setShowSolBuildOns(boolean showSolBuildOns) {
		this.showSolBuildOns = showSolBuildOns;
	}

	public void setBuildOnCnt(String buildOnCnt) {
		this.buildOnCnt = buildOnCnt;
	}

	public List<GroupBean> getpGrps() {
		if (pGrps == null)
			pGrps = new ArrayList<GroupBean>();
		return pGrps;
	}

	public void setpGrps(List<GroupBean> pGrps) {
		this.pGrps = pGrps;
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

	public Long getChalId() {
		return chalId;
	}

	public void setChalId(Long chalId) {
		this.chalId = chalId;
	}

	public int getActiveIndex() {
		return activeIndex;
	}

	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}

	public boolean isShowPubChal() {
		return showPubChal;
	}

	public void setShowPubChal(boolean showPubChal) {
		this.showPubChal = showPubChal;
	}

	public boolean isShowViewChal() {
		return showViewChal;
	}

	public void setShowViewChal(boolean showViewChal) {
		this.showViewChal = showViewChal;
	}

	public boolean isShowCrtChal() {
		return showCrtChal;
	}

	public void setShowCrtChal(boolean showCrtChal) {
		this.showCrtChal = showCrtChal;
	}

	public boolean isShowPubSol() {
		return showPubSol;
	}

	public void setShowPubSol(boolean showPubSol) {
		this.showPubSol = showPubSol;
	}

	public boolean isShowViewSol() {
		return showViewSol;
	}

	public void setShowViewSol(boolean showViewSol) {
		this.showViewSol = showViewSol;
	}

	public boolean isShowCrtSol() {
		return showCrtSol;
	}

	public void setShowCrtSol(boolean showCrtSol) {
		this.showCrtSol = showCrtSol;
	}

	public String getReturnView() {
		if (returnView == null)
			returnView = "";
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

	public boolean isShowReviewChal() {
		return showReviewChal;
	}

	public void setShowReviewChal(boolean showReviewChal) {
		this.showReviewChal = showReviewChal;
	}

	public boolean isShowReviewSol() {
		return showReviewSol;
	}

	public void setShowReviewSol(boolean showReviewSol) {
		this.showReviewSol = showReviewSol;
	}

	public boolean isChalTitleAvail() {
		return chalTitleAvail;
	}

	public void setChalTitleAvail(boolean chalTitleAvail) {
		this.chalTitleAvail = chalTitleAvail;
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

	public AccessController getController() {
		if (controller == null || controller.getFunctions() == null) {
			PortletRequest request = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			User user = (User) request.getAttribute(WebKeys.USER);
			WebClient client = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/verify/" + user.getScreenName());
			UserMessage message = client.accept(MediaType.APPLICATION_JSON).get(UserMessage.class);
			userId = message.getuId();
			controller = new AccessController(userId);
		}
		return controller;
	}

	public void setController(AccessController controller) {
		this.controller = controller;
	}
}
