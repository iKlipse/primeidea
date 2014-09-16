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
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpReviewDAO {
	private static final Logger log = Logger.getLogger(IpReviewDAO.class);
	// property constants
	public static final String REV_ENTITY_ID = "revEntityId";
	public static final String REV_ENTITY_NAME = "revEntityName";
	public static final String REV_ENTITY_STATUS_ID = "revEntityStatusId";
	public static final String REV_SEL_USER_ID = "revSelUserId";

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

	public void save(IpReview transientInstance) {
		log.debug("saving IpReview instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpReview persistentInstance) {
		log.debug("deleting IpReview instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpReview findById(java.lang.Long id) {
		log.debug("getting IpReview instance with id: " + id);
		try {
			IpReview instance = (IpReview) getCurrentSession().get("za.co.idea.ip.orm.bean.IpReview", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpReview> findByExample(IpReview instance) {
		log.debug("finding IpReview instance by example");
		try {
			List<IpReview> results = (List<IpReview>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpReview").add(create(instance)).list();
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
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpReview> findByRevEntityId(Object revEntityId) {
		return findByProperty(REV_ENTITY_ID, revEntityId);
	}

	public List<IpReview> findByRevEntityName(Object revEntityName) {
		return findByProperty(REV_ENTITY_NAME, revEntityName);
	}

	public List<IpReview> findByRevEntityStatusId(Object revEntityStatusId) {
		return findByProperty(REV_ENTITY_STATUS_ID, revEntityStatusId);
	}

	public List<IpReview> findByRevSelUserId(Object revSelUserId) {
		return findByProperty(REV_SEL_USER_ID, revSelUserId);
	}

	public List findAll() {
		log.debug("finding all IpReview instances");
		try {
			String queryString = "from IpReview";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpReview merge(IpReview detachedInstance) {
		log.debug("merging IpReview instance");
		try {
			IpReview result = (IpReview) getCurrentSession().merge(detachedInstance);
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
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpReview instance) {
		log.debug("attaching clean IpReview instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByEntityIdEntityName(Long entityId, String entityName, Integer status) {
		log.debug("finding all reviews by entity id");
		try {
			Query query = getCurrentSession().getNamedQuery("getByEntityIdEntityNameStatus");
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

	public List findAllByEntityIdEntityName(Long entityId, String entityName) {
		log.debug("finding all reviews by entity id");
		try {
			Query query = getCurrentSession().getNamedQuery("getByEntityIdEntityName");
			query.setLong("id", entityId);
			query.setString("tblNm", entityName);
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
			Query query = getCurrentSession().getNamedQuery("getUnallocatedReview");
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
			Query query = getCurrentSession().getNamedQuery("getReviewByUserId");
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

	public Integer findReviewStatusCount(Long entityId, String entityName) {
		log.debug("finding all reviews by entity id");
		try {
			Query query = getCurrentSession().createSQLQuery("select count(distinct ib.rev_entity_status_id) as cnt from ip_review ib where ib.rev_entity_id=:id and lower(ib.rev_entity_name)=lower(:tblNm)");
			query.setLong("id", entityId);
			query.setString("tblNm", entityName);
			List ret = query.list();
			return (Integer) ((ret != null && ret.size() > 0) ? ret.get(0) : 0);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void deleteByEntityIdEntityName(Long entityId, String entityName, Integer status) {
		log.debug("finding all reviews by entity id");
		try {
			Query query = getCurrentSession().getNamedQuery("deleteByEntityIdEntityNameStatus");
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
			Query query = getCurrentSession().getNamedQuery("updateReviewer");
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