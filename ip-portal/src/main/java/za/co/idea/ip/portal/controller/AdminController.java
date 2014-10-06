package za.co.idea.ip.portal.controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
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

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import za.co.idea.ip.portal.bean.AllocationBean;
import za.co.idea.ip.portal.bean.ClaimBean;
import za.co.idea.ip.portal.bean.FunctionBean;
import za.co.idea.ip.portal.bean.GroupBean;
import za.co.idea.ip.portal.bean.ListSelectorBean;
import za.co.idea.ip.portal.bean.MetaDataBean;
import za.co.idea.ip.portal.bean.NewsBean;
import za.co.idea.ip.portal.bean.NotificationBean;
import za.co.idea.ip.portal.bean.RewardsBean;
import za.co.idea.ip.portal.bean.UserBean;
import za.co.idea.ip.portal.bean.UserStatisticsBean;
import za.co.idea.ip.portal.util.IdNumberGen;
import za.co.idea.ip.portal.util.RESTServiceHelper;
import za.co.idea.ip.ws.bean.AllocationMessage;
import za.co.idea.ip.ws.bean.AttachmentMessage;
import za.co.idea.ip.ws.bean.ClaimMessage;
import za.co.idea.ip.ws.bean.FunctionMessage;
import za.co.idea.ip.ws.bean.GroupMessage;
import za.co.idea.ip.ws.bean.MetaDataMessage;
import za.co.idea.ip.ws.bean.NewsMessage;
import za.co.idea.ip.ws.bean.NotificationMessage;
import za.co.idea.ip.ws.bean.PointMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;
import za.co.idea.ip.ws.bean.UserMessage;
import za.co.idea.ip.ws.bean.UserStatisticsMessage;
import za.co.idea.ip.ws.util.CustomObjectMapper;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.restfb.DefaultWebRequestor;

@ManagedBean(name = "adminController")
@SessionScoped
public class AdminController implements Serializable {

	private static final long serialVersionUID = 4122782126915399730L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-portal");
	private static final Logger logger = Logger.getLogger(AdminController.class);

	private UserBean userBean;
	private ClaimBean claimBean;
	private GroupBean groupBean;
	private FunctionBean functionBean;
	private List<FunctionBean> functions;
	private List<GroupBean> pGrps;
	private List<UserBean> admUsers;
	private List<UserBean> viewUsers;
	private List<GroupBean> viewGroups;
	private List<MetaDataBean> secqList;
	private boolean available;
	private boolean availableID;
	private boolean availableEmail;
	private String curEmailId;
	private String curTitle;
	private String curEmpId;
	private Long curId;
	private boolean availableEmpID;
	private String secA;
	private String secQ;
	private StreamedContent image;
	private String fileName;
	private String contentType;
	private StreamedContent grpImage;
	private String grpFileName;
	private String grpContentType;
	private StreamedContent uploadImage;
	private StreamedContent uploadUser;
	private String uploadFileName;
	private String uploadContentType;
	private String uploadSrc;
	private boolean show;
	private boolean showDef;
	private boolean enableUpload;
	private String loggedScrName;
	private static final IdNumberGen COUNTER = new IdNumberGen();
	private String hierarchy;
	private ArrayList<String> uploadErrors;
	private boolean resetPasswd;
	private boolean resetSec;
	private boolean fileAvail;
	private StreamedContent fileContent;
	private String entity;
	private String returnView;
	private String toView;
	private List<AllocationBean> allocs;
	private AllocationBean allocationBean;
	private Integer pointVal;
	private Integer selAllocId;
	private String comments;
	private Long allocUserId;
	private boolean showAddPanel;
	private boolean showModPanel;
	private boolean showAddBtn;
	private List<MetaDataBean> statusList;
	private List<MetaDataBean> disStatusList;
	private HashMap<String, String> allocEntityList;
	private List<MetaDataBean> chalStatusList;
	private List<MetaDataBean> claimStatusList;
	private List<MetaDataBean> ideaStatusList;
	private List<MetaDataBean> solStatusList;
	private Long userId;
	private List<MetaDataBean> beans;
	private MetaDataBean bean;
	private String table;
	private String selId;
	private String selVal;
	private HashMap<String, String> metaList;
	private NewsBean newsBean;
	private List<NewsBean> viewNewsBeans;
	private List<NotificationBean> viewNotifications;
	private StreamedContent uploadContent;
	private NotificationBean notificationBean;
	private Date lastLoginDt;
	private String[] selGrpId;
	private String[] selUserId;
	private UserStatisticsBean statsBean;
	private String imgPath;
	private boolean imgAvail;
	private String grpImgPath;
	private boolean grpImgAvail;
	private List<UserStatisticsBean> viewStatsBean;
	private boolean showEditProfile;
	private boolean showChangePwd;
	private boolean showChangeSecQ;
	private List<ClaimBean> viewClaimBeans;
	private List<RewardsBean> viewRewardsBeans;
	private List<ListSelectorBean> claimStatus;
	private boolean activeUsersView;
	private boolean inactiveUsersView;
	private AccessController controller;
	public boolean showAllocIdeaReview;
	public boolean uploadSuccess;
	public List<String> selUsers;

	private WebClient createCustomClient(String url) {
		WebClient client = WebClient.create(url, Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
		client.header("Content-Type", MediaType.APPLICATION_JSON);
		client.header("Accept", MediaType.APPLICATION_JSON);
		return client;
	}

	@PostConstruct
	public void initialize() {
		initializePage();
		initializeProfilePage();
	}

	public void initializePage() {
		try {
			PortletRequest request = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			User user = (User) request.getAttribute(WebKeys.USER);
			WebClient client = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/verify/" + user.getScreenName());
			UserMessage message = client.accept(MediaType.APPLICATION_JSON).get(UserMessage.class);
			userId = message.getuId();
			viewUsers = RESTServiceHelper.fetchAllUsers();
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void initializeProfilePage() {
		try {
			PortletRequest request = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			User user = (User) request.getAttribute(WebKeys.USER);
			WebClient client = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/verify/" + user.getScreenName());
			UserMessage message = client.accept(MediaType.APPLICATION_JSON).get(UserMessage.class);
			userId = message.getuId();
			loggedScrName = message.getScName();
			imgPath = message.getImgPath();
			imgAvail = message.isImgAvail();
			grpImgPath = message.getGrpImgPath();
			grpImgAvail = message.isGrpImgAvail();
			if (message.getGroupId() != null) {
				WebClient hClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/group/hierarchy/" + message.getGroupId());
				hierarchy = hClient.accept(MediaType.APPLICATION_JSON).get(String.class);
				hClient.close();
			} else {
				hierarchy = "assign primary group";
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
			return "adminv";
		default:
			return "";
		}
	}

	public String showCreateNews() {
		try {
			newsBean = new NewsBean();
			return "admcn";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform news create request", "System error occurred, cannot perform news create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showViewNews() {
		try {
			viewNewsBeans = RESTServiceHelper.fetchAllNews();
			return "admvn";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform news view request", "System error occurred, cannot perform news view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showEditNews() {
		try {
			return "admen";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			fileAvail = true;
			fileContent = null;
			return "";
		}
	}

	public String showSummaryNews() {
		try {
			viewNewsBeans = RESTServiceHelper.fetchAllNews();
			return "admsn";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform news view request", "System error occurred, cannot perform news view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showAllocatePoints() {
		try {
			entity = "";
			admUsers = RESTServiceHelper.fetchAllUsers();
			allocs = new ArrayList<AllocationBean>();
			return "admap";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view reward request", "System error occurred, cannot perform view reward request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showCreateFunction() {
		try {
			pGrps = RESTServiceHelper.fetchActiveGroups();
			return "admfc";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create request", "System error occurred, cannot perform create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showViewFunction() {
		try {
			functions = RESTServiceHelper.fetchAllFunctions();
			admUsers = RESTServiceHelper.fetchAllUsers();
			pGrps = RESTServiceHelper.fetchActiveGroups();
			return "admfv";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showViewGroups() {
		try {
			pGrps = viewGroups = RESTServiceHelper.fetchAllGroups();
			admUsers = RESTServiceHelper.fetchAllUsers();
			return "admgv";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot view groups request", "System error occurred, cannot view groups request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showCreateGroup() {
		try {
			groupBean = new GroupBean();
			viewGroups = pGrps = RESTServiceHelper.fetchActiveGroups();
			admUsers = RESTServiceHelper.fetchAllUsersSortByPG();
			return "admgc";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform created groups view request", "System error occurred, cannot perform created groups view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showEditGroup() {
		try {
			viewGroups = pGrps = RESTServiceHelper.fetchActiveGroups();
			admUsers = RESTServiceHelper.fetchAllUsersSortByPG();
			functions = RESTServiceHelper.fetchAllFunctionsByGroup(groupBean.getgId());
			selUserId = toStringArray(RESTServiceHelper.fetchUsersByGroup(groupBean.getgId()));
			return "admge";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform updated groups view request", "System error occurred, cannot perform updated groups view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showSummaryGroup() {
		try {
			viewGroups = pGrps = RESTServiceHelper.fetchAllGroups();
			admUsers = RESTServiceHelper.fetchAllUsersSortByPG();
			functions = RESTServiceHelper.fetchAllFunctionsByGroup(groupBean.getgId());
			return "admgs";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform updated groups view request", "System error occurred, cannot perform updated groups view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showCreateNotification() {
		try {
			notificationBean = new NotificationBean();
			pGrps = RESTServiceHelper.fetchActiveGroups();
			return "admcno";
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

	public String showEditClaim() {
		try {
			admUsers = RESTServiceHelper.fetchAllUsers();
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

	public String showViewUsers() {
		try {
			activeUsersView = false;
			inactiveUsersView = false;
			viewUsers = RESTServiceHelper.fetchAllUsers();
			return "admuv";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform Users View request", "System error occurred, cannot perform Users View request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showViewActiveUsers() {
		try {
			activeUsersView = true;
			inactiveUsersView = false;
			viewUsers = RESTServiceHelper.fetchActiveUsers();
			return "admuav";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform Users View request", "System error occurred, cannot perform Users View request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showViewInactiveUsers() {
		try {
			activeUsersView = false;
			inactiveUsersView = true;
			viewUsers = RESTServiceHelper.fetchInActiveUsers();
			return "admuiv";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform Users View request", "System error occurred, cannot perform Users View request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showUploadUsers() {
		this.uploadImage = null;
		this.uploadFileName = null;
		this.uploadContentType = null;
		enableUpload = false;
		return "admuu";
	}

	public String showInactivateUsers() {
		this.uploadImage = null;
		this.uploadFileName = null;
		this.uploadContentType = null;
		enableUpload = false;
		return "admiu";
	}

	public String showAllocateGroups() {
		return "admair";
	}

	public String showRPw() {
		secqList = RESTServiceHelper.fetchAllSecQ();
		return "";
	}

	public String showEditUser() {
		try {
			secqList = RESTServiceHelper.fetchAllSecQ();
			viewGroups = RESTServiceHelper.fetchActiveGroups();
			resetPasswd = false;
			resetSec = false;
			curTitle = userBean.getScName();
			curId = userBean.getIdNum();
			curEmailId = userBean.geteMail();
			curEmailId = userBean.getEmployeeId();
			return "admue";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform updated user view request", "System error occurred, cannot perform updated user view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showEditActiveUser() {
		try {
			activeUsersView = true;
			inactiveUsersView = false;
			secqList = RESTServiceHelper.fetchAllSecQ();
			viewGroups = RESTServiceHelper.fetchActiveGroups();
			resetPasswd = false;
			resetSec = false;
			return "admuae";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform updated user view request", "System error occurred, cannot perform updated user view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showEditInActiveUser() {
		try {
			inactiveUsersView = true;
			activeUsersView = false;
			secqList = RESTServiceHelper.fetchAllSecQ();
			viewGroups = RESTServiceHelper.fetchActiveGroups();
			resetPasswd = false;
			resetSec = false;
			return "admuie";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform updated user view request", "System error occurred, cannot perform updated user view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public void initializeEditProfile() {
		try {
			viewGroups = RESTServiceHelper.fetchActiveGroups();
			PortletRequest request = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			User user = (User) request.getAttribute(WebKeys.USER);
			WebClient client = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/verify/" + user.getScreenName());
			UserMessage userMessage = client.accept(MediaType.APPLICATION_JSON).get(UserMessage.class);
			userId = userMessage.getuId();
			userBean = new UserBean();
			userBean.setBio(userMessage.getBio());
			userBean.setContact(userMessage.getContact());
			userBean.seteMail(userMessage.geteMail());
			userBean.setFbHandle(userMessage.getFbHandle());
			userBean.setfName(userMessage.getfName());
			userBean.setIdNum(userMessage.getIdNum());
			userBean.setIsActive(userMessage.getIsActive());
			userBean.setlName(userMessage.getlName());
			userBean.setmName(userMessage.getmName());
			userBean.setPwd(userMessage.getPwd());
			userBean.setScName(userMessage.getScName());
			userBean.setSkills(userMessage.getSkills());
			userBean.setTwHandle(userMessage.getTwHandle());
			userBean.setIsActive(userMessage.getIsActive());
			userBean.setuId(userMessage.getuId());
			userBean.setEmployeeId(userMessage.getEmployeeId());
			userBean.setPriGroupName(userMessage.getPriGroupName());
			userBean.setGroupId(userMessage.getGroupId());
			userBean.setcPw(userBean.getPwd());
			viewGroups = RESTServiceHelper.fetchAllGroups();
			secqList = RESTServiceHelper.fetchAllSecQ();
			showEditProfile = true;
			showChangePwd = false;
			showChangeSecQ = false;
			secqList = RESTServiceHelper.fetchAllSecQ();
			if (toView != null && Integer.valueOf(toView) != -1) {
				switch (Integer.valueOf(toView)) {
				case 1:
					showEditProfile = true;
					showChangePwd = false;
					showChangeSecQ = false;
					break;
				case 2:
					showEditProfile = false;
					showChangePwd = true;
					showChangeSecQ = false;
					break;
				case 3:
					showEditProfile = false;
					showChangePwd = false;
					showChangeSecQ = true;
					break;
				}
			}

		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform updated user profile view request", "System error occurred, cannot perform updated user profile view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void changeEditProfile() {
		viewGroups = RESTServiceHelper.fetchActiveGroups();
		PortletRequest request = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		User user = (User) request.getAttribute(WebKeys.USER);
		WebClient client = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/verify/" + user.getScreenName());
		UserMessage userMessage = client.accept(MediaType.APPLICATION_JSON).get(UserMessage.class);
		userId = userMessage.getuId();
		userBean = RESTServiceHelper.getUserBean(userMessage);
		showEditProfile = true;
		showChangePwd = false;
		showChangeSecQ = false;
	}

	public void changePwd() {
		secqList = RESTServiceHelper.fetchAllSecQ();
		PortletRequest request = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		User user = (User) request.getAttribute(WebKeys.USER);
		WebClient client = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/verify/" + user.getScreenName());
		UserMessage userMessage = client.accept(MediaType.APPLICATION_JSON).get(UserMessage.class);
		userId = userMessage.getuId();
		userBean = RESTServiceHelper.getUserBean(userMessage);
		showEditProfile = false;
		showChangePwd = true;
		showChangeSecQ = false;
	}

	public void changeSecurity() {
		PortletRequest request = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		User user = (User) request.getAttribute(WebKeys.USER);
		WebClient client = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/verify/" + user.getScreenName());
		UserMessage userMessage = client.accept(MediaType.APPLICATION_JSON).get(UserMessage.class);
		userId = userMessage.getuId();
		userBean = RESTServiceHelper.getUserBean(userMessage);
		secqList = RESTServiceHelper.fetchAllSecQ();
		showEditProfile = false;
		showChangePwd = false;
		showChangeSecQ = true;
	}

	public String showCreateUser() {
		try {
			userBean = new UserBean();
			secqList = RESTServiceHelper.fetchAllSecQ();
			viewGroups = RESTServiceHelper.fetchActiveGroups();
			return "admuc";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform created user view request", "System error occurred, cannot perform created user view request");
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
			return "admmpa";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view reward request", "System error occurred, cannot perform view reward request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showEditFunction() {
		try {
			pGrps = RESTServiceHelper.fetchActiveGroups();
			return "admfe";
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform update request", "System error occurred, cannot perform update request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showMetadataMaintain() {
		this.showAddPanel = false;
		this.showModPanel = false;
		this.showAddBtn = false;
		this.table = "";
		this.beans = null;
		return "admmm";
	}

	public void verifyLogin() {
		WebClient loginClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/verify/" + userBean.getScName());
		UserMessage userMessage = loginClient.accept(MediaType.APPLICATION_JSON).get(UserMessage.class);
		loginClient.close();
		if (userMessage == null) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid User Name Password", "Invalid User Name Password");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		} else {
			userBean.setScName(userMessage.getScName());
			userBean.setSecQ(userMessage.getSecQ());
			secQ = userMessage.getSecQ().toString();
			userBean.setSecA(userMessage.getSecA());
		}
	}

	public void resetPassword() {
		ArrayList<String> errors = validateRPwd();
		if (errors != null && errors.size() > 0) {
			for (String error : errors) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			}
		} else {
			try {
				if (Base64.encodeBase64URLSafeString(DigestUtils.md5(secA.getBytes())).equalsIgnoreCase(userBean.getSecA())) {
					User user = UserLocalServiceUtil.getUserByScreenName(10154, userBean.getScName());
					if (Validator.isNotNull(user)) {
						user = UserLocalServiceUtil.updatePassword(user.getUserId(), userBean.getPwd(), userBean.getcPw(), false);
						if (Validator.isNotNull(user)) {
							WebClient loginClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/rpw/");
							ResponseMessage response = loginClient.accept(MediaType.APPLICATION_JSON).put(new String[] { userBean.getScName(), Base64.encodeBase64URLSafeString(DigestUtils.md5(userBean.getPwd().getBytes())), userBean.getPwd() }, ResponseMessage.class);
							loginClient.close();
							if (response.getStatusCode() == 0) {
								FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Password Reset Successful", "Password Reset Successful");
								FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
							} else {
								FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusCode() + " :: " + response.getStatusDesc(), response.getStatusCode() + " :: " + response.getStatusDesc());
								FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
							}
						} else {
							FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Liferay User password Reset Error", "Liferay User password Reset Error");
							FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
						}
					} else {
						WebClient loginClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/rpw/");
						ResponseMessage response = loginClient.accept(MediaType.APPLICATION_JSON).put(new String[] { userBean.getScName(), Base64.encodeBase64URLSafeString(DigestUtils.md5(userBean.getPwd().getBytes())), userBean.getPwd() }, ResponseMessage.class);
						loginClient.close();
						if (response.getStatusCode() == 0) {
							FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Password Reset Successful", "Password Reset Successful");
							FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
						} else {
							FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusCode() + " :: " + response.getStatusDesc(), response.getStatusCode() + " :: " + response.getStatusDesc());
							FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
						}
					}
				} else {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Security Answer", "Invalid Security Answer");
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
			} catch (Exception e) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "User password Reset Error", "User password Reset Error");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			}
		}
		showEditProfile = false;
		showChangePwd = true;
		showChangeSecQ = false;
	}

	public void resetSecurity() {
		if (secQ == null || secQ.length() == 0) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Security Question is Mandatory", "Security Question is Mandatory");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		} else if (secA == null || secA.length() == 0) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Security Answer is Mandatory", "Security Answer is Mandatory");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		} else {
			WebClient loginClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/rsec");
			ResponseMessage response = loginClient.accept(MediaType.APPLICATION_JSON).put(new String[] { userBean.getScName(), secQ.toString(), secA }, ResponseMessage.class);
			loginClient.close();
			this.userBean.setSecA(secA);
			this.userBean.setSecQ(Integer.valueOf(secQ));
			if (response.getStatusCode() != 0) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusCode() + " :: " + response.getStatusDesc(), response.getStatusCode() + " :: " + response.getStatusDesc());
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Security Question Update Successfull", "Security Question Update Successfull");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			}
		}
		showEditProfile = false;
		showChangePwd = false;
		showChangeSecQ = true;
	}

	public void logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}

	public String savePoints() {
		try {
			WebClient savePointsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rs/points/add");
			PointMessage message = new PointMessage();
			message.setAllocId(selAllocId);
			message.setPointId(COUNTER.getNextId("IpPoints"));
			message.setPointValue(pointVal);
			message.setUserId(allocUserId);
			message.setComments(comments);
			message.setCrtdDt(new Date());
			ResponseMessage res = savePointsClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
			if (res.getStatusCode() == 0) {
				selAllocId = null;
				pointVal = null;
				allocUserId = null;
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
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform save point request", "System error occurred, cannot perform save point request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showAllocationMod() {
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
		return "";
	}

	public String showAllocationAdd() {
		this.showAddPanel = true;
		this.showModPanel = false;
		statusList = RESTServiceHelper.fetchAllNonAllocStatus(entity);
		disStatusList = RESTServiceHelper.fetchAllStatusList(entity);
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

	public String updateAllocation() {
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
		return "";
	}

	public String deleteAllocation() {
		try {
			WebClient updateAllocClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rs/alloc/delete");
			AllocationMessage message = new AllocationMessage();
			message.setAllocDesc(allocationBean.getAllocDesc());
			message.setAllocEntity(entity);
			message.setAllocId(allocationBean.getAllocId());
			message.setAllocStatusId(allocationBean.getAllocStatusId());
			message.setAllocVal(allocationBean.getAllocVal());
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
		return "";
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

	public String saveAllocation() {
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
				return "";
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform save allocation request", "System error occurred, cannot perform save allocation request");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform save allocation request", "System error occurred, cannot perform save allocation request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
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

	public void checkAvailability() {
		if (userBean.getScName() == null || userBean.getScName().length() == 0) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Enter Screen Name to Check Availability", "Enter Screen Name to Check Availability");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		} else if (curTitle != null && !curTitle.equals(userBean.getScName())) {
			WebClient checkAvailablityClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/check/screenName/" + userBean.getScName());
			Boolean avail = checkAvailablityClient.accept(MediaType.APPLICATION_JSON).get(Boolean.class);
			checkAvailablityClient.close();
			available = avail.booleanValue();
			if (available) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Screen Name Not Available", "Screen Name Not Available");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Screen Name Available", "Screen Name Available");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			}

		}
	}

	public void checkAvailabilityEmail() {
		if (userBean.geteMail() == null || userBean.geteMail().length() == 0) {

		} else if (curEmailId != null && !curEmailId.equals(userBean.geteMail())) {
			WebClient checkAvailablityClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/check/email/" + userBean.geteMail());
			Boolean availE = checkAvailablityClient.accept(MediaType.APPLICATION_JSON).get(Boolean.class);
			checkAvailablityClient.close();
			availableEmail = availE.booleanValue();
			if (availableEmail) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email already exists", "Email already exists");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			}
		}
	}

	public void checkAvailabilityIDNumber() {
		if (userBean.getIdNum() == null || userBean.getIdNum() == 0) {

		} else if (curId != null && curId != userBean.getIdNum()) {
			WebClient checkAvailablityClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/check/idNumber/" + userBean.getIdNum());
			Boolean availID = checkAvailablityClient.accept(MediaType.APPLICATION_JSON).get(Boolean.class);
			checkAvailablityClient.close();
			availableID = availID.booleanValue();
			if (availableID) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ID Number already exists", "ID Number already exists");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			}
		}
	}

	public void checkAvailabilityEmployeeID() {
		if (userBean.getEmployeeId() == null || userBean.getEmployeeId().length() == 0) {

		} else if (curEmpId != null && !curEmpId.equals(userBean.getEmployeeId())) {
			WebClient checkAvailablityClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/check/employeeId/" + userBean.getEmployeeId());
			Boolean availEmpID = checkAvailablityClient.accept(MediaType.APPLICATION_JSON).get(Boolean.class);
			checkAvailablityClient.close();
			availableEmpID = availEmpID.booleanValue();
			if (availableEmpID) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Employee ID already exists", "Employee ID already exists");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			}
		}
	}

	public String saveUploadUsers() {
		int i = 1;
		try {
			uploadErrors = null;
			uploadSuccess = true;
			Workbook workbook = null;
			if (uploadFileName.endsWith("xlsx"))
				workbook = new XSSFWorkbook(uploadUser.getStream());
			else if (uploadFileName.endsWith("xls"))
				workbook = new HSSFWorkbook(uploadUser.getStream());
			Sheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(i);
			String empCode = "";
			try {
				if (row.getCell(0) != null && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
					empCode = row.getCell(0).getStringCellValue();
				} else if (row.getCell(0) != null && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					empCode = row.getCell(0).getNumericCellValue() + "";
				}
			} catch (Exception e) {
				logger.error("Error : " + e);
			}
			while (empCode.length() != 0) {
				try {
					UserBean bean = new UserBean();
					String contactNum = "";
					String mailID = "";
					Long idNum = 0l;
					String lName = "";
					String fname = "";
					String initial = "";
					try {
						if (row.getCell(1) != null && row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_STRING) {
							lName = row.getCell(1).getStringCellValue();
						}
						if (row.getCell(2) != null && row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_STRING) {
							initial = row.getCell(2).getStringCellValue();
						}
						if (row.getCell(3) != null && row.getCell(3).getCellType() == HSSFCell.CELL_TYPE_STRING) {
							fname = row.getCell(3).getStringCellValue();
						}
						if (row.getCell(4) != null && row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_STRING) {
							idNum = Long.parseLong(row.getCell(4).getStringCellValue());
						} else if (row.getCell(4) != null && row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							idNum = (long) row.getCell(4).getNumericCellValue();
						}
						if (row.getCell(5) != null && row.getCell(5).getCellType() == HSSFCell.CELL_TYPE_STRING) {
							contactNum = row.getCell(5).getStringCellValue();
						} else if (row.getCell(5) != null && row.getCell(5).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							contactNum = row.getCell(5).getNumericCellValue() + "";
						}
						if (row.getCell(6) != null && row.getCell(6).getCellType() == HSSFCell.CELL_TYPE_STRING) {
							mailID = row.getCell(6).getStringCellValue();
						}

					} catch (Exception e) {

					}

					bean.setContact("+27 " + contactNum.substring(1));
					bean.seteMail(mailID);
					bean.setEmployeeId(empCode);
					bean.setIdNum(idNum);
					bean.setIsActive(false);
					bean.setlName(lName);
					bean.setfName(fname);
					bean.setPwd("Passw123");
					bean.setSecQ(4);
					bean.setSecA("Johannesburg");
					bean.setScName(bean.getlName().toLowerCase() + initial.toLowerCase());
					if (bean.geteMail() != null && bean.geteMail().length() != 0) {
						WebClient checkEAvailablityClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/check/email/" + bean.geteMail());
						Boolean availE = checkEAvailablityClient.accept(MediaType.APPLICATION_JSON).get(Boolean.class);
						checkEAvailablityClient.close();
						if (availE) {
							uploadSuccess = false;
							getUploadErrors().add("EMail Id already registered :: " + bean.geteMail() + " row number :: " + i);
							i++;
							row = sheet.getRow(i);
							if (row != null) {
								if (row.getCell(0) != null && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
									empCode = row.getCell(0).getStringCellValue();
								} else if (row.getCell(0) != null && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
									empCode = row.getCell(0).getNumericCellValue() + "";
								}
							} else {
								empCode = "";
							}
							continue;
						}
					}
					if (bean.getIdNum() != null) {
						WebClient checkIAvailablityClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/check/idNumber/" + bean.getIdNum());
						Boolean availID = checkIAvailablityClient.accept(MediaType.APPLICATION_JSON).get(Boolean.class);
						checkIAvailablityClient.close();
						if (availID) {
							uploadSuccess = false;
							getUploadErrors().add("Id Number already registered :: " + bean.getIdNum() + " row number :: " + i);
							i++;
							row = sheet.getRow(i);
							if (row != null) {
								if (row.getCell(0) != null && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
									empCode = row.getCell(0).getStringCellValue();
								} else if (row.getCell(0) != null && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
									empCode = row.getCell(0).getNumericCellValue() + "";
								}
							} else {
								empCode = "";
							}
							continue;
						}
					} else {
						uploadSuccess = false;
						getUploadErrors().add("Id Number cannot be empty row number :: " + i);
						i++;
						row = sheet.getRow(i);
						if (row != null) {
							if (row.getCell(0) != null && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
								empCode = row.getCell(0).getStringCellValue();
							} else if (row.getCell(0) != null && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
								empCode = row.getCell(0).getNumericCellValue() + "";
							}
						} else {
							empCode = "";
						}
						continue;
					}
					if (bean.getEmployeeId() != null && bean.getEmployeeId().length() != 0) {
						WebClient checkEIAvailablityClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/check/employeeId/" + bean.getEmployeeId());
						Boolean availEmpID = checkEIAvailablityClient.accept(MediaType.APPLICATION_JSON).get(Boolean.class);
						checkEIAvailablityClient.close();
						if (availEmpID) {
							uploadSuccess = false;
							getUploadErrors().add("Employee Id already registered :: " + bean.getEmployeeId() + " row number :: " + i);
							i++;
							if (row != null) {
								if (row.getCell(0) != null && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
									empCode = row.getCell(0).getStringCellValue();
								} else if (row.getCell(0) != null && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
									empCode = row.getCell(0).getNumericCellValue() + "";
								}
							} else {
								empCode = "";
							}
							continue;
						}
					}
					WebClient checkAvailablityClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/check/screenName/" + bean.getScName());
					Boolean avail = checkAvailablityClient.accept(MediaType.APPLICATION_JSON).get(Boolean.class);
					checkAvailablityClient.close();
					if (avail)
						bean.setScName(bean.getScName() + i);
					WebClient addUserClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/add");
					UserMessage msg = new UserMessage();
					msg.setContact(bean.getContact());
					msg.seteMail(bean.geteMail());
					msg.setfName(bean.getfName());
					msg.setIdNum(bean.getIdNum());
					msg.setlName(bean.getlName());
					msg.setPwd(bean.getPwd());
					msg.setScName(bean.getScName());
					msg.setIsActive(true);
					msg.setuId(COUNTER.getNextId("IpUser"));
					msg.setLastLoginDt(new Date());
					msg.setSecQ(bean.getSecQ());
					msg.setSecA(bean.getSecA());
					msg.setEmployeeId(bean.getEmployeeId());
					msg.setGroupId(14l);
					msg.setuCrtdDate(new Date());
					ResponseMessage response = addUserClient.accept(MediaType.APPLICATION_JSON).post(msg, ResponseMessage.class);
					addUserClient.close();
					if (response.getStatusCode() != 0) {
						getUploadErrors().add("User creation service call failed due to :: " + response.getStatusDesc() + " row number :: " + i);
						i++;
						row = sheet.getRow(i);
						if (row != null) {
							if (row.getCell(0) != null && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
								empCode = row.getCell(0).getStringCellValue();
							} else if (row.getCell(0) != null && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
								empCode = row.getCell(0).getNumericCellValue() + "";
							}
						} else {
							empCode = "";
						}
						continue;
					}
				} catch (Exception e) {
					logger.error(e, e);
					uploadSuccess = false;
					getUploadErrors().add("User creation service call failed due to :: " + e.getMessage() + " row number :: " + i);
					i++;
					row = sheet.getRow(i);
					if (row != null) {
						if (row.getCell(0) != null && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
							empCode = row.getCell(0).getStringCellValue();
						} else if (row.getCell(0) != null && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
							empCode = row.getCell(0).getNumericCellValue() + "";
						}
					} else {
						empCode = "";
					}
					continue;
				}
				i++;
				row = sheet.getRow(i);
				if (row != null) {
					if (row.getCell(0) != null && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
						empCode = row.getCell(0).getStringCellValue();
					} else if (row.getCell(0) != null && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
						empCode = row.getCell(0).getNumericCellValue() + "";
					}
				} else {
					empCode = "";
				}
			}
		} catch (Exception e) {
			logger.error(e, e);
			uploadSuccess = false;
			getUploadErrors().add("User creation service call failed due to :: " + e.getMessage() + " row number :: " + i);
			return "admuur";
		}
		FacesMessage successMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "File has been uploaded successfully ", "File has been uploaded successfully");
		FacesContext.getCurrentInstance().addMessage(null, successMessage);
		return "admuur";
	}

	public String makeUsersAsInactive() {
		int i = 1;
		try {
			uploadErrors = null;
			uploadSuccess = true;
			Workbook workbook = null;
			if (uploadFileName.endsWith("xlsx"))
				workbook = new XSSFWorkbook(uploadUser.getStream());
			else if (uploadFileName.endsWith("xls"))
				workbook = new HSSFWorkbook(uploadUser.getStream());
			Sheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(i);
			String mailID = "";
			try {
				if (row.getCell(6) != null && row.getCell(6).getCellType() == HSSFCell.CELL_TYPE_STRING) {
					mailID = row.getCell(6).getStringCellValue();
				}
			} catch (Exception e) {
				logger.error("Error : " + e);
			}
			while (mailID.length() != 0) {
				try {
					if (mailID != null && mailID.length() != 0) {
						WebClient checkEAvailablityClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/check/email/" + mailID);
						Boolean availE = checkEAvailablityClient.accept(MediaType.APPLICATION_JSON).get(Boolean.class);
						checkEAvailablityClient.close();
						if (availE) {
							WebClient userInactivateClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/inactivate/email");
							ResponseMessage resUserInactivate = userInactivateClient.accept(MediaType.APPLICATION_JSON).put(mailID, ResponseMessage.class);
							userInactivateClient.close();
							if (resUserInactivate.getStatusCode() != 0) {
								uploadSuccess = false;
								getUploadErrors().add("User is not inactivated :: " + mailID + " row number :: " + i);
							}
							i++;
							row = sheet.getRow(i);
							if (row != null) {
								if (row.getCell(0) != null && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
									mailID = row.getCell(0).getStringCellValue();
								} else if (row.getCell(0) != null && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
									mailID = row.getCell(0).getNumericCellValue() + "";
								}
							} else {
								mailID = "";
							}
							continue;
						} else {
							uploadSuccess = false;
							getUploadErrors().add("Email Id is not valid :: " + mailID + " row number :: " + i);
							i++;
							row = sheet.getRow(i);
							if (row != null) {
								if (row.getCell(6) != null && row.getCell(6).getCellType() == HSSFCell.CELL_TYPE_STRING) {
									mailID = row.getCell(6).getStringCellValue();
								}
							} else {
								mailID = "";
							}
							continue;
						}
					}

				} catch (Exception e) {
					logger.error(e, e);
					uploadSuccess = false;
					getUploadErrors().add("User creation service call failed due to :: " + e.getMessage() + " row number :: " + i);
					i++;
					row = sheet.getRow(i);
					if (row != null) {
						if (row.getCell(6) != null && row.getCell(6).getCellType() == HSSFCell.CELL_TYPE_STRING) {
							mailID = row.getCell(6).getStringCellValue();
						}
					} else {
						mailID = "";
					}
					continue;
				}
				i++;
				row = sheet.getRow(i);
				if (row != null) {
					if (row.getCell(6) != null && row.getCell(6).getCellType() == HSSFCell.CELL_TYPE_STRING) {
						mailID = row.getCell(6).getStringCellValue();
					}
				} else {
					mailID = "";
				}
			}
		} catch (Exception e) {
			logger.error(e, e);
			uploadSuccess = false;
			getUploadErrors().add("User creation service call failed due to :: " + e.getMessage() + " row number :: " + i);
			return "admuur";
		}
		FacesMessage successMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "File has been uploaded successfully ", "File has been uploaded successfully");
		FacesContext.getCurrentInstance().addMessage(null, successMessage);
		return "admuur";
	}

	public String saveUser() {
		try {
			List<String> errors = validateUser();
			if (available) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Screen Name Not Available", "Screen Name Not Available");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
			if (availableID) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ID Number exists already", "ID Number exists already");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
			if (availableEmpID) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Employee ID exists already", "Employee ID exists already");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
			if (availableEmail) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email exists already", "Email exists already");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
			if (errors.size() > 0) {
				for (String error : errors) {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
				return "";
			}
			WebClient addUserClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/add");
			UserMessage bean = new UserMessage();
			bean.setBio(userBean.getBio());
			bean.setContact(userBean.getContact());
			bean.seteMail(userBean.geteMail());
			bean.setFbHandle(userBean.getFbHandle());
			bean.setfName(userBean.getfName());
			bean.setIdNum(userBean.getIdNum());
			bean.setIsActive(userBean.getIsActive());
			bean.setlName(userBean.getlName());
			bean.setmName(userBean.getmName());
			bean.setPwd(userBean.getPwd());
			bean.setScName(userBean.getScName());
			bean.setSkills(userBean.getSkills());
			bean.setTwHandle(userBean.getTwHandle());
			bean.setIsActive(true);
			bean.setuId(COUNTER.getNextId("IpUser"));
			bean.setLastLoginDt(new Date());
			bean.setSecQ(Integer.valueOf(secQ));
			bean.setSecA(userBean.getSecA());
			bean.setEmployeeId(userBean.getEmployeeId());
			bean.setGroupId(userBean.getGroupId());
			bean.setuCrtdDate(new Date());
			ResponseMessage response = addUserClient.accept(MediaType.APPLICATION_JSON).post(bean, ResponseMessage.class);
			addUserClient.close();
			if (response.getStatusCode() == 0) {
				if (image != null) {
					WebClient createBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/create");
					AttachmentMessage message = new AttachmentMessage();
					message.setBlobContentType(contentType);
					message.setBlobEntityId(bean.getuId());
					message.setBlobEntityTblNm("ip_user");
					message.setBlobName(fileName);
					message.setBlobId(COUNTER.getNextId("IpBlob"));
					Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(message);
					createBlobClient.close();
					if (crtRes.getStatus() == 200) {
						WebClient client = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/upload/" + message.getBlobId().toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
						client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
						client.header("Accept", MediaType.APPLICATION_JSON);
						Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(message.getBlobId().toString(), image.getStream(), new ContentDisposition("attachment;;filename=sample.png")));
						if (docRes.getStatus() != 200) {
							FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Profile Image Not Uploaded", "Profile Image Not Uploaded");
							FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
						}
						client.close();
					} else {
						FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Profile Image Not Uploaded", "Profile Image Not Uploaded");
						FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
					}
				}
				FacesMessage successMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "User '" + userBean.getfName() + "' created successfully", "User '" + userBean.getfName() + "' created successfully");
				FacesContext.getCurrentInstance().addMessage(null, successMessage);
				viewUsers = RESTServiceHelper.fetchAllUsers();
				return "admuv";
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

	private List<String> validateUser() {
		ArrayList<String> ret = new ArrayList<String>();
		if (userBean.getfName() == null || userBean.getfName().length() == 0) {
			ret.add("FirstName is Mandatory");
		}
		if (userBean.getlName() == null || userBean.getlName().length() == 0) {
			ret.add("LastName is Mandatory");
		}
		if (userBean.getScName() == null || userBean.getScName().length() == 0) {
			ret.add("Screen Name is Mandatory");
		}
		if (userBean.getIdNum() == null || userBean.getIdNum() == 0) {
			ret.add("ID Number is Mandatory");
		}
		if (secQ == null || secQ.length() == 0) {
			ret.add("Security Question is Mandatory");
		}
		if (userBean.getSecA() == null || userBean.getSecA().length() == 0) {
			ret.add("Security Answer is Mandatory");
		}
		if (userBean.getPwd() == null || userBean.getPwd().length() == 0) {
			ret.add("Password is Mandatory");
		}
		if (userBean.getcPw() == null || userBean.getcPw().length() == 0) {
			ret.add("Confirm Password is Mandatory");
		}
		if (userBean.getGroupId() == null) {
			ret.add("Primary Group Id is Mandatory");
		}
		return ret;
	}

	private List<String> updateUserValidation() {
		ArrayList<String> ret = new ArrayList<String>();
		if (userBean.getIsActive()) {
			if (userBean.getfName() == null || userBean.getfName().length() == 0) {
				ret.add("FirstName is Mandatory");
			}
			if (userBean.getlName() == null || userBean.getlName().length() == 0) {
				ret.add("LastName is Mandatory");
			}
			if (userBean.getScName() == null || userBean.getScName().length() == 0) {
				ret.add("Screen Name is Mandatory");
			}
			if (userBean.getIdNum() == null || userBean.getIdNum() == 0) {
				ret.add("ID Number is Mandatory");
			}
			if (userBean.getGroupId() == null) {
				ret.add("Primary Group Id is Mandatory");
			}
		}
		return ret;
	}

	private List<String> validateFunction() {
		ArrayList<String> ret = new ArrayList<String>();
		if (functionBean.getFuncName() == null || functionBean.getFuncName().length() == 0) {
			ret.add("Function Name is Mandatory");
		}
		return ret;
	}

	private List<String> validateGroup() {
		ArrayList<String> ret = new ArrayList<String>();
		if (groupBean.getgName() == null || groupBean.getgName().length() == 0) {
			ret.add("Group Name is Mandatory");
		}
		return ret;
	}

	protected ArrayList<String> validateLogin() {
		ArrayList<String> ret = new ArrayList<String>();
		if (userBean.getScName() == null || userBean.getScName().length() == 0) {
			ret.add("User Name is Mandatory");
		}
		if (userBean.getPwd() == null || userBean.getPwd().length() == 0) {
			ret.add("Password is Mandatory");
		}
		return ret;
	}

	private ArrayList<String> validateRPwd() {
		ArrayList<String> ret = new ArrayList<String>();
		if (userBean.getScName() == null || userBean.getScName().length() == 0) {
			ret.add("User Name is Mandatory");
		}
		if (userBean.getPwd() == null || userBean.getPwd().length() == 0) {
			ret.add("Password is Mandatory");
		}
		if (userBean.getcPw() == null || userBean.getcPw().length() == 0) {
			ret.add("Confirm Password is Mandatory");
		}
		return ret;
	}

	protected class ProxyWebRequestor extends DefaultWebRequestor {
		protected HttpURLConnection openConnection(URL url) throws IOException {
			if (Boolean.valueOf(BUNDLE.getString("is.proxy"))) {
				InetSocketAddress proxyLocation = new InetSocketAddress(BUNDLE.getString("proxy.host"), Integer.parseInt(BUNDLE.getString("proxy.port")));
				Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyLocation);
				return (HttpURLConnection) url.openConnection(proxy);
			} else {
				return (HttpURLConnection) url.openConnection();
			}
		}
	}

	public String updateUser() {
		try {
			List<String> errors = updateUserValidation();
			if (errors.size() > 0) {
				for (String error : errors) {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
				return "";
			}
			WebClient updateUserClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/modify");
			UserMessage bean = new UserMessage();
			bean.setBio(userBean.getBio());
			bean.setContact(userBean.getContact());
			bean.seteMail(userBean.geteMail());
			bean.setFbHandle(userBean.getFbHandle());
			bean.setfName(userBean.getfName());
			bean.setIdNum(userBean.getIdNum());
			bean.setIsActive(userBean.getIsActive());
			bean.setlName(userBean.getlName());
			bean.setmName(userBean.getmName());
			bean.setPwd(userBean.getPwd());
			bean.setScName(userBean.getScName());
			bean.setSkills(userBean.getSkills());
			bean.setTwHandle(userBean.getTwHandle());
			bean.setIsActive(userBean.getIsActive());
			bean.setuId(userBean.getuId());
			bean.setLastLoginDt(userBean.getLastLoginDt());
			bean.setEmployeeId(userBean.getEmployeeId());
			bean.setGroupId(userBean.getGroupId());
			bean.setuCrtdDate(userBean.getuCrtdDate());
			ResponseMessage response = updateUserClient.accept(MediaType.APPLICATION_JSON).put(bean, ResponseMessage.class);
			updateUserClient.close();
			if (resetPasswd) {
				User user = UserLocalServiceUtil.getUserByScreenName(10154, userBean.getScName());
				user = UserLocalServiceUtil.updatePassword(user.getUserId(), "Passw123", "Passw123", false);
				if (Validator.isNotNull(user)) {
					WebClient pwdUpdateClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/rpw/");
					ResponseMessage pwdUpdateResponse = pwdUpdateClient.accept(MediaType.APPLICATION_JSON).put(new String[] { userBean.getScName(), Base64.encodeBase64URLSafeString(DigestUtils.md5("Passw123".getBytes())), "Passw123" }, ResponseMessage.class);
					pwdUpdateClient.close();
					if (pwdUpdateResponse.getStatusCode() == 0) {
						FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Password Reset Successful", "Password Reset Successful");
						FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
					} else {
						FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, pwdUpdateResponse.getStatusCode() + " :: " + pwdUpdateResponse.getStatusDesc(), pwdUpdateResponse.getStatusCode() + " :: " + pwdUpdateResponse.getStatusDesc());
						FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
					}
				} else {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Liferay User password Reset Error", "Liferay User password Reset Error");
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
				resetPasswd = false;
			}
			if (resetSec) {
				WebClient loginClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/rsec");
				loginClient.accept(MediaType.APPLICATION_JSON).put(new String[] { userBean.getScName(), "4", "Johannesburg" }, ResponseMessage.class);
				loginClient.close();
				resetSec = false;
			}
			if (response.getStatusCode() == 0) {
				FacesMessage successMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "User '" + userBean.getfName() + "' updated successfully", "User '" + userBean.getfName() + "' updated successfully");
				FacesContext.getCurrentInstance().addMessage(null, successMessage);
				if (activeUsersView) {
					viewUsers = RESTServiceHelper.fetchActiveUsers();
					return "admuav";
				} else if (inactiveUsersView) {
					viewUsers = RESTServiceHelper.fetchInActiveUsers();
					return "admuiv";
				} else {
					viewUsers = RESTServiceHelper.fetchAllUsers();
					return "admuv";

				}

			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to update User", "Unable to update User");
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

	public void updateUserProfile() {
		try {
			List<String> errors = updateUserValidation();
			if (errors.size() > 0) {
				for (String error : errors) {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
			} else {
				WebClient updateUserClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/modify");
				UserMessage bean = new UserMessage();
				bean.setBio(userBean.getBio());
				bean.setContact(userBean.getContact());
				bean.seteMail(userBean.geteMail());
				bean.setFbHandle(userBean.getFbHandle());
				bean.setfName(userBean.getfName());
				bean.setIdNum(userBean.getIdNum());
				bean.setIsActive(userBean.getIsActive());
				bean.setlName(userBean.getlName());
				bean.setmName(userBean.getmName());
				bean.setPwd(userBean.getPwd());
				bean.setScName(userBean.getScName());
				bean.setSkills(userBean.getSkills());
				bean.setTwHandle(userBean.getTwHandle());
				bean.setIsActive(userBean.getIsActive());
				bean.setuId(userBean.getuId());
				bean.setLastLoginDt(userBean.getLastLoginDt());
				bean.setEmployeeId(userBean.getEmployeeId());
				bean.setGroupId(userBean.getGroupId());
				bean.setuCrtdDate(userBean.getuCrtdDate());
				ResponseMessage response = updateUserClient.accept(MediaType.APPLICATION_JSON).put(bean, ResponseMessage.class);
				updateUserClient.close();
				if (resetPasswd) {
					WebClient loginClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/rpw/");
					loginClient.accept(MediaType.APPLICATION_JSON).put(new String[] { userBean.getScName(), Base64.encodeBase64URLSafeString(DigestUtils.md5("Passw123".getBytes())), "Passw123" }, ResponseMessage.class);
					loginClient.close();
					resetPasswd = false;
				}
				if (resetSec) {
					WebClient loginClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/rsec");
					loginClient.accept(MediaType.APPLICATION_JSON).put(new String[] { userBean.getScName(), "4", "Johannesburg" }, ResponseMessage.class);
					loginClient.close();
					resetSec = false;
				}
				if (response.getStatusCode() == 0) {
					FacesMessage successMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "User '" + userBean.getScName() + "' profile updated successfully", "User '" + userBean.getScName() + "' profile updated successfully");
					FacesContext.getCurrentInstance().addMessage(null, successMessage);
					// redirectHome();
				} else {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to update User", "Unable to update User");
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
			}
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform update request", "System error occurred, cannot perform update request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public String saveGroup() {
		try {
			List<String> errors = validateGroup();
			if (errors.size() > 0) {
				for (String error : errors) {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
				return "";
			}
			if (verifyGroup(groupBean.getgName())) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot Create Duplicate Group entries", "Cannot Create Duplicate Group entries");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
			WebClient addGroupClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/group/add");
			GroupMessage groupMessage = new GroupMessage();
			groupMessage.setAdmUserId(groupBean.getSelAdmUser());
			groupMessage.setGeMail(groupBean.getGeMail());
			groupMessage.setgId(COUNTER.getNextId("IpGroup"));
			groupMessage.setgName(groupBean.getgName());
			groupMessage.setUserIdList(toLongArray(selUserId));
			groupMessage.setIsActive(true);
			groupMessage.setpGrpId(groupBean.getSelPGrp());
			groupMessage.setCrtdDate(new Date());
			ResponseMessage response = addGroupClient.accept(MediaType.APPLICATION_JSON).post(groupMessage, ResponseMessage.class);
			addGroupClient.close();
			if (response.getStatusCode() == 0) {
				if (grpImage != null) {
					WebClient createBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/create");
					AttachmentMessage message = new AttachmentMessage();
					message.setBlobContentType(grpContentType);
					message.setBlobEntityId(groupBean.getgId());
					message.setBlobEntityTblNm("ip_group");
					message.setBlobName(grpFileName);
					message.setBlobId(COUNTER.getNextId("IpBlob"));
					Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(message);
					if (crtRes.getStatus() == 200) {
						WebClient client = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/upload/" + message.getBlobId().toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
						client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
						client.header("Accept", MediaType.APPLICATION_JSON);
						Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(message.getBlobId().toString(), grpImage.getStream(), new ContentDisposition("attachment;filename=" + grpFileName)));
						if (docRes.getStatus() != 200) {
							FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Group Image Not Uploaded", "Group Image Not Uploaded");
							FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
						}
						client.close();
						// showViewUsers();
					} else {
						FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Group Image Not Uploaded", "Group Image Not Uploaded");
						FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
					}
					createBlobClient.close();
				}
				viewGroups = RESTServiceHelper.fetchAllGroups();
				return "admgv";
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

	public String saveFunction() {
		try {
			List<String> errors = validateFunction();
			if (errors.size() > 0) {
				for (String error : errors) {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
				return "";
			}
			if (verifyFunction(functionBean.getFuncName())) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot Create Duplicate Function entries", "Cannot Create Duplicate Function entries");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
			WebClient addFunctionClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/func/add");
			FunctionMessage functionMessage = new FunctionMessage();
			functionMessage.setFuncId(COUNTER.getNextId("IpFunction"));
			functionMessage.setFuncName(functionBean.getFuncName());
			functionMessage.setGroupIdList(toLongArray(selGrpId));
			functionMessage.setCrtdBy(userId);
			ResponseMessage response = addFunctionClient.accept(MediaType.APPLICATION_JSON).post(functionMessage, ResponseMessage.class);
			addFunctionClient.close();
			if (response.getStatusCode() == 0) {
				functions = RESTServiceHelper.fetchAllFunctions();
				return "admfv";
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

	public String updateGroup() {
		try {
			List<String> errors = validateGroup();
			if (errors.size() > 0) {
				for (String error : errors) {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
				return "";
			}
			WebClient updateGroupClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/group/modify");
			GroupMessage groupMessage = new GroupMessage();
			groupMessage.setAdmUserId(groupBean.getSelAdmUser());
			groupMessage.setGeMail(groupBean.getGeMail());
			groupMessage.setgId(groupBean.getgId());
			groupMessage.setgName(groupBean.getgName());
			groupMessage.setUserIdList(toLongArray(selUserId));
			groupMessage.setIsActive(groupBean.getIsActive());
			groupMessage.setpGrpId(groupBean.getSelPGrp());
			groupMessage.setCrtdDate(groupBean.getgCrtdDate());
			ResponseMessage response = updateGroupClient.accept(MediaType.APPLICATION_JSON).put(groupMessage, ResponseMessage.class);
			updateGroupClient.close();
			if (response.getStatusCode() == 0) {
				if (grpImage != null) {
					WebClient getBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/getId/" + groupBean.getgId() + "/ip_group");
					Long blobId = getBlobClient.accept(MediaType.APPLICATION_JSON).get(Long.class);
					if (blobId == -999) {
						WebClient createBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/create");
						AttachmentMessage message = new AttachmentMessage();
						message.setBlobContentType(grpContentType);
						message.setBlobEntityId(groupBean.getgId());
						message.setBlobEntityTblNm("ip_group");
						message.setBlobName(grpFileName);
						message.setBlobId(COUNTER.getNextId("IpBlob"));
						Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(message);
						if (crtRes.getStatus() == 200) {
							WebClient client = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/upload/" + message.getBlobId().toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
							client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
							client.header("Accept", MediaType.APPLICATION_JSON);
							Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(message.getBlobId().toString(), grpImage.getStream(), new ContentDisposition("attachment;filename=" + grpFileName)));
							client.close();
							if (docRes.getStatus() != 200) {
								FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Group Image Not Uploaded", "Group Image Not Uploaded");
								FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
							}
						} else {
							FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Group Image Not Uploaded", "Group Image Not Uploaded");
							FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
						}
						createBlobClient.close();
					} else {
						WebClient updateBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/update");
						AttachmentMessage attach = new AttachmentMessage();
						attach.setBlobContentType(grpContentType);
						attach.setBlobEntityId(groupBean.getgId());
						attach.setBlobEntityTblNm("ip_group");
						attach.setBlobName(grpFileName);
						attach.setBlobId(blobId);
						Response updRes = updateBlobClient.accept(MediaType.APPLICATION_JSON).put(attach);
						updateBlobClient.close();
						if (updRes.getStatus() == 200) {
							WebClient client = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/upload/" + blobId.toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
							client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
							client.header("Accept", MediaType.APPLICATION_JSON);
							Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(blobId.toString(), grpImage.getStream(), new ContentDisposition("attachment;filename=" + grpFileName)));
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
				grpImage = null;
				viewGroups = RESTServiceHelper.fetchAllGroups();
				return "admgv";
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

	public String updateFunction() {
		try {
			List<String> errors = validateFunction();
			if (errors.size() > 0) {
				for (String error : errors) {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
				return "";
			}
			WebClient updateFunctionClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/func/modify");
			FunctionMessage functionMessage = new FunctionMessage();
			functionMessage.setFuncId(functionBean.getFuncId());
			functionMessage.setFuncName(functionBean.getFuncName());
			functionMessage.setCrtdBy(userId);
			functionMessage.setGroupIdList(toLongArray(selGrpId));
			ResponseMessage response = updateFunctionClient.accept(MediaType.APPLICATION_JSON).put(functionMessage, ResponseMessage.class);
			updateFunctionClient.close();
			if (response.getStatusCode() == 0) {
				functions = RESTServiceHelper.fetchAllFunctions();
				return "admfv";
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

	public void updateImage() {
		try {
			WebClient getBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/getId/" + userId + "/ip_user");
			Long blobId = getBlobClient.accept(MediaType.APPLICATION_JSON).get(Long.class);
			if (blobId == -999) {
				WebClient createBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/create");
				AttachmentMessage message = new AttachmentMessage();
				message.setBlobContentType(uploadContentType);
				message.setBlobEntityId(userId);
				message.setBlobEntityTblNm("ip_user");
				message.setBlobName(uploadFileName);
				message.setBlobId(COUNTER.getNextId("IpBlob"));
				blobId = message.getBlobId();
				Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(message);
				if (crtRes.getStatus() == 200) {
					WebClient client = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/upload/" + blobId.toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
					client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
					client.header("Accept", MediaType.APPLICATION_JSON);
					Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(blobId.toString(), uploadImage.getStream(), new ContentDisposition("attachment;filename=" + fileName)));
					if (docRes.getStatus() != 200) {
						FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Profile Image Not Uploaded", "Profile Image Not Uploaded");
						FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
					} else {
						FacesMessage successMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Profile Image Successfully Uploaded", "Profile Image Successfully Uploaded");
						FacesContext.getCurrentInstance().addMessage(null, successMsg);
						this.image = uploadImage;
					}
				} else {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Profile Image Not Uploaded", "Profile Image Not Uploaded");
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
			} else {
				WebClient updateBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/update");
				AttachmentMessage message = new AttachmentMessage();
				message.setBlobContentType(uploadContentType);
				message.setBlobEntityId(userId);
				message.setBlobEntityTblNm("ip_user");
				message.setBlobName(uploadFileName);
				message.setBlobId(blobId);
				blobId = message.getBlobId();
				Response updRes = updateBlobClient.accept(MediaType.APPLICATION_JSON).put(message);
				if (updRes.getStatus() == 200) {
					WebClient client = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/upload/" + blobId.toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
					client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
					client.header("Accept", MediaType.APPLICATION_JSON);
					Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(blobId.toString(), uploadImage.getStream(), new ContentDisposition("attachment;filename=" + uploadFileName)));
					if (docRes.getStatus() != 200) {
						FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Profile Image Not Uploaded", "Profile Image Not Uploaded");
						FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
					} else {
						FacesMessage successMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Profile Image Successfully Uploaded", "Profile Image Successfully Uploaded");
						FacesContext.getCurrentInstance().addMessage(null, successMsg);
						this.image = uploadImage;
					}
					client.close();
				} else {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Profile Image Not Uploaded", "Profile Image Not Uploaded");
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
			}
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to upload attachment. Please update later", "Unable to upload attachment. Please update later");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void fileUploadHandle(FileUploadEvent fue) {
		try {
			UploadedFile file = fue.getFile();
			this.image = new DefaultStreamedContent(file.getInputstream());
			this.fileName = file.getFileName();
			this.contentType = file.getContentType();
			if (file != null) {
				FacesMessage successMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Profile Image has been uploaded successfully", "Profile Image has been uploaded successfully");
				FacesContext.getCurrentInstance().addMessage(null, successMessage);
			}
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void fileGroupUploadHandle(FileUploadEvent fue) {
		try {
			UploadedFile file = fue.getFile();
			this.grpImage = new DefaultStreamedContent(file.getInputstream());
			this.grpFileName = file.getFileName();
			this.grpContentType = file.getContentType();
			if (file != null) {
				FacesMessage successMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Group Image has been uploaded successfully", "Group Image has been uploaded successfully");
				FacesContext.getCurrentInstance().addMessage(null, successMessage);
			}
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void fileImageUploadHandle(FileUploadEvent fue) {
		try {
			UploadedFile file = fue.getFile();
			this.uploadImage = new DefaultStreamedContent(file.getInputstream());
			this.uploadFileName = file.getFileName();
			this.uploadContentType = file.getContentType();
			enableUpload = true;
			if (file != null) {
				FacesMessage successMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "File has been uploaded successfully", "File has been uploaded successfully");
				FacesContext.getCurrentInstance().addMessage(null, successMessage);
			}
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			enableUpload = false;
		}
	}

	public void userUploadHandle(FileUploadEvent fue) {
		try {
			UploadedFile file = fue.getFile();
			this.uploadUser = new DefaultStreamedContent(file.getInputstream());
			this.uploadFileName = file.getFileName();
			this.uploadContentType = file.getContentType();
			enableUpload = true;
			if (file != null) {
				FacesMessage successMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "File has been uploaded successfully", "File has been uploaded successfully");
				FacesContext.getCurrentInstance().addMessage(null, successMessage);
			}
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			enableUpload = false;
		}
	}

	private boolean verifyGroup(String grpName) {
		boolean ret = false;
		for (GroupBean grpBean : viewGroups) {
			if (grpBean.getgName().equalsIgnoreCase(grpName)) {
				ret = true;
				break;
			}
		}
		return ret;
	}

	public String showMetaData() {
		this.showAddPanel = false;
		this.showModPanel = true;
		selId = bean.getId().toString();
		selVal = bean.getDesc();
		return "admmm";
	}

	public String showMetaDataAdd() {
		bean = new MetaDataBean();
		selId = String.valueOf((COUNTER.getNextId(table).intValue()));
		selVal = "";
		this.showAddBtn = false;
		this.showAddPanel = true;
		this.showModPanel = false;
		return "";
	}

	public String updateMetaData() {
		this.showAddPanel = false;
		this.showModPanel = false;
		this.showAddBtn = true;
		if (verifyMetadata(selVal)) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot Create duplicate values", "Cannot Create duplicate values");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			this.showAddPanel = false;
			this.showModPanel = true;
			return "";
		}
		WebClient mDataClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ms/modify");
		MetaDataMessage message = new MetaDataMessage();
		message.setDesc(selVal);
		message.setId(Integer.valueOf(selId));
		message.setTable(table);
		ResponseMessage response = mDataClient.accept(MediaType.APPLICATION_JSON).put(message, ResponseMessage.class);
		mDataClient.close();
		if (response.getStatusCode() == 0) {
			beans = RESTServiceHelper.fetchAllMetadata(table);
			return "";
		} else {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String deleteMetaData() {
		this.showAddPanel = false;
		this.showModPanel = false;
		this.showAddBtn = true;
		WebClient mDataClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ms/delete");
		MetaDataMessage message = new MetaDataMessage();
		message.setDesc(selVal);
		message.setId(Integer.valueOf(selId));
		message.setTable(table);
		ResponseMessage response = mDataClient.accept(MediaType.APPLICATION_JSON).put(message, ResponseMessage.class);
		mDataClient.close();
		if (response.getStatusCode() == 0) {
			beans = RESTServiceHelper.fetchAllMetadata(table);
			return "admmm";
		} else {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String addMetaData() {
		this.showAddPanel = false;
		this.showModPanel = false;
		this.showAddBtn = true;
		if (verifyMetadata(selVal)) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot Create duplicate values", "Cannot Create duplicate values");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			this.showAddPanel = true;
			this.showModPanel = false;
			return "";
		}
		WebClient mDataClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ms/add");
		MetaDataMessage message = new MetaDataMessage();
		message.setDesc(selVal);
		message.setId(Integer.valueOf(selId));
		message.setTable(table);
		ResponseMessage response = mDataClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		mDataClient.close();
		if (response.getStatusCode() == 0) {
			beans = RESTServiceHelper.fetchAllMetadata(table);
			return "";
		} else {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public void changeMetaData() {
		if (table.equalsIgnoreCase("")) {
			beans = new ArrayList<MetaDataBean>();
			this.showAddPanel = false;
			this.showModPanel = false;
			this.showAddBtn = false;
		} else {
			beans = RESTServiceHelper.fetchAllMetadata(table);
			this.showAddPanel = false;
			this.showModPanel = false;
			this.showAddBtn = true;
		}
	}

	public HashMap<String, String> getMetaList() {
		if (metaList == null) {
			metaList = new HashMap<String, String>();
			metaList.put("Security Questions", "IpSecqList");
			metaList.put("Category", "IpCategory");
			metaList.put("Rewards Category", "IpRewardsCat");
		}
		return metaList;
	}

	private boolean verifyMetadata(String desc) {
		boolean ret = false;
		for (MetaDataBean bean : RESTServiceHelper.fetchAllMetadata(table)) {
			if (bean.getDesc().equalsIgnoreCase(desc)) {
				ret = true;
				break;
			}
		}
		return ret;
	}

	private boolean verifyFunction(String funcName) {
		boolean ret = false;
		return ret;
	}

	public String saveNews() {
		try {
			WebClient addNewsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ns/news/add");
			NewsMessage message = new NewsMessage();
			message.setnTitle(newsBean.getnTitle());
			message.setContent(newsBean.getnContent());
			message.setnId(COUNTER.getNextId("IpNews"));
			message.setStartDate(newsBean.getStartDate());
			message.setEndDate(newsBean.getEndDate());
			message.setNewsCrtdDt(new Date());
			ResponseMessage response = addNewsClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
			addNewsClient.close();
			if (response.getStatusCode() == 0) {
				if (image != null) {
					logger.info("Before adding file content details");
					WebClient createBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/create");
					AttachmentMessage attach = new AttachmentMessage();
					attach.setBlobContentType(contentType);
					attach.setBlobEntityId(message.getnId());
					attach.setBlobEntityTblNm("ip_news");
					attach.setBlobName(fileName);
					attach.setBlobId(COUNTER.getNextId("IpBlob"));
					Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(attach);
					logger.info("Reponse code of file attachment - " + crtRes.getStatus());
					if (crtRes.getStatus() == 200) {
						WebClient client = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/upload/" + attach.getBlobId().toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
						client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
						client.header("Accept", MediaType.APPLICATION_JSON);
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
				viewNewsBeans = RESTServiceHelper.fetchAllNews();
				return "admvn";
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
		} catch (Exception e) {
			logger.error(e, e);
			logger.error("Error in creating news data : " + e.getMessage());

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform news create request", "System error occurred, cannot perform news create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String updateNews() {
		try {
			WebClient updateNewsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ns/news/modify");
			NewsMessage message = new NewsMessage();
			message.setnId(newsBean.getnId());
			message.setnTitle(newsBean.getnTitle());
			message.setContent(newsBean.getnContent());
			message.setStartDate(newsBean.getStartDate());
			message.setEndDate(newsBean.getEndDate());
			message.setNewsCrtdDt(newsBean.getNewsCrtdDt());
			ResponseMessage response = updateNewsClient.accept(MediaType.APPLICATION_JSON).put(message, ResponseMessage.class);
			updateNewsClient.close();
			if (response.getStatusCode() == 0) {
				if (image != null) {
					WebClient getBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/getId/" + newsBean.getnId() + "/ip_news");
					Long blobId = getBlobClient.accept(MediaType.APPLICATION_JSON).get(Long.class);
					if (blobId == -999) {
						WebClient createBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/create");
						AttachmentMessage attach = new AttachmentMessage();
						attach.setBlobContentType(contentType);
						attach.setBlobEntityId(newsBean.getnId());
						attach.setBlobEntityTblNm("ip_news");
						attach.setBlobName(fileName);
						attach.setBlobId(COUNTER.getNextId("IpBlob"));
						Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(attach);
						if (crtRes.getStatus() == 200) {
							WebClient client = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/upload/" + attach.getBlobId().toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
							client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
							client.header("Accept", MediaType.APPLICATION_JSON);
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
						WebClient updateBlobClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/update");
						AttachmentMessage attach = new AttachmentMessage();
						attach.setBlobContentType(contentType);
						attach.setBlobEntityId(newsBean.getnId());
						attach.setBlobEntityTblNm("ip_news");
						attach.setBlobName(fileName);
						attach.setBlobId(blobId);
						Response updRes = updateBlobClient.accept(MediaType.APPLICATION_JSON).put(attach);
						updateBlobClient.close();
						if (updRes.getStatus() == 200) {
							WebClient client = WebClient.create("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/upload/" + blobId.toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
							client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
							client.header("Accept", MediaType.APPLICATION_JSON);
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
				viewNewsBeans = RESTServiceHelper.fetchAllNews();
				return "admvn";
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
		} catch (Exception e) {
			logger.error(e, e);

			logger.error("Error in updating news data : " + e.getMessage());
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform update request", "System error occurred, cannot perform update request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String saveNotification() {
		try {
			WebClient addClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/nos/notif/add");
			NotificationMessage notifMessage = new NotificationMessage();
			notifMessage.setNotifCrtdDate(new Date());
			notifMessage.setNotifId(java.util.UUID.randomUUID().toString());
			notifMessage.setNotifStatus("n");
			notifMessage.setNotifSubject(notificationBean.getNotifSubject());
			notifMessage.setNotifBody(notificationBean.getNotifBody());
			if (uploadContent != null) {
				notifMessage.setNotifAttach(IOUtils.toString(uploadContent.getStream()));
			}
			notifMessage.setGroupIdList(toLongArray(selGrpId));
			ResponseMessage response = addClient.accept(MediaType.APPLICATION_JSON).post(notifMessage, ResponseMessage.class);
			addClient.close();
			if (response.getStatusCode() == 0) {
				uploadContent = null;
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notification Successfully generated", "Notification Successfully generated");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusDesc(), response.getStatusDesc());
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return "";
			}
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create Idea request", "System error occurred, cannot perform create Idea request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	private String[] toStringArray(List<UserBean> users) {
		String[] ret = new String[users.size()];
		int i = 0;
		for (UserBean user : users) {
			ret[i] = user.getuId() + "";
			i++;
		}
		return ret;
	}

	protected Long[] toLongArray(String[] val) {
		Long[] ret = new Long[val.length];
		for (int i = 0; i < val.length; i++)
			ret[i] = Long.valueOf(val[i]);
		return ret;
	}

	public UserBean getUserBean() {
		if (userBean == null)
			userBean = new UserBean();
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public GroupBean getGroupBean() {
		if (groupBean == null)
			groupBean = new GroupBean();
		return groupBean;
	}

	public void setGroupBean(GroupBean groupBean) {
		this.groupBean = groupBean;
	}

	public List<GroupBean> getpGrps() {
		if (pGrps == null)
			pGrps = new ArrayList<GroupBean>();
		return pGrps;
	}

	public void setpGrps(List<GroupBean> pGrps) {
		this.pGrps = pGrps;
	}

	public List<UserBean> getAdmUsers() {
		if (admUsers == null)
			admUsers = new ArrayList<UserBean>();
		return admUsers;
	}

	public void setAdmUsers(List<UserBean> admUsers) {
		this.admUsers = admUsers;
	}

	public List<UserBean> getViewUsers() {
		if (viewUsers == null)
			viewUsers = new ArrayList<UserBean>();
		return viewUsers;
	}

	public void setViewUsers(List<UserBean> viewUsers) {
		this.viewUsers = viewUsers;
	}

	public List<GroupBean> getViewGroups() {
		if (viewGroups == null)
			viewGroups = new ArrayList<GroupBean>();
		return viewGroups;
	}

	public void setViewGroups(List<GroupBean> viewGroups) {
		this.viewGroups = viewGroups;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public FunctionBean getFunctionBean() {
		if (functionBean == null)
			functionBean = new FunctionBean();
		return functionBean;
	}

	public void setFunctionBean(FunctionBean functionBean) {
		this.functionBean = functionBean;
	}

	public List<FunctionBean> getFunctions() {
		if (functions == null)
			functions = new ArrayList<FunctionBean>();
		return functions;
	}

	public void setFunctions(List<FunctionBean> functions) {
		this.functions = functions;
	}

	public String getSecA() {
		if (secA == null)
			secA = "";
		return secA;
	}

	public void setSecA(String secA) {
		this.secA = secA;
	}

	public StreamedContent getImage() {
		return image;
	}

	public void setImage(StreamedContent image) {
		this.image = image;
	}

	public String getUploadSrc() {
		if (uploadSrc == null)
			uploadSrc = "";
		return uploadSrc;
	}

	public void setUploadSrc(String uploadSrc) {
		this.uploadSrc = uploadSrc;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public boolean isShowDef() {
		return showDef;
	}

	public void setShowDef(boolean showDef) {
		this.showDef = showDef;
	}

	public boolean isEnableUpload() {
		return enableUpload;
	}

	public void setEnableUpload(boolean enableUpload) {
		this.enableUpload = enableUpload;
	}

	public String getSecQ() {
		if (secQ == null)
			secQ = "";
		return secQ;
	}

	public void setSecQ(String secQ) {
		this.secQ = secQ;
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

	public String getLoggedScrName() {
		if (loggedScrName == null)
			loggedScrName = "";
		return loggedScrName;
	}

	public void setLoggedScrName(String loggedScrName) {
		this.loggedScrName = loggedScrName;
	}

	public StreamedContent getUploadImage() {
		return uploadImage;
	}

	public String getUploadFileName() {
		if (uploadFileName == null)
			uploadFileName = "";
		return uploadFileName;
	}

	public String getUploadContentType() {
		if (uploadContentType == null)
			uploadContentType = "";
		return uploadContentType;
	}

	public void setUploadImage(StreamedContent uploadImage) {
		this.uploadImage = uploadImage;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public List<MetaDataBean> getSecqList() {
		secqList = RESTServiceHelper.fetchAllSecQ();
		return secqList;
	}

	public void setSecqList(List<MetaDataBean> secqList) {
		this.secqList = secqList;
	}

	public boolean isAvailableID() {
		return availableID;
	}

	public void setAvailableID(boolean availableID) {
		this.availableID = availableID;
	}

	public boolean isAvailableEmail() {
		return availableEmail;
	}

	public void setAvailableEmail(boolean availableEmail) {
		this.availableEmail = availableEmail;
	}

	public boolean isAvailableEmpID() {
		return availableEmpID;
	}

	public void setAvailableEmpID(boolean availableEmpID) {
		this.availableEmpID = availableEmpID;
	}

	public StreamedContent getUploadUser() {
		return uploadUser;
	}

	public void setUploadUser(StreamedContent uploadUser) {
		this.uploadUser = uploadUser;
	}

	public StreamedContent getGrpImage() {
		return grpImage;
	}

	public void setGrpImage(StreamedContent grpImage) {
		this.grpImage = grpImage;
	}

	public String getGrpFileName() {
		if (grpFileName == null)
			grpFileName = "";
		return grpFileName;
	}

	public void setGrpFileName(String grpFileName) {
		this.grpFileName = grpFileName;
	}

	public String getGrpContentType() {
		if (grpContentType == null)
			grpContentType = "";
		return grpContentType;
	}

	public void setGrpContentType(String grpContentType) {
		this.grpContentType = grpContentType;
	}

	public String getHierarchy() {
		if (hierarchy == null)
			hierarchy = "";
		return hierarchy;
	}

	public void setHierarchy(String hierarchy) {
		this.hierarchy = hierarchy;
	}

	public ArrayList<String> getUploadErrors() {
		if (uploadErrors == null)
			uploadErrors = new ArrayList<String>();
		return uploadErrors;
	}

	public void setUploadErrors(ArrayList<String> uploadErrors) {
		this.uploadErrors = uploadErrors;
	}

	public boolean isResetPasswd() {
		return resetPasswd;
	}

	public void setResetPasswd(boolean resetPasswd) {
		this.resetPasswd = resetPasswd;
	}

	public boolean isResetSec() {
		return resetSec;
	}

	public void setResetSec(boolean resetSec) {
		this.resetSec = resetSec;
	}

	public boolean isFileAvail() {
		return fileAvail;
	}

	public void setFileAvail(boolean fileAvail) {
		this.fileAvail = fileAvail;
	}

	public StreamedContent getFileContent() {
		return fileContent;
	}

	public void setFileContent(StreamedContent fileContent) {
		this.fileContent = fileContent;
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

	public String getEntity() {
		if (entity == null)
			entity = "";
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public List<AllocationBean> getAllocs() {
		if (allocs == null)
			allocs = new ArrayList<AllocationBean>();
		return allocs;
	}

	public void setAllocs(List<AllocationBean> allocs) {
		this.allocs = allocs;
	}

	public AllocationBean getAllocationBean() {
		if (allocationBean == null)
			allocationBean = new AllocationBean();
		return allocationBean;
	}

	public void setAllocationBean(AllocationBean allocationBean) {
		this.allocationBean = allocationBean;
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

	public String getComments() {
		if (comments == null)
			comments = "";
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public boolean isShowAddPanel() {
		return showAddPanel;
	}

	public void setShowAddPanel(boolean showAddPanel) {
		this.showAddPanel = showAddPanel;
	}

	public boolean isShowModPanel() {
		return showModPanel;
	}

	public void setShowModPanel(boolean showModPanel) {
		this.showModPanel = showModPanel;
	}

	public boolean isShowAddBtn() {
		return showAddBtn;
	}

	public void setShowAddBtn(boolean showAddBtn) {
		this.showAddBtn = showAddBtn;
	}

	public List<MetaDataBean> getStatusList() {
		if (statusList == null)
			statusList = new ArrayList<MetaDataBean>();
		return statusList;
	}

	public void setStatusList(List<MetaDataBean> statusList) {
		this.statusList = statusList;
	}

	public List<MetaDataBean> getDisStatusList() {
		if (disStatusList == null)
			disStatusList = new ArrayList<MetaDataBean>();
		return disStatusList;
	}

	public void setDisStatusList(List<MetaDataBean> disStatusList) {
		this.disStatusList = disStatusList;
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

	public Long getAllocUserId() {
		return allocUserId;
	}

	public void setAllocUserId(Long allocUserId) {
		this.allocUserId = allocUserId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<MetaDataBean> getBeans() {
		if (beans == null)
			beans = new ArrayList<MetaDataBean>();
		return beans;
	}

	public void setBeans(List<MetaDataBean> beans) {
		this.beans = beans;
	}

	public MetaDataBean getBean() {
		if (bean == null)
			bean = new MetaDataBean();
		return bean;
	}

	public void setBean(MetaDataBean bean) {
		this.bean = bean;
	}

	public String getTable() {
		if (table == null)
			table = "";
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getSelId() {
		if (selId == null)
			selId = "";
		return selId;
	}

	public void setSelId(String selId) {
		this.selId = selId;
	}

	public String getSelVal() {
		if (selVal == null)
			selVal = "";
		return selVal;
	}

	public void setSelVal(String selVal) {
		this.selVal = selVal;
	}

	public void setMetaList(HashMap<String, String> metaList) {
		this.metaList = metaList;
	}

	public NewsBean getNewsBean() {
		if (newsBean == null)
			newsBean = new NewsBean();
		return newsBean;
	}

	public void setNewsBean(NewsBean newsBean) {
		this.newsBean = newsBean;
	}

	public List<NewsBean> getViewNewsBeans() {
		try {
			PortletRequest request = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			User user = (User) request.getAttribute(WebKeys.USER);
			WebClient client = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/verify/" + user.getScreenName());
			UserMessage message = client.accept(MediaType.APPLICATION_JSON).get(UserMessage.class);
			userId = message.getuId();
			viewNewsBeans = RESTServiceHelper.fetchAllNews();
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
		return viewNewsBeans;
	}

	public void setViewNewsBeans(List<NewsBean> viewNewsBeans) {
		this.viewNewsBeans = viewNewsBeans;
	}

	public List<NotificationBean> getViewNotifications() {
		if (viewNotifications == null)
			viewNotifications = new ArrayList<NotificationBean>();
		return viewNotifications;
	}

	public void setViewNotifications(List<NotificationBean> viewNotifications) {
		this.viewNotifications = viewNotifications;
	}

	public StreamedContent getUploadContent() {
		return uploadContent;
	}

	public void setUploadContent(StreamedContent uploadContent) {
		this.uploadContent = uploadContent;
	}

	public NotificationBean getNotificationBean() {
		if (notificationBean == null)
			notificationBean = new NotificationBean();
		return notificationBean;
	}

	public void setNotificationBean(NotificationBean notificationBean) {
		this.notificationBean = notificationBean;
	}

	public String[] getSelGrpId() {
		if (selGrpId == null)
			selGrpId = new String[] {};
		return selGrpId;
	}

	public void setSelGrpId(String[] selGrpId) {
		this.selGrpId = selGrpId;
	}

	public String[] getSelUserId() {
		if (selUserId == null)
			selUserId = new String[] {};
		return selUserId;
	}

	public void setSelUserId(String[] selUserId) {
		this.selUserId = selUserId;
	}

	public Date getLastLoginDt() {
		if (lastLoginDt == null)
			lastLoginDt = new Date();
		return lastLoginDt;
	}

	public void setLastLoginDt(Date lastLoginDt) {
		this.lastLoginDt = lastLoginDt;
	}

	public UserStatisticsBean getStatsBean() {
		WebClient statsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/stats/" + userId);
		UserStatisticsMessage statsMsg = statsClient.accept(MediaType.APPLICATION_JSON).get(UserStatisticsMessage.class);
		statsBean = new UserStatisticsBean();
		statsBean.setChallengesCount(statsMsg.getChallengesCount());
		statsBean.setIdeasCount(statsMsg.getIdeasCount());
		statsBean.setSolutionsCount(statsMsg.getSolutionsCount());
		statsBean.setTotalCount(statsMsg.getTotalCount());
		statsBean.setUserId(statsMsg.getUserId());
		statsBean.setWhishListCount(statsMsg.getWhishListCount());
		statsBean.setPointsCount(statsMsg.getPointsCount());
		statsClient.close();
		logger.info(loggedScrName + " :: " + imgPath + " :: " + imgAvail + " :: " + hierarchy);
		return statsBean;
	}

	public void setStatsBean(UserStatisticsBean statsBean) {
		this.statsBean = statsBean;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public boolean isImgAvail() {
		return imgAvail;
	}

	public void setImgAvail(boolean imgAvail) {
		this.imgAvail = imgAvail;
	}

	public List<UserStatisticsBean> getViewStatsBean() {
		try {
			WebClient statsListClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/stats/topList");
			Collection<? extends UserStatisticsMessage> messages = new ArrayList<UserStatisticsMessage>(statsListClient.accept(MediaType.APPLICATION_JSON).getCollection(UserStatisticsMessage.class));
			statsListClient.close();
			viewStatsBean = new ArrayList<UserStatisticsBean>();
			for (UserStatisticsMessage userStats : messages) {
				UserStatisticsBean stats = new UserStatisticsBean();
				stats.setChallengesCount(userStats.getChallengesCount());
				stats.setIdeasCount(userStats.getIdeasCount());
				stats.setSolutionsCount(userStats.getSolutionsCount());
				stats.setTotalCount(userStats.getTotalCount());
				stats.setUserId(userStats.getUserId());
				stats.setWhishListCount(userStats.getWhishListCount());
				stats.setImgPath(userStats.getImgPath());
				stats.setImgAvail(userStats.isImgAvail());
				stats.setGrpImgAvail(userStats.isGrpImgAvail());
				stats.setGrpImgPath(userStats.getGrpImgPath());
				stats.setUserPriGrpName(userStats.getUserPriGrpName());
				stats.setUserScrNm(userStats.getUserScrNm());
				viewStatsBean.add(stats);
			}
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
		return viewStatsBean;
	}

	public void setViewStatsBean(List<UserStatisticsBean> viewStatsBean) {
		this.viewStatsBean = viewStatsBean;
	}

	public String getGrpImgPath() {
		return grpImgPath;
	}

	public void setGrpImgPath(String grpImgPath) {
		this.grpImgPath = grpImgPath;
	}

	public boolean isGrpImgAvail() {
		return grpImgAvail;
	}

	public void setGrpImgAvail(boolean grpImgAvail) {
		this.grpImgAvail = grpImgAvail;
	}

	public boolean isShowEditProfile() {
		return showEditProfile;
	}

	public void setShowEditProfile(boolean showEditProfile) {
		this.showEditProfile = showEditProfile;
	}

	public boolean isShowChangePwd() {
		return showChangePwd;
	}

	public void setShowChangePwd(boolean showChangePwd) {
		this.showChangePwd = showChangePwd;
	}

	public boolean isShowChangeSecQ() {
		return showChangeSecQ;
	}

	public void setShowChangeSecQ(boolean showChangeSecQ) {
		this.showChangeSecQ = showChangeSecQ;
	}

	public List<ClaimBean> getViewClaimBeans() {
		return viewClaimBeans;
	}

	public void setViewClaimBeans(List<ClaimBean> viewClaimBeans) {
		this.viewClaimBeans = viewClaimBeans;
	}

	public List<RewardsBean> getViewRewardsBeans() {
		return viewRewardsBeans;
	}

	public void setViewRewardsBeans(List<RewardsBean> viewRewardsBeans) {
		this.viewRewardsBeans = viewRewardsBeans;
	}

	public List<ListSelectorBean> getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(List<ListSelectorBean> claimStatus) {
		this.claimStatus = claimStatus;
	}

	public ClaimBean getClaimBean() {
		if (claimBean == null)
			claimBean = new ClaimBean();
		return claimBean;
	}

	public void setClaimBean(ClaimBean claimBean) {
		this.claimBean = claimBean;
	}

	public boolean isActiveUsersView() {
		return activeUsersView;
	}

	public void setActiveUsersView(boolean activeUsersView) {
		this.activeUsersView = activeUsersView;
	}

	public boolean isInactiveUsersView() {
		return inactiveUsersView;
	}

	public void setInactiveUsersView(boolean inactiveUsersView) {
		this.inactiveUsersView = inactiveUsersView;
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

	public String getCurEmailId() {
		return curEmailId;
	}

	public void setCurEmailId(String curEmailId) {
		this.curEmailId = curEmailId;
	}

	public String getCurTitle() {
		return curTitle;
	}

	public void setCurTitle(String curTitle) {
		this.curTitle = curTitle;
	}

	public String getCurEmpId() {
		return curEmpId;
	}

	public void setCurEmpId(String curEmpId) {
		this.curEmpId = curEmpId;
	}

	public Long getCurId() {
		return curId;
	}

	public void setCurId(Long curId) {
		this.curId = curId;
	}

	public boolean isShowAllocIdeaReview() {
		return showAllocIdeaReview;
	}

	public void setShowAllocIdeaReview(boolean showAllocIdeaReview) {
		this.showAllocIdeaReview = showAllocIdeaReview;
	}

	public boolean isUploadSuccess() {
		return uploadSuccess;
	}

	public void setUploadSuccess(boolean uploadSuccess) {
		this.uploadSuccess = uploadSuccess;
	}

	public List<String> getSelUsers() {
		return selUsers;
	}

	public void setSelUsers(List<String> selUsers) {
		this.selUsers = selUsers;
	}
}
