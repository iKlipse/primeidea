package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpIdeaGroup;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpIdeaGroup entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpIdeaGroup
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpIdeaGroupDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpIdeaGroupDAO.class);

	// property constants

	public void save(IpIdeaGroup transientInstance) {
		log.debug("saving IpIdeaGroup instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(transientInstance);
			transaction.commit();session.close();
			
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			transaction.rollback();session.close();
			
			throw re;
		}
	}

	public void delete(IpIdeaGroup persistentInstance) {
		log.debug("deleting IpIdeaGroup instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(persistentInstance);
			transaction.commit();session.close();
			
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			transaction.rollback();session.close();
			
			throw re;
		}
	}

	public IpIdeaGroup findById(java.lang.Long id) {
		log.debug("getting IpIdeaGroup instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpIdeaGroup instance = (IpIdeaGroup) session.get("za.co.idea.ip.orm.bean.IpIdeaGroup", id);
			transaction.commit();session.close();
			
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			transaction.rollback();session.close();
			
			throw re;
		}
	}

	public List findByExample(IpIdeaGroup instance) {
		log.debug("finding IpIdeaGroup instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpIdeaGroup").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			transaction.commit();session.close();
			
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			transaction.rollback();session.close();
			
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpIdeaGroup instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpIdeaGroup as model where model." + propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			List results = queryObject.list();
			transaction.commit();session.close();
			
			return results;

		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			transaction.rollback();session.close();
			
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all IpIdeaGroup instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpIdeaGroup";
			Query queryObject = session.createQuery(queryString);
			List results = queryObject.list();
			transaction.commit();session.close();
			
			return results;

		} catch (RuntimeException re) {
			log.error("find all failed", re);
			transaction.rollback();session.close();
			
			throw re;
		}
	}

	public IpIdeaGroup merge(IpIdeaGroup detachedInstance) {
		log.debug("merging IpIdeaGroup instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpIdeaGroup result = (IpIdeaGroup) session.merge(detachedInstance);
			log.debug("merge successful");
			transaction.commit();session.close();
			
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			transaction.rollback();session.close();
			
			throw re;
		}
	}

	public void attachDirty(IpIdeaGroup instance) {
		log.debug("attaching dirty IpIdeaGroup instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(instance);
			transaction.commit();session.close();
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			transaction.rollback();session.close();
			
			throw re;
		}
	}

	public void deleteByIdeaId(Long id) {
		log.debug("Deleting Idea Groups By Id : " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.getNamedQuery("deleteIGByIdeaId");
			query.setLong("id", id);
			query.executeUpdate();
			transaction.commit();session.close();
			
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			transaction.rollback();session.close();
			throw re;
		}
	}

	public List fetchByIdeaId(Long id) {
		log.debug("Fetching Group Users By Id : " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.getNamedQuery("fetchIGByIdeaId");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpIdeaGroup fg = (IpIdeaGroup) object;
				Hibernate.initialize(fg.getIpGroup());
				Hibernate.initialize(fg.getIpIdea());
			}
			transaction.commit();session.close();
			
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			transaction.rollback();session.close();
			throw re;
		}
	}

	public List fetchByGroupId(Long id) {
		log.debug("Fetching Group Users By Id : " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.getNamedQuery("fetchIGByGroupId");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpIdeaGroup fg = (IpIdeaGroup) object;
				Hibernate.initialize(fg.getIpGroup());
				Hibernate.initialize(fg.getIpIdea());
			}
			transaction.commit();session.close();
			
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			transaction.rollback();session.close();
			throw re;
		}
	}
}