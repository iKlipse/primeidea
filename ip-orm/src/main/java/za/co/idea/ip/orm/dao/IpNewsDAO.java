package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import za.co.idea.ip.orm.bean.IpNews;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpNews entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpNews
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings({ "rawtypes" })
public class IpNewsDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(IpNewsDAO.class);
	// property constants
	public static final String NEWS_TITLE = "newsTitle";
	public static final String NEWS_CONTENT = "newsContent";

	protected void initDao() {
		// do nothing
	}

	public void save(IpNews transientInstance) {
		log.debug("saving IpNews instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpNews persistentInstance) {
		log.debug("deleting IpNews instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpNews findById(java.lang.Long id) {
		log.debug("getting IpNews instance with id: " + id);
		try {
			IpNews instance = (IpNews) getHibernateTemplate().get("za.co.idea.ip.orm.bean.IpNews", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IpNews instance) {
		log.debug("finding IpNews instance by example");
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
		log.debug("finding IpNews instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpNews as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNewsTitle(Object newsTitle) {
		return findByProperty(NEWS_TITLE, newsTitle);
	}

	public List findByNewsContent(Object newsContent) {
		return findByProperty(NEWS_CONTENT, newsContent);
	}

	public List findAll() {
		log.debug("finding all IpNews instances");
		try {
			String queryString = "from IpNews";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpNews merge(IpNews detachedInstance) {
		log.debug("merging IpNews instance");
		try {
			IpNews result = (IpNews) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpNews instance) {
		log.debug("attaching dirty IpNews instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpNews instance) {
		log.debug("attaching clean IpNews instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpNewsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpNewsDAO) ctx.getBean("IpNewsDAO");
	}
}