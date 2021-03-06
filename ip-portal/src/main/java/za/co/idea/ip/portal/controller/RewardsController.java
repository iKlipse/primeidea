package za.co.idea.ip.portal.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
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

import za.co.idea.ip.portal.bean.AllocationBean;
import za.co.idea.ip.portal.bean.GroupBean;
import za.co.idea.ip.portal.bean.ListSelectorBean;
import za.co.idea.ip.portal.bean.MetaDataBean;
import za.co.idea.ip.portal.bean.PointBean;
import za.co.idea.ip.portal.bean.RewardsBean;
import za.co.idea.ip.portal.bean.TagBean;
import za.co.idea.ip.portal.bean.UserBean;
import za.co.idea.ip.portal.util.IdNumberGen;
import za.co.idea.ip.portal.util.RESTServiceHelper;
import za.co.idea.ip.ws.bean.AllocationMessage;
import za.co.idea.ip.ws.bean.AttachmentMessage;
import za.co.idea.ip.ws.bean.MetaDataMessage;
import za.co.idea.ip.ws.bean.PointMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;
import za.co.idea.ip.ws.bean.RewardsMessage;
import za.co.idea.ip.ws.bean.TagMessage;
import za.co.idea.ip.ws.bean.UserMessage;
import za.co.idea.ip.ws.util.CustomObjectMapper;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;

@ManagedBean(name = "rewardsController")
@SessionScoped
public class RewardsController implements Serializable {

	private static final long serialVersionUID = -5950221141502328656L;
	private static final Logger logger = Logger.getLogger(RewardsController.class);
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-portal");
	private static final IdNumberGen COUNTER = new IdNumberGen();
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
	private AllocationBean allocationBean;
	private String allocId;
	private String rewardsWishlistCnt;
	private boolean fileAvail;
	private boolean showRewardsWishlist;
	private HashMap<String, String> allocEntityList;
	private String entity;
	private boolean showAddPanel;
	private boolean showModPanel;
	private boolean showAddBtn;
	private Integer pointVal;
	private Integer selAllocId;
	private String comments;
	private Long userId;
	private Integer selRwCatId;
	private boolean showCrtReward;
	private boolean showViewReward;
	private boolean showOnlineStore;
	private boolean titleAvail;
	private AccessController controller;
	private String toView;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private WebClient createCustomClient(String url) {
		List providers = new ArrayList();
		providers.add(new JacksonJaxbJsonProvider(new CustomObjectMapper(), JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS));
		WebClient client = WebClient.create(url, providers);
		client.header("Content-Type", MediaType.APPLICATION_JSON);
		client.header("Accept", MediaType.APPLICATION_JSON);
		return client;
	}

	@PostConstruct
	public void initializePage() {
		try {
			PortletRequest request = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			User user = (User) request.getAttribute(WebKeys.USER);
			WebClient client = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/verify/" + user.getScreenName());
			UserMessage message = client.accept(MediaType.APPLICATION_JSON).get(UserMessage.class);
			userId = message.getuId();
			admUsers = RESTServiceHelper.fetchAllUsers();
			rewardsCat = RESTServiceHelper.fetchAllRewardsCat();
			totalPoints = RESTServiceHelper.calculateTotal(RESTServiceHelper.fetchAllPointsByUser(userId));
			viewRewardsBeans = RESTServiceHelper.fetchAllAvailableRewards(userId, totalPoints);
			viewRewardsBeans = RESTServiceHelper.fetchAllAvailableRewards(userId, totalPoints);
			showCrtReward = false;
			showViewReward = false;
			showOnlineStore = true;
			if (toView != null && Integer.valueOf(toView) != -1) {
				switch (Integer.valueOf(toView)) {
				case 1:
					showRewardStore();
					break;
				case 2:
					showViewReward();
					break;
				case 3:
					showCreateReward();
					break;
				default:
					showRewardStore();
				}
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
	}

	public String redirectView() {
		showViewReward();
		return "rwvr";
	}

	public void showCreateReward() {
		try {
			admUsers = RESTServiceHelper.fetchActiveUsers();
			rewardsCat = RESTServiceHelper.fetchAllRewardsCat();
			pGrps = RESTServiceHelper.fetchActiveGroups();
			rewardsBean = new RewardsBean();
			showCrtReward = true;
			showViewReward = false;
			showOnlineStore = false;
		} catch (Exception e) {
			logger.error(e, e);
			logger.error("Error while displaying show create reward form : " + e.getMessage());

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create reward request", "System error occurred, cannot perform create reward request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void showRewardStore() {
		try {
			admUsers = RESTServiceHelper.fetchAllUsers();
			rewardsCat = RESTServiceHelper.fetchAllRewardsCat();
			totalPoints = RESTServiceHelper.calculateTotal(RESTServiceHelper.fetchAllPointsByUser(userId));
			viewRewardsBeans = RESTServiceHelper.fetchAllAvailableRewards(userId, totalPoints);
			showCrtReward = false;
			showViewReward = false;
			showOnlineStore = true;
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view reward request", "System error occurred, cannot perform view reward request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void showRewardStoreByCategory() {
		try {
			admUsers = RESTServiceHelper.fetchAllUsers();
			rewardsCat = RESTServiceHelper.fetchAllRewardsCat();
			viewRewardsBeans = RESTServiceHelper.fetchAllAvailableRewardsByCat(userId, totalPoints, selRwCatId);
			showCrtReward = false;
			showViewReward = false;
			showOnlineStore = true;
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view reward request", "System error occurred, cannot perform view reward request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void showViewReward() {
		try {
			admUsers = RESTServiceHelper.fetchAllUsers();
			rewardsCat = RESTServiceHelper.fetchAllRewardsCat();
			totalPoints = RESTServiceHelper.calculateTotal(RESTServiceHelper.fetchAllPointsByUser(userId));
			viewRewardsBeans = RESTServiceHelper.fetchAllRewards(userId, totalPoints);
			showCrtReward = false;
			showViewReward = true;
			showOnlineStore = false;
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view reward request", "System error occurred, cannot perform view reward request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void showViewRewardByUser() {
		try {
			admUsers = RESTServiceHelper.fetchAllUsers();
			rewardsCat = RESTServiceHelper.fetchAllRewardsCat();
			rewardsBean = new RewardsBean();
			viewRewardsBeans = RESTServiceHelper.fetchAllRewardsByUser(userId, totalPoints);
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view reward request", "System error occurred, cannot perform view reward request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void showPointAllocation() {
		try {
			this.showAddPanel = false;
			this.showModPanel = false;
			this.showAddBtn = false;
			this.entity = "";
			this.statusList = new ArrayList<MetaDataBean>();
			this.disStatusList = new ArrayList<MetaDataBean>();
			this.allocs = new ArrayList<AllocationBean>();
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view reward request", "System error occurred, cannot perform view reward request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void showAllocationMod() {
		this.showAddPanel = false;
		this.showModPanel = true;
		disStatusList = RESTServiceHelper.fetchAllStatusList(entity);
		if (entity.equalsIgnoreCase("ip_solution_status"))
			statusList = getSolStatusList();
		else if (entity.equalsIgnoreCase("ip_challenge_status"))
			statusList = getChalStatusList();
		else if (entity.equalsIgnoreCase("ip_idea_status"))
			statusList = getIdeaStatusList();
		else if (entity.equalsIgnoreCase("ip_claim_status"))
			statusList = getClaimStatusList();
	}

	public void showAllocationAdd() {
		this.showAddPanel = true;
		this.showModPanel = false;
		statusList = RESTServiceHelper.fetchAllNonAllocStatus(entity);
		disStatusList = RESTServiceHelper.fetchAllStatusList(entity);
		allocationBean = new AllocationBean();
	}

	public void cancelAllocation() {
		this.showAddPanel = false;
		this.showModPanel = false;
		entity = "";
		allocs = new ArrayList<AllocationBean>();
		statusList = new ArrayList<MetaDataBean>();
		disStatusList = new ArrayList<MetaDataBean>();
	}

	public void showAllocatePoints() {
		try {
			entity = "";
			admUsers = RESTServiceHelper.fetchAllUsers();
			allocs = new ArrayList<AllocationBean>();
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view reward request", "System error occurred, cannot perform view reward request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void updateEntityChange() {
		if (entity.equalsIgnoreCase("")) {
			entity = "";
			allocs = new ArrayList<AllocationBean>();
		} else {
			allocs = RESTServiceHelper.fetchAllAllocationsByEntity(entity);
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
			allocs = RESTServiceHelper.fetchAllAllocationsByEntity(entity);
			disStatusList = RESTServiceHelper.fetchAllStatusList(entity);
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
			admUsers = RESTServiceHelper.fetchActiveUsers();
			rewardsCat = RESTServiceHelper.fetchAllRewardsCat();
			pGrps = RESTServiceHelper.fetchActiveGroups();
			return "rwer";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform update reward request", "System error occurred, cannot perform update reward request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public void showPointProfile() {
		pointBeans = RESTServiceHelper.fetchAllPointsByUser(userId);
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

	public void checkTitleAvailability() {
		if (rewardsBean.getRwTitle() == null || rewardsBean.getRwTitle().length() == 0) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Enter Title to Check Availability", "Enter Title to Check Availability");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
		WebClient checkAvailablityClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rs/reward/check/title/" + rewardsBean.getRwTitle());
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

	public void modifyWishlist() {
		ResponseMessage response = RESTServiceHelper.modifyTag(rewardsBean.getRwId(), userId);
		if (response.getStatusCode() != 0 && response.getStatusCode() != 2)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Like", "Error While Saving Like"));
		viewRewardsBeans = RESTServiceHelper.fetchAllRewardsByUser(userId, totalPoints);
	}

	public void saveRewards() {
		try {
			if (titleAvail) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Title Not Available", "Title Not Available");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			}
			List<String> errors = validateRewards();
			if (errors.size() > 0) {
				for (String error : errors) {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
			}
			WebClient addRewardsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rs/rewards/add");
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
					WebClient createBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/create");
					AttachmentMessage attach = new AttachmentMessage();
					attach.setBlobContentType(rewardsBean.getRwFileType());
					attach.setBlobEntityId(message.getRwId());
					attach.setBlobEntityTblNm("ip_rewards");
					attach.setBlobName(rewardsBean.getRwFileName());
					attach.setBlobId(COUNTER.getNextId("IpBlob"));
					Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(attach);
					createBlobClient.close();
					if (crtRes.getStatus() == 200) {
						WebClient client = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/upload/" + attach.getBlobId(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
						client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
						client.header("Accept", MediaType.APPLICATION_JSON);
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
				viewRewardsBeans = RESTServiceHelper.fetchAllRewards(userId, totalPoints);
				showViewReward();
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			}
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create Reward request", "System error occurred, cannot perform create Reward request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public String updateRewards() {
		try {
			if (titleAvail) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Title Not Available", "Title Not Available");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
			List<String> errors = validateRewards();
			if (errors.size() > 0) {
				for (String error : errors) {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
				return "";
			}
			WebClient updateRewardsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rs/rewards/modify");
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
					WebClient getBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/getId/" + rewardsBean.getRwId() + "/ip_rewards");
					Long blobId = getBlobClient.accept(MediaType.APPLICATION_JSON).get(Long.class);
					if (blobId == -999) {
						WebClient createBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/create");
						AttachmentMessage attach = new AttachmentMessage();
						attach.setBlobContentType(rewardsBean.getRwFileType());
						attach.setBlobEntityId(rewardsBean.getRwId());
						attach.setBlobEntityTblNm("ip_rewards");
						attach.setBlobName(rewardsBean.getRwFileName());
						attach.setBlobId(COUNTER.getNextId("IpBlob"));
						Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(attach);
						if (crtRes.getStatus() == 200) {
							WebClient client = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/upload/" + attach.getBlobId(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
							client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
							client.header("Accept", MediaType.APPLICATION_JSON);
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
						WebClient updateBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/update");
						AttachmentMessage attach = new AttachmentMessage();
						attach.setBlobContentType(rewardsBean.getRwFileType());
						attach.setBlobEntityId(rewardsBean.getRwId());
						attach.setBlobEntityTblNm("ip_rewards");
						attach.setBlobName(rewardsBean.getRwFileName());
						attach.setBlobId(blobId);
						Response updRes = updateBlobClient.accept(MediaType.APPLICATION_JSON).put(attach);
						updateBlobClient.close();
						if (updRes.getStatus() == 200) {
							WebClient client = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/upload/" + blobId.toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
							client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
							client.header("Accept", MediaType.APPLICATION_JSON);
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
				viewRewardsBeans = RESTServiceHelper.fetchAllRewards(userId, totalPoints);
				return "rwvr";
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform update Reward request", "System error occurred, cannot perform update Reward request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public void saveAllocation() {
		try {
			WebClient saveAllocClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rs/alloc/add");
			AllocationMessage message = new AllocationMessage();
			message.setAllocDesc(allocationBean.getAllocDesc());
			message.setAllocEntity(entity);
			allocationBean.setAllocEntity(entity);
			message.setAllocId(COUNTER.getNextId("IpAllocation").intValue());
			message.setAllocStatusId(allocationBean.getAllocStatusId());
			message.setAllocVal(allocationBean.getAllocVal());
			message.setAllocCrtdDt(new Date());
			ResponseMessage res = saveAllocClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
			if (res.getStatusCode() == 0) {
				allocs = RESTServiceHelper.fetchAllAllocationsByEntity(entity);
				this.showAddPanel = false;
				this.showModPanel = false;
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform save allocation request", "System error occurred, cannot perform save allocation request");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			}
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform save allocation request", "System error occurred, cannot perform save allocation request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void savePoints() {
		try {
			WebClient savePointsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rs/points/add");
			PointMessage message = new PointMessage();
			message.setAllocId(selAllocId);
			message.setPointId(COUNTER.getNextId("IpPoints"));
			message.setPointValue(pointVal);
			message.setUserId(userId);
			message.setComments(comments);
			message.setCrtdDt(new Date());
			ResponseMessage res = savePointsClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
			if (res.getStatusCode() == 0) {
				selAllocId = null;
				pointVal = null;
				userId = null;
				entity = null;
				comments = null;
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucessfully added points", "Sucessfully added points");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform save point request", "System error occurred, cannot perform save point request");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			}
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform save point request", "System error occurred, cannot perform save point request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void updateAllocation() {
		try {
			WebClient updateAllocClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rs/alloc/update");
			AllocationMessage message = new AllocationMessage();
			message.setAllocDesc(allocationBean.getAllocDesc());
			message.setAllocEntity(entity);
			allocationBean.setAllocEntity(entity);
			message.setAllocId(allocationBean.getAllocId());
			message.setAllocStatusId(allocationBean.getAllocStatusId());
			message.setAllocVal(allocationBean.getAllocVal());
			message.setAllocCrtdDt(allocationBean.getAllocCrtdDt());
			ResponseMessage res = updateAllocClient.accept(MediaType.APPLICATION_JSON).put(message, ResponseMessage.class);
			if (res.getStatusCode() == 0) {
				allocs = RESTServiceHelper.fetchAllAllocationsByEntity(entity);
				this.showAddPanel = false;
				this.showModPanel = false;
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform update allocation request", "System error occurred, cannot perform update allocation request");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			}
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform update allocation request", "System error occurred, cannot perform update allocation request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void deleteAllocation() {
		try {
			WebClient updateAllocClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rs/alloc/delete");
			AllocationMessage message = new AllocationMessage();
			message.setAllocDesc(allocationBean.getAllocDesc());
			message.setAllocEntity(entity);
			message.setAllocId(allocationBean.getAllocId());
			message.setAllocStatusId(allocationBean.getAllocStatusId());
			message.setAllocVal(allocationBean.getAllocVal());
			message.setAllocCrtdDt(allocationBean.getAllocCrtdDt());
			ResponseMessage res = updateAllocClient.accept(MediaType.APPLICATION_JSON).put(message, ResponseMessage.class);
			if (res.getStatusCode() == 0) {
				allocs = RESTServiceHelper.fetchAllAllocationsByEntity(entity);
				this.showAddPanel = false;
				this.showModPanel = false;
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, res.getStatusDesc(), res.getStatusDesc());
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			}
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform update allocation request", "System error occurred, cannot perform update allocation request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void addToWishlist() {
		WebClient addTagClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ts/tag/add");
		TagMessage message = new TagMessage();
		message.setEntityId(rewardsBean.getRwId());
		message.setTagId(COUNTER.getNextId("IpTag"));
		message.setTeId(4);
		message.setTtId(4);
		message.setUserId(userId);
		message.setDuplicate(false);
		ResponseMessage response = addTagClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		addTagClient.close();
		if (response.getStatusCode() != 0 && response.getStatusCode() != 2)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Like", "Error While Saving Like"));
		viewRewardsBeans = RESTServiceHelper.fetchAllAvailableRewards(userId, totalPoints);
	}

	public void removeFromWishlist() {
		WebClient addTagClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ts/tag/add");
		TagMessage message = new TagMessage();
		message.setEntityId(rewardsBean.getRwId());
		message.setTagId(COUNTER.getNextId("IpTag"));
		message.setTeId(4);
		message.setTtId(4);
		message.setUserId(userId);
		message.setDuplicate(false);
		ResponseMessage response = addTagClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		addTagClient.close();
		if (response.getStatusCode() != 0 && response.getStatusCode() != 2)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Like", "Error While Saving Like"));
		viewRewardsBeans = RESTServiceHelper.fetchAllRewardsByUser(userId, totalPoints);
	}

	public void rwFileUploadHandle(FileUploadEvent fue) {
		try {
			UploadedFile file = fue.getFile();
			this.uploadContent = new DefaultStreamedContent(file.getInputstream());
			this.rewardsBean.setRwFileType(file.getContentType());
			this.rewardsBean.setRwFileName(file.getFileName());
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform upload request", "System error occurred, cannot perform upload request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public RewardsBean getRewardsBean() {
		if (rewardsBean == null)
			rewardsBean = new RewardsBean();
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
		if (viewRewardsBeans == null)
			viewRewardsBeans = new ArrayList<RewardsBean>();
		return viewRewardsBeans;
	}

	public void setViewRewardsBeans(List<RewardsBean> viewRewardsBeans) {
		this.viewRewardsBeans = viewRewardsBeans;
	}

	public List<ListSelectorBean> getRewardsCat() {
		if (rewardsCat == null)
			rewardsCat = new ArrayList<ListSelectorBean>();
		return rewardsCat;
	}

	public void setRewardsCat(List<ListSelectorBean> rewardsCat) {
		this.rewardsCat = rewardsCat;
	}

	public List<UserBean> getAdmUsers() {
		if (admUsers == null)
			admUsers = new ArrayList<UserBean>();
		return admUsers;
	}

	public void setAdmUsers(List<UserBean> admUsers) {
		this.admUsers = admUsers;
	}

	public List<TagBean> getRewardsWishlist() {
		if (rewardsWishlist == null)
			rewardsWishlist = new ArrayList<TagBean>();
		return rewardsWishlist;
	}

	public void setRewardsWishlist(List<TagBean> rewardsWishlist) {
		this.rewardsWishlist = rewardsWishlist;
	}

	public String getRewardsWishlistCnt() {
		if (rewardsWishlistCnt == null)
			rewardsWishlistCnt = "";
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
		if (entity == null)
			entity = "";
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
		if (allocId == null)
			allocId = "";
		return allocId;
	}

	public void setAllocId(String allocId) {
		this.allocId = allocId;
	}

	public List<GroupBean> getpGrps() {
		if (pGrps == null)
			pGrps = new ArrayList<GroupBean>();
		return pGrps;
	}

	public List<MetaDataBean> getChalStatusList() {
		chalStatusList = new ArrayList<MetaDataBean>();
		WebClient mDataClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ms/list/ip_challenge_status");
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
		WebClient mDataClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ms/list/ip_claim_status");
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

	public void setClaimStatusList(List<MetaDataBean> claimStatusList) {
		this.claimStatusList = claimStatusList;
	}

	public List<MetaDataBean> getIdeaStatusList() {
		ideaStatusList = new ArrayList<MetaDataBean>();
		WebClient mDataClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ms/list/ip_idea_status");
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
		WebClient mDataClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ms/list/ip_solution_status");
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
		if (disStatusList == null)
			disStatusList = new ArrayList<MetaDataBean>();
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
		if (comments == null)
			comments = "";
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public List<PointBean> getPointBeans() {
		if (pointBeans == null)
			pointBeans = new ArrayList<PointBean>();
		return pointBeans;
	}

	public void setPointBeans(List<PointBean> pointBeans) {
		this.pointBeans = pointBeans;
	}

	public PointBean getPointBean() {
		if (pointBean == null)
			pointBean = new PointBean();
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

	public boolean isShowCrtReward() {
		return showCrtReward;
	}

	public void setShowCrtReward(boolean showCrtReward) {
		this.showCrtReward = showCrtReward;
	}

	public boolean isShowViewReward() {
		return showViewReward;
	}

	public void setShowViewReward(boolean showViewReward) {
		this.showViewReward = showViewReward;
	}

	public boolean isShowOnlineStore() {
		return showOnlineStore;
	}

	public void setShowOnlineStore(boolean showOnlineStore) {
		this.showOnlineStore = showOnlineStore;
	}

	public boolean isTitleAvail() {
		return titleAvail;
	}

	public void setTitleAvail(boolean titleAvail) {
		this.titleAvail = titleAvail;
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

	public String getToView() {
		return toView;
	}

	public void setToView(String toView) {
		this.toView = toView;
	}

	public void setpGrps(List<GroupBean> pGrps) {
		this.pGrps = pGrps;
	}

}
