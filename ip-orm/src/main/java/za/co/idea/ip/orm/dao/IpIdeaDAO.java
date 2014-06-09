package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpIdea;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpIdea entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpIdea
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpIdeaDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpIdeaDAO.class);
	// property constants
	public static final String IDEA_TITLE = "ideaTitle";
	public static final String IDEA_DESC = "ideaDesc";
	public static final String IDEA_BA = "ideaBa";
	public static final String IDEA_TAG = "ideaTag";

	public void save(IpIdea transientInstance) {
		log.debug("saving IpIdea instance");
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

	public void delete(IpIdea persistentInstance) {
		log.debug("deleting IpIdea instance");
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

	public IpIdea findById(java.lang.Long id) {
		log.debug("getting IpIdea instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpIdea instance = (IpIdea) session.get("za.co.idea.ip.orm.bean.IpIdea", id);
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

	public List findByExample(IpIdea instance) {
		log.debug("finding IpIdea instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpIdea").add(Example.create(instance)).list();
			transaction.commit();
			session.close();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpIdea instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpIdea as model where model." + propertyName + "= ?";
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

	public List findByIdeaTitle(Object ideaTitle) {
		return findByProperty(IDEA_TITLE, ideaTitle);
	}

	public List findByIdeaDesc(Object ideaDesc) {
		return findByProperty(IDEA_DESC, ideaDesc);
	}

	public List findByIdeaBa(Object ideaBa) {
		return findByProperty(IDEA_BA, ideaBa);
	}

	public List findByIdeaTag(Object ideaTag) {
		return findByProperty(IDEA_TAG, ideaTag);
	}

	public List findAll() {
		log.debug("finding all IpIdea instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpIdea";
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

	public IpIdea merge(IpIdea detachedInstance) {
		log.debug("merging IpIdea instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpIdea result = (IpIdea) session.merge(detachedInstance);
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

	public void attachDirty(IpIdea instance) {
		log.debug("attaching dirty IpIdea instance");
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

	public List findByUserId(Long id) {
		log.debug("finding IpIdea instances by user id :: " + id);
		Session session = getSession();
		try {
			Query query = session.getNamedQuery("getIdeaByUser");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpIdea idea = (IpIdea) object;
				Hibernate.initialize(idea.getIpIdeaCat());
				Hibernate.initialize(idea.getIpIdeaStatus());
				Hibernate.initialize(idea.getIpUser());
			}
			session.close();
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}