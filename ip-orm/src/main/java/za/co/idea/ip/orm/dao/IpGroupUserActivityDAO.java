package za.co.idea.ip.orm.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import za.co.idea.ip.orm.bean.IpGroupUserActivity;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpGroupUserActivity entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpGroupUserActivity
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IpGroupUserActivityDAO {
	private static final Logger log = Logger.getLogger(IpGroupUserActivityDAO.class);
	// property constants

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

	public void save(IpGroupUserActivity transientInstance) {
		log.debug("saving IpGroupUserActivity instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpGroupUserActivity persistentInstance) {
		log.debug("deleting IpGroupUserActivity instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpGroupUserActivity findById(za.co.idea.ip.orm.bean.IpGroupUserActivityId id) {
		log.debug("getting IpGroupUserActivity instance with id: " + id);
		try {
			IpGroupUserActivity instance = (IpGroupUserActivity) getCurrentSession().get("za.co.idea.ip.orm.bean.IpGroupUserActivity", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpGroupUserActivity> findByExample(IpGroupUserActivity instance) {
		log.debug("finding IpGroupUserActivity instance by example");
		try {
			List<IpGroupUserActivity> results = (List<IpGroupUserActivity>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpGroupUserActivity").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpGroupUserActivity instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpGroupUserActivity as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all IpGroupUserActivity instances");
		try {
			String queryString = "from IpGroupUserActivity";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List initializeAll() {
		log.debug("finding all IpGroupUserActivity instances");
		try {
			String queryString = "from IpGroupUserActivity";
			Query queryObject = getCurrentSession().createQuery(queryString);
			List ret = queryObject.list();
			for (Object obj : ret) {
				if (obj == null)
					continue;
				IpGroupUserActivity act = (IpGroupUserActivity) obj;
				Hibernate.initialize(act.getId());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpGroupUserActivity merge(IpGroupUserActivity detachedInstance) {
		log.debug("merging IpGroupUserActivity instance");
		try {
			IpGroupUserActivity result = (IpGroupUserActivity) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpGroupUserActivity instance) {
		log.debug("attaching dirty IpGroupUserActivity instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpGroupUserActivity instance) {
		log.debug("attaching clean IpGroupUserActivity instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpGroupUserActivityDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpGroupUserActivityDAO) ctx.getBean("IpGroupUserActivityDAO");
	}
}