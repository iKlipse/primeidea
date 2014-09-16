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

import za.co.idea.ip.orm.bean.IpIdeaCat;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpIdeaCat entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpIdeaCat
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpIdeaCatDAO {
	private static final Logger log = Logger.getLogger(IpIdeaCatDAO.class);
	// property constants
	public static final String IC_DESC = "icDesc";

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

	public void save(IpIdeaCat transientInstance) {
		log.debug("saving IpIdeaCat instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpIdeaCat persistentInstance) {
		log.debug("deleting IpIdeaCat instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpIdeaCat findById(java.lang.Integer id) {
		log.debug("getting IpIdeaCat instance with id: " + id);
		try {
			IpIdeaCat instance = (IpIdeaCat) getCurrentSession().get("za.co.idea.ip.orm.bean.IpIdeaCat", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpIdeaCat> findByExample(IpIdeaCat instance) {
		log.debug("finding IpIdeaCat instance by example");
		try {
			List<IpIdeaCat> results = (List<IpIdeaCat>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpIdeaCat").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpIdeaCat instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpIdeaCat as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpIdeaCat> findByIcDesc(Object icDesc) {
		return findByProperty(IC_DESC, icDesc);
	}

	public List findAll() {
		log.debug("finding all IpIdeaCat instances");
		try {
			String queryString = "from IpIdeaCat";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpIdeaCat merge(IpIdeaCat detachedInstance) {
		log.debug("merging IpIdeaCat instance");
		try {
			IpIdeaCat result = (IpIdeaCat) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpIdeaCat instance) {
		log.debug("attaching dirty IpIdeaCat instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpIdeaCat instance) {
		log.debug("attaching clean IpIdeaCat instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpIdeaCatDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpIdeaCatDAO) ctx.getBean("IpIdeaCatDAO");
	}
}