package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import za.co.idea.ip.orm.bean.IpFuncGroup;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpFuncGroup entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpFuncGroup
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings({ "rawtypes" })
public class IpFuncGroupDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(IpFuncGroupDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(IpFuncGroup transientInstance) {
		log.debug("saving IpFuncGroup instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpFuncGroup persistentInstance) {
		log.debug("deleting IpFuncGroup instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpFuncGroup findById(java.lang.Long id) {
		log.debug("getting IpFuncGroup instance with id: " + id);
		try {
			IpFuncGroup instance = (IpFuncGroup) getHibernateTemplate().get("za.co.idea.ip.orm.bean.IpFuncGroup", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IpFuncGroup instance) {
		log.debug("finding IpFuncGroup instance by example");
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
		log.debug("finding IpFuncGroup instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpFuncGroup as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all IpFuncGroup instances");
		try {
			String queryString = "from IpFuncGroup";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpFuncGroup merge(IpFuncGroup detachedInstance) {
		log.debug("merging IpFuncGroup instance");
		try {
			IpFuncGroup result = (IpFuncGroup) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpFuncGroup instance) {
		log.debug("attaching dirty IpFuncGroup instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpFuncGroup instance) {
		log.debug("attaching clean IpFuncGroup instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void deleteByFunctionId(Long id) {
		log.debug("Deleting Function Groups By Id : " + id);
		try {
			Query query = getSession().getNamedQuery("deleteFGByFuncId");
			query.setLong("id", id);
			query.executeUpdate();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List fetchByFuncId(Long id) {
		log.debug("Fetching Group Users By Id : " + id);
		try {
			Query query = getSession().getNamedQuery("fetchFGByFuncId");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpFuncGroup fg = (IpFuncGroup) object;
				Hibernate.initialize(fg.getIpGroup());
				Hibernate.initialize(fg.getIpFunction());
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
			Query query = getSession().getNamedQuery("fetchFGByGroupId");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpFuncGroup fg = (IpFuncGroup) object;
				Hibernate.initialize(fg.getIpGroup());
				Hibernate.initialize(fg.getIpFunction());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpFuncGroupDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpFuncGroupDAO) ctx.getBean("IpFuncGroupDAO");
	}
}