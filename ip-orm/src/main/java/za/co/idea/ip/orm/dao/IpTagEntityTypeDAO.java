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

import za.co.idea.ip.orm.bean.IpTagEntityType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpTagEntityType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpTagEntityType
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpTagEntityTypeDAO {
	private static final Logger log = Logger.getLogger(IpTagEntityTypeDAO.class);
	// property constants
	public static final String TE_DESC = "teDesc";

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

	public void save(IpTagEntityType transientInstance) {
		log.debug("saving IpTagEntityType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpTagEntityType persistentInstance) {
		log.debug("deleting IpTagEntityType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpTagEntityType findById(java.lang.Integer id) {
		log.debug("getting IpTagEntityType instance with id: " + id);
		try {
			IpTagEntityType instance = (IpTagEntityType) getCurrentSession().get("za.co.idea.ip.orm.bean.IpTagEntityType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpTagEntityType> findByExample(IpTagEntityType instance) {
		log.debug("finding IpTagEntityType instance by example");
		try {
			List<IpTagEntityType> results = (List<IpTagEntityType>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpTagEntityType").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpTagEntityType instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpTagEntityType as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpTagEntityType> findByTeDesc(Object teDesc) {
		return findByProperty(TE_DESC, teDesc);
	}

	public List findAll() {
		log.debug("finding all IpTagEntityType instances");
		try {
			String queryString = "from IpTagEntityType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpTagEntityType merge(IpTagEntityType detachedInstance) {
		log.debug("merging IpTagEntityType instance");
		try {
			IpTagEntityType result = (IpTagEntityType) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpTagEntityType instance) {
		log.debug("attaching dirty IpTagEntityType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpTagEntityType instance) {
		log.debug("attaching clean IpTagEntityType instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpTagEntityTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpTagEntityTypeDAO) ctx.getBean("IpTagEntityTypeDAO");
	}
}