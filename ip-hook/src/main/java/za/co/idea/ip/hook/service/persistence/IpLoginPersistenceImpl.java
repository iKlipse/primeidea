package za.co.idea.ip.hook.service.persistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import za.co.idea.ip.hook.NoSuchIpLoginException;
import za.co.idea.ip.hook.model.IpLogin;
import za.co.idea.ip.hook.model.impl.IpLoginImpl;
import za.co.idea.ip.hook.model.impl.IpLoginModelImpl;
import za.co.idea.ip.hook.service.persistence.IpLoginPersistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the ip login service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author VMPattamatta
 * @see IpLoginPersistence
 * @see IpLoginUtil
 * @generated
 */
@SuppressWarnings("unchecked")
public class IpLoginPersistenceImpl extends BasePersistenceImpl<IpLogin>
    implements IpLoginPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IpLoginUtil} to access the ip login persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IpLoginImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IpLoginModelImpl.ENTITY_CACHE_ENABLED,
            IpLoginModelImpl.FINDER_CACHE_ENABLED, IpLoginImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IpLoginModelImpl.ENTITY_CACHE_ENABLED,
            IpLoginModelImpl.FINDER_CACHE_ENABLED, IpLoginImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IpLoginModelImpl.ENTITY_CACHE_ENABLED,
            IpLoginModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GETIPLOGIN =
        new FinderPath(IpLoginModelImpl.ENTITY_CACHE_ENABLED,
            IpLoginModelImpl.FINDER_CACHE_ENABLED, IpLoginImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBygetIpLogin",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPLOGIN =
        new FinderPath(IpLoginModelImpl.ENTITY_CACHE_ENABLED,
            IpLoginModelImpl.FINDER_CACHE_ENABLED, IpLoginImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBygetIpLogin",
            new String[] { Long.class.getName() },
            IpLoginModelImpl.LOGINID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_GETIPLOGIN = new FinderPath(IpLoginModelImpl.ENTITY_CACHE_ENABLED,
            IpLoginModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBygetIpLogin",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_GETIPLOGIN_LOGINID_2 = "ipLogin.loginId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GETIPLOGINSCREENNAME_PASSWORD =
        new FinderPath(IpLoginModelImpl.ENTITY_CACHE_ENABLED,
            IpLoginModelImpl.FINDER_CACHE_ENABLED, IpLoginImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findBygetIpLoginScreenName_Password",
            new String[] {
                String.class.getName(), String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPLOGINSCREENNAME_PASSWORD =
        new FinderPath(IpLoginModelImpl.ENTITY_CACHE_ENABLED,
            IpLoginModelImpl.FINDER_CACHE_ENABLED, IpLoginImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findBygetIpLoginScreenName_Password",
            new String[] { String.class.getName(), String.class.getName() },
            IpLoginModelImpl.LOGINNAME_COLUMN_BITMASK |
            IpLoginModelImpl.LOGINPWD_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_GETIPLOGINSCREENNAME_PASSWORD =
        new FinderPath(IpLoginModelImpl.ENTITY_CACHE_ENABLED,
            IpLoginModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countBygetIpLoginScreenName_Password",
            new String[] { String.class.getName(), String.class.getName() });
    private static final String _FINDER_COLUMN_GETIPLOGINSCREENNAME_PASSWORD_LOGINNAME_1 =
        "ipLogin.loginName IS NULL AND ";
    private static final String _FINDER_COLUMN_GETIPLOGINSCREENNAME_PASSWORD_LOGINNAME_2 =
        "ipLogin.loginName = ? AND ";
    private static final String _FINDER_COLUMN_GETIPLOGINSCREENNAME_PASSWORD_LOGINNAME_3 =
        "(ipLogin.loginName IS NULL OR ipLogin.loginName = '') AND ";
    private static final String _FINDER_COLUMN_GETIPLOGINSCREENNAME_PASSWORD_LOGINPWD_1 =
        "ipLogin.loginPwd IS NULL";
    private static final String _FINDER_COLUMN_GETIPLOGINSCREENNAME_PASSWORD_LOGINPWD_2 =
        "ipLogin.loginPwd = ?";
    private static final String _FINDER_COLUMN_GETIPLOGINSCREENNAME_PASSWORD_LOGINPWD_3 =
        "(ipLogin.loginPwd IS NULL OR ipLogin.loginPwd = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GETIPLOGINSCREENNAME =
        new FinderPath(IpLoginModelImpl.ENTITY_CACHE_ENABLED,
            IpLoginModelImpl.FINDER_CACHE_ENABLED, IpLoginImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findBygetIpLoginScreenName",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPLOGINSCREENNAME =
        new FinderPath(IpLoginModelImpl.ENTITY_CACHE_ENABLED,
            IpLoginModelImpl.FINDER_CACHE_ENABLED, IpLoginImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findBygetIpLoginScreenName",
            new String[] { String.class.getName() },
            IpLoginModelImpl.LOGINNAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_GETIPLOGINSCREENNAME = new FinderPath(IpLoginModelImpl.ENTITY_CACHE_ENABLED,
            IpLoginModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countBygetIpLoginScreenName",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINNAME_1 = "ipLogin.loginName IS NULL";
    private static final String _FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINNAME_2 = "ipLogin.loginName = ?";
    private static final String _FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINNAME_3 = "(ipLogin.loginName IS NULL OR ipLogin.loginName = '')";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GETIPLOGINSCREENNAME_LOGINSECA =
        new FinderPath(IpLoginModelImpl.ENTITY_CACHE_ENABLED,
            IpLoginModelImpl.FINDER_CACHE_ENABLED, IpLoginImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findBygetIpLoginScreenName_LoginSecA",
            new String[] {
                String.class.getName(), String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPLOGINSCREENNAME_LOGINSECA =
        new FinderPath(IpLoginModelImpl.ENTITY_CACHE_ENABLED,
            IpLoginModelImpl.FINDER_CACHE_ENABLED, IpLoginImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findBygetIpLoginScreenName_LoginSecA",
            new String[] { String.class.getName(), String.class.getName() },
            IpLoginModelImpl.LOGINNAME_COLUMN_BITMASK |
            IpLoginModelImpl.LOGINSECA_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_GETIPLOGINSCREENNAME_LOGINSECA =
        new FinderPath(IpLoginModelImpl.ENTITY_CACHE_ENABLED,
            IpLoginModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countBygetIpLoginScreenName_LoginSecA",
            new String[] { String.class.getName(), String.class.getName() });
    private static final String _FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINSECA_LOGINNAME_1 =
        "ipLogin.loginName IS NULL AND ";
    private static final String _FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINSECA_LOGINNAME_2 =
        "ipLogin.loginName = ? AND ";
    private static final String _FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINSECA_LOGINNAME_3 =
        "(ipLogin.loginName IS NULL OR ipLogin.loginName = '') AND ";
    private static final String _FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINSECA_LOGINSECA_1 =
        "ipLogin.loginSecA IS NULL";
    private static final String _FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINSECA_LOGINSECA_2 =
        "ipLogin.loginSecA = ?";
    private static final String _FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINSECA_LOGINSECA_3 =
        "(ipLogin.loginSecA IS NULL OR ipLogin.loginSecA = '')";
    private static final String _SQL_SELECT_IPLOGIN = "SELECT ipLogin FROM IpLogin ipLogin";
    private static final String _SQL_SELECT_IPLOGIN_WHERE = "SELECT ipLogin FROM IpLogin ipLogin WHERE ";
    private static final String _SQL_COUNT_IPLOGIN = "SELECT COUNT(ipLogin) FROM IpLogin ipLogin";
    private static final String _SQL_COUNT_IPLOGIN_WHERE = "SELECT COUNT(ipLogin) FROM IpLogin ipLogin WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ipLogin.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IpLogin exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No IpLogin exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IpLoginPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "loginId", "loginUserId", "loginName", "loginPwd", "loginLastDt",
                "loginSecQ", "loginSecA"
            });
    private static IpLogin _nullIpLogin = new IpLoginImpl() {
            /**
		 * 
		 */
		private static final long serialVersionUID = 1209598427371708195L;

			@Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IpLogin> toCacheModel() {
                return _nullIpLoginCacheModel;
            }
        };

    private static CacheModel<IpLogin> _nullIpLoginCacheModel = new CacheModel<IpLogin>() {
            /**
		 * 
		 */
		private static final long serialVersionUID = 3190122309796775470L;

			@Override
            public IpLogin toEntityModel() {
                return _nullIpLogin;
            }
        };

    public IpLoginPersistenceImpl() {
        setModelClass(IpLogin.class);
    }

    /**
     * Returns all the ip logins where loginId = &#63;.
     *
     * @param loginId the login ID
     * @return the matching ip logins
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IpLogin> findBygetIpLogin(long loginId)
        throws SystemException {
        return findBygetIpLogin(loginId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
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
    @Override
    public List<IpLogin> findBygetIpLogin(long loginId, int start, int end)
        throws SystemException {
        return findBygetIpLogin(loginId, start, end, null);
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
    @Override
    public List<IpLogin> findBygetIpLogin(long loginId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPLOGIN;
            finderArgs = new Object[] { loginId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GETIPLOGIN;
            finderArgs = new Object[] { loginId, start, end, orderByComparator };
        }

        List<IpLogin> list = (List<IpLogin>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (IpLogin ipLogin : list) {
                if ((loginId != ipLogin.getLoginId())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_IPLOGIN_WHERE);

            query.append(_FINDER_COLUMN_GETIPLOGIN_LOGINID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(IpLoginModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(loginId);

                if (!pagination) {
                    list = (List<IpLogin>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IpLogin>(list);
                } else {
                    list = (List<IpLogin>) QueryUtil.list(q, getDialect(),
                            start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
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
    @Override
    public IpLogin findBygetIpLogin_First(long loginId,
        OrderByComparator orderByComparator)
        throws NoSuchIpLoginException, SystemException {
        IpLogin ipLogin = fetchBygetIpLogin_First(loginId, orderByComparator);

        if (ipLogin != null) {
            return ipLogin;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("loginId=");
        msg.append(loginId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIpLoginException(msg.toString());
    }

    /**
     * Returns the first ip login in the ordered set where loginId = &#63;.
     *
     * @param loginId the login ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ip login, or <code>null</code> if a matching ip login could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpLogin fetchBygetIpLogin_First(long loginId,
        OrderByComparator orderByComparator) throws SystemException {
        List<IpLogin> list = findBygetIpLogin(loginId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public IpLogin findBygetIpLogin_Last(long loginId,
        OrderByComparator orderByComparator)
        throws NoSuchIpLoginException, SystemException {
        IpLogin ipLogin = fetchBygetIpLogin_Last(loginId, orderByComparator);

        if (ipLogin != null) {
            return ipLogin;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("loginId=");
        msg.append(loginId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIpLoginException(msg.toString());
    }

    /**
     * Returns the last ip login in the ordered set where loginId = &#63;.
     *
     * @param loginId the login ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ip login, or <code>null</code> if a matching ip login could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpLogin fetchBygetIpLogin_Last(long loginId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countBygetIpLogin(loginId);

        if (count == 0) {
            return null;
        }

        List<IpLogin> list = findBygetIpLogin(loginId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Removes all the ip logins where loginId = &#63; from the database.
     *
     * @param loginId the login ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeBygetIpLogin(long loginId) throws SystemException {
        for (IpLogin ipLogin : findBygetIpLogin(loginId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(ipLogin);
        }
    }

    /**
     * Returns the number of ip logins where loginId = &#63;.
     *
     * @param loginId the login ID
     * @return the number of matching ip logins
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countBygetIpLogin(long loginId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_GETIPLOGIN;

        Object[] finderArgs = new Object[] { loginId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_IPLOGIN_WHERE);

            query.append(_FINDER_COLUMN_GETIPLOGIN_LOGINID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(loginId);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns all the ip logins where loginName = &#63; and loginPwd = &#63;.
     *
     * @param loginName the login name
     * @param loginPwd the login pwd
     * @return the matching ip logins
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IpLogin> findBygetIpLoginScreenName_Password(String loginName,
        String loginPwd) throws SystemException {
        return findBygetIpLoginScreenName_Password(loginName, loginPwd,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
    @Override
    public List<IpLogin> findBygetIpLoginScreenName_Password(String loginName,
        String loginPwd, int start, int end) throws SystemException {
        return findBygetIpLoginScreenName_Password(loginName, loginPwd, start,
            end, null);
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
    @Override
    public List<IpLogin> findBygetIpLoginScreenName_Password(String loginName,
        String loginPwd, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPLOGINSCREENNAME_PASSWORD;
            finderArgs = new Object[] { loginName, loginPwd };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GETIPLOGINSCREENNAME_PASSWORD;
            finderArgs = new Object[] {
                    loginName, loginPwd,
                    
                    start, end, orderByComparator
                };
        }

        List<IpLogin> list = (List<IpLogin>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (IpLogin ipLogin : list) {
                if (!Validator.equals(loginName, ipLogin.getLoginName()) ||
                        !Validator.equals(loginPwd, ipLogin.getLoginPwd())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(4);
            }

            query.append(_SQL_SELECT_IPLOGIN_WHERE);

            boolean bindLoginName = false;

            if (loginName == null) {
                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_PASSWORD_LOGINNAME_1);
            } else if (loginName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_PASSWORD_LOGINNAME_3);
            } else {
                bindLoginName = true;

                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_PASSWORD_LOGINNAME_2);
            }

            boolean bindLoginPwd = false;

            if (loginPwd == null) {
                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_PASSWORD_LOGINPWD_1);
            } else if (loginPwd.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_PASSWORD_LOGINPWD_3);
            } else {
                bindLoginPwd = true;

                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_PASSWORD_LOGINPWD_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(IpLoginModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindLoginName) {
                    qPos.add(loginName);
                }

                if (bindLoginPwd) {
                    qPos.add(loginPwd);
                }

                if (!pagination) {
                    list = (List<IpLogin>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IpLogin>(list);
                } else {
                    list = (List<IpLogin>) QueryUtil.list(q, getDialect(),
                            start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
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
    @Override
    public IpLogin findBygetIpLoginScreenName_Password_First(String loginName,
        String loginPwd, OrderByComparator orderByComparator)
        throws NoSuchIpLoginException, SystemException {
        IpLogin ipLogin = fetchBygetIpLoginScreenName_Password_First(loginName,
                loginPwd, orderByComparator);

        if (ipLogin != null) {
            return ipLogin;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("loginName=");
        msg.append(loginName);

        msg.append(", loginPwd=");
        msg.append(loginPwd);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIpLoginException(msg.toString());
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
    @Override
    public IpLogin fetchBygetIpLoginScreenName_Password_First(
        String loginName, String loginPwd, OrderByComparator orderByComparator)
        throws SystemException {
        List<IpLogin> list = findBygetIpLoginScreenName_Password(loginName,
                loginPwd, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public IpLogin findBygetIpLoginScreenName_Password_Last(String loginName,
        String loginPwd, OrderByComparator orderByComparator)
        throws NoSuchIpLoginException, SystemException {
        IpLogin ipLogin = fetchBygetIpLoginScreenName_Password_Last(loginName,
                loginPwd, orderByComparator);

        if (ipLogin != null) {
            return ipLogin;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("loginName=");
        msg.append(loginName);

        msg.append(", loginPwd=");
        msg.append(loginPwd);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIpLoginException(msg.toString());
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
    @Override
    public IpLogin fetchBygetIpLoginScreenName_Password_Last(String loginName,
        String loginPwd, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countBygetIpLoginScreenName_Password(loginName, loginPwd);

        if (count == 0) {
            return null;
        }

        List<IpLogin> list = findBygetIpLoginScreenName_Password(loginName,
                loginPwd, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public IpLogin[] findBygetIpLoginScreenName_Password_PrevAndNext(
        long loginId, String loginName, String loginPwd,
        OrderByComparator orderByComparator)
        throws NoSuchIpLoginException, SystemException {
        IpLogin ipLogin = findByPrimaryKey(loginId);

        Session session = null;

        try {
            session = openSession();

            IpLogin[] array = new IpLoginImpl[3];

            array[0] = getBygetIpLoginScreenName_Password_PrevAndNext(session,
                    ipLogin, loginName, loginPwd, orderByComparator, true);

            array[1] = ipLogin;

            array[2] = getBygetIpLoginScreenName_Password_PrevAndNext(session,
                    ipLogin, loginName, loginPwd, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected IpLogin getBygetIpLoginScreenName_Password_PrevAndNext(
        Session session, IpLogin ipLogin, String loginName, String loginPwd,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_IPLOGIN_WHERE);

        boolean bindLoginName = false;

        if (loginName == null) {
            query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_PASSWORD_LOGINNAME_1);
        } else if (loginName.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_PASSWORD_LOGINNAME_3);
        } else {
            bindLoginName = true;

            query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_PASSWORD_LOGINNAME_2);
        }

        boolean bindLoginPwd = false;

        if (loginPwd == null) {
            query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_PASSWORD_LOGINPWD_1);
        } else if (loginPwd.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_PASSWORD_LOGINPWD_3);
        } else {
            bindLoginPwd = true;

            query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_PASSWORD_LOGINPWD_2);
        }

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(IpLoginModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindLoginName) {
            qPos.add(loginName);
        }

        if (bindLoginPwd) {
            qPos.add(loginPwd);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(ipLogin);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<IpLogin> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the ip logins where loginName = &#63; and loginPwd = &#63; from the database.
     *
     * @param loginName the login name
     * @param loginPwd the login pwd
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeBygetIpLoginScreenName_Password(String loginName,
        String loginPwd) throws SystemException {
        for (IpLogin ipLogin : findBygetIpLoginScreenName_Password(loginName,
                loginPwd, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(ipLogin);
        }
    }

    /**
     * Returns the number of ip logins where loginName = &#63; and loginPwd = &#63;.
     *
     * @param loginName the login name
     * @param loginPwd the login pwd
     * @return the number of matching ip logins
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countBygetIpLoginScreenName_Password(String loginName,
        String loginPwd) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_GETIPLOGINSCREENNAME_PASSWORD;

        Object[] finderArgs = new Object[] { loginName, loginPwd };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_IPLOGIN_WHERE);

            boolean bindLoginName = false;

            if (loginName == null) {
                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_PASSWORD_LOGINNAME_1);
            } else if (loginName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_PASSWORD_LOGINNAME_3);
            } else {
                bindLoginName = true;

                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_PASSWORD_LOGINNAME_2);
            }

            boolean bindLoginPwd = false;

            if (loginPwd == null) {
                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_PASSWORD_LOGINPWD_1);
            } else if (loginPwd.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_PASSWORD_LOGINPWD_3);
            } else {
                bindLoginPwd = true;

                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_PASSWORD_LOGINPWD_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindLoginName) {
                    qPos.add(loginName);
                }

                if (bindLoginPwd) {
                    qPos.add(loginPwd);
                }

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns all the ip logins where loginName = &#63;.
     *
     * @param loginName the login name
     * @return the matching ip logins
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IpLogin> findBygetIpLoginScreenName(String loginName)
        throws SystemException {
        return findBygetIpLoginScreenName(loginName, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
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
    @Override
    public List<IpLogin> findBygetIpLoginScreenName(String loginName,
        int start, int end) throws SystemException {
        return findBygetIpLoginScreenName(loginName, start, end, null);
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
    @Override
    public List<IpLogin> findBygetIpLoginScreenName(String loginName,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPLOGINSCREENNAME;
            finderArgs = new Object[] { loginName };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GETIPLOGINSCREENNAME;
            finderArgs = new Object[] { loginName, start, end, orderByComparator };
        }

        List<IpLogin> list = (List<IpLogin>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (IpLogin ipLogin : list) {
                if (!Validator.equals(loginName, ipLogin.getLoginName())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_IPLOGIN_WHERE);

            boolean bindLoginName = false;

            if (loginName == null) {
                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINNAME_1);
            } else if (loginName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINNAME_3);
            } else {
                bindLoginName = true;

                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINNAME_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(IpLoginModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindLoginName) {
                    qPos.add(loginName);
                }

                if (!pagination) {
                    list = (List<IpLogin>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IpLogin>(list);
                } else {
                    list = (List<IpLogin>) QueryUtil.list(q, getDialect(),
                            start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
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
    @Override
    public IpLogin findBygetIpLoginScreenName_First(String loginName,
        OrderByComparator orderByComparator)
        throws NoSuchIpLoginException, SystemException {
        IpLogin ipLogin = fetchBygetIpLoginScreenName_First(loginName,
                orderByComparator);

        if (ipLogin != null) {
            return ipLogin;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("loginName=");
        msg.append(loginName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIpLoginException(msg.toString());
    }

    /**
     * Returns the first ip login in the ordered set where loginName = &#63;.
     *
     * @param loginName the login name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ip login, or <code>null</code> if a matching ip login could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpLogin fetchBygetIpLoginScreenName_First(String loginName,
        OrderByComparator orderByComparator) throws SystemException {
        List<IpLogin> list = findBygetIpLoginScreenName(loginName, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public IpLogin findBygetIpLoginScreenName_Last(String loginName,
        OrderByComparator orderByComparator)
        throws NoSuchIpLoginException, SystemException {
        IpLogin ipLogin = fetchBygetIpLoginScreenName_Last(loginName,
                orderByComparator);

        if (ipLogin != null) {
            return ipLogin;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("loginName=");
        msg.append(loginName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIpLoginException(msg.toString());
    }

    /**
     * Returns the last ip login in the ordered set where loginName = &#63;.
     *
     * @param loginName the login name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ip login, or <code>null</code> if a matching ip login could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpLogin fetchBygetIpLoginScreenName_Last(String loginName,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countBygetIpLoginScreenName(loginName);

        if (count == 0) {
            return null;
        }

        List<IpLogin> list = findBygetIpLoginScreenName(loginName, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public IpLogin[] findBygetIpLoginScreenName_PrevAndNext(long loginId,
        String loginName, OrderByComparator orderByComparator)
        throws NoSuchIpLoginException, SystemException {
        IpLogin ipLogin = findByPrimaryKey(loginId);

        Session session = null;

        try {
            session = openSession();

            IpLogin[] array = new IpLoginImpl[3];

            array[0] = getBygetIpLoginScreenName_PrevAndNext(session, ipLogin,
                    loginName, orderByComparator, true);

            array[1] = ipLogin;

            array[2] = getBygetIpLoginScreenName_PrevAndNext(session, ipLogin,
                    loginName, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected IpLogin getBygetIpLoginScreenName_PrevAndNext(Session session,
        IpLogin ipLogin, String loginName, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_IPLOGIN_WHERE);

        boolean bindLoginName = false;

        if (loginName == null) {
            query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINNAME_1);
        } else if (loginName.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINNAME_3);
        } else {
            bindLoginName = true;

            query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINNAME_2);
        }

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(IpLoginModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindLoginName) {
            qPos.add(loginName);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(ipLogin);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<IpLogin> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the ip logins where loginName = &#63; from the database.
     *
     * @param loginName the login name
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeBygetIpLoginScreenName(String loginName)
        throws SystemException {
        for (IpLogin ipLogin : findBygetIpLoginScreenName(loginName,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(ipLogin);
        }
    }

    /**
     * Returns the number of ip logins where loginName = &#63;.
     *
     * @param loginName the login name
     * @return the number of matching ip logins
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countBygetIpLoginScreenName(String loginName)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_GETIPLOGINSCREENNAME;

        Object[] finderArgs = new Object[] { loginName };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_IPLOGIN_WHERE);

            boolean bindLoginName = false;

            if (loginName == null) {
                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINNAME_1);
            } else if (loginName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINNAME_3);
            } else {
                bindLoginName = true;

                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINNAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindLoginName) {
                    qPos.add(loginName);
                }

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns all the ip logins where loginName = &#63; and loginSecA = &#63;.
     *
     * @param loginName the login name
     * @param loginSecA the login sec a
     * @return the matching ip logins
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IpLogin> findBygetIpLoginScreenName_LoginSecA(
        String loginName, String loginSecA) throws SystemException {
        return findBygetIpLoginScreenName_LoginSecA(loginName, loginSecA,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
    @Override
    public List<IpLogin> findBygetIpLoginScreenName_LoginSecA(
        String loginName, String loginSecA, int start, int end)
        throws SystemException {
        return findBygetIpLoginScreenName_LoginSecA(loginName, loginSecA,
            start, end, null);
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
    @Override
    public List<IpLogin> findBygetIpLoginScreenName_LoginSecA(
        String loginName, String loginSecA, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPLOGINSCREENNAME_LOGINSECA;
            finderArgs = new Object[] { loginName, loginSecA };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GETIPLOGINSCREENNAME_LOGINSECA;
            finderArgs = new Object[] {
                    loginName, loginSecA,
                    
                    start, end, orderByComparator
                };
        }

        List<IpLogin> list = (List<IpLogin>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (IpLogin ipLogin : list) {
                if (!Validator.equals(loginName, ipLogin.getLoginName()) ||
                        !Validator.equals(loginSecA, ipLogin.getLoginSecA())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(4);
            }

            query.append(_SQL_SELECT_IPLOGIN_WHERE);

            boolean bindLoginName = false;

            if (loginName == null) {
                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINSECA_LOGINNAME_1);
            } else if (loginName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINSECA_LOGINNAME_3);
            } else {
                bindLoginName = true;

                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINSECA_LOGINNAME_2);
            }

            boolean bindLoginSecA = false;

            if (loginSecA == null) {
                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINSECA_LOGINSECA_1);
            } else if (loginSecA.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINSECA_LOGINSECA_3);
            } else {
                bindLoginSecA = true;

                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINSECA_LOGINSECA_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(IpLoginModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindLoginName) {
                    qPos.add(loginName);
                }

                if (bindLoginSecA) {
                    qPos.add(loginSecA);
                }

                if (!pagination) {
                    list = (List<IpLogin>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IpLogin>(list);
                } else {
                    list = (List<IpLogin>) QueryUtil.list(q, getDialect(),
                            start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
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
    @Override
    public IpLogin findBygetIpLoginScreenName_LoginSecA_First(
        String loginName, String loginSecA, OrderByComparator orderByComparator)
        throws NoSuchIpLoginException, SystemException {
        IpLogin ipLogin = fetchBygetIpLoginScreenName_LoginSecA_First(loginName,
                loginSecA, orderByComparator);

        if (ipLogin != null) {
            return ipLogin;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("loginName=");
        msg.append(loginName);

        msg.append(", loginSecA=");
        msg.append(loginSecA);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIpLoginException(msg.toString());
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
    @Override
    public IpLogin fetchBygetIpLoginScreenName_LoginSecA_First(
        String loginName, String loginSecA, OrderByComparator orderByComparator)
        throws SystemException {
        List<IpLogin> list = findBygetIpLoginScreenName_LoginSecA(loginName,
                loginSecA, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public IpLogin findBygetIpLoginScreenName_LoginSecA_Last(String loginName,
        String loginSecA, OrderByComparator orderByComparator)
        throws NoSuchIpLoginException, SystemException {
        IpLogin ipLogin = fetchBygetIpLoginScreenName_LoginSecA_Last(loginName,
                loginSecA, orderByComparator);

        if (ipLogin != null) {
            return ipLogin;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("loginName=");
        msg.append(loginName);

        msg.append(", loginSecA=");
        msg.append(loginSecA);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIpLoginException(msg.toString());
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
    @Override
    public IpLogin fetchBygetIpLoginScreenName_LoginSecA_Last(
        String loginName, String loginSecA, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countBygetIpLoginScreenName_LoginSecA(loginName, loginSecA);

        if (count == 0) {
            return null;
        }

        List<IpLogin> list = findBygetIpLoginScreenName_LoginSecA(loginName,
                loginSecA, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public IpLogin[] findBygetIpLoginScreenName_LoginSecA_PrevAndNext(
        long loginId, String loginName, String loginSecA,
        OrderByComparator orderByComparator)
        throws NoSuchIpLoginException, SystemException {
        IpLogin ipLogin = findByPrimaryKey(loginId);

        Session session = null;

        try {
            session = openSession();

            IpLogin[] array = new IpLoginImpl[3];

            array[0] = getBygetIpLoginScreenName_LoginSecA_PrevAndNext(session,
                    ipLogin, loginName, loginSecA, orderByComparator, true);

            array[1] = ipLogin;

            array[2] = getBygetIpLoginScreenName_LoginSecA_PrevAndNext(session,
                    ipLogin, loginName, loginSecA, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected IpLogin getBygetIpLoginScreenName_LoginSecA_PrevAndNext(
        Session session, IpLogin ipLogin, String loginName, String loginSecA,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_IPLOGIN_WHERE);

        boolean bindLoginName = false;

        if (loginName == null) {
            query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINSECA_LOGINNAME_1);
        } else if (loginName.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINSECA_LOGINNAME_3);
        } else {
            bindLoginName = true;

            query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINSECA_LOGINNAME_2);
        }

        boolean bindLoginSecA = false;

        if (loginSecA == null) {
            query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINSECA_LOGINSECA_1);
        } else if (loginSecA.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINSECA_LOGINSECA_3);
        } else {
            bindLoginSecA = true;

            query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINSECA_LOGINSECA_2);
        }

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(IpLoginModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindLoginName) {
            qPos.add(loginName);
        }

        if (bindLoginSecA) {
            qPos.add(loginSecA);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(ipLogin);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<IpLogin> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the ip logins where loginName = &#63; and loginSecA = &#63; from the database.
     *
     * @param loginName the login name
     * @param loginSecA the login sec a
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeBygetIpLoginScreenName_LoginSecA(String loginName,
        String loginSecA) throws SystemException {
        for (IpLogin ipLogin : findBygetIpLoginScreenName_LoginSecA(loginName,
                loginSecA, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(ipLogin);
        }
    }

    /**
     * Returns the number of ip logins where loginName = &#63; and loginSecA = &#63;.
     *
     * @param loginName the login name
     * @param loginSecA the login sec a
     * @return the number of matching ip logins
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countBygetIpLoginScreenName_LoginSecA(String loginName,
        String loginSecA) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_GETIPLOGINSCREENNAME_LOGINSECA;

        Object[] finderArgs = new Object[] { loginName, loginSecA };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_IPLOGIN_WHERE);

            boolean bindLoginName = false;

            if (loginName == null) {
                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINSECA_LOGINNAME_1);
            } else if (loginName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINSECA_LOGINNAME_3);
            } else {
                bindLoginName = true;

                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINSECA_LOGINNAME_2);
            }

            boolean bindLoginSecA = false;

            if (loginSecA == null) {
                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINSECA_LOGINSECA_1);
            } else if (loginSecA.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINSECA_LOGINSECA_3);
            } else {
                bindLoginSecA = true;

                query.append(_FINDER_COLUMN_GETIPLOGINSCREENNAME_LOGINSECA_LOGINSECA_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindLoginName) {
                    qPos.add(loginName);
                }

                if (bindLoginSecA) {
                    qPos.add(loginSecA);
                }

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Caches the ip login in the entity cache if it is enabled.
     *
     * @param ipLogin the ip login
     */
    @Override
    public void cacheResult(IpLogin ipLogin) {
        EntityCacheUtil.putResult(IpLoginModelImpl.ENTITY_CACHE_ENABLED,
            IpLoginImpl.class, ipLogin.getPrimaryKey(), ipLogin);

        ipLogin.resetOriginalValues();
    }

    /**
     * Caches the ip logins in the entity cache if it is enabled.
     *
     * @param ipLogins the ip logins
     */
    @Override
    public void cacheResult(List<IpLogin> ipLogins) {
        for (IpLogin ipLogin : ipLogins) {
            if (EntityCacheUtil.getResult(
                        IpLoginModelImpl.ENTITY_CACHE_ENABLED,
                        IpLoginImpl.class, ipLogin.getPrimaryKey()) == null) {
                cacheResult(ipLogin);
            } else {
                ipLogin.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ip logins.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IpLoginImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IpLoginImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ip login.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IpLogin ipLogin) {
        EntityCacheUtil.removeResult(IpLoginModelImpl.ENTITY_CACHE_ENABLED,
            IpLoginImpl.class, ipLogin.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IpLogin> ipLogins) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IpLogin ipLogin : ipLogins) {
            EntityCacheUtil.removeResult(IpLoginModelImpl.ENTITY_CACHE_ENABLED,
                IpLoginImpl.class, ipLogin.getPrimaryKey());
        }
    }

    /**
     * Creates a new ip login with the primary key. Does not add the ip login to the database.
     *
     * @param loginId the primary key for the new ip login
     * @return the new ip login
     */
    @Override
    public IpLogin create(long loginId) {
        IpLogin ipLogin = new IpLoginImpl();

        ipLogin.setNew(true);
        ipLogin.setPrimaryKey(loginId);

        return ipLogin;
    }

    /**
     * Removes the ip login with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param loginId the primary key of the ip login
     * @return the ip login that was removed
     * @throws za.co.idea.ip.hook.NoSuchIpLoginException if a ip login with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpLogin remove(long loginId)
        throws NoSuchIpLoginException, SystemException {
        return remove((Serializable) loginId);
    }

    /**
     * Removes the ip login with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ip login
     * @return the ip login that was removed
     * @throws za.co.idea.ip.hook.NoSuchIpLoginException if a ip login with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpLogin remove(Serializable primaryKey)
        throws NoSuchIpLoginException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IpLogin ipLogin = (IpLogin) session.get(IpLoginImpl.class,
                    primaryKey);

            if (ipLogin == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIpLoginException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ipLogin);
        } catch (NoSuchIpLoginException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IpLogin removeImpl(IpLogin ipLogin) throws SystemException {
        ipLogin = toUnwrappedModel(ipLogin);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ipLogin)) {
                ipLogin = (IpLogin) session.get(IpLoginImpl.class,
                        ipLogin.getPrimaryKeyObj());
            }

            if (ipLogin != null) {
                session.delete(ipLogin);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ipLogin != null) {
            clearCache(ipLogin);
        }

        return ipLogin;
    }

    @Override
    public IpLogin updateImpl(za.co.idea.ip.hook.model.IpLogin ipLogin)
        throws SystemException {
        ipLogin = toUnwrappedModel(ipLogin);

        boolean isNew = ipLogin.isNew();

        IpLoginModelImpl ipLoginModelImpl = (IpLoginModelImpl) ipLogin;

        Session session = null;

        try {
            session = openSession();

            if (ipLogin.isNew()) {
                session.save(ipLogin);

                ipLogin.setNew(false);
            } else {
                session.merge(ipLogin);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !IpLoginModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((ipLoginModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPLOGIN.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        ipLoginModelImpl.getOriginalLoginId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GETIPLOGIN,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPLOGIN,
                    args);

                args = new Object[] { ipLoginModelImpl.getLoginId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GETIPLOGIN,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPLOGIN,
                    args);
            }

            if ((ipLoginModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPLOGINSCREENNAME_PASSWORD.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        ipLoginModelImpl.getOriginalLoginName(),
                        ipLoginModelImpl.getOriginalLoginPwd()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GETIPLOGINSCREENNAME_PASSWORD,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPLOGINSCREENNAME_PASSWORD,
                    args);

                args = new Object[] {
                        ipLoginModelImpl.getLoginName(),
                        ipLoginModelImpl.getLoginPwd()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GETIPLOGINSCREENNAME_PASSWORD,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPLOGINSCREENNAME_PASSWORD,
                    args);
            }

            if ((ipLoginModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPLOGINSCREENNAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        ipLoginModelImpl.getOriginalLoginName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GETIPLOGINSCREENNAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPLOGINSCREENNAME,
                    args);

                args = new Object[] { ipLoginModelImpl.getLoginName() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GETIPLOGINSCREENNAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPLOGINSCREENNAME,
                    args);
            }

            if ((ipLoginModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPLOGINSCREENNAME_LOGINSECA.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        ipLoginModelImpl.getOriginalLoginName(),
                        ipLoginModelImpl.getOriginalLoginSecA()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GETIPLOGINSCREENNAME_LOGINSECA,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPLOGINSCREENNAME_LOGINSECA,
                    args);

                args = new Object[] {
                        ipLoginModelImpl.getLoginName(),
                        ipLoginModelImpl.getLoginSecA()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GETIPLOGINSCREENNAME_LOGINSECA,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPLOGINSCREENNAME_LOGINSECA,
                    args);
            }
        }

        EntityCacheUtil.putResult(IpLoginModelImpl.ENTITY_CACHE_ENABLED,
            IpLoginImpl.class, ipLogin.getPrimaryKey(), ipLogin);

        return ipLogin;
    }

    protected IpLogin toUnwrappedModel(IpLogin ipLogin) {
        if (ipLogin instanceof IpLoginImpl) {
            return ipLogin;
        }

        IpLoginImpl ipLoginImpl = new IpLoginImpl();

        ipLoginImpl.setNew(ipLogin.isNew());
        ipLoginImpl.setPrimaryKey(ipLogin.getPrimaryKey());

        ipLoginImpl.setLoginId(ipLogin.getLoginId());
        ipLoginImpl.setLoginUserId(ipLogin.getLoginUserId());
        ipLoginImpl.setLoginName(ipLogin.getLoginName());
        ipLoginImpl.setLoginPwd(ipLogin.getLoginPwd());
        ipLoginImpl.setLoginLastDt(ipLogin.getLoginLastDt());
        ipLoginImpl.setLoginSecQ(ipLogin.getLoginSecQ());
        ipLoginImpl.setLoginSecA(ipLogin.getLoginSecA());

        return ipLoginImpl;
    }

    /**
     * Returns the ip login with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ip login
     * @return the ip login
     * @throws za.co.idea.ip.hook.NoSuchIpLoginException if a ip login with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpLogin findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIpLoginException, SystemException {
        IpLogin ipLogin = fetchByPrimaryKey(primaryKey);

        if (ipLogin == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIpLoginException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ipLogin;
    }

    /**
     * Returns the ip login with the primary key or throws a {@link za.co.idea.ip.hook.NoSuchIpLoginException} if it could not be found.
     *
     * @param loginId the primary key of the ip login
     * @return the ip login
     * @throws za.co.idea.ip.hook.NoSuchIpLoginException if a ip login with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpLogin findByPrimaryKey(long loginId)
        throws NoSuchIpLoginException, SystemException {
        return findByPrimaryKey((Serializable) loginId);
    }

    /**
     * Returns the ip login with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ip login
     * @return the ip login, or <code>null</code> if a ip login with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpLogin fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IpLogin ipLogin = (IpLogin) EntityCacheUtil.getResult(IpLoginModelImpl.ENTITY_CACHE_ENABLED,
                IpLoginImpl.class, primaryKey);

        if (ipLogin == _nullIpLogin) {
            return null;
        }

        if (ipLogin == null) {
            Session session = null;

            try {
                session = openSession();

                ipLogin = (IpLogin) session.get(IpLoginImpl.class, primaryKey);

                if (ipLogin != null) {
                    cacheResult(ipLogin);
                } else {
                    EntityCacheUtil.putResult(IpLoginModelImpl.ENTITY_CACHE_ENABLED,
                        IpLoginImpl.class, primaryKey, _nullIpLogin);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IpLoginModelImpl.ENTITY_CACHE_ENABLED,
                    IpLoginImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ipLogin;
    }

    /**
     * Returns the ip login with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param loginId the primary key of the ip login
     * @return the ip login, or <code>null</code> if a ip login with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpLogin fetchByPrimaryKey(long loginId) throws SystemException {
        return fetchByPrimaryKey((Serializable) loginId);
    }

    /**
     * Returns all the ip logins.
     *
     * @return the ip logins
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IpLogin> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
    public List<IpLogin> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
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
    @Override
    public List<IpLogin> findAll(int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
            finderArgs = FINDER_ARGS_EMPTY;
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
            finderArgs = new Object[] { start, end, orderByComparator };
        }

        List<IpLogin> list = (List<IpLogin>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IPLOGIN);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IPLOGIN;

                if (pagination) {
                    sql = sql.concat(IpLoginModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IpLogin>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IpLogin>(list);
                } else {
                    list = (List<IpLogin>) QueryUtil.list(q, getDialect(),
                            start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Removes all the ip logins from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IpLogin ipLogin : findAll()) {
            remove(ipLogin);
        }
    }

    /**
     * Returns the number of ip logins.
     *
     * @return the number of ip logins
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_IPLOGIN);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    @Override
    protected Set<String> getBadColumnNames() {
        return _badColumnNames;
    }

    /**
     * Initializes the ip login persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.za.co.idea.ip.hook.model.IpLogin")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IpLogin>> listenersList = new ArrayList<ModelListener<IpLogin>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IpLogin>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IpLoginImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
