package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpChallenge;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpChallenge entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpChallenge
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpChallengeDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpChallengeDAO.class);
	// property constants
	public static final String CHAL_TITLE = "chalTitle";
	public static final String CHAL_DESC = "chalDesc";
	public static final String CHAL_HOVER_TXT = "chalHoverTxt";
	public static final String CHAL_TAGS = "chalTags";

	public void save(IpChallenge transientInstance) {
		log.debug("saving IpChallenge instance");
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

	public void delete(IpChallenge persistentInstance) {
		log.debug("deleting IpChallenge instance");
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

	public IpChallenge findById(java.lang.Long id) {
		log.debug("getting IpChallenge instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpChallenge instance = (IpChallenge) session.get("za.co.idea.ip.orm.bean.IpChallenge", id);
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

	public List findByExample(IpChallenge instance) {
		log.debug("finding IpChallenge instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpChallenge").add(Example.create(instance)).list();
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
		log.debug("finding IpChallenge instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpChallenge as model where model." + propertyName + "= ?";
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

	public List findByChalTitle(Object chalTitle) {
		return findByProperty(CHAL_TITLE, chalTitle);
	}

	public List findByChalDesc(Object chalDesc) {
		return findByProperty(CHAL_DESC, chalDesc);
	}

	public List findByChalHoverTxt(Object chalHoverTxt) {
		return findByProperty(CHAL_HOVER_TXT, chalHoverTxt);
	}

	public List findByChalTags(Object chalTags) {
		return findByProperty(CHAL_TAGS, chalTags);
	}

	public List findAll() {
		log.debug("finding all IpChallenge instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpChallenge";
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

	public IpChallenge merge(IpChallenge detachedInstance) {
		log.debug("merging IpChallenge instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpChallenge result = (IpChallenge) session.merge(detachedInstance);
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

	public void attachDirty(IpChallenge instance) {
		log.debug("attaching dirty IpChallenge instance");
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
		log.debug("Fetching Challenge by Query :: getChallengeByUser");
		Session session = getSession();
		try {
			Query query = session.getNamedQuery("getChallengeByUser");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpChallenge chal = (IpChallenge) object;
				Hibernate.initialize(chal.getIpChallengeCat());
				Hibernate.initialize(chal.getIpChallengeStatus());
				Hibernate.initialize(chal.getIpUser());
			}
			session.close();
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByStatusId(Integer id) {
		log.debug("Fetching Challenge by Query :: getChallengeByStatus");
		Session session = getSession();
		try {
			Query query = session.getNamedQuery("getChallengeByStatus");
			query.setInteger("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpChallenge chal = (IpChallenge) object;
				Hibernate.initialize(chal.getIpChallengeCat());
				Hibernate.initialize(chal.getIpChallengeStatus());
				Hibernate.initialize(chal.getIpUser());
			}
			session.close();
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}