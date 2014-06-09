package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpChallengeCat;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpChallengeCat entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpChallengeCat
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpChallengeCatDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpChallengeCatDAO.class);
	// property constants
	public static final String CC_DESC = "ccDesc";

	public void save(IpChallengeCat transientInstance) {
		log.debug("saving IpChallengeCat instance");
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

	public void delete(IpChallengeCat persistentInstance) {
		log.debug("deleting IpChallengeCat instance");
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

	public IpChallengeCat findById(java.lang.Integer id) {
		log.debug("getting IpChallengeCat instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpChallengeCat instance = (IpChallengeCat) session.get("za.co.idea.ip.orm.bean.IpChallengeCat", id);
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

	public List findByExample(IpChallengeCat instance) {
		log.debug("finding IpChallengeCat instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpChallengeCat").add(Example.create(instance)).list();
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
		log.debug("finding IpChallengeCat instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpChallengeCat as model where model." + propertyName + "= ?";
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

	public List findByCcDesc(Object ccDesc) {
		return findByProperty(CC_DESC, ccDesc);
	}

	public List findAll() {
		log.debug("finding all IpChallengeCat instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpChallengeCat";
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

	public IpChallengeCat merge(IpChallengeCat detachedInstance) {
		log.debug("merging IpChallengeCat instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpChallengeCat result = (IpChallengeCat) session.merge(detachedInstance);
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

	public void attachDirty(IpChallengeCat instance) {
		log.debug("attaching dirty IpChallengeCat instance");
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