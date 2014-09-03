package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import za.co.idea.ip.orm.bean.IpReview;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpReview entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpReview
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings({ "rawtypes" })
public class IpReviewDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(IpReviewDAO.class);
	// property constants
	public static final String REV_ENTITY_ID = "revEntityId";
	public static final String REV_ENTITY_NAME = "revEntityName";

	protected void initDao() {
		// do nothing
	}

	public void save(IpReview transientInstance) {
		log.debug("saving IpReview instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpReview persistentInstance) {
		log.debug("deleting IpReview instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpReview findById(java.lang.Long id) {
		log.debug("getting IpReview instance with id: " + id);
		try {
			IpReview instance = (IpReview) getHibernateTemplate().get("za.co.idea.ip.orm.bean.IpReview", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IpReview instance) {
		log.debug("finding IpReview instance by example");
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
		log.debug("finding IpReview instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpReview as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRevEntityId(Object revEntityId) {
		return findByProperty(REV_ENTITY_ID, revEntityId);
	}

	public List findByRevEntityName(Object revEntityName) {
		return findByProperty(REV_ENTITY_NAME, revEntityName);
	}

	public List findAll() {
		log.debug("finding all IpReview instances");
		try {
			String queryString = "from IpReview";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpReview merge(IpReview detachedInstance) {
		log.debug("merging IpReview instance");
		try {
			IpReview result = (IpReview) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpReview instance) {
		log.debug("attaching dirty IpReview instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpReview instance) {
		log.debug("attaching clean IpReview instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByEntityIdEntityName(Long entityId, String entityName, Integer status) {
		log.debug("finding all reviews by entity id");
		try {
			Query query = getSession().getNamedQuery("getByEntityIdEntityNameStatus");
			query.setLong("id", entityId);
			query.setString("tblNm", entityName);
			query.setInteger("status", status);
			List ret = query.list();
			for (Object object : ret) {
				IpReview review = (IpReview) object;
				Hibernate.initialize(review.getIpGroup());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findUnAllocatedReviews() {
		log.debug("finding all reviews by entity id");
		try {
			Query query = getSession().getNamedQuery("getUnallocatedReview");
			List ret = query.list();
			for (Object object : ret) {
				IpReview review = (IpReview) object;
				Hibernate.initialize(review.getIpGroup());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findReviewsByUserId(Long uId) {
		log.debug("finding all reviews by entity id");
		try {
			Query query = getSession().getNamedQuery("getReviewByUserId");
			query.setLong("id", uId);
			List ret = query.list();
			for (Object object : ret) {
				IpReview review = (IpReview) object;
				Hibernate.initialize(review.getIpGroup());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void deleteByEntityIdEntityName(Long entityId, String entityName, Integer status) {
		log.debug("finding all reviews by entity id");
		try {
			Query query = getSession().getNamedQuery("deleteByEntityIdEntityNameStatus");
			query.setLong("id", entityId);
			query.setString("tblNm", entityName);
			query.setInteger("status", status);
			query.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void updateReviewer(Long entityId, String entityName, Long uId) {
		log.debug("updating reviewer");
		try {
			Query query = getSession().getNamedQuery("updateReviewer");
			query.setLong("id", entityId);
			query.setString("tblNm", entityName);
			query.setLong("uid", uId);
			query.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public static IpReviewDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpReviewDAO) ctx.getBean("IpReviewDAO");
	}

}