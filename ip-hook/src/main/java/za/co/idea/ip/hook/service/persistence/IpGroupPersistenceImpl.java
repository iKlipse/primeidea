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

import za.co.idea.ip.hook.NoSuchIpGroupException;
import za.co.idea.ip.hook.model.IpGroup;
import za.co.idea.ip.hook.model.impl.IpGroupImpl;
import za.co.idea.ip.hook.model.impl.IpGroupModelImpl;
import za.co.idea.ip.hook.service.persistence.IpGroupPersistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the ip group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author VMPattamatta
 * @see IpGroupPersistence
 * @see IpGroupUtil
 * @generated
 */
@SuppressWarnings("unchecked")
public class IpGroupPersistenceImpl extends BasePersistenceImpl<IpGroup>
    implements IpGroupPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link IpGroupUtil} to access the ip group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = IpGroupImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IpGroupModelImpl.ENTITY_CACHE_ENABLED,
            IpGroupModelImpl.FINDER_CACHE_ENABLED, IpGroupImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IpGroupModelImpl.ENTITY_CACHE_ENABLED,
            IpGroupModelImpl.FINDER_CACHE_ENABLED, IpGroupImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IpGroupModelImpl.ENTITY_CACHE_ENABLED,
            IpGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GETIPGROUP =
        new FinderPath(IpGroupModelImpl.ENTITY_CACHE_ENABLED,
            IpGroupModelImpl.FINDER_CACHE_ENABLED, IpGroupImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBygetIpGroup",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPGROUP =
        new FinderPath(IpGroupModelImpl.ENTITY_CACHE_ENABLED,
            IpGroupModelImpl.FINDER_CACHE_ENABLED, IpGroupImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBygetIpGroup",
            new String[] { Long.class.getName() },
            IpGroupModelImpl.GROUPID_COLUMN_BITMASK |
            IpGroupModelImpl.GROUPNAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_GETIPGROUP = new FinderPath(IpGroupModelImpl.ENTITY_CACHE_ENABLED,
            IpGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBygetIpGroup",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_GETIPGROUP_GROUPID_2 = "ipGroup.groupId = ?";
    private static final String _SQL_SELECT_IPGROUP = "SELECT ipGroup FROM IpGroup ipGroup";
    private static final String _SQL_SELECT_IPGROUP_WHERE = "SELECT ipGroup FROM IpGroup ipGroup WHERE ";
    private static final String _SQL_COUNT_IPGROUP = "SELECT COUNT(ipGroup) FROM IpGroup ipGroup";
    private static final String _SQL_COUNT_IPGROUP_WHERE = "SELECT COUNT(ipGroup) FROM IpGroup ipGroup WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "ipGroup.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IpGroup exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No IpGroup exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(IpGroupPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "groupId", "groupAdminId", "groupParentId", "groupName",
                "groupStatus", "groupEmail", "groupIsCore"
            });
    private static IpGroup _nullIpGroup = new IpGroupImpl() {
            /**
		 * 
		 */
		private static final long serialVersionUID = -1004812970906822710L;

			@Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<IpGroup> toCacheModel() {
                return _nullIpGroupCacheModel;
            }
        };

    private static CacheModel<IpGroup> _nullIpGroupCacheModel = new CacheModel<IpGroup>() {
            /**
		 * 
		 */
		private static final long serialVersionUID = -355965174150056762L;

			@Override
            public IpGroup toEntityModel() {
                return _nullIpGroup;
            }
        };

    public IpGroupPersistenceImpl() {
        setModelClass(IpGroup.class);
    }

    /**
     * Returns all the ip groups where groupId = &#63;.
     *
     * @param groupId the group ID
     * @return the matching ip groups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IpGroup> findBygetIpGroup(long groupId)
        throws SystemException {
        return findBygetIpGroup(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

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
    @Override
    public List<IpGroup> findBygetIpGroup(long groupId, int start, int end)
        throws SystemException {
        return findBygetIpGroup(groupId, start, end, null);
    }

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
    @Override
    public List<IpGroup> findBygetIpGroup(long groupId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPGROUP;
            finderArgs = new Object[] { groupId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GETIPGROUP;
            finderArgs = new Object[] { groupId, start, end, orderByComparator };
        }

        List<IpGroup> list = (List<IpGroup>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (IpGroup ipGroup : list) {
                if ((groupId != ipGroup.getGroupId())) {
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

            query.append(_SQL_SELECT_IPGROUP_WHERE);

            query.append(_FINDER_COLUMN_GETIPGROUP_GROUPID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(IpGroupModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(groupId);

                if (!pagination) {
                    list = (List<IpGroup>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IpGroup>(list);
                } else {
                    list = (List<IpGroup>) QueryUtil.list(q, getDialect(),
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
     * Returns the first ip group in the ordered set where groupId = &#63;.
     *
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ip group
     * @throws za.co.idea.ip.hook.NoSuchIpGroupException if a matching ip group could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpGroup findBygetIpGroup_First(long groupId,
        OrderByComparator orderByComparator)
        throws NoSuchIpGroupException, SystemException {
        IpGroup ipGroup = fetchBygetIpGroup_First(groupId, orderByComparator);

        if (ipGroup != null) {
            return ipGroup;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("groupId=");
        msg.append(groupId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIpGroupException(msg.toString());
    }

    /**
     * Returns the first ip group in the ordered set where groupId = &#63;.
     *
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching ip group, or <code>null</code> if a matching ip group could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpGroup fetchBygetIpGroup_First(long groupId,
        OrderByComparator orderByComparator) throws SystemException {
        List<IpGroup> list = findBygetIpGroup(groupId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last ip group in the ordered set where groupId = &#63;.
     *
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ip group
     * @throws za.co.idea.ip.hook.NoSuchIpGroupException if a matching ip group could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpGroup findBygetIpGroup_Last(long groupId,
        OrderByComparator orderByComparator)
        throws NoSuchIpGroupException, SystemException {
        IpGroup ipGroup = fetchBygetIpGroup_Last(groupId, orderByComparator);

        if (ipGroup != null) {
            return ipGroup;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("groupId=");
        msg.append(groupId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchIpGroupException(msg.toString());
    }

    /**
     * Returns the last ip group in the ordered set where groupId = &#63;.
     *
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching ip group, or <code>null</code> if a matching ip group could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpGroup fetchBygetIpGroup_Last(long groupId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countBygetIpGroup(groupId);

        if (count == 0) {
            return null;
        }

        List<IpGroup> list = findBygetIpGroup(groupId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Removes all the ip groups where groupId = &#63; from the database.
     *
     * @param groupId the group ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeBygetIpGroup(long groupId) throws SystemException {
        for (IpGroup ipGroup : findBygetIpGroup(groupId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(ipGroup);
        }
    }

    /**
     * Returns the number of ip groups where groupId = &#63;.
     *
     * @param groupId the group ID
     * @return the number of matching ip groups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countBygetIpGroup(long groupId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_GETIPGROUP;

        Object[] finderArgs = new Object[] { groupId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_IPGROUP_WHERE);

            query.append(_FINDER_COLUMN_GETIPGROUP_GROUPID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(groupId);

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
     * Caches the ip group in the entity cache if it is enabled.
     *
     * @param ipGroup the ip group
     */
    @Override
    public void cacheResult(IpGroup ipGroup) {
        EntityCacheUtil.putResult(IpGroupModelImpl.ENTITY_CACHE_ENABLED,
            IpGroupImpl.class, ipGroup.getPrimaryKey(), ipGroup);

        ipGroup.resetOriginalValues();
    }

    /**
     * Caches the ip groups in the entity cache if it is enabled.
     *
     * @param ipGroups the ip groups
     */
    @Override
    public void cacheResult(List<IpGroup> ipGroups) {
        for (IpGroup ipGroup : ipGroups) {
            if (EntityCacheUtil.getResult(
                        IpGroupModelImpl.ENTITY_CACHE_ENABLED,
                        IpGroupImpl.class, ipGroup.getPrimaryKey()) == null) {
                cacheResult(ipGroup);
            } else {
                ipGroup.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all ip groups.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(IpGroupImpl.class.getName());
        }

        EntityCacheUtil.clearCache(IpGroupImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the ip group.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(IpGroup ipGroup) {
        EntityCacheUtil.removeResult(IpGroupModelImpl.ENTITY_CACHE_ENABLED,
            IpGroupImpl.class, ipGroup.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<IpGroup> ipGroups) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (IpGroup ipGroup : ipGroups) {
            EntityCacheUtil.removeResult(IpGroupModelImpl.ENTITY_CACHE_ENABLED,
                IpGroupImpl.class, ipGroup.getPrimaryKey());
        }
    }

    /**
     * Creates a new ip group with the primary key. Does not add the ip group to the database.
     *
     * @param groupId the primary key for the new ip group
     * @return the new ip group
     */
    @Override
    public IpGroup create(long groupId) {
        IpGroup ipGroup = new IpGroupImpl();

        ipGroup.setNew(true);
        ipGroup.setPrimaryKey(groupId);

        return ipGroup;
    }

    /**
     * Removes the ip group with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param groupId the primary key of the ip group
     * @return the ip group that was removed
     * @throws za.co.idea.ip.hook.NoSuchIpGroupException if a ip group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpGroup remove(long groupId)
        throws NoSuchIpGroupException, SystemException {
        return remove((Serializable) groupId);
    }

    /**
     * Removes the ip group with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the ip group
     * @return the ip group that was removed
     * @throws za.co.idea.ip.hook.NoSuchIpGroupException if a ip group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpGroup remove(Serializable primaryKey)
        throws NoSuchIpGroupException, SystemException {
        Session session = null;

        try {
            session = openSession();

            IpGroup ipGroup = (IpGroup) session.get(IpGroupImpl.class,
                    primaryKey);

            if (ipGroup == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchIpGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(ipGroup);
        } catch (NoSuchIpGroupException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected IpGroup removeImpl(IpGroup ipGroup) throws SystemException {
        ipGroup = toUnwrappedModel(ipGroup);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(ipGroup)) {
                ipGroup = (IpGroup) session.get(IpGroupImpl.class,
                        ipGroup.getPrimaryKeyObj());
            }

            if (ipGroup != null) {
                session.delete(ipGroup);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (ipGroup != null) {
            clearCache(ipGroup);
        }

        return ipGroup;
    }

    @Override
    public IpGroup updateImpl(za.co.idea.ip.hook.model.IpGroup ipGroup)
        throws SystemException {
        ipGroup = toUnwrappedModel(ipGroup);

        boolean isNew = ipGroup.isNew();

        IpGroupModelImpl ipGroupModelImpl = (IpGroupModelImpl) ipGroup;

        Session session = null;

        try {
            session = openSession();

            if (ipGroup.isNew()) {
                session.save(ipGroup);

                ipGroup.setNew(false);
            } else {
                session.merge(ipGroup);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !IpGroupModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((ipGroupModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPGROUP.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        ipGroupModelImpl.getOriginalGroupId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GETIPGROUP,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPGROUP,
                    args);

                args = new Object[] { ipGroupModelImpl.getGroupId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GETIPGROUP,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GETIPGROUP,
                    args);
            }
        }

        EntityCacheUtil.putResult(IpGroupModelImpl.ENTITY_CACHE_ENABLED,
            IpGroupImpl.class, ipGroup.getPrimaryKey(), ipGroup);

        return ipGroup;
    }

    protected IpGroup toUnwrappedModel(IpGroup ipGroup) {
        if (ipGroup instanceof IpGroupImpl) {
            return ipGroup;
        }

        IpGroupImpl ipGroupImpl = new IpGroupImpl();

        ipGroupImpl.setNew(ipGroup.isNew());
        ipGroupImpl.setPrimaryKey(ipGroup.getPrimaryKey());

        ipGroupImpl.setGroupId(ipGroup.getGroupId());
        ipGroupImpl.setGroupAdminId(ipGroup.getGroupAdminId());
        ipGroupImpl.setGroupParentId(ipGroup.getGroupParentId());
        ipGroupImpl.setGroupName(ipGroup.getGroupName());
        ipGroupImpl.setGroupStatus(ipGroup.getGroupStatus());
        ipGroupImpl.setGroupEmail(ipGroup.getGroupEmail());
        ipGroupImpl.setGroupIsCore(ipGroup.getGroupIsCore());

        return ipGroupImpl;
    }

    /**
     * Returns the ip group with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the ip group
     * @return the ip group
     * @throws za.co.idea.ip.hook.NoSuchIpGroupException if a ip group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpGroup findByPrimaryKey(Serializable primaryKey)
        throws NoSuchIpGroupException, SystemException {
        IpGroup ipGroup = fetchByPrimaryKey(primaryKey);

        if (ipGroup == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchIpGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return ipGroup;
    }

    /**
     * Returns the ip group with the primary key or throws a {@link za.co.idea.ip.hook.NoSuchIpGroupException} if it could not be found.
     *
     * @param groupId the primary key of the ip group
     * @return the ip group
     * @throws za.co.idea.ip.hook.NoSuchIpGroupException if a ip group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpGroup findByPrimaryKey(long groupId)
        throws NoSuchIpGroupException, SystemException {
        return findByPrimaryKey((Serializable) groupId);
    }

    /**
     * Returns the ip group with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the ip group
     * @return the ip group, or <code>null</code> if a ip group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpGroup fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        IpGroup ipGroup = (IpGroup) EntityCacheUtil.getResult(IpGroupModelImpl.ENTITY_CACHE_ENABLED,
                IpGroupImpl.class, primaryKey);

        if (ipGroup == _nullIpGroup) {
            return null;
        }

        if (ipGroup == null) {
            Session session = null;

            try {
                session = openSession();

                ipGroup = (IpGroup) session.get(IpGroupImpl.class, primaryKey);

                if (ipGroup != null) {
                    cacheResult(ipGroup);
                } else {
                    EntityCacheUtil.putResult(IpGroupModelImpl.ENTITY_CACHE_ENABLED,
                        IpGroupImpl.class, primaryKey, _nullIpGroup);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(IpGroupModelImpl.ENTITY_CACHE_ENABLED,
                    IpGroupImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return ipGroup;
    }

    /**
     * Returns the ip group with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param groupId the primary key of the ip group
     * @return the ip group, or <code>null</code> if a ip group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public IpGroup fetchByPrimaryKey(long groupId) throws SystemException {
        return fetchByPrimaryKey((Serializable) groupId);
    }

    /**
     * Returns all the ip groups.
     *
     * @return the ip groups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<IpGroup> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

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
    @Override
    public List<IpGroup> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

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
    @Override
    public List<IpGroup> findAll(int start, int end,
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

        List<IpGroup> list = (List<IpGroup>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IPGROUP);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IPGROUP;

                if (pagination) {
                    sql = sql.concat(IpGroupModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<IpGroup>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<IpGroup>(list);
                } else {
                    list = (List<IpGroup>) QueryUtil.list(q, getDialect(),
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
     * Removes all the ip groups from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (IpGroup ipGroup : findAll()) {
            remove(ipGroup);
        }
    }

    /**
     * Returns the number of ip groups.
     *
     * @return the number of ip groups
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

                Query q = session.createQuery(_SQL_COUNT_IPGROUP);

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
     * Initializes the ip group persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.za.co.idea.ip.hook.model.IpGroup")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<IpGroup>> listenersList = new ArrayList<ModelListener<IpGroup>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<IpGroup>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(IpGroupImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
