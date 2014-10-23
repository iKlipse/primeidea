package za.co.idea.ip.hook.service;

import za.co.idea.ip.hook.model.IpLogin;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for IpLogin. This utility wraps
 * {@link za.co.idea.ip.hook.service.impl.IpLoginServiceImpl} and is the primary
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be accessed
 * remotely.
 *
 * @author VMPattamatta
 * @see IpLoginService
 * @see za.co.idea.ip.hook.service.base.IpLoginServiceBaseImpl
 * @see za.co.idea.ip.hook.service.impl.IpLoginServiceImpl
 * @generated
 */
public class IpLoginServiceUtil {
	private static IpLoginService _service;

	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never modify this class directly. Add custom service methods to {@link
	 * za.co.idea.ip.hook.service.impl.IpLoginServiceImpl} and rerun
	 * ServiceBuilder to regenerate this class.
	 */

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier
	 *            the Spring bean ID for this bean
	 */
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name, java.lang.String[] parameterTypes, java.lang.Object[] arguments) throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static boolean validateIpLogin(java.lang.String loginName, java.lang.String password) {
		return getService().validateIpLogin(loginName, password);
	}

	public static boolean validateReminder(java.lang.String loginName, java.lang.String reminderAnswer) {
		return getService().validateReminder(loginName, reminderAnswer);
	}

	public static boolean updatePasswordIpLogin(java.lang.String loginName, java.lang.String password, long companyId, boolean passwordReset) {
		return getService().updatePasswordIpLogin(loginName, password, companyId, passwordReset);
	}

	public static IpLogin getIpLogin(java.lang.String loginName) {
		return getService().getIpLogin(loginName);
	}

	public static boolean setLoginLastDate(java.lang.String loginName) {
		return getService().setLoginLastDate(loginName);
	}

	public static boolean updateReminder(java.lang.String loginName, int reminderQuestion, java.lang.String reminderAnswer) {
		return getService().updateReminder(loginName, reminderQuestion, reminderAnswer);
	}

	public static void clearService() {
		_service = null;
	}

	public static IpLoginService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(), IpLoginService.class.getName());

			if (invokableService instanceof IpLoginService) {
				_service = (IpLoginService) invokableService;
			} else {
				_service = new IpLoginServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(IpLoginServiceUtil.class, "_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(IpLoginService service) {
	}
}
