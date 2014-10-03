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

import za.co.idea.ip.orm.bean.IpChallengeStatus;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpChallengeStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpChallengeStatus
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpChallengeStatusDAO {
	private static final Logger log = Logger.getLogger(IpChallengeStatusDAO.class);
	// property constants
	public static final String CS_DESC = "csDesc";

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

	public void save(IpChallengeStatus transientInstance) {
		log.debug("saving IpChallengeStatus instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpChallengeStatus persistentInstance) {
		log.debug("deleting IpChallengeStatus instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpChallengeStatus findById(java.lang.Integer id) {
		log.debug("getting IpChallengeStatus instance with id: " + id);
		try {
			IpChallengeStatus instance = (IpChallengeStatus) getCurrentSession().get("za.co.idea.ip.orm.bean.IpChallengeStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpChallengeStatus> findByExample(IpChallengeStatus instance) {
		log.debug("finding IpChallengeStatus instance by example");
		try {
			List<IpChallengeStatus> results = (List<IpChallengeStatus>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpChallengeStatus").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpChallengeStatus instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpChallengeStatus as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpChallengeStatus> findByCsDesc(Object csDesc) {
		return findByProperty(CS_DESC, csDesc);
	}

	public List findAll() {
		log.debug("finding all IpChallengeStatus instances");
		try {
			String queryString = "from IpChallengeStatus";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpChallengeStatus merge(IpChallengeStatus detachedInstance) {
		log.debug("merging IpChallengeStatus instance");
		try {
			IpChallengeStatus result = (IpChallengeStatus) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpChallengeStatus instance) {
		log.debug("attaching dirty IpChallengeStatus instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpChallengeStatus instance) {
		log.debug("attaching clean IpChallengeStatus instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findNext(Integer curr) {
		log.debug("finding Next IpChallengeStatus instances");
		try {
			Query query = getCurrentSession().getNamedQuery("getNextChalStatus");
			query.setLong("curr", curr);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findAllNonAlloc() {
		log.debug("finding all IpChallengeStatus instances");
		try {
			Query query = getCurrentSession().getNamedQuery("getNonAllocChalStatus");
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public static IpChallengeStatusDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpChallengeStatusDAO) ctx.getBean("IpChallengeStatusDAO");
	}
}