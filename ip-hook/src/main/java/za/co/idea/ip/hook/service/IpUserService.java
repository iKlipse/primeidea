package za.co.idea.ip.hook.service;

import za.co.idea.ip.hook.model.IpUser;

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
 * Provides the remote service interface for IpUser. Methods of this service are
 * expected to have security checks based on the propagated JAAS credentials
 * because this service can be accessed remotely.
 *
 * @author VMPattamatta
 * @see IpUserServiceUtil
 * @see za.co.idea.ip.hook.service.base.IpUserServiceBaseImpl
 * @see za.co.idea.ip.hook.service.impl.IpUserServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor = { PortalException.class, SystemException.class })
public interface IpUserService extends BaseService, InvokableService {
	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never modify or reference this interface directly. Always use {@link
	 * IpUserServiceUtil} to access the ip user remote service. Add custom
	 * service methods to {@link
	 * za.co.idea.ip.hook.service.impl.IpUserServiceImpl} and rerun
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

	public boolean validateIpUser(java.lang.String loginName);

	public boolean validateLiferayUser(java.lang.String loginName, long companyId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IpUser getIpUser(java.lang.String loginName, java.lang.String password);

	public boolean addLiferayUser(long userId, java.lang.String firstName, java.lang.String lastName, java.lang.String email, java.lang.String password, java.lang.String screenName, long creatorUserId, long companyId, com.liferay.portal.service.ServiceContext serviceContext);

	public boolean updateIpUser(java.lang.String loginName, java.lang.String emailAddress, java.lang.String firstName, java.lang.String lastName, long companyId);
}
