package za.co.idea.web.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.primefaces.model.StreamedContent;

import za.co.idea.ip.ws.bean.ClaimMessage;
import za.co.idea.ip.ws.bean.MetaDataMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;
import za.co.idea.ip.ws.bean.RewardsMessage;
import za.co.idea.ip.ws.bean.UserMessage;
import za.co.idea.ip.ws.util.CustomObjectMapper;
import za.co.idea.web.ui.bean.ClaimBean;
import za.co.idea.web.ui.bean.ListSelectorBean;
import za.co.idea.web.ui.bean.RewardsBean;
import za.co.idea.web.ui.bean.UserBean;
import za.co.idea.web.util.IdNumberGen;

public class ClaimController implements Serializable {

	private static final long serialVersionUID = 1568895653520394971L;
	private ClaimBean claimBean;
	private StreamedContent fileContent;
	private List<ClaimBean> viewClaimBeans;
	private List<RewardsBean> viewRewardsBeans;
	private List<ListSelectorBean> claimStatus;
	private List<UserBean> admUsers;
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

	public String showCreateClaim() {
		try {
			admUsers = fetchAllUsers();
			claimStatus = fetchAllClaimStatuses();
			viewRewardsBeans = fetchAllAvailableRewards();
			claimBean = new ClaimBean();
			return "clmcc";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showViewClaim() {
		try {
			admUsers = fetchAllUsers();
			claimStatus = fetchAllClaimStatuses();
			viewClaimBeans = fetchAllClaims();
			viewRewardsBeans = fetchAllRewards();
			return "clmvc";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showViewClaimByUser() {
		try {
			admUsers = fetchAllUsers();
			claimStatus = fetchAllClaimStatuses();
			viewClaimBeans = fetchAllClaimsByUser();
			viewRewardsBeans = fetchAllRewards();
			return "clmuc";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showEditClaim() {
		try {
			admUsers = fetchAllUsers();
			claimStatus = fetchNextClaimStatuses();
			viewRewardsBeans = fetchAllRewards();
			return "clmec";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String saveClaim() {
		try {
			WebClient addClaimClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/cls/claim/add");
			ClaimMessage message = new ClaimMessage();
			message.setClaimCrtdDt(new Date());
			message.setClaimDesc(claimBean.getClaimDesc());
			message.setClaimId(COUNTER.getNextId("IpClaim"));
			message.setcStatusId(1);
			message.setRewardsId(claimBean.getRewardsId());
			message.setUserId((Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId"));
			ResponseMessage response = addClaimClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
			addClaimClient.close();
			if (response.getStatusCode() == 0) {
				return showViewClaim();
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String updateClaim() {
		try {
			WebClient updateClaimClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/cls/claim/modify");
			ClaimMessage message = new ClaimMessage();
			message.setClaimCrtdDt(claimBean.getClaimCrtdDt());
			message.setClaimDesc(claimBean.getClaimDesc());
			message.setClaimId(claimBean.getClaimId());
			message.setcStatusId(claimBean.getcStatusId());
			message.setRewardsId(claimBean.getRewardsId());
			message.setUserId((Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId"));
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
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
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

	private List<ClaimBean> fetchAllClaims() {
		List<ClaimBean> ret = new ArrayList<ClaimBean>();
		WebClient fetchClaimClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/cls/claim/list");
		Collection<? extends ClaimMessage> claims = new ArrayList<ClaimMessage>(fetchClaimClient.accept(MediaType.APPLICATION_JSON).getCollection(ClaimMessage.class));
		fetchClaimClient.close();
		for (ClaimMessage message : claims) {
			ClaimBean bean = new ClaimBean();
			bean.setClaimCrtdDt(message.getClaimCrtdDt());
			bean.setClaimDesc(message.getClaimDesc());
			bean.setClaimId(message.getClaimId());
			bean.setcStatusId(message.getcStatusId());
			bean.setRewardsId(message.getRewardsId());
			bean.setUserId(message.getUserId());
			ret.add(bean);
		}
		return ret;
	}

	private List<ClaimBean> fetchAllClaimsByUser() {
		List<ClaimBean> ret = new ArrayList<ClaimBean>();
		WebClient fetchClaimClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/cls/claim/list/" + ((Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId")).longValue());
		Collection<? extends ClaimMessage> claims = new ArrayList<ClaimMessage>(fetchClaimClient.accept(MediaType.APPLICATION_JSON).getCollection(ClaimMessage.class));
		fetchClaimClient.close();
		for (ClaimMessage message : claims) {
			ClaimBean bean = new ClaimBean();
			bean.setClaimCrtdDt(message.getClaimCrtdDt());
			bean.setClaimDesc(message.getClaimDesc());
			bean.setClaimId(message.getClaimId());
			bean.setcStatusId(message.getcStatusId());
			bean.setRewardsId(message.getRewardsId());
			bean.setUserId(message.getUserId());
			ret.add(bean);
		}
		return ret;
	}

	private List<ListSelectorBean> fetchAllClaimStatuses() {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewClaimSelectClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/cls/claim/status/list");
		Collection<? extends MetaDataMessage> md = new ArrayList<MetaDataMessage>(viewClaimSelectClient.accept(MediaType.APPLICATION_JSON).getCollection(MetaDataMessage.class));
		viewClaimSelectClient.close();
		for (MetaDataMessage metaDataMessage : md) {
			ListSelectorBean bean = new ListSelectorBean();
			bean.setId(metaDataMessage.getId());
			bean.setDesc(metaDataMessage.getDesc());
			ret.add(bean);
		}
		return ret;
	}

	private List<ListSelectorBean> fetchNextClaimStatuses() {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewClaimSelectClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/cls/claim/status/list/" + claimBean.getcStatusId());
		Collection<? extends MetaDataMessage> md = new ArrayList<MetaDataMessage>(viewClaimSelectClient.accept(MediaType.APPLICATION_JSON).getCollection(MetaDataMessage.class));
		viewClaimSelectClient.close();
		for (MetaDataMessage metaDataMessage : md) {
			ListSelectorBean bean = new ListSelectorBean();
			bean.setId(metaDataMessage.getId());
			bean.setDesc(metaDataMessage.getDesc());
			ret.add(bean);
		}
		return ret;
	}

	private List<RewardsBean> fetchAllRewards() {
		List<RewardsBean> ret = new ArrayList<RewardsBean>();
		WebClient viewRewardsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/rs/rewards/list");
		Collection<? extends RewardsMessage> rewards = new ArrayList<RewardsMessage>(viewRewardsClient.accept(MediaType.APPLICATION_JSON).getCollection(RewardsMessage.class));
		viewRewardsClient.close();
		for (RewardsMessage message : rewards) {
			RewardsBean bean = new RewardsBean();
			bean.setrCatId(message.getrCatId());
			bean.setrStatusId(message.getrStatusId());
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
			ret.add(bean);
		}
		return ret;
	}

	private List<RewardsBean> fetchAllAvailableRewards() {
		List<RewardsBean> ret = new ArrayList<RewardsBean>();
		WebClient viewRewardsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/rs/rewards/list/3");
		Collection<? extends RewardsMessage> rewards = new ArrayList<RewardsMessage>(viewRewardsClient.accept(MediaType.APPLICATION_JSON).getCollection(RewardsMessage.class));
		viewRewardsClient.close();
		for (RewardsMessage message : rewards) {
			RewardsBean bean = new RewardsBean();
			bean.setrCatId(message.getrCatId());
			bean.setrStatusId(message.getrStatusId());
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
			ret.add(bean);
		}
		return ret;
	}

	public ClaimBean getClaimBean() {
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
		return viewClaimBeans;
	}

	public void setViewClaimBeans(List<ClaimBean> viewClaimBeans) {
		this.viewClaimBeans = viewClaimBeans;
	}

	public List<ListSelectorBean> getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(List<ListSelectorBean> claimStatus) {
		this.claimStatus = claimStatus;
	}

	public List<UserBean> getAdmUsers() {
		return admUsers;
	}

	public void setAdmUsers(List<UserBean> admUsers) {
		this.admUsers = admUsers;
	}

	public List<RewardsBean> getViewRewardsBeans() {
		return viewRewardsBeans;
	}

	public void setViewRewardsBeans(List<RewardsBean> viewRewardsBeans) {
		this.viewRewardsBeans = viewRewardsBeans;
	}
}
