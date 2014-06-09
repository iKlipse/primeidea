package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpFuncGroup;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpFuncGroup entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpFuncGroup
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpFuncGroupDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpFuncGroupDAO.class);

	// property constants

	public void save(IpFuncGroup transientInstance) {
		log.debug("saving IpFuncGroup instance");
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

	public void delete(IpFuncGroup persistentInstance) {
		log.debug("deleting IpFuncGroup instance");
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

	public IpFuncGroup findById(java.lang.Long id) {
		log.debug("getting IpFuncGroup instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpFuncGroup instance = (IpFuncGroup) session.get("za.co.idea.ip.orm.bean.IpFuncGroup", id);
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

	public List findByExample(IpFuncGroup instance) {
		log.debug("finding IpFuncGroup instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpFuncGroup").add(Example.create(instance)).list();
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
		log.debug("finding IpFuncGroup instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpFuncGroup as model where model." + propertyName + "= ?";
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
		log.debug("finding all IpFuncGroup instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpFuncGroup";
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

	public IpFuncGroup merge(IpFuncGroup detachedInstance) {
		log.debug("merging IpFuncGroup instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpFuncGroup result = (IpFuncGroup) session.merge(detachedInstance);
			log.debug("merge successful");
			transaction.commit();
			session.close();
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public void attachDirty(IpFuncGroup instance) {
		log.debug("attaching dirty IpFuncGroup instance");
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

	public void deleteByFunctionId(Long id) {
		log.debug("Deleting Function Groups By Id : " + id);
		Session session = getSession();
		try {
			Query query = session.getNamedQuery("deleteFGByFuncId");
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
			Query query = session.getNamedQuery("fetchFGByFuncId");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpFuncGroup fg = (IpFuncGroup) object;
				Hibernate.initialize(fg.getIpGroup());
				Hibernate.initialize(fg.getIpFunction());
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
			Query query = session.getNamedQuery("fetchFGByGroupId");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpFuncGroup fg = (IpFuncGroup) object;
				Hibernate.initialize(fg.getIpGroup());
				Hibernate.initialize(fg.getIpFunction());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}