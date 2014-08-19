package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

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
@SuppressWarnings({ "rawtypes" })
public class IpClaimStatusDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(IpClaimStatusDAO.class);
	// property constants
	public static final String CS_DESC = "csDesc";

	protected void initDao() {
		// do nothing
	}

	public void save(IpClaimStatus transientInstance) {
		log.debug("saving IpClaimStatus instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpClaimStatus persistentInstance) {
		log.debug("deleting IpClaimStatus instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpClaimStatus findById(java.lang.Integer id) {
		log.debug("getting IpClaimStatus instance with id: " + id);
		try {
			IpClaimStatus instance = (IpClaimStatus) getHibernateTemplate().get("za.co.idea.ip.orm.bean.IpClaimStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IpClaimStatus instance) {
		log.debug("finding IpClaimStatus instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
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
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCsDesc(Object csDesc) {
		return findByProperty(CS_DESC, csDesc);
	}

	public List findAll() {
		log.debug("finding all IpClaimStatus instances");
		try {
			String queryString = "from IpClaimStatus";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpClaimStatus merge(IpClaimStatus detachedInstance) {
		log.debug("merging IpClaimStatus instance");
		try {
			IpClaimStatus result = (IpClaimStatus) getHibernateTemplate().merge(detachedInstance);
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
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpClaimStatus instance) {
		log.debug("attaching clean IpClaimStatus instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findNext(Integer curr) {
		log.debug("finding Next IpClaimStatus instances");
		try {
			Query query = getSession().getNamedQuery("getNextClmStatus");
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
			Query query = getSession().getNamedQuery("getNonAllocClaimStatus");
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