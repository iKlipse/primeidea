package za.co.idea.ip.portal.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import za.co.idea.ip.portal.bean.GroupBean;
import za.co.idea.ip.portal.bean.NotificationBean;
import za.co.idea.ip.ws.bean.GroupMessage;
import za.co.idea.ip.ws.bean.NotificationMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;
import za.co.idea.ip.ws.util.CustomObjectMapper;

@ManagedBean(name = "notificationController")
@SessionScoped
public class NotificationController implements Serializable {

	private static final long serialVersionUID = 4208051043571552191L;
	private static final Logger logger = Logger.getLogger(NotificationController.class);
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-portal");
	private NotificationBean notificationBean;
	private DualListModel<GroupBean> groupTwinSelect;
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

	// private static final IdNumberGen COUNTER = new IdNumberGen();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private WebClient createCustomClient(String url) {
		List providers = new ArrayList();
		providers.add(new JacksonJaxbJsonProvider(new CustomObjectMapper(), JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS));
		WebClient client = WebClient.create(url, providers);
		client.header("Content-Type", "application/json");
		client.header("Accept", "application/json");
		return client;
	}

	@PostConstruct
	public void initializePage() {
		try {
			notificationBean = new NotificationBean();
			pGrps = fetchAllGroups();
			groupTwinSelect = new DualListModel<GroupBean>(pGrps, new ArrayList<GroupBean>());
			showCreateNotification = true;
			if (toView != null && Integer.valueOf(toView) != -1) {
				switch (Integer.valueOf(toView)) {
				case 1:
					break;
				default:
					break;
				}
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

	public String showViewNotifications() {
		try {
			viewNotifications = fetchAllNotifications();
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
			pGrps = fetchAllGroups();
			groupTwinSelect = new DualListModel<GroupBean>(pGrps, new ArrayList<GroupBean>());
			showCreateNotification = true;
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform create request", "System error occurred, cannot perform create request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	/*
	 * public String showEditNotification() { try { pGrps = fetchAllGroups();
	 * groupTwinSelect = initializeSelectedGroups(pGrps); try { WebClient
	 * getBlobClient = createCustomClient("http://" +
	 * BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") +
	 * "/ip-ws/ip/ds/doc/getId/" + notificationBean.getNotifId() + "/ip_notif");
	 * Long blobId =
	 * getBlobClient.accept(MediaType.APPLICATION_JSON).get(Long.class);
	 * getBlobClient.close(); if (blobId != -999l) { WebClient getBlobNameClient
	 * = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" +
	 * BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/getName/" + blobId);
	 * String blobName =
	 * getBlobNameClient.accept(MediaType.APPLICATION_JSON).get(String.class);
	 * getBlobNameClient.close(); WebClient client = WebClient.create("http://"
	 * + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") +
	 * "/ip-ws/ip/ds/doc/download/" + blobId + "/" + blobName,
	 * Collections.singletonList(new JacksonJsonProvider(new
	 * CustomObjectMapper()))); client.header("Content-Type",
	 * "application/json"); client.header("Accept",
	 * MediaType.MULTIPART_FORM_DATA); Attachment attachment =
	 * client.accept(MediaType.MULTIPART_FORM_DATA).get(Attachment.class); if
	 * (attachment != null) { setFileAvail(false);
	 * this.setFileName(attachment.getContentDisposition
	 * ().toString().replace("attachment;filename=", "")); fileContent = new
	 * DefaultStreamedContent(attachment.getDataHandler().getInputStream()); }
	 * else { setFileAvail(true); fileContent = null; } } else {
	 * setFileAvail(true); fileContent = null; } } catch (Exception e)
	 * {logger.error(e,e); FacesMessage exceptionMessage = new
	 * FacesMessage(FacesMessage.SEVERITY_ERROR,
	 * "System error occurred, cannot perform edit request",
	 * "System error occurred, cannot perform edit request");
	 * FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
	 * setFileAvail(true); fileContent = null; } return "notife"; } catch
	 * (Exception e) { FacesMessage exceptionMessage = new
	 * FacesMessage(FacesMessage.SEVERITY_ERROR,
	 * "System error occurred, cannot perform edit request",
	 * "System error occurred, cannot perform edit request");
	 * FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
	 * setFileAvail(true); fileContent = null; return ""; } }
	 */

	private List<NotificationBean> fetchAllNotifications() {
		List<NotificationBean> ret = new ArrayList<NotificationBean>();
		WebClient fetchNotifClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/nos/notif/list/" + ((Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userId")).longValue());
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

	public String saveNotification() {
		try {
			WebClient addClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/nos/notif/add");
			NotificationMessage notifMessage = new NotificationMessage();
			notifMessage.setNotifCrtdDate(new Date().toString());
			notifMessage.setNotifId(java.util.UUID.randomUUID().toString());
			notifMessage.setNotifStatus("n");
			notifMessage.setNotifSubject(notificationBean.getNotifSubject());
			notifMessage.setNotifBody(notificationBean.getNotifBody());
			if (uploadContent != null) {
				notifMessage.setNotifAttach(IOUtils.toString(uploadContent.getStream()));
			}
			notifMessage.setGroupIdList(getSelGroupIds());
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

	private List<GroupBean> fetchAllGroups() {
		List<GroupBean> ret = new ArrayList<GroupBean>();
		WebClient viewGroupsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/group/list");
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
		if (ae != null)
			for (Long id : ae)
				ret.add(id);
		return ret;
	}

	public NotificationBean getNotificationBean() {
		return notificationBean;
	}

	public void setNotificationBean(NotificationBean notificationBean) {
		this.notificationBean = notificationBean;
	}

	public DualListModel<GroupBean> getGroupTwinSelect() {
		return groupTwinSelect;
	}

	public void setGroupTwinSelect(DualListModel<GroupBean> groupTwinSelect) {
		this.groupTwinSelect = groupTwinSelect;
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

}
