package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import za.co.idea.ip.orm.bean.IpAllocation;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpAllocation entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpAllocation
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings({ "rawtypes" })
public class IpAllocationDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(IpAllocationDAO.class);
	// property constants
	public static final String ALLOC_DESC = "allocDesc";
	public static final String ALLOC_VAL = "allocVal";
	public static final String ALLOC_ENTITY = "allocEntity";
	public static final String ALLOC_STATUS_ID = "allocStatusId";

	protected void initDao() {
		// do nothing
	}

	public void save(IpAllocation transientInstance) {
		log.debug("saving IpAllocation instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpAllocation persistentInstance) {
		log.debug("deleting IpAllocation instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpAllocation findById(java.lang.Integer id) {
		log.debug("getting IpAllocation instance with id: " + id);
		try {
			IpAllocation instance = (IpAllocation) getHibernateTemplate().get("za.co.idea.ip.orm.bean.IpAllocation", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IpAllocation instance) {
		log.debug("finding IpAllocation instance by example");
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
		log.debug("finding IpAllocation instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpAllocation as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAllocDesc(Object allocDesc) {
		return findByProperty(ALLOC_DESC, allocDesc);
	}

	public List findByAllocVal(Object allocVal) {
		return findByProperty(ALLOC_VAL, allocVal);
	}

	public List findByAllocEntity(Object allocEntity) {
		return findByProperty(ALLOC_ENTITY, allocEntity);
	}

	public List findByAllocStatusId(Object allocStatusId) {
		return findByProperty(ALLOC_STATUS_ID, allocStatusId);
	}

	public List findAll() {
		log.debug("finding all IpAllocation instances");
		try {
			String queryString = "from IpAllocation";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpAllocation merge(IpAllocation detachedInstance) {
		log.debug("merging IpAllocation instance");
		try {
			IpAllocation result = (IpAllocation) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpAllocation instance) {
		log.debug("attaching dirty IpAllocation instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpAllocation instance) {
		log.debug("attaching clean IpAllocation instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List getAllocationByEntity(String entity) {
		log.debug("Fetching Allocation By Query :: getAllocationByEntity");
		try {
			Query query = getSession().getNamedQuery("getAllocationByEntity");
			query.setString("entity", entity);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List getUtilisedAllocation(String entity) {
		log.debug("Fetching Allocation By Query :: getUsedAllocation");
		try {
			Query query = getSession().getNamedQuery("getUsedAllocation");
			query.setString("entity", entity);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpAllocationDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpAllocationDAO) ctx.getBean("IpAllocationDAO");
	}
}