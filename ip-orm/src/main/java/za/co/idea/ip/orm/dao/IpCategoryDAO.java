package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

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
@SuppressWarnings({ "rawtypes" })
public class IpCategoryDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(IpCategoryDAO.class);
	// property constants
	public static final String CAT_DESC = "catDesc";

	protected void initDao() {
		// do nothing
	}

	public void save(IpCategory transientInstance) {
		log.debug("saving IpCategory instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpCategory persistentInstance) {
		log.debug("deleting IpCategory instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpCategory findById(java.lang.Integer id) {
		log.debug("getting IpCategory instance with id: " + id);
		try {
			IpCategory instance = (IpCategory) getHibernateTemplate().get("za.co.idea.ip.orm.bean.IpCategory", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IpCategory instance) {
		log.debug("finding IpCategory instance by example");
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
		log.debug("finding IpCategory instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpCategory as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCatDesc(Object catDesc) {
		return findByProperty(CAT_DESC, catDesc);
	}

	public List findAll() {
		log.debug("finding all IpCategory instances");
		try {
			String queryString = "from IpCategory";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpCategory merge(IpCategory detachedInstance) {
		log.debug("merging IpCategory instance");
		try {
			IpCategory result = (IpCategory) getHibernateTemplate().merge(detachedInstance);
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
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpCategory instance) {
		log.debug("attaching clean IpCategory instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List listDependentCat(Long catId) {
		log.debug("checking category dependency");
		try {
			Query query = getSession().getNamedQuery("checkDependency");
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