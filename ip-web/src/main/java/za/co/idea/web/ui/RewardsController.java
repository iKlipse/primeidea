package za.co.idea.web.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;
import org.apache.log4j.Logger;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import za.co.idea.ip.ws.bean.AllocationMessage;
import za.co.idea.ip.ws.bean.AttachmentMessage;
import za.co.idea.ip.ws.bean.GroupMessage;
import za.co.idea.ip.ws.bean.MetaDataMessage;
import za.co.idea.ip.ws.bean.PointMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;
import za.co.idea.ip.ws.bean.RewardsMessage;
import za.co.idea.ip.ws.bean.TagMessage;
import za.co.idea.ip.ws.bean.UserMessage;
import za.co.idea.ip.ws.util.CustomObjectMapper;
import za.co.idea.web.ui.bean.AllocationBean;
import za.co.idea.web.ui.bean.GroupBean;
import za.co.idea.web.ui.bean.ListSelectorBean;
import za.co.idea.web.ui.bean.MetaDataBean;
import za.co.idea.web.ui.bean.PointBean;
import za.co.idea.web.ui.bean.RewardsBean;
import za.co.idea.web.ui.bean.TagBean;
import za.co.idea.web.ui.bean.UserBean;
import za.co.idea.web.util.IdNumberGen;

public class RewardsController implements Serializable {

	private static final Log LOG = LogFactory.getLog(RewardsController.class);
	private static final long serialVersionUID = 1830096234056863422L;

	private RewardsBean rewardsBean;
	private StreamedContent fileContent;
	private StreamedContent uploadContent;
	private List<RewardsBean> viewRewardsBeans;
	private List<ListSelectorBean> rewardsCat;
	private List<UserBean> admUsers;
	private List<TagBean> rewardsWishlist;
	private List<MetaDataBean> statusList;
	private List<MetaDataBean> disStatusList;
	private List<MetaDataBean> chalStatusList;
	private List<MetaDataBean> claimStatusList;
	private List<MetaDataBean> ideaStatusList;
	private List<MetaDataBean> solStatusList;
	private List<AllocationBean> allocs;
	private List<GroupBean> pGrps;
	private List<PointBean> pointBeans;
	private PointBean pointBean;
	private Long totalPoints;
	private DualListModel<GroupBean> groupTwinSelect;
	private AllocationBean allocationBean;
	private String allocId;
	private String rewardsWishlistCnt;
	private boolean fileAvail;
	private boolean showRewardsWishlist;
	private HashMap<String, String> allocEntityList;
	private String entity;
	private static final IdNumberGen COUNTER = new IdNumberGen();
	private boolean showAddPanel;
	private boolean showModPanel;
	private boolean showAddBtn;
	private Integer pointVal;
	private Integer selAllocId;
	private String comments;
	private Long userId;
	private Integer selRwCatId;
	private static final Logger logger = Logger.getLogger(RewardsController.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private WebClient createCustomClient(String url) {
		
		List providers = new ArrayList();
		providers.add(new JacksonJaxbJsonProvider(new CustomObjectMapper(), JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS));
		WebClient client = WebClient.create(url, providers);
		client.header("Content-Type", "application/json");
		client.header("Accept", "application/json");
		return client;
	}

	public String showCreateReward() {
		try {
			admUsers = fetchAllUsers();
			rewardsCat = fetchAllRewardsCat();
			pGrps = fetchAllGroups();
			groupTwinSelect = new DualListModel<GroupBean>(pGrps, new ArrayList<GroupBean>());
			rewardsBean = new RewardsBean();
			return "rwcr";
		} catch (Exception e) {
			logger.error("Error while displaying show create reward form : " + e.getMessage());
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create reward request", "System error occurred, cannot perform create reward request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showRewardStore() {
		try {
			admUsers = fetchAllUsers();
			rewardsCat = fetchAllRewardsCat();
			viewRewardsBeans = fetchAllAvailableRewards();
			return "rwsr";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view reward request", "System error occurred, cannot perform view reward request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showRewardStoreByCategory() {
		try {
			admUsers = fetchAllUsers();
			rewardsCat = fetchAllRewardsCat();
			viewRewardsBeans = fetchAllAvailableRewardsByCat();
			return "rwsr";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view reward request", "System error occurred, cannot perform view reward request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showViewReward() {
		try {
			admUsers = fetchAllUsers();
			rewardsCat = fetchAllRewardsCat();
			viewRewardsBeans = fetchAllRewards();
			return "rwvr";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view reward request", "System error occurred, cannot perform view reward request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showViewRewardByUser() {
		try {
			admUsers = fetchAllUsers();
			rewardsCat = fetchAllRewardsCat();
			rewardsBean = new RewardsBean();
			viewRewardsBeans = fetchAllRewardsByUser();
			return "rwur";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view reward request", "System error occurred, cannot perform view reward request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showPointAllocation() {
		try {
			this.showAddPanel = false;
			this.showModPanel = false;
			this.showAddBtn = false;
			this.entity = "";
			this.statusList = new ArrayList<MetaDataBean>();
			this.disStatusList = new ArrayList<MetaDataBean>();
			this.allocs = new ArrayList<AllocationBean>();
			return "rwpa";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view reward request", "System error occurred, cannot perform view reward request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showAllocationMod() {
		this.showAddPanel = false;
		this.showModPanel = true;
		disStatusList = fetchAllStatusList();
		if (entity.equalsIgnoreCase("ip_solution_status"))
			statusList = getSolStatusList();
		else if (entity.equalsIgnoreCase("ip_challenge_status"))
			statusList = getChalStatusList();
		else if (entity.equalsIgnoreCase("ip_idea_status"))
			statusList = getIdeaStatusList();
		else if (entity.equalsIgnoreCase("ip_claim_status"))
			statusList = getClaimStatusList();
		return "";
	}

	public String showAllocationAdd() {
		this.showAddPanel = true;
		this.showModPanel = false;
		statusList = fetchAllNonAllocStatus();
		disStatusList = fetchAllStatusList();
		allocationBean = new AllocationBean();
		return "";
	}

	public String cancelAllocation() {
		this.showAddPanel = false;
		this.showModPanel = false;
		entity = "";
		allocs = new ArrayList<AllocationBean>();
		statusList = new ArrayList<MetaDataBean>();
		disStatusList = new ArrayList<MetaDataBean>();
		return "";
	}

	public String showAllocatePoints() {
		try {
			entity = "";
			admUsers = fetchAllUsers();
			allocs = new ArrayList<AllocationBean>();
			return "rwap";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view reward request", "System error occurred, cannot perform view reward request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public void updateEntityChange() {
		if (entity.equalsIgnoreCase("")) {
			entity = "";
			allocs = new ArrayList<AllocationBean>();
		} else {
			allocs = fetchAllAllocationsByEntity();
		}
		pointVal = null;
	}

	public void updateAllocationChange() {
		if (entity.equalsIgnoreCase("")) {
			pointVal = null;
		} else {
			if (allocationBean != null && allocationBean.getAllocVal() != null)
				pointVal = allocationBean.getAllocVal();
			else
				pointVal = 0;
		}
	}

	public void updateStatusList() {
		if (entity.equalsIgnoreCase("")) {
			statusList = new ArrayList<MetaDataBean>();
			allocs = new ArrayList<AllocationBean>();
			this.showAddBtn = false;
		} else {
			allocs = fetchAllAllocationsByEntity();
			disStatusList = fetchAllStatusList();
			if (entity.equalsIgnoreCase("ip_solution_status"))
				statusList = getSolStatusList();
			else if (entity.equalsIgnoreCase("ip_challenge_status"))
				statusList = getChalStatusList();
			else if (entity.equalsIgnoreCase("ip_idea_status"))
				statusList = getIdeaStatusList();
			else if (entity.equalsIgnoreCase("ip_claim_status"))
				statusList = getClaimStatusList();
			this.showAddBtn = true;
		}
		this.showAddPanel = false;
		this.showModPanel = false;
	}

	public String showEditReward() {
		try {
			admUsers = fetchAllUsers();
			rewardsCat = fetchAllRewardsCat();
			pGrps = fetchAllGroups();
			groupTwinSelect = initializeSelectedGroups(pGrps);
			try {
				WebClient getBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getId/" + rewardsBean.getRwId() + "/ip_rewards");
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
						WebClient getBlobTypeClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getContentType/" + blobId);
						String blobType = getBlobTypeClient.accept(MediaType.APPLICATION_JSON).get(String.class);
						getBlobTypeClient.close();
						rewardsBean.setRwFileName(attachment.getContentDisposition().toString().replace("attachment;filename=", ""));
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
				e.printStackTrace();
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform update reward request", "System error occurred, cannot perform update reward request");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				fileAvail = true;
				fileContent = null;
			}
			return "rwer";
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform update reward request", "System error occurred, cannot perform update reward request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showPointProfile() {
		pointBeans = fetchAllPointsByUser();
		return "rwpp";
	}

	private List<String> validateRewards() {
		ArrayList<String> ret = new ArrayList<String>();
		if (rewardsBean.getRwTitle() == null || rewardsBean.getRwTitle().length() == 0) {
			ret.add("Title is Mandatory");
		} else if (!lengthValidation(rewardsBean.getRwTitle(), 1, 100)) {
			ret.add("Title sholud not exceed 100 characters");
		}
		if (rewardsBean.getrCatId() == null || rewardsBean.getrCatId().toString().length() == 0) {
			ret.add("Category is Mandatory");
		}
		if (rewardsBean.getRwDesc() == null || rewardsBean.getRwDesc().length() == 0) {
			ret.add("Description is Mandatory");
		}
		if (rewardsBean.getRwValue() == null || rewardsBean.getRwValue().toString().length() == 0) {
			ret.add("Point Value");
		}
		if (rewardsBean.getRwStockCodeNum() == null || rewardsBean.getRwStockCodeNum().toString().length() == 0) {
			ret.add("Stock Code Num is Mandatory");
		}
		if (rewardsBean.getRwPrice() == null || rewardsBean.getRwPrice().toString().length() == 0) {
			ret.add("Price is Mandatory");
		}
		if (rewardsBean.getRwLaunchDt() == null || rewardsBean.getRwLaunchDt().toString().length() == 0) {
			ret.add("Launch Date is Mandatory");
		}
		if (rewardsBean.getRwExpiryDt() == null || rewardsBean.getRwExpiryDt().toString().length() == 0) {
			ret.add("Expiry Date is Mandatory");
		}
		return ret;
	}

	public boolean lengthValidation(String str, int minLimit, int maxLimit) {
		int intLength = str.length();
		if (intLength >= minLimit && intLength <= maxLimit) {
			return true;
		} else {
			return false;
		}

	}

	public String saveRewards() {
		try {
			List<String> errors = validateRewards();
			if (errors.size() > 0) {
				for (String error : errors) {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
				return "";
			}
			WebClient addRewardsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/rs/rewards/add");
			RewardsMessage message = new RewardsMessage();
			message.setrCatId(rewardsBean.getrCatId());
			message.setRwCrtdDt(new Date());
			message.setRwDesc(rewardsBean.getRwDesc());
			message.setRwId(COUNTER.getNextId("IpRewards"));
			message.setRwTag(rewardsBean.getRwTag());
			message.setRwTitle(rewardsBean.getRwTitle());
			message.setRwValue(rewardsBean.getRwValue());
			message.setRwHoverText(rewardsBean.getRwHoverText());
			message.setRwStockCodeNum(rewardsBean.getRwStockCodeNum());
			message.setRwLaunchDt(rewardsBean.getRwLaunchDt());
			message.setRwExpiryDt(rewardsBean.getRwExpiryDt());
			message.setRwPrice(rewardsBean.getRwPrice());
			message.setRwQuantity((rewardsBean.getRwQuantity() == null) ? 0l : rewardsBean.getRwQuantity());
			ResponseMessage response = addRewardsClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
			addRewardsClient.close();
			if (response.getStatusCode() == 0) {
				if (uploadContent != null) {
					WebClient createBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/create");
					AttachmentMessage attach = new AttachmentMessage();
					attach.setBlobContentType(rewardsBean.getRwFileType());
					attach.setBlobEntityId(message.getRwId());
					attach.setBlobEntityTblNm("ip_rewards");
					attach.setBlobName(rewardsBean.getRwFileName());
					attach.setBlobId(COUNTER.getNextId("IpBlob"));
					Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(attach);
					createBlobClient.close();
					if (crtRes.getStatus() == 200) {
						WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/upload/" + attach.getBlobId(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
						client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
						client.header("Accept", "application/json");
						Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(attach.getBlobId().toString(), uploadContent.getStream(), new ContentDisposition("attachment;filename=" + rewardsBean.getRwFileName())));
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
				return showViewReward();
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create Reward request", "System error occurred, cannot perform create Reward request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String updateRewards() {
		try {
			List<String> errors = validateRewards();
			if (errors.size() > 0) {
				for (String error : errors) {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
				return "";
			}
			WebClient updateRewardsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/rs/rewards/modify");
			RewardsMessage message = new RewardsMessage();
			message.setrCatId(rewardsBean.getrCatId());
			message.setRwCrtdDt(new Date());
			message.setRwDesc(rewardsBean.getRwDesc());
			message.setRwId(rewardsBean.getRwId());
			message.setRwTag(rewardsBean.getRwTag());
			message.setRwTitle(rewardsBean.getRwTitle());
			message.setRwValue(rewardsBean.getRwValue());
			message.setRwHoverText(rewardsBean.getRwHoverText());
			message.setRwStockCodeNum(rewardsBean.getRwStockCodeNum());
			message.setRwLaunchDt(rewardsBean.getRwLaunchDt());
			message.setRwExpiryDt(rewardsBean.getRwExpiryDt());
			message.setRwPrice(rewardsBean.getRwPrice());
			message.setRwQuantity(rewardsBean.getRwQuantity());
			ResponseMessage response = updateRewardsClient.accept(MediaType.APPLICATION_JSON).put(message, ResponseMessage.class);
			updateRewardsClient.close();
			if (response.getStatusCode() == 0) {
				if (uploadContent != null) {
					WebClient getBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getId/" + rewardsBean.getRwId() + "/ip_rewards");
					Long blobId = getBlobClient.accept(MediaType.APPLICATION_JSON).get(Long.class);
					if (blobId == -999) {
						WebClient createBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/create");
						AttachmentMessage attach = new AttachmentMessage();
						attach.setBlobContentType(rewardsBean.getRwFileType());
						attach.setBlobEntityId(rewardsBean.getRwId());
						attach.setBlobEntityTblNm("ip_rewards");
						attach.setBlobName(rewardsBean.getRwFileName());
						attach.setBlobId(COUNTER.getNextId("IpBlob"));
						Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(attach);
						if (crtRes.getStatus() == 200) {
							WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/upload/" + attach.getBlobId(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
							client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
							client.header("Accept", "application/json");
							Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(attach.getBlobId().toString(), uploadContent.getStream(), new ContentDisposition("attachment;filename=" + rewardsBean.getRwFileName())));
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
						attach.setBlobContentType(rewardsBean.getRwFileType());
						attach.setBlobEntityId(rewardsBean.getRwId());
						attach.setBlobEntityTblNm("ip_rewards");
						attach.setBlobName(rewardsBean.getRwFileName());
						attach.setBlobId(blobId);
						Response updRes = updateBlobClient.accept(MediaType.APPLICATION_JSON).put(attach);
						updateBlobClient.close();
						if (updRes.getStatus() == 200) {
							WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/upload/" + blobId.toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
							client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
							client.header("Accept", "application/json");
							Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(blobId.toString(), uploadContent.getStream(), new ContentDisposition("attachment;filename=" + rewardsBean.getRwFileName())));
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
				return showViewReward();
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform update Reward request", "System error occurred, cannot perform update Reward request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String saveAllocation() {
		try {
			WebClient saveAllocClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/rs/alloc/add");
			AllocationMessage message = new AllocationMessage();
			message.setAllocDesc(allocationBean.getAllocDesc());
			message.setAllocEntity(entity);
			allocationBean.setAllocEntity(entity);
			message.setAllocId(COUNTER.getNextId("IpAllocation").intValue());
			message.setAllocStatusId(allocationBean.getAllocStatusId());
			message.setAllocVal(allocationBean.getAllocVal());
			ResponseMessage res = saveAllocClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
			if (res.getStatusCode() == 0) {
				allocs = fetchAllAllocationsByEntity();
				this.showAddPanel = false;
				this.showModPanel = false;
				return "";
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform save allocation request", "System error occurred, cannot perform save allocation request");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform save allocation request", "System error occurred, cannot perform save allocation request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String savePoints() {
		try {
			WebClient savePointsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/rs/points/add");
			PointMessage message = new PointMessage();
			message.setAllocId(selAllocId);
			message.setPointId(COUNTER.getNextId("IpPoints"));
			message.setPointValue(pointVal);
			message.setUserId(userId);
			message.setComments(comments);
			ResponseMessage res = savePointsClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
			if (res.getStatusCode() == 0) {
				selAllocId = null;
				pointVal = null;
				userId = null;
				entity = null;
				comments = null;
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucessfully added points", "Sucessfully added points");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform save point request", "System error occurred, cannot perform save point request");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform save point request", "System error occurred, cannot perform save point request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String updateAllocation() {
		try {
			WebClient updateAllocClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/rs/alloc/update");
			AllocationMessage message = new AllocationMessage();
			message.setAllocDesc(allocationBean.getAllocDesc());
			message.setAllocEntity(entity);
			allocationBean.setAllocEntity(entity);
			message.setAllocId(allocationBean.getAllocId());
			message.setAllocStatusId(allocationBean.getAllocStatusId());
			message.setAllocVal(allocationBean.getAllocVal());
			ResponseMessage res = updateAllocClient.accept(MediaType.APPLICATION_JSON).put(message, ResponseMessage.class);
			if (res.getStatusCode() == 0) {
				allocs = fetchAllAllocationsByEntity();
				this.showAddPanel = false;
				this.showModPanel = false;
				return "";
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform update allocation request", "System error occurred, cannot perform update allocation request");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform update allocation request", "System error occurred, cannot perform update allocation request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String deleteAllocation() {
		try {
			WebClient updateAllocClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/rs/alloc/delete");
			AllocationMessage message = new AllocationMessage();
			message.setAllocDesc(allocationBean.getAllocDesc());
			message.setAllocEntity(entity);
			message.setAllocId(allocationBean.getAllocId());
			message.setAllocStatusId(allocationBean.getAllocStatusId());
			message.setAllocVal(allocationBean.getAllocVal());
			ResponseMessage res = updateAllocClient.accept(MediaType.APPLICATION_JSON).put(message, ResponseMessage.class);
			if (res.getStatusCode() == 0) {
				allocs = fetchAllAllocationsByEntity();
				this.showAddPanel = false;
				this.showModPanel = false;
				return "";
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, res.getStatusDesc(), res.getStatusDesc());
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform update allocation request", "System error occurred, cannot perform update allocation request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	private List<UserBean> fetchAllUsers() {
		List<UserBean> ret = new ArrayList<UserBean>();
		WebClient viewUsersClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/list/sort/pg");
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

	public String addToWishlist() {
		WebClient addTagClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ts/tag/add");
		TagMessage message = new TagMessage();
		message.setEntityId(rewardsBean.getRwId());
		message.setTagId(COUNTER.getNextId("IpTag"));
		message.setTeId(4);
		message.setTtId(4);
		message.setUserId((Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId"));
		message.setDuplicate(false);
		ResponseMessage response = addTagClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		addTagClient.close();
		if (response.getStatusCode() != 0 && response.getStatusCode() != 2)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Like", "Error While Saving Like"));
		viewRewardsBeans = fetchAllAvailableRewards();
		return "";
	}

	public String removeFromWishlist() {
		WebClient addTagClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ts/tag/add");
		TagMessage message = new TagMessage();
		message.setEntityId(rewardsBean.getRwId());
		message.setTagId(COUNTER.getNextId("IpTag"));
		message.setTeId(4);
		message.setTtId(4);
		message.setUserId((Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId"));
		message.setDuplicate(false);
		ResponseMessage response = addTagClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		addTagClient.close();
		if (response.getStatusCode() != 0 && response.getStatusCode() != 2)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Like", "Error While Saving Like"));
		viewRewardsBeans = fetchAllRewardsByUser();
		return "";
	}

	private List<RewardsBean> fetchAllAvailableRewardsByCat() {
		fetchAllPointsByUser();
		List<RewardsBean> ret = new ArrayList<RewardsBean>();
		WebClient viewRewardsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/rs/rewards/list/cat/" + selRwCatId);
		Collection<? extends RewardsMessage> rewards = new ArrayList<RewardsMessage>(viewRewardsClient.accept(MediaType.APPLICATION_JSON).getCollection(RewardsMessage.class));
		viewRewardsClient.close();
		for (RewardsMessage message : rewards) {
			RewardsBean bean = new RewardsBean();
			bean.setrCatId(message.getrCatId());
			bean.setRwCrtdDt(message.getRwCrtdDt());
			bean.setRwDesc(message.getRwDesc());
			bean.setRwExpiryDt(message.getRwExpiryDt());
			bean.setRwHoverText(message.getRwHoverText());
			bean.setRwId(message.getRwId());
			bean.setRwLaunchDt(message.getRwLaunchDt());
			bean.setRwStockCodeNum(message.getRwStockCodeNum());
			bean.setRwTag(message.getRwTag());
			bean.setRwTitle(message.getRwTitle());
			bean.setRwValue(message.getRwValue());
			bean.setRwPrice(message.getRwPrice());
			bean.setRwQuantity(message.getRwQuantity());
			bean.setRwImgAvail(message.isRwImgAvail());
			bean.setRwUrl(message.getRwUrl());
			bean.setRwTaggable(isWishlist(message.getRwId()));
			bean.setRwClaimable(totalPoints >= message.getRwValue() && message.getRwQuantity() > 0);
			logger.info("-----in whishlist ----" + bean.isRwTaggable());
			ret.add(bean);
		}
		return ret;

	}

	private List<RewardsBean> fetchAllRewards() {
		fetchAllPointsByUser();
		List<RewardsBean> ret = new ArrayList<RewardsBean>();
		WebClient viewRewardsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/rs/rewards/list");
		Collection<? extends RewardsMessage> rewards = new ArrayList<RewardsMessage>(viewRewardsClient.accept(MediaType.APPLICATION_JSON).getCollection(RewardsMessage.class));
		viewRewardsClient.close();
		for (RewardsMessage message : rewards) {
			RewardsBean bean = new RewardsBean();
			bean.setrCatId(message.getrCatId());
			bean.setRwCrtdDt(message.getRwCrtdDt());
			bean.setRwDesc(message.getRwDesc());
			bean.setRwExpiryDt(message.getRwExpiryDt());
			bean.setRwHoverText(message.getRwHoverText());
			bean.setRwId(message.getRwId());
			bean.setRwLaunchDt(message.getRwLaunchDt());
			bean.setRwStockCodeNum(message.getRwStockCodeNum());
			bean.setRwTag(message.getRwTag());
			bean.setRwTitle(message.getRwTitle());
			bean.setRwValue(message.getRwValue());
			bean.setRwPrice(message.getRwPrice());
			bean.setRwQuantity(message.getRwQuantity());
			bean.setRwImgAvail(message.isRwImgAvail());
			bean.setRwUrl(message.getRwUrl());
			bean.setRwClaimable(totalPoints > message.getRwValue() && message.getRwQuantity() > 0);
			bean.setRwTaggable(isWishlist(message.getRwId()));
			ret.add(bean);
		}
		return ret;
	}

	private List<RewardsBean> fetchAllRewardsByUser() {
		fetchAllPointsByUser();
		List<RewardsBean> ret = new ArrayList<RewardsBean>();
		WebClient viewRewardsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/rs/rewards/list/" + ((Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId")).longValue());
		Collection<? extends RewardsMessage> rewards = new ArrayList<RewardsMessage>(viewRewardsClient.accept(MediaType.APPLICATION_JSON).getCollection(RewardsMessage.class));
		viewRewardsClient.close();
		for (RewardsMessage message : rewards) {
			RewardsBean bean = new RewardsBean();
			bean.setrCatId(message.getrCatId());
			bean.setRwCrtdDt(message.getRwCrtdDt());
			bean.setRwDesc(message.getRwDesc());
			bean.setRwExpiryDt(message.getRwExpiryDt());
			bean.setRwHoverText(message.getRwHoverText());
			bean.setRwId(message.getRwId());
			bean.setRwLaunchDt(message.getRwLaunchDt());
			bean.setRwStockCodeNum(message.getRwStockCodeNum());
			bean.setRwTag(message.getRwTag());
			bean.setRwTitle(message.getRwTitle());
			bean.setRwValue(message.getRwValue());
			bean.setRwPrice(message.getRwPrice());
			bean.setRwQuantity(message.getRwQuantity());
			bean.setRwImgAvail(message.isRwImgAvail());
			bean.setRwUrl(message.getRwUrl());
			bean.setRwTaggable(isWishlist(message.getRwId()));
			bean.setRwClaimable(totalPoints >= message.getRwValue() && message.getRwQuantity() > 0);
			ret.add(bean);
		}
		return ret;
	}

	private List<PointBean> fetchAllPointsByUser() {
		List<PointBean> ret = new ArrayList<PointBean>();
		WebClient viewPointClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/rs/points/get/user/" + ((Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId")).longValue());
		Collection<? extends PointMessage> points = new ArrayList<PointMessage>(viewPointClient.accept(MediaType.APPLICATION_JSON).getCollection(PointMessage.class));
		viewPointClient.close();
		totalPoints = 0l;
		for (PointMessage message : points) {
			PointBean bean = new PointBean();
			bean.setAllocId(message.getAllocId());
			bean.setComments(message.getComments());
			bean.setEntityId(message.getEntityId());
			bean.setPointId(message.getPointId());
			bean.setPointValue(message.getPointValue());
			bean.setUserId(message.getUserId());
			totalPoints += message.getPointValue();
			ret.add(bean);
		}
		return ret;
	}

	private List<RewardsBean> fetchAllAvailableRewards() {
		fetchAllPointsByUser();
		List<RewardsBean> ret = new ArrayList<RewardsBean>();
		WebClient viewRewardsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/rs/rewards/list/avail");
		Collection<? extends RewardsMessage> rewards = new ArrayList<RewardsMessage>(viewRewardsClient.accept(MediaType.APPLICATION_JSON).getCollection(RewardsMessage.class));
		viewRewardsClient.close();
		for (RewardsMessage message : rewards) {
			RewardsBean bean = new RewardsBean();
			bean.setrCatId(message.getrCatId());
			bean.setRwCrtdDt(message.getRwCrtdDt());
			bean.setRwDesc(message.getRwDesc());
			bean.setRwExpiryDt(message.getRwExpiryDt());
			bean.setRwHoverText(message.getRwHoverText());
			bean.setRwId(message.getRwId());
			bean.setRwLaunchDt(message.getRwLaunchDt());
			bean.setRwStockCodeNum(message.getRwStockCodeNum());
			bean.setRwTag(message.getRwTag());
			bean.setRwTitle(message.getRwTitle());
			bean.setRwValue(message.getRwValue());
			bean.setRwPrice(message.getRwPrice());
			bean.setRwQuantity(message.getRwQuantity());
			bean.setRwImgAvail(message.isRwImgAvail());
			bean.setRwUrl(message.getRwUrl());
			bean.setRwClaimable(totalPoints >= message.getRwValue() && message.getRwQuantity() > 0);
			bean.setRwTaggable(isWishlist(message.getRwId()));
			logger.info("id ----+" + bean.getRwTitle() + "---------" + bean.isRwTaggable());
			ret.add(bean);
		}
		return ret;
	}

	private boolean isWishlist(Long rwId) {
		WebClient viewRewardsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ts/tag/getByUser/" + rwId + "/4/4/" + ((Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId")).longValue());
		Collection<? extends TagMessage> rewards = new ArrayList<TagMessage>(viewRewardsClient.accept(MediaType.APPLICATION_JSON).getCollection(TagMessage.class));
		return (rewards.size() > 0);
	}

	private List<ListSelectorBean> fetchAllRewardsCat() {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewRewardsSelectClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/rs/rewards/cat/list");
		Collection<? extends MetaDataMessage> md = new ArrayList<MetaDataMessage>(viewRewardsSelectClient.accept(MediaType.APPLICATION_JSON).getCollection(MetaDataMessage.class));
		viewRewardsSelectClient.close();
		for (MetaDataMessage metaDataMessage : md) {
			ListSelectorBean bean = new ListSelectorBean();
			bean.setId(metaDataMessage.getId());
			bean.setDesc(metaDataMessage.getDesc());
			ret.add(bean);
		}
		return ret;
	}

	public void rwFileUploadHandle(FileUploadEvent fue) {
		try {
			UploadedFile file = fue.getFile();
			this.uploadContent = new DefaultStreamedContent(file.getInputstream());
			this.rewardsBean.setRwFileType(file.getContentType());
			this.rewardsBean.setRwFileName(file.getFileName());
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform upload request", "System error occurred, cannot perform upload request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	private List<MetaDataBean> fetchAllNonAllocStatus() {
		List<MetaDataBean> ret = new ArrayList<MetaDataBean>();
		WebClient mDataClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ms/list/non/" + entity);
		Collection<? extends MetaDataMessage> messages = new ArrayList<MetaDataMessage>(mDataClient.accept(MediaType.APPLICATION_JSON).getCollection(MetaDataMessage.class));
		mDataClient.close();
		for (MetaDataMessage message : messages) {
			MetaDataBean bean = new MetaDataBean();
			bean.setDesc(message.getDesc());
			bean.setId(message.getId());
			bean.setTable(message.getTable());
			ret.add(bean);
		}
		return ret;
	}

	private List<AllocationBean> fetchAllAllocationsByEntity() {
		List<AllocationBean> ret = new ArrayList<AllocationBean>();
		WebClient allocCLient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/rs/alloc/list/" + entity);
		Collection<? extends AllocationMessage> messages = new ArrayList<AllocationMessage>(allocCLient.accept(MediaType.APPLICATION_JSON).getCollection(AllocationMessage.class));
		for (AllocationMessage message : messages) {
			AllocationBean bean = new AllocationBean();
			bean.setAllocDesc(message.getAllocDesc());
			bean.setAllocEntity(message.getAllocEntity());
			bean.setAllocId(message.getAllocId());
			bean.setAllocStatusId(message.getAllocStatusId());
			bean.setAllocVal(message.getAllocVal());
			ret.add(bean);
		}
		return ret;
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
		List<Long> selGrps = rewardsBean.getGroupIdList();
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

	public RewardsBean getRewardsBean() {
		return rewardsBean;
	}

	public void setRewardsBean(RewardsBean rewardsBean) {
		this.rewardsBean = rewardsBean;
	}

	public StreamedContent getFileContent() {
		return fileContent;
	}

	public void setFileContent(StreamedContent fileContent) {
		this.fileContent = fileContent;
	}

	public List<RewardsBean> getViewRewardsBeans() {
		return viewRewardsBeans;
	}

	public void setViewRewardsBeans(List<RewardsBean> viewRewardsBeans) {
		this.viewRewardsBeans = viewRewardsBeans;
	}

	public List<ListSelectorBean> getRewardsCat() {
		return rewardsCat;
	}

	public void setRewardsCat(List<ListSelectorBean> rewardsCat) {
		this.rewardsCat = rewardsCat;
	}

	public List<UserBean> getAdmUsers() {
		return admUsers;
	}

	public void setAdmUsers(List<UserBean> admUsers) {
		this.admUsers = admUsers;
	}

	public List<TagBean> getRewardsWishlist() {
		return rewardsWishlist;
	}

	public void setRewardsWishlist(List<TagBean> rewardsWishlist) {
		this.rewardsWishlist = rewardsWishlist;
	}

	public String getRewardsWishlistCnt() {
		return rewardsWishlistCnt;
	}

	public void setRewardsWishlistCnt(String rewardsWishlistCnt) {
		this.rewardsWishlistCnt = rewardsWishlistCnt;
	}

	public boolean isFileAvail() {
		return fileAvail;
	}

	public void setFileAvail(boolean fileAvail) {
		this.fileAvail = fileAvail;
	}

	public boolean isShowRewardsWishlist() {
		return showRewardsWishlist;
	}

	public void setShowRewardsWishlist(boolean showRewardsWishlist) {
		this.showRewardsWishlist = showRewardsWishlist;
	}

	public StreamedContent getUploadContent() {
		return uploadContent;
	}

	public void setUploadContent(StreamedContent uploadContent) {
		this.uploadContent = uploadContent;
	}

	public HashMap<String, String> getAllocEntityList() {
		if (allocEntityList == null) {
			allocEntityList = new HashMap<String, String>();
			allocEntityList.put("Solution", "ip_solution_status");
			allocEntityList.put("Challenge", "ip_challenge_status");
			allocEntityList.put("Idea", "ip_idea_status");
			allocEntityList.put("Claim", "ip_claim_status");
		}
		return allocEntityList;
	}

	public void setAllocEntityList(HashMap<String, String> allocEntityList) {
		this.allocEntityList = allocEntityList;
	}

	public List<MetaDataBean> getStatusList() {
		if (statusList == null)
			statusList = new ArrayList<MetaDataBean>();
		return statusList;
	}

	public void setStatusList(List<MetaDataBean> statusList) {
		this.statusList = statusList;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public boolean isShowAddPanel() {
		return showAddPanel;
	}

	public boolean isShowModPanel() {
		return showModPanel;
	}

	public boolean isShowAddBtn() {
		return showAddBtn;
	}

	public void setShowAddPanel(boolean showAddPanel) {
		this.showAddPanel = showAddPanel;
	}

	public void setShowModPanel(boolean showModPanel) {
		this.showModPanel = showModPanel;
	}

	public void setShowAddBtn(boolean showAddBtn) {
		this.showAddBtn = showAddBtn;
	}

	public AllocationBean getAllocationBean() {
		if (allocationBean == null)
			allocationBean = new AllocationBean();
		return allocationBean;
	}

	public void setAllocationBean(AllocationBean allocationBean) {
		this.allocationBean = allocationBean;
	}

	public List<AllocationBean> getAllocs() {
		if (allocs == null)
			allocs = new ArrayList<AllocationBean>();
		return allocs;
	}

	public void setAllocs(List<AllocationBean> allocs) {
		this.allocs = allocs;
	}

	public String getAllocId() {
		return allocId;
	}

	public void setAllocId(String allocId) {
		this.allocId = allocId;
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

	public List<MetaDataBean> getChalStatusList() {
		chalStatusList = new ArrayList<MetaDataBean>();
		WebClient mDataClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ms/list/ip_challenge_status");
		Collection<? extends MetaDataMessage> messages = new ArrayList<MetaDataMessage>(mDataClient.accept(MediaType.APPLICATION_JSON).getCollection(MetaDataMessage.class));
		mDataClient.close();
		for (MetaDataMessage message : messages) {
			MetaDataBean bean = new MetaDataBean();
			bean.setDesc(message.getDesc());
			bean.setId(message.getId());
			bean.setTable(message.getTable());
			chalStatusList.add(bean);
		}
		return chalStatusList;
	}

	public void setChalStatusList(List<MetaDataBean> chalStatusList) {
		this.chalStatusList = chalStatusList;
	}

	public List<MetaDataBean> getClaimStatusList() {
		claimStatusList = new ArrayList<MetaDataBean>();
		WebClient mDataClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ms/list/ip_claim_status");
		Collection<? extends MetaDataMessage> messages = new ArrayList<MetaDataMessage>(mDataClient.accept(MediaType.APPLICATION_JSON).getCollection(MetaDataMessage.class));
		mDataClient.close();
		for (MetaDataMessage message : messages) {
			MetaDataBean bean = new MetaDataBean();
			bean.setDesc(message.getDesc());
			bean.setId(message.getId());
			bean.setTable(message.getTable());
			claimStatusList.add(bean);
		}
		return claimStatusList;
	}

	private List<MetaDataBean> fetchAllStatusList() {
		List<MetaDataBean> ret = new ArrayList<MetaDataBean>();
		WebClient mDataClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ms/list/" + entity);
		Collection<? extends MetaDataMessage> messages = new ArrayList<MetaDataMessage>(mDataClient.accept(MediaType.APPLICATION_JSON).getCollection(MetaDataMessage.class));
		mDataClient.close();
		for (MetaDataMessage message : messages) {
			MetaDataBean bean = new MetaDataBean();
			bean.setDesc(message.getDesc());
			bean.setId(message.getId());
			bean.setTable(message.getTable());
			ret.add(bean);
		}
		return ret;
	}

	public void setClaimStatusList(List<MetaDataBean> claimStatusList) {
		this.claimStatusList = claimStatusList;
	}

	public List<MetaDataBean> getIdeaStatusList() {
		ideaStatusList = new ArrayList<MetaDataBean>();
		WebClient mDataClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ms/list/ip_idea_status");
		Collection<? extends MetaDataMessage> messages = new ArrayList<MetaDataMessage>(mDataClient.accept(MediaType.APPLICATION_JSON).getCollection(MetaDataMessage.class));
		mDataClient.close();
		for (MetaDataMessage message : messages) {
			MetaDataBean bean = new MetaDataBean();
			bean.setDesc(message.getDesc());
			bean.setId(message.getId());
			bean.setTable(message.getTable());
			ideaStatusList.add(bean);
		}
		return ideaStatusList;
	}

	public void setIdeaStatusList(List<MetaDataBean> ideaStatusList) {
		this.ideaStatusList = ideaStatusList;
	}

	public List<MetaDataBean> getSolStatusList() {
		solStatusList = new ArrayList<MetaDataBean>();
		WebClient mDataClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ms/list/ip_solution_status");
		Collection<? extends MetaDataMessage> messages = new ArrayList<MetaDataMessage>(mDataClient.accept(MediaType.APPLICATION_JSON).getCollection(MetaDataMessage.class));
		mDataClient.close();
		for (MetaDataMessage message : messages) {
			MetaDataBean bean = new MetaDataBean();
			bean.setDesc(message.getDesc());
			bean.setId(message.getId());
			bean.setTable(message.getTable());
			solStatusList.add(bean);
		}
		return solStatusList;
	}

	public void setSolStatusList(List<MetaDataBean> solStatusList) {
		this.solStatusList = solStatusList;
	}

	public List<MetaDataBean> getDisStatusList() {
		return disStatusList;
	}

	public void setDisStatusList(List<MetaDataBean> disStatusList) {
		this.disStatusList = disStatusList;
	}

	public Integer getPointVal() {
		return pointVal;
	}

	public void setPointVal(Integer pointVal) {
		this.pointVal = pointVal;
	}

	public Integer getSelAllocId() {
		return selAllocId;
	}

	public void setSelAllocId(Integer selAllocId) {
		this.selAllocId = selAllocId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<PointBean> getPointBeans() {
		return pointBeans;
	}

	public void setPointBeans(List<PointBean> pointBeans) {
		this.pointBeans = pointBeans;
	}

	public PointBean getPointBean() {
		return pointBean;
	}

	public void setPointBean(PointBean pointBean) {
		this.pointBean = pointBean;
	}

	public Long getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Long totalPoints) {
		this.totalPoints = totalPoints;
	}

	public Integer getSelRwCatId() {
		return selRwCatId;
	}

	public void setSelRwCatId(Integer selRwCatId) {
		this.selRwCatId = selRwCatId;
	}

}