package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpIdeaCat;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpIdeaCat entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpIdeaCat
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpIdeaCatDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpIdeaCatDAO.class);
	// property constants
	public static final String IC_DESC = "icDesc";

	public void save(IpIdeaCat transientInstance) {
		log.debug("saving IpIdeaCat instance");
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

	public void delete(IpIdeaCat persistentInstance) {
		log.debug("deleting IpIdeaCat instance");
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

	public IpIdeaCat findById(java.lang.Integer id) {
		log.debug("getting IpIdeaCat instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpIdeaCat instance = (IpIdeaCat) session.get("za.co.idea.ip.orm.bean.IpIdeaCat", id);
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

	public List findByExample(IpIdeaCat instance) {
		log.debug("finding IpIdeaCat instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpIdeaCat").add(Example.create(instance)).list();
			transaction.commit();
			session.close();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpIdeaCat instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpIdeaCat as model where model." + propertyName + "= ?";
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

	public List findByIcDesc(Object icDesc) {
		return findByProperty(IC_DESC, icDesc);
	}

	public List findAll() {
		log.debug("finding all IpIdeaCat instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpIdeaCat";
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

	public IpIdeaCat merge(IpIdeaCat detachedInstance) {
		log.debug("merging IpIdeaCat instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpIdeaCat result = (IpIdeaCat) session.merge(detachedInstance);
			transaction.commit();
			session.close();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public void attachDirty(IpIdeaCat instance) {
		log.debug("attaching dirty IpIdeaCat instance");
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
}