package za.co.idea.ip.orm.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpSolutionStatusDAO {
	private static final Logger log = Logger.getLogger(IpSolutionStatusDAO.class);
	// property constants
	public static final String SS_DESC = "ssDesc";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(IpSolutionStatus transientInstance) {
		log.debug("saving IpSolutionStatus instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpSolutionStatus persistentInstance) {
		log.debug("deleting IpSolutionStatus instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpSolutionStatus findById(java.lang.Integer id) {
		log.debug("getting IpSolutionStatus instance with id: " + id);
		try {
			IpSolutionStatus instance = (IpSolutionStatus) getCurrentSession().get("za.co.idea.ip.orm.bean.IpSolutionStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpSolutionStatus> findByExample(IpSolutionStatus instance) {
		log.debug("finding IpSolutionStatus instance by example");
		try {
			List<IpSolutionStatus> results = (List<IpSolutionStatus>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpSolutionStatus").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpSolutionStatus instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpSolutionStatus as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpSolutionStatus> findBySsDesc(Object ssDesc) {
		return findByProperty(SS_DESC, ssDesc);
	}

	public List findAll() {
		log.debug("finding all IpSolutionStatus instances");
		try {
			String queryString = "from IpSolutionStatus";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpSolutionStatus merge(IpSolutionStatus detachedInstance) {
		log.debug("merging IpSolutionStatus instance");
		try {
			IpSolutionStatus result = (IpSolutionStatus) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpSolutionStatus instance) {
		log.debug("attaching dirty IpSolutionStatus instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpSolutionStatus instance) {
		log.debug("attaching clean IpSolutionStatus instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findNext(Integer curr) {
		log.debug("finding Next IpSolutionStatus instances");
		try {
			Query query = getCurrentSession().getNamedQuery("getNextSolStatus");
			query.setLong("curr", curr);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findAllNonAlloc() {
		log.debug("finding all IpSolutionStatus instances");
		try {
			Query query = getCurrentSession().getNamedQuery("getNonAllocSolStatus");
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public static IpSolutionStatusDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpSolutionStatusDAO) ctx.getBean("IpSolutionStatusDAO");
	}
}