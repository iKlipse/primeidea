package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpChallengeGroup;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpChallengeGroup entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpChallengeGroup
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpChallengeGroupDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpChallengeGroupDAO.class);

	// property constants

	public void save(IpChallengeGroup transientInstance) {
		log.debug("saving IpChallengeGroup instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(transientInstance);
			transaction.commit();
			session.close();

			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			if (transaction.isActive())
				transaction.rollback();
			if (session.isOpen())
				session.close();

			throw re;
		}
	}

	public void delete(IpChallengeGroup persistentInstance) {
		log.debug("deleting IpChallengeGroup instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(persistentInstance);
			transaction.commit();
			session.close();

			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			if (transaction.isActive())
				transaction.rollback();
			if (session.isOpen())
				session.close();

			throw re;
		}
	}

	public IpChallengeGroup findById(java.lang.Long id) {
		log.debug("getting IpChallengeGroup instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpChallengeGroup instance = (IpChallengeGroup) session.get("za.co.idea.ip.orm.bean.IpChallengeGroup", id);
			transaction.commit();
			session.close();

			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			if (transaction.isActive())
				transaction.rollback();
			if (session.isOpen())
				session.close();

			throw re;
		}
	}

	public List findByExample(IpChallengeGroup instance) {
		log.debug("finding IpChallengeGroup instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpChallengeGroup").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			transaction.commit();
			session.close();

			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			if (transaction.isActive())
				transaction.rollback();
			if (session.isOpen())
				session.close();

			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpChallengeGroup instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpChallengeGroup as model where model." + propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			List results = queryObject.list();
			transaction.commit();
			session.close();

			return results;

		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			if (transaction.isActive())
				transaction.rollback();
			if (session.isOpen())
				session.close();

			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all IpChallengeGroup instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpChallengeGroup";
			Query queryObject = session.createQuery(queryString);
			List results = queryObject.list();
			transaction.commit();
			session.close();

			return results;

		} catch (RuntimeException re) {
			log.error("find all failed", re);
			if (transaction.isActive())
				transaction.rollback();
			if (session.isOpen())
				session.close();

			throw re;
		}
	}

	public IpChallengeGroup merge(IpChallengeGroup detachedInstance) {
		log.debug("merging IpChallengeGroup instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpChallengeGroup result = (IpChallengeGroup) session.merge(detachedInstance);
			log.debug("merge successful");
			transaction.commit();
			session.close();

			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			if (transaction.isActive())
				transaction.rollback();
			if (session.isOpen())
				session.close();

			throw re;
		}
	}

	public void attachDirty(IpChallengeGroup instance) {
		log.debug("attaching dirty IpChallengeGroup instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(instance);
			transaction.commit();
			session.close();

			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			if (transaction.isActive())
				transaction.rollback();
			if (session.isOpen())
				session.close();

			throw re;
		}
	}

	public void deleteByChallengeId(Long id) {
		log.debug("Deleting Challenge Groups By Id : " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.getNamedQuery("deleteCGByChalId");
			query.setLong("id", id);
			query.executeUpdate();
			transaction.commit();
			session.close();

		} catch (RuntimeException re) {
			log.error("attach failed", re);
			if (transaction.isActive())
				transaction.rollback();
			if (session.isOpen())
				session.close();
			throw re;
		}
	}

	public List fetchByChallengeId(Long id) {
		log.debug("Fetching Group Users By Id : " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.getNamedQuery("fetchCGByChalId");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpChallengeGroup fg = (IpChallengeGroup) object;
				Hibernate.initialize(fg.getIpGroup());
				Hibernate.initialize(fg.getIpChallenge());
			}
			transaction.commit();
			session.close();

			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			if (transaction.isActive())
				transaction.rollback();
			if (session.isOpen())
				session.close();
			throw re;
		}
	}

	public List fetchByGroupId(Long id) {
		log.debug("Fetching Group Users By Id : " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.getNamedQuery("fetchCGByGroupId");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpChallengeGroup fg = (IpChallengeGroup) object;
				Hibernate.initialize(fg.getIpGroup());
				Hibernate.initialize(fg.getIpChallenge());
			}
			transaction.commit();
			session.close();

			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			if (transaction.isActive())
				transaction.rollback();
			if (session.isOpen())
				session.close();
			throw re;
		}
	}
}