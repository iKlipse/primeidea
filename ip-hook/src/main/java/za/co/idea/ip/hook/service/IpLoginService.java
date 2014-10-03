package za.co.idea.ip.hook.service;

import za.co.idea.ip.hook.model.IpLogin;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.security.ac.AccessControlled;
import com.liferay.portal.service.BaseService;
import com.liferay.portal.service.InvokableService;

/**
 * Provides the remote service interface for IpLogin. Methods of this service
 * are expected to have security checks based on the propagated JAAS credentials
 * because this service can be accessed remotely.
 *
 * @author VMPattamatta
 * @see IpLoginServiceUtil
 * @see za.co.idea.ip.hook.service.base.IpLoginServiceBaseImpl
 * @see za.co.idea.ip.hook.service.impl.IpLoginServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor = { PortalException.class, SystemException.class })
public interface IpLoginService extends BaseService, InvokableService {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never modify or reference this interface directly. Always use {@link
	 * IpLoginServiceUtil} to access the ip login remote service. Add custom
	 * service methods to {@link
	 * za.co.idea.ip.hook.service.impl.IpLoginServiceImpl} and rerun
	 * ServiceBuilder to automatically copy the method declarations to this
	 * interface.
	 */

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	public java.lang.String getBeanIdentifier();

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier
	 *            the Spring bean ID for this bean
	 */
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	@Override
	public java.lang.Object invokeMethod(java.lang.String name, java.lang.String[] parameterTypes, java.lang.Object[] arguments) throws java.lang.Throwable;

	public boolean validateIpLogin(java.lang.String loginName, java.lang.String password);

	public boolean validateReminder(java.lang.String loginName, java.lang.String reminderAnswer);

	public boolean updatePasswordIpLogin(java.lang.String loginName, java.lang.String password, long companyId, boolean passwordReset);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IpLogin getIpLogin(java.lang.String loginName);

	public boolean setLoginLastDate(java.lang.String loginName);

	public boolean updateReminder(java.lang.String loginName, int reminderQuestion, java.lang.String reminderAnswer);
}
