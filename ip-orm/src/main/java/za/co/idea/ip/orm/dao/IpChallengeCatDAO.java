package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import za.co.idea.ip.orm.bean.IpChallengeCat;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpChallengeCat entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpChallengeCat
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings({ "rawtypes" })
public class IpChallengeCatDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(IpChallengeCatDAO.class);
	// property constants
	public static final String CC_DESC = "ccDesc";

	protected void initDao() {
		// do nothing
	}

	public void save(IpChallengeCat transientInstance) {
		log.debug("saving IpChallengeCat instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpChallengeCat persistentInstance) {
		log.debug("deleting IpChallengeCat instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpChallengeCat findById(java.lang.Integer id) {
		log.debug("getting IpChallengeCat instance with id: " + id);
		try {
			IpChallengeCat instance = (IpChallengeCat) getHibernateTemplate().get("za.co.idea.ip.orm.bean.IpChallengeCat", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IpChallengeCat instance) {
		log.debug("finding IpChallengeCat instance by example");
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
		log.debug("finding IpChallengeCat instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpChallengeCat as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCcDesc(Object ccDesc) {
		return findByProperty(CC_DESC, ccDesc);
	}

	public List findAll() {
		log.debug("finding all IpChallengeCat instances");
		try {
			String queryString = "from IpChallengeCat";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpChallengeCat merge(IpChallengeCat detachedInstance) {
		log.debug("merging IpChallengeCat instance");
		try {
			IpChallengeCat result = (IpChallengeCat) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpChallengeCat instance) {
		log.debug("attaching dirty IpChallengeCat instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpChallengeCat instance) {
		log.debug("attaching clean IpChallengeCat instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpChallengeCatDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpChallengeCatDAO) ctx.getBean("IpChallengeCatDAO");
	}
}