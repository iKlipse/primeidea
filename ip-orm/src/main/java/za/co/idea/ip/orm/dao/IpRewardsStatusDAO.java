package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpRewardsStatus;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpRewardsStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpRewardsStatus
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpRewardsStatusDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpRewardsStatusDAO.class);
	// property constants
	public static final String RS_DESC = "rsDesc";

	public void save(IpRewardsStatus transientInstance) {
		log.debug("saving IpRewardsStatus instance");
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

	public void delete(IpRewardsStatus persistentInstance) {
		log.debug("deleting IpRewardsStatus instance");
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

	public IpRewardsStatus findById(java.lang.Integer id) {
		log.debug("getting IpRewardsStatus instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpRewardsStatus instance = (IpRewardsStatus) session.get("za.co.idea.ip.orm.bean.IpRewardsStatus", id);
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

	public List findByExample(IpRewardsStatus instance) {
		log.debug("finding IpRewardsStatus instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpRewardsStatus").add(Example.create(instance)).list();
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
		log.debug("finding IpRewardsStatus instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpRewardsStatus as model where model." + propertyName + "= ?";
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

	public List findByRsDesc(Object rsDesc) {
		return findByProperty(RS_DESC, rsDesc);
	}

	public List findAll() {
		log.debug("finding all IpRewardsStatus instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpRewardsStatus";
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

	public IpRewardsStatus merge(IpRewardsStatus detachedInstance) {
		log.debug("merging IpRewardsStatus instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpRewardsStatus result = (IpRewardsStatus) session.merge(detachedInstance);
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

	public void attachDirty(IpRewardsStatus instance) {
		log.debug("attaching dirty IpRewardsStatus instance");
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
		log.debug("finding Next IpRewardsStatus instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.getNamedQuery("getNextRwStatus");
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