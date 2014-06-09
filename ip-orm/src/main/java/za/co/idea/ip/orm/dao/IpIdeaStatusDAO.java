package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpIdeaStatus;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpIdeaStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpIdeaStatus
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpIdeaStatusDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpIdeaStatusDAO.class);
	// property constants
	public static final String IS_DESC = "isDesc";

	public void save(IpIdeaStatus transientInstance) {
		log.debug("saving IpIdeaStatus instance");
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

	public void delete(IpIdeaStatus persistentInstance) {
		log.debug("deleting IpIdeaStatus instance");
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

	public IpIdeaStatus findById(java.lang.Integer id) {
		log.debug("getting IpIdeaStatus instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpIdeaStatus instance = (IpIdeaStatus) session.get("za.co.idea.ip.orm.bean.IpIdeaStatus", id);
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

	public List findByExample(IpIdeaStatus instance) {
		log.debug("finding IpIdeaStatus instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpIdeaStatus").add(Example.create(instance)).list();
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
		log.debug("finding IpIdeaStatus instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpIdeaStatus as model where model." + propertyName + "= ?";
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

	public List findByIsDesc(Object isDesc) {
		return findByProperty(IS_DESC, isDesc);
	}

	public List findAll() {
		log.debug("finding all IpIdeaStatus instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpIdeaStatus";
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

	public IpIdeaStatus merge(IpIdeaStatus detachedInstance) {
		log.debug("merging IpIdeaStatus instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpIdeaStatus result = (IpIdeaStatus) session.merge(detachedInstance);
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

	public void attachDirty(IpIdeaStatus instance) {
		log.debug("attaching dirty IpIdeaStatus instance");
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
		log.debug("finding Next IpIdeaStatus instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.getNamedQuery("getNextIdeaStatus");
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