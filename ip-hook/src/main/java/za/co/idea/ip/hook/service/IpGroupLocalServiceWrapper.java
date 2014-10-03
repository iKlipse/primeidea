package za.co.idea.ip.hook.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IpGroupLocalService}.
 *
 * @author VMPattamatta
 * @see IpGroupLocalService
 * @generated
 */
public class IpGroupLocalServiceWrapper implements IpGroupLocalService,
    ServiceWrapper<IpGroupLocalService> {
    private IpGroupLocalService _ipGroupLocalService;

    public IpGroupLocalServiceWrapper(IpGroupLocalService ipGroupLocalService) {
        _ipGroupLocalService = ipGroupLocalService;
    }

    /**
    * Adds the ip group to the database. Also notifies the appropriate model listeners.
    *
    * @param ipGroup the ip group
    * @return the ip group that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public za.co.idea.ip.hook.model.IpGroup addIpGroup(
        za.co.idea.ip.hook.model.IpGroup ipGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipGroupLocalService.addIpGroup(ipGroup);
    }

    /**
    * Creates a new ip group with the primary key. Does not add the ip group to the database.
    *
    * @param groupId the primary key for the new ip group
    * @return the new ip group
    */
    @Override
    public za.co.idea.ip.hook.model.IpGroup createIpGroup(long groupId) {
        return _ipGroupLocalService.createIpGroup(groupId);
    }

    /**
    * Deletes the ip group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param groupId the primary key of the ip group
    * @return the ip group that was removed
    * @throws PortalException if a ip group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public za.co.idea.ip.hook.model.IpGroup deleteIpGroup(long groupId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ipGroupLocalService.deleteIpGroup(groupId);
    }

    /**
    * Deletes the ip group from the database. Also notifies the appropriate model listeners.
    *
    * @param ipGroup the ip group
    * @return the ip group that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public za.co.idea.ip.hook.model.IpGroup deleteIpGroup(
        za.co.idea.ip.hook.model.IpGroup ipGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipGroupLocalService.deleteIpGroup(ipGroup);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ipGroupLocalService.dynamicQuery();
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
        return _ipGroupLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ipGroupLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ipGroupLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _ipGroupLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ipGroupLocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public za.co.idea.ip.hook.model.IpGroup fetchIpGroup(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipGroupLocalService.fetchIpGroup(groupId);
    }

    /**
    * Returns the ip group with the primary key.
    *
    * @param groupId the primary key of the ip group
    * @return the ip group
    * @throws PortalException if a ip group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public za.co.idea.ip.hook.model.IpGroup getIpGroup(long groupId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ipGroupLocalService.getIpGroup(groupId);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ipGroupLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ip groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ip groups
    * @param end the upper bound of the range of ip groups (not inclusive)
    * @return the range of ip groups
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<za.co.idea.ip.hook.model.IpGroup> getIpGroups(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipGroupLocalService.getIpGroups(start, end);
    }

    /**
    * Returns the number of ip groups.
    *
    * @return the number of ip groups
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIpGroupsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipGroupLocalService.getIpGroupsCount();
    }

    /**
    * Updates the ip group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ipGroup the ip group
    * @return the ip group that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public za.co.idea.ip.hook.model.IpGroup updateIpGroup(
        za.co.idea.ip.hook.model.IpGroup ipGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipGroupLocalService.updateIpGroup(ipGroup);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ipGroupLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ipGroupLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ipGroupLocalService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IpGroupLocalService getWrappedIpGroupLocalService() {
        return _ipGroupLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIpGroupLocalService(
        IpGroupLocalService ipGroupLocalService) {
        _ipGroupLocalService = ipGroupLocalService;
    }

    @Override
    public IpGroupLocalService getWrappedService() {
        return _ipGroupLocalService;
    }

    @Override
    public void setWrappedService(IpGroupLocalService ipGroupLocalService) {
        _ipGroupLocalService = ipGroupLocalService;
    }
}
