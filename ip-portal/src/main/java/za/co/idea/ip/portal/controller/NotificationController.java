package za.co.idea.ip.portal.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import za.co.idea.ip.portal.bean.GroupBean;
import za.co.idea.ip.portal.bean.NotificationBean;
import za.co.idea.ip.portal.util.RESTServiceHelper;
import za.co.idea.ip.ws.bean.NotificationMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;
import za.co.idea.ip.ws.bean.UserMessage;
import za.co.idea.ip.ws.util.CustomObjectMapper;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;

@ManagedBean(name = "notificationController")
@SessionScoped
public class NotificationController implements Serializable {

	private static final long serialVersionUID = 4208051043571552191L;
	private static final Logger logger = Logger.getLogger(NotificationController.class);
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-portal");
	private NotificationBean notificationBean;
	private List<NotificationBean> viewNotifications;
	private List<GroupBean> pGrps;
	private StreamedContent fileContent;
	private StreamedContent uploadContent;
	private String fileName;
	private String contentType;
	private boolean fileAvail;
	private boolean showCreateNotification;
	private String returnView;
	private String toView;
	private Long userId;
	private AccessController controller;

	// private static final IdNumberGen COUNTER = new IdNumberGen();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private WebClient createCustomClient(String url) {
		List providers = new ArrayList();
		providers.add(new JacksonJaxbJsonProvider(new CustomObjectMapper(), JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS));
		WebClient client = WebClient.create(url, providers);
		client.header("Content-Type", MediaType.APPLICATION_JSON);
		client.header("Accept", MediaType.APPLICATION_JSON);
		return client;
	}

	public void initializePage() {
		try {
			notificationBean = new NotificationBean();
			pGrps = RESTServiceHelper.fetchActiveGroups();
			showCreateNotification = true;
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

	public String showViewNotifications() {
		try {
			viewNotifications = RESTServiceHelper.fetchAllNotifications(userId);
			return "notifv";
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			return "";
		}
	}

	public void showCreateNotification() {
		try {
			notificationBean = new NotificationBean();
			pGrps = RESTServiceHelper.fetchActiveGroups();
			showCreateNotification = true;
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create request", "System error occurred, cannot perform create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
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

	public void fileUploadHandle(FileUploadEvent fue) {
		try {
			UploadedFile file = fue.getFile();
			this.uploadContent = new DefaultStreamedContent(file.getInputstream());
			this.fileName = file.getFileName();
			this.contentType = file.getContentType();
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform upload request", "System error occurred, cannot perform upload request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public NotificationBean getNotificationBean() {
		return notificationBean;
	}

	public void setNotificationBean(NotificationBean notificationBean) {
		this.notificationBean = notificationBean;
	}

	public List<NotificationBean> getViewNotifications() {
		return viewNotifications;
	}

	public void setViewNotifications(List<NotificationBean> viewNotifications) {
		this.viewNotifications = viewNotifications;
	}

	public List<GroupBean> getpGrps() {
		return pGrps;
	}

	public void setpGrps(List<GroupBean> pGrps) {
		this.pGrps = pGrps;
	}

	public StreamedContent getFileContent() {
		return fileContent;
	}

	public void setFileContent(StreamedContent fileContent) {
		this.fileContent = fileContent;
	}

	public StreamedContent getUploadContent() {
		return uploadContent;
	}

	public void setUploadContent(StreamedContent uploadContent) {
		this.uploadContent = uploadContent;
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

	public boolean isFileAvail() {
		return fileAvail;
	}

	public void setFileAvail(boolean fileAvail) {
		this.fileAvail = fileAvail;
	}

	public boolean isShowCreateNotification() {
		return showCreateNotification;
	}

	public void setShowCreateNotification(boolean showCreateNotification) {
		this.showCreateNotification = showCreateNotification;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

}
