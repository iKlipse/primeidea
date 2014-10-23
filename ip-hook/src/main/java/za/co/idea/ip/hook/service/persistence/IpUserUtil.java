package za.co.idea.ip.hook.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import za.co.idea.ip.hook.model.IpUser;

import java.util.List;

/**
 * The persistence utility for the ip user service. This utility wraps {@link IpUserPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author VMPattamatta
 * @see IpUserPersistence
 * @see IpUserPersistenceImpl
 * @generated
 */
@SuppressWarnings("unchecked")
public class IpUserUtil {
    private static IpUserPersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
     */
    public static void clearCache(IpUser ipUser) {
        getPersistence().clearCache(ipUser);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<IpUser> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IpUser> findWithDynamicQuery(DynamicQuery dynamicQuery,
        int start, int end) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IpUser> findWithDynamicQuery(DynamicQuery dynamicQuery,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static IpUser update(IpUser ipUser) throws SystemException {
        return getPersistence().update(ipUser);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static IpUser update(IpUser ipUser, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(ipUser, serviceContext);
    }

    /**
    * Returns all the ip users where userId = &#63;.
    *
    * @param userId the user ID
    * @return the matching ip users
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<za.co.idea.ip.hook.model.IpUser> findBygetIpUser(
        long userId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBygetIpUser(userId);
    }

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
    public static java.util.List<za.co.idea.ip.hook.model.IpUser> findBygetIpUser(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBygetIpUser(userId, start, end);
    }

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
    public static java.util.List<za.co.idea.ip.hook.model.IpUser> findBygetIpUser(
        long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBygetIpUser(userId, start, end, orderByComparator);
    }

    /**
    * Returns the first ip user in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip user
    * @throws za.co.idea.ip.hook.NoSuchIpUserException if a matching ip user could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpUser findBygetIpUser_First(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpUserException {
        return getPersistence().findBygetIpUser_First(userId, orderByComparator);
    }

    /**
    * Returns the first ip user in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip user, or <code>null</code> if a matching ip user could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpUser fetchBygetIpUser_First(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchBygetIpUser_First(userId, orderByComparator);
    }

    /**
    * Returns the last ip user in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip user
    * @throws za.co.idea.ip.hook.NoSuchIpUserException if a matching ip user could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpUser findBygetIpUser_Last(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpUserException {
        return getPersistence().findBygetIpUser_Last(userId, orderByComparator);
    }

    /**
    * Returns the last ip user in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip user, or <code>null</code> if a matching ip user could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpUser fetchBygetIpUser_Last(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchBygetIpUser_Last(userId, orderByComparator);
    }

    /**
    * Removes all the ip users where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeBygetIpUser(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeBygetIpUser(userId);
    }

    /**
    * Returns the number of ip users where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching ip users
    * @throws SystemException if a system exception occurred
    */
    public static int countBygetIpUser(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countBygetIpUser(userId);
    }

    /**
    * Returns all the ip users where userScreenName = &#63;.
    *
    * @param userScreenName the user screen name
    * @return the matching ip users
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<za.co.idea.ip.hook.model.IpUser> findBygetIpUserScreenName(
        java.lang.String userScreenName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBygetIpUserScreenName(userScreenName);
    }

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
    public static java.util.List<za.co.idea.ip.hook.model.IpUser> findBygetIpUserScreenName(
        java.lang.String userScreenName, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBygetIpUserScreenName(userScreenName, start, end);
    }

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
    public static java.util.List<za.co.idea.ip.hook.model.IpUser> findBygetIpUserScreenName(
        java.lang.String userScreenName, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBygetIpUserScreenName(userScreenName, start, end,
            orderByComparator);
    }

    /**
    * Returns the first ip user in the ordered set where userScreenName = &#63;.
    *
    * @param userScreenName the user screen name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip user
    * @throws za.co.idea.ip.hook.NoSuchIpUserException if a matching ip user could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpUser findBygetIpUserScreenName_First(
        java.lang.String userScreenName,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpUserException {
        return getPersistence()
                   .findBygetIpUserScreenName_First(userScreenName,
            orderByComparator);
    }

    /**
    * Returns the first ip user in the ordered set where userScreenName = &#63;.
    *
    * @param userScreenName the user screen name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip user, or <code>null</code> if a matching ip user could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpUser fetchBygetIpUserScreenName_First(
        java.lang.String userScreenName,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBygetIpUserScreenName_First(userScreenName,
            orderByComparator);
    }

    /**
    * Returns the last ip user in the ordered set where userScreenName = &#63;.
    *
    * @param userScreenName the user screen name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip user
    * @throws za.co.idea.ip.hook.NoSuchIpUserException if a matching ip user could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpUser findBygetIpUserScreenName_Last(
        java.lang.String userScreenName,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpUserException {
        return getPersistence()
                   .findBygetIpUserScreenName_Last(userScreenName,
            orderByComparator);
    }

    /**
    * Returns the last ip user in the ordered set where userScreenName = &#63;.
    *
    * @param userScreenName the user screen name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip user, or <code>null</code> if a matching ip user could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpUser fetchBygetIpUserScreenName_Last(
        java.lang.String userScreenName,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBygetIpUserScreenName_Last(userScreenName,
            orderByComparator);
    }

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
    public static za.co.idea.ip.hook.model.IpUser[] findBygetIpUserScreenName_PrevAndNext(
        long userId, java.lang.String userScreenName,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpUserException {
        return getPersistence()
                   .findBygetIpUserScreenName_PrevAndNext(userId,
            userScreenName, orderByComparator);
    }

    /**
    * Removes all the ip users where userScreenName = &#63; from the database.
    *
    * @param userScreenName the user screen name
    * @throws SystemException if a system exception occurred
    */
    public static void removeBygetIpUserScreenName(
        java.lang.String userScreenName)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeBygetIpUserScreenName(userScreenName);
    }

    /**
    * Returns the number of ip users where userScreenName = &#63;.
    *
    * @param userScreenName the user screen name
    * @return the number of matching ip users
    * @throws SystemException if a system exception occurred
    */
    public static int countBygetIpUserScreenName(
        java.lang.String userScreenName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countBygetIpUserScreenName(userScreenName);
    }

    /**
    * Caches the ip user in the entity cache if it is enabled.
    *
    * @param ipUser the ip user
    */
    public static void cacheResult(za.co.idea.ip.hook.model.IpUser ipUser) {
        getPersistence().cacheResult(ipUser);
    }

    /**
    * Caches the ip users in the entity cache if it is enabled.
    *
    * @param ipUsers the ip users
    */
    public static void cacheResult(
        java.util.List<za.co.idea.ip.hook.model.IpUser> ipUsers) {
        getPersistence().cacheResult(ipUsers);
    }

    /**
    * Creates a new ip user with the primary key. Does not add the ip user to the database.
    *
    * @param userId the primary key for the new ip user
    * @return the new ip user
    */
    public static za.co.idea.ip.hook.model.IpUser create(long userId) {
        return getPersistence().create(userId);
    }

    /**
    * Removes the ip user with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param userId the primary key of the ip user
    * @return the ip user that was removed
    * @throws za.co.idea.ip.hook.NoSuchIpUserException if a ip user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpUser remove(long userId)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpUserException {
        return getPersistence().remove(userId);
    }

    public static za.co.idea.ip.hook.model.IpUser updateImpl(
        za.co.idea.ip.hook.model.IpUser ipUser)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ipUser);
    }

    /**
    * Returns the ip user with the primary key or throws a {@link za.co.idea.ip.hook.NoSuchIpUserException} if it could not be found.
    *
    * @param userId the primary key of the ip user
    * @return the ip user
    * @throws za.co.idea.ip.hook.NoSuchIpUserException if a ip user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpUser findByPrimaryKey(long userId)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpUserException {
        return getPersistence().findByPrimaryKey(userId);
    }

    /**
    * Returns the ip user with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param userId the primary key of the ip user
    * @return the ip user, or <code>null</code> if a ip user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpUser fetchByPrimaryKey(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(userId);
    }

    /**
    * Returns all the ip users.
    *
    * @return the ip users
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<za.co.idea.ip.hook.model.IpUser> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
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
    public static java.util.List<za.co.idea.ip.hook.model.IpUser> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<za.co.idea.ip.hook.model.IpUser> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ip users from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ip users.
    *
    * @return the number of ip users
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IpUserPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IpUserPersistence) PortletBeanLocatorUtil.locate(za.co.idea.ip.hook.service.ClpSerializer.getServletContextName(),
                    IpUserPersistence.class.getName());

            ReferenceRegistry.registerReference(IpUserUtil.class, "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IpUserPersistence persistence) {
    }
}
