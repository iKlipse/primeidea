package za.co.idea.ip.portal.controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import za.co.idea.ip.portal.bean.UserBean;

import com.liferay.faces.portal.context.LiferayFacesContext;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.login.util.LoginUtil;

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
			PortletRequest portletRequest = LiferayFacesContext.getInstance().getPortletRequest();
			HttpServletRequest request = PortalUtil.getHttpServletRequest(portletRequest);
			PortletResponse portletResponse = LiferayFacesContext.getInstance().getPortletResponse();
			HttpServletResponse response = PortalUtil.getHttpServletResponse(portletResponse);
			LoginUtil.login(request, response, userBean.getScName(), userBean.getPwd(), true, CompanyConstants.AUTH_TYPE_SN);
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
