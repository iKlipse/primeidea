package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import za.co.idea.ip.orm.bean.IpChallengeStatus;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpChallengeStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpChallengeStatus
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings({ "rawtypes" })
public class IpChallengeStatusDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(IpChallengeStatusDAO.class);
	// property constants
	public static final String CS_DESC = "csDesc";

	protected void initDao() {
		// do nothing
	}

	public void save(IpChallengeStatus transientInstance) {
		log.debug("saving IpChallengeStatus instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpChallengeStatus persistentInstance) {
		log.debug("deleting IpChallengeStatus instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpChallengeStatus findById(java.lang.Integer id) {
		log.debug("getting IpChallengeStatus instance with id: " + id);
		try {
			IpChallengeStatus instance = (IpChallengeStatus) getHibernateTemplate().get("za.co.idea.ip.orm.bean.IpChallengeStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IpChallengeStatus instance) {
		log.debug("finding IpChallengeStatus instance by example");
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
		log.debug("finding IpChallengeStatus instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpChallengeStatus as model where model." + propertyName + "= ?";
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
		log.debug("finding all IpChallengeStatus instances");
		try {
			String queryString = "from IpChallengeStatus";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpChallengeStatus merge(IpChallengeStatus detachedInstance) {
		log.debug("merging IpChallengeStatus instance");
		try {
			IpChallengeStatus result = (IpChallengeStatus) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpChallengeStatus instance) {
		log.debug("attaching dirty IpChallengeStatus instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpChallengeStatus instance) {
		log.debug("attaching clean IpChallengeStatus instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findNext(Integer curr) {
		log.debug("finding Next IpChallengeStatus instances");
		try {
			Query query = getSession().getNamedQuery("getNextChalStatus");
			query.setLong("curr", curr);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findAllNonAlloc() {
		log.debug("finding all IpChallengeStatus instances");
		try {
			Query query = getSession().getNamedQuery("getNonAllocChalStatus");
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public static IpChallengeStatusDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpChallengeStatusDAO) ctx.getBean("IpChallengeStatusDAO");
	}
}