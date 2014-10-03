package za.co.idea.ip.hook.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IpLoginLocalService}.
 *
 * @author VMPattamatta
 * @see IpLoginLocalService
 * @generated
 */
public class IpLoginLocalServiceWrapper implements IpLoginLocalService,
    ServiceWrapper<IpLoginLocalService> {
    private IpLoginLocalService _ipLoginLocalService;

    public IpLoginLocalServiceWrapper(IpLoginLocalService ipLoginLocalService) {
        _ipLoginLocalService = ipLoginLocalService;
    }

    /**
    * Adds the ip login to the database. Also notifies the appropriate model listeners.
    *
    * @param ipLogin the ip login
    * @return the ip login that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public za.co.idea.ip.hook.model.IpLogin addIpLogin(
        za.co.idea.ip.hook.model.IpLogin ipLogin)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipLoginLocalService.addIpLogin(ipLogin);
    }

    /**
    * Creates a new ip login with the primary key. Does not add the ip login to the database.
    *
    * @param loginId the primary key for the new ip login
    * @return the new ip login
    */
    @Override
    public za.co.idea.ip.hook.model.IpLogin createIpLogin(long loginId) {
        return _ipLoginLocalService.createIpLogin(loginId);
    }

    /**
    * Deletes the ip login with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param loginId the primary key of the ip login
    * @return the ip login that was removed
    * @throws PortalException if a ip login with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public za.co.idea.ip.hook.model.IpLogin deleteIpLogin(long loginId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ipLoginLocalService.deleteIpLogin(loginId);
    }

    /**
    * Deletes the ip login from the database. Also notifies the appropriate model listeners.
    *
    * @param ipLogin the ip login
    * @return the ip login that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public za.co.idea.ip.hook.model.IpLogin deleteIpLogin(
        za.co.idea.ip.hook.model.IpLogin ipLogin)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipLoginLocalService.deleteIpLogin(ipLogin);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ipLoginLocalService.dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipLoginLocalService.dynamicQuery(dynamicQuery);
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
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _ipLoginLocalService.dynamicQuery(dynamicQuery, start, end);
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
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipLoginLocalService.dynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipLoginLocalService.dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipLoginLocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public za.co.idea.ip.hook.model.IpLogin fetchIpLogin(long loginId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipLoginLocalService.fetchIpLogin(loginId);
    }

    /**
    * Returns the ip login with the primary key.
    *
    * @param loginId the primary key of the ip login
    * @return the ip login
    * @throws PortalException if a ip login with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public za.co.idea.ip.hook.model.IpLogin getIpLogin(long loginId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ipLoginLocalService.getIpLogin(loginId);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ipLoginLocalService.getPersistedModel(primaryKeyObj);
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
    @Override
    public java.util.List<za.co.idea.ip.hook.model.IpLogin> getIpLogins(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipLoginLocalService.getIpLogins(start, end);
    }

    /**
    * Returns the number of ip logins.
    *
    * @return the number of ip logins
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIpLoginsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipLoginLocalService.getIpLoginsCount();
    }

    /**
    * Updates the ip login in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ipLogin the ip login
    * @return the ip login that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public za.co.idea.ip.hook.model.IpLogin updateIpLogin(
        za.co.idea.ip.hook.model.IpLogin ipLogin)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipLoginLocalService.updateIpLogin(ipLogin);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ipLoginLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ipLoginLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ipLoginLocalService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IpLoginLocalService getWrappedIpLoginLocalService() {
        return _ipLoginLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIpLoginLocalService(
        IpLoginLocalService ipLoginLocalService) {
        _ipLoginLocalService = ipLoginLocalService;
    }

    @Override
    public IpLoginLocalService getWrappedService() {
        return _ipLoginLocalService;
    }

    @Override
    public void setWrappedService(IpLoginLocalService ipLoginLocalService) {
        _ipLoginLocalService = ipLoginLocalService;
    }
}
