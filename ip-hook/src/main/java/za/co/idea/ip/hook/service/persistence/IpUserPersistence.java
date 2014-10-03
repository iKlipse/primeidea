package za.co.idea.ip.hook.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import za.co.idea.ip.hook.model.IpUser;

/**
 * The persistence interface for the ip user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author VMPattamatta
 * @see IpUserPersistenceImpl
 * @see IpUserUtil
 * @generated
 */
public interface IpUserPersistence extends BasePersistence<IpUser> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IpUserUtil} to access the ip user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the ip users where userId = &#63;.
    *
    * @param userId the user ID
    * @return the matching ip users
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<za.co.idea.ip.hook.model.IpUser> findBygetIpUser(
        long userId) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ip users where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of ip users
    * @param end the upper bound of the range of ip users (not inclusive)
    * @return the range of matching ip users
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<za.co.idea.ip.hook.model.IpUser> findBygetIpUser(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ip users where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of ip users
    * @param end the upper bound of the range of ip users (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ip users
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<za.co.idea.ip.hook.model.IpUser> findBygetIpUser(
        long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first ip user in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip user
    * @throws za.co.idea.ip.hook.NoSuchIpUserException if a matching ip user could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpUser findBygetIpUser_First(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpUserException;

    /**
    * Returns the first ip user in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip user, or <code>null</code> if a matching ip user could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpUser fetchBygetIpUser_First(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last ip user in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip user
    * @throws za.co.idea.ip.hook.NoSuchIpUserException if a matching ip user could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpUser findBygetIpUser_Last(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpUserException;

    /**
    * Returns the last ip user in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip user, or <code>null</code> if a matching ip user could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpUser fetchBygetIpUser_Last(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the ip users where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public void removeBygetIpUser(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ip users where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching ip users
    * @throws SystemException if a system exception occurred
    */
    public int countBygetIpUser(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the ip users where userScreenName = &#63;.
    *
    * @param userScreenName the user screen name
    * @return the matching ip users
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<za.co.idea.ip.hook.model.IpUser> findBygetIpUserScreenName(
        java.lang.String userScreenName)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ip users where userScreenName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param userScreenName the user screen name
    * @param start the lower bound of the range of ip users
    * @param end the upper bound of the range of ip users (not inclusive)
    * @return the range of matching ip users
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<za.co.idea.ip.hook.model.IpUser> findBygetIpUserScreenName(
        java.lang.String userScreenName, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ip users where userScreenName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param userScreenName the user screen name
    * @param start the lower bound of the range of ip users
    * @param end the upper bound of the range of ip users (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ip users
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<za.co.idea.ip.hook.model.IpUser> findBygetIpUserScreenName(
        java.lang.String userScreenName, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first ip user in the ordered set where userScreenName = &#63;.
    *
    * @param userScreenName the user screen name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip user
    * @throws za.co.idea.ip.hook.NoSuchIpUserException if a matching ip user could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpUser findBygetIpUserScreenName_First(
        java.lang.String userScreenName,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpUserException;

    /**
    * Returns the first ip user in the ordered set where userScreenName = &#63;.
    *
    * @param userScreenName the user screen name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip user, or <code>null</code> if a matching ip user could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpUser fetchBygetIpUserScreenName_First(
        java.lang.String userScreenName,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last ip user in the ordered set where userScreenName = &#63;.
    *
    * @param userScreenName the user screen name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip user
    * @throws za.co.idea.ip.hook.NoSuchIpUserException if a matching ip user could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpUser findBygetIpUserScreenName_Last(
        java.lang.String userScreenName,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpUserException;

    /**
    * Returns the last ip user in the ordered set where userScreenName = &#63;.
    *
    * @param userScreenName the user screen name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip user, or <code>null</code> if a matching ip user could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpUser fetchBygetIpUserScreenName_Last(
        java.lang.String userScreenName,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the ip users before and after the current ip user in the ordered set where userScreenName = &#63;.
    *
    * @param userId the primary key of the current ip user
    * @param userScreenName the user screen name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ip user
    * @throws za.co.idea.ip.hook.NoSuchIpUserException if a ip user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpUser[] findBygetIpUserScreenName_PrevAndNext(
        long userId, java.lang.String userScreenName,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpUserException;

    /**
    * Removes all the ip users where userScreenName = &#63; from the database.
    *
    * @param userScreenName the user screen name
    * @throws SystemException if a system exception occurred
    */
    public void removeBygetIpUserScreenName(java.lang.String userScreenName)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ip users where userScreenName = &#63;.
    *
    * @param userScreenName the user screen name
    * @return the number of matching ip users
    * @throws SystemException if a system exception occurred
    */
    public int countBygetIpUserScreenName(java.lang.String userScreenName)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the ip user in the entity cache if it is enabled.
    *
    * @param ipUser the ip user
    */
    public void cacheResult(za.co.idea.ip.hook.model.IpUser ipUser);

    /**
    * Caches the ip users in the entity cache if it is enabled.
    *
    * @param ipUsers the ip users
    */
    public void cacheResult(
        java.util.List<za.co.idea.ip.hook.model.IpUser> ipUsers);

    /**
    * Creates a new ip user with the primary key. Does not add the ip user to the database.
    *
    * @param userId the primary key for the new ip user
    * @return the new ip user
    */
    public za.co.idea.ip.hook.model.IpUser create(long userId);

    /**
    * Removes the ip user with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param userId the primary key of the ip user
    * @return the ip user that was removed
    * @throws za.co.idea.ip.hook.NoSuchIpUserException if a ip user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpUser remove(long userId)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpUserException;

    public za.co.idea.ip.hook.model.IpUser updateImpl(
        za.co.idea.ip.hook.model.IpUser ipUser)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the ip user with the primary key or throws a {@link za.co.idea.ip.hook.NoSuchIpUserException} if it could not be found.
    *
    * @param userId the primary key of the ip user
    * @return the ip user
    * @throws za.co.idea.ip.hook.NoSuchIpUserException if a ip user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpUser findByPrimaryKey(long userId)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpUserException;

    /**
    * Returns the ip user with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param userId the primary key of the ip user
    * @return the ip user, or <code>null</code> if a ip user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpUser fetchByPrimaryKey(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the ip users.
    *
    * @return the ip users
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<za.co.idea.ip.hook.model.IpUser> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<za.co.idea.ip.hook.model.IpUser> findAll(int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ip users.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ip users
    * @param end the upper bound of the range of ip users (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ip users
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<za.co.idea.ip.hook.model.IpUser> findAll(int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the ip users from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ip users.
    *
    * @return the number of ip users
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
