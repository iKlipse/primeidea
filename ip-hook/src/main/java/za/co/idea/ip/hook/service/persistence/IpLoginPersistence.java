package za.co.idea.ip.hook.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import za.co.idea.ip.hook.model.IpLogin;

/**
 * The persistence interface for the ip login service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author VMPattamatta
 * @see IpLoginPersistenceImpl
 * @see IpLoginUtil
 * @generated
 */
public interface IpLoginPersistence extends BasePersistence<IpLogin> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IpLoginUtil} to access the ip login persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the ip logins where loginId = &#63;.
    *
    * @param loginId the login ID
    * @return the matching ip logins
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<za.co.idea.ip.hook.model.IpLogin> findBygetIpLogin(
        long loginId)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<za.co.idea.ip.hook.model.IpLogin> findBygetIpLogin(
        long loginId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<za.co.idea.ip.hook.model.IpLogin> findBygetIpLogin(
        long loginId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first ip login in the ordered set where loginId = &#63;.
    *
    * @param loginId the login ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip login
    * @throws za.co.idea.ip.hook.NoSuchIpLoginException if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpLogin findBygetIpLogin_First(
        long loginId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException;

    /**
    * Returns the first ip login in the ordered set where loginId = &#63;.
    *
    * @param loginId the login ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip login, or <code>null</code> if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpLogin fetchBygetIpLogin_First(
        long loginId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last ip login in the ordered set where loginId = &#63;.
    *
    * @param loginId the login ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip login
    * @throws za.co.idea.ip.hook.NoSuchIpLoginException if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpLogin findBygetIpLogin_Last(
        long loginId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException;

    /**
    * Returns the last ip login in the ordered set where loginId = &#63;.
    *
    * @param loginId the login ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip login, or <code>null</code> if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpLogin fetchBygetIpLogin_Last(
        long loginId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the ip logins where loginId = &#63; from the database.
    *
    * @param loginId the login ID
    * @throws SystemException if a system exception occurred
    */
    public void removeBygetIpLogin(long loginId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ip logins where loginId = &#63;.
    *
    * @param loginId the login ID
    * @return the number of matching ip logins
    * @throws SystemException if a system exception occurred
    */
    public int countBygetIpLogin(long loginId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the ip logins where loginName = &#63; and loginPwd = &#63;.
    *
    * @param loginName the login name
    * @param loginPwd the login pwd
    * @return the matching ip logins
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<za.co.idea.ip.hook.model.IpLogin> findBygetIpLoginScreenName_Password(
        java.lang.String loginName, java.lang.String loginPwd)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<za.co.idea.ip.hook.model.IpLogin> findBygetIpLoginScreenName_Password(
        java.lang.String loginName, java.lang.String loginPwd, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<za.co.idea.ip.hook.model.IpLogin> findBygetIpLoginScreenName_Password(
        java.lang.String loginName, java.lang.String loginPwd, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public za.co.idea.ip.hook.model.IpLogin findBygetIpLoginScreenName_Password_First(
        java.lang.String loginName, java.lang.String loginPwd,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException;

    /**
    * Returns the first ip login in the ordered set where loginName = &#63; and loginPwd = &#63;.
    *
    * @param loginName the login name
    * @param loginPwd the login pwd
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip login, or <code>null</code> if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpLogin fetchBygetIpLoginScreenName_Password_First(
        java.lang.String loginName, java.lang.String loginPwd,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public za.co.idea.ip.hook.model.IpLogin findBygetIpLoginScreenName_Password_Last(
        java.lang.String loginName, java.lang.String loginPwd,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException;

    /**
    * Returns the last ip login in the ordered set where loginName = &#63; and loginPwd = &#63;.
    *
    * @param loginName the login name
    * @param loginPwd the login pwd
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip login, or <code>null</code> if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpLogin fetchBygetIpLoginScreenName_Password_Last(
        java.lang.String loginName, java.lang.String loginPwd,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public za.co.idea.ip.hook.model.IpLogin[] findBygetIpLoginScreenName_Password_PrevAndNext(
        long loginId, java.lang.String loginName, java.lang.String loginPwd,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException;

    /**
    * Removes all the ip logins where loginName = &#63; and loginPwd = &#63; from the database.
    *
    * @param loginName the login name
    * @param loginPwd the login pwd
    * @throws SystemException if a system exception occurred
    */
    public void removeBygetIpLoginScreenName_Password(
        java.lang.String loginName, java.lang.String loginPwd)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ip logins where loginName = &#63; and loginPwd = &#63;.
    *
    * @param loginName the login name
    * @param loginPwd the login pwd
    * @return the number of matching ip logins
    * @throws SystemException if a system exception occurred
    */
    public int countBygetIpLoginScreenName_Password(
        java.lang.String loginName, java.lang.String loginPwd)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the ip logins where loginName = &#63;.
    *
    * @param loginName the login name
    * @return the matching ip logins
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<za.co.idea.ip.hook.model.IpLogin> findBygetIpLoginScreenName(
        java.lang.String loginName)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<za.co.idea.ip.hook.model.IpLogin> findBygetIpLoginScreenName(
        java.lang.String loginName, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<za.co.idea.ip.hook.model.IpLogin> findBygetIpLoginScreenName(
        java.lang.String loginName, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first ip login in the ordered set where loginName = &#63;.
    *
    * @param loginName the login name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip login
    * @throws za.co.idea.ip.hook.NoSuchIpLoginException if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpLogin findBygetIpLoginScreenName_First(
        java.lang.String loginName,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException;

    /**
    * Returns the first ip login in the ordered set where loginName = &#63;.
    *
    * @param loginName the login name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip login, or <code>null</code> if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpLogin fetchBygetIpLoginScreenName_First(
        java.lang.String loginName,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last ip login in the ordered set where loginName = &#63;.
    *
    * @param loginName the login name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip login
    * @throws za.co.idea.ip.hook.NoSuchIpLoginException if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpLogin findBygetIpLoginScreenName_Last(
        java.lang.String loginName,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException;

    /**
    * Returns the last ip login in the ordered set where loginName = &#63;.
    *
    * @param loginName the login name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip login, or <code>null</code> if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpLogin fetchBygetIpLoginScreenName_Last(
        java.lang.String loginName,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public za.co.idea.ip.hook.model.IpLogin[] findBygetIpLoginScreenName_PrevAndNext(
        long loginId, java.lang.String loginName,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException;

    /**
    * Removes all the ip logins where loginName = &#63; from the database.
    *
    * @param loginName the login name
    * @throws SystemException if a system exception occurred
    */
    public void removeBygetIpLoginScreenName(java.lang.String loginName)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ip logins where loginName = &#63;.
    *
    * @param loginName the login name
    * @return the number of matching ip logins
    * @throws SystemException if a system exception occurred
    */
    public int countBygetIpLoginScreenName(java.lang.String loginName)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the ip logins where loginName = &#63; and loginSecA = &#63;.
    *
    * @param loginName the login name
    * @param loginSecA the login sec a
    * @return the matching ip logins
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<za.co.idea.ip.hook.model.IpLogin> findBygetIpLoginScreenName_LoginSecA(
        java.lang.String loginName, java.lang.String loginSecA)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<za.co.idea.ip.hook.model.IpLogin> findBygetIpLoginScreenName_LoginSecA(
        java.lang.String loginName, java.lang.String loginSecA, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<za.co.idea.ip.hook.model.IpLogin> findBygetIpLoginScreenName_LoginSecA(
        java.lang.String loginName, java.lang.String loginSecA, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public za.co.idea.ip.hook.model.IpLogin findBygetIpLoginScreenName_LoginSecA_First(
        java.lang.String loginName, java.lang.String loginSecA,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException;

    /**
    * Returns the first ip login in the ordered set where loginName = &#63; and loginSecA = &#63;.
    *
    * @param loginName the login name
    * @param loginSecA the login sec a
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching ip login, or <code>null</code> if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpLogin fetchBygetIpLoginScreenName_LoginSecA_First(
        java.lang.String loginName, java.lang.String loginSecA,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public za.co.idea.ip.hook.model.IpLogin findBygetIpLoginScreenName_LoginSecA_Last(
        java.lang.String loginName, java.lang.String loginSecA,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException;

    /**
    * Returns the last ip login in the ordered set where loginName = &#63; and loginSecA = &#63;.
    *
    * @param loginName the login name
    * @param loginSecA the login sec a
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching ip login, or <code>null</code> if a matching ip login could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpLogin fetchBygetIpLoginScreenName_LoginSecA_Last(
        java.lang.String loginName, java.lang.String loginSecA,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public za.co.idea.ip.hook.model.IpLogin[] findBygetIpLoginScreenName_LoginSecA_PrevAndNext(
        long loginId, java.lang.String loginName, java.lang.String loginSecA,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException;

    /**
    * Removes all the ip logins where loginName = &#63; and loginSecA = &#63; from the database.
    *
    * @param loginName the login name
    * @param loginSecA the login sec a
    * @throws SystemException if a system exception occurred
    */
    public void removeBygetIpLoginScreenName_LoginSecA(
        java.lang.String loginName, java.lang.String loginSecA)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ip logins where loginName = &#63; and loginSecA = &#63;.
    *
    * @param loginName the login name
    * @param loginSecA the login sec a
    * @return the number of matching ip logins
    * @throws SystemException if a system exception occurred
    */
    public int countBygetIpLoginScreenName_LoginSecA(
        java.lang.String loginName, java.lang.String loginSecA)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the ip login in the entity cache if it is enabled.
    *
    * @param ipLogin the ip login
    */
    public void cacheResult(za.co.idea.ip.hook.model.IpLogin ipLogin);

    /**
    * Caches the ip logins in the entity cache if it is enabled.
    *
    * @param ipLogins the ip logins
    */
    public void cacheResult(
        java.util.List<za.co.idea.ip.hook.model.IpLogin> ipLogins);

    /**
    * Creates a new ip login with the primary key. Does not add the ip login to the database.
    *
    * @param loginId the primary key for the new ip login
    * @return the new ip login
    */
    public za.co.idea.ip.hook.model.IpLogin create(long loginId);

    /**
    * Removes the ip login with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param loginId the primary key of the ip login
    * @return the ip login that was removed
    * @throws za.co.idea.ip.hook.NoSuchIpLoginException if a ip login with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpLogin remove(long loginId)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException;

    public za.co.idea.ip.hook.model.IpLogin updateImpl(
        za.co.idea.ip.hook.model.IpLogin ipLogin)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the ip login with the primary key or throws a {@link za.co.idea.ip.hook.NoSuchIpLoginException} if it could not be found.
    *
    * @param loginId the primary key of the ip login
    * @return the ip login
    * @throws za.co.idea.ip.hook.NoSuchIpLoginException if a ip login with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpLogin findByPrimaryKey(long loginId)
        throws com.liferay.portal.kernel.exception.SystemException,
            za.co.idea.ip.hook.NoSuchIpLoginException;

    /**
    * Returns the ip login with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param loginId the primary key of the ip login
    * @return the ip login, or <code>null</code> if a ip login with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public za.co.idea.ip.hook.model.IpLogin fetchByPrimaryKey(long loginId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the ip logins.
    *
    * @return the ip logins
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<za.co.idea.ip.hook.model.IpLogin> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<za.co.idea.ip.hook.model.IpLogin> findAll(int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<za.co.idea.ip.hook.model.IpLogin> findAll(int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the ip logins from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ip logins.
    *
    * @return the number of ip logins
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
