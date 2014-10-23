package za.co.idea.ip.hook.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import za.co.idea.ip.hook.model.IpGroup;

import java.util.List;

/**
 * The persistence utility for the ip group service. This utility wraps
 * {@link IpGroupPersistenceImpl} and provides direct access to the database for
 * CRUD operations. This utility should only be used by the service layer, as it
 * must operate within a transaction. Never access this utility in a JSP,
 * controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in
 * <code>portal.properties</code>
 * </p>
 *
 * @author VMPattamatta
 * @see IpGroupPersistence
 * @see IpGroupPersistenceImpl
 * @generated
 */
@SuppressWarnings("unchecked")
public class IpGroupUtil {
	private static IpGroupPersistence _persistence;

	/*
	 * NOTE FOR DEVELOPERS:
	 * 
	 * Never modify this class directly. Modify <code>service.xml</code> and
	 * rerun ServiceBuilder to regenerate this class.
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
	public static void clearCache(IpGroup ipGroup) {
		getPersistence().clearCache(ipGroup);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<IpGroup> findWithDynamicQuery(DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery,
	 *      int, int)
	 */
	public static List<IpGroup> findWithDynamicQuery(DynamicQuery dynamicQuery, int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery,
	 *      int, int, OrderByComparator)
	 */
	public static List<IpGroup> findWithDynamicQuery(DynamicQuery dynamicQuery, int start, int end, OrderByComparator orderByComparator) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static IpGroup update(IpGroup ipGroup) throws SystemException {
		return getPersistence().update(ipGroup);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel,
	 *      ServiceContext)
	 */
	public static IpGroup update(IpGroup ipGroup, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(ipGroup, serviceContext);
	}

	/**
	 * Returns all the ip groups where groupId = &#63;.
	 *
	 * @param groupId
	 *            the group ID
	 * @return the matching ip groups
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static java.util.List<za.co.idea.ip.hook.model.IpGroup> findBygetIpGroup(long groupId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBygetIpGroup(groupId);
	}

	/**
	 * Returns a range of all the ip groups where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of
	 * <code>end - start</code> instances. <code>start</code> and
	 * <code>end</code> are not primary keys, they are indexes in the result
	 * set. Thus, <code>0</code> refers to the first result in the set. Setting
	 * both <code>start</code> and <code>end</code> to
	 * {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return
	 * the full result set. If <code>orderByComparator</code> is specified, then
	 * the query will include the given ORDER BY logic. If
	 * <code>orderByComparator</code> is absent and pagination is required (
	 * <code>start</code> and <code>end</code> are not
	 * {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the
	 * query will include the default ORDER BY logic from
	 * {@link za.co.idea.ip.hook.model.impl.IpGroupModelImpl}. If both
	 * <code>orderByComparator</code> and pagination are absent, for performance
	 * reasons, the query will not have an ORDER BY clause and the returned
	 * result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId
	 *            the group ID
	 * @param start
	 *            the lower bound of the range of ip groups
	 * @param end
	 *            the upper bound of the range of ip groups (not inclusive)
	 * @return the range of matching ip groups
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static java.util.List<za.co.idea.ip.hook.model.IpGroup> findBygetIpGroup(long groupId, int start, int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBygetIpGroup(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the ip groups where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of
	 * <code>end - start</code> instances. <code>start</code> and
	 * <code>end</code> are not primary keys, they are indexes in the result
	 * set. Thus, <code>0</code> refers to the first result in the set. Setting
	 * both <code>start</code> and <code>end</code> to
	 * {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return
	 * the full result set. If <code>orderByComparator</code> is specified, then
	 * the query will include the given ORDER BY logic. If
	 * <code>orderByComparator</code> is absent and pagination is required (
	 * <code>start</code> and <code>end</code> are not
	 * {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the
	 * query will include the default ORDER BY logic from
	 * {@link za.co.idea.ip.hook.model.impl.IpGroupModelImpl}. If both
	 * <code>orderByComparator</code> and pagination are absent, for performance
	 * reasons, the query will not have an ORDER BY clause and the returned
	 * result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId
	 *            the group ID
	 * @param start
	 *            the lower bound of the range of ip groups
	 * @param end
	 *            the upper bound of the range of ip groups (not inclusive)
	 * @param orderByComparator
	 *            the comparator to order the results by (optionally
	 *            <code>null</code>)
	 * @return the ordered range of matching ip groups
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static java.util.List<za.co.idea.ip.hook.model.IpGroup> findBygetIpGroup(long groupId, int start, int end, com.liferay.portal.kernel.util.OrderByComparator orderByComparator) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBygetIpGroup(groupId, start, end, orderByComparator);
	}

	/**
	 * Returns the first ip group in the ordered set where groupId = &#63;.
	 *
	 * @param groupId
	 *            the group ID
	 * @param orderByComparator
	 *            the comparator to order the set by (optionally
	 *            <code>null</code>)
	 * @return the first matching ip group
	 * @throws za.co.idea.ip.hook.NoSuchIpGroupException
	 *             if a matching ip group could not be found
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static za.co.idea.ip.hook.model.IpGroup findBygetIpGroup_First(long groupId, com.liferay.portal.kernel.util.OrderByComparator orderByComparator) throws com.liferay.portal.kernel.exception.SystemException, za.co.idea.ip.hook.NoSuchIpGroupException {
		return getPersistence().findBygetIpGroup_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first ip group in the ordered set where groupId = &#63;.
	 *
	 * @param groupId
	 *            the group ID
	 * @param orderByComparator
	 *            the comparator to order the set by (optionally
	 *            <code>null</code>)
	 * @return the first matching ip group, or <code>null</code> if a matching
	 *         ip group could not be found
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static za.co.idea.ip.hook.model.IpGroup fetchBygetIpGroup_First(long groupId, com.liferay.portal.kernel.util.OrderByComparator orderByComparator) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchBygetIpGroup_First(groupId, orderByComparator);
	}

	/**
	 * Returns the last ip group in the ordered set where groupId = &#63;.
	 *
	 * @param groupId
	 *            the group ID
	 * @param orderByComparator
	 *            the comparator to order the set by (optionally
	 *            <code>null</code>)
	 * @return the last matching ip group
	 * @throws za.co.idea.ip.hook.NoSuchIpGroupException
	 *             if a matching ip group could not be found
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static za.co.idea.ip.hook.model.IpGroup findBygetIpGroup_Last(long groupId, com.liferay.portal.kernel.util.OrderByComparator orderByComparator) throws com.liferay.portal.kernel.exception.SystemException, za.co.idea.ip.hook.NoSuchIpGroupException {
		return getPersistence().findBygetIpGroup_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last ip group in the ordered set where groupId = &#63;.
	 *
	 * @param groupId
	 *            the group ID
	 * @param orderByComparator
	 *            the comparator to order the set by (optionally
	 *            <code>null</code>)
	 * @return the last matching ip group, or <code>null</code> if a matching ip
	 *         group could not be found
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static za.co.idea.ip.hook.model.IpGroup fetchBygetIpGroup_Last(long groupId, com.liferay.portal.kernel.util.OrderByComparator orderByComparator) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchBygetIpGroup_Last(groupId, orderByComparator);
	}

	/**
	 * Removes all the ip groups where groupId = &#63; from the database.
	 *
	 * @param groupId
	 *            the group ID
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static void removeBygetIpGroup(long groupId) throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBygetIpGroup(groupId);
	}

	/**
	 * Returns the number of ip groups where groupId = &#63;.
	 *
	 * @param groupId
	 *            the group ID
	 * @return the number of matching ip groups
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static int countBygetIpGroup(long groupId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBygetIpGroup(groupId);
	}

	/**
	 * Caches the ip group in the entity cache if it is enabled.
	 *
	 * @param ipGroup
	 *            the ip group
	 */
	public static void cacheResult(za.co.idea.ip.hook.model.IpGroup ipGroup) {
		getPersistence().cacheResult(ipGroup);
	}

	/**
	 * Caches the ip groups in the entity cache if it is enabled.
	 *
	 * @param ipGroups
	 *            the ip groups
	 */
	public static void cacheResult(java.util.List<za.co.idea.ip.hook.model.IpGroup> ipGroups) {
		getPersistence().cacheResult(ipGroups);
	}

	/**
	 * Creates a new ip group with the primary key. Does not add the ip group to
	 * the database.
	 *
	 * @param groupId
	 *            the primary key for the new ip group
	 * @return the new ip group
	 */
	public static za.co.idea.ip.hook.model.IpGroup create(long groupId) {
		return getPersistence().create(groupId);
	}

	/**
	 * Removes the ip group with the primary key from the database. Also
	 * notifies the appropriate model listeners.
	 *
	 * @param groupId
	 *            the primary key of the ip group
	 * @return the ip group that was removed
	 * @throws za.co.idea.ip.hook.NoSuchIpGroupException
	 *             if a ip group with the primary key could not be found
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static za.co.idea.ip.hook.model.IpGroup remove(long groupId) throws com.liferay.portal.kernel.exception.SystemException, za.co.idea.ip.hook.NoSuchIpGroupException {
		return getPersistence().remove(groupId);
	}

	public static za.co.idea.ip.hook.model.IpGroup updateImpl(za.co.idea.ip.hook.model.IpGroup ipGroup) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(ipGroup);
	}

	/**
	 * Returns the ip group with the primary key or throws a
	 * {@link za.co.idea.ip.hook.NoSuchIpGroupException} if it could not be
	 * found.
	 *
	 * @param groupId
	 *            the primary key of the ip group
	 * @return the ip group
	 * @throws za.co.idea.ip.hook.NoSuchIpGroupException
	 *             if a ip group with the primary key could not be found
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static za.co.idea.ip.hook.model.IpGroup findByPrimaryKey(long groupId) throws com.liferay.portal.kernel.exception.SystemException, za.co.idea.ip.hook.NoSuchIpGroupException {
		return getPersistence().findByPrimaryKey(groupId);
	}

	/**
	 * Returns the ip group with the primary key or returns <code>null</code> if
	 * it could not be found.
	 *
	 * @param groupId
	 *            the primary key of the ip group
	 * @return the ip group, or <code>null</code> if a ip group with the primary
	 *         key could not be found
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static za.co.idea.ip.hook.model.IpGroup fetchByPrimaryKey(long groupId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(groupId);
	}

	/**
	 * Returns all the ip groups.
	 *
	 * @return the ip groups
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static java.util.List<za.co.idea.ip.hook.model.IpGroup> findAll() throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the ip groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of
	 * <code>end - start</code> instances. <code>start</code> and
	 * <code>end</code> are not primary keys, they are indexes in the result
	 * set. Thus, <code>0</code> refers to the first result in the set. Setting
	 * both <code>start</code> and <code>end</code> to
	 * {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return
	 * the full result set. If <code>orderByComparator</code> is specified, then
	 * the query will include the given ORDER BY logic. If
	 * <code>orderByComparator</code> is absent and pagination is required (
	 * <code>start</code> and <code>end</code> are not
	 * {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the
	 * query will include the default ORDER BY logic from
	 * {@link za.co.idea.ip.hook.model.impl.IpGroupModelImpl}. If both
	 * <code>orderByComparator</code> and pagination are absent, for performance
	 * reasons, the query will not have an ORDER BY clause and the returned
	 * result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start
	 *            the lower bound of the range of ip groups
	 * @param end
	 *            the upper bound of the range of ip groups (not inclusive)
	 * @return the range of ip groups
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static java.util.List<za.co.idea.ip.hook.model.IpGroup> findAll(int start, int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the ip groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of
	 * <code>end - start</code> instances. <code>start</code> and
	 * <code>end</code> are not primary keys, they are indexes in the result
	 * set. Thus, <code>0</code> refers to the first result in the set. Setting
	 * both <code>start</code> and <code>end</code> to
	 * {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return
	 * the full result set. If <code>orderByComparator</code> is specified, then
	 * the query will include the given ORDER BY logic. If
	 * <code>orderByComparator</code> is absent and pagination is required (
	 * <code>start</code> and <code>end</code> are not
	 * {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the
	 * query will include the default ORDER BY logic from
	 * {@link za.co.idea.ip.hook.model.impl.IpGroupModelImpl}. If both
	 * <code>orderByComparator</code> and pagination are absent, for performance
	 * reasons, the query will not have an ORDER BY clause and the returned
	 * result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start
	 *            the lower bound of the range of ip groups
	 * @param end
	 *            the upper bound of the range of ip groups (not inclusive)
	 * @param orderByComparator
	 *            the comparator to order the results by (optionally
	 *            <code>null</code>)
	 * @return the ordered range of ip groups
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static java.util.List<za.co.idea.ip.hook.model.IpGroup> findAll(int start, int end, com.liferay.portal.kernel.util.OrderByComparator orderByComparator) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Removes all the ip groups from the database.
	 *
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static void removeAll() throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of ip groups.
	 *
	 * @return the number of ip groups
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static int countAll() throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static IpGroupPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (IpGroupPersistence) PortletBeanLocatorUtil.locate(za.co.idea.ip.hook.service.ClpSerializer.getServletContextName(), IpGroupPersistence.class.getName());

			ReferenceRegistry.registerReference(IpGroupUtil.class, "_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(IpGroupPersistence persistence) {
	}
}
