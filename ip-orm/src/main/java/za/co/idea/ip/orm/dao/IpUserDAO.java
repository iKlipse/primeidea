package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpUser;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpUser entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpUser
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpUserDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpUserDAO.class);
	// property constants
	public static final String USER_FNAME = "userFName";
	public static final String USER_LNAME = "userLName";
	public static final String USER_MNAME = "userMName";
	public static final String USER_ID_NUM = "userIdNum";
	public static final String USER_SCREEN_NAME = "userScreenName";
	public static final String USER_EMAIL = "userEmail";
	public static final String USER_CONTACT = "userContact";
	public static final String USER_SKILLS = "userSkills";
	public static final String USER_BIO = "userBio";
	public static final String USER_FB_HANDLE = "userFbHandle";
	public static final String USER_TW_HANDLE = "userTwHandle";
	public static final String USER_STATUS = "userStatus";

	public void save(IpUser transientInstance) {
		log.debug("saving IpUser instance");
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

	public void delete(IpUser persistentInstance) {
		log.debug("deleting IpUser instance");
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

	public IpUser findById(java.lang.Long id) {
		log.debug("getting IpUser instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpUser instance = (IpUser) session.get("za.co.idea.ip.orm.bean.IpUser", id);
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

	public List findByExample(IpUser instance) {
		log.debug("finding IpUser instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpUser").add(Example.create(instance)).list();
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
		log.debug("finding IpUser instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpUser as model where model." + propertyName + "= ?";
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

	public List findByUserFName(Object userFName) {
		return findByProperty(USER_FNAME, userFName);
	}

	public List findByUserLName(Object userLName) {
		return findByProperty(USER_LNAME, userLName);
	}

	public List findByUserMName(Object userMName) {
		return findByProperty(USER_MNAME, userMName);
	}

	public List findByUserIdNum(Object userIdNum) {
		return findByProperty(USER_ID_NUM, userIdNum);
	}

	public List findByUserScreenName(Object userScreenName) {
		return findByProperty(USER_SCREEN_NAME, userScreenName);
	}

	public List findByUserEmail(Object userEmail) {
		return findByProperty(USER_EMAIL, userEmail);
	}

	public List findByUserContact(Object userContact) {
		return findByProperty(USER_CONTACT, userContact);
	}

	public List findByUserSkills(Object userSkills) {
		return findByProperty(USER_SKILLS, userSkills);
	}

	public List findByUserBio(Object userBio) {
		return findByProperty(USER_BIO, userBio);
	}

	public List findByUserFbHandle(Object userFbHandle) {
		return findByProperty(USER_FB_HANDLE, userFbHandle);
	}

	public List findByUserTwHandle(Object userTwHandle) {
		return findByProperty(USER_TW_HANDLE, userTwHandle);
	}

	public List findByUserStatus(Object userStatus) {
		return findByProperty(USER_STATUS, userStatus);
	}

	public List findAll() {
		log.debug("finding all IpUser instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpUser";
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

	public IpUser merge(IpUser detachedInstance) {
		log.debug("merging IpUser instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpUser result = (IpUser) session.merge(detachedInstance);
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

	public void attachDirty(IpUser instance) {
		log.debug("attaching dirty IpUser instance");
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