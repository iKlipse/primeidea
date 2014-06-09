package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpFunction;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpFunction entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpFunction
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpFunctionDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpFunctionDAO.class);
	// property constants
	public static final String FUNC_NAME = "funcName";
	public static final String FUNC_IS_CORE = "funcIsCore";

	public void save(IpFunction transientInstance) {
		log.debug("saving IpFunction instance");
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

	public void delete(IpFunction persistentInstance) {
		log.debug("deleting IpFunction instance");
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

	public IpFunction findById(java.lang.Long id) {
		log.debug("getting IpFunction instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpFunction instance = (IpFunction) session.get("za.co.idea.ip.orm.bean.IpFunction", id);
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

	public List findByExample(IpFunction instance) {
		log.debug("finding IpFunction instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpFunction").add(Example.create(instance)).list();
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
		log.debug("finding IpFunction instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpFunction as model where model." + propertyName + "= ?";
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

	public List findByFuncName(Object funcName) {
		return findByProperty(FUNC_NAME, funcName);
	}

	public List findByFuncIsCore(Object funcIsCore) {
		return findByProperty(FUNC_IS_CORE, funcIsCore);
	}

	public List findAll() {
		log.debug("finding all IpFunction instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpFunction";
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

	public IpFunction merge(IpFunction detachedInstance) {
		log.debug("merging IpFunction instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpFunction result = (IpFunction) session.merge(detachedInstance);
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

	public void attachDirty(IpFunction instance) {
		log.debug("attaching dirty IpFunction instance");
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

	public List getFunctionByQuery() {
		log.debug("Fetching Functions by Query :: getAllFunction");
		Session session = getSession();
		try {
			Query query = session.getNamedQuery("getAllFunction");
			List ret = query.list();
			for (Object object : ret) {
				IpFunction function = (IpFunction) object;
				Hibernate.initialize(function.getIpFuncGroups());
			}
			session.close();
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public IpFunction getFunctionById(Long id) {
		log.debug("Fetching Functions by Query :: getFunctionById");
		Session session = getSession();
		try {
			Query query = session.getNamedQuery("getFunctionById");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpFunction function = (IpFunction) object;
				Hibernate.initialize(function.getIpFuncGroups());
			}
			session.close();
			return (IpFunction) ret.get(0);
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}