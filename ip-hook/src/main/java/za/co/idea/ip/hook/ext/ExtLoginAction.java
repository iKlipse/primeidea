package za.co.idea.ip.hook.ext;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.log4j.Logger;

import za.co.idea.ip.hook.model.IpUser;
import za.co.idea.ip.hook.service.IpLoginServiceUtil;
import za.co.idea.ip.hook.service.IpUserServiceUtil;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

public class ExtLoginAction extends BaseStrutsPortletAction {
	private static final Logger LOGGER = Logger.getLogger(ExtLoginAction.class);

	public void processAction(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		Company company = PortalUtil.getCompany(actionRequest);
		String authType = company.getAuthType();
		if (authType.equals("screenName")) {
			String login = ParamUtil.getString(actionRequest, "login");
			String password = actionRequest.getParameter("password");
			if (IpLoginServiceUtil.validateIpLogin(login, password)) {
				if (IpUserServiceUtil.validateLiferayUser(login, company.getCompanyId())) {
					originalStrutsPortletAction.processAction(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse);
				} else {
					ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
					long userId = themeDisplay.getUserId();
					IpUser ipUser = IpUserServiceUtil.getIpUser(login, password);
					if (Validator.isNotNull(ipUser)) {
						LOGGER.info("User :: " + ipUser.getUserScreenName() + " :: " + ipUser.getUserStatus());
						if (ipUser.getUserStatus().equalsIgnoreCase("y")) {
							LOGGER.info("User :: " + ipUser.getUserScreenName() + " :: " + ipUser.getUserStatus());
							ServiceContext serviceContext = ServiceContextFactory.getInstance(User.class.getName(), actionRequest);
							if (IpUserServiceUtil.addLiferayUser(ipUser.getUserId(), ipUser.getUserFName(), ipUser.getUserLName(), ipUser.getUserEmail(), password, ipUser.getUserScreenName(), userId, company.getCompanyId(), serviceContext)) {
								originalStrutsPortletAction.processAction(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse);
							} else {
								LOGGER.info("User :: " + ipUser.getUserScreenName() + " :: " + ipUser.getUserStatus());
								SessionErrors.add(actionRequest, "no-such-user-exception");
							}
						} else {
							LOGGER.info("User :: " + ipUser.getUserScreenName() + " :: " + ipUser.getUserStatus());
							SessionErrors.add(actionRequest, "inactive-user-exception");
						}
					}
				}
			} else {
				SessionErrors.add(actionRequest, "no-such-user-exception");
			}
		} else {
			originalStrutsPortletAction.processAction(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse);
		}
	}

	public String render(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig, RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
		return originalStrutsPortletAction.render(originalStrutsPortletAction, portletConfig, renderRequest, renderResponse);
	}
}
