package za.co.idea.ip.portal.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.portlet.PortletContext;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudModel;

import za.co.idea.ip.portal.bean.AllocationBean;
import za.co.idea.ip.portal.bean.ChallengeBean;
import za.co.idea.ip.portal.bean.ClaimBean;
import za.co.idea.ip.portal.bean.FileBean;
import za.co.idea.ip.portal.bean.FunctionBean;
import za.co.idea.ip.portal.bean.GroupBean;
import za.co.idea.ip.portal.bean.IdeaBean;
import za.co.idea.ip.portal.bean.ListSelectorBean;
import za.co.idea.ip.portal.bean.MetaDataBean;
import za.co.idea.ip.portal.bean.NewsBean;
import za.co.idea.ip.portal.bean.NotificationBean;
import za.co.idea.ip.portal.bean.PointBean;
import za.co.idea.ip.portal.bean.ReviewBean;
import za.co.idea.ip.portal.bean.RewardsBean;
import za.co.idea.ip.portal.bean.SolutionBean;
import za.co.idea.ip.portal.bean.TagBean;
import za.co.idea.ip.portal.bean.UserBean;
import za.co.idea.ip.portal.controller.AccessController;
import za.co.idea.ip.ws.bean.AllocationMessage;
import za.co.idea.ip.ws.bean.ChallengeMessage;
import za.co.idea.ip.ws.bean.ClaimMessage;
import za.co.idea.ip.ws.bean.FileMessage;
import za.co.idea.ip.ws.bean.FunctionMessage;
import za.co.idea.ip.ws.bean.GroupMessage;
import za.co.idea.ip.ws.bean.IdeaMessage;
import za.co.idea.ip.ws.bean.MetaDataMessage;
import za.co.idea.ip.ws.bean.NewsMessage;
import za.co.idea.ip.ws.bean.NotificationMessage;
import za.co.idea.ip.ws.bean.PointMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;
import za.co.idea.ip.ws.bean.ReviewMessage;
import za.co.idea.ip.ws.bean.RewardsMessage;
import za.co.idea.ip.ws.bean.SolutionMessage;
import za.co.idea.ip.ws.bean.TagMessage;
import za.co.idea.ip.ws.bean.UserMessage;
import za.co.idea.ip.ws.util.CustomObjectMapper;

public class RESTServiceHelper {
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-portal");
	private static final IdNumberGen COUNTER = new IdNumberGen();
	private static final Logger logger = Logger.getLogger(RESTServiceHelper.class);

	public static WebClient createCustomClient(String url) {
		WebClient client = WebClient.create(url, Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
		client.header("Content-Type", MediaType.APPLICATION_JSON);
		client.header("Accept", MediaType.APPLICATION_JSON);
		return client;
	}

	public static UserBean getUserBean(UserMessage userMessage) {
		UserBean bean = new UserBean();
		try {
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
			bean.setGroupId(userMessage.getGroupId());
			bean.setPriGroupName(userMessage.getPriGroupName());
			bean.setcPw(userMessage.getPwd());
			bean.setSecQ(userMessage.getSecQ());
			bean.setSecA(userMessage.getSecA());
			bean.setuCrtdDate(userMessage.getuCrtdDate());
		} catch (Exception e) {
			logger.error("Error: " + e);
		}
		return bean;
	}

	// Users Section
	public static List<UserBean> fetchAllUsers() {
		List<UserBean> ret = new ArrayList<UserBean>();
		WebClient viewUsersClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/list/sort/pg");
		Collection<? extends UserMessage> users = new ArrayList<UserMessage>(viewUsersClient.accept(MediaType.APPLICATION_JSON).getCollection(UserMessage.class));
		viewUsersClient.close();
		for (UserMessage userMessage : users) {
			UserBean bean = getUserBean(userMessage);
			ret.add(bean);
		}
		return ret;
	}

	public static List<UserBean> fetchActiveUsers() {
		List<UserBean> ret = new ArrayList<UserBean>();
		WebClient viewUsersClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/active/list");
		Collection<? extends UserMessage> users = new ArrayList<UserMessage>(viewUsersClient.accept(MediaType.APPLICATION_JSON).getCollection(UserMessage.class));
		viewUsersClient.close();
		for (UserMessage userMessage : users) {
			UserBean bean = getUserBean(userMessage);
			ret.add(bean);
		}
		return ret;
	}

	public static List<UserBean> fetchInActiveUsers() {
		List<UserBean> ret = new ArrayList<UserBean>();
		WebClient viewUsersClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/inActive/list");
		Collection<? extends UserMessage> users = new ArrayList<UserMessage>(viewUsersClient.accept(MediaType.APPLICATION_JSON).getCollection(UserMessage.class));
		viewUsersClient.close();
		for (UserMessage userMessage : users) {
			UserBean bean = getUserBean(userMessage);
			ret.add(bean);
		}
		return ret;
	}

	public static List<UserBean> fetchUsersByGroup(Long groupId) {
		List<UserBean> ret = new ArrayList<UserBean>();
		WebClient viewUsersClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/list/group/" + groupId);
		Collection<? extends UserMessage> users = new ArrayList<UserMessage>(viewUsersClient.accept(MediaType.APPLICATION_JSON).getCollection(UserMessage.class));
		viewUsersClient.close();
		for (UserMessage userMessage : users) {
			UserBean bean = getUserBean(userMessage);
			ret.add(bean);
		}
		return ret;
	}

	private static GroupBean getGroupBean(GroupMessage groupMessage) {
		GroupBean bean = new GroupBean();
		try {
			bean.setgId(groupMessage.getgId());
			bean.setGeMail(groupMessage.getGeMail());
			bean.setgName(groupMessage.getgName());
			bean.setIsActive(groupMessage.getIsActive());
			bean.setSelAdmUser(groupMessage.getAdmUserId());
			bean.setSelPGrp(groupMessage.getpGrpId());
			bean.setSelPGrpName(groupMessage.getpGrpName());
			bean.setgCrtdDate(groupMessage.getCrtdDate());
			bean.getUserIdList().clear();
			for (Long id : groupMessage.getUserIdList())
				if (id != null)
					bean.getUserIdList().add(id);
		} catch (Exception e) {
			logger.error(e, e);
		}
		return bean;
	}

	// Group Section
	public static List<GroupBean> fetchAllGroups() {
		List<GroupBean> ret = new ArrayList<GroupBean>();
		WebClient viewGroupsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/group/list");
		Collection<? extends GroupMessage> groups = new ArrayList<GroupMessage>(viewGroupsClient.accept(MediaType.APPLICATION_JSON).getCollection(GroupMessage.class));
		viewGroupsClient.close();
		for (GroupMessage groupMessage : groups) {
			GroupBean bean = getGroupBean(groupMessage);
			ret.add(bean);
		}
		return ret;
	}

	public static List<GroupBean> fetchActiveGroups() {
		List<GroupBean> ret = new ArrayList<GroupBean>();
		WebClient viewGroupsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/group/active/list");
		Collection<? extends GroupMessage> groups = new ArrayList<GroupMessage>(viewGroupsClient.accept(MediaType.APPLICATION_JSON).getCollection(GroupMessage.class));
		viewGroupsClient.close();
		for (GroupMessage groupMessage : groups) {
			GroupBean bean = getGroupBean(groupMessage);
			ret.add(bean);
		}
		return ret;
	}

	public static List<GroupBean> fetchReviewGroups() {
		List<GroupBean> ret = new ArrayList<GroupBean>();
		WebClient viewGroupsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/group/review/list");
		Collection<? extends GroupMessage> groups = new ArrayList<GroupMessage>(viewGroupsClient.accept(MediaType.APPLICATION_JSON).getCollection(GroupMessage.class));
		viewGroupsClient.close();
		for (GroupMessage groupMessage : groups) {
			GroupBean bean = getGroupBean(groupMessage);
			ret.add(bean);
		}
		return ret;
	}

	public static List<GroupBean> fetchSubGroups(Long id) {
		List<GroupBean> ret = new ArrayList<GroupBean>();
		WebClient viewGroupsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/group/sub/list/" + id);
		Collection<? extends GroupMessage> groups = new ArrayList<GroupMessage>(viewGroupsClient.accept(MediaType.APPLICATION_JSON).getCollection(GroupMessage.class));
		viewGroupsClient.close();
		for (GroupMessage groupMessage : groups) {
			GroupBean bean = getGroupBean(groupMessage);
			ret.add(bean);
		}
		return ret;
	}

	public static GroupBean getGroupById(Long pGrpId) {
		GroupBean bean = new GroupBean();
		WebClient groupByIdClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/group/get/" + pGrpId);
		GroupMessage groupMessage = groupByIdClient.accept(MediaType.APPLICATION_JSON).get(GroupMessage.class);
		groupByIdClient.close();
		bean = getGroupBean(groupMessage);
		return bean;
	}

	private static IdeaBean getIdeaBean(IdeaMessage ideaMessage) {
		IdeaBean bean = new IdeaBean();
		try {
			bean.setCrtdById(ideaMessage.getCrtdById());
			bean.setCrtdDate(ideaMessage.getCrtdDate());
			bean.setIdeaDesc(ideaMessage.getIdeaDesc());
			bean.setIdeaTag(ideaMessage.getIdeaTag());
			bean.setIdeaId(ideaMessage.getIdeaId());
			bean.setIdeaTitle(ideaMessage.getIdeaTitle());
			bean.setSelCatId(ideaMessage.getSelCatId());
			bean.setSetStatusId(ideaMessage.getSetStatusId());
			bean.setGroupIdList(getIdsFromArray(ideaMessage.getGroupIdList()));
			bean.setCrtByImgAvail(ideaMessage.isCrtByImgAvail());
			bean.setCrtByImgPath(ideaMessage.getCrtByImgPath());
			bean.setCrtdByName(ideaMessage.getCrtdByName());
			bean.setStatusName(ideaMessage.getStatusName());
			bean.setRevUserId(ideaMessage.getRevUserId());
			WebClient fetchDocClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/list/" + ideaMessage.getIdeaId() + "/ip_idea");
			Collection<? extends FileMessage> res = new ArrayList<FileMessage>(fetchDocClient.accept(MediaType.APPLICATION_JSON).getCollection(FileMessage.class));
			logger.info("Files list ");
			if (res != null && !res.isEmpty()) {
				bean.setAttachsAvail(true);
				ArrayList<FileBean> list = new ArrayList<FileBean>();
				for (FileMessage fileMessage : res) {
					FileBean fileBean = new FileBean();
					fileBean.setId(fileMessage.getId());
					logger.info("-----File id : " + fileMessage.getId() + "---name : " + fileMessage.getName());
					fileBean.setLoc(fileMessage.getLoc());
					fileBean.setName(fileMessage.getName());
					fileBean.setSize(fileMessage.getSize());
					fileBean.setType(fileMessage.getType());
					fileBean.setUrl(fileMessage.getUrl());
					list.add(fileBean);
				}
				bean.setFiles(list);
			} else {
				bean.setAttachsAvail(false);
			}
			fetchDocClient.close();
		} catch (Exception e) {
			logger.error(e, e);
		}
		return bean;
	}

	// Ideas Section
	public List<IdeaBean> fetchAllIdeas(AccessController controller, Long userId) {
		List<IdeaBean> ret = new ArrayList<IdeaBean>();
		WebClient fetchIdeaClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/is/idea/list");
		Collection<? extends IdeaMessage> ideas = new ArrayList<IdeaMessage>(fetchIdeaClient.accept(MediaType.APPLICATION_JSON).getCollection(IdeaMessage.class));
		fetchIdeaClient.close();
		for (IdeaMessage ideaMessage : ideas) {
			IdeaBean bean = getIdeaBean(ideaMessage);
			if (controller.isAdminEnabled() || bean.getCrtdById() == userId)
				bean.setDisableEdit(false);
			else
				bean.setDisableEdit(true);
			ret.add(bean);
		}
		return ret;
	}

	public static List<IdeaBean> fetchAllIdeasByUser(AccessController controller, long userId) {
		List<IdeaBean> ret = new ArrayList<IdeaBean>();
		WebClient fetchIdeaClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/is/idea/list/user/access/" + userId);
		Collection<? extends IdeaMessage> ideas = new ArrayList<IdeaMessage>(fetchIdeaClient.accept(MediaType.APPLICATION_JSON).getCollection(IdeaMessage.class));
		fetchIdeaClient.close();
		for (IdeaMessage ideaMessage : ideas) {
			IdeaBean bean = getIdeaBean(ideaMessage);
			if (controller.isAdminEnabled() || bean.getCrtdById() == userId)
				bean.setDisableEdit(false);
			else
				bean.setDisableEdit(true);
			ret.add(bean);
		}
		return ret;
	}

	public static List<IdeaBean> fetchAllIdeasCreatedByUser(AccessController controller, long userId) {
		List<IdeaBean> ret = new ArrayList<IdeaBean>();
		WebClient fetchIdeaClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/is/idea/list/user/created/" + userId);
		Collection<? extends IdeaMessage> ideas = new ArrayList<IdeaMessage>(fetchIdeaClient.accept(MediaType.APPLICATION_JSON).getCollection(IdeaMessage.class));
		fetchIdeaClient.close();
		for (IdeaMessage ideaMessage : ideas) {
			IdeaBean bean = getIdeaBean(ideaMessage);
			if (controller.isAdminEnabled() || bean.getCrtdById() == userId)
				bean.setDisableEdit(false);
			else
				bean.setDisableEdit(true);
			ret.add(bean);
		}
		return ret;
	}

	public static List<IdeaBean> fetchAllIdeasByStatus(AccessController controller, Integer status, Long userId) {
		List<IdeaBean> ret = new ArrayList<IdeaBean>();
		WebClient fetchIdeaClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/is/idea/list/status/" + status);
		Collection<? extends IdeaMessage> ideas = new ArrayList<IdeaMessage>(fetchIdeaClient.accept(MediaType.APPLICATION_JSON).getCollection(IdeaMessage.class));
		fetchIdeaClient.close();
		for (IdeaMessage ideaMessage : ideas) {
			IdeaBean bean = getIdeaBean(ideaMessage);
			if (controller.isAdminEnabled() || bean.getCrtdById() == userId)
				bean.setDisableEdit(false);
			else
				bean.setDisableEdit(true);
			ret.add(bean);
		}
		return ret;
	}

	public static List<IdeaBean> fetchAllReviewIdeasByUserId(AccessController controller, long userId) {
		List<IdeaBean> ret = new ArrayList<IdeaBean>();
		WebClient fetchIdeaClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/is/idea/list/reviewStatus/user/" + userId);
		Collection<? extends IdeaMessage> ideas = new ArrayList<IdeaMessage>(fetchIdeaClient.accept(MediaType.APPLICATION_JSON).getCollection(IdeaMessage.class));
		fetchIdeaClient.close();
		for (IdeaMessage ideaMessage : ideas) {
			IdeaBean bean = getIdeaBean(ideaMessage);
			if (controller.isAdminEnabled() || bean.getCrtdById() == userId)
				bean.setDisableEdit(false);
			else
				bean.setDisableEdit(true);
			ret.add(bean);
		}
		return ret;
	}

	public static List<IdeaBean> fetchAllIdeasByStatusIdUserId(AccessController controller, Integer status, long userId) {
		List<IdeaBean> ret = new ArrayList<IdeaBean>();
		WebClient fetchIdeaClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/is/idea/list/status/" + status + "/user/" + userId);
		Collection<? extends IdeaMessage> ideas = new ArrayList<IdeaMessage>(fetchIdeaClient.accept(MediaType.APPLICATION_JSON).getCollection(IdeaMessage.class));
		fetchIdeaClient.close();
		for (IdeaMessage ideaMessage : ideas) {
			IdeaBean bean = getIdeaBean(ideaMessage);
			if (controller.isAdminEnabled() || bean.getCrtdById() == userId)
				bean.setDisableEdit(false);
			else
				bean.setDisableEdit(true);
			ret.add(bean);
		}
		return ret;
	}

	public static List<ListSelectorBean> fetchFilteredIdeaStatuses(Long id) {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewIdeaSelectClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/is/idea/status/list");
		Collection<? extends MetaDataMessage> md = new ArrayList<MetaDataMessage>(viewIdeaSelectClient.accept(MediaType.APPLICATION_JSON).getCollection(MetaDataMessage.class));
		viewIdeaSelectClient.close();
		WebClient fetchReviewGroupsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rvs/review/list/cnt/" + id + "/ip_idea");
		Integer msgs = fetchReviewGroupsClient.accept(MediaType.APPLICATION_JSON).get(Integer.class);
		for (MetaDataMessage metaDataMessage : md) {
			if (msgs != 5 && (metaDataMessage.getId() > 2 + msgs && metaDataMessage.getId() <= 7))
				continue;
			ListSelectorBean bean = new ListSelectorBean();
			bean.setId(metaDataMessage.getId());
			bean.setDesc(metaDataMessage.getDesc());
			ret.add(bean);
		}
		return ret;
	}

	public static List<ListSelectorBean> fetchFilteredChallengeStatuses(Long id) {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewChallengeSelectClient = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/cs/challenge/status/list");
		Collection<? extends MetaDataMessage> md = new ArrayList<MetaDataMessage>(viewChallengeSelectClient.accept(MediaType.APPLICATION_JSON).getCollection(MetaDataMessage.class));
		viewChallengeSelectClient.close();
		WebClient fetchReviewGroupsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rvs/review/list/cnt/" + id + "/ip_challenge");
		Integer msgs = fetchReviewGroupsClient.accept(MediaType.APPLICATION_JSON).get(Integer.class);
		for (MetaDataMessage metaDataMessage : md) {
			if (msgs != 5 && (metaDataMessage.getId() > 1 + msgs && metaDataMessage.getId() <= 7))
				continue;
			ListSelectorBean bean = new ListSelectorBean();
			bean.setId(metaDataMessage.getId());
			bean.setDesc(metaDataMessage.getDesc());
			ret.add(bean);
		}
		return ret;
	}

	public static List<ListSelectorBean> fetchFilteredSolutionStatuses(Long id) {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewSolutionSelectClient = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ss/solution/status/list");
		Collection<? extends MetaDataMessage> md = new ArrayList<MetaDataMessage>(viewSolutionSelectClient.accept(MediaType.APPLICATION_JSON).getCollection(MetaDataMessage.class));
		viewSolutionSelectClient.close();
		WebClient fetchReviewGroupsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rvs/review/list/cnt/" + id + "/ip_solution");
		Integer msgs = fetchReviewGroupsClient.accept(MediaType.APPLICATION_JSON).get(Integer.class);
		for (MetaDataMessage metaDataMessage : md) {
			if (msgs != 5 && (metaDataMessage.getId() > 2 + msgs && metaDataMessage.getId() <= 7))
				continue;
			ListSelectorBean bean = new ListSelectorBean();
			bean.setId(metaDataMessage.getId());
			bean.setDesc(metaDataMessage.getDesc());
			ret.add(bean);
		}
		return ret;
	}

	public static List<ListSelectorBean> fetchAllIdeaStatuses() {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewIdeaSelectClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/is/idea/status/list");
		Collection<? extends MetaDataMessage> md = new ArrayList<MetaDataMessage>(viewIdeaSelectClient.accept(MediaType.APPLICATION_JSON).getCollection(MetaDataMessage.class));
		viewIdeaSelectClient.close();
		for (MetaDataMessage metaDataMessage : md) {
			ListSelectorBean bean = new ListSelectorBean();
			bean.setId(metaDataMessage.getId());
			bean.setDesc(metaDataMessage.getDesc());
			ret.add(bean);
		}
		return ret;
	}

	public static List<ListSelectorBean> fetchAllChallengeStatuses() {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewChallengeSelectClient = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/cs/challenge/status/list");
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

	public static List<ListSelectorBean> fetchAllSolutionStatuses() {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewSolutionSelectClient = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ss/solution/status/list");
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

	public static List<ListSelectorBean> fetchAllReviewIdeaStatuses(Long id, Integer statusId) {
		WebClient fetchReviewGroupsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rvs/review/list/cnt/" + id + "/ip_idea");
		Integer msgs = fetchReviewGroupsClient.accept(MediaType.APPLICATION_JSON).get(Integer.class);
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		ListSelectorBean bean = new ListSelectorBean();
		if (msgs == 1 && statusId == 2) {
			bean.setId(3);
			bean.setDesc("In-Review 1");
			ret.add(bean);
		} else if (msgs == 2) {
			if (statusId == 2) {
				bean.setId(3);
				bean.setDesc("In-Review 1");
				ret.add(bean);
				bean.setId(4);
				bean.setDesc("In-Review 2");
				ret.add(bean);
			} else if (statusId == 3) {
				bean.setId(4);
				bean.setDesc("In-Review 2");
				ret.add(bean);
			}
		} else if (msgs == 3) {
			if (statusId == 2) {
				bean.setId(3);
				bean.setDesc("In-Review 1");
				ret.add(bean);
				bean.setId(4);
				bean.setDesc("In-Review 2");
				ret.add(bean);
				bean.setId(5);
				bean.setDesc("In-Review 3");
				ret.add(bean);
			} else if (statusId == 3) {
				bean.setId(4);
				bean.setDesc("In-Review 2");
				ret.add(bean);
				bean.setId(5);
				bean.setDesc("In-Review 3");
				ret.add(bean);
			} else if (statusId == 4) {
				bean.setId(5);
				bean.setDesc("In-Review 3");
				ret.add(bean);
			}
		} else if (msgs == 4) {
			if (statusId == 2) {
				bean.setId(3);
				bean.setDesc("In-Review 1");
				ret.add(bean);
				bean.setId(4);
				bean.setDesc("In-Review 2");
				ret.add(bean);
				bean.setId(5);
				bean.setDesc("In-Review 3");
				ret.add(bean);
				bean.setId(6);
				bean.setDesc("In-Review 4");
				ret.add(bean);
			} else if (statusId == 3) {
				bean.setId(4);
				bean.setDesc("In-Review 2");
				ret.add(bean);
				bean.setId(5);
				bean.setDesc("In-Review 3");
				ret.add(bean);
				bean.setId(6);
				bean.setDesc("In-Review 4");
				ret.add(bean);
			} else if (statusId == 3) {
				bean.setId(5);
				bean.setDesc("In-Review 3");
				ret.add(bean);
				bean.setId(6);
				bean.setDesc("In-Review 4");
				ret.add(bean);
			} else if (statusId == 4) {
				bean.setId(6);
				bean.setDesc("In-Review 4");
				ret.add(bean);
			}
		} else if (msgs == 5) {
			if (statusId == 1) {
				bean.setId(3);
				bean.setDesc("In-Review 1");
				ret.add(bean);
				bean.setId(4);
				bean.setDesc("In-Review 2");
				ret.add(bean);
				bean.setId(5);
				bean.setDesc("In-Review 3");
				ret.add(bean);
				bean.setId(6);
				bean.setDesc("In-Review 4");
				ret.add(bean);
				bean.setId(7);
				bean.setDesc("In-Review 5");
				ret.add(bean);
			} else if (statusId == 2) {
				bean.setId(4);
				bean.setDesc("In-Review 2");
				ret.add(bean);
				bean.setId(5);
				bean.setDesc("In-Review 3");
				ret.add(bean);
				bean.setId(6);
				bean.setDesc("In-Review 4");
				ret.add(bean);
				bean.setId(7);
				bean.setDesc("In-Review 5");
				ret.add(bean);
			} else if (statusId == 3) {
				bean.setId(5);
				bean.setDesc("In-Review 3");
				ret.add(bean);
				bean.setId(6);
				bean.setDesc("In-Review 4");
				ret.add(bean);
				bean.setId(7);
				bean.setDesc("In-Review 5");
				ret.add(bean);
			} else if (statusId == 4) {
				bean.setId(6);
				bean.setDesc("In-Review 4");
				ret.add(bean);
				bean.setId(7);
				bean.setDesc("In-Review 5");
				ret.add(bean);
			} else if (statusId == 5) {
				bean.setId(7);
				bean.setDesc("In-Review 5");
				ret.add(bean);
			}
		}
		bean.setId(9);
		bean.setDesc("Approved");
		ret.add(bean);
		bean = new ListSelectorBean();
		bean.setId(12);
		bean.setDesc("Closed");
		ret.add(bean);
		return ret;
	}

	public static List<ListSelectorBean> fetchAllReviewChallengeStatuses(Long id, Integer statusId) {
		WebClient fetchReviewGroupsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rvs/review/list/cnt/" + id + "/ip_challenge");
		Integer msgs = fetchReviewGroupsClient.accept(MediaType.APPLICATION_JSON).get(Integer.class);
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		ListSelectorBean bean = new ListSelectorBean();
		logger.info("msgs ----" + msgs);
		if (msgs != null) {
			if (msgs == 1 && statusId == 1) {
				bean.setId(2);
				bean.setDesc("In-Review 1");
				ret.add(bean);
			} else if (msgs == 2) {
				if (statusId == 1) {
					bean.setId(2);
					bean.setDesc("In-Review 1");
					ret.add(bean);
					bean.setId(3);
					bean.setDesc("In-Review 2");
					ret.add(bean);
				} else if (statusId == 2) {
					bean.setId(3);
					bean.setDesc("In-Review 2");
					ret.add(bean);
				}
			} else if (msgs == 3) {
				if (statusId == 1) {
					bean.setId(2);
					bean.setDesc("In-Review 1");
					ret.add(bean);
					bean.setId(3);
					bean.setDesc("In-Review 2");
					ret.add(bean);
					bean.setId(4);
					bean.setDesc("In-Review 3");
					ret.add(bean);
				} else if (statusId == 2) {
					bean.setId(3);
					bean.setDesc("In-Review 2");
					ret.add(bean);
					bean.setId(4);
					bean.setDesc("In-Review 3");
					ret.add(bean);
				} else if (statusId == 3) {
					bean.setId(4);
					bean.setDesc("In-Review 3");
					ret.add(bean);
				}
			} else if (msgs == 4) {
				if (statusId == 1) {
					bean.setId(2);
					bean.setDesc("In-Review 1");
					ret.add(bean);
					bean.setId(3);
					bean.setDesc("In-Review 2");
					ret.add(bean);
					bean.setId(4);
					bean.setDesc("In-Review 3");
					ret.add(bean);
					bean.setId(5);
					bean.setDesc("In-Review 4");
					ret.add(bean);
				} else if (statusId == 2) {
					bean.setId(3);
					bean.setDesc("In-Review 2");
					ret.add(bean);
					bean.setId(4);
					bean.setDesc("In-Review 3");
					ret.add(bean);
					bean.setId(5);
					bean.setDesc("In-Review 4");
					ret.add(bean);
				} else if (statusId == 3) {
					bean.setId(4);
					bean.setDesc("In-Review 3");
					ret.add(bean);
					bean.setId(5);
					bean.setDesc("In-Review 4");
					ret.add(bean);
				} else if (statusId == 4) {
					bean.setId(5);
					bean.setDesc("In-Review 4");
					ret.add(bean);
				}
			} else if (msgs == 5) {
				if (statusId == 1) {
					bean.setId(2);
					bean.setDesc("In-Review 1");
					ret.add(bean);
					bean.setId(3);
					bean.setDesc("In-Review 2");
					ret.add(bean);
					bean.setId(4);
					bean.setDesc("In-Review 3");
					ret.add(bean);
					bean.setId(5);
					bean.setDesc("In-Review 4");
					ret.add(bean);
					bean.setId(6);
					bean.setDesc("In-Review 5");
					ret.add(bean);
				} else if (statusId == 2) {
					bean.setId(3);
					bean.setDesc("In-Review 2");
					ret.add(bean);
					bean.setId(4);
					bean.setDesc("In-Review 3");
					ret.add(bean);
					bean.setId(5);
					bean.setDesc("In-Review 4");
					ret.add(bean);
					bean.setId(6);
					bean.setDesc("In-Review 5");
					ret.add(bean);
				} else if (statusId == 3) {
					bean.setId(4);
					bean.setDesc("In-Review 3");
					ret.add(bean);
					bean.setId(5);
					bean.setDesc("In-Review 4");
					ret.add(bean);
					bean.setId(6);
					bean.setDesc("In-Review 5");
					ret.add(bean);
				} else if (statusId == 4) {
					bean.setId(5);
					bean.setDesc("In-Review 4");
					ret.add(bean);
					bean.setId(6);
					bean.setDesc("In-Review 5");
					ret.add(bean);
				} else if (statusId == 5) {
					bean.setId(6);
					bean.setDesc("In-Review 5");
					ret.add(bean);
				}
			}

			bean.setId(7);
			bean.setDesc("Approve");
			ret.add(bean);
			bean = new ListSelectorBean();
			bean.setId(10);
			bean.setDesc("Reject");
			ret.add(bean);
		}
		return ret;
	}

	public static List<ListSelectorBean> fetchAllReviewSolutionStatuses(Long id, Integer statusId) {
		WebClient fetchReviewGroupsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rvs/review/list/cnt/" + id + "/ip_solution");
		Integer msgs = fetchReviewGroupsClient.accept(MediaType.APPLICATION_JSON).get(Integer.class);
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		ListSelectorBean bean = new ListSelectorBean();
		if (msgs == 1 && statusId == 2) {
			bean.setId(3);
			bean.setDesc("In-Review 1");
			ret.add(bean);
		} else if (msgs == 2) {
			if (statusId == 2) {
				bean.setId(3);
				bean.setDesc("In-Review 1");
				ret.add(bean);
				bean.setId(4);
				bean.setDesc("In-Review 2");
				ret.add(bean);
			} else if (statusId == 3) {
				bean.setId(4);
				bean.setDesc("In-Review 2");
				ret.add(bean);
			}
		} else if (msgs == 3) {
			if (statusId == 2) {
				bean.setId(3);
				bean.setDesc("In-Review 1");
				ret.add(bean);
				bean.setId(4);
				bean.setDesc("In-Review 2");
				ret.add(bean);
				bean.setId(5);
				bean.setDesc("In-Review 3");
				ret.add(bean);
			} else if (statusId == 3) {
				bean.setId(4);
				bean.setDesc("In-Review 2");
				ret.add(bean);
				bean.setId(5);
				bean.setDesc("In-Review 3");
				ret.add(bean);
			} else if (statusId == 4) {
				bean.setId(5);
				bean.setDesc("In-Review 3");
				ret.add(bean);
			}
		} else if (msgs == 4) {
			if (statusId == 2) {
				bean.setId(3);
				bean.setDesc("In-Review 1");
				ret.add(bean);
				bean.setId(4);
				bean.setDesc("In-Review 2");
				ret.add(bean);
				bean.setId(5);
				bean.setDesc("In-Review 3");
				ret.add(bean);
				bean.setId(6);
				bean.setDesc("In-Review 4");
				ret.add(bean);
			} else if (statusId == 3) {
				bean.setId(4);
				bean.setDesc("In-Review 2");
				ret.add(bean);
				bean.setId(5);
				bean.setDesc("In-Review 3");
				ret.add(bean);
				bean.setId(6);
				bean.setDesc("In-Review 4");
				ret.add(bean);
			} else if (statusId == 3) {
				bean.setId(5);
				bean.setDesc("In-Review 3");
				ret.add(bean);
				bean.setId(6);
				bean.setDesc("In-Review 4");
				ret.add(bean);
			} else if (statusId == 4) {
				bean.setId(6);
				bean.setDesc("In-Review 4");
				ret.add(bean);
			}
		} else if (msgs == 5) {
			if (statusId == 1) {
				bean.setId(3);
				bean.setDesc("In-Review 1");
				ret.add(bean);
				bean.setId(4);
				bean.setDesc("In-Review 2");
				ret.add(bean);
				bean.setId(5);
				bean.setDesc("In-Review 3");
				ret.add(bean);
				bean.setId(6);
				bean.setDesc("In-Review 4");
				ret.add(bean);
				bean.setId(7);
				bean.setDesc("In-Review 5");
				ret.add(bean);
			} else if (statusId == 2) {
				bean.setId(4);
				bean.setDesc("In-Review 2");
				ret.add(bean);
				bean.setId(5);
				bean.setDesc("In-Review 3");
				ret.add(bean);
				bean.setId(6);
				bean.setDesc("In-Review 4");
				ret.add(bean);
				bean.setId(7);
				bean.setDesc("In-Review 5");
				ret.add(bean);
			} else if (statusId == 3) {
				bean.setId(5);
				bean.setDesc("In-Review 3");
				ret.add(bean);
				bean.setId(6);
				bean.setDesc("In-Review 4");
				ret.add(bean);
				bean.setId(7);
				bean.setDesc("In-Review 5");
				ret.add(bean);
			} else if (statusId == 4) {
				bean.setId(6);
				bean.setDesc("In-Review 4");
				ret.add(bean);
				bean.setId(7);
				bean.setDesc("In-Review 5");
				ret.add(bean);
			} else if (statusId == 5) {
				bean.setId(7);
				bean.setDesc("In-Review 5");
				ret.add(bean);
			}
		}
		bean.setId(9);
		bean.setDesc("Approved");
		ret.add(bean);
		bean = new ListSelectorBean();
		bean.setId(12);
		bean.setDesc("Closed");
		ret.add(bean);
		return ret;
	}

	public static List<ListSelectorBean> fetchNextIdeaStatuses(Long status) {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewIdeaSelectClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/is/idea/status/list/" + status);
		Collection<? extends MetaDataMessage> md = new ArrayList<MetaDataMessage>(viewIdeaSelectClient.accept(MediaType.APPLICATION_JSON).getCollection(MetaDataMessage.class));
		viewIdeaSelectClient.close();
		for (MetaDataMessage metaDataMessage : md) {
			ListSelectorBean bean = new ListSelectorBean();
			bean.setId(metaDataMessage.getId());
			bean.setDesc(metaDataMessage.getDesc());
			ret.add(bean);
		}
		return ret;
	}

	public static List<ListSelectorBean> fetchAllIdeaCat() {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewIdeaSelectClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/is/idea/cat/list");
		Collection<? extends MetaDataMessage> md = new ArrayList<MetaDataMessage>(viewIdeaSelectClient.accept(MediaType.APPLICATION_JSON).getCollection(MetaDataMessage.class));
		viewIdeaSelectClient.close();
		for (MetaDataMessage metaDataMessage : md) {
			ListSelectorBean bean = new ListSelectorBean();
			bean.setId(metaDataMessage.getId());
			bean.setDesc(metaDataMessage.getDesc());
			ret.add(bean);
		}
		return ret;
	}

	private static List<Long> getIdsFromArray(Long[] ae) {
		List<Long> ret = new ArrayList<Long>();
		if (ae != null)
			for (Long id : ae)
				ret.add(id);
		return ret;
	}

	private static ChallengeBean getChallengeBean(ChallengeMessage challengeMessage) {
		ChallengeBean bean = new ChallengeBean();
		try {
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
			bean.setCrtByImgAvail(challengeMessage.isCrtByImgAvail());
			bean.setCrtByImgPath(challengeMessage.getCrtByImgPath());
			bean.setCrtdByName(challengeMessage.getCrtdByName());
			bean.setImgAvail(challengeMessage.isImgAvail());
			bean.setBlobUrl(challengeMessage.getBlobUrl());
			bean.setFileName(challengeMessage.getFileName());
			bean.setStatusName(challengeMessage.getStatusName());
			bean.setCatName(challengeMessage.getCatName());
			WebClient fetchDocClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/list/" + challengeMessage.getId() + "/ip_challenge");
			Collection<? extends FileMessage> res = new ArrayList<FileMessage>(fetchDocClient.accept(MediaType.APPLICATION_JSON).getCollection(FileMessage.class));
			if (res != null && !res.isEmpty()) {
				bean.setImgAvail(true);
				for (FileMessage fileMessage : res) {
					FileBean fileBean = new FileBean();
					fileBean.setId(fileMessage.getId());
					fileBean.setLoc(fileMessage.getLoc());
					fileBean.setName(fileMessage.getName());
					fileBean.setSize(fileMessage.getSize());
					fileBean.setType(fileMessage.getType());
					fileBean.setUrl(fileMessage.getUrl());
					bean.getFiles().add(fileBean);
				}
			} else {
				bean.setImgAvail(false);
			}
			fetchDocClient.close();
		} catch (Exception e) {
			logger.error(e, e);
		}
		return bean;
	}

	public static List<ChallengeBean> fetchAllReviewChallengesByUser(Long userId) {
		List<ChallengeBean> ret = new ArrayList<ChallengeBean>();
		WebClient fetchChallengeClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/cs/challenge/list/reviewStatus/" + userId);
		Collection<? extends ChallengeMessage> challenges = new ArrayList<ChallengeMessage>(fetchChallengeClient.accept(MediaType.APPLICATION_JSON).getCollection(ChallengeMessage.class));
		fetchChallengeClient.close();
		for (ChallengeMessage challengeMessage : challenges) {
			ChallengeBean bean = getChallengeBean(challengeMessage);
			ret.add(bean);
		}
		return ret;
	}

	private static RewardsBean getRewardsBean(RewardsMessage message, Long userId, long totalPoints) {
		RewardsBean bean = new RewardsBean();
		try {
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
			bean.setRwTaggable(isWishlist(message.getRwId(), userId));
			bean.setBlobUrl(message.getBlobUrl());
			bean.setrCatName(message.getrCatName());
		} catch (Exception e) {
			logger.error(e, e);
		}
		return bean;
	}

	public static List<RewardsBean> fetchAllRewards(Long userId, long totalPoints) {
		fetchAllPointsByUser(userId);
		List<RewardsBean> ret = new ArrayList<RewardsBean>();
		WebClient viewRewardsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rs/rewards/list");
		Collection<? extends RewardsMessage> rewards = new ArrayList<RewardsMessage>(viewRewardsClient.accept(MediaType.APPLICATION_JSON).getCollection(RewardsMessage.class));
		viewRewardsClient.close();
		for (RewardsMessage message : rewards) {
			RewardsBean bean = getRewardsBean(message, userId, totalPoints);
			ret.add(bean);
		}
		return ret;
	}

	public static List<RewardsBean> fetchAllRewardsByUser(Long userId, long totalPoints) {
		fetchAllPointsByUser(userId);
		List<RewardsBean> ret = new ArrayList<RewardsBean>();
		WebClient viewRewardsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rs/rewards/list/" + userId);
		Collection<? extends RewardsMessage> rewards = new ArrayList<RewardsMessage>(viewRewardsClient.accept(MediaType.APPLICATION_JSON).getCollection(RewardsMessage.class));
		viewRewardsClient.close();
		for (RewardsMessage message : rewards) {
			RewardsBean bean = getRewardsBean(message, userId, totalPoints);
			ret.add(bean);
		}
		return ret;
	}

	public static List<RewardsBean> fetchAllAvailableRewards(Long userId, long totalPoints) {
		List<RewardsBean> ret = new ArrayList<RewardsBean>();
		WebClient viewRewardsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rs/rewards/list/avail");
		Collection<? extends RewardsMessage> rewards = new ArrayList<RewardsMessage>(viewRewardsClient.accept(MediaType.APPLICATION_JSON).getCollection(RewardsMessage.class));
		viewRewardsClient.close();
		for (RewardsMessage message : rewards) {
			RewardsBean bean = getRewardsBean(message, userId, totalPoints);
			ret.add(bean);
		}
		return ret;
	}

	public static List<RewardsBean> fetchAllWishlistByUser(Long userId) {
		fetchAllPointsByUser(userId);
		List<RewardsBean> ret = new ArrayList<RewardsBean>();
		WebClient viewRewardsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rs/rewards/list/" + userId);
		Collection<? extends RewardsMessage> rewards = new ArrayList<RewardsMessage>(viewRewardsClient.accept(MediaType.APPLICATION_JSON).getCollection(RewardsMessage.class));
		viewRewardsClient.close();
		for (RewardsMessage message : rewards) {
			RewardsBean bean = getRewardsBean(message, userId, 0);
			ret.add(bean);
		}
		return ret;
	}

	public static Long calculateTotal(List<PointBean> pntBeans) {
		Long ret = 0l;
		for (PointBean pointBean : pntBeans)
			ret += pointBean.getPointValue();
		return ret;
	}

	public static List<PointBean> fetchAllPointsByUser(Long userId) {
		List<PointBean> ret = new ArrayList<PointBean>();
		WebClient viewPointClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rs/points/get/user/" + userId);
		Collection<? extends PointMessage> points = new ArrayList<PointMessage>(viewPointClient.accept(MediaType.APPLICATION_JSON).getCollection(PointMessage.class));
		viewPointClient.close();
		for (PointMessage message : points) {
			PointBean bean = new PointBean();
			bean.setAllocId(message.getAllocId());
			bean.setComments(message.getComments());
			bean.setEntityId(message.getEntityId());
			bean.setPointId(message.getPointId());
			bean.setPointValue(message.getPointValue());
			bean.setUserId(message.getUserId());
			bean.setCrtdDt(message.getCrtdDt());
			ret.add(bean);
		}
		return ret;
	}

	public static List<ListSelectorBean> fetchAllRewardsCat() {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewRewardsSelectClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rs/rewards/cat/list");
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

	public static List<RewardsBean> fetchAllAvailableRewardsByCat(Long userId, long totalPoints, Integer selRwCatId) {
		fetchAllPointsByUser(userId);
		List<RewardsBean> ret = new ArrayList<RewardsBean>();
		WebClient viewRewardsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rs/rewards/list/cat/" + selRwCatId);
		Collection<? extends RewardsMessage> rewards = new ArrayList<RewardsMessage>(viewRewardsClient.accept(MediaType.APPLICATION_JSON).getCollection(RewardsMessage.class));
		viewRewardsClient.close();
		for (RewardsMessage message : rewards) {
			RewardsBean bean = getRewardsBean(message, userId, totalPoints);
			ret.add(bean);
		}
		return ret;

	}

	public static List<ClaimBean> fetchAllClaimsByUser(Long userId) {
		List<ClaimBean> ret = new ArrayList<ClaimBean>();
		WebClient fetchClaimClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/cls/claim/list/user/" + (userId).longValue());
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
			bean.setClaimComment(message.getClaimComment());
			bean.setUserName(message.getUserName());
			bean.setcStatusName(message.getcStatusName());
			ret.add(bean);
		}
		return ret;
	}

	private static RewardsBean getRewardsBeanAll(RewardsMessage message) {
		RewardsBean bean = new RewardsBean();
		try {
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
			bean.setRwQuantity(message.getRwQuantity());
		} catch (Exception e) {
			logger.error(e, e);
		}
		return bean;
	}

	public static List<RewardsBean> fetchAllRewards() {
		List<RewardsBean> ret = new ArrayList<RewardsBean>();
		WebClient viewRewardsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rs/rewards/list");
		Collection<? extends RewardsMessage> rewards = new ArrayList<RewardsMessage>(viewRewardsClient.accept(MediaType.APPLICATION_JSON).getCollection(RewardsMessage.class));
		viewRewardsClient.close();
		for (RewardsMessage message : rewards) {
			RewardsBean bean = getRewardsBeanAll(message);

			ret.add(bean);
		}
		return ret;
	}

	public static boolean isWishlist(Long rwId, Long userId) {
		WebClient viewRewardsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ts/tag/getByUser/" + rwId + "/4/4/" + userId);
		Collection<? extends TagMessage> rewards = new ArrayList<TagMessage>(viewRewardsClient.accept(MediaType.APPLICATION_JSON).getCollection(TagMessage.class));
		return (rewards.size() > 0);
	}

	public static List<ListSelectorBean> fetchAllClaimStatuses() {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewClaimSelectClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/cls/claim/status/list");
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

	public static List<ChallengeBean> fetchAllChallengesByUser(Long userId) {
		List<ChallengeBean> ret = new ArrayList<ChallengeBean>();
		WebClient fetchChallengeClient = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/cs/challenge/list/user/access/" + userId);
		Collection<? extends ChallengeMessage> challenges = new ArrayList<ChallengeMessage>(fetchChallengeClient.accept(MediaType.APPLICATION_JSON).getCollection(ChallengeMessage.class));
		fetchChallengeClient.close();
		for (ChallengeMessage challengeMessage : challenges) {
			ChallengeBean bean = getChallengeBean(challengeMessage);
			ret.add(bean);
		}
		return ret;
	}

	private static SolutionBean getSolutionBean(SolutionMessage solutionMessage) {
		SolutionBean bean = new SolutionBean();
		try {
			logger.info("----" + solutionMessage.getId());
			bean.setChalId(solutionMessage.getChalId());
			bean.setCatId(solutionMessage.getCatId());
			bean.setCrtdById(solutionMessage.getCrtdById());
			bean.setCrtByName(solutionMessage.getCrtByName());
			bean.setCrtByImgAvail(solutionMessage.isCrtByImgAvail());
			bean.setCrtByImgPath(solutionMessage.getCrtByImgPath());
			bean.setCrtdDt(solutionMessage.getCrtdDt());
			bean.setDesc(solutionMessage.getDesc());
			bean.setId(solutionMessage.getId());
			bean.setStatusId(solutionMessage.getStatusId());
			bean.setTags(solutionMessage.getTags());
			bean.setTitle(solutionMessage.getTitle());
			bean.setSolImgAvl(solutionMessage.isSolImgAvl());
			bean.setSolImg(solutionMessage.getSolImg());
			bean.setSolImgAvl(solutionMessage.isSolImgAvl());
			bean.setBlobUrl(solutionMessage.getBlobUrl());
			bean.setFileName(solutionMessage.getFileName());
			bean.setRevUserId(solutionMessage.getRevUserId());
			bean.setStatusName(solutionMessage.getStatusName());
			bean.setCatName(solutionMessage.getCatName());
			logger.info("------" + solutionMessage.getCrtdDt());
			if (solutionMessage.isSolImgAvl()) {
				File file = new File("/resources/images/" + solutionMessage.getSolImg());
				if (file.exists()) {
					logger.info("image file content type---" + solutionMessage.getContentType() + "-----file name -----" + solutionMessage.getFileName() + "-------imag file ----" + solutionMessage.getSolImg());
					bean.setSolStream(new DefaultStreamedContent(((PortletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/resources/images/" + solutionMessage.getSolImg()), solutionMessage.getContentType(), solutionMessage.getFileName()));
				} else {
					bean.setSolImgAvl(false);
				}

			}
			WebClient fetchDocClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ds/doc/list/" + solutionMessage.getId() + "/ip_solution");
			Collection<? extends FileMessage> res = new ArrayList<FileMessage>(fetchDocClient.accept(MediaType.APPLICATION_JSON).getCollection(FileMessage.class));
			if (res != null && !res.isEmpty()) {
				bean.setSolImgAvl(true);
				for (FileMessage fileMessage : res) {
					FileBean fileBean = new FileBean();
					fileBean.setId(fileMessage.getId());
					fileBean.setLoc(fileMessage.getLoc());
					fileBean.setName(fileMessage.getName());
					fileBean.setSize(fileMessage.getSize());
					fileBean.setType(fileMessage.getType());
					fileBean.setUrl(fileMessage.getUrl());
					bean.getFiles().add(fileBean);
				}
			} else {
				bean.setSolImgAvl(false);
			}
			fetchDocClient.close();
		} catch (Exception e) {
			logger.error("Error :" + e);
		}
		return bean;
	}

	public static List<SolutionBean> fetchAllSolutionsByUser(Long userId) {
		List<SolutionBean> ret = new ArrayList<SolutionBean>();
		WebClient fetchSolutionClient = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ss/solution/list/user/access/" + userId);
		Collection<? extends SolutionMessage> solutions = new ArrayList<SolutionMessage>(fetchSolutionClient.accept(MediaType.APPLICATION_JSON).getCollection(SolutionMessage.class));
		fetchSolutionClient.close();
		for (SolutionMessage solutionMessage : solutions) {
			SolutionBean bean = getSolutionBean(solutionMessage);
			ret.add(bean);
		}
		return ret;
	}

	public static List<ListSelectorBean> fetchAllChallengeCat() {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewChallengeSelectClient = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/cs/challenge/cat/list");
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

	public static List<ListSelectorBean> fetchAllSolutionCat() {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewSolutionSelectClient = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ss/solution/cat/list");
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

	public static List<ListSelectorBean> fetchNextChallengeStatuses(Integer statusId) {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewChallengeSelectClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/cs/challenge/status/list/" + statusId);
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

	public static List<ListSelectorBean> fetchNextSolutionStatuses(Integer statusId) {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewSolutionSelectClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ss/solution/status/list/" + statusId);
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

	public static ResponseMessage modifyTag(Long entityId, Long userId) {
		WebClient addTagClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ts/tag/add");
		TagMessage message = new TagMessage();
		message.setEntityId(entityId);
		message.setTagId(COUNTER.getNextId("IpTag"));
		message.setTeId(4);
		message.setTtId(4);
		message.setUserId(userId);
		message.setDuplicate(false);
		ResponseMessage response = addTagClient.accept(MediaType.APPLICATION_JSON).post(message, ResponseMessage.class);
		addTagClient.close();
		return response;
	}

	private static ClaimBean getClaimBean(ClaimMessage message) {
		ClaimBean bean = new ClaimBean();
		try {
			bean.setClaimCrtdDt(message.getClaimCrtdDt());
			bean.setClaimDesc(message.getClaimDesc());
			bean.setClaimId(message.getClaimId());
			bean.setcStatusId(message.getcStatusId());
			bean.setRewardsId(message.getRewardsId());
			bean.setUserId(message.getUserId());
			bean.setClaimComment(message.getClaimComment());
			bean.setUserName(message.getUserName());
			bean.setcStatusName(message.getcStatusName());
		} catch (Exception e) {
			logger.error(e, e);
		}
		return bean;
	}

	public static List<ClaimBean> fetchAllClaims() {
		List<ClaimBean> ret = new ArrayList<ClaimBean>();
		WebClient fetchClaimClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/cls/claim/list");
		Collection<? extends ClaimMessage> claims = new ArrayList<ClaimMessage>(fetchClaimClient.accept(MediaType.APPLICATION_JSON).getCollection(ClaimMessage.class));
		fetchClaimClient.close();
		for (ClaimMessage message : claims) {
			ClaimBean bean = getClaimBean(message);
			ret.add(bean);
		}
		return ret;
	}

	public static List<ListSelectorBean> fetchNextClaimStatuses(Integer statusId) {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewClaimSelectClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/cls/claim/status/list/" + statusId);
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

	public static List<MetaDataBean> fetchAllStatusList(String entity) {
		List<MetaDataBean> ret = new ArrayList<MetaDataBean>();
		WebClient mDataClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ms/list/" + entity);
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

	public static List<UserBean> fetchAllUsersSortByPG() {
		List<UserBean> ret = new ArrayList<UserBean>();
		WebClient viewUsersClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/list/sort/pg");
		Collection<? extends UserMessage> users = new ArrayList<UserMessage>(viewUsersClient.accept(MediaType.APPLICATION_JSON).getCollection(UserMessage.class));
		viewUsersClient.close();
		for (UserMessage userMessage : users) {
			UserBean bean = getUserBean(userMessage);
			ret.add(bean);
		}
		return ret;
	}

	public static List<UserBean> fetchAdminUser() {
		List<UserBean> ret = new ArrayList<UserBean>();
		WebClient userByIdClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/get/" + 0l);
		UserMessage userMessage = userByIdClient.accept(MediaType.APPLICATION_JSON).get(UserMessage.class);
		userByIdClient.close();
		UserBean bean = getUserBean(userMessage);
		ret.add(bean);
		return ret;
	}

	private static FunctionBean getFunctionBean(FunctionMessage functionMessage) {
		FunctionBean bean = new FunctionBean();
		try {
			bean.setFuncId(functionMessage.getFuncId());
			bean.setFuncName(functionMessage.getFuncName());
			bean.getGroupIdList().clear();
			for (Long id : functionMessage.getGroupIdList())
				if (id != null)
					bean.getGroupIdList().add(id);
		} catch (Exception e) {
			logger.error(e, e);
		}
		return bean;
	}

	public static List<FunctionBean> fetchAllFunctions() {
		List<FunctionBean> ret = new ArrayList<FunctionBean>();
		WebClient viewFunctionsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/func/list");
		Collection<? extends FunctionMessage> functions = new ArrayList<FunctionMessage>(viewFunctionsClient.accept(MediaType.APPLICATION_JSON).getCollection(FunctionMessage.class));
		viewFunctionsClient.close();
		for (FunctionMessage functionMessage : functions) {
			FunctionBean bean = getFunctionBean(functionMessage);
			ret.add(bean);
		}
		return ret;
	}

	public static List<FunctionBean> fetchAllFunctionsByGroup(Long groupId) {
		List<FunctionBean> ret = new ArrayList<FunctionBean>();
		WebClient viewFunctionsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/func/group/list/" + groupId);
		Collection<? extends FunctionMessage> functions = new ArrayList<FunctionMessage>(viewFunctionsClient.accept(MediaType.APPLICATION_JSON).getCollection(FunctionMessage.class));
		viewFunctionsClient.close();
		for (FunctionMessage functionMessage : functions) {
			FunctionBean bean = getFunctionBean(functionMessage);
			ret.add(bean);
		}
		return ret;
	}

	private static NewsBean getNewsBean(NewsMessage message) {
		NewsBean bean = new NewsBean();
		try {
			bean.setnId(message.getnId());
			bean.setnContent(message.getContent());
			bean.setEndDate(message.getEndDate());
			bean.setStartDate(message.getStartDate());
			bean.setnTitle(message.getnTitle());
			bean.setNewsUrl(message.getNewsUrl());
			bean.setNwImgAvail(message.isNwImgAvail());
			bean.setBlobUrl(message.getBlobUrl());
			bean.setFileName(message.getFileName());
			bean.setNewsCrtdDt(message.getNewsCrtdDt());
		} catch (Exception e) {
			logger.error(e, e);
		}
		return bean;
	}

	public static List<NewsBean> fetchAllNews() {
		List<NewsBean> ret = new ArrayList<NewsBean>();
		logger.info("Control handled in fetchAllNews() ");
		try {
			WebClient fetchNewsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ns/news/list");
			Collection<? extends NewsMessage> news = new ArrayList<NewsMessage>(fetchNewsClient.accept(MediaType.APPLICATION_JSON).getCollection(NewsMessage.class));
			fetchNewsClient.close();
			for (NewsMessage message : news) {
				NewsBean bean = getNewsBean(message);
				ret.add(bean);
			}
		} catch (Exception e) {
			logger.error(e, e);
			logger.error("Error in fetching data : " + e.getMessage());
		}
		logger.info("News data displaying from List: " + ret);
		return ret;
	}

	private static NotificationBean getNotificationBean(NotificationMessage notificationMessage) {
		NotificationBean bean = new NotificationBean();
		try {
			bean.setNotifAttach(notificationMessage.getNotifAttach());
			bean.setNotifBody(notificationMessage.getNotifBody());
			bean.setNotifCrtdDate(notificationMessage.getNotifCrtdDate());
			bean.setNotifEntityId(notificationMessage.getNotifEntityId());
			bean.setNotifEntityTblName(notificationMessage.getNotifEntityTblName());
			bean.setNotifId(notificationMessage.getNotifId());
			bean.setNotifStatus(notificationMessage.getNotifStatus());
			bean.setNotifSubject(notificationMessage.getNotifSubject());
			bean.setGroupIdList(getIdsFromArray(notificationMessage.getGroupIdList()));
		} catch (Exception e) {
			logger.error(e, e);
		}
		return bean;
	}

	public static List<NotificationBean> fetchAllNotifications(Long userId) {
		List<NotificationBean> ret = new ArrayList<NotificationBean>();
		WebClient fetchNotifClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/nos/notif/list/" + userId);
		Collection<? extends NotificationMessage> notifications = new ArrayList<NotificationMessage>(fetchNotifClient.accept(MediaType.APPLICATION_JSON).getCollection(NotificationMessage.class));
		fetchNotifClient.close();
		for (NotificationMessage notificationMessage : notifications) {
			NotificationBean bean = getNotificationBean(notificationMessage);
			ret.add(bean);
		}
		return ret;
	}

	public static List<MetaDataBean> fetchAllSecQ() {
		List<MetaDataBean> ret = new ArrayList<MetaDataBean>();
		WebClient metaDataClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ms/list/IpSecqList");
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

	public static List<MetaDataBean> fetchAllNonAllocStatus(String entity) {
		List<MetaDataBean> ret = new ArrayList<MetaDataBean>();
		WebClient mDataClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ms/list/non/" + entity);
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

	public static List<AllocationBean> fetchAllAllocationsByEntity(String entity) {
		List<AllocationBean> ret = new ArrayList<AllocationBean>();
		WebClient allocCLient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rs/alloc/list/" + entity);
		Collection<? extends AllocationMessage> messages = new ArrayList<AllocationMessage>(allocCLient.accept(MediaType.APPLICATION_JSON).getCollection(AllocationMessage.class));
		for (AllocationMessage message : messages) {
			AllocationBean bean = new AllocationBean();
			bean.setAllocDesc(message.getAllocDesc());
			bean.setAllocEntity(message.getAllocEntity());
			bean.setAllocId(message.getAllocId());
			bean.setAllocStatusId(message.getAllocStatusId());
			bean.setAllocVal(message.getAllocVal());
			bean.setAllocCrtdDt(message.getAllocCrtdDt());
			ret.add(bean);
		}
		return ret;
	}

	private static String[] toStringArray(Long[] val) {
		String[] ret = new String[val.length];
		for (int i = 0; i < val.length; i++)
			ret[i] = val[i].toString();
		return ret;
	}

	public static List<ChallengeBean> fetchAllChallenges() {
		List<ChallengeBean> ret = new ArrayList<ChallengeBean>();
		WebClient fetchChallengeClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/cs/challenge/list");
		Collection<? extends ChallengeMessage> challenges = new ArrayList<ChallengeMessage>(fetchChallengeClient.accept(MediaType.APPLICATION_JSON).getCollection(ChallengeMessage.class));
		fetchChallengeClient.close();
		for (ChallengeMessage challengeMessage : challenges) {
			ChallengeBean bean = getChallengeBean(challengeMessage);
			ret.add(bean);
		}
		return ret;
	}

	public static ChallengeBean fetchChallengeById(Long chalId) {
		logger.debug("Control handled in fecthChallengeById() ");
		WebClient fetchChallengeClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/cs/challenge/get/" + chalId);
		ChallengeMessage challengeMessage = fetchChallengeClient.accept(MediaType.APPLICATION_JSON).get(ChallengeMessage.class);
		fetchChallengeClient.close();
		ChallengeBean bean = getChallengeBean(challengeMessage);
		logger.info("Before returning challenge bean: " + bean);
		return bean;
	}

	public static List<ChallengeBean> fetchAllAvailableChallenges() {
		List<ChallengeBean> ret = new ArrayList<ChallengeBean>();
		WebClient fetchChallengeClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/cs/challenge/list/status/8");
		Collection<? extends ChallengeMessage> challenges = new ArrayList<ChallengeMessage>(fetchChallengeClient.accept(MediaType.APPLICATION_JSON).getCollection(ChallengeMessage.class));
		fetchChallengeClient.close();
		for (ChallengeMessage challengeMessage : challenges) {
			ChallengeBean bean = getChallengeBean(challengeMessage);
			ret.add(bean);
		}
		return ret;
	}

	public static List<ChallengeBean> fetchAllChallengesCreatedByUser(Long userId) {
		List<ChallengeBean> ret = new ArrayList<ChallengeBean>();
		WebClient fetchChallengeClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/cs/challenge/list/user/created/" + userId);
		Collection<? extends ChallengeMessage> challenges = new ArrayList<ChallengeMessage>(fetchChallengeClient.accept(MediaType.APPLICATION_JSON).getCollection(ChallengeMessage.class));
		fetchChallengeClient.close();
		for (ChallengeMessage challengeMessage : challenges) {
			ChallengeBean bean = getChallengeBean(challengeMessage);
			ret.add(bean);
		}
		return ret;
	}

	public static List<ChallengeBean> fetchAllChallengesByStatusIdUserId(Long userId, Integer status) {
		List<ChallengeBean> ret = new ArrayList<ChallengeBean>();
		WebClient fetchChallengeClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/cs/challenge/list/status/" + status + "/user/" + userId);
		Collection<? extends ChallengeMessage> challenges = new ArrayList<ChallengeMessage>(fetchChallengeClient.accept(MediaType.APPLICATION_JSON).getCollection(ChallengeMessage.class));
		fetchChallengeClient.close();
		for (ChallengeMessage challengeMessage : challenges) {
			ChallengeBean bean = getChallengeBean(challengeMessage);
			ret.add(bean);
		}
		return ret;
	}

	public static List<SolutionBean> fetchAllSolutions() {
		List<SolutionBean> ret = new ArrayList<SolutionBean>();
		WebClient fetchSolutionClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ss/solution/list");
		Collection<? extends SolutionMessage> solutions = new ArrayList<SolutionMessage>(fetchSolutionClient.accept(MediaType.APPLICATION_JSON).getCollection(SolutionMessage.class));
		fetchSolutionClient.close();
		for (SolutionMessage solutionMessage : solutions) {
			SolutionBean bean = getSolutionBean(solutionMessage);
			ret.add(bean);
		}
		return ret;
	}

	public static List<SolutionBean> fetchAllSolutionsCreatedByUser(Long userId) {
		List<SolutionBean> ret = new ArrayList<SolutionBean>();
		WebClient fetchSolutionClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ss/solution/list/user/created/" + userId);
		Collection<? extends SolutionMessage> solutions = new ArrayList<SolutionMessage>(fetchSolutionClient.accept(MediaType.APPLICATION_JSON).getCollection(SolutionMessage.class));
		fetchSolutionClient.close();
		for (SolutionMessage solutionMessage : solutions) {
			SolutionBean bean = getSolutionBean(solutionMessage);
			ret.add(bean);
		}
		logger.info("After getting solutions");
		return ret;
	}

	public static List<SolutionBean> fetchAllSolutionsByStatusIdUserId(Long userId, Integer status) {
		List<SolutionBean> ret = new ArrayList<SolutionBean>();
		WebClient fetchSolutionClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ss/solution/list/status/" + status + "/user/" + userId);
		Collection<? extends SolutionMessage> solutions = new ArrayList<SolutionMessage>(fetchSolutionClient.accept(MediaType.APPLICATION_JSON).getCollection(SolutionMessage.class));
		fetchSolutionClient.close();
		for (SolutionMessage solutionMessage : solutions) {
			SolutionBean bean = getSolutionBean(solutionMessage);
			ret.add(bean);
		}
		return ret;
	}

	public static List<SolutionBean> fetchAllReviewSolutionsByUser(Long userId) {
		List<SolutionBean> ret = new ArrayList<SolutionBean>();
		WebClient fetchSolutionClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ss/solution/list/reviewStatus/" + userId);
		Collection<? extends SolutionMessage> solutions = new ArrayList<SolutionMessage>(fetchSolutionClient.accept(MediaType.APPLICATION_JSON).getCollection(SolutionMessage.class));
		fetchSolutionClient.close();
		for (SolutionMessage solutionMessage : solutions) {
			SolutionBean bean = getSolutionBean(solutionMessage);
			ret.add(bean);
		}
		return ret;
	}

	public static List<SolutionBean> fetchAllSolutionsByChal(Long chalId) {
		logger.debug("Control handled in fetchAllSolutionsByChal method ");
		List<SolutionBean> ret = new ArrayList<SolutionBean>();
		try {
			WebClient fetchSolutionClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ss/solution/list/chal/" + chalId);
			Collection<? extends SolutionMessage> solutions = new ArrayList<SolutionMessage>(fetchSolutionClient.accept(MediaType.APPLICATION_JSON).getCollection(SolutionMessage.class));
			fetchSolutionClient.close();
			logger.info("Before getting all solutions in controller");
			for (SolutionMessage solutionMessage : solutions) {
				SolutionBean bean = getSolutionBean(solutionMessage);
				logger.info("Solution: Id-" + solutionMessage.getId() + " name-" + solutionMessage.getTitle() + " image-" + solutionMessage.getSolImg());
				ret.add(bean);
			}
		} catch (Exception e) {
			logger.error(e, e);
			logger.error("Error in fetchAllSolutionsByChal() : " + e.getMessage());
		}
		return ret;
	}

	public static List<MetaDataBean> fetchAllMetadata(String table) {
		List<MetaDataBean> ret = new ArrayList<MetaDataBean>();
		WebClient mDataClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ms/list/" + table);
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

	private static TagBean getTagBean(TagMessage msg) {
		TagBean bean = new TagBean();
		try {
			bean.setTagText(msg.getTagText());
			bean.setUsrScreenName(msg.getUsrScreenName());
			bean.setTagDate(msg.getTagDate());
			bean.setTagId(msg.getTagId());
			bean.setImgAvail(msg.isImgAvail());
			bean.setBlobUrl(msg.getBlobUrl());
			bean.setFileName(msg.getFileName());
		} catch (Exception e) {
			logger.error(e, e);
		}
		return bean;
	}

	public static List<TagBean> fetchAllBuildOns(Long entityId, int entityType) {
		WebClient fetchIdeaBuildOnsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ts/tag/get/" + entityId + "/" + entityType + "/3");
		Collection<? extends TagMessage> msgs = new ArrayList<TagMessage>(fetchIdeaBuildOnsClient.accept(MediaType.APPLICATION_JSON).getCollection(TagMessage.class));
		fetchIdeaBuildOnsClient.close();
		List<TagBean> ret = new ArrayList<TagBean>();
		for (TagMessage msg : msgs) {
			TagBean bean = getTagBean(msg);
			ret.add(bean);
		}
		return ret;
	}

	public static TagCloudModel fetchAllBuildonLikes(Long entityId, int entityType) {
		TagCloudModel likes = new DefaultTagCloudModel();
		WebClient fetchBuildonLikesClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ts/tag/get/" + entityId + "/" + entityType + "/1");
		Collection<? extends TagMessage> likeList = new ArrayList<TagMessage>(fetchBuildonLikesClient.accept(MediaType.APPLICATION_JSON).getCollection(TagMessage.class));
		fetchBuildonLikesClient.close();
		for (TagMessage tagMessage : likeList)
			likes.addTag(new DefaultTagCloudItem(tagMessage.getUsrScreenName(), 1));
		return likes;
	}

	public static List<TagBean> fetchAllBuildonComments(Long entityId, int entityType) {
		WebClient fetchBuildonCommentsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ts/tag/get/" + entityId + "/" + entityType + "/2");
		Collection<? extends TagMessage> msgs = new ArrayList<TagMessage>(fetchBuildonCommentsClient.accept(MediaType.APPLICATION_JSON).getCollection(TagMessage.class));
		fetchBuildonCommentsClient.close();
		List<TagBean> ret = new ArrayList<TagBean>();
		for (TagMessage msg : msgs) {
			TagBean bean = getTagBean(msg);
			ret.add(bean);
		}
		return ret;
	}

	public static Integer fetchReviewStatusCnt(Long entityId, String entityNm) {
		WebClient fetchReviewGroupsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rvs/review/list/cnt/" + entityId + "/" + entityNm);
		Integer msgs = fetchReviewGroupsClient.accept(MediaType.APPLICATION_JSON).get(Integer.class);
		return msgs;
	}

	public static List<ReviewBean> fetchReviewGroups(Long entityId, String entityNm) {
		List<ReviewBean> ret = new ArrayList<ReviewBean>();
		WebClient fetchReviewGroupsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rvs/review/list/all/" + entityId + "/" + entityNm);
		Collection<? extends ReviewMessage> msgs = new ArrayList<ReviewMessage>(fetchReviewGroupsClient.accept(MediaType.APPLICATION_JSON).getCollection(ReviewMessage.class));
		for (ReviewMessage msg : msgs) {
			ReviewBean bean = new ReviewBean();
			bean.setEntityId(msg.getEntityId());
			bean.setStatusId(msg.getStatusId());
			bean.setTblNm(msg.getTblNm());
			bean.setGroupId(toStringArray(msg.getGroupId()));
			ret.add(bean);
		}
		fetchReviewGroupsClient.close();
		return ret;

	}
}