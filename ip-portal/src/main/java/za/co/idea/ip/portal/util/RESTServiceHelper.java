package za.co.idea.ip.portal.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import za.co.idea.ip.portal.bean.ChallengeBean;
import za.co.idea.ip.portal.bean.ClaimBean;
import za.co.idea.ip.portal.bean.GroupBean;
import za.co.idea.ip.portal.bean.IdeaBean;
import za.co.idea.ip.portal.bean.ListSelectorBean;
import za.co.idea.ip.portal.bean.PointBean;
import za.co.idea.ip.portal.bean.RewardsBean;
import za.co.idea.ip.portal.bean.SolutionBean;
import za.co.idea.ip.portal.bean.UserBean;
import za.co.idea.ip.ws.bean.ChallengeMessage;
import za.co.idea.ip.ws.bean.ClaimMessage;
import za.co.idea.ip.ws.bean.GroupMessage;
import za.co.idea.ip.ws.bean.IdeaMessage;
import za.co.idea.ip.ws.bean.MetaDataMessage;
import za.co.idea.ip.ws.bean.PointMessage;
import za.co.idea.ip.ws.bean.ResponseMessage;
import za.co.idea.ip.ws.bean.RewardsMessage;
import za.co.idea.ip.ws.bean.SolutionMessage;
import za.co.idea.ip.ws.bean.TagMessage;
import za.co.idea.ip.ws.bean.UserMessage;
import za.co.idea.ip.ws.util.CustomObjectMapper;

public class RESTServiceHelper {
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-portal");
	private static final IdNumberGen COUNTER = new IdNumberGen();

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
			bean.setBlobUrl(ideaMessage.getBlobUrl());
			bean.setFileName(ideaMessage.getFileName());
			bean.setImgAvail(ideaMessage.isImgAvail());
			bean.setStatusName(ideaMessage.getStatusName());
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
			bean.setBlobUrl(ideaMessage.getBlobUrl());
			bean.setFileName(ideaMessage.getFileName());
			bean.setImgAvail(ideaMessage.isImgAvail());
			bean.setStatusName(ideaMessage.getStatusName());
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
			bean.setBlobUrl(ideaMessage.getBlobUrl());
			bean.setFileName(ideaMessage.getFileName());
			bean.setImgAvail(ideaMessage.isImgAvail());
			bean.setStatusName(ideaMessage.getStatusName());
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
			bean.setBlobUrl(ideaMessage.getBlobUrl());
			bean.setFileName(ideaMessage.getFileName());
			bean.setImgAvail(ideaMessage.isImgAvail());
			bean.setStatusName(ideaMessage.getStatusName());
			bean.setRevUserId(ideaMessage.getRevUserId());
			ret.add(bean);
		}
		return ret;
	}

	public static List<IdeaBean> fetchAllReviewIdeasByUserId(long userId) {
		List<IdeaBean> ret = new ArrayList<IdeaBean>();
		WebClient fetchIdeaClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/is/idea/list/reviewStatus/user/" + userId);
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
			bean.setBlobUrl(ideaMessage.getBlobUrl());
			bean.setFileName(ideaMessage.getFileName());
			bean.setImgAvail(ideaMessage.isImgAvail());
			bean.setStatusName(ideaMessage.getStatusName());
			bean.setRevUserId(ideaMessage.getRevUserId());
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
			bean.setBlobUrl(ideaMessage.getBlobUrl());
			bean.setFileName(ideaMessage.getFileName());
			bean.setImgAvail(ideaMessage.isImgAvail());
			bean.setStatusName(ideaMessage.getStatusName());
			bean.setRevUserId(ideaMessage.getRevUserId());
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

	public static List<ListSelectorBean> fetchAllReviewIdeaStatuses() {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		ListSelectorBean bean = new ListSelectorBean();
		bean.setId(5);
		bean.setDesc("Approved");
		ret.add(bean);
		bean = new ListSelectorBean();
		bean.setId(8);
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

	public static List<ChallengeBean> fetchAllReviewChallengesByUser(Long userId) {
		List<ChallengeBean> ret = new ArrayList<ChallengeBean>();
		WebClient fetchChallengeClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/cs/challenge/list/reviewStatus/" + userId);
		Collection<? extends ChallengeMessage> challenges = new ArrayList<ChallengeMessage>(fetchChallengeClient.accept(MediaType.APPLICATION_JSON).getCollection(ChallengeMessage.class));
		fetchChallengeClient.close();
		for (ChallengeMessage challengeMessage : challenges) {
			ChallengeBean bean = new ChallengeBean();
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
			ret.add(bean);
		}
		return ret;
	}

	public static List<ListSelectorBean> fetchAllReviewChallengeNextStatuses() {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		ListSelectorBean bean = new ListSelectorBean();
		bean.setId(3);
		bean.setDesc("Approve");
		ret.add(bean);
		bean = new ListSelectorBean();
		bean.setId(6);
		bean.setDesc("Reject");
		ret.add(bean);
		return ret;
	}

	public static List<ListSelectorBean> fetchAllReviewSolutionStatuses() {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		ListSelectorBean bean = new ListSelectorBean();
		bean.setId(5);
		bean.setDesc("Approved");
		ret.add(bean);

		bean = new ListSelectorBean();
		bean.setId(8);
		bean.setDesc("Closed");
		ret.add(bean);
		return ret;
	}

	public static List<RewardsBean> fetchAllRewardsByUser(Long userId, long totalPoints) {
		fetchAllPointsByUser(userId);
		List<RewardsBean> ret = new ArrayList<RewardsBean>();
		WebClient viewRewardsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rs/rewards/list/" + userId);
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
			bean.setRwTaggable(isWishlist(message.getRwId(), userId));
			bean.setRwClaimable(totalPoints >= message.getRwValue() && message.getRwQuantity() > 0);
			ret.add(bean);
		}
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

	public static List<RewardsBean> fetchAllRewards() {
		List<RewardsBean> ret = new ArrayList<RewardsBean>();
		WebClient viewRewardsClient = createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/rs/rewards/list");
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
			bean.setRwQuantity(message.getRwQuantity());
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
			ChallengeBean bean = new ChallengeBean();
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
			bean.setGroupIdList(FacesContextHelper.getIdsFromArray(challengeMessage.getGroupIdList()));
			bean.setCrtByImgAvail(challengeMessage.isCrtByImgAvail());
			bean.setCrtByImgPath(challengeMessage.getCrtByImgPath());
			bean.setCrtdByName(challengeMessage.getCrtdByName());
			bean.setBlobUrl(challengeMessage.getBlobUrl());
			bean.setFileName(challengeMessage.getFileName());
			bean.setImgAvail(challengeMessage.isImgAvail());
			bean.setRevUserId(challengeMessage.getRevUserId());
			ret.add(bean);
		}
		return ret;
	}

	public static List<SolutionBean> fetchAllSolutionsByUser(Long userId) {
		List<SolutionBean> ret = new ArrayList<SolutionBean>();
		WebClient fetchSolutionClient = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ss/solution/list/user/access/" + userId);
		Collection<? extends SolutionMessage> solutions = new ArrayList<SolutionMessage>(fetchSolutionClient.accept(MediaType.APPLICATION_JSON).getCollection(SolutionMessage.class));
		fetchSolutionClient.close();
		for (SolutionMessage solutionMessage : solutions) {
			SolutionBean bean = new SolutionBean();
			bean.setChalId(solutionMessage.getChalId());
			bean.setCatId(solutionMessage.getCatId());
			bean.setCrtdById(solutionMessage.getCrtdById());
			bean.setCrtByName(solutionMessage.getCrtByName());
			bean.setCrtdDt(solutionMessage.getCrtdDt());
			bean.setDesc(solutionMessage.getDesc());
			bean.setId(solutionMessage.getId());
			bean.setStatusId(solutionMessage.getStatusId());
			bean.setTags(solutionMessage.getTags());
			bean.setTitle(solutionMessage.getTitle());
			bean.setCrtByImgAvail(solutionMessage.isCrtByImgAvail());
			bean.setCrtByImgPath(solutionMessage.getCrtByImgPath());
			bean.setSolImgAvl(solutionMessage.isSolImgAvl());
			bean.setBlobUrl(solutionMessage.getBlobUrl());
			bean.setFileName(solutionMessage.getFileName());
			bean.setRevUserId(solutionMessage.getRevUserId());
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
}