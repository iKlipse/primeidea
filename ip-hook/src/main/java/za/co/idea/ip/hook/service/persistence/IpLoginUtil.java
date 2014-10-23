package za.co.idea.ip.hook.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import za.co.idea.ip.hook.model.IpLogin;

import java.util.List;

/**
 * The persistence utility for the ip login service. This utility wraps {@link IpLoginPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author VMPattamatta
 * @see IpLoginPersistence
 * @see IpLoginPersistenceImpl
 * @generated
 */
@SuppressWarnings("unchecked")
public class IpLoginUtil {
    private static IpLoginPersistence _persistence;

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
    public static void clearCache(IpLogin ipLogin) {
        getPersistence().clearCache(ipLogin);
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
    public static List<IpLogin> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<IpLogin> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<IpLogin> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static IpLogin update(IpLogin ipLogin) throws SystemException {
        return getPersistence().update(ipLogin);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static IpLogin update(IpLogin ipLogin, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(ipLogin, serviceContext);
    }

    /**
    * Returns all the ip logins where loginId = &#63;.
    *
    * @param loginId the login ID
    * @return the matching ip logins
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<za.co.idea.ip.hook.model.IpLogin> findBygetIpLogin(
        long loginId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBygetIpLogin(loginId);
    }

    /**
    * Returns a range of all the ip logins where loginId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param loginId the login ID
    * @param start the lower bound of the range of ip logins
    * @param end the upper bound of the range of ip logins (not inclusive)
    * @return the range of matching ip logins
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<za.co.idea.ip.hook.model.IpLogin> findBygetIpLogin(
        long loginId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBygetIpLogin(loginId, start, end);
    }

    /**
    * Returns an ordered range of all the ip logins where loginId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param loginId the login ID
    * @param start the lower bound of the range of ip logins
    * @param end the upper bound of the range of ip logins (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ip logins
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<za.co.idea.ip.hook.model.IpLogin> findBygetIpLogin(
        long loginId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBygetIpLogin(loginId, start, end, orderByComparator);
    }

    /**
    * Returns the first ip login in the ordered set where loginId = &#63;.
    *
    * @param loginId the login ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip login
    * @throws za.co.idea.ip.hook.NoSuchIpLoginException if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin findBygetIpLogin_First(
        long loginId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException {
        return getPersistence()
                   .findBygetIpLogin_First(loginId, orderByComparator);
    }

    /**
    * Returns the first ip login in the ordered set where loginId = &#63;.
    *
    * @param loginId the login ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip login, or <code>null</code> if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin fetchBygetIpLogin_First(
        long loginId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBygetIpLogin_First(loginId, orderByComparator);
    }

    /**
    * Returns the last ip login in the ordered set where loginId = &#63;.
    *
    * @param loginId the login ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip login
    * @throws za.co.idea.ip.hook.NoSuchIpLoginException if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin findBygetIpLogin_Last(
        long loginId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException {
        return getPersistence().findBygetIpLogin_Last(loginId, orderByComparator);
    }

    /**
    * Returns the last ip login in the ordered set where loginId = &#63;.
    *
    * @param loginId the login ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip login, or <code>null</code> if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin fetchBygetIpLogin_Last(
        long loginId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBygetIpLogin_Last(loginId, orderByComparator);
    }

    /**
    * Removes all the ip logins where loginId = &#63; from the database.
    *
    * @param loginId the login ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeBygetIpLogin(long loginId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeBygetIpLogin(loginId);
    }

    /**
    * Returns the number of ip logins where loginId = &#63;.
    *
    * @param loginId the login ID
    * @return the number of matching ip logins
    * @throws SystemException if a system exception occurred
    */
    public static int countBygetIpLogin(long loginId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countBygetIpLogin(loginId);
    }

    /**
    * Returns all the ip logins where loginName = &#63; and loginPwd = &#63;.
    *
    * @param loginName the login name
    * @param loginPwd the login pwd
    * @return the matching ip logins
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<za.co.idea.ip.hook.model.IpLogin> findBygetIpLoginScreenName_Password(
        java.lang.String loginName, java.lang.String loginPwd)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBygetIpLoginScreenName_Password(loginName, loginPwd);
    }

    /**
    * Returns a range of all the ip logins where loginName = &#63; and loginPwd = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param loginName the login name
    * @param loginPwd the login pwd
    * @param start the lower bound of the range of ip logins
    * @param end the upper bound of the range of ip logins (not inclusive)
    * @return the range of matching ip logins
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<za.co.idea.ip.hook.model.IpLogin> findBygetIpLoginScreenName_Password(
        java.lang.String loginName, java.lang.String loginPwd, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBygetIpLoginScreenName_Password(loginName, loginPwd,
            start, end);
    }

    /**
    * Returns an ordered range of all the ip logins where loginName = &#63; and loginPwd = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param loginName the login name
    * @param loginPwd the login pwd
    * @param start the lower bound of the range of ip logins
    * @param end the upper bound of the range of ip logins (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ip logins
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<za.co.idea.ip.hook.model.IpLogin> findBygetIpLoginScreenName_Password(
        java.lang.String loginName, java.lang.String loginPwd, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBygetIpLoginScreenName_Password(loginName, loginPwd,
            start, end, orderByComparator);
    }

    /**
    * Returns the first ip login in the ordered set where loginName = &#63; and loginPwd = &#63;.
    *
    * @param loginName the login name
    * @param loginPwd the login pwd
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip login
    * @throws za.co.idea.ip.hook.NoSuchIpLoginException if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin findBygetIpLoginScreenName_Password_First(
        java.lang.String loginName, java.lang.String loginPwd,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException {
        return getPersistence()
                   .findBygetIpLoginScreenName_Password_First(loginName,
            loginPwd, orderByComparator);
    }

    /**
    * Returns the first ip login in the ordered set where loginName = &#63; and loginPwd = &#63;.
    *
    * @param loginName the login name
    * @param loginPwd the login pwd
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip login, or <code>null</code> if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin fetchBygetIpLoginScreenName_Password_First(
        java.lang.String loginName, java.lang.String loginPwd,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBygetIpLoginScreenName_Password_First(loginName,
            loginPwd, orderByComparator);
    }

    /**
    * Returns the last ip login in the ordered set where loginName = &#63; and loginPwd = &#63;.
    *
    * @param loginName the login name
    * @param loginPwd the login pwd
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip login
    * @throws za.co.idea.ip.hook.NoSuchIpLoginException if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin findBygetIpLoginScreenName_Password_Last(
        java.lang.String loginName, java.lang.String loginPwd,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException {
        return getPersistence()
                   .findBygetIpLoginScreenName_Password_Last(loginName,
            loginPwd, orderByComparator);
    }

    /**
    * Returns the last ip login in the ordered set where loginName = &#63; and loginPwd = &#63;.
    *
    * @param loginName the login name
    * @param loginPwd the login pwd
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip login, or <code>null</code> if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin fetchBygetIpLoginScreenName_Password_Last(
        java.lang.String loginName, java.lang.String loginPwd,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBygetIpLoginScreenName_Password_Last(loginName,
            loginPwd, orderByComparator);
    }

    /**
    * Returns the ip logins before and after the current ip login in the ordered set where loginName = &#63; and loginPwd = &#63;.
    *
    * @param loginId the primary key of the current ip login
    * @param loginName the login name
    * @param loginPwd the login pwd
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ip login
    * @throws za.co.idea.ip.hook.NoSuchIpLoginException if a ip login with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin[] findBygetIpLoginScreenName_Password_PrevAndNext(
        long loginId, java.lang.String loginName, java.lang.String loginPwd,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException {
        return getPersistence()
                   .findBygetIpLoginScreenName_Password_PrevAndNext(loginId,
            loginName, loginPwd, orderByComparator);
    }

    /**
    * Removes all the ip logins where loginName = &#63; and loginPwd = &#63; from the database.
    *
    * @param loginName the login name
    * @param loginPwd the login pwd
    * @throws SystemException if a system exception occurred
    */
    public static void removeBygetIpLoginScreenName_Password(
        java.lang.String loginName, java.lang.String loginPwd)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeBygetIpLoginScreenName_Password(loginName, loginPwd);
    }

    /**
    * Returns the number of ip logins where loginName = &#63; and loginPwd = &#63;.
    *
    * @param loginName the login name
    * @param loginPwd the login pwd
    * @return the number of matching ip logins
    * @throws SystemException if a system exception occurred
    */
    public static int countBygetIpLoginScreenName_Password(
        java.lang.String loginName, java.lang.String loginPwd)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countBygetIpLoginScreenName_Password(loginName, loginPwd);
    }

    /**
    * Returns all the ip logins where loginName = &#63;.
    *
    * @param loginName the login name
    * @return the matching ip logins
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<za.co.idea.ip.hook.model.IpLogin> findBygetIpLoginScreenName(
        java.lang.String loginName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBygetIpLoginScreenName(loginName);
    }

    /**
    * Returns a range of all the ip logins where loginName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param loginName the login name
    * @param start the lower bound of the range of ip logins
    * @param end the upper bound of the range of ip logins (not inclusive)
    * @return the range of matching ip logins
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<za.co.idea.ip.hook.model.IpLogin> findBygetIpLoginScreenName(
        java.lang.String loginName, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBygetIpLoginScreenName(loginName, start, end);
    }

    /**
    * Returns an ordered range of all the ip logins where loginName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param loginName the login name
    * @param start the lower bound of the range of ip logins
    * @param end the upper bound of the range of ip logins (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ip logins
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<za.co.idea.ip.hook.model.IpLogin> findBygetIpLoginScreenName(
        java.lang.String loginName, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBygetIpLoginScreenName(loginName, start, end,
            orderByComparator);
    }

    /**
    * Returns the first ip login in the ordered set where loginName = &#63;.
    *
    * @param loginName the login name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip login
    * @throws za.co.idea.ip.hook.NoSuchIpLoginException if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin findBygetIpLoginScreenName_First(
        java.lang.String loginName,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException {
        return getPersistence()
                   .findBygetIpLoginScreenName_First(loginName,
            orderByComparator);
    }

    /**
    * Returns the first ip login in the ordered set where loginName = &#63;.
    *
    * @param loginName the login name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip login, or <code>null</code> if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin fetchBygetIpLoginScreenName_First(
        java.lang.String loginName,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBygetIpLoginScreenName_First(loginName,
            orderByComparator);
    }

    /**
    * Returns the last ip login in the ordered set where loginName = &#63;.
    *
    * @param loginName the login name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip login
    * @throws za.co.idea.ip.hook.NoSuchIpLoginException if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin findBygetIpLoginScreenName_Last(
        java.lang.String loginName,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException {
        return getPersistence()
                   .findBygetIpLoginScreenName_Last(loginName, orderByComparator);
    }

    /**
    * Returns the last ip login in the ordered set where loginName = &#63;.
    *
    * @param loginName the login name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip login, or <code>null</code> if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin fetchBygetIpLoginScreenName_Last(
        java.lang.String loginName,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBygetIpLoginScreenName_Last(loginName,
            orderByComparator);
    }

    /**
    * Returns the ip logins before and after the current ip login in the ordered set where loginName = &#63;.
    *
    * @param loginId the primary key of the current ip login
    * @param loginName the login name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ip login
    * @throws za.co.idea.ip.hook.NoSuchIpLoginException if a ip login with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin[] findBygetIpLoginScreenName_PrevAndNext(
        long loginId, java.lang.String loginName,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException {
        return getPersistence()
                   .findBygetIpLoginScreenName_PrevAndNext(loginId, loginName,
            orderByComparator);
    }

    /**
    * Removes all the ip logins where loginName = &#63; from the database.
    *
    * @param loginName the login name
    * @throws SystemException if a system exception occurred
    */
    public static void removeBygetIpLoginScreenName(java.lang.String loginName)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeBygetIpLoginScreenName(loginName);
    }

    /**
    * Returns the number of ip logins where loginName = &#63;.
    *
    * @param loginName the login name
    * @return the number of matching ip logins
    * @throws SystemException if a system exception occurred
    */
    public static int countBygetIpLoginScreenName(java.lang.String loginName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countBygetIpLoginScreenName(loginName);
    }

    /**
    * Returns all the ip logins where loginName = &#63; and loginSecA = &#63;.
    *
    * @param loginName the login name
    * @param loginSecA the login sec a
    * @return the matching ip logins
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<za.co.idea.ip.hook.model.IpLogin> findBygetIpLoginScreenName_LoginSecA(
        java.lang.String loginName, java.lang.String loginSecA)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBygetIpLoginScreenName_LoginSecA(loginName, loginSecA);
    }

    /**
    * Returns a range of all the ip logins where loginName = &#63; and loginSecA = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param loginName the login name
    * @param loginSecA the login sec a
    * @param start the lower bound of the range of ip logins
    * @param end the upper bound of the range of ip logins (not inclusive)
    * @return the range of matching ip logins
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<za.co.idea.ip.hook.model.IpLogin> findBygetIpLoginScreenName_LoginSecA(
        java.lang.String loginName, java.lang.String loginSecA, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBygetIpLoginScreenName_LoginSecA(loginName, loginSecA,
            start, end);
    }

    /**
    * Returns an ordered range of all the ip logins where loginName = &#63; and loginSecA = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param loginName the login name
    * @param loginSecA the login sec a
    * @param start the lower bound of the range of ip logins
    * @param end the upper bound of the range of ip logins (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching ip logins
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<za.co.idea.ip.hook.model.IpLogin> findBygetIpLoginScreenName_LoginSecA(
        java.lang.String loginName, java.lang.String loginSecA, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBygetIpLoginScreenName_LoginSecA(loginName, loginSecA,
            start, end, orderByComparator);
    }

    /**
    * Returns the first ip login in the ordered set where loginName = &#63; and loginSecA = &#63;.
    *
    * @param loginName the login name
    * @param loginSecA the login sec a
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip login
    * @throws za.co.idea.ip.hook.NoSuchIpLoginException if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin findBygetIpLoginScreenName_LoginSecA_First(
        java.lang.String loginName, java.lang.String loginSecA,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException {
        return getPersistence()
                   .findBygetIpLoginScreenName_LoginSecA_First(loginName,
            loginSecA, orderByComparator);
    }

    /**
    * Returns the first ip login in the ordered set where loginName = &#63; and loginSecA = &#63;.
    *
    * @param loginName the login name
    * @param loginSecA the login sec a
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip login, or <code>null</code> if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin fetchBygetIpLoginScreenName_LoginSecA_First(
        java.lang.String loginName, java.lang.String loginSecA,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBygetIpLoginScreenName_LoginSecA_First(loginName,
            loginSecA, orderByComparator);
    }

    /**
    * Returns the last ip login in the ordered set where loginName = &#63; and loginSecA = &#63;.
    *
    * @param loginName the login name
    * @param loginSecA the login sec a
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip login
    * @throws za.co.idea.ip.hook.NoSuchIpLoginException if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin findBygetIpLoginScreenName_LoginSecA_Last(
        java.lang.String loginName, java.lang.String loginSecA,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException {
        return getPersistence()
                   .findBygetIpLoginScreenName_LoginSecA_Last(loginName,
            loginSecA, orderByComparator);
    }

    /**
    * Returns the last ip login in the ordered set where loginName = &#63; and loginSecA = &#63;.
    *
    * @param loginName the login name
    * @param loginSecA the login sec a
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip login, or <code>null</code> if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin fetchBygetIpLoginScreenName_LoginSecA_Last(
        java.lang.String loginName, java.lang.String loginSecA,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBygetIpLoginScreenName_LoginSecA_Last(loginName,
            loginSecA, orderByComparator);
    }

    /**
    * Returns the ip logins before and after the current ip login in the ordered set where loginName = &#63; and loginSecA = &#63;.
    *
    * @param loginId the primary key of the current ip login
    * @param loginName the login name
    * @param loginSecA the login sec a
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next ip login
    * @throws za.co.idea.ip.hook.NoSuchIpLoginException if a ip login with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin[] findBygetIpLoginScreenName_LoginSecA_PrevAndNext(
        long loginId, java.lang.String loginName, java.lang.String loginSecA,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException {
        return getPersistence()
                   .findBygetIpLoginScreenName_LoginSecA_PrevAndNext(loginId,
            loginName, loginSecA, orderByComparator);
    }

    /**
    * Removes all the ip logins where loginName = &#63; and loginSecA = &#63; from the database.
    *
    * @param loginName the login name
    * @param loginSecA the login sec a
    * @throws SystemException if a system exception occurred
    */
    public static void removeBygetIpLoginScreenName_LoginSecA(
        java.lang.String loginName, java.lang.String loginSecA)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeBygetIpLoginScreenName_LoginSecA(loginName, loginSecA);
    }

    /**
    * Returns the number of ip logins where loginName = &#63; and loginSecA = &#63;.
    *
    * @param loginName the login name
    * @param loginSecA the login sec a
    * @return the number of matching ip logins
    * @throws SystemException if a system exception occurred
    */
    public static int countBygetIpLoginScreenName_LoginSecA(
        java.lang.String loginName, java.lang.String loginSecA)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countBygetIpLoginScreenName_LoginSecA(loginName, loginSecA);
    }

    /**
    * Caches the ip login in the entity cache if it is enabled.
    *
    * @param ipLogin the ip login
    */
    public static void cacheResult(za.co.idea.ip.hook.model.IpLogin ipLogin) {
        getPersistence().cacheResult(ipLogin);
    }

    /**
    * Caches the ip logins in the entity cache if it is enabled.
    *
    * @param ipLogins the ip logins
    */
    public static void cacheResult(
        java.util.List<za.co.idea.ip.hook.model.IpLogin> ipLogins) {
        getPersistence().cacheResult(ipLogins);
    }

    /**
    * Creates a new ip login with the primary key. Does not add the ip login to the database.
    *
    * @param loginId the primary key for the new ip login
    * @return the new ip login
    */
    public static za.co.idea.ip.hook.model.IpLogin create(long loginId) {
        return getPersistence().create(loginId);
    }

    /**
    * Removes the ip login with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param loginId the primary key of the ip login
    * @return the ip login that was removed
    * @throws za.co.idea.ip.hook.NoSuchIpLoginException if a ip login with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin remove(long loginId)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException {
        return getPersistence().remove(loginId);
    }

    public static za.co.idea.ip.hook.model.IpLogin updateImpl(
        za.co.idea.ip.hook.model.IpLogin ipLogin)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ipLogin);
    }

    /**
    * Returns the ip login with the primary key or throws a {@link za.co.idea.ip.hook.NoSuchIpLoginException} if it could not be found.
    *
    * @param loginId the primary key of the ip login
    * @return the ip login
    * @throws za.co.idea.ip.hook.NoSuchIpLoginException if a ip login with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin findByPrimaryKey(
        long loginId)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException {
        return getPersistence().findByPrimaryKey(loginId);
    }

    /**
    * Returns the ip login with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param loginId the primary key of the ip login
    * @return the ip login, or <code>null</code> if a ip login with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static za.co.idea.ip.hook.model.IpLogin fetchByPrimaryKey(
        long loginId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(loginId);
    }

    /**
    * Returns all the ip logins.
    *
    * @return the ip logins
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<za.co.idea.ip.hook.model.IpLogin> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
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
    public static java.util.List<za.co.idea.ip.hook.model.IpLogin> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ip logins.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link za.co.idea.ip.hook.model.impl.IpLoginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ip logins
    * @param end the upper bound of the range of ip logins (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ip logins
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<za.co.idea.ip.hook.model.IpLogin> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ip logins from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ip logins.
    *
    * @return the number of ip logins
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static IpLoginPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (IpLoginPersistence) PortletBeanLocatorUtil.locate(za.co.idea.ip.hook.service.ClpSerializer.getServletContextName(),
                    IpLoginPersistence.class.getName());

            ReferenceRegistry.registerReference(IpLoginUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(IpLoginPersistence persistence) {
    }
}
