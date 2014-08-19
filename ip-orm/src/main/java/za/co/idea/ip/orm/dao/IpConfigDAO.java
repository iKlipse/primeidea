package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import za.co.idea.ip.orm.bean.IpConfig;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpConfig entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpConfig
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings({ "rawtypes" })
public class IpConfigDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(IpConfigDAO.class);
	// property constants
	public static final String CONFIG_KEY = "configKey";
	public static final String CONFIG_VALUE = "configValue";
	public static final String CONFIG_ENV = "configEnv";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(IpConfig transientInstance) {
		log.debug("saving IpConfig instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpConfig persistentInstance) {
		log.debug("deleting IpConfig instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpConfig findById(java.lang.Integer id) {
		log.debug("getting IpConfig instance with id: " + id);
		try {
			IpConfig instance = (IpConfig) getHibernateTemplate().get("za.co.idea.ip.orm.bean.IpConfig", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IpConfig instance) {
		log.debug("finding IpConfig instance by example");
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
		log.debug("finding IpConfig instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpConfig as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByConfigKey(Object configKey) {
		return findByProperty(CONFIG_KEY, configKey);
	}

	public List findByConfigValue(Object configValue) {
		return findByProperty(CONFIG_VALUE, configValue);
	}

	public List findByConfigEnv(Object configEnv) {
		return findByProperty(CONFIG_ENV, configEnv);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all IpConfig instances");
		try {
			String queryString = "from IpConfig";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpConfig merge(IpConfig detachedInstance) {
		log.debug("merging IpConfig instance");
		try {
			IpConfig result = (IpConfig) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpConfig instance) {
		log.debug("attaching dirty IpConfig instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpConfig instance) {
		log.debug("attaching clean IpConfig instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpConfigDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpConfigDAO) ctx.getBean("IpConfigDAO");
	}
}