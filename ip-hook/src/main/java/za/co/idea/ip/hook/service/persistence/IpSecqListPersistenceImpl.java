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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import za.co.idea.ip.hook.NoSuchIpSecqListException;
import za.co.idea.ip.hook.model.IpSecqList;
import za.co.idea.ip.hook.model.impl.IpSecqListImpl;
import za.co.idea.ip.hook.model.impl.IpSecqListModelImpl;
import za.co.idea.ip.hook.service.persistence.IpSecqListPersistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the ip secq list service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author VMPattamatta
 * @see IpSecqListPersistence
 * @see IpSecqListUtil
 * @generated
 */
@SuppressWarnings("unchecked")
public class IpSecqListPersistenceImpl extends BasePersistenceImpl<IpSecqList>
    implements IpSecqListPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IpSecqListUtil} to access the ip secq list persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IpSecqListImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IpSecqListModelImpl.ENTITY_CACHE_ENABLED,
            IpSecqListModelImpl.FINDER_CACHE_ENABLED, IpSecqListImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IpSecqListModelImpl.ENTITY_CACHE_ENABLED,
            IpSecqListModelImpl.FINDER_CACHE_ENABLED, IpSecqListImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IpSecqListModelImpl.ENTITY_CACHE_ENABLED,
            IpSecqListModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GETIPSECQLIST =
        new FinderPath(IpSecqListModelImpl.ENTITY_CACHE_ENABLED,
            IpSecqListModelImpl.FINDER_CACHE_ENABLED, IpSecqListImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBygetIpSecqList",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPSECQLIST =
        new FinderPath(IpSecqListModelImpl.ENTITY_CACHE_ENABLED,
            IpSecqListModelImpl.FINDER_CACHE_ENABLED, IpSecqListImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBygetIpSecqList",
            new String[] { Long.class.getName() },
            IpSecqListModelImpl.ISLID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_GETIPSECQLIST = new FinderPath(IpSecqListModelImpl.ENTITY_CACHE_ENABLED,
            IpSecqListModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBygetIpSecqList",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_GETIPSECQLIST_ISLID_2 = "ipSecqList.islId = ?";
    private static final String _SQL_SELECT_IPSECQLIST = "SELECT ipSecqList FROM IpSecqList ipSecqList";
    private static final String _SQL_SELECT_IPSECQLIST_WHERE = "SELECT ipSecqList FROM IpSecqList ipSecqList WHERE ";
    private static final String _SQL_COUNT_IPSECQLIST = "SELECT COUNT(ipSecqList) FROM IpSecqList ipSecqList";
    private static final String _SQL_COUNT_IPSECQLIST_WHERE = "SELECT COUNT(ipSecqList) FROM IpSecqList ipSecqList WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ipSecqList.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IpSecqList exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No IpSecqList exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IpSecqListPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "islId", "islDesc"
            });
    private static IpSecqList _nullIpSecqList = new IpSecqListImpl() {
            /**
		 * 
		 */
		private static final long serialVersionUID = -349690112250409962L;

			@Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IpSecqList> toCacheModel() {
                return _nullIpSecqListCacheModel;
            }
        };

    private static CacheModel<IpSecqList> _nullIpSecqListCacheModel = new CacheModel<IpSecqList>() {
            /**
		 * 
		 */
		private static final long serialVersionUID = 6961700851633733709L;

			@Override
            public IpSecqList toEntityModel() {
                return _nullIpSecqList;
            }
        };

    public IpSecqListPersistenceImpl() {
        setModelClass(IpSecqList.class);
    }

    /**
     * Returns all the ip secq lists where islId = &#63;.
     *
     * @param islId the isl ID
     * @return the matching ip secq lists
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IpSecqList> findBygetIpSecqList(long islId)
        throws SystemException {
        return findBygetIpSecqList(islId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

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
    @Override
    public List<IpSecqList> findBygetIpSecqList(long islId, int start, int end)
        throws SystemException {
        return findBygetIpSecqList(islId, start, end, null);
    }

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
    @Override
    public List<IpSecqList> findBygetIpSecqList(long islId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPSECQLIST;
            finderArgs = new Object[] { islId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GETIPSECQLIST;
            finderArgs = new Object[] { islId, start, end, orderByComparator };
        }

        List<IpSecqList> list = (List<IpSecqList>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (IpSecqList ipSecqList : list) {
                if ((islId != ipSecqList.getIslId())) {
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

            query.append(_SQL_SELECT_IPSECQLIST_WHERE);

            query.append(_FINDER_COLUMN_GETIPSECQLIST_ISLID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(IpSecqListModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(islId);

                if (!pagination) {
                    list = (List<IpSecqList>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IpSecqList>(list);
                } else {
                    list = (List<IpSecqList>) QueryUtil.list(q, getDialect(),
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
     * Returns the first ip secq list in the ordered set where islId = &#63;.
     *
     * @param islId the isl ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ip secq list
     * @throws za.co.idea.ip.hook.NoSuchIpSecqListException if a matching ip secq list could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpSecqList findBygetIpSecqList_First(long islId,
        OrderByComparator orderByComparator)
        throws NoSuchIpSecqListException, SystemException {
        IpSecqList ipSecqList = fetchBygetIpSecqList_First(islId,
                orderByComparator);

        if (ipSecqList != null) {
            return ipSecqList;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("islId=");
        msg.append(islId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIpSecqListException(msg.toString());
    }

    /**
     * Returns the first ip secq list in the ordered set where islId = &#63;.
     *
     * @param islId the isl ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ip secq list, or <code>null</code> if a matching ip secq list could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpSecqList fetchBygetIpSecqList_First(long islId,
        OrderByComparator orderByComparator) throws SystemException {
        List<IpSecqList> list = findBygetIpSecqList(islId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last ip secq list in the ordered set where islId = &#63;.
     *
     * @param islId the isl ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ip secq list
     * @throws za.co.idea.ip.hook.NoSuchIpSecqListException if a matching ip secq list could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpSecqList findBygetIpSecqList_Last(long islId,
        OrderByComparator orderByComparator)
        throws NoSuchIpSecqListException, SystemException {
        IpSecqList ipSecqList = fetchBygetIpSecqList_Last(islId,
                orderByComparator);

        if (ipSecqList != null) {
            return ipSecqList;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("islId=");
        msg.append(islId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIpSecqListException(msg.toString());
    }

    /**
     * Returns the last ip secq list in the ordered set where islId = &#63;.
     *
     * @param islId the isl ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ip secq list, or <code>null</code> if a matching ip secq list could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpSecqList fetchBygetIpSecqList_Last(long islId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countBygetIpSecqList(islId);

        if (count == 0) {
            return null;
        }

        List<IpSecqList> list = findBygetIpSecqList(islId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Removes all the ip secq lists where islId = &#63; from the database.
     *
     * @param islId the isl ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeBygetIpSecqList(long islId) throws SystemException {
        for (IpSecqList ipSecqList : findBygetIpSecqList(islId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(ipSecqList);
        }
    }

    /**
     * Returns the number of ip secq lists where islId = &#63;.
     *
     * @param islId the isl ID
     * @return the number of matching ip secq lists
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countBygetIpSecqList(long islId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_GETIPSECQLIST;

        Object[] finderArgs = new Object[] { islId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_IPSECQLIST_WHERE);

            query.append(_FINDER_COLUMN_GETIPSECQLIST_ISLID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(islId);

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
     * Caches the ip secq list in the entity cache if it is enabled.
     *
     * @param ipSecqList the ip secq list
     */
    @Override
    public void cacheResult(IpSecqList ipSecqList) {
        EntityCacheUtil.putResult(IpSecqListModelImpl.ENTITY_CACHE_ENABLED,
            IpSecqListImpl.class, ipSecqList.getPrimaryKey(), ipSecqList);

        ipSecqList.resetOriginalValues();
    }

    /**
     * Caches the ip secq lists in the entity cache if it is enabled.
     *
     * @param ipSecqLists the ip secq lists
     */
    @Override
    public void cacheResult(List<IpSecqList> ipSecqLists) {
        for (IpSecqList ipSecqList : ipSecqLists) {
            if (EntityCacheUtil.getResult(
                        IpSecqListModelImpl.ENTITY_CACHE_ENABLED,
                        IpSecqListImpl.class, ipSecqList.getPrimaryKey()) == null) {
                cacheResult(ipSecqList);
            } else {
                ipSecqList.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ip secq lists.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IpSecqListImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IpSecqListImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ip secq list.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IpSecqList ipSecqList) {
        EntityCacheUtil.removeResult(IpSecqListModelImpl.ENTITY_CACHE_ENABLED,
            IpSecqListImpl.class, ipSecqList.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IpSecqList> ipSecqLists) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IpSecqList ipSecqList : ipSecqLists) {
            EntityCacheUtil.removeResult(IpSecqListModelImpl.ENTITY_CACHE_ENABLED,
                IpSecqListImpl.class, ipSecqList.getPrimaryKey());
        }
    }

    /**
     * Creates a new ip secq list with the primary key. Does not add the ip secq list to the database.
     *
     * @param islId the primary key for the new ip secq list
     * @return the new ip secq list
     */
    @Override
    public IpSecqList create(long islId) {
        IpSecqList ipSecqList = new IpSecqListImpl();

        ipSecqList.setNew(true);
        ipSecqList.setPrimaryKey(islId);

        return ipSecqList;
    }

    /**
     * Removes the ip secq list with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param islId the primary key of the ip secq list
     * @return the ip secq list that was removed
     * @throws za.co.idea.ip.hook.NoSuchIpSecqListException if a ip secq list with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpSecqList remove(long islId)
        throws NoSuchIpSecqListException, SystemException {
        return remove((Serializable) islId);
    }

    /**
     * Removes the ip secq list with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ip secq list
     * @return the ip secq list that was removed
     * @throws za.co.idea.ip.hook.NoSuchIpSecqListException if a ip secq list with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpSecqList remove(Serializable primaryKey)
        throws NoSuchIpSecqListException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IpSecqList ipSecqList = (IpSecqList) session.get(IpSecqListImpl.class,
                    primaryKey);

            if (ipSecqList == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIpSecqListException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ipSecqList);
        } catch (NoSuchIpSecqListException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IpSecqList removeImpl(IpSecqList ipSecqList)
        throws SystemException {
        ipSecqList = toUnwrappedModel(ipSecqList);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ipSecqList)) {
                ipSecqList = (IpSecqList) session.get(IpSecqListImpl.class,
                        ipSecqList.getPrimaryKeyObj());
            }

            if (ipSecqList != null) {
                session.delete(ipSecqList);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ipSecqList != null) {
            clearCache(ipSecqList);
        }

        return ipSecqList;
    }

    @Override
    public IpSecqList updateImpl(za.co.idea.ip.hook.model.IpSecqList ipSecqList)
        throws SystemException {
        ipSecqList = toUnwrappedModel(ipSecqList);

        boolean isNew = ipSecqList.isNew();

        IpSecqListModelImpl ipSecqListModelImpl = (IpSecqListModelImpl) ipSecqList;

        Session session = null;

        try {
            session = openSession();

            if (ipSecqList.isNew()) {
                session.save(ipSecqList);

                ipSecqList.setNew(false);
            } else {
                session.merge(ipSecqList);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !IpSecqListModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((ipSecqListModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPSECQLIST.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        ipSecqListModelImpl.getOriginalIslId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GETIPSECQLIST,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPSECQLIST,
                    args);

                args = new Object[] { ipSecqListModelImpl.getIslId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GETIPSECQLIST,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPSECQLIST,
                    args);
            }
        }

        EntityCacheUtil.putResult(IpSecqListModelImpl.ENTITY_CACHE_ENABLED,
            IpSecqListImpl.class, ipSecqList.getPrimaryKey(), ipSecqList);

        return ipSecqList;
    }

    protected IpSecqList toUnwrappedModel(IpSecqList ipSecqList) {
        if (ipSecqList instanceof IpSecqListImpl) {
            return ipSecqList;
        }

        IpSecqListImpl ipSecqListImpl = new IpSecqListImpl();

        ipSecqListImpl.setNew(ipSecqList.isNew());
        ipSecqListImpl.setPrimaryKey(ipSecqList.getPrimaryKey());

        ipSecqListImpl.setIslId(ipSecqList.getIslId());
        ipSecqListImpl.setIslDesc(ipSecqList.getIslDesc());

        return ipSecqListImpl;
    }

    /**
     * Returns the ip secq list with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ip secq list
     * @return the ip secq list
     * @throws za.co.idea.ip.hook.NoSuchIpSecqListException if a ip secq list with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpSecqList findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIpSecqListException, SystemException {
        IpSecqList ipSecqList = fetchByPrimaryKey(primaryKey);

        if (ipSecqList == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIpSecqListException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ipSecqList;
    }

    /**
     * Returns the ip secq list with the primary key or throws a {@link za.co.idea.ip.hook.NoSuchIpSecqListException} if it could not be found.
     *
     * @param islId the primary key of the ip secq list
     * @return the ip secq list
     * @throws za.co.idea.ip.hook.NoSuchIpSecqListException if a ip secq list with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpSecqList findByPrimaryKey(long islId)
        throws NoSuchIpSecqListException, SystemException {
        return findByPrimaryKey((Serializable) islId);
    }

    /**
     * Returns the ip secq list with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ip secq list
     * @return the ip secq list, or <code>null</code> if a ip secq list with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpSecqList fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IpSecqList ipSecqList = (IpSecqList) EntityCacheUtil.getResult(IpSecqListModelImpl.ENTITY_CACHE_ENABLED,
                IpSecqListImpl.class, primaryKey);

        if (ipSecqList == _nullIpSecqList) {
            return null;
        }

        if (ipSecqList == null) {
            Session session = null;

            try {
                session = openSession();

                ipSecqList = (IpSecqList) session.get(IpSecqListImpl.class,
                        primaryKey);

                if (ipSecqList != null) {
                    cacheResult(ipSecqList);
                } else {
                    EntityCacheUtil.putResult(IpSecqListModelImpl.ENTITY_CACHE_ENABLED,
                        IpSecqListImpl.class, primaryKey, _nullIpSecqList);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IpSecqListModelImpl.ENTITY_CACHE_ENABLED,
                    IpSecqListImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ipSecqList;
    }

    /**
     * Returns the ip secq list with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param islId the primary key of the ip secq list
     * @return the ip secq list, or <code>null</code> if a ip secq list with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpSecqList fetchByPrimaryKey(long islId) throws SystemException {
        return fetchByPrimaryKey((Serializable) islId);
    }

    /**
     * Returns all the ip secq lists.
     *
     * @return the ip secq lists
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IpSecqList> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
    public List<IpSecqList> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

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
    @Override
    public List<IpSecqList> findAll(int start, int end,
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

        List<IpSecqList> list = (List<IpSecqList>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IPSECQLIST);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IPSECQLIST;

                if (pagination) {
                    sql = sql.concat(IpSecqListModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IpSecqList>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IpSecqList>(list);
                } else {
                    list = (List<IpSecqList>) QueryUtil.list(q, getDialect(),
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
     * Removes all the ip secq lists from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IpSecqList ipSecqList : findAll()) {
            remove(ipSecqList);
        }
    }

    /**
     * Returns the number of ip secq lists.
     *
     * @return the number of ip secq lists
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

                Query q = session.createQuery(_SQL_COUNT_IPSECQLIST);

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
     * Initializes the ip secq list persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.za.co.idea.ip.hook.model.IpSecqList")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IpSecqList>> listenersList = new ArrayList<ModelListener<IpSecqList>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IpSecqList>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IpSecqListImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
