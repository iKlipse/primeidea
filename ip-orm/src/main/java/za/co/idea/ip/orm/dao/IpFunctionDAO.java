package za.co.idea.ip.orm.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpFunctionDAO {
	private static final Logger log = Logger.getLogger(IpFunctionDAO.class);
	// property constants
	public static final String FUNC_NAME = "funcName";
	public static final String FUNC_IS_CORE = "funcIsCore";

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

	public void save(IpFunction transientInstance) {
		log.debug("saving IpFunction instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpFunction persistentInstance) {
		log.debug("deleting IpFunction instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpFunction findById(java.lang.Long id) {
		log.debug("getting IpFunction instance with id: " + id);
		try {
			IpFunction instance = (IpFunction) getCurrentSession().get("za.co.idea.ip.orm.bean.IpFunction", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpFunction> findByExample(IpFunction instance) {
		log.debug("finding IpFunction instance by example");
		try {
			List<IpFunction> results = (List<IpFunction>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpFunction").add(create(instance)).list();
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
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpFunction> findByFuncName(Object funcName) {
		return findByProperty(FUNC_NAME, funcName);
	}

	public List<IpFunction> findByFuncIsCore(Object funcIsCore) {
		return findByProperty(FUNC_IS_CORE, funcIsCore);
	}

	public List findAll() {
		log.debug("finding all IpFunction instances");
		try {
			String queryString = "from IpFunction";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpFunction merge(IpFunction detachedInstance) {
		log.debug("merging IpFunction instance");
		try {
			IpFunction result = (IpFunction) getCurrentSession().merge(detachedInstance);
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
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpFunction instance) {
		log.debug("attaching clean IpFunction instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List getFunctionByQuery() {
		log.debug("Fetching Functions by Query :: getAllFunction");
		try {
			Query query = getCurrentSession().getNamedQuery("getAllFunction");
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
			Query query = getCurrentSession().getNamedQuery("getFunctionById");
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
			Query query = getCurrentSession().getNamedQuery("getFunctionByUserId");
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