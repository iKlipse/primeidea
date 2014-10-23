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
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpConfigDAO {
	private static final Logger log = Logger.getLogger(IpConfigDAO.class);
	// property constants
	public static final String CONFIG_KEY = "configKey";
	public static final String CONFIG_VALUE = "configValue";
	public static final String CONFIG_ENV = "configEnv";
	public static final String CREATED_BY = "createdBy";

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

	public void save(IpConfig transientInstance) {
		log.debug("saving IpConfig instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpConfig persistentInstance) {
		log.debug("deleting IpConfig instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpConfig findById(java.lang.Integer id) {
		log.debug("getting IpConfig instance with id: " + id);
		try {
			IpConfig instance = (IpConfig) getCurrentSession().get("za.co.idea.ip.orm.bean.IpConfig", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpConfig> findByExample(IpConfig instance) {
		log.debug("finding IpConfig instance by example");
		try {
			List<IpConfig> results = (List<IpConfig>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpConfig").add(create(instance)).list();
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
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpConfig> findByConfigKey(Object configKey) {
		return findByProperty(CONFIG_KEY, configKey);
	}

	public List<IpConfig> findByConfigValue(Object configValue) {
		return findByProperty(CONFIG_VALUE, configValue);
	}

	public List<IpConfig> findByConfigEnv(Object configEnv) {
		return findByProperty(CONFIG_ENV, configEnv);
	}

	public List<IpConfig> findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all IpConfig instances");
		try {
			String queryString = "from IpConfig";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpConfig merge(IpConfig detachedInstance) {
		log.debug("merging IpConfig instance");
		try {
			IpConfig result = (IpConfig) getCurrentSession().merge(detachedInstance);
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
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpConfig instance) {
		log.debug("attaching clean IpConfig instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
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