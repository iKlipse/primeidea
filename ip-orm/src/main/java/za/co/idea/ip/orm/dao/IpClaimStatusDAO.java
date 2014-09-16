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
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpClaimStatusDAO {
	private static final Logger log = Logger.getLogger(IpClaimStatusDAO.class);
	// property constants
	public static final String CS_DESC = "csDesc";

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

	public void save(IpClaimStatus transientInstance) {
		log.debug("saving IpClaimStatus instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpClaimStatus persistentInstance) {
		log.debug("deleting IpClaimStatus instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpClaimStatus findById(java.lang.Integer id) {
		log.debug("getting IpClaimStatus instance with id: " + id);
		try {
			IpClaimStatus instance = (IpClaimStatus) getCurrentSession().get("za.co.idea.ip.orm.bean.IpClaimStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpClaimStatus> findByExample(IpClaimStatus instance) {
		log.debug("finding IpClaimStatus instance by example");
		try {
			List<IpClaimStatus> results = (List<IpClaimStatus>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpClaimStatus").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpClaimStatus instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpClaimStatus as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpClaimStatus> findByCsDesc(Object csDesc) {
		return findByProperty(CS_DESC, csDesc);
	}

	public List findAll() {
		log.debug("finding all IpClaimStatus instances");
		try {
			String queryString = "from IpClaimStatus";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpClaimStatus merge(IpClaimStatus detachedInstance) {
		log.debug("merging IpClaimStatus instance");
		try {
			IpClaimStatus result = (IpClaimStatus) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpClaimStatus instance) {
		log.debug("attaching dirty IpClaimStatus instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpClaimStatus instance) {
		log.debug("attaching clean IpClaimStatus instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findNext(Integer curr) {
		log.debug("finding Next IpClaimStatus instances");
		try {
			Query query = getCurrentSession().getNamedQuery("getNextClmStatus");
			query.setLong("curr", curr);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findAllNonAlloc() {
		log.debug("finding all IpClaimStatus instances");
		try {
			Query query = getCurrentSession().getNamedQuery("getNonAllocClaimStatus");
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public static IpClaimStatusDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpClaimStatusDAO) ctx.getBean("IpClaimStatusDAO");
	}
}