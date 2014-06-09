package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpStateTran;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpStateTran entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpStateTran
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpStateTranDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpStateTranDAO.class);
	// property constants
	public static final String TRAN_ENTITY = "tranEntity";
	public static final String TRAN_CURR_STATE = "tranCurrState";
	public static final String TRAN_NEXT_STATE = "tranNextState";

	public void save(IpStateTran transientInstance) {
		log.debug("saving IpStateTran instance");
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

	public void delete(IpStateTran persistentInstance) {
		log.debug("deleting IpStateTran instance");
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

	public IpStateTran findById(java.lang.Integer id) {
		log.debug("getting IpStateTran instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpStateTran instance = (IpStateTran) session.get("za.co.idea.ip.orm.bean.IpStateTran", id);
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

	public List findByExample(IpStateTran instance) {
		log.debug("finding IpStateTran instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpStateTran").add(Example.create(instance)).list();
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
		log.debug("finding IpStateTran instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpStateTran as model where model." + propertyName + "= ?";
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

	public List findByTranEntity(Object tranEntity) {
		return findByProperty(TRAN_ENTITY, tranEntity);
	}

	public List findByTranCurrState(Object tranCurrState) {
		return findByProperty(TRAN_CURR_STATE, tranCurrState);
	}

	public List findByTranNextState(Object tranNextState) {
		return findByProperty(TRAN_NEXT_STATE, tranNextState);
	}

	public List findAll() {
		log.debug("finding all IpStateTran instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpStateTran";
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

	public IpStateTran merge(IpStateTran detachedInstance) {
		log.debug("merging IpStateTran instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpStateTran result = (IpStateTran) session.merge(detachedInstance);
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

	public void attachDirty(IpStateTran instance) {
		log.debug("attaching dirty IpStateTran instance");
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