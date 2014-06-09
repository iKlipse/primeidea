package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpKeys;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpKeys entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpKeys
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpKeysDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpKeysDAO.class);
	// property constants
	public static final String TABLE_NM = "tableNm";
	public static final String VAL = "val";

	public void save(IpKeys transientInstance) {
		log.debug("saving IpKeys instance");
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

	public void delete(IpKeys persistentInstance) {
		log.debug("deleting IpKeys instance");
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

	public IpKeys findById(java.lang.Integer id) {
		log.debug("getting IpKeys instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpKeys instance = (IpKeys) session.get("za.co.idea.ip.orm.bean.IpKeys", id);
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

	public List findByExample(IpKeys instance) {
		log.debug("finding IpKeys instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpKeys").add(Example.create(instance)).list();
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
		log.debug("finding IpKeys instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpKeys as model where model." + propertyName + "= ?";
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

	public List findByTableNm(Object tableNm) {
		return findByProperty(TABLE_NM, tableNm);
	}

	public List findByVal(Object val) {
		return findByProperty(VAL, val);
	}

	public List findAll() {
		log.debug("finding all IpKeys instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpKeys";
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

	public IpKeys merge(IpKeys detachedInstance) {
		log.debug("merging IpKeys instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpKeys result = (IpKeys) session.merge(detachedInstance);
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

	public void attachDirty(IpKeys instance) {
		log.debug("attaching dirty IpKeys instance");
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

}