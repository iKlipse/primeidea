package za.co.idea.ip.orm.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpGroupDAO {
	private static final Logger log = Logger.getLogger(IpGroupDAO.class);
	// property constants
	public static final String GROUP_NAME = "groupName";
	public static final String GROUP_STATUS = "groupStatus";
	public static final String GROUP_EMAIL = "groupEmail";
	public static final String GROUP_IS_CORE = "groupIsCore";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(IpGroup transientInstance) {
		log.debug("saving IpGroup instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpGroup persistentInstance) {
		log.debug("deleting IpGroup instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpGroup findById(java.lang.Long id) {
		log.debug("getting IpGroup instance with id: " + id);
		try {
			IpGroup instance = (IpGroup) getCurrentSession().get("za.co.idea.ip.orm.bean.IpGroup", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpGroup> findByExample(IpGroup instance) {
		log.debug("finding IpGroup instance by example");
		try {
			List<IpGroup> results = (List<IpGroup>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpGroup").add(create(instance)).list();
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
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpGroup> findByGroupName(Object groupName) {
		return findByProperty(GROUP_NAME, groupName);
	}

	public List<IpGroup> findByGroupStatus(Object groupStatus) {
		return findByProperty(GROUP_STATUS, groupStatus);
	}

	public List<IpGroup> findByGroupEmail(Object groupEmail) {
		return findByProperty(GROUP_EMAIL, groupEmail);
	}

	public List<IpGroup> findByGroupIsCore(Object groupIsCore) {
		return findByProperty(GROUP_IS_CORE, groupIsCore);
	}

	public List findAll() {
		log.debug("finding all IpGroup instances");
		try {
			String queryString = "from IpGroup";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpGroup merge(IpGroup detachedInstance) {
		log.debug("merging IpGroup instance");
		try {
			IpGroup result = (IpGroup) getCurrentSession().merge(detachedInstance);
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
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpGroup instance) {
		log.debug("attaching clean IpGroup instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findActiveGroups() {
		log.debug("finding all active  IpGroup instances by query: getGroupsByStatus");
		try {
			Query query = getCurrentSession().getNamedQuery("getGroupsByStatus");
			query.setString("status", "y");
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findReviewGroups() {
		log.debug("finding all active  IpGroup instances by query: getGroupsForReview");
		try {
			Query query = getCurrentSession().getNamedQuery("getGroupsForReview");
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findSubGroups(Long id) {
		log.debug("finding all sub group instances by query: getGroupsByParent");
		try {
			Query query = getCurrentSession().getNamedQuery("getGroupsByParent");
			query.setLong("id", id);
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
			Query query = getCurrentSession().getNamedQuery("getGroupsByStatus");
			query.setString("status", "n");
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public String getGroupHierarchy(Long grpId) {
		log.debug("Fetching Group By Query :: fetchGroupHierarchy");
		try {
			Query query = getCurrentSession().createSQLQuery("select calc_grp_path_in(" + grpId + ") from dual");
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