package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpClaimStatus;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpClaimStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpClaimStatus
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpClaimStatusDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpClaimStatusDAO.class);
	// property constants
	public static final String CS_DESC = "csDesc";

	public void save(IpClaimStatus transientInstance) {
		log.debug("saving IpClaimStatus instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(transientInstance);
			transaction.commit();
			

			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			if (transaction.isActive())
				transaction.rollback();
			
				

			throw re;
		}
	}

	public void delete(IpClaimStatus persistentInstance) {
		log.debug("deleting IpClaimStatus instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(persistentInstance);
			transaction.commit();
			

			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			if (transaction.isActive())
				transaction.rollback();
			
				

			throw re;
		}
	}

	public IpClaimStatus findById(java.lang.Integer id) {
		log.debug("getting IpClaimStatus instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpClaimStatus instance = (IpClaimStatus) session.get("za.co.idea.ip.orm.bean.IpClaimStatus", id);
			transaction.commit();
			

			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			if (transaction.isActive())
				transaction.rollback();
			
				

			throw re;
		}
	}

	public List findByExample(IpClaimStatus instance) {
		log.debug("finding IpClaimStatus instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpClaimStatus").add(Example.create(instance)).list();
			transaction.commit();
			

			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			if (transaction.isActive())
				transaction.rollback();
			
				

			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpClaimStatus instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpClaimStatus as model where model." + propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			List results = queryObject.list();
			transaction.commit();
			

			return results;

		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			if (transaction.isActive())
				transaction.rollback();
			
				

			throw re;
		}
	}

	public List findByCsDesc(Object csDesc) {
		return findByProperty(CS_DESC, csDesc);
	}

	public List findAll() {
		log.debug("finding all IpClaimStatus instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpClaimStatus";
			Query queryObject = session.createQuery(queryString);
			List results = queryObject.list();
			transaction.commit();
			

			return results;

		} catch (RuntimeException re) {
			log.error("find all failed", re);
			if (transaction.isActive())
				transaction.rollback();
			
				

			throw re;
		}
	}

	public IpClaimStatus merge(IpClaimStatus detachedInstance) {
		log.debug("merging IpClaimStatus instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpClaimStatus result = (IpClaimStatus) session.merge(detachedInstance);
			log.debug("merge successful");
			transaction.commit();
			

			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			if (transaction.isActive())
				transaction.rollback();
			
				

			throw re;
		}
	}

	public void attachDirty(IpClaimStatus instance) {
		log.debug("attaching dirty IpClaimStatus instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(instance);
			transaction.commit();
			

			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			if (transaction.isActive())
				transaction.rollback();
			
				

			throw re;
		}
	}

	public List findNext(Integer curr) {
		log.debug("finding Next IpClaimStatus instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.getNamedQuery("getNextClmStatus");
			query.setLong("curr", curr);
			List ret = query.list();
			transaction.commit();
			

			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			if (transaction.isActive())
				transaction.rollback();
			
				

			throw re;
		}
	}

	public List findAllNonAlloc() {
		log.debug("finding all IpClaimStatus instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.getNamedQuery("getNonAllocClaimStatus");
			List ret = query.list();
			transaction.commit();
			

			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			if (transaction.isActive())
				transaction.rollback();
			
				

			throw re;
		}
	}

}