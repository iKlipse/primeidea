package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpClaim;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpClaim entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpClaim
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpClaimDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpClaimDAO.class);
	// property constants
	public static final String CLAIM_DESC = "claimDesc";

	public void save(IpClaim transientInstance) {
		log.debug("saving IpClaim instance");
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

	public void delete(IpClaim persistentInstance) {
		log.debug("deleting IpClaim instance");
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

	public IpClaim findById(java.lang.Long id) {
		log.debug("getting IpClaim instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpClaim instance = (IpClaim) session.get("za.co.idea.ip.orm.bean.IpClaim", id);
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

	public List findByExample(IpClaim instance) {
		log.debug("finding IpClaim instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpClaim").add(Example.create(instance)).list();
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
		log.debug("finding IpClaim instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpClaim as model where model." + propertyName + "= ?";
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

	public List findByClaimDesc(Object claimDesc) {
		return findByProperty(CLAIM_DESC, claimDesc);
	}

	public List findAll() {
		log.debug("finding all IpClaim instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpClaim";
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

	public IpClaim merge(IpClaim detachedInstance) {
		log.debug("merging IpClaim instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpClaim result = (IpClaim) session.merge(detachedInstance);
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

	public void attachDirty(IpClaim instance) {
		log.debug("attaching dirty IpClaim instance");
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

	public List findByStatusId(Integer id) {
		log.debug("Fetching Challenge by Query :: getClaimByStatus");
		Session session = getSession();
		try {
			Query query = session.getNamedQuery("getClaimByStatus");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpClaim clm = (IpClaim) object;
				Hibernate.initialize(clm.getIpClaimStatus());
				Hibernate.initialize(clm.getIpRewards());
				Hibernate.initialize(clm.getIpUser());
			}
			session.close();
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByUserId(Long id) {
		log.debug("Fetching Challenge by Query :: getClaimByUser");
		Session session = getSession();
		try {
			Query query = session.getNamedQuery("getClaimByUser");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpClaim clm = (IpClaim) object;
				Hibernate.initialize(clm.getIpClaimStatus());
				Hibernate.initialize(clm.getIpRewards());
				Hibernate.initialize(clm.getIpUser());
			}
			session.close();
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}