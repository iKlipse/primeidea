package za.co.idea.ip.portal.controller;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.cxf.jaxrs.client.WebClient;

import za.co.idea.ip.portal.bean.UserBean;
import za.co.idea.ip.portal.util.RESTServiceHelper;
import za.co.idea.ip.ws.bean.UserMessage;

import com.liferay.faces.portal.context.LiferayFacesContext;

@ManagedBean(name = "loginPortletController")
@SessionScoped
public class LoginPortletController implements Serializable {

	private static final long serialVersionUID = 1081521351048260552L;
	private UserBean userBean;

	public void login() {
		ArrayList<String> errors = validateLogin();
		if (errors != null && errors.size() > 0) {
			for (String error : errors) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, error, error);
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			}
		}
		try {
			WebClient loginClient = RESTServiceHelper.createCustomClient("http://127.0.0.1:8080/ip-ws/ip/as/user/login/" + userBean.getScName() + "/" + Base64.encodeBase64URLSafeString(DigestUtils.md5(userBean.getPwd())));
			UserMessage userMessage = loginClient.accept(MediaType.APPLICATION_JSON).get(UserMessage.class);
			loginClient.close();
			if (userMessage != null && userMessage.getuId() != null && userMessage.getuId() == -999l) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "User Account Is De-Activate", "User Account Is De-Activate");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			} else if (userMessage == null || userMessage.getuId() == null || userMessage.getScName() == null) {
				FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid User Name Password", "Invalid User Name Password");
				FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
			} else {
				PortletRequest request = LiferayFacesContext.getInstance().getPortletRequest();
				String url = new URL(request.getScheme(), request.getServerName(), request.getServerPort(), "/web/ip/landing-page").toString();
				FacesContext.getCurrentInstance().getExternalContext().redirect(url);
			}
		} catch (Exception e) {
			FacesMessage exceptionMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "System Exception Occured During Login", "System Exception Occured During Login");
			FacesContext.getCurrentInstance().addMessage(null, exceptionMessage);
		}
	}

	protected ArrayList<String> validateLogin() {
		ArrayList<String> ret = new ArrayList<String>();
		if (userBean.getScName() == null || userBean.getScName().length() == 0) {
			ret.add("User Name is Mandatory");
		}
		if (userBean.getPwd() == null || userBean.getPwd().length() == 0) {
			ret.add("Password is Mandatory");
		}
		return ret;
	}

	public void showRPw() {
	}

	public UserBean getUserBean() {
		if (userBean == null)
			userBean = new UserBean();
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
