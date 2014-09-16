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
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpSolutionCatDAO {
	private static final Logger log = Logger.getLogger(IpSolutionCatDAO.class);
	// property constants
	public static final String SC_DESC = "scDesc";

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

	public void save(IpSolutionCat transientInstance) {
		log.debug("saving IpSolutionCat instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpSolutionCat persistentInstance) {
		log.debug("deleting IpSolutionCat instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpSolutionCat findById(java.lang.Integer id) {
		log.debug("getting IpSolutionCat instance with id: " + id);
		try {
			IpSolutionCat instance = (IpSolutionCat) getCurrentSession().get("za.co.idea.ip.orm.bean.IpSolutionCat", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpSolutionCat> findByExample(IpSolutionCat instance) {
		log.debug("finding IpSolutionCat instance by example");
		try {
			List<IpSolutionCat> results = (List<IpSolutionCat>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpSolutionCat").add(create(instance)).list();
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
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpSolutionCat> findByScDesc(Object scDesc) {
		return findByProperty(SC_DESC, scDesc);
	}

	public List findAll() {
		log.debug("finding all IpSolutionCat instances");
		try {
			String queryString = "from IpSolutionCat";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpSolutionCat merge(IpSolutionCat detachedInstance) {
		log.debug("merging IpSolutionCat instance");
		try {
			IpSolutionCat result = (IpSolutionCat) getCurrentSession().merge(detachedInstance);
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
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpSolutionCat instance) {
		log.debug("attaching clean IpSolutionCat instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
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