package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpSecqList;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpSecqList entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpSecqList
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpSecqListDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpSecqListDAO.class);
	// property constants
	public static final String ISL_DESC = "islDesc";

	public void save(IpSecqList transientInstance) {
		log.debug("saving IpSecqList instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(transientInstance);
			transaction.commit();session.close();
			

			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			if (transaction.isActive())
				transaction.rollback();session.close();
			
				

			throw re;
		}
	}

	public void delete(IpSecqList persistentInstance) {
		log.debug("deleting IpSecqList instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(persistentInstance);
			transaction.commit();session.close();
			

			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			if (transaction.isActive())
				transaction.rollback();session.close();
			
				

			throw re;
		}
	}

	public IpSecqList findById(java.lang.Integer id) {
		log.debug("getting IpSecqList instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpSecqList instance = (IpSecqList) session.get("za.co.idea.ip.orm.bean.IpSecqList", id);
			transaction.commit();session.close();
			

			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			if (transaction.isActive())
				transaction.rollback();session.close();
			
				

			throw re;
		}
	}

	public List findByExample(IpSecqList instance) {
		log.debug("finding IpSecqList instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpSecqList").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			transaction.commit();session.close();
			

			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			if (transaction.isActive())
				transaction.rollback();session.close();
			
				

			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpSecqList instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpSecqList as model where model." + propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			List results = queryObject.list();
			transaction.commit();session.close();
			

			return results;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			if (transaction.isActive())
				transaction.rollback();session.close();
			
				

			throw re;
		}
	}

	public List findByIslDesc(Object islDesc) {
		return findByProperty(ISL_DESC, islDesc);
	}

	public List findAll() {
		log.debug("finding all IpSecqList instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpSecqList isl order by isl.islDesc asc";
			Query queryObject = session.createQuery(queryString);
			List results = queryObject.list();
			transaction.commit();session.close();
			
			return results;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			if (transaction.isActive())
				transaction.rollback();session.close();
			
				

			throw re;
		}
	}

	public IpSecqList merge(IpSecqList detachedInstance) {
		log.debug("merging IpSecqList instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpSecqList result = (IpSecqList) session.merge(detachedInstance);
			transaction.commit();session.close();
			

			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			if (transaction.isActive())
				transaction.rollback();session.close();
			
				

			throw re;
		}
	}

	public void attachDirty(IpSecqList instance) {
		log.debug("attaching dirty IpSecqList instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(instance);
			transaction.commit();session.close();
			

			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			if (transaction.isActive())
				transaction.rollback();session.close();
			
				

			throw re;
		}
	}
}