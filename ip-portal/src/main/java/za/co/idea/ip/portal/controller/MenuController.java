package za.co.idea.ip.portal.controller;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;

import com.liferay.faces.portal.context.LiferayFacesContext;

@ManagedBean(name = "menuController")
@SessionScoped
public class MenuController implements Serializable {
	private static final long serialVersionUID = -7805375851486801882L;
	private List<String> images;

	public List<String> getImages() {
		images = new ArrayList<String>();
		for (int i = 1; i <= 8; i++) {
			images.add(i + ".jpg");
		}
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public void redirectChallenge() {
		try {
			PortletRequest request = LiferayFacesContext.getInstance().getPortletRequest();
			String url = new URL(request.getScheme(), request.getServerName(), request.getServerPort(), "/web/ip/challenge").toString();
			FacesContext.getCurrentInstance().getExternalContext().redirect(url);
		} catch (Exception e) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System Exception Occured During Login", "System Exception Occured During Login");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void redirectIdea() {
		try {
			PortletRequest request = LiferayFacesContext.getInstance().getPortletRequest();
			String url = new URL(request.getScheme(), request.getServerName(), request.getServerPort(), "/web/ip/idea").toString();
			FacesContext.getCurrentInstance().getExternalContext().redirect(url);
		} catch (Exception e) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System Exception Occured During Login", "System Exception Occured During Login");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void redirectSolution() {
		try {
			PortletRequest request = LiferayFacesContext.getInstance().getPortletRequest();
			String url = new URL(request.getScheme(), request.getServerName(), request.getServerPort(), "/web/ip/solution").toString();
			FacesContext.getCurrentInstance().getExternalContext().redirect(url);
		} catch (Exception e) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System Exception Occured During Login", "System Exception Occured During Login");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	public void redirectRewards() {
		try {
			PortletRequest request = LiferayFacesContext.getInstance().getPortletRequest();
			String url = new URL(request.getScheme(), request.getServerName(), request.getServerPort(), "/web/ip/rewards").toString();
			FacesContext.getCurrentInstance().getExternalContext().redirect(url);
		} catch (Exception e) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System Exception Occured During Login", "System Exception Occured During Login");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}
}
