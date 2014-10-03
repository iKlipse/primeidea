package za.co.idea.ip.hook.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IpUserLocalService}.
 *
 * @author VMPattamatta
 * @see IpUserLocalService
 * @generated
 */
public class IpUserLocalServiceWrapper implements IpUserLocalService,
    ServiceWrapper<IpUserLocalService> {
    private IpUserLocalService _ipUserLocalService;

    public IpUserLocalServiceWrapper(IpUserLocalService ipUserLocalService) {
        _ipUserLocalService = ipUserLocalService;
    }

    /**
    * Adds the ip user to the database. Also notifies the appropriate model listeners.
    *
    * @param ipUser the ip user
    * @return the ip user that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public za.co.idea.ip.hook.model.IpUser addIpUser(
        za.co.idea.ip.hook.model.IpUser ipUser)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipUserLocalService.addIpUser(ipUser);
    }

    /**
    * Creates a new ip user with the primary key. Does not add the ip user to the database.
    *
    * @param userId the primary key for the new ip user
    * @return the new ip user
    */
    @Override
    public za.co.idea.ip.hook.model.IpUser createIpUser(long userId) {
        return _ipUserLocalService.createIpUser(userId);
    }

    /**
    * Deletes the ip user with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param userId the primary key of the ip user
    * @return the ip user that was removed
    * @throws PortalException if a ip user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public za.co.idea.ip.hook.model.IpUser deleteIpUser(long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ipUserLocalService.deleteIpUser(userId);
    }

    /**
    * Deletes the ip user from the database. Also notifies the appropriate model listeners.
    *
    * @param ipUser the ip user
    * @return the ip user that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public za.co.idea.ip.hook.model.IpUser deleteIpUser(
        za.co.idea.ip.hook.model.IpUser ipUser)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipUserLocalService.deleteIpUser(ipUser);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ipUserLocalService.dynamicQuery();
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
        return _ipUserLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ipUserLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ipUserLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _ipUserLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ipUserLocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public za.co.idea.ip.hook.model.IpUser fetchIpUser(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipUserLocalService.fetchIpUser(userId);
    }

    /**
    * Returns the ip user with the primary key.
    *
    * @param userId the primary key of the ip user
    * @return the ip user
    * @throws PortalException if a ip user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public za.co.idea.ip.hook.model.IpUser getIpUser(long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ipUserLocalService.getIpUser(userId);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ipUserLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ip users.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ip users
    * @param end the upper bound of the range of ip users (not inclusive)
    * @return the range of ip users
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<za.co.idea.ip.hook.model.IpUser> getIpUsers(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipUserLocalService.getIpUsers(start, end);
    }

    /**
    * Returns the number of ip users.
    *
    * @return the number of ip users
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIpUsersCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipUserLocalService.getIpUsersCount();
    }

    /**
    * Updates the ip user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ipUser the ip user
    * @return the ip user that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public za.co.idea.ip.hook.model.IpUser updateIpUser(
        za.co.idea.ip.hook.model.IpUser ipUser)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipUserLocalService.updateIpUser(ipUser);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ipUserLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ipUserLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ipUserLocalService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IpUserLocalService getWrappedIpUserLocalService() {
        return _ipUserLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIpUserLocalService(
        IpUserLocalService ipUserLocalService) {
        _ipUserLocalService = ipUserLocalService;
    }

    @Override
    public IpUserLocalService getWrappedService() {
        return _ipUserLocalService;
    }

    @Override
    public void setWrappedService(IpUserLocalService ipUserLocalService) {
        _ipUserLocalService = ipUserLocalService;
    }
}
