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

import za.co.idea.ip.orm.bean.IpClaim;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpClaim entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpClaim
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpClaimDAO {
	private static final Logger log = Logger.getLogger(IpClaimDAO.class);
	// property constants
	public static final String CLAIM_DESC = "claimDesc";
	public static final String CLAIM_COMMENT = "claimComment";

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

	public void save(IpClaim transientInstance) {
		log.debug("saving IpClaim instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpClaim persistentInstance) {
		log.debug("deleting IpClaim instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpClaim findById(java.lang.Long id) {
		log.debug("getting IpClaim instance with id: " + id);
		try {
			IpClaim instance = (IpClaim) getCurrentSession().get("za.co.idea.ip.orm.bean.IpClaim", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpClaim> findByExample(IpClaim instance) {
		log.debug("finding IpClaim instance by example");
		try {
			List<IpClaim> results = (List<IpClaim>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpClaim").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpClaim instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpClaim as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpClaim> findByClaimDesc(Object claimDesc) {
		return findByProperty(CLAIM_DESC, claimDesc);
	}

	public List<IpClaim> findByClaimComment(Object claimComment) {
		return findByProperty(CLAIM_COMMENT, claimComment);
	}

	public List findAll() {
		log.debug("finding all IpClaim instances");
		try {
			String queryString = "from IpClaim";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpClaim merge(IpClaim detachedInstance) {
		log.debug("merging IpClaim instance");
		try {
			IpClaim result = (IpClaim) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpClaim instance) {
		log.debug("attaching dirty IpClaim instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpClaim instance) {
		log.debug("attaching clean IpClaim instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByStatusId(Integer id) {
		log.debug("Fetching Challenge by Query :: getClaimByStatus");
		try {
			Query query = getCurrentSession().getNamedQuery("getClaimByStatus");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpClaim clm = (IpClaim) object;
				Hibernate.initialize(clm.getIpClaimStatus());
				Hibernate.initialize(clm.getIpRewards());
				Hibernate.initialize(clm.getIpUser());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByUserId(Long id) {
		log.debug("Fetching Challenge by Query :: getClaimByUser");
		try {
			Query query = getCurrentSession().getNamedQuery("getClaimByUser");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpClaim clm = (IpClaim) object;
				Hibernate.initialize(clm.getIpClaimStatus());
				Hibernate.initialize(clm.getIpRewards());
				Hibernate.initialize(clm.getIpUser());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpClaimDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpClaimDAO) ctx.getBean("IpClaimDAO");
	}
}