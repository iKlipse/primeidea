package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpTag;

/**
 * A data access object (DAO) providing persistence and search support for IpTag
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpTag
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpTagDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpTagDAO.class);
	// property constants
	public static final String TAG_ENTITY_ID = "tagEntityId";
	public static final String TAG_TEXT = "tagText";

	public void save(IpTag transientInstance) {
		log.debug("saving IpTag instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(transientInstance);
			transaction.commit();
			session.close();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public void delete(IpTag persistentInstance) {
		log.debug("deleting IpTag instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(persistentInstance);
			transaction.commit();
			session.close();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public IpTag findById(java.lang.Long id) {
		log.debug("getting IpTag instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpTag instance = (IpTag) session.get("za.co.idea.ip.orm.bean.IpTag", id);
			transaction.commit();
			session.close();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public List findByExample(IpTag instance) {
		log.debug("finding IpTag instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpTag").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			transaction.commit();
			session.close();
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpTag instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpTag as model where model." + propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			List results = queryObject.list();
			transaction.commit();
			session.close();
			return results;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public List findByTagEntityId(Object tagEntityId) {
		return findByProperty(TAG_ENTITY_ID, tagEntityId);
	}

	public List findByTagText(Object tagText) {
		return findByProperty(TAG_TEXT, tagText);
	}

	public List findAll() {
		log.debug("finding all IpTag instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpTag";
			Query queryObject = session.createQuery(queryString);
			List results = queryObject.list();
			transaction.commit();
			session.close();
			return results;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public IpTag merge(IpTag detachedInstance) {
		log.debug("merging IpTag instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpTag result = (IpTag) session.merge(detachedInstance);
			transaction.commit();
			session.close();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public void attachDirty(IpTag instance) {
		log.debug("attaching dirty IpTag instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(instance);
			transaction.commit();
			session.close();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public List getTagByFilterA(Long entityId, Integer teId, Integer ttId) {
		log.debug("Fetching Tag by Query :: getTagByFilterA");
		Session session = getSession();
		try {
			Query query = session.getNamedQuery("getTagByFilterA");
			query.setLong("entityId", entityId);
			query.setInteger("ttId", ttId);
			query.setInteger("teId", teId);
			List ret = query.list();
			for (Object object : ret) {
				IpTag tag = (IpTag) object;
				Hibernate.initialize(tag.getIpUser());
				Hibernate.initialize(tag.getIpTagEntityType());
				Hibernate.initialize(tag.getIpTagType());
			}
			session.close();
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List getTagByFilterB(Long entityId, Integer teId, Integer ttId, Long userId) {
		log.debug("Fetching Tag by Query :: getTagByFilterB");
		Session session = getSession();
		try {
			Query query = session.getNamedQuery("getTagByFilterB");
			query.setLong("entityId", entityId);
			query.setLong("userId", userId);
			query.setInteger("ttId", ttId);
			query.setInteger("teId", teId);
			List ret = query.list();
			for (Object object : ret) {
				IpTag tag = (IpTag) object;
				Hibernate.initialize(tag.getIpUser());
				Hibernate.initialize(tag.getIpTagEntityType());
				Hibernate.initialize(tag.getIpTagType());
			}
			session.close();
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}