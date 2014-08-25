package za.co.idea.ip.portal.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import za.co.idea.ip.portal.bean.ChallengeBean;
import za.co.idea.ip.portal.bean.ClaimBean;
import za.co.idea.ip.portal.bean.IdeaBean;
import za.co.idea.ip.portal.bean.ListSelectorBean;
import za.co.idea.ip.portal.bean.PointBean;
import za.co.idea.ip.portal.bean.RewardsBean;
import za.co.idea.ip.portal.bean.SolutionBean;
import za.co.idea.ip.portal.bean.UserBean;
import za.co.idea.ip.portal.util.RESTServiceHelper;
import za.co.idea.ip.ws.bean.ResponseMessage;
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
	private boolean showWishlist;
	private boolean showPoints;
	private boolean showClaims;
	private String toView;
	private List<ListSelectorBean> rewardsCat;
	private RewardsBean rewardsBean;
	private List<RewardsBean> viewRewardsBeans;
	private List<ListSelectorBean> claimStatus;
	private List<ClaimBean> viewClaimBeans;
	private ClaimBean claimBean;
	private List<PointBean> pointBeans;
	private Long totalPoints;

	@PostConstruct
	public void initializePage() {
		try {
			PortletRequest request = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			User user = (User) request.getAttribute(WebKeys.USER);
			WebClient client = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/verify/" + user.getScreenName());
			UserMessage message = client.accept(MediaType.APPLICATION_JSON).get(UserMessage.class);
			userId = message.getuId();
			AccessController controller = new AccessController(userId);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("accessBean", controller);
			admUsers = RESTServiceHelper.fetchAllUsers();
			viewIdeas = RESTServiceHelper.fetchAllIdeasByUser(userId);
			ideaCats = RESTServiceHelper.fetchAllIdeaCat();
			ideaStatuses = RESTServiceHelper.fetchAllIdeaStatuses();
			viewChallenges = RESTServiceHelper.fetchAllChallengesByUser(userId);
			challengeCats = RESTServiceHelper.fetchAllChallengeCat();
			challengeStatuses = RESTServiceHelper.fetchAllChallengeStatuses();
			viewSolutions = RESTServiceHelper.fetchAllSolutionsByUser(userId);
			solutionCats = RESTServiceHelper.fetchAllSolutionCat();
			solutionStatuses = RESTServiceHelper.fetchAllSolutionStatuses();
			claimStatus = RESTServiceHelper.fetchAllClaimStatuses();
			viewClaimBeans = RESTServiceHelper.fetchAllClaimsByUser(userId);
			viewRewardsBeans = RESTServiceHelper.fetchAllRewards();
			pointBeans = RESTServiceHelper.fetchAllPointsByUser(userId);
			totalPoints = calculateTotal(pointBeans);
			rewardsCat = RESTServiceHelper.fetchAllRewardsCat();
			viewRewardsBeans = RESTServiceHelper.fetchAllRewardsByUser(userId, totalPoints);
			showIdeas = false;
			showChals = true;
			showSols = false;
			showWishlist = false;
			showClaims = false;
			showPoints = false;
			if (toView != null && Integer.valueOf(toView) != -1) {
				switch (Integer.valueOf(toView)) {
				case 1:
					showIdeas = true;
					showChals = false;
					showSols = false;
					showWishlist = false;
					showClaims = false;
					showPoints = false;
					break;
				case 2:
					showIdeas = false;
					showChals = true;
					showSols = false;
					showWishlist = false;
					showClaims = false;
					showPoints = false;
					break;
				case 3:
					showIdeas = false;
					showChals = false;
					showSols = true;
					showWishlist = false;
					showClaims = false;
					showPoints = false;
					break;
				case 4:
					rewardsBean = new RewardsBean();
					showIdeas = false;
					showChals = false;
					showSols = false;
					showWishlist = true;
					showClaims = false;
					showPoints = false;
					break;
				case 5:
					showIdeas = false;
					showChals = false;
					showSols = false;
					showWishlist = false;
					showClaims = true;
					showPoints = false;
					break;
				case 6:
					showIdeas = false;
					showChals = false;
					showSols = false;
					showWishlist = false;
					showClaims = false;
					showPoints = true;
					break;
				}
			}

		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	private Long calculateTotal(List<PointBean> pntBeans) {
		Long ret = 0l;
		for (PointBean pointBean : pntBeans)
			ret += pointBean.getPointValue();
		return ret;
	}

	public void changeIdea() {
		try {
			viewIdeas = RESTServiceHelper.fetchAllIdeasByUser(userId);
			ideaCats = RESTServiceHelper.fetchAllIdeaCat();
			admUsers = RESTServiceHelper.fetchAllUsers();
			ideaStatuses = RESTServiceHelper.fetchAllIdeaStatuses();
			showIdeas = true;
			showChals = false;
			showSols = false;
			showWishlist = false;
			showClaims = false;
			showPoints = false;
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void changeChallenge() {
		try {
			viewChallenges = RESTServiceHelper.fetchAllChallengesByUser(userId);
			challengeCats = RESTServiceHelper.fetchAllChallengeCat();
			admUsers = RESTServiceHelper.fetchAllUsers();
			challengeStatuses = RESTServiceHelper.fetchAllChallengeStatuses();
			showIdeas = false;
			showChals = true;
			showSols = false;
			showWishlist = false;
			showClaims = false;
			showPoints = false;
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void changeSolution() {
		try {
			viewSolutions = RESTServiceHelper.fetchAllSolutionsByUser(userId);
			solutionCats = RESTServiceHelper.fetchAllSolutionCat();
			admUsers = RESTServiceHelper.fetchAllUsers();
			solutionStatuses = RESTServiceHelper.fetchAllSolutionStatuses();
			showIdeas = false;
			showChals = false;
			showSols = true;
			showWishlist = false;
			showClaims = false;
			showPoints = false;
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void showWishList() {
		try {
			admUsers = RESTServiceHelper.fetchAllUsers();
			rewardsCat = RESTServiceHelper.fetchAllRewardsCat();
			rewardsBean = new RewardsBean();
			viewRewardsBeans = RESTServiceHelper.fetchAllRewardsByUser(userId, totalPoints);
			showIdeas = false;
			showChals = false;
			showSols = false;
			showWishlist = true;
			showClaims = false;
			showPoints = false;
		} catch (Exception e) {
			logger.error(e, e);

			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void showViewClaimByUser() {
		try {
			claimBean = new ClaimBean();
			admUsers = RESTServiceHelper.fetchAllUsers();
			claimStatus = RESTServiceHelper.fetchAllClaimStatuses();
			viewClaimBeans = RESTServiceHelper.fetchAllClaimsByUser(userId);
			viewRewardsBeans = RESTServiceHelper.fetchAllRewards();
			showIdeas = false;
			showChals = false;
			showSols = false;
			showWishlist = false;
			showClaims = true;
			showPoints = false;
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void showPointProfile() {
		pointBeans = RESTServiceHelper.fetchAllPointsByUser(userId);
		totalPoints = calculateTotal(pointBeans);
		showIdeas = false;
		showChals = false;
		showSols = false;
		showWishlist = false;
		showClaims = false;
		showPoints = true;
	}

	public void modifyWishlist() {
		ResponseMessage response = RESTServiceHelper.modifyTag(rewardsBean.getRwId(), userId);
		if (response.getStatusCode() != 0 && response.getStatusCode() != 2)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error While Saving Like", "Error While Saving Like"));
		viewRewardsBeans = RESTServiceHelper.fetchAllRewardsByUser(userId, totalPoints);
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
		if (toView == null)
			toView = "";
		return toView;
	}

	public void setToView(String toView) {
		this.toView = toView;
	}

	public boolean isShowWishlist() {
		return showWishlist;
	}

	public void setShowWishlist(boolean showWishlist) {
		this.showWishlist = showWishlist;
	}

	public boolean isShowPoints() {
		return showPoints;
	}

	public void setShowPoints(boolean showPoints) {
		this.showPoints = showPoints;
	}

	public boolean isShowClaims() {
		return showClaims;
	}

	public void setShowClaims(boolean showClaims) {
		this.showClaims = showClaims;
	}

	public List<ListSelectorBean> getRewardsCat() {
		return rewardsCat;
	}

	public void setRewardsCat(List<ListSelectorBean> rewardsCat) {
		this.rewardsCat = rewardsCat;
	}

	public RewardsBean getRewardsBean() {
		return rewardsBean;
	}

	public void setRewardsBean(RewardsBean rewardsBean) {
		this.rewardsBean = rewardsBean;
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

	public List<ClaimBean> getViewClaimBeans() {
		return viewClaimBeans;
	}

	public void setViewClaimBeans(List<ClaimBean> viewClaimBeans) {
		this.viewClaimBeans = viewClaimBeans;
	}

	public ClaimBean getClaimBean() {
		return claimBean;
	}

	public void setClaimBean(ClaimBean claimBean) {
		this.claimBean = claimBean;
	}

	public List<PointBean> getPointBeans() {
		return pointBeans;
	}

	public void setPointBeans(List<PointBean> pointBeans) {
		this.pointBeans = pointBeans;
	}

	public Long getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Long totalPoints) {
		this.totalPoints = totalPoints;
	}

}
