package za.co.idea.ip.hook.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import za.co.idea.ip.hook.model.IpGroup;

/**
 * The persistence interface for the ip group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author VMPattamatta
 * @see IpGroupPersistenceImpl
 * @see IpGroupUtil
 * @generated
 */
public interface IpGroupPersistence extends BasePersistence<IpGroup> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IpGroupUtil} to access the ip group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the ip groups where groupId = &#63;.
    *
    * @param groupId the group ID
    * @return the matching ip groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<za.co.idea.ip.hook.model.IpGroup> findBygetIpGroup(
        long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ip groups where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param groupId the group ID
    * @param start the lower bound of the range of ip groups
    * @param end the upper bound of the range of ip groups (not inclusive)
    * @return the range of matching ip groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<za.co.idea.ip.hook.model.IpGroup> findBygetIpGroup(
        long groupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ip groups where groupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param groupId the group ID
    * @param start the lower bound of the range of ip groups
    * @param end the upper bound of the range of ip groups (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ip groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<za.co.idea.ip.hook.model.IpGroup> findBygetIpGroup(
        long groupId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first ip group in the ordered set where groupId = &#63;.
    *
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip group
    * @throws za.co.idea.ip.hook.NoSuchIpGroupException if a matching ip group could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpGroup findBygetIpGroup_First(
        long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpGroupException;

    /**
    * Returns the first ip group in the ordered set where groupId = &#63;.
    *
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip group, or <code>null</code> if a matching ip group could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpGroup fetchBygetIpGroup_First(
        long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last ip group in the ordered set where groupId = &#63;.
    *
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip group
    * @throws za.co.idea.ip.hook.NoSuchIpGroupException if a matching ip group could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpGroup findBygetIpGroup_Last(
        long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpGroupException;

    /**
    * Returns the last ip group in the ordered set where groupId = &#63;.
    *
    * @param groupId the group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip group, or <code>null</code> if a matching ip group could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpGroup fetchBygetIpGroup_Last(
        long groupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the ip groups where groupId = &#63; from the database.
    *
    * @param groupId the group ID
    * @throws SystemException if a system exception occurred
    */
    public void removeBygetIpGroup(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ip groups where groupId = &#63;.
    *
    * @param groupId the group ID
    * @return the number of matching ip groups
    * @throws SystemException if a system exception occurred
    */
    public int countBygetIpGroup(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the ip group in the entity cache if it is enabled.
    *
    * @param ipGroup the ip group
    */
    public void cacheResult(za.co.idea.ip.hook.model.IpGroup ipGroup);

    /**
    * Caches the ip groups in the entity cache if it is enabled.
    *
    * @param ipGroups the ip groups
    */
    public void cacheResult(
        java.util.List<za.co.idea.ip.hook.model.IpGroup> ipGroups);

    /**
    * Creates a new ip group with the primary key. Does not add the ip group to the database.
    *
    * @param groupId the primary key for the new ip group
    * @return the new ip group
    */
    public za.co.idea.ip.hook.model.IpGroup create(long groupId);

    /**
    * Removes the ip group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param groupId the primary key of the ip group
    * @return the ip group that was removed
    * @throws za.co.idea.ip.hook.NoSuchIpGroupException if a ip group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpGroup remove(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpGroupException;

    public za.co.idea.ip.hook.model.IpGroup updateImpl(
        za.co.idea.ip.hook.model.IpGroup ipGroup)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the ip group with the primary key or throws a {@link za.co.idea.ip.hook.NoSuchIpGroupException} if it could not be found.
    *
    * @param groupId the primary key of the ip group
    * @return the ip group
    * @throws za.co.idea.ip.hook.NoSuchIpGroupException if a ip group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpGroup findByPrimaryKey(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpGroupException;

    /**
    * Returns the ip group with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param groupId the primary key of the ip group
    * @return the ip group, or <code>null</code> if a ip group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpGroup fetchByPrimaryKey(long groupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the ip groups.
    *
    * @return the ip groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<za.co.idea.ip.hook.model.IpGroup> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<za.co.idea.ip.hook.model.IpGroup> findAll(int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ip groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ip groups
    * @param end the upper bound of the range of ip groups (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ip groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<za.co.idea.ip.hook.model.IpGroup> findAll(int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the ip groups from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ip groups.
    *
    * @return the number of ip groups
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
