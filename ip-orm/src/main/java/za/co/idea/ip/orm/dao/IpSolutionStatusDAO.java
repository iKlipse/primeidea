package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpSolutionStatus;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpSolutionStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpSolutionStatus
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpSolutionStatusDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpSolutionStatusDAO.class);
	// property constants
	public static final String SS_DESC = "ssDesc";

	public void save(IpSolutionStatus transientInstance) {
		log.debug("saving IpSolutionStatus instance");
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

	public void delete(IpSolutionStatus persistentInstance) {
		log.debug("deleting IpSolutionStatus instance");
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

	public IpSolutionStatus findById(java.lang.Integer id) {
		log.debug("getting IpSolutionStatus instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpSolutionStatus instance = (IpSolutionStatus) session.get("za.co.idea.ip.orm.bean.IpSolutionStatus", id);
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

	public List findByExample(IpSolutionStatus instance) {
		log.debug("finding IpSolutionStatus instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpSolutionStatus").add(Example.create(instance)).list();
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
		log.debug("finding IpSolutionStatus instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpSolutionStatus as model where model." + propertyName + "= ?";
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

	public List findBySsDesc(Object ssDesc) {
		return findByProperty(SS_DESC, ssDesc);
	}

	public List findAll() {
		log.debug("finding all IpSolutionStatus instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpSolutionStatus";
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

	public IpSolutionStatus merge(IpSolutionStatus detachedInstance) {
		log.debug("merging IpSolutionStatus instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpSolutionStatus result = (IpSolutionStatus) session.merge(detachedInstance);
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

	public void attachDirty(IpSolutionStatus instance) {
		log.debug("attaching dirty IpSolutionStatus instance");
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

	public List findNext(Integer curr) {
		log.debug("finding Next IpSolutionStatus instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.getNamedQuery("getNextSolStatus");
			query.setLong("curr", curr);
			List ret = query.list();
			transaction.commit();
			session.close();
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}
}