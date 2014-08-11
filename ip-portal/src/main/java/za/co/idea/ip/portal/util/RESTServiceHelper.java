package za.co.idea.ip.portal.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import za.co.idea.ip.portal.bean.GroupBean;
import za.co.idea.ip.portal.bean.IdeaBean;
import za.co.idea.ip.portal.bean.ListSelectorBean;
import za.co.idea.ip.portal.bean.UserBean;
import za.co.idea.ip.ws.bean.GroupMessage;
import za.co.idea.ip.ws.bean.IdeaMessage;
import za.co.idea.ip.ws.bean.MetaDataMessage;
import za.co.idea.ip.ws.bean.UserMessage;
import za.co.idea.ip.ws.util.CustomObjectMapper;

public class RESTServiceHelper {
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-portal");

	public static WebClient createCustomClient(String url) {
		WebClient client = WebClient.create(url, Collections.singletonList(new JacksonJsonProvider(new CustomObjectMapper())));
		client.header("Content-Type", "application/json");
		client.header("Accept", "application/json");
		return client;
	}

	// Users Section
	public static List<UserBean> fetchAllUsers() {
		List<UserBean> ret = new ArrayList<UserBean>();
		WebClient viewUsersClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/list/sort/pg");
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

	// Group Section
	public static List<GroupBean> fetchAllGroups() {
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

	public static GroupBean getGroupById(Long pGrpId) {
		GroupBean bean = new GroupBean();
		WebClient groupByIdClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/group/get/" + pGrpId);
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

	// Ideas Section
	public List<IdeaBean> fetchAllIdeas() {
		List<IdeaBean> ret = new ArrayList<IdeaBean>();
		WebClient fetchIdeaClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/is/idea/list");
		Collection<? extends IdeaMessage> ideas = new ArrayList<IdeaMessage>(fetchIdeaClient.accept(MediaType.APPLICATION_JSON).getCollection(IdeaMessage.class));
		fetchIdeaClient.close();
		for (IdeaMessage ideaMessage : ideas) {
			IdeaBean bean = new IdeaBean();
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
			ret.add(bean);
		}
		return ret;
	}

	public static List<IdeaBean> fetchAllIdeasByUser(long userId) {
		List<IdeaBean> ret = new ArrayList<IdeaBean>();
		WebClient fetchIdeaClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/is/idea/list/user/access/" + userId);
		Collection<? extends IdeaMessage> ideas = new ArrayList<IdeaMessage>(fetchIdeaClient.accept(MediaType.APPLICATION_JSON).getCollection(IdeaMessage.class));
		fetchIdeaClient.close();
		for (IdeaMessage ideaMessage : ideas) {
			IdeaBean bean = new IdeaBean();
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
			ret.add(bean);
		}
		return ret;
	}

	public static List<IdeaBean> fetchAllIdeasCreatedByUser(long userId) {
		List<IdeaBean> ret = new ArrayList<IdeaBean>();
		WebClient fetchIdeaClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/is/idea/list/user/created/" + userId);
		Collection<? extends IdeaMessage> ideas = new ArrayList<IdeaMessage>(fetchIdeaClient.accept(MediaType.APPLICATION_JSON).getCollection(IdeaMessage.class));
		fetchIdeaClient.close();
		for (IdeaMessage ideaMessage : ideas) {
			IdeaBean bean = new IdeaBean();
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
			ret.add(bean);
		}
		return ret;
	}

	public static List<IdeaBean> fetchAllIdeasByStatus(Integer status) {
		List<IdeaBean> ret = new ArrayList<IdeaBean>();
		WebClient fetchIdeaClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/is/idea/list/status/" + status);
		Collection<? extends IdeaMessage> ideas = new ArrayList<IdeaMessage>(fetchIdeaClient.accept(MediaType.APPLICATION_JSON).getCollection(IdeaMessage.class));
		fetchIdeaClient.close();
		for (IdeaMessage ideaMessage : ideas) {
			IdeaBean bean = new IdeaBean();
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
			ret.add(bean);
		}
		return ret;
	}

	public static List<IdeaBean> fetchAllIdeasByStatusIdUserId(Integer status, long userId) {
		List<IdeaBean> ret = new ArrayList<IdeaBean>();
		WebClient fetchIdeaClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/is/idea/list/status/" + status + "/user/" + userId);
		Collection<? extends IdeaMessage> ideas = new ArrayList<IdeaMessage>(fetchIdeaClient.accept(MediaType.APPLICATION_JSON).getCollection(IdeaMessage.class));
		fetchIdeaClient.close();
		for (IdeaMessage ideaMessage : ideas) {
			IdeaBean bean = new IdeaBean();
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

	public static List<ListSelectorBean> fetchNextIdeaStatuses(Integer status) {
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

}
