package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpConfig;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpConfig entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpConfig
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpConfigDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpConfigDAO.class);
	// property constants
	public static final String CONFIG_KEY = "configKey";
	public static final String CONFIG_VALUE = "configValue";
	public static final String CONFIG_ENV = "configEnv";
	public static final String CREATED_BY = "createdBy";

	public void save(IpConfig transientInstance) {
		log.debug("saving IpConfig instance");
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

	public void delete(IpConfig persistentInstance) {
		log.debug("deleting IpConfig instance");
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

	public IpConfig findById(java.lang.Integer id) {
		log.debug("getting IpConfig instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpConfig instance = (IpConfig) session.get("za.co.idea.ip.orm.bean.IpConfig", id);
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

	public List findByExample(IpConfig instance) {
		log.debug("finding IpConfig instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpConfig").add(Example.create(instance)).list();
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
		log.debug("finding IpConfig instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpConfig as model where model." + propertyName + "= ?";
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

	public List findByConfigKey(Object configKey) {
		return findByProperty(CONFIG_KEY, configKey);
	}

	public List findByConfigValue(Object configValue) {
		return findByProperty(CONFIG_VALUE, configValue);
	}

	public List findByConfigEnv(Object configEnv) {
		return findByProperty(CONFIG_ENV, configEnv);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all IpConfig instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpConfig";
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

	public IpConfig merge(IpConfig detachedInstance) {
		log.debug("merging IpConfig instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpConfig result = (IpConfig) session.merge(detachedInstance);
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

	public void attachDirty(IpConfig instance) {
		log.debug("attaching dirty IpConfig instance");
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