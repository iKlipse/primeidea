package za.co.idea.ip.hook.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import za.co.idea.ip.hook.model.IpSecqList;

import java.util.List;

/**
 * The persistence utility for the ip secq list service. This utility wraps
 * {@link IpSecqListPersistenceImpl} and provides direct access to the database
 * for CRUD operations. This utility should only be used by the service layer,
 * as it must operate within a transaction. Never access this utility in a JSP,
 * controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in
 * <code>portal.properties</code>
 * </p>
 *
 * @author VMPattamatta
 * @see IpSecqListPersistence
 * @see IpSecqListPersistenceImpl
 * @generated
 */
@SuppressWarnings("unchecked")
public class IpSecqListUtil {
	private static IpSecqListPersistence _persistence;

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
	public static void clearCache(IpSecqList ipSecqList) {
		getPersistence().clearCache(ipSecqList);
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
	public static List<IpSecqList> findWithDynamicQuery(DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery,
	 *      int, int)
	 */
	public static List<IpSecqList> findWithDynamicQuery(DynamicQuery dynamicQuery, int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery,
	 *      int, int, OrderByComparator)
	 */
	public static List<IpSecqList> findWithDynamicQuery(DynamicQuery dynamicQuery, int start, int end, OrderByComparator orderByComparator) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static IpSecqList update(IpSecqList ipSecqList) throws SystemException {
		return getPersistence().update(ipSecqList);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel,
	 *      ServiceContext)
	 */
	public static IpSecqList update(IpSecqList ipSecqList, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(ipSecqList, serviceContext);
	}

	/**
	 * Returns all the ip secq lists where islId = &#63;.
	 *
	 * @param islId
	 *            the isl ID
	 * @return the matching ip secq lists
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static java.util.List<za.co.idea.ip.hook.model.IpSecqList> findBygetIpSecqList(long islId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBygetIpSecqList(islId);
	}

	/**
	 * Returns a range of all the ip secq lists where islId = &#63;.
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
	 * {@link za.co.idea.ip.hook.model.impl.IpSecqListModelImpl}. If both
	 * <code>orderByComparator</code> and pagination are absent, for performance
	 * reasons, the query will not have an ORDER BY clause and the returned
	 * result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param islId
	 *            the isl ID
	 * @param start
	 *            the lower bound of the range of ip secq lists
	 * @param end
	 *            the upper bound of the range of ip secq lists (not inclusive)
	 * @return the range of matching ip secq lists
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static java.util.List<za.co.idea.ip.hook.model.IpSecqList> findBygetIpSecqList(long islId, int start, int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBygetIpSecqList(islId, start, end);
	}

	/**
	 * Returns an ordered range of all the ip secq lists where islId = &#63;.
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
	 * {@link za.co.idea.ip.hook.model.impl.IpSecqListModelImpl}. If both
	 * <code>orderByComparator</code> and pagination are absent, for performance
	 * reasons, the query will not have an ORDER BY clause and the returned
	 * result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param islId
	 *            the isl ID
	 * @param start
	 *            the lower bound of the range of ip secq lists
	 * @param end
	 *            the upper bound of the range of ip secq lists (not inclusive)
	 * @param orderByComparator
	 *            the comparator to order the results by (optionally
	 *            <code>null</code>)
	 * @return the ordered range of matching ip secq lists
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static java.util.List<za.co.idea.ip.hook.model.IpSecqList> findBygetIpSecqList(long islId, int start, int end, com.liferay.portal.kernel.util.OrderByComparator orderByComparator) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBygetIpSecqList(islId, start, end, orderByComparator);
	}

	/**
	 * Returns the first ip secq list in the ordered set where islId = &#63;.
	 *
	 * @param islId
	 *            the isl ID
	 * @param orderByComparator
	 *            the comparator to order the set by (optionally
	 *            <code>null</code>)
	 * @return the first matching ip secq list
	 * @throws za.co.idea.ip.hook.NoSuchIpSecqListException
	 *             if a matching ip secq list could not be found
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static za.co.idea.ip.hook.model.IpSecqList findBygetIpSecqList_First(long islId, com.liferay.portal.kernel.util.OrderByComparator orderByComparator) throws com.liferay.portal.kernel.exception.SystemException, za.co.idea.ip.hook.NoSuchIpSecqListException {
		return getPersistence().findBygetIpSecqList_First(islId, orderByComparator);
	}

	/**
	 * Returns the first ip secq list in the ordered set where islId = &#63;.
	 *
	 * @param islId
	 *            the isl ID
	 * @param orderByComparator
	 *            the comparator to order the set by (optionally
	 *            <code>null</code>)
	 * @return the first matching ip secq list, or <code>null</code> if a
	 *         matching ip secq list could not be found
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static za.co.idea.ip.hook.model.IpSecqList fetchBygetIpSecqList_First(long islId, com.liferay.portal.kernel.util.OrderByComparator orderByComparator) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchBygetIpSecqList_First(islId, orderByComparator);
	}

	/**
	 * Returns the last ip secq list in the ordered set where islId = &#63;.
	 *
	 * @param islId
	 *            the isl ID
	 * @param orderByComparator
	 *            the comparator to order the set by (optionally
	 *            <code>null</code>)
	 * @return the last matching ip secq list
	 * @throws za.co.idea.ip.hook.NoSuchIpSecqListException
	 *             if a matching ip secq list could not be found
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static za.co.idea.ip.hook.model.IpSecqList findBygetIpSecqList_Last(long islId, com.liferay.portal.kernel.util.OrderByComparator orderByComparator) throws com.liferay.portal.kernel.exception.SystemException, za.co.idea.ip.hook.NoSuchIpSecqListException {
		return getPersistence().findBygetIpSecqList_Last(islId, orderByComparator);
	}

	/**
	 * Returns the last ip secq list in the ordered set where islId = &#63;.
	 *
	 * @param islId
	 *            the isl ID
	 * @param orderByComparator
	 *            the comparator to order the set by (optionally
	 *            <code>null</code>)
	 * @return the last matching ip secq list, or <code>null</code> if a
	 *         matching ip secq list could not be found
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static za.co.idea.ip.hook.model.IpSecqList fetchBygetIpSecqList_Last(long islId, com.liferay.portal.kernel.util.OrderByComparator orderByComparator) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchBygetIpSecqList_Last(islId, orderByComparator);
	}

	/**
	 * Removes all the ip secq lists where islId = &#63; from the database.
	 *
	 * @param islId
	 *            the isl ID
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static void removeBygetIpSecqList(long islId) throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBygetIpSecqList(islId);
	}

	/**
	 * Returns the number of ip secq lists where islId = &#63;.
	 *
	 * @param islId
	 *            the isl ID
	 * @return the number of matching ip secq lists
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static int countBygetIpSecqList(long islId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBygetIpSecqList(islId);
	}

	/**
	 * Caches the ip secq list in the entity cache if it is enabled.
	 *
	 * @param ipSecqList
	 *            the ip secq list
	 */
	public static void cacheResult(za.co.idea.ip.hook.model.IpSecqList ipSecqList) {
		getPersistence().cacheResult(ipSecqList);
	}

	/**
	 * Caches the ip secq lists in the entity cache if it is enabled.
	 *
	 * @param ipSecqLists
	 *            the ip secq lists
	 */
	public static void cacheResult(java.util.List<za.co.idea.ip.hook.model.IpSecqList> ipSecqLists) {
		getPersistence().cacheResult(ipSecqLists);
	}

	/**
	 * Creates a new ip secq list with the primary key. Does not add the ip secq
	 * list to the database.
	 *
	 * @param islId
	 *            the primary key for the new ip secq list
	 * @return the new ip secq list
	 */
	public static za.co.idea.ip.hook.model.IpSecqList create(long islId) {
		return getPersistence().create(islId);
	}

	/**
	 * Removes the ip secq list with the primary key from the database. Also
	 * notifies the appropriate model listeners.
	 *
	 * @param islId
	 *            the primary key of the ip secq list
	 * @return the ip secq list that was removed
	 * @throws za.co.idea.ip.hook.NoSuchIpSecqListException
	 *             if a ip secq list with the primary key could not be found
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static za.co.idea.ip.hook.model.IpSecqList remove(long islId) throws com.liferay.portal.kernel.exception.SystemException, za.co.idea.ip.hook.NoSuchIpSecqListException {
		return getPersistence().remove(islId);
	}

	public static za.co.idea.ip.hook.model.IpSecqList updateImpl(za.co.idea.ip.hook.model.IpSecqList ipSecqList) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(ipSecqList);
	}

	/**
	 * Returns the ip secq list with the primary key or throws a
	 * {@link za.co.idea.ip.hook.NoSuchIpSecqListException} if it could not be
	 * found.
	 *
	 * @param islId
	 *            the primary key of the ip secq list
	 * @return the ip secq list
	 * @throws za.co.idea.ip.hook.NoSuchIpSecqListException
	 *             if a ip secq list with the primary key could not be found
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static za.co.idea.ip.hook.model.IpSecqList findByPrimaryKey(long islId) throws com.liferay.portal.kernel.exception.SystemException, za.co.idea.ip.hook.NoSuchIpSecqListException {
		return getPersistence().findByPrimaryKey(islId);
	}

	/**
	 * Returns the ip secq list with the primary key or returns
	 * <code>null</code> if it could not be found.
	 *
	 * @param islId
	 *            the primary key of the ip secq list
	 * @return the ip secq list, or <code>null</code> if a ip secq list with the
	 *         primary key could not be found
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static za.co.idea.ip.hook.model.IpSecqList fetchByPrimaryKey(long islId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(islId);
	}

	/**
	 * Returns all the ip secq lists.
	 *
	 * @return the ip secq lists
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static java.util.List<za.co.idea.ip.hook.model.IpSecqList> findAll() throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the ip secq lists.
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
	 * {@link za.co.idea.ip.hook.model.impl.IpSecqListModelImpl}. If both
	 * <code>orderByComparator</code> and pagination are absent, for performance
	 * reasons, the query will not have an ORDER BY clause and the returned
	 * result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start
	 *            the lower bound of the range of ip secq lists
	 * @param end
	 *            the upper bound of the range of ip secq lists (not inclusive)
	 * @return the range of ip secq lists
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static java.util.List<za.co.idea.ip.hook.model.IpSecqList> findAll(int start, int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the ip secq lists.
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
	 * {@link za.co.idea.ip.hook.model.impl.IpSecqListModelImpl}. If both
	 * <code>orderByComparator</code> and pagination are absent, for performance
	 * reasons, the query will not have an ORDER BY clause and the returned
	 * result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start
	 *            the lower bound of the range of ip secq lists
	 * @param end
	 *            the upper bound of the range of ip secq lists (not inclusive)
	 * @param orderByComparator
	 *            the comparator to order the results by (optionally
	 *            <code>null</code>)
	 * @return the ordered range of ip secq lists
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static java.util.List<za.co.idea.ip.hook.model.IpSecqList> findAll(int start, int end, com.liferay.portal.kernel.util.OrderByComparator orderByComparator) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Removes all the ip secq lists from the database.
	 *
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static void removeAll() throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of ip secq lists.
	 *
	 * @return the number of ip secq lists
	 * @throws SystemException
	 *             if a system exception occurred
	 */
	public static int countAll() throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static IpSecqListPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (IpSecqListPersistence) PortletBeanLocatorUtil.locate(za.co.idea.ip.hook.service.ClpSerializer.getServletContextName(), IpSecqListPersistence.class.getName());

			ReferenceRegistry.registerReference(IpSecqListUtil.class, "_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(IpSecqListPersistence persistence) {
	}
}
