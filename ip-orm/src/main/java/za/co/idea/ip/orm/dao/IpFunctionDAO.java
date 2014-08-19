package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import za.co.idea.ip.orm.bean.IpFunction;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpFunction entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpFunction
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings({ "rawtypes" })
public class IpFunctionDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(IpFunctionDAO.class);
	// property constants
	public static final String FUNC_NAME = "funcName";
	public static final String FUNC_IS_CORE = "funcIsCore";

	protected void initDao() {
		// do nothing
	}

	public void save(IpFunction transientInstance) {
		log.debug("saving IpFunction instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpFunction persistentInstance) {
		log.debug("deleting IpFunction instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpFunction findById(java.lang.Long id) {
		log.debug("getting IpFunction instance with id: " + id);
		try {
			IpFunction instance = (IpFunction) getHibernateTemplate().get("za.co.idea.ip.orm.bean.IpFunction", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IpFunction instance) {
		log.debug("finding IpFunction instance by example");
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
		log.debug("finding IpFunction instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpFunction as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFuncName(Object funcName) {
		return findByProperty(FUNC_NAME, funcName);
	}

	public List findByFuncIsCore(Object funcIsCore) {
		return findByProperty(FUNC_IS_CORE, funcIsCore);
	}

	public List findAll() {
		log.debug("finding all IpFunction instances");
		try {
			String queryString = "from IpFunction";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpFunction merge(IpFunction detachedInstance) {
		log.debug("merging IpFunction instance");
		try {
			IpFunction result = (IpFunction) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpFunction instance) {
		log.debug("attaching dirty IpFunction instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpFunction instance) {
		log.debug("attaching clean IpFunction instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List getFunctionByQuery() {
		log.debug("Fetching Functions by Query :: getAllFunction");
		try {
			Query query = getSession().getNamedQuery("getAllFunction");
			List ret = query.list();
			for (Object object : ret) {
				IpFunction function = (IpFunction) object;
				Hibernate.initialize(function.getIpFuncGroups());
			}

			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public IpFunction getFunctionById(Long id) {
		log.debug("Fetching Functions by Query :: getFunctionById");
		try {
			Query query = getSession().getNamedQuery("getFunctionById");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpFunction function = (IpFunction) object;
				Hibernate.initialize(function.getIpFuncGroups());
			}

			return (IpFunction) ret.get(0);
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List getFunctionByUserId(Long id) {
		log.debug("Fetching Functions by Query :: getFunctionByUserId");
		try {
			Query query = getSession().getNamedQuery("getFunctionByUserId");
			query.setLong("id", id);
			List ret = query.list();

			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpFunctionDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpFunctionDAO) ctx.getBean("IpFunctionDAO");
	}
}