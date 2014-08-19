package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import za.co.idea.ip.orm.bean.IpSolutionCat;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpSolutionCat entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpSolutionCat
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings({ "rawtypes" })
public class IpSolutionCatDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(IpSolutionCatDAO.class);
	// property constants
	public static final String SC_DESC = "scDesc";

	protected void initDao() {
		// do nothing
	}

	public void save(IpSolutionCat transientInstance) {
		log.debug("saving IpSolutionCat instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpSolutionCat persistentInstance) {
		log.debug("deleting IpSolutionCat instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpSolutionCat findById(java.lang.Integer id) {
		log.debug("getting IpSolutionCat instance with id: " + id);
		try {
			IpSolutionCat instance = (IpSolutionCat) getHibernateTemplate().get("za.co.idea.ip.orm.bean.IpSolutionCat", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IpSolutionCat instance) {
		log.debug("finding IpSolutionCat instance by example");
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
		log.debug("finding IpSolutionCat instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpSolutionCat as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByScDesc(Object scDesc) {
		return findByProperty(SC_DESC, scDesc);
	}

	public List findAll() {
		log.debug("finding all IpSolutionCat instances");
		try {
			String queryString = "from IpSolutionCat";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpSolutionCat merge(IpSolutionCat detachedInstance) {
		log.debug("merging IpSolutionCat instance");
		try {
			IpSolutionCat result = (IpSolutionCat) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpSolutionCat instance) {
		log.debug("attaching dirty IpSolutionCat instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpSolutionCat instance) {
		log.debug("attaching clean IpSolutionCat instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpSolutionCatDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpSolutionCatDAO) ctx.getBean("IpSolutionCatDAO");
	}
}