package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import za.co.idea.ip.orm.bean.IpClaim;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpClaim entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpClaim
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings({ "rawtypes" })
public class IpClaimDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(IpClaimDAO.class);
	// property constants
	public static final String CLAIM_DESC = "claimDesc";
	public static final String CLAIM_COMMENT = "claimComment";

	protected void initDao() {
		// do nothing
	}

	public void save(IpClaim transientInstance) {
		log.debug("saving IpClaim instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpClaim persistentInstance) {
		log.debug("deleting IpClaim instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpClaim findById(java.lang.Long id) {
		log.debug("getting IpClaim instance with id: " + id);
		try {
			IpClaim instance = (IpClaim) getHibernateTemplate().get("za.co.idea.ip.orm.bean.IpClaim", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IpClaim instance) {
		log.debug("finding IpClaim instance by example");
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
		log.debug("finding IpClaim instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpClaim as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByClaimDesc(Object claimDesc) {
		return findByProperty(CLAIM_DESC, claimDesc);
	}

	public List findByClaimComment(Object claimComment) {
		return findByProperty(CLAIM_COMMENT, claimComment);
	}

	public List findAll() {
		log.debug("finding all IpClaim instances");
		try {
			String queryString = "from IpClaim";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpClaim merge(IpClaim detachedInstance) {
		log.debug("merging IpClaim instance");
		try {
			IpClaim result = (IpClaim) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpClaim instance) {
		log.debug("attaching dirty IpClaim instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpClaim instance) {
		log.debug("attaching clean IpClaim instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByStatusId(Integer id) {
		log.debug("Fetching Challenge by Query :: getClaimByStatus");
		try {
			Query query = getSession().getNamedQuery("getClaimByStatus");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpClaim clm = (IpClaim) object;
				Hibernate.initialize(clm.getIpClaimStatus());
				Hibernate.initialize(clm.getIpRewards());
				Hibernate.initialize(clm.getIpUser());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByUserId(Long id) {
		log.debug("Fetching Challenge by Query :: getClaimByUser");
		try {
			Query query = getSession().getNamedQuery("getClaimByUser");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpClaim clm = (IpClaim) object;
				Hibernate.initialize(clm.getIpClaimStatus());
				Hibernate.initialize(clm.getIpRewards());
				Hibernate.initialize(clm.getIpUser());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpClaimDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpClaimDAO) ctx.getBean("IpClaimDAO");
	}
}