package za.co.idea.ip.portal.controller;

import java.io.Serializable;
import java.util.ArrayList;
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

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.primefaces.model.StreamedContent;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;

import za.co.idea.ip.portal.bean.ClaimBean;
import za.co.idea.ip.portal.bean.ListSelectorBean;
import za.co.idea.ip.portal.bean.RewardsBean;
import za.co.idea.ip.portal.bean.UserBean;
import za.co.idea.ip.portal.util.IdNumberGen;
import za.co.idea.ip.portal.util.RESTServiceHelper;
import za.co.idea.ip.ws.bean.ClaimMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;
import za.co.idea.ip.ws.bean.UserMessage;
import za.co.idea.ip.ws.util.CustomObjectMapper;

@ManagedBean(name = "claimController")
@SessionScoped
public class ClaimController implements Serializable {
	private static final Logger logger = Logger.getLogger(ClaimController.class);
	private static final long serialVersionUID = 1568895653520394971L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-portal");
	private ClaimBean claimBean;
	private StreamedContent fileContent;
	private List<ClaimBean> viewClaimBeans;
	private List<RewardsBean> viewRewardsBeans;
	private List<ListSelectorBean> claimStatus;
	private List<UserBean> admUsers;
	private Long totalPoints;
	private String selRwId;
	private Long userId;
	private String returnView;
	private static final IdNumberGen COUNTER = new IdNumberGen();
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

	public String redirectMain() {
		switch (Integer.valueOf(returnView)) {
		case 1:
			return "lani";
		case 2:
			return "clmvc";
		default:
			return "";
		}
	}

	public String showCreateClaim() {
		try {
			admUsers = RESTServiceHelper.fetchActiveUsers();
			claimStatus = RESTServiceHelper.fetchAllClaimStatuses();
			viewRewardsBeans = RESTServiceHelper.fetchAllAvailableRewards(userId, totalPoints);
			claimBean = new ClaimBean();
			selRwId = "";
			RESTServiceHelper.fetchAllPointsByUser(userId);
			return "clmcc";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create request", "System error occurred, cannot perform create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showClaimReward() {
		try {
			Map<String, String> reqMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			admUsers = RESTServiceHelper.fetchActiveUsers();
			claimStatus = RESTServiceHelper.fetchAllClaimStatuses();
			viewRewardsBeans = RESTServiceHelper.fetchAllAvailableRewards(userId, totalPoints);
			RESTServiceHelper.fetchAllPointsByUser(userId);
			claimBean = new ClaimBean();
			this.selRwId = reqMap.get("rewardsId") + "^" + reqMap.get("rwQuantity") + "^" + reqMap.get("rwValue");
			return "clmcc";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create request", "System error occurred, cannot perform create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showViewClaim() {
		try {
			admUsers = RESTServiceHelper.fetchAllUsers();
			claimStatus = RESTServiceHelper.fetchAllClaimStatuses();
			viewClaimBeans = RESTServiceHelper.fetchAllClaims();
			viewRewardsBeans = RESTServiceHelper.fetchAllRewards();
			return "clmvc";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showViewClaimByUser() {
		try {
			admUsers = RESTServiceHelper.fetchAllUsers();
			claimStatus = RESTServiceHelper.fetchAllClaimStatuses();
			viewClaimBeans = RESTServiceHelper.fetchAllClaimsByUser(userId);
			viewRewardsBeans = RESTServiceHelper.fetchAllRewards();
			return "clmuc";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showEditClaim() {
		try {
			admUsers = RESTServiceHelper.fetchActiveUsers();
			claimStatus = RESTServiceHelper.fetchNextClaimStatuses(claimBean.getcStatusId());
			viewRewardsBeans = RESTServiceHelper.fetchAllRewards();
			return "clmec";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform update request", "System error occurred, cannot perform update request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String saveClaim() {
		try {
			if (selRwId == null || selRwId.trim().length() == 0) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Select A Reward", "Please Select A Reward");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
			if (Long.valueOf(StringUtils.split(selRwId, "^")[1]) == 0) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Rewards Stock Not Available", "Rewards Stock Not Available");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
			if (totalPoints < Integer.valueOf(StringUtils.split(selRwId, "^")[2])) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not enough points to submit a claim for reward.", "Not enough points to submit a claim for reward.");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
			WebClient addClaimClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/cls/claim/add");
			ClaimMessage message = new ClaimMessage();
			message.setClaimCrtdDt(new Date());
			message.setClaimDesc(claimBean.getClaimDesc());
			message.setClaimId(COUNTER.getNextId("IpClaim"));
			message.setcStatusId(1);
			message.setRewardsId(Long.valueOf(StringUtils.split(selRwId, "^")[0]));
			message.setUserId(userId);
			ResponseMessage response = addClaimClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
			addClaimClient.close();
			if (response.getStatusCode() == 0) {
				return showViewClaimByUser();
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create request", "System error occurred, cannot perform create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String updateClaim() {
		try {
			WebClient updateClaimClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/cls/claim/modify");
			ClaimMessage message = new ClaimMessage();
			message.setClaimCrtdDt(claimBean.getClaimCrtdDt());
			message.setClaimDesc(claimBean.getClaimDesc());
			message.setClaimId(claimBean.getClaimId());
			message.setcStatusId(claimBean.getcStatusId());
			message.setClaimComment(claimBean.getClaimComment());
			message.setRewardsId(claimBean.getRewardsId());
			message.setUserId(claimBean.getUserId());
			ResponseMessage response = updateClaimClient.accept(MediaType.APPLICATION_JSON).put(message, ResponseMessage.class);
			updateClaimClient.close();
			if (response.getStatusCode() == 0) {
				return showViewClaim();
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


	public ClaimBean getClaimBean() {
		if (claimBean == null)
			claimBean = new ClaimBean();
		return claimBean;
	}

	public void setClaimBean(ClaimBean claimBean) {
		this.claimBean = claimBean;
	}

	public StreamedContent getFileContent() {
		return fileContent;
	}

	public void setFileContent(StreamedContent fileContent) {
		this.fileContent = fileContent;
	}

	public List<ClaimBean> getViewClaimBeans() {
		if (viewClaimBeans == null)
			viewClaimBeans = new ArrayList<ClaimBean>();
		return viewClaimBeans;
	}

	public void setViewClaimBeans(List<ClaimBean> viewClaimBeans) {
		this.viewClaimBeans = viewClaimBeans;
	}

	public List<ListSelectorBean> getClaimStatus() {
		if (claimStatus == null)
			claimStatus = new ArrayList<ListSelectorBean>();
		return claimStatus;
	}

	public void setClaimStatus(List<ListSelectorBean> claimStatus) {
		this.claimStatus = claimStatus;
	}

	public List<UserBean> getAdmUsers() {
		if (admUsers == null)
			admUsers = new ArrayList<UserBean>();
		return admUsers;
	}

	public void setAdmUsers(List<UserBean> admUsers) {
		this.admUsers = admUsers;
	}

	public List<RewardsBean> getViewRewardsBeans() {
		if (viewRewardsBeans == null)
			viewRewardsBeans = new ArrayList<RewardsBean>();
		return viewRewardsBeans;
	}

	public void setViewRewardsBeans(List<RewardsBean> viewRewardsBeans) {
		this.viewRewardsBeans = viewRewardsBeans;
	}

	public Long getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Long totalPoints) {
		this.totalPoints = totalPoints;
	}

	public String getSelRwId() {
		if (selRwId == null)
			selRwId = "";
		return selRwId;
	}

	public void setSelRwId(String selRwId) {
		this.selRwId = selRwId;
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