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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.idea.ip.orm.bean.IpChallengeGroup;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpChallengeGroup entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpChallengeGroup
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpChallengeGroupDAO {
	private static final Logger log = Logger.getLogger(IpChallengeGroupDAO.class);
	// property constants

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

	public void save(IpChallengeGroup transientInstance) {
		log.debug("saving IpChallengeGroup instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpChallengeGroup persistentInstance) {
		log.debug("deleting IpChallengeGroup instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpChallengeGroup findById(java.lang.Long id) {
		log.debug("getting IpChallengeGroup instance with id: " + id);
		try {
			IpChallengeGroup instance = (IpChallengeGroup) getCurrentSession().get("za.co.idea.ip.orm.bean.IpChallengeGroup", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpChallengeGroup> findByExample(IpChallengeGroup instance) {
		log.debug("finding IpChallengeGroup instance by example");
		try {
			List<IpChallengeGroup> results = (List<IpChallengeGroup>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpChallengeGroup").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpChallengeGroup instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpChallengeGroup as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all IpChallengeGroup instances");
		try {
			String queryString = "from IpChallengeGroup";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpChallengeGroup merge(IpChallengeGroup detachedInstance) {
		log.debug("merging IpChallengeGroup instance");
		try {
			IpChallengeGroup result = (IpChallengeGroup) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpChallengeGroup instance) {
		log.debug("attaching dirty IpChallengeGroup instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteByChallengeId(Long id) {
		log.debug("Deleting Challenge Groups By Id : " + id);
		try {
			Query query = getCurrentSession().getNamedQuery("deleteCGByChalId");
			query.setLong("id", id);
			query.executeUpdate();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpChallengeGroup instance) {
		log.debug("attaching clean IpChallengeGroup instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List fetchByChallengeId(Long id) {
		log.debug("Fetching Group Users By Id : " + id);
		try {
			Query query = getCurrentSession().getNamedQuery("fetchCGByChalId");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpChallengeGroup fg = (IpChallengeGroup) object;
				Hibernate.initialize(fg.getIpGroup());
				Hibernate.initialize(fg.getIpChallenge());
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
			Query query = getCurrentSession().getNamedQuery("fetchCGByGroupId");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpChallengeGroup fg = (IpChallengeGroup) object;
				Hibernate.initialize(fg.getIpGroup());
				Hibernate.initialize(fg.getIpChallenge());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpChallengeGroupDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpChallengeGroupDAO) ctx.getBean("IpChallengeGroupDAO");
	}
}