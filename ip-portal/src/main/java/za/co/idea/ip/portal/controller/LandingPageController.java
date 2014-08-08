package za.co.idea.ip.portal.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import za.co.idea.ip.portal.bean.ChallengeBean;
import za.co.idea.ip.portal.bean.IdeaBean;
import za.co.idea.ip.portal.bean.ListSelectorBean;
import za.co.idea.ip.portal.bean.SolutionBean;
import za.co.idea.ip.portal.bean.UserBean;
import za.co.idea.ip.portal.util.FacesContextHelper;
import za.co.idea.ip.portal.util.RESTServiceHelper;
import za.co.idea.ip.ws.bean.ChallengeMessage;
import za.co.idea.ip.ws.bean.MetaDataMessage;
import za.co.idea.ip.ws.bean.SolutionMessage;
import za.co.idea.ip.ws.bean.UserMessage;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;

@ManagedBean(name = "landingPageController")
@SessionScoped
public class LandingPageController implements Serializable {

	private static final Logger logger = Logger.getLogger(LandingPageController.class);
	private static final long serialVersionUID = -440824165553001559L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-portal");
	private List<UserBean> admUsers;
	private List<ListSelectorBean> ideaCats;
	private List<ListSelectorBean> ideaStatuses;
	private List<IdeaBean> viewIdeas;
	private IdeaBean ideaBean;
	private List<ListSelectorBean> challengeCats;
	private List<ListSelectorBean> challengeStatuses;
	private List<ChallengeBean> viewChallenges;
	private ChallengeBean challengeBean;
	private List<ListSelectorBean> solutionCats;
	private List<ListSelectorBean> solutionStatuses;
	private List<SolutionBean> viewSolutions;
	private SolutionBean solutionBean;
	private Long userId;
	private boolean showIdeas;
	private boolean showChals;
	private boolean showSols;
	private String toView;

	public void initializePage() {
		try {
			PortletRequest request = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			User user = (User) request.getAttribute(WebKeys.USER);
			WebClient client = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/verify/" + user.getScreenName());
			UserMessage message = client.accept(MediaType.APPLICATION_JSON).get(UserMessage.class);
			userId = message.getuId();
			admUsers = RESTServiceHelper.fetchAllUsers();
			viewIdeas = RESTServiceHelper.fetchAllIdeasByUser(userId);
			ideaCats = fetchAllIdeaCat();
			ideaStatuses = fetchAllIdeaStatuses();
			viewChallenges = fetchAllChallengesByUser();
			challengeCats = fetchAllChallengeCat();
			challengeStatuses = fetchAllChallengeStatuses();
			viewSolutions = fetchAllSolutionsByUser();
			solutionCats = fetchAllSolutionCat();
			solutionStatuses = fetchAllSolutionStatuses();
			showIdeas = true;
			showChals = false;
			showSols = false;
			if (toView != null && Integer.valueOf(toView) != -1) {
				switch (Integer.valueOf(toView)) {
				case 1:
					showIdeas = true;
					showChals = false;
					showSols = false;
					break;
				case 2:
					showIdeas = false;
					showChals = true;
					showSols = false;
					break;
				case 3:
					showIdeas = false;
					showChals = false;
					showSols = true;
					break;
				default:
					showIdeas = true;
					showChals = false;
					showSols = false;
				}
			}

		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void changeIdea() {
		try {
			viewIdeas = RESTServiceHelper.fetchAllIdeasByUser(userId);
			ideaCats = fetchAllIdeaCat();
			admUsers = RESTServiceHelper.fetchAllUsers();
			ideaStatuses = fetchAllIdeaStatuses();
			showIdeas = true;
			showChals = false;
			showSols = false;
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void changeChallenge() {
		try {
			viewChallenges = fetchAllChallengesByUser();
			challengeCats = fetchAllChallengeCat();
			admUsers = RESTServiceHelper.fetchAllUsers();
			challengeStatuses = fetchAllChallengeStatuses();
			showIdeas = false;
			showChals = true;
			showSols = false;
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void changeSolution() {
		try {
			viewSolutions = fetchAllSolutionsByUser();
			solutionCats = fetchAllSolutionCat();
			admUsers = RESTServiceHelper.fetchAllUsers();
			solutionStatuses = fetchAllSolutionStatuses();
			showIdeas = false;
			showChals = false;
			showSols = true;
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	private List<ChallengeBean> fetchAllChallengesByUser() {
		List<ChallengeBean> ret = new ArrayList<ChallengeBean>();
		WebClient fetchChallengeClient = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/cs/challenge/list/user/access/" + userId);
		// WebClient fetchChallengeClient =
		// RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/cs/challenge/list/user/access/0");
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
			ret.add(bean);
		}
		return ret;
	}

	private List<SolutionBean> fetchAllSolutionsByUser() {
		List<SolutionBean> ret = new ArrayList<SolutionBean>();
		WebClient fetchSolutionClient = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ss/solution/list/user/access/" + userId);
		// WebClient fetchSolutionClient =
		// RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/ss/solution/list/user/access/0");
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
			ret.add(bean);
		}
		return ret;
	}

	private List<ListSelectorBean> fetchAllIdeaCat() {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewIdeaSelectClient = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/is/idea/cat/list");
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

	private List<ListSelectorBean> fetchAllChallengeCat() {
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

	private List<ListSelectorBean> fetchAllSolutionCat() {
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

	private List<ListSelectorBean> fetchAllIdeaStatuses() {
		List<ListSelectorBean> ret = new ArrayList<ListSelectorBean>();
		WebClient viewIdeaSelectClient = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/is/idea/status/list");
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

	private List<ListSelectorBean> fetchAllChallengeStatuses() {
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

	private List<ListSelectorBean> fetchAllSolutionStatuses() {
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

	public List<UserBean> getAdmUsers() {
		if (admUsers == null)
			admUsers = new ArrayList<UserBean>();
		return admUsers;
	}

	public void setAdmUsers(List<UserBean> admUsers) {
		this.admUsers = admUsers;
	}

	public List<ListSelectorBean> getIdeaCats() {
		if (ideaCats == null)
			ideaCats = new ArrayList<ListSelectorBean>();
		return ideaCats;
	}

	public void setIdeaCats(List<ListSelectorBean> ideaCats) {
		this.ideaCats = ideaCats;
	}

	public List<ListSelectorBean> getIdeaStatuses() {
		if (ideaStatuses == null)
			ideaStatuses = new ArrayList<ListSelectorBean>();
		return ideaStatuses;
	}

	public void setIdeaStatuses(List<ListSelectorBean> ideaStatuses) {
		this.ideaStatuses = ideaStatuses;
	}

	public List<IdeaBean> getViewIdeas() {
		if (viewIdeas == null)
			viewIdeas = new ArrayList<IdeaBean>();
		return viewIdeas;
	}

	public void setViewIdeas(List<IdeaBean> viewIdeas) {
		this.viewIdeas = viewIdeas;
	}

	public List<ListSelectorBean> getChallengeCats() {
		if (challengeCats == null)
			challengeCats = new ArrayList<ListSelectorBean>();
		return challengeCats;
	}

	public void setChallengeCats(List<ListSelectorBean> challengeCats) {
		this.challengeCats = challengeCats;
	}

	public List<ListSelectorBean> getChallengeStatuses() {
		if (challengeStatuses == null)
			challengeStatuses = new ArrayList<ListSelectorBean>();
		return challengeStatuses;
	}

	public void setChallengeStatuses(List<ListSelectorBean> challengeStatuses) {
		this.challengeStatuses = challengeStatuses;
	}

	public List<ChallengeBean> getViewChallenges() {
		if (viewChallenges == null)
			viewChallenges = new ArrayList<ChallengeBean>();
		return viewChallenges;
	}

	public void setViewChallenges(List<ChallengeBean> viewChallenges) {
		this.viewChallenges = viewChallenges;
	}

	public List<ListSelectorBean> getSolutionCats() {
		if (solutionCats == null)
			solutionCats = new ArrayList<ListSelectorBean>();
		return solutionCats;
	}

	public void setSolutionCats(List<ListSelectorBean> solutionCats) {
		this.solutionCats = solutionCats;
	}

	public List<ListSelectorBean> getSolutionStatuses() {
		if (solutionStatuses == null)
			solutionStatuses = new ArrayList<ListSelectorBean>();
		return solutionStatuses;
	}

	public void setSolutionStatuses(List<ListSelectorBean> solutionStatuses) {
		this.solutionStatuses = solutionStatuses;
	}

	public List<SolutionBean> getViewSolutions() {
		if (viewSolutions == null)
			viewSolutions = new ArrayList<SolutionBean>();
		return viewSolutions;
	}

	public void setViewSolutions(List<SolutionBean> viewSolutions) {
		this.viewSolutions = viewSolutions;
	}

	public IdeaBean getIdeaBean() {
		if (ideaBean == null)
			ideaBean = new IdeaBean();
		return ideaBean;
	}

	public void setIdeaBean(IdeaBean ideaBean) {
		this.ideaBean = ideaBean;
	}

	public ChallengeBean getChallengeBean() {
		if (challengeBean == null)
			challengeBean = new ChallengeBean();
		return challengeBean;
	}

	public void setChallengeBean(ChallengeBean challengeBean) {
		this.challengeBean = challengeBean;
	}

	public SolutionBean getSolutionBean() {
		if (solutionBean == null)
			solutionBean = new SolutionBean();
		return solutionBean;
	}

	public void setSolutionBean(SolutionBean solutionBean) {
		this.solutionBean = solutionBean;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean isShowIdeas() {
		return showIdeas;
	}

	public void setShowIdeas(boolean showIdeas) {
		this.showIdeas = showIdeas;
	}

	public boolean isShowChals() {
		return showChals;
	}

	public void setShowChals(boolean showChals) {
		this.showChals = showChals;
	}

	public boolean isShowSols() {
		return showSols;
	}

	public void setShowSols(boolean showSols) {
		this.showSols = showSols;
	}

	public String getToView() {
		return toView;
	}

	public void setToView(String toView) {
		this.toView = toView;
	}
}
