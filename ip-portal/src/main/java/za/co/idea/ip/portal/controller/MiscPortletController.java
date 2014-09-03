package za.co.idea.ip.portal.controller;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import za.co.idea.ip.portal.bean.PointBean;
import za.co.idea.ip.portal.bean.RewardsBean;
import za.co.idea.ip.portal.bean.comparable.RewardsPointsDiffComparable;
import za.co.idea.ip.portal.util.RESTServiceHelper;
import za.co.idea.ip.ws.bean.UserMessage;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;

@ManagedBean(name = "miscPortletController")
@SessionScoped
public class MiscPortletController implements Serializable {

	private static final long serialVersionUID = 8415134460058949523L;
	private static final Logger logger = Logger.getLogger(MiscPortletController.class);
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("ip-portal");
	private Long userId;
	private Long totalPoints;
	private List<RewardsBean> viewRewardsBeans;
	private List<PointBean> pointBeans;
	private List<RewardsPointsDiffComparable> rewardsPointsDiffList;

	public void initializeWishlist() {
		Set<RewardsPointsDiffComparable> rewardsPoints = new TreeSet<RewardsPointsDiffComparable>();
		try {
			PortletRequest request = (PortletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			User user = (User) request.getAttribute(WebKeys.USER);
			WebClient client = RESTServiceHelper.createCustomClient("http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/ip-ws/ip/as/user/verify/" + user.getScreenName());
			UserMessage message = client.accept(MediaType.APPLICATION_JSON).get(UserMessage.class);
			userId = message.getuId();
			pointBeans = RESTServiceHelper.fetchAllPointsByUser(userId);
			totalPoints = RESTServiceHelper.calculateTotal(pointBeans);
			viewRewardsBeans = RESTServiceHelper.fetchAllWishlistByUser(userId);
			for (RewardsBean reward : viewRewardsBeans) {
				RewardsPointsDiffComparable rewPoint = new RewardsPointsDiffComparable();
				rewPoint = (RewardsPointsDiffComparable) reward;
				rewPoint.setPointsDiff((int) (totalPoints - reward.getRwValue()));
				rewardsPoints.add(rewPoint);
			}
			int i = 0;
			for (RewardsPointsDiffComparable object : rewardsPoints) {
				i++;
				if (i <= 5) {
					rewardsPointsDiffList.add((RewardsPointsDiffComparable) object);
				}
			}
		} catch (Exception e) {
			logger.error(e, e);
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System error occurred, cannot perform view request", "System error occurred, cannot perform view request");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Long totalPoints) {
		this.totalPoints = totalPoints;
	}

	public List<RewardsBean> getViewRewardsBeans() {
		return viewRewardsBeans;
	}

	public void setViewRewardsBeans(List<RewardsBean> viewRewardsBeans) {
		this.viewRewardsBeans = viewRewardsBeans;
	}

	public List<PointBean> getPointBeans() {
		return pointBeans;
	}

	public void setPointBeans(List<PointBean> pointBeans) {
		this.pointBeans = pointBeans;
	}

	public List<RewardsPointsDiffComparable> getRewardsPointsDiffList() {
		return rewardsPointsDiffList;
	}

	public void setRewardsPointsDiffList(List<RewardsPointsDiffComparable> rewardsPointsDiffList) {
		this.rewardsPointsDiffList = rewardsPointsDiffList;
	}

}
