package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import za.co.idea.ip.orm.bean.IpRewardsCat;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpRewardsCat entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpRewardsCat
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings({ "rawtypes" })
public class IpRewardsCatDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(IpRewardsCatDAO.class);
	// property constants
	public static final String RC_DESC = "rcDesc";

	protected void initDao() {
		// do nothing
	}

	public void save(IpRewardsCat transientInstance) {
		log.debug("saving IpRewardsCat instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpRewardsCat persistentInstance) {
		log.debug("deleting IpRewardsCat instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpRewardsCat findById(java.lang.Integer id) {
		log.debug("getting IpRewardsCat instance with id: " + id);
		try {
			IpRewardsCat instance = (IpRewardsCat) getHibernateTemplate().get("za.co.idea.ip.orm.bean.IpRewardsCat", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IpRewardsCat instance) {
		log.debug("finding IpRewardsCat instance by example");
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
		log.debug("finding IpRewardsCat instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpRewardsCat as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRcDesc(Object rcDesc) {
		return findByProperty(RC_DESC, rcDesc);
	}

	public List findAll() {
		log.debug("finding all IpRewardsCat instances");
		try {
			String queryString = "from IpRewardsCat";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpRewardsCat merge(IpRewardsCat detachedInstance) {
		log.debug("merging IpRewardsCat instance");
		try {
			IpRewardsCat result = (IpRewardsCat) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpRewardsCat instance) {
		log.debug("attaching dirty IpRewardsCat instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpRewardsCat instance) {
		log.debug("attaching clean IpRewardsCat instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List listDependentCat() {
		log.debug("checking category dependency");
		try {
			Query query = getSession().getNamedQuery("checkRwDependency");
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("checking category", re);
			throw re;
		}
	}

	public static IpRewardsCatDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpRewardsCatDAO) ctx.getBean("IpRewardsCatDAO");
	}
}