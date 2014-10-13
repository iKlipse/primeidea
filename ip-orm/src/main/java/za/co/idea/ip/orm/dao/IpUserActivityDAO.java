package za.co.idea.ip.orm.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import za.co.idea.ip.orm.bean.IpUserActivity;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpUserActivity entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpUserActivity
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IpUserActivityDAO {
	private static final Logger log = Logger.getLogger(IpUserActivityDAO.class);
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

	public void save(IpUserActivity transientInstance) {
		log.debug("saving IpUserActivity instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpUserActivity persistentInstance) {
		log.debug("deleting IpUserActivity instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpUserActivity findById(za.co.idea.ip.orm.bean.IpUserActivityId id) {
		log.debug("getting IpUserActivity instance with id: " + id);
		try {
			IpUserActivity instance = (IpUserActivity) getCurrentSession().get(
					"za.co.idea.ip.orm.bean.IpUserActivity", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpUserActivity> findByExample(IpUserActivity instance) {
		log.debug("finding IpUserActivity instance by example");
		try {
			List<IpUserActivity> results = (List<IpUserActivity>) getCurrentSession()
					.createCriteria("za.co.idea.ip.orm.bean.IpUserActivity")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpUserActivity instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from IpUserActivity as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all IpUserActivity instances");
		try {
			String queryString = "from IpUserActivity";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpUserActivity merge(IpUserActivity detachedInstance) {
		log.debug("merging IpUserActivity instance");
		try {
			IpUserActivity result = (IpUserActivity) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpUserActivity instance) {
		log.debug("attaching dirty IpUserActivity instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpUserActivity instance) {
		log.debug("attaching clean IpUserActivity instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpUserActivityDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IpUserActivityDAO) ctx.getBean("IpUserActivityDAO");
	}
}