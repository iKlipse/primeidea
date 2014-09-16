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

import za.co.idea.ip.orm.bean.IpCategory;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpCategory entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpCategory
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpCategoryDAO {
	private static final Logger log = Logger.getLogger(IpCategoryDAO.class);
	// property constants
	public static final String CAT_DESC = "catDesc";

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

	public void save(IpCategory transientInstance) {
		log.debug("saving IpCategory instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpCategory persistentInstance) {
		log.debug("deleting IpCategory instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpCategory findById(java.lang.Integer id) {
		log.debug("getting IpCategory instance with id: " + id);
		try {
			IpCategory instance = (IpCategory) getCurrentSession().get("za.co.idea.ip.orm.bean.IpCategory", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpCategory> findByExample(IpCategory instance) {
		log.debug("finding IpCategory instance by example");
		try {
			List<IpCategory> results = (List<IpCategory>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpCategory").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpCategory instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpCategory as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpCategory> findByCatDesc(Object catDesc) {
		return findByProperty(CAT_DESC, catDesc);
	}

	public List findAll() {
		log.debug("finding all IpCategory instances");
		try {
			String queryString = "from IpCategory";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpCategory merge(IpCategory detachedInstance) {
		log.debug("merging IpCategory instance");
		try {
			IpCategory result = (IpCategory) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpCategory instance) {
		log.debug("attaching dirty IpCategory instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpCategory instance) {
		log.debug("attaching clean IpCategory instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List listDependentCat(Long catId) {
		log.debug("checking category dependency");
		try {
			Query query = getCurrentSession().getNamedQuery("checkDependency");
			query.setLong("id", catId);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("checking category", re);
			throw re;
		}
	}

	public static IpCategoryDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpCategoryDAO) ctx.getBean("IpCategoryDAO");
	}
}