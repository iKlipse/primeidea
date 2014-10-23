package za.co.idea.ip.hook.service;

import za.co.idea.ip.hook.model.IpUser;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IpUserService}.
 *
 * @author VMPattamatta
 * @see IpUserService
 * @generated
 */
public class IpUserServiceWrapper implements IpUserService, ServiceWrapper<IpUserService> {
	private IpUserService _ipUserService;

	public IpUserServiceWrapper(IpUserService ipUserService) {
		_ipUserService = ipUserService;
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public java.lang.String getBeanIdentifier() {
		return _ipUserService.getBeanIdentifier();
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier
	 *            the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_ipUserService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name, java.lang.String[] parameterTypes, java.lang.Object[] arguments) throws java.lang.Throwable {
		return _ipUserService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public boolean validateIpUser(java.lang.String loginName) {
		return _ipUserService.validateIpUser(loginName);
	}

	@Override
	public boolean validateLiferayUser(java.lang.String loginName, long companyId) {
		return _ipUserService.validateLiferayUser(loginName, companyId);
	}

	@Override
	public IpUser getIpUser(java.lang.String loginName, java.lang.String password) {
		return _ipUserService.getIpUser(loginName, password);
	}

	@Override
	public boolean addLiferayUser(long userId, java.lang.String firstName, java.lang.String lastName, java.lang.String email, java.lang.String password, java.lang.String screenName, long creatorUserId, long companyId, com.liferay.portal.service.ServiceContext serviceContext) {
		return _ipUserService.addLiferayUser(userId, firstName, lastName, email, password, screenName, creatorUserId, companyId, serviceContext);
	}

	@Override
	public boolean updateIpUser(java.lang.String loginName, java.lang.String emailAddress, java.lang.String firstName, java.lang.String lastName, long companyId) {
		return _ipUserService.updateIpUser(loginName, emailAddress, firstName, lastName, companyId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public IpUserService getWrappedIpUserService() {
		return _ipUserService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedIpUserService(IpUserService ipUserService) {
		_ipUserService = ipUserService;
	}

	@Override
	public IpUserService getWrappedService() {
		return _ipUserService;
	}

	@Override
	public void setWrappedService(IpUserService ipUserService) {
		_ipUserService = ipUserService;
	}
}
