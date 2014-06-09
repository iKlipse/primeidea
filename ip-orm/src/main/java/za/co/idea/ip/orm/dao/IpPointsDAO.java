package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpPoints;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpPoints entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpPoints
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpPointsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpPointsDAO.class);
	// property constants
	public static final String POINT_VALUE = "pointValue";

	public void save(IpPoints transientInstance) {
		log.debug("saving IpPoints instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(transientInstance);
			transaction.commit();
			session.close();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public void delete(IpPoints persistentInstance) {
		log.debug("deleting IpPoints instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(persistentInstance);
			transaction.commit();
			session.close();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public IpPoints findById(java.lang.Long id) {
		log.debug("getting IpPoints instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpPoints instance = (IpPoints) session.get("za.co.idea.ip.orm.bean.IpPoints", id);
			transaction.commit();
			session.close();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public List findByExample(IpPoints instance) {
		log.debug("finding IpPoints instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpPoints").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			transaction.commit();
			session.close();
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpPoints instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpPoints as model where model." + propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			List results = queryObject.list();
			transaction.commit();
			session.close();
			return results;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public List findByPointValue(Object pointValue) {
		return findByProperty(POINT_VALUE, pointValue);
	}

	public List findAll() {
		log.debug("finding all IpPoints instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpPoints";
			Query queryObject = session.createQuery(queryString);
			List results = queryObject.list();
			transaction.commit();
			session.close();
			return results;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public IpPoints merge(IpPoints detachedInstance) {
		log.debug("merging IpPoints instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpPoints result = (IpPoints) session.merge(detachedInstance);
			log.debug("merge successful");
			transaction.commit();
			session.close();
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public void attachDirty(IpPoints instance) {
		log.debug("attaching dirty IpPoints instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(instance);
			transaction.commit();
			session.close();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public List findByUser(Long id) {
		log.debug("Fetching Points by Query :: getPointsByUser");
		Session session = getSession();
		try {
			Query query = session.getNamedQuery("getPointsByUser");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpPoints ip = (IpPoints) object;
				Hibernate.initialize(ip.getIpAllocation());
				Hibernate.initialize(ip.getIpUser());
			}
			session.close();
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}