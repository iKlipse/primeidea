package za.co.idea.ip.hook.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for IpLogin. This utility wraps
 * {@link za.co.idea.ip.hook.service.impl.IpLoginLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author VMPattamatta
 * @see IpLoginLocalService
 * @see za.co.idea.ip.hook.service.base.IpLoginLocalServiceBaseImpl
 * @see za.co.idea.ip.hook.service.impl.IpLoginLocalServiceImpl
 * @generated
 */
public class IpLoginLocalServiceUtil {
    private static IpLoginLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link za.co.idea.ip.hook.service.impl.IpLoginLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the ip login to the database. Also notifies the appropriate model listeners.
    *
    * @param ipLogin the ip login
    * @return the ip login that was added
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin addIpLogin(
        za.co.idea.ip.hook.model.IpLogin ipLogin)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addIpLogin(ipLogin);
    }

    /**
    * Creates a new ip login with the primary key. Does not add the ip login to the database.
    *
    * @param loginId the primary key for the new ip login
    * @return the new ip login
    */
    public static za.co.idea.ip.hook.model.IpLogin createIpLogin(long loginId) {
        return getService().createIpLogin(loginId);
    }

    /**
    * Deletes the ip login with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param loginId the primary key of the ip login
    * @return the ip login that was removed
    * @throws PortalException if a ip login with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin deleteIpLogin(long loginId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteIpLogin(loginId);
    }

    /**
    * Deletes the ip login from the database. Also notifies the appropriate model listeners.
    *
    * @param ipLogin the ip login
    * @return the ip login that was removed
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin deleteIpLogin(
        za.co.idea.ip.hook.model.IpLogin ipLogin)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteIpLogin(ipLogin);
    }

    public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return getService().dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery, projection);
    }

    public static za.co.idea.ip.hook.model.IpLogin fetchIpLogin(long loginId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchIpLogin(loginId);
    }

    /**
    * Returns the ip login with the primary key.
    *
    * @param loginId the primary key of the ip login
    * @return the ip login
    * @throws PortalException if a ip login with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin getIpLogin(long loginId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getIpLogin(loginId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ip logins.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ip logins
    * @param end the upper bound of the range of ip logins (not inclusive)
    * @return the range of ip logins
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<za.co.idea.ip.hook.model.IpLogin> getIpLogins(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getIpLogins(start, end);
    }

    /**
    * Returns the number of ip logins.
    *
    * @return the number of ip logins
    * @throws SystemException if a system exception occurred
    */
    public static int getIpLoginsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getIpLoginsCount();
    }

    /**
    * Updates the ip login in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ipLogin the ip login
    * @return the ip login that was updated
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin updateIpLogin(
        za.co.idea.ip.hook.model.IpLogin ipLogin)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateIpLogin(ipLogin);
    }

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
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

    public static void clearService() {
        _service = null;
    }

    public static IpLoginLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    IpLoginLocalService.class.getName());

            if (invokableLocalService instanceof IpLoginLocalService) {
                _service = (IpLoginLocalService) invokableLocalService;
            } else {
                _service = new IpLoginLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(IpLoginLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(IpLoginLocalService service) {
    }
}
