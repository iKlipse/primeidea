package za.co.idea.ip.hook.ext;

import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import za.co.idea.ip.hook.NoSuchIpLoginException;
import za.co.idea.ip.hook.NoSuchIpUserException;
import za.co.idea.ip.hook.model.IpUser;
import za.co.idea.ip.hook.service.IpLoginServiceUtil;
import za.co.idea.ip.hook.service.IpUserServiceUtil;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

public class ExtLoginAction {
	private static final String NAMESPACE_SERVLET_REQUEST_FQCN = "com.liferay.portal.servlet.NamespaceServletRequest";

	public void processLogIn(long companyId, long userId, String handle, String password, boolean rememberMe, String authType, ActionResponse actionResponse, HttpServletRequest httpServletRequest, ServiceContext serviceContext) throws Exception {

		if (httpServletRequest.getClass().getName().equals(NAMESPACE_SERVLET_REQUEST_FQCN)) {
			while (httpServletRequest instanceof HttpServletRequestWrapper) {
				HttpServletRequestWrapper httpServletRequestWrapper = (HttpServletRequestWrapper) httpServletRequest;
				httpServletRequest = (HttpServletRequest) httpServletRequestWrapper.getRequest();
			}
		}

		HttpServletResponse httpServletResponse = PortalUtil.getHttpServletResponse(actionResponse);

		if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
			if (IpLoginServiceUtil.validateIpLogin(handle, password)) {
				if (IpUserServiceUtil.validateLiferayUser(handle, companyId)) {
					MethodInvoker.invokeLogin(httpServletRequest, httpServletResponse, handle, password, rememberMe, authType);
					IpLoginServiceUtil.setLoginLastDate(handle);
				} else {
					IpUser ipUser = IpUserServiceUtil.getIpUser(handle, password);
					if (Validator.isNotNull(ipUser)) {

						if (IpUserServiceUtil.addLiferayUser(ipUser.getUserId(), ipUser.getUserFName(), ipUser.getUserLName(), ipUser.getUserEmail(), password, ipUser.getUserScreenName(), userId, companyId, serviceContext)) {
							MethodInvoker.invokeLogin(httpServletRequest, httpServletResponse, handle, password, rememberMe, authType);
							IpLoginServiceUtil.setLoginLastDate(ipUser.getUserScreenName());
						} else {
							throw new NoSuchIpUserException();
						}

					}
				}
			} else {
				throw new NoSuchIpLoginException();
			}
		} else {
			MethodInvoker.invokeLogin(httpServletRequest, httpServletResponse, handle, password, rememberMe, authType);
		}

	}

}
