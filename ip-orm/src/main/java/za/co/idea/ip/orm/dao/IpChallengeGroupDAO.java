package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import za.co.idea.ip.orm.bean.IpChallengeGroup;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpChallengeGroup entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpChallengeGroup
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings({ "rawtypes" })
public class IpChallengeGroupDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(IpChallengeGroupDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(IpChallengeGroup transientInstance) {
		log.debug("saving IpChallengeGroup instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpChallengeGroup persistentInstance) {
		log.debug("deleting IpChallengeGroup instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpChallengeGroup findById(java.lang.Long id) {
		log.debug("getting IpChallengeGroup instance with id: " + id);
		try {
			IpChallengeGroup instance = (IpChallengeGroup) getHibernateTemplate().get("za.co.idea.ip.orm.bean.IpChallengeGroup", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IpChallengeGroup instance) {
		log.debug("finding IpChallengeGroup instance by example");
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
		log.debug("finding IpChallengeGroup instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpChallengeGroup as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all IpChallengeGroup instances");
		try {
			String queryString = "from IpChallengeGroup";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpChallengeGroup merge(IpChallengeGroup detachedInstance) {
		log.debug("merging IpChallengeGroup instance");
		try {
			IpChallengeGroup result = (IpChallengeGroup) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpChallengeGroup instance) {
		log.debug("attaching dirty IpChallengeGroup instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpChallengeGroup instance) {
		log.debug("attaching clean IpChallengeGroup instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void deleteByChallengeId(Long id) {
		log.debug("Deleting Challenge Groups By Id : " + id);
		try {
			Query query = getSession().getNamedQuery("deleteCGByChalId");
			query.setLong("id", id);
			query.executeUpdate();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List fetchByChallengeId(Long id) {
		log.debug("Fetching Group Users By Id : " + id);
		try {
			Query query = getSession().getNamedQuery("fetchCGByChalId");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpChallengeGroup fg = (IpChallengeGroup) object;
				Hibernate.initialize(fg.getIpGroup());
				Hibernate.initialize(fg.getIpChallenge());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List fetchByGroupId(Long id) {
		log.debug("Fetching Group Users By Id : " + id);
		try {
			Query query = getSession().getNamedQuery("fetchCGByGroupId");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpChallengeGroup fg = (IpChallengeGroup) object;
				Hibernate.initialize(fg.getIpGroup());
				Hibernate.initialize(fg.getIpChallenge());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpChallengeGroupDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpChallengeGroupDAO) ctx.getBean("IpChallengeGroupDAO");
	}
}