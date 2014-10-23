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

import za.co.idea.ip.orm.bean.IpPoints;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpPoints entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpPoints
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpPointsDAO {
	private static final Logger log = Logger.getLogger(IpPointsDAO.class);
	// property constants
	public static final String POINT_VALUE = "pointValue";
	public static final String COMMENTS = "comments";
	public static final String ENTITY_ID = "entityId";

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

	public void save(IpPoints transientInstance) {
		log.debug("saving IpPoints instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpPoints persistentInstance) {
		log.debug("deleting IpPoints instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpPoints findById(java.lang.Long id) {
		log.debug("getting IpPoints instance with id: " + id);
		try {
			IpPoints instance = (IpPoints) getCurrentSession().get("za.co.idea.ip.orm.bean.IpPoints", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpPoints> findByExample(IpPoints instance) {
		log.debug("finding IpPoints instance by example");
		try {
			List<IpPoints> results = (List<IpPoints>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpPoints").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpPoints instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpPoints as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpPoints> findByPointValue(Object pointValue) {
		return findByProperty(POINT_VALUE, pointValue);
	}

	public List<IpPoints> findByComments(Object comments) {
		return findByProperty(COMMENTS, comments);
	}

	public List<IpPoints> findByEntityId(Object entityId) {
		return findByProperty(ENTITY_ID, entityId);
	}

	public List findAll() {
		log.debug("finding all IpPoints instances");
		try {
			String queryString = "from IpPoints";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpPoints merge(IpPoints detachedInstance) {
		log.debug("merging IpPoints instance");
		try {
			IpPoints result = (IpPoints) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpPoints instance) {
		log.debug("attaching dirty IpPoints instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpPoints instance) {
		log.debug("attaching clean IpPoints instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByUser(Long id) {
		log.debug("Fetching Points by Query :: getPointsByUser");
		try {
			Query query = getCurrentSession().getNamedQuery("getPointsByUser");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpPoints ip = (IpPoints) object;
				Hibernate.initialize(ip.getIpAllocation());
				Hibernate.initialize(ip.getIpUser());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpPointsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpPointsDAO) ctx.getBean("IpPointsDAO");
	}
}