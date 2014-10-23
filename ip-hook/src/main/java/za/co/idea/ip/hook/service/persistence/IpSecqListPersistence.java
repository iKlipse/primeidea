package za.co.idea.ip.hook.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import za.co.idea.ip.hook.model.IpSecqList;

/**
 * The persistence interface for the ip secq list service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author VMPattamatta
 * @see IpSecqListPersistenceImpl
 * @see IpSecqListUtil
 * @generated
 */
public interface IpSecqListPersistence extends BasePersistence<IpSecqList> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IpSecqListUtil} to access the ip secq list persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the ip secq lists where islId = &#63;.
    *
    * @param islId the isl ID
    * @return the matching ip secq lists
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<za.co.idea.ip.hook.model.IpSecqList> findBygetIpSecqList(
        long islId) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ip secq lists where islId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpSecqListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param islId the isl ID
    * @param start the lower bound of the range of ip secq lists
    * @param end the upper bound of the range of ip secq lists (not inclusive)
    * @return the range of matching ip secq lists
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<za.co.idea.ip.hook.model.IpSecqList> findBygetIpSecqList(
        long islId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ip secq lists where islId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpSecqListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param islId the isl ID
    * @param start the lower bound of the range of ip secq lists
    * @param end the upper bound of the range of ip secq lists (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ip secq lists
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<za.co.idea.ip.hook.model.IpSecqList> findBygetIpSecqList(
        long islId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first ip secq list in the ordered set where islId = &#63;.
    *
    * @param islId the isl ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip secq list
    * @throws za.co.idea.ip.hook.NoSuchIpSecqListException if a matching ip secq list could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpSecqList findBygetIpSecqList_First(
        long islId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpSecqListException;

    /**
    * Returns the first ip secq list in the ordered set where islId = &#63;.
    *
    * @param islId the isl ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip secq list, or <code>null</code> if a matching ip secq list could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpSecqList fetchBygetIpSecqList_First(
        long islId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last ip secq list in the ordered set where islId = &#63;.
    *
    * @param islId the isl ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip secq list
    * @throws za.co.idea.ip.hook.NoSuchIpSecqListException if a matching ip secq list could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpSecqList findBygetIpSecqList_Last(
        long islId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpSecqListException;

    /**
    * Returns the last ip secq list in the ordered set where islId = &#63;.
    *
    * @param islId the isl ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip secq list, or <code>null</code> if a matching ip secq list could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpSecqList fetchBygetIpSecqList_Last(
        long islId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the ip secq lists where islId = &#63; from the database.
    *
    * @param islId the isl ID
    * @throws SystemException if a system exception occurred
    */
    public void removeBygetIpSecqList(long islId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ip secq lists where islId = &#63;.
    *
    * @param islId the isl ID
    * @return the number of matching ip secq lists
    * @throws SystemException if a system exception occurred
    */
    public int countBygetIpSecqList(long islId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the ip secq list in the entity cache if it is enabled.
    *
    * @param ipSecqList the ip secq list
    */
    public void cacheResult(za.co.idea.ip.hook.model.IpSecqList ipSecqList);

    /**
    * Caches the ip secq lists in the entity cache if it is enabled.
    *
    * @param ipSecqLists the ip secq lists
    */
    public void cacheResult(
        java.util.List<za.co.idea.ip.hook.model.IpSecqList> ipSecqLists);

    /**
    * Creates a new ip secq list with the primary key. Does not add the ip secq list to the database.
    *
    * @param islId the primary key for the new ip secq list
    * @return the new ip secq list
    */
    public za.co.idea.ip.hook.model.IpSecqList create(long islId);

    /**
    * Removes the ip secq list with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param islId the primary key of the ip secq list
    * @return the ip secq list that was removed
    * @throws za.co.idea.ip.hook.NoSuchIpSecqListException if a ip secq list with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpSecqList remove(long islId)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpSecqListException;

    public za.co.idea.ip.hook.model.IpSecqList updateImpl(
        za.co.idea.ip.hook.model.IpSecqList ipSecqList)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the ip secq list with the primary key or throws a {@link za.co.idea.ip.hook.NoSuchIpSecqListException} if it could not be found.
    *
    * @param islId the primary key of the ip secq list
    * @return the ip secq list
    * @throws za.co.idea.ip.hook.NoSuchIpSecqListException if a ip secq list with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpSecqList findByPrimaryKey(long islId)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpSecqListException;

    /**
    * Returns the ip secq list with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param islId the primary key of the ip secq list
    * @return the ip secq list, or <code>null</code> if a ip secq list with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpSecqList fetchByPrimaryKey(long islId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the ip secq lists.
    *
    * @return the ip secq lists
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<za.co.idea.ip.hook.model.IpSecqList> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<za.co.idea.ip.hook.model.IpSecqList> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ip secq lists.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpSecqListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ip secq lists
    * @param end the upper bound of the range of ip secq lists (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ip secq lists
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<za.co.idea.ip.hook.model.IpSecqList> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the ip secq lists from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ip secq lists.
    *
    * @return the number of ip secq lists
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
