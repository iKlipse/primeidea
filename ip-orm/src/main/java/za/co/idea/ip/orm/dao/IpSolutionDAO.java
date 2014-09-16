package za.co.idea.ip.orm.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import za.co.idea.ip.orm.bean.IpSolution;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpSolution entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpSolution
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpSolutionDAO {
	private static final Logger log = Logger.getLogger(IpSolutionDAO.class);
	// property constants
	public static final String SOL_TITLE = "solTitle";
	public static final String SOL_DESC = "solDesc";
	public static final String SOL_TAGS = "solTags";
	public static final String SOL_REVIEW_CNT = "solReviewCnt";

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

	public void save(IpSolution transientInstance) {
		log.debug("saving IpSolution instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpSolution persistentInstance) {
		log.debug("deleting IpSolution instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpSolution findById(java.lang.Long id) {
		log.debug("getting IpSolution instance with id: " + id);
		try {
			IpSolution instance = (IpSolution) getCurrentSession().get("za.co.idea.ip.orm.bean.IpSolution", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpSolution> findByExample(IpSolution instance) {
		log.debug("finding IpSolution instance by example");
		try {
			List<IpSolution> results = (List<IpSolution>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpSolution").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpSolution instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpSolution as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpSolution> findBySolTitle(Object solTitle) {
		return findByProperty(SOL_TITLE, solTitle);
	}

	public List<IpSolution> findBySolDesc(Object solDesc) {
		return findByProperty(SOL_DESC, solDesc);
	}

	public List<IpSolution> findBySolTags(Object solTags) {
		return findByProperty(SOL_TAGS, solTags);
	}

	public List<IpSolution> findBySolReviewCnt(Object solReviewCnt) {
		return findByProperty(SOL_REVIEW_CNT, solReviewCnt);
	}

	public List findAll() {
		log.debug("finding all IpSolution instances");
		try {
			String queryString = "from IpSolution";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpSolution merge(IpSolution detachedInstance) {
		log.debug("merging IpSolution instance");
		try {
			IpSolution result = (IpSolution) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpSolution instance) {
		log.debug("attaching dirty IpSolution instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpSolution instance) {
		log.debug("attaching clean IpSolution instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findByUserId(Long id) {
		log.debug("Fetching Challenge by Query :: getSolutionByUser");
		try {
			Query query = getCurrentSession().getNamedQuery("getSolutionByUser");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpSolution sol = (IpSolution) object;
				Hibernate.initialize(sol.getIpChallenge());
				Hibernate.initialize(sol.getIpSolutionCat());
				Hibernate.initialize(sol.getIpSolutionStatus());
				Hibernate.initialize(sol.getIpUser());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findCreatedByUserId(Long id) {
		log.debug("Fetching Solution by Query :: getSolutionCreatedByUser");
		try {
			Query query = getCurrentSession().getNamedQuery("getSolutionCreatedByUser");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpSolution sol = (IpSolution) object;
				Hibernate.initialize(sol.getIpChallenge());
				Hibernate.initialize(sol.getIpSolutionCat());
				Hibernate.initialize(sol.getIpSolutionStatus());
				Hibernate.initialize(sol.getIpUser());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByStatusId(Integer id) {
		log.debug("Fetching Challenge by Query :: getSolutionByStatus");
		try {
			Query query = getCurrentSession().getNamedQuery("getSolutionByStatus");
			query.setInteger("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpSolution sol = (IpSolution) object;
				Hibernate.initialize(sol.getIpChallenge());
				Hibernate.initialize(sol.getIpSolutionCat());
				Hibernate.initialize(sol.getIpSolutionStatus());
				Hibernate.initialize(sol.getIpUser());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByStatusIdUserId(Integer sid, Long id) {
		log.debug("Fetching Challenge by Query :: getSolutionByUserStatus");
		try {
			Query query = getCurrentSession().getNamedQuery("getSolutionByUserStatus");
			query.setInteger("sid", sid);
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpSolution sol = (IpSolution) object;
				Hibernate.initialize(sol.getIpChallenge());
				Hibernate.initialize(sol.getIpSolutionCat());
				Hibernate.initialize(sol.getIpSolutionStatus());
				Hibernate.initialize(sol.getIpUser());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByChalId(Long id) {
		log.debug("Fetching Challenge by Query :: getSolutionByChallenge");
		try {
			Query query = getCurrentSession().getNamedQuery("getSolutionByChallenge");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpSolution sol = (IpSolution) object;
				Hibernate.initialize(sol.getIpChallenge());
				Hibernate.initialize(sol.getIpSolutionCat());
				Hibernate.initialize(sol.getIpSolutionStatus());
				Hibernate.initialize(sol.getIpUser());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findReviewSolByserId(Long id) {
		log.debug("Fetching Challenge by Query :: getReviewSolutionByStatus");
		try {
			Query query = getCurrentSession().getNamedQuery("getReviewSolutionByStatus");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpSolution sol = (IpSolution) object;
				Hibernate.initialize(sol.getIpChallenge());
				Hibernate.initialize(sol.getIpSolutionCat());
				Hibernate.initialize(sol.getIpSolutionStatus());
				Hibernate.initialize(sol.getIpUser());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public int updateStatusOnExpiry() {
		log.debug("Updating Solution by Query :: updateSolStatusOnExpiry");
		try {
			Query query = getCurrentSession().getNamedQuery("updateSolStatusOnExpiry");
			int ret = query.executeUpdate();
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpSolutionDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpSolutionDAO) ctx.getBean("IpSolutionDAO");
	}
}