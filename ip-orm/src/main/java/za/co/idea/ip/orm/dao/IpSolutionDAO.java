package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpSolution;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpSolution entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpSolution
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpSolutionDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpSolutionDAO.class);
	// property constants
	public static final String SOL_TITLE = "solTitle";
	public static final String SOL_DESC = "solDesc";
	public static final String SOL_TAGS = "solTags";

	public void save(IpSolution transientInstance) {
		log.debug("saving IpSolution instance");
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

	public void delete(IpSolution persistentInstance) {
		log.debug("deleting IpSolution instance");
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

	public IpSolution findById(java.lang.Long id) {
		log.debug("getting IpSolution instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpSolution instance = (IpSolution) session.get("za.co.idea.ip.orm.bean.IpSolution", id);
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

	public List findByExample(IpSolution instance) {
		log.debug("finding IpSolution instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpSolution").add(Example.create(instance)).list();
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
		log.debug("finding IpSolution instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpSolution as model where model." + propertyName + "= ?";
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

	public List findBySolTitle(Object solTitle) {
		return findByProperty(SOL_TITLE, solTitle);
	}

	public List findBySolDesc(Object solDesc) {
		return findByProperty(SOL_DESC, solDesc);
	}

	public List findBySolTags(Object solTags) {
		return findByProperty(SOL_TAGS, solTags);
	}

	public List findAll() {
		log.debug("finding all IpSolution instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpSolution";
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

	public IpSolution merge(IpSolution detachedInstance) {
		log.debug("merging IpSolution instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpSolution result = (IpSolution) session.merge(detachedInstance);
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

	public void attachDirty(IpSolution instance) {
		log.debug("attaching dirty IpSolution instance");
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

	public List findByUserId(Long id) {
		log.debug("Fetching Challenge by Query :: getSolutionByUser");
		Session session = getSession();
		try {
			Query query = session.getNamedQuery("getSolutionByUser");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpSolution sol = (IpSolution) object;
				Hibernate.initialize(sol.getIpChallenge());
				Hibernate.initialize(sol.getIpSolutionCat());
				Hibernate.initialize(sol.getIpSolutionStatus());
				Hibernate.initialize(sol.getIpUser());
			}
			session.close();
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByStatusId(Integer id) {
		log.debug("Fetching Challenge by Query :: getSolutionByStatus");
		Session session = getSession();
		try {
			Query query = session.getNamedQuery("getSolutionByUser");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpSolution sol = (IpSolution) object;
				Hibernate.initialize(sol.getIpChallenge());
				Hibernate.initialize(sol.getIpSolutionCat());
				Hibernate.initialize(sol.getIpSolutionStatus());
				Hibernate.initialize(sol.getIpUser());
			}
			session.close();
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}