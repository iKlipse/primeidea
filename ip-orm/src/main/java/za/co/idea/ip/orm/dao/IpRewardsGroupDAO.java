package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import za.co.idea.ip.orm.bean.IpRewardsGroup;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpRewardsGroup entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpRewardsGroup
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings({ "rawtypes" })
public class IpRewardsGroupDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(IpRewardsGroupDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(IpRewardsGroup transientInstance) {
		log.debug("saving IpRewardsGroup instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpRewardsGroup persistentInstance) {
		log.debug("deleting IpRewardsGroup instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpRewardsGroup findById(java.lang.Long id) {
		log.debug("getting IpRewardsGroup instance with id: " + id);
		try {
			IpRewardsGroup instance = (IpRewardsGroup) getHibernateTemplate().get("za.co.idea.ip.orm.bean.IpRewardsGroup", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IpRewardsGroup instance) {
		log.debug("finding IpRewardsGroup instance by example");
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
		log.debug("finding IpRewardsGroup instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpRewardsGroup as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all IpRewardsGroup instances");
		try {
			String queryString = "from IpRewardsGroup";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpRewardsGroup merge(IpRewardsGroup detachedInstance) {
		log.debug("merging IpRewardsGroup instance");
		try {
			IpRewardsGroup result = (IpRewardsGroup) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpRewardsGroup instance) {
		log.debug("attaching dirty IpRewardsGroup instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpRewardsGroup instance) {
		log.debug("attaching clean IpRewardsGroup instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void deleteByRewardsId(Long id) {
		log.debug("Deleting Rewards Groups By Id : " + id);
		try {
			Query query = getSession().getNamedQuery("deleteRGByRwId");
			query.setLong("id", id);
			query.executeUpdate();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List fetchByRewardsId(Long id) {
		log.debug("Fetching Group Users By Id : " + id);
		try {
			Query query = getSession().getNamedQuery("fetchRGByRwId");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpRewardsGroup fg = (IpRewardsGroup) object;
				Hibernate.initialize(fg.getIpGroup());
				Hibernate.initialize(fg.getIpRewards());
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
			Query query = getSession().getNamedQuery("fetchRGByGroupId");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpRewardsGroup fg = (IpRewardsGroup) object;
				Hibernate.initialize(fg.getIpGroup());
				Hibernate.initialize(fg.getIpRewards());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpRewardsGroupDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpRewardsGroupDAO) ctx.getBean("IpRewardsGroupDAO");
	}
}