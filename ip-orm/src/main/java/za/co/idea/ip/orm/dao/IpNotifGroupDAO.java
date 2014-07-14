package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpNotifGroup;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpNotifGroup entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpNotifGroup
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpNotifGroupDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpNotifGroupDAO.class);
	// property constants
	public static final String ING_NOTIF_ID = "ingNotifId";
	public static final String ING_GRP_ID = "ingGrpId";

	public void save(IpNotifGroup transientInstance) {
		log.debug("saving IpNotifGroup instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(transientInstance);
			transaction.commit();session.close();
			
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			transaction.rollback();session.close();
			
			throw re;
		}
	}

	public void delete(IpNotifGroup persistentInstance) {
		log.debug("deleting IpNotifGroup instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(persistentInstance);
			transaction.commit();session.close();
			
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			transaction.rollback();session.close();
			
			throw re;
		}
	}

	public IpNotifGroup findById(java.lang.Long id) {
		log.debug("getting IpNotifGroup instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpNotifGroup instance = (IpNotifGroup) session.get("za.co.idea.ip.orm.bean.IpNotifGroup", id);
			transaction.commit();session.close();
			
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			transaction.rollback();session.close();
			
			throw re;
		}
	}

	public List findByExample(IpNotifGroup instance) {
		log.debug("finding IpNotifGroup instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpNotifGroup").add(Example.create(instance)).list();
			transaction.commit();session.close();
			
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			transaction.rollback();session.close();
			
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpNotifGroup instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpNotifGroup as model where model." + propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			List results = queryObject.list();
			transaction.commit();session.close();
			
			return results;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			transaction.rollback();session.close();
			
			throw re;
		}
	}

	public List findByIngNotifId(Object ingNotifId) {
		return findByProperty(ING_NOTIF_ID, ingNotifId);
	}

	public List findByIngGrpId(Object ingGrpId) {
		return findByProperty(ING_GRP_ID, ingGrpId);
	}

	public List findAll() {
		log.debug("finding all IpNotifGroup instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpNotifGroup";
			Query queryObject = session.createQuery(queryString);
			List results = queryObject.list();
			transaction.commit();session.close();
			
			return results;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			transaction.rollback();session.close();
			
			throw re;
		}

	}

	public IpNotifGroup merge(IpNotifGroup detachedInstance) {
		log.debug("merging IpNotifGroup instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpNotifGroup result = (IpNotifGroup) session.merge(detachedInstance);
			transaction.commit();session.close();
			
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			transaction.rollback();session.close();
			
			throw re;
		}
	}

	public void attachDirty(IpNotifGroup instance) {
		log.debug("attaching dirty IpNotifGroup instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(instance);
			transaction.commit();session.close();
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			transaction.rollback();session.close();
			
			throw re;
		}
	}

	public void deleteByNotifId(String id) {
		log.debug("Deleting Notification Groups By Notif Id : " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.getNamedQuery("deleteNGByNotifId");
			query.setString("id", id);
			query.executeUpdate();
			transaction.commit();session.close();
			
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			transaction.rollback();session.close();
			throw re;
		}
	}

	public List fetchByNotifId(String id) {
		log.debug("Fetching Notification Group Users By Notif Id : " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.getNamedQuery("fetchNGByNotifId");
			query.setString("id", id);
			List ret = query.list();
			transaction.commit();session.close();
			
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			transaction.rollback();session.close();
			throw re;
		}
	}

}