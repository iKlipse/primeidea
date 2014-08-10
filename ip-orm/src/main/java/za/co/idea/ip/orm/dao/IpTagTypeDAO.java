package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpTagType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpTagType entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpTagType
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpTagTypeDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpTagTypeDAO.class);
	// property constants
	public static final String TT_DESC = "ttDesc";

	public void save(IpTagType transientInstance) {
		log.debug("saving IpTagType instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(transientInstance);
			transaction.commit();
			

			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			if (transaction.isActive())
				transaction.rollback();
			
				

			throw re;
		}
	}

	public void delete(IpTagType persistentInstance) {
		log.debug("deleting IpTagType instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(persistentInstance);
			transaction.commit();
			

			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			if (transaction.isActive())
				transaction.rollback();
			
				

			throw re;
		}
	}

	public IpTagType findById(java.lang.Integer id) {
		log.debug("getting IpTagType instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpTagType instance = (IpTagType) session.get("za.co.idea.ip.orm.bean.IpTagType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			transaction.commit();
			

			if (transaction.isActive())
				transaction.rollback();
			
				

			throw re;
		}
	}

	public List findByExample(IpTagType instance) {
		log.debug("finding IpTagType instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpTagType").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			transaction.commit();
			

			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			if (transaction.isActive())
				transaction.rollback();
			
				

			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpTagType instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpTagType as model where model." + propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			List results = queryObject.list();
			transaction.commit();
			

			return results;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			if (transaction.isActive())
				transaction.rollback();
			
				

			throw re;
		}
	}

	public List findByTtDesc(Object ttDesc) {
		return findByProperty(TT_DESC, ttDesc);
	}

	public List findAll() {
		log.debug("finding all IpTagType instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpTagType";
			Query queryObject = session.createQuery(queryString);
			List results = queryObject.list();
			transaction.commit();
			

			return results;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			if (transaction.isActive())
				transaction.rollback();
			
				

			throw re;
		}
	}

	public IpTagType merge(IpTagType detachedInstance) {
		log.debug("merging IpTagType instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpTagType result = (IpTagType) session.merge(detachedInstance);
			log.debug("merge successful");
			transaction.commit();
			

			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			if (transaction.isActive())
				transaction.rollback();
			
				

			throw re;
		}
	}

	public void attachDirty(IpTagType instance) {
		log.debug("attaching dirty IpTagType instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(instance);
			transaction.commit();
			

			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			if (transaction.isActive())
				transaction.rollback();
			
				

			throw re;
		}
	}
}