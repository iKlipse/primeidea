package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpGroupUser;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpGroupUser entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpGroupUser
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpGroupUserDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpGroupUserDAO.class);

	// property constants

	public void save(IpGroupUser transientInstance) {
		log.debug("saving IpGroupUser instance");
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

	public void delete(IpGroupUser persistentInstance) {
		log.debug("deleting IpGroupUser instance");
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

	public IpGroupUser findById(java.lang.Long id) {
		log.debug("getting IpGroupUser instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpGroupUser instance = (IpGroupUser) session.get("za.co.idea.ip.orm.bean.IpGroupUser", id);
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

	public List findByExample(IpGroupUser instance) {
		log.debug("finding IpGroupUser instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpGroupUser").add(Example.create(instance)).list();
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
		log.debug("finding IpGroupUser instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpGroupUser as model where model." + propertyName + "= ?";
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

	public List findAll() {
		log.debug("finding all IpGroupUser instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpGroupUser";
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

	public IpGroupUser merge(IpGroupUser detachedInstance) {
		log.debug("merging IpGroupUser instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpGroupUser result = (IpGroupUser) session.merge(detachedInstance);
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

	public void attachDirty(IpGroupUser instance) {
		log.debug("attaching dirty IpGroupUser instance");
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

	public void deleteByGroupId(Long id) {
		log.debug("Deleting Group Users By Id : " + id);
		Session session = getSession();
		try {
			Query query = session.getNamedQuery("deleteGUByGroupId");
			query.setLong("id", id);
			query.executeUpdate();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List fetchByGroupId(Long id) {
		log.debug("Fetching Group Users By Id : " + id);
		Session session = getSession();
		try {
			Query query = session.getNamedQuery("fetchGUByGroupId");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpGroupUser gu = (IpGroupUser) object;
				Hibernate.initialize(gu.getIpGroup());
				Hibernate.initialize(gu.getIpUser());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List fetchByUserId(Long id) {
		log.debug("Fetching Group Users By Id : " + id);
		Session session = getSession();
		try {
			Query query = session.getNamedQuery("fetchGUByUserId");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpGroupUser gu = (IpGroupUser) object;
				Hibernate.initialize(gu.getIpGroup());
				Hibernate.initialize(gu.getIpUser());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}