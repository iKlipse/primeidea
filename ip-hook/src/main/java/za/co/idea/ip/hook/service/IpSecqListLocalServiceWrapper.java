package za.co.idea.ip.hook.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IpSecqListLocalService}.
 *
 * @author VMPattamatta
 * @see IpSecqListLocalService
 * @generated
 */
public class IpSecqListLocalServiceWrapper implements IpSecqListLocalService,
    ServiceWrapper<IpSecqListLocalService> {
    private IpSecqListLocalService _ipSecqListLocalService;

    public IpSecqListLocalServiceWrapper(
        IpSecqListLocalService ipSecqListLocalService) {
        _ipSecqListLocalService = ipSecqListLocalService;
    }

    /**
    * Adds the ip secq list to the database. Also notifies the appropriate model listeners.
    *
    * @param ipSecqList the ip secq list
    * @return the ip secq list that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public za.co.idea.ip.hook.model.IpSecqList addIpSecqList(
        za.co.idea.ip.hook.model.IpSecqList ipSecqList)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipSecqListLocalService.addIpSecqList(ipSecqList);
    }

    /**
    * Creates a new ip secq list with the primary key. Does not add the ip secq list to the database.
    *
    * @param islId the primary key for the new ip secq list
    * @return the new ip secq list
    */
    @Override
    public za.co.idea.ip.hook.model.IpSecqList createIpSecqList(long islId) {
        return _ipSecqListLocalService.createIpSecqList(islId);
    }

    /**
    * Deletes the ip secq list with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param islId the primary key of the ip secq list
    * @return the ip secq list that was removed
    * @throws PortalException if a ip secq list with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public za.co.idea.ip.hook.model.IpSecqList deleteIpSecqList(long islId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ipSecqListLocalService.deleteIpSecqList(islId);
    }

    /**
    * Deletes the ip secq list from the database. Also notifies the appropriate model listeners.
    *
    * @param ipSecqList the ip secq list
    * @return the ip secq list that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public za.co.idea.ip.hook.model.IpSecqList deleteIpSecqList(
        za.co.idea.ip.hook.model.IpSecqList ipSecqList)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipSecqListLocalService.deleteIpSecqList(ipSecqList);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ipSecqListLocalService.dynamicQuery();
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
        return _ipSecqListLocalService.dynamicQuery(dynamicQuery);
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
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _ipSecqListLocalService.dynamicQuery(dynamicQuery, start, end);
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
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipSecqListLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _ipSecqListLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ipSecqListLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public za.co.idea.ip.hook.model.IpSecqList fetchIpSecqList(long islId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipSecqListLocalService.fetchIpSecqList(islId);
    }

    /**
    * Returns the ip secq list with the primary key.
    *
    * @param islId the primary key of the ip secq list
    * @return the ip secq list
    * @throws PortalException if a ip secq list with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public za.co.idea.ip.hook.model.IpSecqList getIpSecqList(long islId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ipSecqListLocalService.getIpSecqList(islId);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ipSecqListLocalService.getPersistedModel(primaryKeyObj);
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
    @Override
    public java.util.List<za.co.idea.ip.hook.model.IpSecqList> getIpSecqLists(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipSecqListLocalService.getIpSecqLists(start, end);
    }

    /**
    * Returns the number of ip secq lists.
    *
    * @return the number of ip secq lists
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIpSecqListsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipSecqListLocalService.getIpSecqListsCount();
    }

    /**
    * Updates the ip secq list in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ipSecqList the ip secq list
    * @return the ip secq list that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public za.co.idea.ip.hook.model.IpSecqList updateIpSecqList(
        za.co.idea.ip.hook.model.IpSecqList ipSecqList)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ipSecqListLocalService.updateIpSecqList(ipSecqList);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ipSecqListLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ipSecqListLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ipSecqListLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IpSecqListLocalService getWrappedIpSecqListLocalService() {
        return _ipSecqListLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIpSecqListLocalService(
        IpSecqListLocalService ipSecqListLocalService) {
        _ipSecqListLocalService = ipSecqListLocalService;
    }

    @Override
    public IpSecqListLocalService getWrappedService() {
        return _ipSecqListLocalService;
    }

    @Override
    public void setWrappedService(IpSecqListLocalService ipSecqListLocalService) {
        _ipSecqListLocalService = ipSecqListLocalService;
    }
}
