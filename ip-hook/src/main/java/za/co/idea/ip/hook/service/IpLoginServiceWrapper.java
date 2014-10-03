package za.co.idea.ip.hook.service;

import za.co.idea.ip.hook.model.IpLogin;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IpLoginService}.
 *
 * @author VMPattamatta
 * @see IpLoginService
 * @generated
 */
public class IpLoginServiceWrapper implements IpLoginService, ServiceWrapper<IpLoginService> {
	private IpLoginService _ipLoginService;

	public IpLoginServiceWrapper(IpLoginService ipLoginService) {
		_ipLoginService = ipLoginService;
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public java.lang.String getBeanIdentifier() {
		return _ipLoginService.getBeanIdentifier();
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier
	 *            the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_ipLoginService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name, java.lang.String[] parameterTypes, java.lang.Object[] arguments) throws java.lang.Throwable {
		return _ipLoginService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public boolean validateIpLogin(java.lang.String loginName, java.lang.String password) {
		return _ipLoginService.validateIpLogin(loginName, password);
	}

	@Override
	public boolean validateReminder(java.lang.String loginName, java.lang.String reminderAnswer) {
		return _ipLoginService.validateReminder(loginName, reminderAnswer);
	}

	@Override
	public boolean updatePasswordIpLogin(java.lang.String loginName, java.lang.String password, long companyId, boolean passwordReset) {
		return _ipLoginService.updatePasswordIpLogin(loginName, password, companyId, passwordReset);
	}

	@Override
	public IpLogin getIpLogin(java.lang.String loginName) {
		return _ipLoginService.getIpLogin(loginName);
	}

	@Override
	public boolean setLoginLastDate(java.lang.String loginName) {
		return _ipLoginService.setLoginLastDate(loginName);
	}

	@Override
	public boolean updateReminder(java.lang.String loginName, int reminderQuestion, java.lang.String reminderAnswer) {
		return _ipLoginService.updateReminder(loginName, reminderQuestion, reminderAnswer);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public IpLoginService getWrappedIpLoginService() {
		return _ipLoginService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedIpLoginService(IpLoginService ipLoginService) {
		_ipLoginService = ipLoginService;
	}

	@Override
	public IpLoginService getWrappedService() {
		return _ipLoginService;
	}

	@Override
	public void setWrappedService(IpLoginService ipLoginService) {
		_ipLoginService = ipLoginService;
	}
}
