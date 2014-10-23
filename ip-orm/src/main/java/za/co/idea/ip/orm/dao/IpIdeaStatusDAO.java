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

import za.co.idea.ip.orm.bean.IpIdeaStatus;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpIdeaStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpIdeaStatus
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpIdeaStatusDAO {
	private static final Logger log = Logger.getLogger(IpIdeaStatusDAO.class);
	// property constants
	public static final String IS_DESC = "isDesc";

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

	public void save(IpIdeaStatus transientInstance) {
		log.debug("saving IpIdeaStatus instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpIdeaStatus persistentInstance) {
		log.debug("deleting IpIdeaStatus instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpIdeaStatus findById(java.lang.Integer id) {
		log.debug("getting IpIdeaStatus instance with id: " + id);
		try {
			IpIdeaStatus instance = (IpIdeaStatus) getCurrentSession().get("za.co.idea.ip.orm.bean.IpIdeaStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpIdeaStatus> findByExample(IpIdeaStatus instance) {
		log.debug("finding IpIdeaStatus instance by example");
		try {
			List<IpIdeaStatus> results = (List<IpIdeaStatus>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpIdeaStatus").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpIdeaStatus instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpIdeaStatus as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpIdeaStatus> findByIsDesc(Object isDesc) {
		return findByProperty(IS_DESC, isDesc);
	}

	public List findAll() {
		log.debug("finding all IpIdeaStatus instances");
		try {
			String queryString = "from IpIdeaStatus";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpIdeaStatus merge(IpIdeaStatus detachedInstance) {
		log.debug("merging IpIdeaStatus instance");
		try {
			IpIdeaStatus result = (IpIdeaStatus) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpIdeaStatus instance) {
		log.debug("attaching dirty IpIdeaStatus instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpIdeaStatus instance) {
		log.debug("attaching clean IpIdeaStatus instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findNext(Integer curr) {
		log.debug("finding Next IpIdeaStatus instances");
		try {
			Query query = getCurrentSession().getNamedQuery("getNextIdeaStatus");
			query.setLong("curr", curr);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findAllNonAlloc() {
		log.debug("finding all IpIdeaStatus instances");
		try {
			Query query = getCurrentSession().getNamedQuery("getNonAllocIdeaStatus");
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public static IpIdeaStatusDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpIdeaStatusDAO) ctx.getBean("IpIdeaStatusDAO");
	}
}