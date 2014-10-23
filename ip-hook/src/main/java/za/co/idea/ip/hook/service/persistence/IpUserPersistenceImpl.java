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

import za.co.idea.ip.hook.NoSuchIpUserException;
import za.co.idea.ip.hook.model.IpUser;
import za.co.idea.ip.hook.model.impl.IpUserImpl;
import za.co.idea.ip.hook.model.impl.IpUserModelImpl;
import za.co.idea.ip.hook.service.persistence.IpUserPersistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the ip user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author VMPattamatta
 * @see IpUserPersistence
 * @see IpUserUtil
 * @generated
 */
@SuppressWarnings("unchecked")
public class IpUserPersistenceImpl extends BasePersistenceImpl<IpUser>
    implements IpUserPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IpUserUtil} to access the ip user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IpUserImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IpUserModelImpl.ENTITY_CACHE_ENABLED,
            IpUserModelImpl.FINDER_CACHE_ENABLED, IpUserImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IpUserModelImpl.ENTITY_CACHE_ENABLED,
            IpUserModelImpl.FINDER_CACHE_ENABLED, IpUserImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IpUserModelImpl.ENTITY_CACHE_ENABLED,
            IpUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GETIPUSER =
        new FinderPath(IpUserModelImpl.ENTITY_CACHE_ENABLED,
            IpUserModelImpl.FINDER_CACHE_ENABLED, IpUserImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBygetIpUser",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPUSER =
        new FinderPath(IpUserModelImpl.ENTITY_CACHE_ENABLED,
            IpUserModelImpl.FINDER_CACHE_ENABLED, IpUserImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBygetIpUser",
            new String[] { Long.class.getName() },
            IpUserModelImpl.USERID_COLUMN_BITMASK |
            IpUserModelImpl.USERSCREENNAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_GETIPUSER = new FinderPath(IpUserModelImpl.ENTITY_CACHE_ENABLED,
            IpUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBygetIpUser",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_GETIPUSER_USERID_2 = "ipUser.userId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GETIPUSERSCREENNAME =
        new FinderPath(IpUserModelImpl.ENTITY_CACHE_ENABLED,
            IpUserModelImpl.FINDER_CACHE_ENABLED, IpUserImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findBygetIpUserScreenName",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPUSERSCREENNAME =
        new FinderPath(IpUserModelImpl.ENTITY_CACHE_ENABLED,
            IpUserModelImpl.FINDER_CACHE_ENABLED, IpUserImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findBygetIpUserScreenName",
            new String[] { String.class.getName() },
            IpUserModelImpl.USERSCREENNAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_GETIPUSERSCREENNAME = new FinderPath(IpUserModelImpl.ENTITY_CACHE_ENABLED,
            IpUserModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countBygetIpUserScreenName",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_GETIPUSERSCREENNAME_USERSCREENNAME_1 =
        "ipUser.userScreenName IS NULL";
    private static final String _FINDER_COLUMN_GETIPUSERSCREENNAME_USERSCREENNAME_2 =
        "ipUser.userScreenName = ?";
    private static final String _FINDER_COLUMN_GETIPUSERSCREENNAME_USERSCREENNAME_3 =
        "(ipUser.userScreenName IS NULL OR ipUser.userScreenName = '')";
    private static final String _SQL_SELECT_IPUSER = "SELECT ipUser FROM IpUser ipUser";
    private static final String _SQL_SELECT_IPUSER_WHERE = "SELECT ipUser FROM IpUser ipUser WHERE ";
    private static final String _SQL_COUNT_IPUSER = "SELECT COUNT(ipUser) FROM IpUser ipUser";
    private static final String _SQL_COUNT_IPUSER_WHERE = "SELECT COUNT(ipUser) FROM IpUser ipUser WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ipUser.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IpUser exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No IpUser exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IpUserPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "userId", "userFName", "userLName", "userMName", "userIdNum",
                "userScreenName", "userEmail", "userContact", "userSkills",
                "userBio", "userFbHandle", "userTwHandle", "userStatus",
                "userEmployeeId", "priGrpId"
            });
    private static IpUser _nullIpUser = new IpUserImpl() {
            /**
		 * 
		 */
		private static final long serialVersionUID = 3545964634877503091L;

			@Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IpUser> toCacheModel() {
                return _nullIpUserCacheModel;
            }
        };

    private static CacheModel<IpUser> _nullIpUserCacheModel = new CacheModel<IpUser>() {
            /**
		 * 
		 */
		private static final long serialVersionUID = -8986134782529486272L;

			@Override
            public IpUser toEntityModel() {
                return _nullIpUser;
            }
        };

    public IpUserPersistenceImpl() {
        setModelClass(IpUser.class);
    }

    /**
     * Returns all the ip users where userId = &#63;.
     *
     * @param userId the user ID
     * @return the matching ip users
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IpUser> findBygetIpUser(long userId) throws SystemException {
        return findBygetIpUser(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
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
    @Override
    public List<IpUser> findBygetIpUser(long userId, int start, int end)
        throws SystemException {
        return findBygetIpUser(userId, start, end, null);
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
    @Override
    public List<IpUser> findBygetIpUser(long userId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPUSER;
            finderArgs = new Object[] { userId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GETIPUSER;
            finderArgs = new Object[] { userId, start, end, orderByComparator };
        }

        List<IpUser> list = (List<IpUser>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (IpUser ipUser : list) {
                if ((userId != ipUser.getUserId())) {
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

            query.append(_SQL_SELECT_IPUSER_WHERE);

            query.append(_FINDER_COLUMN_GETIPUSER_USERID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(IpUserModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

                if (!pagination) {
                    list = (List<IpUser>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IpUser>(list);
                } else {
                    list = (List<IpUser>) QueryUtil.list(q, getDialect(),
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
     * Returns the first ip user in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ip user
     * @throws za.co.idea.ip.hook.NoSuchIpUserException if a matching ip user could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpUser findBygetIpUser_First(long userId,
        OrderByComparator orderByComparator)
        throws NoSuchIpUserException, SystemException {
        IpUser ipUser = fetchBygetIpUser_First(userId, orderByComparator);

        if (ipUser != null) {
            return ipUser;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("userId=");
        msg.append(userId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIpUserException(msg.toString());
    }

    /**
     * Returns the first ip user in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ip user, or <code>null</code> if a matching ip user could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpUser fetchBygetIpUser_First(long userId,
        OrderByComparator orderByComparator) throws SystemException {
        List<IpUser> list = findBygetIpUser(userId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public IpUser findBygetIpUser_Last(long userId,
        OrderByComparator orderByComparator)
        throws NoSuchIpUserException, SystemException {
        IpUser ipUser = fetchBygetIpUser_Last(userId, orderByComparator);

        if (ipUser != null) {
            return ipUser;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("userId=");
        msg.append(userId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIpUserException(msg.toString());
    }

    /**
     * Returns the last ip user in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ip user, or <code>null</code> if a matching ip user could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpUser fetchBygetIpUser_Last(long userId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countBygetIpUser(userId);

        if (count == 0) {
            return null;
        }

        List<IpUser> list = findBygetIpUser(userId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Removes all the ip users where userId = &#63; from the database.
     *
     * @param userId the user ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeBygetIpUser(long userId) throws SystemException {
        for (IpUser ipUser : findBygetIpUser(userId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(ipUser);
        }
    }

    /**
     * Returns the number of ip users where userId = &#63;.
     *
     * @param userId the user ID
     * @return the number of matching ip users
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countBygetIpUser(long userId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_GETIPUSER;

        Object[] finderArgs = new Object[] { userId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_IPUSER_WHERE);

            query.append(_FINDER_COLUMN_GETIPUSER_USERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

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
     * Returns all the ip users where userScreenName = &#63;.
     *
     * @param userScreenName the user screen name
     * @return the matching ip users
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IpUser> findBygetIpUserScreenName(String userScreenName)
        throws SystemException {
        return findBygetIpUserScreenName(userScreenName, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
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
    @Override
    public List<IpUser> findBygetIpUserScreenName(String userScreenName,
        int start, int end) throws SystemException {
        return findBygetIpUserScreenName(userScreenName, start, end, null);
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
    @Override
    public List<IpUser> findBygetIpUserScreenName(String userScreenName,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPUSERSCREENNAME;
            finderArgs = new Object[] { userScreenName };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GETIPUSERSCREENNAME;
            finderArgs = new Object[] {
                    userScreenName,
                    
                    start, end, orderByComparator
                };
        }

        List<IpUser> list = (List<IpUser>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (IpUser ipUser : list) {
                if (!Validator.equals(userScreenName, ipUser.getUserScreenName())) {
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

            query.append(_SQL_SELECT_IPUSER_WHERE);

            boolean bindUserScreenName = false;

            if (userScreenName == null) {
                query.append(_FINDER_COLUMN_GETIPUSERSCREENNAME_USERSCREENNAME_1);
            } else if (userScreenName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_GETIPUSERSCREENNAME_USERSCREENNAME_3);
            } else {
                bindUserScreenName = true;

                query.append(_FINDER_COLUMN_GETIPUSERSCREENNAME_USERSCREENNAME_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(IpUserModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindUserScreenName) {
                    qPos.add(userScreenName);
                }

                if (!pagination) {
                    list = (List<IpUser>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IpUser>(list);
                } else {
                    list = (List<IpUser>) QueryUtil.list(q, getDialect(),
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
     * Returns the first ip user in the ordered set where userScreenName = &#63;.
     *
     * @param userScreenName the user screen name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ip user
     * @throws za.co.idea.ip.hook.NoSuchIpUserException if a matching ip user could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpUser findBygetIpUserScreenName_First(String userScreenName,
        OrderByComparator orderByComparator)
        throws NoSuchIpUserException, SystemException {
        IpUser ipUser = fetchBygetIpUserScreenName_First(userScreenName,
                orderByComparator);

        if (ipUser != null) {
            return ipUser;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("userScreenName=");
        msg.append(userScreenName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIpUserException(msg.toString());
    }

    /**
     * Returns the first ip user in the ordered set where userScreenName = &#63;.
     *
     * @param userScreenName the user screen name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ip user, or <code>null</code> if a matching ip user could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpUser fetchBygetIpUserScreenName_First(String userScreenName,
        OrderByComparator orderByComparator) throws SystemException {
        List<IpUser> list = findBygetIpUserScreenName(userScreenName, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public IpUser findBygetIpUserScreenName_Last(String userScreenName,
        OrderByComparator orderByComparator)
        throws NoSuchIpUserException, SystemException {
        IpUser ipUser = fetchBygetIpUserScreenName_Last(userScreenName,
                orderByComparator);

        if (ipUser != null) {
            return ipUser;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("userScreenName=");
        msg.append(userScreenName);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIpUserException(msg.toString());
    }

    /**
     * Returns the last ip user in the ordered set where userScreenName = &#63;.
     *
     * @param userScreenName the user screen name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ip user, or <code>null</code> if a matching ip user could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpUser fetchBygetIpUserScreenName_Last(String userScreenName,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countBygetIpUserScreenName(userScreenName);

        if (count == 0) {
            return null;
        }

        List<IpUser> list = findBygetIpUserScreenName(userScreenName,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public IpUser[] findBygetIpUserScreenName_PrevAndNext(long userId,
        String userScreenName, OrderByComparator orderByComparator)
        throws NoSuchIpUserException, SystemException {
        IpUser ipUser = findByPrimaryKey(userId);

        Session session = null;

        try {
            session = openSession();

            IpUser[] array = new IpUserImpl[3];

            array[0] = getBygetIpUserScreenName_PrevAndNext(session, ipUser,
                    userScreenName, orderByComparator, true);

            array[1] = ipUser;

            array[2] = getBygetIpUserScreenName_PrevAndNext(session, ipUser,
                    userScreenName, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected IpUser getBygetIpUserScreenName_PrevAndNext(Session session,
        IpUser ipUser, String userScreenName,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_IPUSER_WHERE);

        boolean bindUserScreenName = false;

        if (userScreenName == null) {
            query.append(_FINDER_COLUMN_GETIPUSERSCREENNAME_USERSCREENNAME_1);
        } else if (userScreenName.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_GETIPUSERSCREENNAME_USERSCREENNAME_3);
        } else {
            bindUserScreenName = true;

            query.append(_FINDER_COLUMN_GETIPUSERSCREENNAME_USERSCREENNAME_2);
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
            query.append(IpUserModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindUserScreenName) {
            qPos.add(userScreenName);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(ipUser);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<IpUser> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the ip users where userScreenName = &#63; from the database.
     *
     * @param userScreenName the user screen name
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeBygetIpUserScreenName(String userScreenName)
        throws SystemException {
        for (IpUser ipUser : findBygetIpUserScreenName(userScreenName,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(ipUser);
        }
    }

    /**
     * Returns the number of ip users where userScreenName = &#63;.
     *
     * @param userScreenName the user screen name
     * @return the number of matching ip users
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countBygetIpUserScreenName(String userScreenName)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_GETIPUSERSCREENNAME;

        Object[] finderArgs = new Object[] { userScreenName };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_IPUSER_WHERE);

            boolean bindUserScreenName = false;

            if (userScreenName == null) {
                query.append(_FINDER_COLUMN_GETIPUSERSCREENNAME_USERSCREENNAME_1);
            } else if (userScreenName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_GETIPUSERSCREENNAME_USERSCREENNAME_3);
            } else {
                bindUserScreenName = true;

                query.append(_FINDER_COLUMN_GETIPUSERSCREENNAME_USERSCREENNAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindUserScreenName) {
                    qPos.add(userScreenName);
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
     * Caches the ip user in the entity cache if it is enabled.
     *
     * @param ipUser the ip user
     */
    @Override
    public void cacheResult(IpUser ipUser) {
        EntityCacheUtil.putResult(IpUserModelImpl.ENTITY_CACHE_ENABLED,
            IpUserImpl.class, ipUser.getPrimaryKey(), ipUser);

        ipUser.resetOriginalValues();
    }

    /**
     * Caches the ip users in the entity cache if it is enabled.
     *
     * @param ipUsers the ip users
     */
    @Override
    public void cacheResult(List<IpUser> ipUsers) {
        for (IpUser ipUser : ipUsers) {
            if (EntityCacheUtil.getResult(
                        IpUserModelImpl.ENTITY_CACHE_ENABLED, IpUserImpl.class,
                        ipUser.getPrimaryKey()) == null) {
                cacheResult(ipUser);
            } else {
                ipUser.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ip users.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IpUserImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IpUserImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ip user.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IpUser ipUser) {
        EntityCacheUtil.removeResult(IpUserModelImpl.ENTITY_CACHE_ENABLED,
            IpUserImpl.class, ipUser.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IpUser> ipUsers) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IpUser ipUser : ipUsers) {
            EntityCacheUtil.removeResult(IpUserModelImpl.ENTITY_CACHE_ENABLED,
                IpUserImpl.class, ipUser.getPrimaryKey());
        }
    }

    /**
     * Creates a new ip user with the primary key. Does not add the ip user to the database.
     *
     * @param userId the primary key for the new ip user
     * @return the new ip user
     */
    @Override
    public IpUser create(long userId) {
        IpUser ipUser = new IpUserImpl();

        ipUser.setNew(true);
        ipUser.setPrimaryKey(userId);

        return ipUser;
    }

    /**
     * Removes the ip user with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param userId the primary key of the ip user
     * @return the ip user that was removed
     * @throws za.co.idea.ip.hook.NoSuchIpUserException if a ip user with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpUser remove(long userId)
        throws NoSuchIpUserException, SystemException {
        return remove((Serializable) userId);
    }

    /**
     * Removes the ip user with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ip user
     * @return the ip user that was removed
     * @throws za.co.idea.ip.hook.NoSuchIpUserException if a ip user with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpUser remove(Serializable primaryKey)
        throws NoSuchIpUserException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IpUser ipUser = (IpUser) session.get(IpUserImpl.class, primaryKey);

            if (ipUser == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIpUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ipUser);
        } catch (NoSuchIpUserException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IpUser removeImpl(IpUser ipUser) throws SystemException {
        ipUser = toUnwrappedModel(ipUser);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ipUser)) {
                ipUser = (IpUser) session.get(IpUserImpl.class,
                        ipUser.getPrimaryKeyObj());
            }

            if (ipUser != null) {
                session.delete(ipUser);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ipUser != null) {
            clearCache(ipUser);
        }

        return ipUser;
    }

    @Override
    public IpUser updateImpl(za.co.idea.ip.hook.model.IpUser ipUser)
        throws SystemException {
        ipUser = toUnwrappedModel(ipUser);

        boolean isNew = ipUser.isNew();

        IpUserModelImpl ipUserModelImpl = (IpUserModelImpl) ipUser;

        Session session = null;

        try {
            session = openSession();

            if (ipUser.isNew()) {
                session.save(ipUser);

                ipUser.setNew(false);
            } else {
                session.merge(ipUser);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !IpUserModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((ipUserModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPUSER.getColumnBitmask()) != 0) {
                Object[] args = new Object[] { ipUserModelImpl.getOriginalUserId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GETIPUSER,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPUSER,
                    args);

                args = new Object[] { ipUserModelImpl.getUserId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GETIPUSER,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPUSER,
                    args);
            }

            if ((ipUserModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPUSERSCREENNAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        ipUserModelImpl.getOriginalUserScreenName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GETIPUSERSCREENNAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPUSERSCREENNAME,
                    args);

                args = new Object[] { ipUserModelImpl.getUserScreenName() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GETIPUSERSCREENNAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPUSERSCREENNAME,
                    args);
            }
        }

        EntityCacheUtil.putResult(IpUserModelImpl.ENTITY_CACHE_ENABLED,
            IpUserImpl.class, ipUser.getPrimaryKey(), ipUser);

        return ipUser;
    }

    protected IpUser toUnwrappedModel(IpUser ipUser) {
        if (ipUser instanceof IpUserImpl) {
            return ipUser;
        }

        IpUserImpl ipUserImpl = new IpUserImpl();

        ipUserImpl.setNew(ipUser.isNew());
        ipUserImpl.setPrimaryKey(ipUser.getPrimaryKey());

        ipUserImpl.setUserId(ipUser.getUserId());
        ipUserImpl.setUserFName(ipUser.getUserFName());
        ipUserImpl.setUserLName(ipUser.getUserLName());
        ipUserImpl.setUserMName(ipUser.getUserMName());
        ipUserImpl.setUserIdNum(ipUser.getUserIdNum());
        ipUserImpl.setUserScreenName(ipUser.getUserScreenName());
        ipUserImpl.setUserEmail(ipUser.getUserEmail());
        ipUserImpl.setUserContact(ipUser.getUserContact());
        ipUserImpl.setUserSkills(ipUser.getUserSkills());
        ipUserImpl.setUserBio(ipUser.getUserBio());
        ipUserImpl.setUserFbHandle(ipUser.getUserFbHandle());
        ipUserImpl.setUserTwHandle(ipUser.getUserTwHandle());
        ipUserImpl.setUserStatus(ipUser.getUserStatus());
        ipUserImpl.setUserEmployeeId(ipUser.getUserEmployeeId());
        ipUserImpl.setPriGrpId(ipUser.getPriGrpId());

        return ipUserImpl;
    }

    /**
     * Returns the ip user with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ip user
     * @return the ip user
     * @throws za.co.idea.ip.hook.NoSuchIpUserException if a ip user with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpUser findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIpUserException, SystemException {
        IpUser ipUser = fetchByPrimaryKey(primaryKey);

        if (ipUser == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIpUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ipUser;
    }

    /**
     * Returns the ip user with the primary key or throws a {@link za.co.idea.ip.hook.NoSuchIpUserException} if it could not be found.
     *
     * @param userId the primary key of the ip user
     * @return the ip user
     * @throws za.co.idea.ip.hook.NoSuchIpUserException if a ip user with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpUser findByPrimaryKey(long userId)
        throws NoSuchIpUserException, SystemException {
        return findByPrimaryKey((Serializable) userId);
    }

    /**
     * Returns the ip user with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ip user
     * @return the ip user, or <code>null</code> if a ip user with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpUser fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IpUser ipUser = (IpUser) EntityCacheUtil.getResult(IpUserModelImpl.ENTITY_CACHE_ENABLED,
                IpUserImpl.class, primaryKey);

        if (ipUser == _nullIpUser) {
            return null;
        }

        if (ipUser == null) {
            Session session = null;

            try {
                session = openSession();

                ipUser = (IpUser) session.get(IpUserImpl.class, primaryKey);

                if (ipUser != null) {
                    cacheResult(ipUser);
                } else {
                    EntityCacheUtil.putResult(IpUserModelImpl.ENTITY_CACHE_ENABLED,
                        IpUserImpl.class, primaryKey, _nullIpUser);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IpUserModelImpl.ENTITY_CACHE_ENABLED,
                    IpUserImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ipUser;
    }

    /**
     * Returns the ip user with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param userId the primary key of the ip user
     * @return the ip user, or <code>null</code> if a ip user with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpUser fetchByPrimaryKey(long userId) throws SystemException {
        return fetchByPrimaryKey((Serializable) userId);
    }

    /**
     * Returns all the ip users.
     *
     * @return the ip users
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IpUser> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
    public List<IpUser> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
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
    @Override
    public List<IpUser> findAll(int start, int end,
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

        List<IpUser> list = (List<IpUser>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IPUSER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IPUSER;

                if (pagination) {
                    sql = sql.concat(IpUserModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IpUser>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IpUser>(list);
                } else {
                    list = (List<IpUser>) QueryUtil.list(q, getDialect(),
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
     * Removes all the ip users from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IpUser ipUser : findAll()) {
            remove(ipUser);
        }
    }

    /**
     * Returns the number of ip users.
     *
     * @return the number of ip users
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

                Query q = session.createQuery(_SQL_COUNT_IPUSER);

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
     * Initializes the ip user persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.za.co.idea.ip.hook.model.IpUser")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IpUser>> listenersList = new ArrayList<ModelListener<IpUser>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IpUser>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IpUserImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
