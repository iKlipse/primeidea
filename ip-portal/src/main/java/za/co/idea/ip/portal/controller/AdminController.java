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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import za.co.idea.ip.portal.bean.AllocationBean;
import za.co.idea.ip.portal.bean.FunctionBean;
import za.co.idea.ip.portal.bean.GroupBean;
import za.co.idea.ip.portal.bean.MetaDataBean;
import za.co.idea.ip.portal.bean.NewsBean;
import za.co.idea.ip.portal.bean.NotificationBean;
import za.co.idea.ip.portal.bean.UserBean;
import za.co.idea.ip.portal.util.IdNumberGen;
import za.co.idea.ip.portal.util.RESTServiceHelper;
import za.co.idea.ip.ws.bean.AllocationMessage;
import za.co.idea.ip.ws.bean.AttachmentMessage;
import za.co.idea.ip.ws.bean.FunctionMessage;
import za.co.idea.ip.ws.bean.GroupMessage;
import za.co.idea.ip.ws.bean.MetaDataMessage;
import za.co.idea.ip.ws.bean.NewsMessage;
import za.co.idea.ip.ws.bean.NotificationMessage;
import za.co.idea.ip.ws.bean.PointMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;
import za.co.idea.ip.ws.bean.UserMessage;
import za.co.idea.ip.ws.util.CustomObjectMapper;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.restfb.DefaultWebRequestor;

@ManagedBean(name = "adminController")
@SessionScoped
public class AdminController implements Serializable {

	private static final long serialVersionUID = 4122782126915399730L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-portal");
	private static final Logger logger = Logger.getLogger(AdminController.class);

	private UserBean userBean;
	private GroupBean groupBean;
	private FunctionBean functionBean;
	private List<FunctionBean> functions;
	private List<GroupBean> pGrps;
	private List<UserBean> admUsers;
	private List<UserBean> viewUsers;
	private List<GroupBean> viewGroups;
	private List<MetaDataBean> secqList;
	private DualListModel<UserBean> userTwinSelect;
	private DualListModel<GroupBean> groupTwinSelect;
	private boolean available;
	private boolean availableID;
	private boolean availableEmail;
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
	private String[] selGrpId;
	private String[] selUserId;

	private WebClient createCustomClient(String url) {
		WebClient client = WebClient.create(url, Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
		client.header("Content-Type", "application/json");
		client.header("Accept", "application/json");
		return client;
	}

	public void initializePage() {
		try {
			PortletRequest request = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			User user = (User) request.getAttribute(WebKeys.USER);
			WebClient client = RESTServiceHelper.createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/verify/" + user.getScreenName());
			UserMessage message = client.accept(MediaType.APPLICATION_JSON).get(UserMessage.class);
			userId = message.getuId();
			viewUsers = fetchAllUsers();
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
			logger.debug("Control handled in createCustomClient()");
			newsBean = new NewsBean();
			return "admcn";
		} catch (Exception e) {
			logger.error(e, e);

			logger.error("Error while displaying show create news form: " + e.getMessage());
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform news create request", "System error occurred, cannot perform news create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showViewNews() {
		try {
			logger.debug("Control handled in createCustomClient()");
			viewNewsBeans = fetchAllNews();
			return "admvn";
		} catch (Exception e) {
			logger.error(e, e);

			logger.error("Error while displaying show view news form: " + e.getMessage());
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform news view request", "System error occurred, cannot perform news view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showEditNews() {
		try {
			logger.debug("Control handled in showEditNews() method");
			logger.info("Sending request to NewsService");
			WebClient getBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getId/" + newsBean.getnId() + "/ip_news");
			Long blobId = getBlobClient.accept(MediaType.APPLICATION_JSON).get(Long.class);
			getBlobClient.close();
			logger.info("After gettin response from NewsService");
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
					fileContent = new DefaultStreamedContent(attachment.getDataHandler().getInputStream(), blobType, blobName);
				} else {
					fileAvail = true;
					fileContent = null;
				}
			} else {
				fileAvail = true;
				fileContent = null;
			}
			return "admen";
		} catch (Exception e) {
			logger.error(e, e);
			logger.error("Error while displaying edit news form: " + e.getMessage());
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			fileAvail = true;
			fileContent = null;
			return "";
		}
	}

	public String showSummaryNews() {
		try {
			logger.debug("In showSummaryNews method");
			viewNewsBeans = fetchAllNews();
			logger.info("News details after fetching data from fetchALLNews(): " + viewNewsBeans);
			return "admsn";
		} catch (Exception e) {
			logger.error(e, e);
			logger.error("Error while displaying news summary details: " + e.getMessage());
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform news view request", "System error occurred, cannot perform news view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showAllocatePoints() {
		try {
			entity = "";
			admUsers = fetchAllUsers();
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
			pGrps = fetchAllGroups();
			groupTwinSelect = new DualListModel<GroupBean>(fetchAllGroups(), new ArrayList<GroupBean>());
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
			functions = fetchAllFunctions();
			admUsers = fetchAllUsers();
			pGrps = fetchAllGroups();
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
			pGrps = viewGroups = fetchAllGroups();
			admUsers = fetchAllUsers();
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
			viewGroups = pGrps = fetchAllGroups();
			admUsers = fetchAllUsersSortByPG();
			userTwinSelect = new DualListModel<UserBean>(fetchAllUsersSortByPG(), fetchAdminUser());
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
			viewGroups = pGrps = fetchAllGroups();
			admUsers = fetchAllUsersSortByPG();
			userTwinSelect = initializeSelectedUsers(admUsers);
			functions = fetchAllFunctionsByGroup();
			try {
				WebClient getBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getId/" + groupBean.getgId() + "/ip_group");
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
						groupBean.setFileName(attachment.getContentDisposition().toString().replace("attachment;filename=", ""));
						fileContent = new DefaultStreamedContent(attachment.getDataHandler().getInputStream(), blobType, blobName);
					} else {
						fileAvail = true;
						fileContent = null;
					}
				} else {
					fileAvail = true;
					fileContent = null;
				}
				return "admge";
			} catch (Exception e) {
				logger.error(e, e);

				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				fileAvail = true;
				fileContent = null;
				return "";
			}
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform updated groups view request", "System error occurred, cannot perform updated groups view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showSummaryGroup() {
		try {
			viewGroups = pGrps = fetchAllGroups();
			admUsers = fetchAllUsersSortByPG();
			userTwinSelect = initializeSelectedUsers(admUsers);
			functions = fetchAllFunctionsByGroup();
			try {
				WebClient getBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getId/" + groupBean.getgId() + "/ip_group");
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
						groupBean.setFileName(attachment.getContentDisposition().toString().replace("attachment;filename=", ""));
						fileContent = new DefaultStreamedContent(attachment.getDataHandler().getInputStream(), blobType, blobName);
					} else {
						fileAvail = true;
						fileContent = null;
					}
				} else {
					fileAvail = true;
					fileContent = null;
				}
				return "admgs";
			} catch (Exception e) {
				logger.error(e, e);

				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				fileAvail = true;
				fileContent = null;
				return "";
			}
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
			pGrps = fetchAllGroups();
			groupTwinSelect = new DualListModel<GroupBean>(pGrps, new ArrayList<GroupBean>());
			return "admcno";
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create request", "System error occurred, cannot perform create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showViewUsers() {
		try {
			viewUsers = fetchAllUsers();
			return "admuv";
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform Users View request", "System error occurred, cannot perform Users View request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showUploadUsers() {
		return "admuu";
	}

	public String showRPw() {
		secqList = fetchAllSecQ();
		return "";
	}

	public String showEditUser() {
		try {
			secqList = fetchAllSecQ();
			viewGroups = fetchAllGroups();
			resetPasswd = false;
			resetSec = false;
			return "admue";
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform updated user view request", "System error occurred, cannot perform updated user view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public String showCreateUser() {
		try {
			userBean = new UserBean();
			secqList = fetchAllSecQ();
			viewGroups = fetchAllGroups();
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
			pGrps = fetchAllGroups();
			groupTwinSelect = initializeSelectedGroups(pGrps);
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

	public void login() {
		ArrayList<String> errors = validateLogin();
		if (errors != null && errors.size() > 0) {
			for (String error : errors) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			}
		} else {
			WebClient loginClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/login/" + userBean.getScName() + "/" + Base64.encodeBase64URLSafeString(DigestUtils.md5(userBean.getPwd().getBytes())));
			UserMessage userMessage = loginClient.accept(MediaType.APPLICATION_JSON).get(UserMessage.class);
			loginClient.close();
			if (userMessage != null && userMessage.getuId() != null && userMessage.getuId() == -999l) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "User Profile De-Activated. Please contact Admin.", "User Profile De-Activated. Please contact Admin.");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			} else if (userMessage == null || userMessage.getuId() == null || userMessage.getScName() == null) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid User Name Password", "Invalid User Name Password");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			} else {
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
				bean.setIsActive(userMessage.getIsActive());
				bean.setuId(userMessage.getuId());
				bean.setLastLoginDt(userMessage.getLastLoginDt());
				bean.setGroupId(userMessage.getGroupId());
				bean.setPriGroupName(userMessage.getPriGroupName());
				loggedScrName = userMessage.getScName();
				secqList = fetchAllSecQ();
				if (userMessage.getGroupId() != null) {
					WebClient hClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/group/hierarchy/" + userMessage.getGroupId());
					hierarchy = hClient.accept(MediaType.APPLICATION_JSON).get(String.class);
					hClient.close();
				} else {
					hierarchy = "assign primary group";
				}
				/*
				 * AccessController controller = new
				 * AccessController(bean.getuId());
				 * FacesContext.getCurrentInstance
				 * ().getExternalContext().getSessionMap().put("accessBean",
				 * controller);
				 */
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", bean);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userId", bean.getuId());
				try {
					logger.info("Before image displaying");
					WebClient docIdClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getId/" + userMessage.getuId() + "/ip_user");
					Long blobId = docIdClient.accept(MediaType.APPLICATION_JSON).get(Long.class);
					docIdClient.close();
					logger.info("After getting response from service /ds/doc/getId: " + userMessage.getuId() + " --blobId---" + blobId);
					if (blobId != -999l) {
						WebClient getBlobNameClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getName/" + blobId);
						String blobName = getBlobNameClient.accept(MediaType.APPLICATION_JSON).get(String.class);
						getBlobNameClient.close();
						logger.info("blob name: " + blobName);
						WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/download/" + blobId + "/" + blobName, Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
						client.header("Content-Type", "application/json");
						client.header("Accept", MediaType.MULTIPART_FORM_DATA);
						Attachment attachment = client.accept(MediaType.MULTIPART_FORM_DATA).get(Attachment.class);
						logger.info("After getting attachment");
						if (attachment != null) {
							logger.info("Before getting blob content type");
							WebClient getBlobTypeClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getContentType/" + blobId);
							String blobType = getBlobTypeClient.accept(MediaType.APPLICATION_JSON).get(String.class);
							getBlobTypeClient.close();
							logger.info("blob type: " + blobType);
							this.image = new DefaultStreamedContent(attachment.getDataHandler().getInputStream(), blobType, blobName);
							logger.info("After setting streamed content to image");
							show = true;
							showDef = false;
						} else {
							show = false;
							showDef = true;
						}
						client.close();
					} else {
						show = false;
						showDef = true;
					}
				} catch (Exception e) {
					logger.error(e, e);

					logger.error("Error while login : " + e.getMessage());
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform Login request", "System error occurred, cannot perform Login request");
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
					show = false;
					showDef = true;
				}
			}
		}
	}

	public void verifyLogin() {
		WebClient loginClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/verify/" + userBean.getScName());
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
			if (Base64.encodeBase64URLSafeString(DigestUtils.md5(secA.getBytes())).equalsIgnoreCase(userBean.getSecA())) {
				WebClient loginClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/rpw/");
				ResponseMessage response = loginClient.accept(MediaType.APPLICATION_JSON).put(new String[] { userBean.getScName(), Base64.encodeBase64URLSafeString(DigestUtils.md5(userBean.getPwd().getBytes())) }, ResponseMessage.class);
				loginClient.close();
				if (response.getStatusCode() == 0) {
					FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
					userBean = new UserBean();
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Password Reset Successful", "Password Reset Successful");
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				} else {
					FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusCode() + " :: " + response.getStatusDesc(), response.getStatusCode() + " :: " + response.getStatusDesc());
					FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				}
			} else {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Security Answer", "Invalid Security Answer");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			}
		}

	}

	public void resetSecurity() {
		if (secQ == null || secQ.length() == 0) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Security Question is Mandatory", "Security Question is Mandatory");
			FacesContext.getCurrentInstance().addMessage("txtSCName", exceptionMessage);
			RequestContext.getCurrentInstance().openDialog("dlgSecUpdate");
		} else if (secA == null || secA.length() == 0) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Security Answer is Mandatory", "Security Answer is Mandatory");
			FacesContext.getCurrentInstance().addMessage("txtSCName", exceptionMessage);
			RequestContext.getCurrentInstance().openDialog("dlgSecUpdate");
		} else {
			WebClient loginClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/rsec");
			ResponseMessage response = loginClient.accept(MediaType.APPLICATION_JSON).put(new String[] { userBean.getScName(), secQ.toString(), secA }, ResponseMessage.class);
			loginClient.close();
			this.userBean.setSecA(secA);
			this.userBean.setSecQ(Integer.valueOf(secQ));
			if (response.getStatusCode() != 0) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, response.getStatusCode() + " :: " + response.getStatusDesc(), response.getStatusCode() + " :: " + response.getStatusDesc());
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				RequestContext.getCurrentInstance().closeDialog("dlgSecUpdate");
			}
		}
	}

	public void logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}

	public String savePoints() {
		try {
			WebClient savePointsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/rs/points/add");
			PointMessage message = new PointMessage();
			message.setAllocId(selAllocId);
			message.setPointId(COUNTER.getNextId("IpPoints"));
			message.setPointValue(pointVal);
			message.setUserId(allocUserId);
			message.setComments(comments);
			ResponseMessage res = savePointsClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
			if (res.getStatusCode() == 0) {
				selAllocId = null;
				pointVal = null;
				allocUserId = null;
				entity = null;
				comments = null;
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucessfully added points", "Sucessfully added points");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
				return redirectMain();
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
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform save allocation request", "System error occurred, cannot perform save allocation request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
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

	public void setClaimStatusList(List<MetaDataBean> claimStatusList) {
		this.claimStatusList = claimStatusList;
	}

	public void checkAvailability() {
		if (userBean.getScName() == null || userBean.getScName().length() == 0) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Enter Screen Name to Check Availability", "Enter Screen Name to Check Availability");
			FacesContext.getCurrentInstance().addMessage("txtSCName", exceptionMessage);
		}
		WebClient checkAvailablityClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/check/screenName/" + userBean.getScName());
		Boolean avail = checkAvailablityClient.accept(MediaType.APPLICATION_JSON).get(Boolean.class);
		checkAvailablityClient.close();
		available = avail.booleanValue();
		if (available) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Screen Name Not Available", "Screen Name Not Available");
			FacesContext.getCurrentInstance().addMessage("txtSCName", exceptionMessage);
		} else {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Screen Name Available", "Screen Name Available");
			FacesContext.getCurrentInstance().addMessage("txtSCName", exceptionMessage);
		}
	}

	public void checkAvailabilityEmail() {
		if (userBean.geteMail() == null || userBean.geteMail().length() == 0) {

		} else {
			WebClient checkAvailablityClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/check/email/" + userBean.geteMail());
			Boolean availE = checkAvailablityClient.accept(MediaType.APPLICATION_JSON).get(Boolean.class);
			checkAvailablityClient.close();
			availableEmail = availE.booleanValue();
			if (availableEmail) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email already exists", "Email already exists");
				FacesContext.getCurrentInstance().addMessage("txtEMail", exceptionMessage);
			}
		}
	}

	public void checkAvailabilityIDNumber() {
		if (userBean.getIdNum() == null || userBean.getIdNum() == 0) {

		} else {
			WebClient checkAvailablityClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/check/idNumber/" + userBean.getIdNum());
			Boolean availID = checkAvailablityClient.accept(MediaType.APPLICATION_JSON).get(Boolean.class);
			checkAvailablityClient.close();
			availableID = availID.booleanValue();
			if (availableID) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ID Number already exists", "ID Number already exists");
				FacesContext.getCurrentInstance().addMessage("txtIdNum", exceptionMessage);
			}
		}
	}

	public void checkAvailabilityEmployeeID() {
		if (userBean.getEmployeeId() == null || userBean.getEmployeeId().length() == 0) {

		} else {
			WebClient checkAvailablityClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/check/employeeId/" + userBean.getEmployeeId());
			Boolean availEmpID = checkAvailablityClient.accept(MediaType.APPLICATION_JSON).get(Boolean.class);
			checkAvailablityClient.close();
			availableEmpID = availEmpID.booleanValue();
			if (availableEmpID) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Employee ID already exists", "Employee ID already exists");
				FacesContext.getCurrentInstance().addMessage("txtEmpId", exceptionMessage);
			}
		}
	}

	public String saveUploadUsers() {
		int i = 3;
		try {
			uploadErrors = null;
			Workbook workbook = null;
			if (uploadFileName.endsWith("xlsx"))
				workbook = new XSSFWorkbook(uploadUser.getStream());
			else if (uploadFileName.endsWith("xls"))
				workbook = new HSSFWorkbook(uploadUser.getStream());
			Sheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(i);
			Cell cell0 = row.getCell(0);
			while ((cell0 != null && cell0.getStringCellValue() != null) || cell0.getStringCellValue().length() != 0) {
				try {
					UserBean bean = new UserBean();
					bean.setContact("+27 " + row.getCell(5).getStringCellValue().substring(1));
					bean.seteMail(row.getCell(6).getStringCellValue());
					bean.setEmployeeId(row.getCell(0).getStringCellValue());
					bean.setIdNum(Long.valueOf(row.getCell(4).getStringCellValue()));
					bean.setIsActive(false);
					bean.setlName(row.getCell(1).getStringCellValue());
					bean.setfName(row.getCell(3).getStringCellValue());
					bean.setPwd("Passw123");
					bean.setSecQ(4);
					bean.setSecA("Johannesburg");
					bean.setScName(bean.getlName().toLowerCase() + row.getCell(2).getStringCellValue().toLowerCase());
					if (bean.geteMail() != null && bean.geteMail().length() != 0) {
						WebClient checkEAvailablityClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/check/email/" + bean.geteMail());
						Boolean availE = checkEAvailablityClient.accept(MediaType.APPLICATION_JSON).get(Boolean.class);
						checkEAvailablityClient.close();
						if (availE) {
							getUploadErrors().add("EMail Id already registered :: " + bean.geteMail() + " row number :: " + i);
							i++;
							row = sheet.getRow(i);
							cell0 = row.getCell(0);
							continue;
						}
					}
					if (bean.getIdNum() != null) {
						WebClient checkIAvailablityClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/check/idNumber/" + bean.getIdNum());
						Boolean availID = checkIAvailablityClient.accept(MediaType.APPLICATION_JSON).get(Boolean.class);
						checkIAvailablityClient.close();
						if (availID) {
							getUploadErrors().add("Id Number already registered :: " + bean.getIdNum() + " row number :: " + i);
							i++;
							row = sheet.getRow(i);
							cell0 = row.getCell(0);
							continue;
						}
					} else {
						getUploadErrors().add("Id Number cannot be empty row number :: " + i);
						i++;
						row = sheet.getRow(i);
						cell0 = row.getCell(0);
						continue;
					}
					if (bean.getEmployeeId() != null && bean.getEmployeeId().length() != 0) {
						WebClient checkEIAvailablityClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/check/employeeId/" + bean.getEmployeeId());
						Boolean availEmpID = checkEIAvailablityClient.accept(MediaType.APPLICATION_JSON).get(Boolean.class);
						checkEIAvailablityClient.close();
						if (availEmpID) {
							getUploadErrors().add("Employee Id already registered :: " + bean.getEmployeeId() + " row number :: " + i);
							i++;
							row = sheet.getRow(i);
							cell0 = row.getCell(0);
							continue;
						}
					}
					WebClient checkAvailablityClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/check/screenName/" + bean.getScName());
					Boolean avail = checkAvailablityClient.accept(MediaType.APPLICATION_JSON).get(Boolean.class);
					checkAvailablityClient.close();
					if (avail)
						bean.setScName(bean.getScName() + i);
					WebClient addUserClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/add");
					UserMessage msg = new UserMessage();
					msg.setContact(bean.getContact());
					msg.seteMail(bean.geteMail());
					msg.setfName(bean.getfName());
					msg.setIdNum(bean.getIdNum());
					msg.setlName(bean.getlName());
					msg.setPwd(bean.getPwd());
					msg.setScName(bean.getScName());
					msg.setIsActive(false);
					msg.setuId(COUNTER.getNextId("IpUser"));
					msg.setLastLoginDt(new Date());
					msg.setSecQ(bean.getSecQ());
					msg.setSecA(bean.getSecA());
					msg.setEmployeeId(bean.getEmployeeId());
					msg.setGroupId(14l);
					ResponseMessage response = addUserClient.accept(MediaType.APPLICATION_JSON).post(msg, ResponseMessage.class);
					addUserClient.close();
					if (response.getStatusCode() != 0) {
						getUploadErrors().add("User creation service call failed due to :: " + response.getStatusDesc() + " row number :: " + i);
						i++;
						row = sheet.getRow(i);
						cell0 = row.getCell(0);
						continue;
					}
				} catch (Exception e) {
					logger.error(e, e);
					getUploadErrors().add("User creation service call failed due to :: " + e.getMessage() + " row number :: " + i);
					i++;
					row = sheet.getRow(i);
					cell0 = row.getCell(0);
					continue;
				}
				i++;
				row = sheet.getRow(i);
				cell0 = row.getCell(0);
			}
		} catch (Exception e) {
			logger.error(e, e);

			getUploadErrors().add("User creation service call failed due to :: " + e.getMessage() + " row number :: " + i);
			return "admuur";
		}
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
			WebClient addUserClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/add");
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
			ResponseMessage response = addUserClient.accept(MediaType.APPLICATION_JSON).post(bean, ResponseMessage.class);
			addUserClient.close();
			if (response.getStatusCode() == 0) {
				if (image != null) {
					WebClient createBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/create");
					AttachmentMessage message = new AttachmentMessage();
					message.setBlobContentType(contentType);
					message.setBlobEntityId(bean.getuId());
					message.setBlobEntityTblNm("ip_user");
					message.setBlobName(fileName);
					message.setBlobId(COUNTER.getNextId("IpBlob"));
					Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(message);
					if (crtRes.getStatus() == 200) {
						WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/upload/" + message.getBlobId().toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
						client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
						client.header("Accept", "application/json");
						Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(message.getBlobId().toString(), image.getStream(), new ContentDisposition("attachment;;filename=sample.png")));
						if (docRes.getStatus() != 200) {
							FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Profile Image Not Uploaded", "Profile Image Not Uploaded");
							FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
						}
						client.close();
						viewUsers = fetchAllUsers();
						return "admuv";
					} else {
						FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Profile Image Not Uploaded", "Profile Image Not Uploaded");
						FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
					}
					createBlobClient.close();
				}
				FacesMessage successMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "User '" + userBean.getfName() + "' created successfully", "User '" + userBean.getfName() + "' created successfully");
				FacesContext.getCurrentInstance().addMessage(null, successMessage);
				viewUsers = fetchAllUsers();
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

	private ArrayList<String> validateLogin() {
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
			WebClient updateUserClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/modify");
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
			ResponseMessage response = updateUserClient.accept(MediaType.APPLICATION_JSON).put(bean, ResponseMessage.class);
			updateUserClient.close();
			if (resetPasswd) {
				WebClient loginClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/rpw/");
				loginClient.accept(MediaType.APPLICATION_JSON).put(new String[] { userBean.getScName(), Base64.encodeBase64URLSafeString(DigestUtils.md5("Passw123".getBytes())) }, ResponseMessage.class);
				loginClient.close();
				resetPasswd = false;
			}
			if (resetSec) {
				WebClient loginClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/rsec");
				loginClient.accept(MediaType.APPLICATION_JSON).put(new String[] { userBean.getScName(), "4", "Johannesburg" }, ResponseMessage.class);
				loginClient.close();
				resetSec = false;
			}
			if (response.getStatusCode() == 0) {
				FacesMessage successMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "User '" + userBean.getfName() + "' updated successfully", "User '" + userBean.getfName() + "' updated successfully");
				FacesContext.getCurrentInstance().addMessage(null, successMessage);
				viewUsers = fetchAllUsers();
				return "admvu";
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
				WebClient updateUserClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/modify");
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
				ResponseMessage response = updateUserClient.accept(MediaType.APPLICATION_JSON).put(bean, ResponseMessage.class);
				updateUserClient.close();
				if (resetPasswd) {
					WebClient loginClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/rpw/");
					loginClient.accept(MediaType.APPLICATION_JSON).put(new String[] { userBean.getScName(), Base64.encodeBase64URLSafeString(DigestUtils.md5("Passw123".getBytes())) }, ResponseMessage.class);
					loginClient.close();
					resetPasswd = false;
				}
				if (resetSec) {
					WebClient loginClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/rsec");
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
			WebClient addGroupClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/group/add");
			GroupMessage groupMessage = new GroupMessage();
			groupMessage.setAdmUserId(groupBean.getSelAdmUser());
			groupMessage.setGeMail(groupBean.getGeMail());
			groupMessage.setgId(COUNTER.getNextId("IpGroup"));
			groupMessage.setgName(groupBean.getgName());
			groupMessage.setUserIdList(toLongArray(selUserId));
			groupMessage.setIsActive(true);
			groupMessage.setpGrpId(groupBean.getSelPGrp());
			ResponseMessage response = addGroupClient.accept(MediaType.APPLICATION_JSON).post(groupMessage, ResponseMessage.class);
			addGroupClient.close();
			if (response.getStatusCode() == 0) {
				if (grpImage != null) {
					WebClient createBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/create");
					AttachmentMessage message = new AttachmentMessage();
					message.setBlobContentType(grpContentType);
					message.setBlobEntityId(groupBean.getgId());
					message.setBlobEntityTblNm("ip_group");
					message.setBlobName(grpFileName);
					message.setBlobId(COUNTER.getNextId("IpBlob"));
					Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(message);
					if (crtRes.getStatus() == 200) {
						WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/upload/" + message.getBlobId().toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
						client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
						client.header("Accept", "application/json");
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
				viewGroups = fetchAllGroups();
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
			WebClient addFunctionClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/func/add");
			FunctionMessage functionMessage = new FunctionMessage();
			functionMessage.setFuncId(COUNTER.getNextId("IpFunction"));
			functionMessage.setFuncName(functionBean.getFuncName());
			functionMessage.setGroupIdList(toLongArray(selGrpId));
			functionMessage.setCrtdBy(userId);
			ResponseMessage response = addFunctionClient.accept(MediaType.APPLICATION_JSON).post(functionMessage, ResponseMessage.class);
			addFunctionClient.close();
			if (response.getStatusCode() == 0) {
				functions = fetchAllFunctions();
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
			WebClient updateGroupClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/group/modify");
			GroupMessage groupMessage = new GroupMessage();
			groupMessage.setAdmUserId(groupBean.getSelAdmUser());
			groupMessage.setGeMail(groupBean.getGeMail());
			groupMessage.setgId(groupBean.getgId());
			groupMessage.setgName(groupBean.getgName());
			groupMessage.setUserIdList(toLongArray(selUserId));
			groupMessage.setIsActive(groupBean.getIsActive());
			groupMessage.setpGrpId(groupBean.getSelPGrp());
			ResponseMessage response = updateGroupClient.accept(MediaType.APPLICATION_JSON).put(groupMessage, ResponseMessage.class);
			updateGroupClient.close();
			if (response.getStatusCode() == 0) {
				if (grpImage != null) {
					WebClient getBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getId/" + groupBean.getgId() + "/ip_group");
					Long blobId = getBlobClient.accept(MediaType.APPLICATION_JSON).get(Long.class);
					if (blobId == -999) {
						WebClient createBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/create");
						AttachmentMessage message = new AttachmentMessage();
						message.setBlobContentType(grpContentType);
						message.setBlobEntityId(groupBean.getgId());
						message.setBlobEntityTblNm("ip_group");
						message.setBlobName(grpFileName);
						message.setBlobId(COUNTER.getNextId("IpBlob"));
						Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(message);
						if (crtRes.getStatus() == 200) {
							WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/upload/" + message.getBlobId().toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
							client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
							client.header("Accept", "application/json");
							Response docRes = client.accept(MediaType.APPLICATION_JSON).post(new Attachment(message.getBlobId().toString(), grpImage.getStream(), new ContentDisposition("attachment;filename=" + grpFileName)));
							if (docRes.getStatus() != 200) {
								FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Group Image Not Uploaded", "Group Image Not Uploaded");
								FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
							}
							client.close();
						} else {
							FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Group Image Not Uploaded", "Group Image Not Uploaded");
							FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
						}
						createBlobClient.close();
					} else {
						WebClient updateBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/update");
						AttachmentMessage attach = new AttachmentMessage();
						attach.setBlobContentType(grpContentType);
						attach.setBlobEntityId(groupBean.getgId());
						attach.setBlobEntityTblNm("ip_group");
						attach.setBlobName(grpFileName);
						attach.setBlobId(blobId);
						Response updRes = updateBlobClient.accept(MediaType.APPLICATION_JSON).put(attach);
						updateBlobClient.close();
						if (updRes.getStatus() == 200) {
							WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/upload/" + blobId.toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
							client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
							client.header("Accept", "application/json");
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
				viewGroups = fetchAllGroups();
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
			WebClient updateFunctionClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/func/modify");
			FunctionMessage functionMessage = new FunctionMessage();
			functionMessage.setFuncId(functionBean.getFuncId());
			functionMessage.setFuncName(functionBean.getFuncName());
			functionMessage.setCrtdBy(userId);
			functionMessage.setGroupIdList(toLongArray(selGrpId));
			ResponseMessage response = updateFunctionClient.accept(MediaType.APPLICATION_JSON).put(functionMessage, ResponseMessage.class);
			updateFunctionClient.close();
			if (response.getStatusCode() == 0) {
				functions = fetchAllFunctions();
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
			WebClient getBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getId/" + userId + "/ip_user");
			Long blobId = getBlobClient.accept(MediaType.APPLICATION_JSON).get(Long.class);
			if (blobId == -999) {
				WebClient createBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/create");
				AttachmentMessage message = new AttachmentMessage();
				message.setBlobContentType(uploadContentType);
				message.setBlobEntityId(userId);
				message.setBlobEntityTblNm("ip_user");
				message.setBlobName(uploadFileName);
				message.setBlobId(COUNTER.getNextId("IpBlob"));
				blobId = message.getBlobId();
				Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(message);
				if (crtRes.getStatus() == 200) {
					WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/upload/" + blobId.toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
					client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
					client.header("Accept", "application/json");
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
				WebClient updateBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/update");
				AttachmentMessage message = new AttachmentMessage();
				message.setBlobContentType(uploadContentType);
				message.setBlobEntityId(userId);
				message.setBlobEntityTblNm("ip_user");
				message.setBlobName(uploadFileName);
				message.setBlobId(blobId);
				blobId = message.getBlobId();
				Response updRes = updateBlobClient.accept(MediaType.APPLICATION_JSON).put(message);
				if (updRes.getStatus() == 200) {
					WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/upload/" + blobId.toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
					client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
					client.header("Accept", "application/json");
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

	// private Long[] getSelUserIds() {
	// Long[] ret = new Long[userTwinSelect.getTarget().size()];
	// int i = 0;
	// for (UserBean bean : userTwinSelect.getTarget()) {
	// ret[i] = bean.getuId();
	// i++;
	// }
	// return ret;
	// }

	// private Long[] getSelGroupIds() {
	// Long[] ret = new Long[groupTwinSelect.getTarget().size()];
	// int i = 0;
	// for (GroupBean bean : groupTwinSelect.getTarget()) {
	// ret[i] = bean.getgId();
	// i++;
	// }
	// return ret;
	// }
	//
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
			bean.setIsActive(userMessage.getIsActive());
			bean.setuId(userMessage.getuId());
			bean.setEmployeeId(userMessage.getEmployeeId());
			bean.setPriGroupName(userMessage.getPriGroupName());
			bean.setGroupId(userMessage.getGroupId());
			ret.add(bean);
		}
		return ret;
	}

	private List<UserBean> fetchAllUsersSortByPG() {
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
			bean.setIsActive(userMessage.getIsActive());
			bean.setuId(userMessage.getuId());
			bean.setEmployeeId(userMessage.getEmployeeId());
			bean.setPriGroupName(userMessage.getPriGroupName());
			ret.add(bean);
		}
		return ret;
	}

	private List<UserBean> fetchAdminUser() {
		List<UserBean> ret = new ArrayList<UserBean>();
		WebClient userByIdClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/get/" + 0l);
		UserMessage userMessage = userByIdClient.accept(MediaType.APPLICATION_JSON).get(UserMessage.class);
		userByIdClient.close();
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
		bean.setIsActive(userMessage.getIsActive());
		bean.setuId(userMessage.getuId());
		bean.setEmployeeId(userMessage.getEmployeeId());
		bean.setPriGroupName(userMessage.getPriGroupName());
		ret.add(bean);
		return ret;
	}

	private DualListModel<GroupBean> initializeSelectedGroups(List<GroupBean> grps) {
		List<Long> selGrps = functionBean.getGroupIdList();
		DualListModel<GroupBean> ret = new DualListModel<GroupBean>(new ArrayList<GroupBean>(), new ArrayList<GroupBean>());
		for (Long grpId : selGrps)
			ret.getTarget().add(getGroupById(grpId));
		for (GroupBean bean : grps)
			if (selGrps.contains(bean.getgId()))
				continue;
			else
				ret.getSource().add(bean);
		return ret;
	}

	private DualListModel<UserBean> initializeSelectedUsers(List<UserBean> users) {
		List<Long> selUsrs = groupBean.getUserIdList();
		DualListModel<UserBean> ret = new DualListModel<UserBean>(new ArrayList<UserBean>(), new ArrayList<UserBean>());
		for (Long usrId : selUsrs)
			ret.getTarget().add(getUserById(usrId));
		for (UserBean bean : users)
			if (selUsrs.contains(bean.getuId()))
				continue;
			else
				ret.getSource().add(bean);
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

	private List<FunctionBean> fetchAllFunctions() {
		List<FunctionBean> ret = new ArrayList<FunctionBean>();
		WebClient viewFunctionsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/func/list");
		Collection<? extends FunctionMessage> functions = new ArrayList<FunctionMessage>(viewFunctionsClient.accept(MediaType.APPLICATION_JSON).getCollection(FunctionMessage.class));
		viewFunctionsClient.close();
		for (FunctionMessage functionMessage : functions) {
			FunctionBean bean = new FunctionBean();
			bean.setFuncId(functionMessage.getFuncId());
			bean.setFuncName(functionMessage.getFuncName());
			bean.getGroupIdList().clear();
			for (Long id : functionMessage.getGroupIdList())
				if (id != null)
					bean.getGroupIdList().add(id);
			ret.add(bean);
		}
		return ret;
	}

	private List<FunctionBean> fetchAllFunctionsByGroup() {
		List<FunctionBean> ret = new ArrayList<FunctionBean>();
		WebClient viewFunctionsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/func/group/list/" + groupBean.getgId());
		Collection<? extends FunctionMessage> functions = new ArrayList<FunctionMessage>(viewFunctionsClient.accept(MediaType.APPLICATION_JSON).getCollection(FunctionMessage.class));
		viewFunctionsClient.close();
		for (FunctionMessage functionMessage : functions) {
			FunctionBean bean = new FunctionBean();
			bean.setFuncId(functionMessage.getFuncId());
			bean.setFuncName(functionMessage.getFuncName());
			bean.getGroupIdList().clear();
			for (Long id : functionMessage.getGroupIdList())
				if (id != null)
					bean.getGroupIdList().add(id);
			ret.add(bean);
		}
		return ret;
	}

	private List<NewsBean> fetchAllNews() {
		List<NewsBean> ret = new ArrayList<NewsBean>();
		try {
			logger.debug("Control handled in fetchAllNews method of NewsController ");
			WebClient fetchNewsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ns/news/list");
			Collection<? extends NewsMessage> news = new ArrayList<NewsMessage>(fetchNewsClient.accept(MediaType.APPLICATION_JSON).getCollection(NewsMessage.class));
			fetchNewsClient.close();
			logger.info("News data adding to List: ");
			for (NewsMessage message : news) {
				NewsBean bean = new NewsBean();
				bean.setnId(message.getnId());
				bean.setnContent(message.getContent());
				bean.setnEndDate(message.getEndDate());
				bean.setnStartDate(message.getStartDate());
				bean.setnTitle(message.getnTitle());
				bean.setNewsUrl(message.getNewsUrl());
				bean.setNwImgAvail(message.isNwImgAvail());
				ret.add(bean);
			}
		} catch (Exception e) {
			logger.error(e, e);
			logger.error("Error in fetching data : " + e.getMessage());
		}
		logger.info("News data displaying from List: " + ret);
		return ret;
	}

	protected List<NotificationBean> fetchAllNotifications() {
		List<NotificationBean> ret = new ArrayList<NotificationBean>();
		WebClient fetchNotifClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/nos/notif/list/" + userId);
		Collection<? extends NotificationMessage> notifications = new ArrayList<NotificationMessage>(fetchNotifClient.accept(MediaType.APPLICATION_JSON).getCollection(NotificationMessage.class));
		fetchNotifClient.close();
		for (NotificationMessage notificationMessage : notifications) {
			NotificationBean bean = new NotificationBean();
			bean.setNotifAttach(notificationMessage.getNotifAttach());
			bean.setNotifBody(notificationMessage.getNotifBody());
			bean.setNotifCrtdDate(notificationMessage.getNotifCrtdDate());
			bean.setNotifEntityId(notificationMessage.getNotifEntityId());
			bean.setNotifEntityTblName(notificationMessage.getNotifEntityTblName());
			bean.setNotifId(notificationMessage.getNotifId());
			bean.setNotifStatus(notificationMessage.getNotifStatus());
			bean.setNotifSubject(notificationMessage.getNotifSubject());
			bean.setGroupIdList(getIdsFromArray(notificationMessage.getGroupIdList()));
			ret.add(bean);
		}
		return ret;
	}

	private List<MetaDataBean> fetchAllSecQ() {
		List<MetaDataBean> ret = new ArrayList<MetaDataBean>();
		WebClient metaDataClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ms/list/IpSecqList");
		Collection<? extends MetaDataMessage> metaDatas = new ArrayList<MetaDataMessage>(metaDataClient.accept(MediaType.APPLICATION_JSON).getCollection(MetaDataMessage.class));
		metaDataClient.close();
		for (MetaDataMessage metaDataMessage : metaDatas) {
			MetaDataBean bean = new MetaDataBean();
			bean.setDesc(metaDataMessage.getDesc());
			bean.setId(metaDataMessage.getId());
			bean.setTable(metaDataMessage.getTable());
			ret.add(bean);
		}
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

	private UserBean getUserById(Long id) {
		UserBean bean = new UserBean();
		WebClient userByIdClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/get/" + id);
		UserMessage userMessage = userByIdClient.accept(MediaType.APPLICATION_JSON).get(UserMessage.class);
		userByIdClient.close();
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
		bean.setPriGroupName(userMessage.getPriGroupName());
		bean.setIsActive(true);
		bean.setuId(userMessage.getuId());
		return bean;
	}

	public void fileUploadHandle(FileUploadEvent fue) {
		try {
			UploadedFile file = fue.getFile();
			this.image = new DefaultStreamedContent(file.getInputstream());
			this.fileName = file.getFileName();
			this.contentType = file.getContentType();
			if (file != null) {
				FacesMessage successMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Profile Image has been uploaded successfully", "Profile Image has been uploaded successfully");
				FacesContext.getCurrentInstance().addMessage("fuCUpload", successMessage);
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
				FacesContext.getCurrentInstance().addMessage("fuCUpload", successMessage);
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
				FacesContext.getCurrentInstance().addMessage("fuCUpload", successMessage);
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
				FacesContext.getCurrentInstance().addMessage("fuCUpload", successMessage);
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
		WebClient mDataClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ms/modify");
		MetaDataMessage message = new MetaDataMessage();
		message.setDesc(selVal);
		message.setId(Integer.valueOf(selId));
		message.setTable(table);
		ResponseMessage response = mDataClient.accept(MediaType.APPLICATION_JSON).put(message, ResponseMessage.class);
		mDataClient.close();
		if (response.getStatusCode() == 0) {
			beans = fetchAllMetadata();
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
		WebClient mDataClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ms/delete");
		MetaDataMessage message = new MetaDataMessage();
		message.setDesc(selVal);
		message.setId(Integer.valueOf(selId));
		message.setTable(table);
		ResponseMessage response = mDataClient.accept(MediaType.APPLICATION_JSON).put(message, ResponseMessage.class);
		mDataClient.close();
		if (response.getStatusCode() == 0) {
			beans = fetchAllMetadata();
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
		WebClient mDataClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ms/add");
		MetaDataMessage message = new MetaDataMessage();
		message.setDesc(selVal);
		message.setId(Integer.valueOf(selId));
		message.setTable(table);
		ResponseMessage response = mDataClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		mDataClient.close();
		if (response.getStatusCode() == 0) {
			beans = fetchAllMetadata();
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
			beans = fetchAllMetadata();
			this.showAddPanel = false;
			this.showModPanel = false;
			this.showAddBtn = true;
		}
	}

	private List<MetaDataBean> fetchAllMetadata() {
		List<MetaDataBean> ret = new ArrayList<MetaDataBean>();
		WebClient mDataClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ms/list/" + table);
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
		for (MetaDataBean bean : fetchAllMetadata()) {
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
			logger.debug("Control handled in saveNews method");
			WebClient addNewsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ns/news/add");
			NewsMessage message = new NewsMessage();
			message.setnTitle(newsBean.getnTitle());
			message.setContent(newsBean.getnContent());
			message.setnId(COUNTER.getNextId("IpNews"));
			message.setStartDate(newsBean.getnStartDate());
			message.setEndDate(newsBean.getnEndDate());
			ResponseMessage response = addNewsClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
			addNewsClient.close();
			logger.info("dispalying response of save news action : " + response.getStatusCode());
			if (response.getStatusCode() == 0) {
				if (image != null) {
					logger.info("Before adding file content details");
					WebClient createBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/create");
					AttachmentMessage attach = new AttachmentMessage();
					attach.setBlobContentType(contentType);
					attach.setBlobEntityId(message.getnId());
					attach.setBlobEntityTblNm("ip_news");
					attach.setBlobName(fileName);
					attach.setBlobId(COUNTER.getNextId("IpBlob"));
					Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(attach);
					logger.info("Reponse code of file attachment - " + crtRes.getStatus());
					if (crtRes.getStatus() == 200) {
						WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/upload/" + attach.getBlobId().toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
						client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
						client.header("Accept", "application/json");
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
				return redirectMain();
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
			logger.debug("Control handled in updateNews()");
			WebClient updateNewsClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ns/news/modify");
			NewsMessage message = new NewsMessage();
			message.setnId(newsBean.getnId());
			message.setnTitle(newsBean.getnTitle());
			message.setContent(newsBean.getnContent());
			message.setStartDate(newsBean.getnStartDate());
			message.setEndDate(newsBean.getnEndDate());
			ResponseMessage response = updateNewsClient.accept(MediaType.APPLICATION_JSON).put(message, ResponseMessage.class);
			updateNewsClient.close();
			if (response.getStatusCode() == 0) {
				if (image != null) {
					WebClient getBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/getId/" + newsBean.getnId() + "/ip_news");
					Long blobId = getBlobClient.accept(MediaType.APPLICATION_JSON).get(Long.class);
					if (blobId == -999) {
						WebClient createBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/create");
						AttachmentMessage attach = new AttachmentMessage();
						attach.setBlobContentType(contentType);
						attach.setBlobEntityId(newsBean.getnId());
						attach.setBlobEntityTblNm("ip_news");
						attach.setBlobName(fileName);
						attach.setBlobId(COUNTER.getNextId("IpBlob"));
						Response crtRes = createBlobClient.accept(MediaType.APPLICATION_JSON).post(attach);
						if (crtRes.getStatus() == 200) {
							WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/upload/" + attach.getBlobId().toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
							client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
							client.header("Accept", "application/json");
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
						WebClient updateBlobClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/ds/doc/update");
						AttachmentMessage attach = new AttachmentMessage();
						attach.setBlobContentType(contentType);
						attach.setBlobEntityId(newsBean.getnId());
						attach.setBlobEntityTblNm("ip_news");
						attach.setBlobName(fileName);
						attach.setBlobId(blobId);
						Response updRes = updateBlobClient.accept(MediaType.APPLICATION_JSON).put(attach);
						updateBlobClient.close();
						if (updRes.getStatus() == 200) {
							WebClient client = WebClient.create("http://127.0.0.1:8080/ip-ws/ip/ds/doc/upload/" + blobId.toString(), Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
							client.header("Content-Type", MediaType.MULTIPART_FORM_DATA);
							client.header("Accept", "application/json");
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
				return redirectMain();
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
			WebClient addClient = createCustomClient("http://127.0.0.1:8080/ip-ws/ip/nos/notif/add");
			NotificationMessage notifMessage = new NotificationMessage();
			notifMessage.setNotifCrtdDate(new Date().toString());
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
				return redirectMain();
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

	private List<Long> getIdsFromArray(Long[] ae) {
		List<Long> ret = new ArrayList<Long>();
		if (ae != null)
			for (Long id : ae)
				ret.add(id);
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
		return secQ;
	}

	public void setSecQ(String secQ) {
		this.secQ = secQ;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getLoggedScrName() {
		return loggedScrName;
	}

	public void setLoggedScrName(String loggedScrName) {
		this.loggedScrName = loggedScrName;
	}

	public StreamedContent getUploadImage() {
		return uploadImage;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public String getUploadContentType() {
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
		return secqList;
	}

	public void setSecqList(List<MetaDataBean> secqList) {
		this.secqList = secqList;
	}

	public DualListModel<UserBean> getUserTwinSelect() {
		return userTwinSelect;
	}

	public DualListModel<GroupBean> getGroupTwinSelect() {
		return groupTwinSelect;
	}

	public void setUserTwinSelect(DualListModel<UserBean> userTwinSelect) {
		this.userTwinSelect = userTwinSelect;
	}

	public void setGroupTwinSelect(DualListModel<GroupBean> groupTwinSelect) {
		this.groupTwinSelect = groupTwinSelect;
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
		return grpFileName;
	}

	public void setGrpFileName(String grpFileName) {
		this.grpFileName = grpFileName;
	}

	public String getGrpContentType() {
		return grpContentType;
	}

	public void setGrpContentType(String grpContentType) {
		this.grpContentType = grpContentType;
	}

	public String getHierarchy() {
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
		return returnView;
	}

	public void setReturnView(String returnView) {
		this.returnView = returnView;
	}

	public String getToView() {
		return toView;
	}

	public void setToView(String toView) {
		this.toView = toView;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public List<AllocationBean> getAllocs() {
		return allocs;
	}

	public void setAllocs(List<AllocationBean> allocs) {
		this.allocs = allocs;
	}

	public AllocationBean getAllocationBean() {
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
		return disStatusList;
	}

	public void setDisStatusList(List<MetaDataBean> disStatusList) {
		this.disStatusList = disStatusList;
	}

	public HashMap<String, String> getAllocEntityList() {
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
		return beans;
	}

	public void setBeans(List<MetaDataBean> beans) {
		this.beans = beans;
	}

	public MetaDataBean getBean() {
		return bean;
	}

	public void setBean(MetaDataBean bean) {
		this.bean = bean;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getSelId() {
		return selId;
	}

	public void setSelId(String selId) {
		this.selId = selId;
	}

	public String getSelVal() {
		return selVal;
	}

	public void setSelVal(String selVal) {
		this.selVal = selVal;
	}

	public void setMetaList(HashMap<String, String> metaList) {
		this.metaList = metaList;
	}

	public NewsBean getNewsBean() {
		return newsBean;
	}

	public void setNewsBean(NewsBean newsBean) {
		this.newsBean = newsBean;
	}

	public List<NewsBean> getViewNewsBeans() {
		return viewNewsBeans;
	}

	public void setViewNewsBeans(List<NewsBean> viewNewsBeans) {
		this.viewNewsBeans = viewNewsBeans;
	}

	public List<NotificationBean> getViewNotifications() {
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
		return notificationBean;
	}

	public void setNotificationBean(NotificationBean notificationBean) {
		this.notificationBean = notificationBean;
	}

	public String[] getSelGrpId() {
		return selGrpId;
	}

	public void setSelGrpId(String[] selGrpId) {
		this.selGrpId = selGrpId;
	}

	public String[] getSelUserId() {
		return selUserId;
	}

	public void setSelUserId(String[] selUserId) {
		this.selUserId = selUserId;
	}

}
