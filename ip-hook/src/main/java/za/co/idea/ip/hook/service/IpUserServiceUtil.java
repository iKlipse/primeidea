package za.co.idea.ip.hook.service;

import za.co.idea.ip.hook.model.IpUser;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service utility for IpUser. This utility wraps
 * {@link za.co.idea.ip.hook.service.impl.IpUserServiceImpl} and is the primary
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be accessed
 * remotely.
 *
 * @author VMPattamatta
 * @see IpUserService
 * @see za.co.idea.ip.hook.service.base.IpUserServiceBaseImpl
 * @see za.co.idea.ip.hook.service.impl.IpUserServiceImpl
 * @generated
 */
public class IpUserServiceUtil {
	private static IpUserService _service;

	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never modify this class directly. Add custom service methods to {@link
	 * za.co.idea.ip.hook.service.impl.IpUserServiceImpl} and rerun
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

	public static boolean validateIpUser(java.lang.String loginName) {
		return getService().validateIpUser(loginName);
	}

	public static boolean validateLiferayUser(java.lang.String loginName, long companyId) {
		return getService().validateLiferayUser(loginName, companyId);
	}

	public static IpUser getIpUser(java.lang.String loginName, java.lang.String password) {
		return getService().getIpUser(loginName, password);
	}

	public static boolean addLiferayUser(long userId, java.lang.String firstName, java.lang.String lastName, java.lang.String email, java.lang.String password, java.lang.String screenName, long creatorUserId, long companyId, com.liferay.portal.service.ServiceContext serviceContext) {
		return getService().addLiferayUser(userId, firstName, lastName, email, password, screenName, creatorUserId, companyId, serviceContext);
	}

	public static boolean updateIpUser(java.lang.String loginName, java.lang.String emailAddress, java.lang.String firstName, java.lang.String lastName, long companyId) {
		return getService().updateIpUser(loginName, emailAddress, firstName, lastName, companyId);
	}

	public static void clearService() {
		_service = null;
	}

	public static IpUserService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(), IpUserService.class.getName());

			if (invokableService instanceof IpUserService) {
				_service = (IpUserService) invokableService;
			} else {
				_service = new IpUserServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(IpUserServiceUtil.class, "_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(IpUserService service) {
	}
}
