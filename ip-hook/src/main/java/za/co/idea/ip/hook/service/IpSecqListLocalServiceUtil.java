package za.co.idea.ip.hook.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for IpSecqList. This utility wraps
 * {@link za.co.idea.ip.hook.service.impl.IpSecqListLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author VMPattamatta
 * @see IpSecqListLocalService
 * @see za.co.idea.ip.hook.service.base.IpSecqListLocalServiceBaseImpl
 * @see za.co.idea.ip.hook.service.impl.IpSecqListLocalServiceImpl
 * @generated
 */
public class IpSecqListLocalServiceUtil {
    private static IpSecqListLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link za.co.idea.ip.hook.service.impl.IpSecqListLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the ip secq list to the database. Also notifies the appropriate model listeners.
    *
    * @param ipSecqList the ip secq list
    * @return the ip secq list that was added
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpSecqList addIpSecqList(
        za.co.idea.ip.hook.model.IpSecqList ipSecqList)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addIpSecqList(ipSecqList);
    }

    /**
    * Creates a new ip secq list with the primary key. Does not add the ip secq list to the database.
    *
    * @param islId the primary key for the new ip secq list
    * @return the new ip secq list
    */
    public static za.co.idea.ip.hook.model.IpSecqList createIpSecqList(
        long islId) {
        return getService().createIpSecqList(islId);
    }

    /**
    * Deletes the ip secq list with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param islId the primary key of the ip secq list
    * @return the ip secq list that was removed
    * @throws PortalException if a ip secq list with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpSecqList deleteIpSecqList(
        long islId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteIpSecqList(islId);
    }

    /**
    * Deletes the ip secq list from the database. Also notifies the appropriate model listeners.
    *
    * @param ipSecqList the ip secq list
    * @return the ip secq list that was removed
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpSecqList deleteIpSecqList(
        za.co.idea.ip.hook.model.IpSecqList ipSecqList)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteIpSecqList(ipSecqList);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpSecqListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpSecqListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static za.co.idea.ip.hook.model.IpSecqList fetchIpSecqList(
        long islId) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchIpSecqList(islId);
    }

    /**
    * Returns the ip secq list with the primary key.
    *
    * @param islId the primary key of the ip secq list
    * @return the ip secq list
    * @throws PortalException if a ip secq list with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpSecqList getIpSecqList(long islId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getIpSecqList(islId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ip secq lists.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpSecqListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ip secq lists
    * @param end the upper bound of the range of ip secq lists (not inclusive)
    * @return the range of ip secq lists
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<za.co.idea.ip.hook.model.IpSecqList> getIpSecqLists(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getIpSecqLists(start, end);
    }

    /**
    * Returns the number of ip secq lists.
    *
    * @return the number of ip secq lists
    * @throws SystemException if a system exception occurred
    */
    public static int getIpSecqListsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getIpSecqListsCount();
    }

    /**
    * Updates the ip secq list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ipSecqList the ip secq list
    * @return the ip secq list that was updated
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpSecqList updateIpSecqList(
        za.co.idea.ip.hook.model.IpSecqList ipSecqList)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateIpSecqList(ipSecqList);
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

    public static IpSecqListLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    IpSecqListLocalService.class.getName());

            if (invokableLocalService instanceof IpSecqListLocalService) {
                _service = (IpSecqListLocalService) invokableLocalService;
            } else {
                _service = new IpSecqListLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(IpSecqListLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(IpSecqListLocalService service) {
    }
}
