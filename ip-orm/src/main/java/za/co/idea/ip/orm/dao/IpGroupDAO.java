package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import za.co.idea.ip.orm.bean.IpGroup;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpGroup entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpGroup
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings({ "rawtypes" })
public class IpGroupDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(IpGroupDAO.class);
	// property constants
	public static final String GROUP_NAME = "groupName";
	public static final String GROUP_STATUS = "groupStatus";
	public static final String GROUP_EMAIL = "groupEmail";
	public static final String GROUP_IS_CORE = "groupIsCore";

	protected void initDao() {
		// do nothing
	}

	public void save(IpGroup transientInstance) {
		log.debug("saving IpGroup instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpGroup persistentInstance) {
		log.debug("deleting IpGroup instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpGroup findById(java.lang.Long id) {
		log.debug("getting IpGroup instance with id: " + id);
		try {
			IpGroup instance = (IpGroup) getHibernateTemplate().get("za.co.idea.ip.orm.bean.IpGroup", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IpGroup instance) {
		log.debug("finding IpGroup instance by example");
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
		log.debug("finding IpGroup instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpGroup as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByGroupName(Object groupName) {
		return findByProperty(GROUP_NAME, groupName);
	}

	public List findByGroupStatus(Object groupStatus) {
		return findByProperty(GROUP_STATUS, groupStatus);
	}

	public List findByGroupEmail(Object groupEmail) {
		return findByProperty(GROUP_EMAIL, groupEmail);
	}

	public List findByGroupIsCore(Object groupIsCore) {
		return findByProperty(GROUP_IS_CORE, groupIsCore);
	}

	public List findAll() {
		log.debug("finding all IpGroup instances");
		try {
			String queryString = "from IpGroup";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findActiveGroups() {
		log.debug("finding all active  IpGroup instances by query: getGroupsByStatus");
		try {
			Query query = getSession().getNamedQuery("getGroupsByStatus");
			query.setString("status", "y");
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findInActiveGroups() {
		log.debug("finding all In active IpGroup instances by query: getGroupsByStatus ");
		try {
			Query query = getSession().getNamedQuery("getGroupsByStatus");
			query.setString("status", "n");
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpGroup merge(IpGroup detachedInstance) {
		log.debug("merging IpGroup instance");
		try {
			IpGroup result = (IpGroup) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpGroup instance) {
		log.debug("attaching dirty IpGroup instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpGroup instance) {
		log.debug("attaching clean IpGroup instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public String getGroupHierarchy(Long grpId) {
		log.debug("Fetching Group By Query :: fetchGroupHierarchy");
		try {
			Query query = getSession().createSQLQuery("select calc_grp_path_in(" + grpId + ") from dual");
			List ret = query.list();
			return (ret != null && ret.get(0) != null) ? ret.get(0).toString() : "";
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	public static IpGroupDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpGroupDAO) ctx.getBean("IpGroupDAO");
	}
}