package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import za.co.idea.ip.orm.bean.IpIdeaGroup;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpIdeaGroup entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpIdeaGroup
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings({ "rawtypes" })
public class IpIdeaGroupDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(IpIdeaGroupDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(IpIdeaGroup transientInstance) {
		log.debug("saving IpIdeaGroup instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpIdeaGroup persistentInstance) {
		log.debug("deleting IpIdeaGroup instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpIdeaGroup findById(java.lang.Long id) {
		log.debug("getting IpIdeaGroup instance with id: " + id);
		try {
			IpIdeaGroup instance = (IpIdeaGroup) getHibernateTemplate().get("za.co.idea.ip.orm.bean.IpIdeaGroup", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IpIdeaGroup instance) {
		log.debug("finding IpIdeaGroup instance by example");
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
		log.debug("finding IpIdeaGroup instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpIdeaGroup as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all IpIdeaGroup instances");
		try {
			String queryString = "from IpIdeaGroup";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpIdeaGroup merge(IpIdeaGroup detachedInstance) {
		log.debug("merging IpIdeaGroup instance");
		try {
			IpIdeaGroup result = (IpIdeaGroup) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpIdeaGroup instance) {
		log.debug("attaching dirty IpIdeaGroup instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpIdeaGroup instance) {
		log.debug("attaching clean IpIdeaGroup instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void deleteByIdeaId(Long id) {
		log.debug("Deleting Idea Groups By Id : " + id);
		try {
			Query query = getSession().getNamedQuery("deleteIGByIdeaId");
			query.setLong("id", id);
			query.executeUpdate();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List fetchByIdeaId(Long id) {
		log.debug("Fetching Group Users By Id : " + id);
		try {
			Query query = getSession().getNamedQuery("fetchIGByIdeaId");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpIdeaGroup fg = (IpIdeaGroup) object;
				Hibernate.initialize(fg.getIpGroup());
				Hibernate.initialize(fg.getIpIdea());
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
			Query query = getSession().getNamedQuery("fetchIGByGroupId");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpIdeaGroup fg = (IpIdeaGroup) object;
				Hibernate.initialize(fg.getIpGroup());
				Hibernate.initialize(fg.getIpIdea());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpIdeaGroupDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpIdeaGroupDAO) ctx.getBean("IpIdeaGroupDAO");
	}
}