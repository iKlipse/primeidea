package za.co.idea.web.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudModel;

import za.co.idea.ip.ws.bean.AttachmentMessage;
import za.co.idea.ip.ws.bean.ChallengeMessage;
import za.co.idea.ip.ws.bean.GroupMessage;
import za.co.idea.ip.ws.bean.MetaDataMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;
import za.co.idea.ip.ws.bean.SolutionMessage;
import za.co.idea.ip.ws.bean.TagMessage;
import za.co.idea.ip.ws.bean.UserMessage;
import za.co.idea.ip.ws.util.CustomObjectMapper;
import za.co.idea.web.ui.bean.ChallengeBean;
import za.co.idea.web.ui.bean.GroupBean;
import za.co.idea.web.ui.bean.ListSelectorBean;
import za.co.idea.web.ui.bean.SolutionBean;
import za.co.idea.web.ui.bean.TagBean;
import za.co.idea.web.ui.bean.UserBean;
import za.co.idea.web.util.IdNumberGen;

public class ChallengeController implements Serializable {

	private static final long serialVersionUID = -6939719926402016671L;
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
	private DualListModel<GroupBean> groupTwinSelect;
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

	public String showCreateChallenge() {
		try {
			admUsers = fetchAllUsers();
			challengeCats = fetchAllChallengeCat();
			challengeStatuses = fetchAllChallengeStatuses();
			pGrps = fetchAllGroups();
			groupTwinSelect = new DualListModel<GroupBean>(pGrps, new ArrayList<GroupBean>());
			challengeBean = new ChallengeBean();
			return "chalcc";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create request", "System error occurred, cannot perform create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showViewChallenge() {
		try {
			viewChallenges = fetchAllChallenges();
			challengeCats = fetchAllChallengeCat();
			admUsers = fetchAllUsers();
			challengeStatuses = fetchAllChallengeStatuses();
			return "chalvc";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showViewChallengeByUser() {
		try {
			viewChallenges = fetchAllChallengesByUser();
			challengeCats = fetchAllChallengeCat();
			admUsers = fetchAllUsers();
			challengeStatuses = fetchAllChallengeStatuses();
			return "chaluc";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showEditChallenge() {
		try {
			challengeCats = fetchAllChallengeCat();
			admUsers = fetchAllUsers();
			challengeStatuses = fetchNextChallengeStatuses();
			pGrps = fetchAllGroups();
			groupTwinSelect = initializeSelectedGroups(pGrps);
			try {
				WebClient getBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getId/" + challengeBean.getId() + "/ip_challenge");
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
						chalFileAvail = false;
						challengeBean.setFileName(attachment.getContentDisposition().toString().replace("attachment; filename=", ""));
						chalFileContent = new DefaultStreamedContent(attachment.getDataHandler().getInputStream(), null, blobName);
					} else {
						chalFileAvail = true;
						chalFileContent = null;
					}
				} else {
					chalFileAvail = true;
					chalFileContent = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform edit request", "System error occurred, cannot perform edit request");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				chalFileAvail = true;
				chalFileContent = null;
			}
			return "chalec";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform edit request", "System error occurred, cannot perform edit request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showSummaryChallenge() {
		chalLikes = fetchAllChalLikes();
		chalComments = fetchAllChalComments();
		chalLikeCnt = "(" + chalLikes.getTags().size() + ")	";
		chalCommentCnt = "(" + chalComments.size() + ")	";
		showChallengeComments = false;
		showChallengeLikes = false;
		viewSolutions = fetchAllSolutionsByChal();
		try {
			WebClient getBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getId/" + challengeBean.getId() + "/ip_challenge");
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
					chalFileAvail = false;
					challengeBean.setFileName(attachment.getContentDisposition().toString().replace("attachment; filename=", ""));
					chalFileContent = new DefaultStreamedContent(attachment.getDataHandler().getInputStream(), null, blobName);
				} else {
					chalFileAvail = true;
					chalFileContent = null;
				}
			} else {
				chalFileAvail = true;
				chalFileContent = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform summary request", "System error occurred, cannot perform summary request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			chalFileAvail = true;
			chalFileContent = null;
		}
		return "chalsc";
	}

	public String saveChallenge() {
		try {
			List<String> errors = validateChallenge();
			if (errors.size() > 0) {
				for (String error : errors) {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
				return "";
			}
			WebClient addChallengeClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/cs/challenge/add");
			ChallengeMessage message = new ChallengeMessage();
			message.setCatId(challengeBean.getCatId());
			message.setCrtdById((Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId"));
			message.setCrtdDt(new Date());
			message.setDesc(challengeBean.getDesc());
			message.setExprDt(challengeBean.getExprDt());
			message.setHoverText(challengeBean.getHoverText());
			message.setId(COUNTER.getNextId("IpChallenge"));
			message.setLaunchDt(challengeBean.getLaunchDt());
			message.setStatusId(1);
			message.setTag(challengeBean.getTag());
			message.setTitle(challengeBean.getTitle());
			message.setGroupIdList(getSelGroupIds());
			ResponseMessage response = addChallengeClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
			addChallengeClient.close();
			if (response.getStatusCode() == 0) {
				if (chalUploadContent != null) {
					WebClient createBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/create");
					AttachmentMessage attach = new AttachmentMessage();
					attach.setBlobContentType(challengeBean.getContentType());
					attach.setBlobEntityId(message.getId());
					attach.setBlobEntityTblNm("ip_challenge");
					attach.setBlobName(challengeBean.getFileName());
					attach.setBlobId(COUNTER.getNextId("IpBlob"));
					Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(attach);
					if (crtRes.getStatus() == 200) {
						WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/upload/" + attach.getBlobId(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
						client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
						client.header("Accept", "application/json");
						Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(attach.getBlobId().toString(), chalUploadContent.getStream(), new ContentDisposition("attachment; filename=" + challengeBean.getFileName())));
						if (docRes.getStatus() != 200) {
							FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Document Upload Failed", "Document Upload Failed");
							FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
						}
					} else {
						FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Document Upload Failed", "Document Upload Failed");
						FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
					}
				}
				chalUploadContent = null;
				return showViewChallenge();
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create request", "System error occurred, cannot perform create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String updateChallenge() {
		try {
			List<String> errors = validateChallenge();
			if (errors.size() > 0) {
				for (String error : errors) {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
				return "";
			}
			WebClient updateChallengeClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/cs/challenge/modify");
			ChallengeMessage message = new ChallengeMessage();
			message.setCatId(challengeBean.getCatId());
			message.setCrtdById((Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId"));
			message.setCrtdDt(challengeBean.getCrtdDt());
			message.setDesc(challengeBean.getDesc());
			message.setExprDt(challengeBean.getExprDt());
			message.setHoverText(challengeBean.getHoverText());
			message.setId(challengeBean.getId());
			message.setLaunchDt(challengeBean.getLaunchDt());
			message.setStatusId(challengeBean.getStatusId());
			message.setTag(challengeBean.getTag());
			message.setTitle(challengeBean.getTitle());
			message.setGroupIdList(getSelGroupIds());
			ResponseMessage response = updateChallengeClient.accept(MediaType.APPLICATION_JSON).put(message, ResponseMessage.class);
			updateChallengeClient.close();
			if (response.getStatusCode() == 0) {
				if (chalUploadContent != null) {
					WebClient getBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getId/" + challengeBean.getId() + "/ip_challenge");
					Long blobId = getBlobClient.accept(MediaType.APPLICATION_JSON).get(Long.class);
					if (blobId == -999) {
						WebClient createBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/create");
						AttachmentMessage attach = new AttachmentMessage();
						attach.setBlobContentType(challengeBean.getContentType());
						attach.setBlobEntityId(challengeBean.getId());
						attach.setBlobEntityTblNm("ip_challenge");
						attach.setBlobName(challengeBean.getFileName());
						attach.setBlobId(COUNTER.getNextId("IpBlob"));
						Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(attach);
						if (crtRes.getStatus() == 200) {
							WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/upload/" + attach.getBlobId(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
							client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
							client.header("Accept", "application/json");
							Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(attach.getBlobId().toString(), chalUploadContent.getStream(), new ContentDisposition("attachment; filename=" + challengeBean.getFileName())));
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
						AttachmentMessage attach = new AttachmentMessage();
						attach.setBlobContentType(challengeBean.getContentType());
						attach.setBlobEntityId(challengeBean.getId());
						attach.setBlobEntityTblNm("ip_challenge");
						attach.setBlobName(challengeBean.getFileName());
						attach.setBlobId(blobId);
						Response updRes = updateBlobClient.accept(MediaType.APPLICATION_JSON).put(attach);
						updateBlobClient.close();
						if (updRes.getStatus() == 200) {
							WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/upload/" + blobId.toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
							client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
							client.header("Accept", "application/json");
							Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(blobId.toString(), chalUploadContent.getStream(), new ContentDisposition("attachment; filename=" + challengeBean.getFileName())));
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
				chalUploadContent = null;
				return showViewChallenge();
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

	public void commentChallenge() {
		WebClient addTagClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ts/tag/add");
		TagMessage message = new TagMessage();
		message.setEntityId(challengeBean.getId());
		message.setTagId(COUNTER.getNextId("IpTag"));
		message.setTagText(commentText);
		message.setTeId(2);
		message.setTtId(2);
		message.setDuplicate(true);
		message.setUserId((Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId"));
		ResponseMessage response = addTagClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		addTagClient.close();
		if (response.getStatusCode() != 0)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Comment", "Error While Saving Comment"));
		chalComments = fetchAllChalComments();
		chalCommentCnt = "(" + chalComments.size() + ")	";
		commentText = "";
		showChallengeComments = true;
		showChallengeLikes = false;
	}

	public String likeChallenge() {
		WebClient addTagClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ts/tag/add");
		TagMessage message = new TagMessage();
		message.setEntityId(challengeBean.getId());
		message.setTagId(COUNTER.getNextId("IpTag"));
		message.setTeId(2);
		message.setTtId(1);
		message.setUserId((Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId"));
		message.setDuplicate(false);
		ResponseMessage response = addTagClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		addTagClient.close();
		if (response.getStatusCode() != 0 && response.getStatusCode() != 2)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Like", "Error While Saving Like"));
		chalLikes = fetchAllChalLikes();
		chalLikeCnt = "(" + chalLikes.getTags().size() + ")	";
		showChallengeComments = false;
		showChallengeLikes = true;
		return "";
	}

	public String showCommentChallenge() {
		chalComments = fetchAllChalComments();
		showChallengeComments = true;
		showChallengeLikes = false;
		return "";
	}

	public String showCreateSolution() {
		try {
			admUsers = fetchAllUsers();
			viewChallenges = fetchAllAvailableChallenges();
			solutionCats = fetchAllSolutionCat();
			solutionStatuses = fetchAllSolutionStatuses();
			solutionBean = new SolutionBean();
			return "solcs";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create request", "System error occurred, cannot perform create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showViewSolution() {
		try {
			admUsers = fetchAllUsers();
			viewChallenges = fetchAllChallenges();
			solutionCats = fetchAllSolutionCat();
			solutionStatuses = fetchAllSolutionStatuses();
			solutionBean = new SolutionBean();
			viewSolutions = fetchAllSolutions();
			return "solvs";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showViewSolutionByUser() {
		try {
			admUsers = fetchAllUsers();
			viewChallenges = fetchAllChallenges();
			solutionCats = fetchAllSolutionCat();
			solutionStatuses = fetchAllSolutionStatuses();
			solutionBean = new SolutionBean();
			viewSolutions = fetchAllSolutionsByUser();
			return "solus";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showEditSolution() {
		try {
			admUsers = fetchAllUsers();
			viewChallenges = fetchAllChallenges();
			solutionCats = fetchAllSolutionCat();
			solutionStatuses = fetchNextSolutionStatuses();
			try {
				WebClient getBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getId/" + solutionBean.getId() + "/ip_solution");
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
						solFileAvail = false;
						solutionBean.setFileName(attachment.getContentDisposition().toString().replace("attachment; filename=", ""));
						solFileContent = new DefaultStreamedContent(attachment.getDataHandler().getInputStream(), null, blobName);
					} else {
						solFileAvail = true;
						solFileContent = null;
					}
				} else {
					solFileAvail = true;
					solFileContent = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform updated view request", "System error occurred, cannot perform updated view request");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				solFileAvail = false;
				solFileContent = null;
			}
			return "soles";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform updated view request", "System error occurred, cannot perform updated view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showSummarySolution() {
		solLikes = fetchAllSolLikes();
		solComments = fetchAllSolComments();
		solLikeCnt = "(" + solLikes.getTags().size() + ")	";
		solCommentCnt = "(" + solComments.size() + ")	";
		buildOns = fetchAllBuildOns();
		buildOnCnt = "(" + buildOns.size() + ")	";
		buildOnText = "";
		showSolutionComments = false;
		showSolBuildOns = true;
		showSolutionLikes = false;
		try {
			WebClient getBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getId/" + solutionBean.getId() + "/ip_solution");
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
					solFileAvail = false;
					solutionBean.setFileName(attachment.getContentDisposition().toString().replace("attachment; filename=", ""));
					solFileContent = new DefaultStreamedContent(attachment.getDataHandler().getInputStream(), null, blobName);
				} else {
					solFileAvail = true;
					solFileContent = null;
				}
			} else {
				solFileAvail = true;
				solFileContent = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create request", "System error occurred, cannot perform create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			solFileAvail = false;
			solFileContent = null;
		}
		return "solss";
	}

	public String saveSolution() {
		try {
			List<String> errors = validateSolution();
			if (errors.size() > 0) {
				for (String error : errors) {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
				return "";
			}
			WebClient addSolutionClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ss/solution/add");
			SolutionMessage message = new SolutionMessage();
			message.setCatId(solutionBean.getCatId());
			message.setChalId(solutionBean.getChalId());
			message.setCrtdById((Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId"));
			message.setCrtdDt(new Date());
			message.setDesc(solutionBean.getDesc());
			message.setId(COUNTER.getNextId("IpSolution"));
			message.setStatusId(1);
			message.setTags(solutionBean.getTags());
			message.setTitle(solutionBean.getTitle());
			ResponseMessage response = addSolutionClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
			addSolutionClient.close();
			if (response.getStatusCode() == 0) {
				if (solUploadContent != null) {
					WebClient createBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/create");
					AttachmentMessage attach = new AttachmentMessage();
					attach.setBlobContentType(solutionBean.getContentType());
					attach.setBlobEntityId(message.getId());
					attach.setBlobEntityTblNm("ip_solution");
					attach.setBlobName(solutionBean.getFileName());
					attach.setBlobId(COUNTER.getNextId("IpBlob"));
					Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(attach);
					createBlobClient.close();
					if (crtRes.getStatus() == 200) {
						WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/upload/" + attach.getBlobId(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
						client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
						client.header("Accept", "application/json");
						Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(attach.getBlobId().toString(), solUploadContent.getStream(), new ContentDisposition("attachment; filename=" + solutionBean.getFileName())));
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
				return showViewSolution();
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create request", "System error occurred, cannot perform create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
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
			WebClient updateSolutionClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ss/solution/modify");
			SolutionMessage message = new SolutionMessage();
			message.setCatId(solutionBean.getCatId());
			message.setChalId(solutionBean.getChalId());
			message.setCrtdById((Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId"));
			message.setCrtdDt(new Date());
			message.setDesc(solutionBean.getDesc());
			message.setId(solutionBean.getId());
			message.setStatusId(solutionBean.getStatusId());
			message.setTags(solutionBean.getTags());
			message.setTitle(solutionBean.getTitle());
			ResponseMessage response = updateSolutionClient.accept(MediaType.APPLICATION_JSON).put(message, ResponseMessage.class);
			updateSolutionClient.close();
			if (response.getStatusCode() == 0) {
				if (solUploadContent != null) {
					WebClient getBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getId/" + solutionBean.getId() + "/ip_solution");
					Long blobId = getBlobClient.accept(MediaType.APPLICATION_JSON).get(Long.class);
					if (blobId == -999) {
						WebClient createBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/create");
						AttachmentMessage attach = new AttachmentMessage();
						attach.setBlobContentType(solutionBean.getContentType());
						attach.setBlobEntityId(solutionBean.getId());
						attach.setBlobEntityTblNm("ip_solution");
						attach.setBlobName(solutionBean.getFileName());
						attach.setBlobId(COUNTER.getNextId("IpBlob"));
						Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(attach);
						if (crtRes.getStatus() == 200) {
							WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/upload/" + attach.getBlobId(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
							client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
							client.header("Accept", "application/json");
							Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(attach.getBlobId().toString(), solUploadContent.getStream(), new ContentDisposition("attachment; filename=" + solutionBean.getFileName())));
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
						AttachmentMessage attach = new AttachmentMessage();
						attach.setBlobContentType(solutionBean.getContentType());
						attach.setBlobEntityId(solutionBean.getId());
						attach.setBlobEntityTblNm("ip_solution");
						attach.setBlobName(solutionBean.getFileName());
						attach.setBlobId(blobId);
						Response updRes = updateBlobClient.accept(MediaType.APPLICATION_JSON).put(attach);
						updateBlobClient.close();
						if (updRes.getStatus() == 200) {
							WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/upload/" + blobId.toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
							client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
							client.header("Accept", "application/json");
							Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(blobId.toString(), solUploadContent.getStream(), new ContentDisposition("attachment; filename=" + solutionBean.getFileName())));
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
				return showViewSolution();
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

	public String likeSolution() {
		WebClient addTagClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ts/tag/add");
		TagMessage message = new TagMessage();
		message.setEntityId(solutionBean.getId());
		message.setTagId(COUNTER.getNextId("IpTag"));
		message.setTeId(3);
		message.setTtId(1);
		message.setUserId((Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId"));
		message.setDuplicate(false);
		ResponseMessage response = addTagClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		addTagClient.close();
		if (response.getStatusCode() != 0 && response.getStatusCode() != 2)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Like", "Error While Saving Like"));
		solLikes = fetchAllSolLikes();
		solLikeCnt = "(" + solLikes.getTags().size() + ")	";
		showSolutionComments = false;
		showSolutionLikes = true;
		return "";
	}

	public void commentSolution() {
		WebClient addTagClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ts/tag/add");
		TagMessage message = new TagMessage();
		message.setEntityId(solutionBean.getId());
		message.setTagId(COUNTER.getNextId("IpTag"));
		message.setTagText(commentText);
		message.setTeId(3);
		message.setTtId(2);
		message.setDuplicate(true);
		message.setUserId((Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId"));
		ResponseMessage response = addTagClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		addTagClient.close();
		if (response.getStatusCode() != 0)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Comment", "Error While Saving Comment"));
		solComments = fetchAllSolComments();
		solCommentCnt = "(" + solComments.size() + ")	";
		commentText = "";
		showSolutionComments = true;
		showSolutionLikes = false;
	}

	public String buildOnSolution() {
		WebClient addTagClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ts/tag/add");
		TagMessage message = new TagMessage();
		message.setEntityId(solutionBean.getId());
		message.setTagId(COUNTER.getNextId("IpTag"));
		message.setTagText(buildOnText);
		message.setTeId(3);
		message.setTtId(3);
		message.setDuplicate(true);
		message.setUserId((Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId"));
		ResponseMessage response = addTagClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		addTagClient.close();
		if (response.getStatusCode() != 0)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Build-On", "Error While Saving Build-On"));
		buildOns = fetchAllBuildOns();
		buildOnCnt = "(" + buildOns.size() + ")	";
		buildOnText = "";
		showSolutionComments = false;
		showSolBuildOns = true;
		showSolutionLikes = false;
		return "";
	}

	public String showCommentSolution() {
		solComments = fetchAllSolComments();
		showSolutionComments = true;
		showSolutionLikes = false;
		return "";
	}

	private List<TagBean> fetchAllBuildOns() {
		WebClient fetchIdeaBuildOnsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ts/tag/get/" + solutionBean.getId() + "/3/3");
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

	private List<ChallengeBean> fetchAllChallenges() {
		List<ChallengeBean> ret = new ArrayList<ChallengeBean>();
		WebClient fetchChallengeClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/cs/challenge/list");
		Collection<? extends ChallengeMessage> challenges = new ArrayList<ChallengeMessage>(fetchChallengeClient.accept(MediaType.APPLICATION_JSON).getCollection(ChallengeMessage.class));
		fetchChallengeClient.close();
		for (ChallengeMessage challengeMessage : challenges) {
			ChallengeBean bean = new ChallengeBean();
			bean.setCatId(challengeMessage.getCatId());
			bean.setCrtdById(challengeMessage.getCrtdById());
			bean.setCrtdDt(challengeMessage.getCrtdDt());
			bean.setDesc(challengeMessage.getDesc());
			bean.setExprDt(challengeMessage.getExprDt());
			bean.setHoverText(challengeMessage.getHoverText());
			bean.setId(challengeMessage.getId());
			bean.setLaunchDt(challengeMessage.getLaunchDt());
			bean.setStatusId(challengeMessage.getStatusId());
			bean.setTag(challengeMessage.getTag());
			bean.setTitle(challengeMessage.getTitle());
			bean.setGroupIdList(getIdsFromArray(challengeMessage.getGroupIdList()));
			ret.add(bean);
		}
		return ret;
	}

	private List<ChallengeBean> fetchAllAvailableChallenges() {
		List<ChallengeBean> ret = new ArrayList<ChallengeBean>();
		WebClient fetchChallengeClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/cs/challenge/list//status/4");
		Collection<? extends ChallengeMessage> challenges = new ArrayList<ChallengeMessage>(fetchChallengeClient.accept(MediaType.APPLICATION_JSON).getCollection(ChallengeMessage.class));
		fetchChallengeClient.close();
		for (ChallengeMessage challengeMessage : challenges) {
			ChallengeBean bean = new ChallengeBean();
			bean.setCatId(challengeMessage.getCatId());
			bean.setCrtdById(challengeMessage.getCrtdById());
			bean.setCrtdDt(challengeMessage.getCrtdDt());
			bean.setDesc(challengeMessage.getDesc());
			bean.setExprDt(challengeMessage.getExprDt());
			bean.setHoverText(challengeMessage.getHoverText());
			bean.setId(challengeMessage.getId());
			bean.setLaunchDt(challengeMessage.getLaunchDt());
			bean.setStatusId(challengeMessage.getStatusId());
			bean.setTag(challengeMessage.getTag());
			bean.setTitle(challengeMessage.getTitle());
			ret.add(bean);
		}
		return ret;
	}

	private List<ChallengeBean> fetchAllChallengesByUser() {
		List<ChallengeBean> ret = new ArrayList<ChallengeBean>();
		WebClient fetchChallengeClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/cs/challenge/list/" + ((Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId")).longValue());
		Collection<? extends ChallengeMessage> challenges = new ArrayList<ChallengeMessage>(fetchChallengeClient.accept(MediaType.APPLICATION_JSON).getCollection(ChallengeMessage.class));
		fetchChallengeClient.close();
		for (ChallengeMessage challengeMessage : challenges) {
			ChallengeBean bean = new ChallengeBean();
			bean.setCatId(challengeMessage.getCatId());
			bean.setCrtdById(challengeMessage.getCrtdById());
			bean.setCrtdDt(challengeMessage.getCrtdDt());
			bean.setDesc(challengeMessage.getDesc());
			bean.setExprDt(challengeMessage.getExprDt());
			bean.setHoverText(challengeMessage.getHoverText());
			bean.setId(challengeMessage.getId());
			bean.setLaunchDt(challengeMessage.getLaunchDt());
			bean.setStatusId(challengeMessage.getStatusId());
			bean.setTag(challengeMessage.getTag());
			bean.setTitle(challengeMessage.getTitle());
			ret.add(bean);
		}
		return ret;
	}

	private List<SolutionBean> fetchAllSolutions() {
		List<SolutionBean> ret = new ArrayList<SolutionBean>();
		WebClient fetchSolutionClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ss/solution/list");
		Collection<? extends SolutionMessage> solutions = new ArrayList<SolutionMessage>(fetchSolutionClient.accept(MediaType.APPLICATION_JSON).getCollection(SolutionMessage.class));
		fetchSolutionClient.close();
		for (SolutionMessage solutionMessage : solutions) {
			SolutionBean bean = new SolutionBean();
			bean.setChalId(solutionMessage.getChalId());
			bean.setCatId(solutionMessage.getCatId());
			bean.setCrtdById(solutionMessage.getCrtdById());
			bean.setCrtdDt(solutionMessage.getCrtdDt());
			bean.setDesc(solutionMessage.getDesc());
			bean.setId(solutionMessage.getId());
			bean.setStatusId(solutionMessage.getStatusId());
			bean.setTags(solutionMessage.getTags());
			bean.setTitle(solutionMessage.getTitle());
			ret.add(bean);
		}
		return ret;
	}

	private List<SolutionBean> fetchAllSolutionsByUser() {
		List<SolutionBean> ret = new ArrayList<SolutionBean>();
		WebClient fetchSolutionClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ss/solution/list/" + ((Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId")).longValue());
		Collection<? extends SolutionMessage> solutions = new ArrayList<SolutionMessage>(fetchSolutionClient.accept(MediaType.APPLICATION_JSON).getCollection(SolutionMessage.class));
		fetchSolutionClient.close();
		for (SolutionMessage solutionMessage : solutions) {
			SolutionBean bean = new SolutionBean();
			bean.setChalId(solutionMessage.getChalId());
			bean.setCatId(solutionMessage.getCatId());
			bean.setCrtdById(solutionMessage.getCrtdById());
			bean.setCrtdDt(solutionMessage.getCrtdDt());
			bean.setDesc(solutionMessage.getDesc());
			bean.setId(solutionMessage.getId());
			bean.setStatusId(solutionMessage.getStatusId());
			bean.setTags(solutionMessage.getTags());
			bean.setTitle(solutionMessage.getTitle());
			ret.add(bean);
		}
		return ret;
	}

	private List<SolutionBean> fetchAllSolutionsByChal() {
		List<SolutionBean> ret = new ArrayList<SolutionBean>();
		WebClient fetchSolutionClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ss/solution/list/chal/" + challengeBean.getId());
		Collection<? extends SolutionMessage> solutions = new ArrayList<SolutionMessage>(fetchSolutionClient.accept(MediaType.APPLICATION_JSON).getCollection(SolutionMessage.class));
		fetchSolutionClient.close();
		for (SolutionMessage solutionMessage : solutions) {
			SolutionBean bean = new SolutionBean();
			bean.setChalId(solutionMessage.getChalId());
			bean.setCatId(solutionMessage.getCatId());
			bean.setCrtdById(solutionMessage.getCrtdById());
			bean.setCrtdDt(solutionMessage.getCrtdDt());
			bean.setDesc(solutionMessage.getDesc());
			bean.setId(solutionMessage.getId());
			bean.setStatusId(solutionMessage.getStatusId());
			bean.setTags(solutionMessage.getTags());
			bean.setTitle(solutionMessage.getTitle());
			ret.add(bean);
		}
		return ret;
	}

	public void chalFileUploadHandle(FileUploadEvent fue) {
		try {
			UploadedFile file = fue.getFile();
			this.chalUploadContent = new DefaultStreamedContent(file.getInputstream());
			this.challengeBean.setContentType(file.getContentType());
			this.challengeBean.setFileName(file.getFileName());
		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	private TagCloudModel fetchAllChalLikes() {
		TagCloudModel likes = new DefaultTagCloudModel();
		WebClient fetchChallengeLikesClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ts/tag/get/" + challengeBean.getId() + "/2/1");
		Collection<? extends TagMessage> likeList = new ArrayList<TagMessage>(fetchChallengeLikesClient.accept(MediaType.APPLICATION_JSON).getCollection(TagMessage.class));
		fetchChallengeLikesClient.close();
		for (TagMessage tagMessage : likeList)
			likes.addTag(new DefaultTagCloudItem(tagMessage.getUsrScreenName(), 1));
		return likes;
	}

	private List<TagBean> fetchAllChalComments() {
		WebClient fetchChallengeCommentsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ts/tag/get/" + challengeBean.getId() + "/2/2");
		Collection<? extends TagMessage> msgs = new ArrayList<TagMessage>(fetchChallengeCommentsClient.accept(MediaType.APPLICATION_JSON).getCollection(TagMessage.class));
		fetchChallengeCommentsClient.close();
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

	private TagCloudModel fetchAllSolLikes() {
		TagCloudModel likes = new DefaultTagCloudModel();
		WebClient fetchSolutionLikesClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ts/tag/get/" + solutionBean.getId() + "/3/1");
		Collection<? extends TagMessage> likeList = new ArrayList<TagMessage>(fetchSolutionLikesClient.accept(MediaType.APPLICATION_JSON).getCollection(TagMessage.class));
		fetchSolutionLikesClient.close();
		for (TagMessage tagMessage : likeList)
			likes.addTag(new DefaultTagCloudItem(tagMessage.getUsrScreenName(), 1));
		return likes;
	}

	private List<TagBean> fetchAllSolComments() {
		WebClient fetchSolutionCommentsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ts/tag/get/" + solutionBean.getId() + "/3/2");
		Collection<? extends TagMessage> msgs = new ArrayList<TagMessage>(fetchSolutionCommentsClient.accept(MediaType.APPLICATION_JSON).getCollection(TagMessage.class));
		fetchSolutionCommentsClient.close();
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

	private List<ListSelectorBean> fetchAllChallengeStatuses() {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewChallengeSelectClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/cs/challenge/status/list");
		Collection<? extends MetaDataMessage> md = new ArrayList<MetaDataMessage>(viewChallengeSelectClient.accept(MediaType.APPLICATION_JSON).getCollection(MetaDataMessage.class));
		viewChallengeSelectClient.close();
		for (MetaDataMessage metaDataMessage : md) {
			ListSelectorBean bean = new ListSelectorBean();
			bean.setId(metaDataMessage.getId());
			bean.setDesc(metaDataMessage.getDesc());
			ret.add(bean);
		}
		return ret;
	}

	private List<ListSelectorBean> fetchNextChallengeStatuses() {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewChallengeSelectClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/cs/challenge/status/list/" + challengeBean.getStatusId());
		Collection<? extends MetaDataMessage> md = new ArrayList<MetaDataMessage>(viewChallengeSelectClient.accept(MediaType.APPLICATION_JSON).getCollection(MetaDataMessage.class));
		viewChallengeSelectClient.close();
		for (MetaDataMessage metaDataMessage : md) {
			ListSelectorBean bean = new ListSelectorBean();
			bean.setId(metaDataMessage.getId());
			bean.setDesc(metaDataMessage.getDesc());
			ret.add(bean);
		}
		return ret;
	}

	private List<ListSelectorBean> fetchAllChallengeCat() {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewChallengeSelectClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/cs/challenge/cat/list");
		Collection<? extends MetaDataMessage> md = new ArrayList<MetaDataMessage>(viewChallengeSelectClient.accept(MediaType.APPLICATION_JSON).getCollection(MetaDataMessage.class));
		viewChallengeSelectClient.close();
		for (MetaDataMessage metaDataMessage : md) {
			ListSelectorBean bean = new ListSelectorBean();
			bean.setId(metaDataMessage.getId());
			bean.setDesc(metaDataMessage.getDesc());
			ret.add(bean);
		}
		return ret;
	}

	private List<ListSelectorBean> fetchAllSolutionStatuses() {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewSolutionSelectClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ss/solution/status/list");
		Collection<? extends MetaDataMessage> md = new ArrayList<MetaDataMessage>(viewSolutionSelectClient.accept(MediaType.APPLICATION_JSON).getCollection(MetaDataMessage.class));
		viewSolutionSelectClient.close();
		for (MetaDataMessage metaDataMessage : md) {
			ListSelectorBean bean = new ListSelectorBean();
			bean.setId(metaDataMessage.getId());
			bean.setDesc(metaDataMessage.getDesc());
			ret.add(bean);
		}
		return ret;
	}

	private List<ListSelectorBean> fetchNextSolutionStatuses() {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewSolutionSelectClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ss/solution/status/list/" + solutionBean.getStatusId());
		Collection<? extends MetaDataMessage> md = new ArrayList<MetaDataMessage>(viewSolutionSelectClient.accept(MediaType.APPLICATION_JSON).getCollection(MetaDataMessage.class));
		viewSolutionSelectClient.close();
		for (MetaDataMessage metaDataMessage : md) {
			ListSelectorBean bean = new ListSelectorBean();
			bean.setId(metaDataMessage.getId());
			bean.setDesc(metaDataMessage.getDesc());
			ret.add(bean);
		}
		return ret;
	}

	private List<ListSelectorBean> fetchAllSolutionCat() {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewSolutionSelectClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ss/solution/cat/list");
		Collection<? extends MetaDataMessage> md = new ArrayList<MetaDataMessage>(viewSolutionSelectClient.accept(MediaType.APPLICATION_JSON).getCollection(MetaDataMessage.class));
		viewSolutionSelectClient.close();
		for (MetaDataMessage metaDataMessage : md) {
			ListSelectorBean bean = new ListSelectorBean();
			bean.setId(metaDataMessage.getId());
			bean.setDesc(metaDataMessage.getDesc());
			ret.add(bean);
		}
		return ret;
	}

	private List<UserBean> fetchAllUsers() {
		List<UserBean> ret = new ArrayList<UserBean>();
		WebClient viewUsersClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/list");
		Collection<? extends UserMessage> users = new ArrayList<UserMessage>(viewUsersClient.accept(MediaType.APPLICATION_JSON).getCollection(UserMessage.class));
		viewUsersClient.close();
		for (UserMessage userMessage : users) {
			UserBean bean = new UserBean();
			bean.setBio(userMessage.getBio());
			bean.setContact(userMessage.getContact());
			bean.seteMail(userMessage.geteMail());
			bean.setFbHandle(userMessage.getFbHandle());
			bean.setfName(userMessage.getfName());
			bean.setIdNum(userMessage.getIdNum());
			bean.setIsActive(userMessage.getIsActive());
			bean.setlName(userMessage.getlName());
			bean.setmName(userMessage.getmName());
			bean.setPwd(userMessage.getPwd());
			bean.setScName(userMessage.getScName());
			bean.setSkills(userMessage.getSkills());
			bean.setTwHandle(userMessage.getTwHandle());
			bean.setIsActive(true);
			bean.setuId(userMessage.getuId());
			ret.add(bean);
		}
		return ret;
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
		if (solutionBean.getCatId() == null || solutionBean.getCatId().toString().length() == 0) {
			ret.add("Category is Mandatory");
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

	private List<GroupBean> fetchAllGroups() {
		List<GroupBean> ret = new ArrayList<GroupBean>();
		WebClient viewGroupsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/group/list");
		Collection<? extends GroupMessage> groups = new ArrayList<GroupMessage>(viewGroupsClient.accept(MediaType.APPLICATION_JSON).getCollection(GroupMessage.class));
		viewGroupsClient.close();
		for (GroupMessage groupMessage : groups) {
			GroupBean bean = new GroupBean();
			bean.setgId(groupMessage.getgId());
			bean.setGeMail(groupMessage.getGeMail());
			bean.setgName(groupMessage.getgName());
			bean.setIsActive(groupMessage.getIsActive());
			bean.setSelAdmUser(groupMessage.getAdmUserId());
			bean.setSelPGrp(groupMessage.getpGrpId());
			bean.getUserIdList().clear();
			for (Long id : groupMessage.getUserIdList())
				if (id != null)
					bean.getUserIdList().add(id);
			ret.add(bean);
		}
		return ret;
	}

	private DualListModel<GroupBean> initializeSelectedGroups(List<GroupBean> grps) {
		List<Long> selGrps = challengeBean.getGroupIdList();
		DualListModel<GroupBean> ret = new DualListModel<GroupBean>(new ArrayList<GroupBean>(), new ArrayList<GroupBean>());
		if (selGrps != null)
			for (Long grpId : selGrps)
				ret.getTarget().add(getGroupById(grpId));
		if (grps != null)
			for (GroupBean bean : grps)
				if (selGrps != null && selGrps.contains(bean.getgId()))
					continue;
				else
					ret.getSource().add(bean);
		return ret;
	}

	private GroupBean getGroupById(Long pGrpId) {
		GroupBean bean = new GroupBean();
		WebClient groupByIdClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/group/get/" + pGrpId);
		GroupMessage groupMessage = groupByIdClient.accept(MediaType.APPLICATION_JSON).get(GroupMessage.class);
		groupByIdClient.close();
		bean.setgId(groupMessage.getgId());
		bean.setGeMail(groupMessage.getGeMail());
		bean.setgName(groupMessage.getgName());
		bean.setIsActive(groupMessage.getIsActive());
		bean.setSelAdmUser(groupMessage.getAdmUserId());
		bean.setSelPGrp(groupMessage.getpGrpId());
		return bean;
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

	private List<Long> getIdsFromArray(Long[] ae) {
		List<Long> ret = new ArrayList<Long>();
		for (Long id : ae)
			ret.add(id);
		return ret;
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
		return chalLikeCnt;
	}

	public void setChalLikeCnt(String chalLikeCnt) {
		this.chalLikeCnt = chalLikeCnt;
	}

	public String getChalCommentCnt() {
		return chalCommentCnt;
	}

	public void setChalCommentCnt(String chalCommentCnt) {
		this.chalCommentCnt = chalCommentCnt;
	}

	public String getSolLikeCnt() {
		return solLikeCnt;
	}

	public void setSolLikeCnt(String solLikeCnt) {
		this.solLikeCnt = solLikeCnt;
	}

	public String getSolCommentCnt() {
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
		return buildOns;
	}

	public String getBuildOnText() {
		return buildOnText;
	}

	public boolean isShowSolBuildOns() {
		return showSolBuildOns;
	}

	public String getBuildOnCnt() {
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
		return pGrps;
	}

	public DualListModel<GroupBean> getGroupTwinSelect() {
		return groupTwinSelect;
	}

	public void setpGrps(List<GroupBean> pGrps) {
		this.pGrps = pGrps;
	}

	public void setGroupTwinSelect(DualListModel<GroupBean> groupTwinSelect) {
		this.groupTwinSelect = groupTwinSelect;
	}

}
