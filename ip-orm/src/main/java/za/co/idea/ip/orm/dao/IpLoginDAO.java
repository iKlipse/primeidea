package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpLogin;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpLogin entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpLogin
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpLoginDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpLoginDAO.class);
	// property constants
	public static final String LOGIN_NAME = "loginName";
	public static final String LOGIN_PWD = "loginPwd";
	public static final String LOGIN_SEC_Q = "loginSecQ";
	public static final String LOGIN_SEC_A = "loginSecA";

	public void save(IpLogin transientInstance) {
		log.debug("saving IpLogin instance");
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

	public void delete(IpLogin persistentInstance) {
		log.debug("deleting IpLogin instance");
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

	public IpLogin findById(java.lang.Long id) {
		log.debug("getting IpLogin instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpLogin instance = (IpLogin) session.get("za.co.idea.ip.orm.bean.IpLogin", id);
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

	public List findByExample(IpLogin instance) {
		log.debug("finding IpLogin instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpLogin").add(Example.create(instance)).list();
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
		log.debug("finding IpLogin instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpLogin as model where model." + propertyName + "= ?";
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

	public List findByLoginName(Object loginName) {
		return findByProperty(LOGIN_NAME, loginName);
	}

	public List findByLoginPwd(Object loginPwd) {
		return findByProperty(LOGIN_PWD, loginPwd);
	}

	public List findByLoginSecQ(Object loginSecQ) {
		return findByProperty(LOGIN_SEC_Q, loginSecQ);
	}

	public List findByLoginSecA(Object loginSecA) {
		return findByProperty(LOGIN_SEC_A, loginSecA);
	}

	public List findAll() {
		log.debug("finding all IpLogin instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpLogin";
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

	public IpLogin merge(IpLogin detachedInstance) {
		log.debug("merging IpLogin instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpLogin result = (IpLogin) session.merge(detachedInstance);
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

	public void attachDirty(IpLogin instance) {
		log.debug("attaching dirty IpLogin instance");
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

	public List verifyLogin(String login, String pwd) {
		log.debug("Fetching Login By Query :: verify Login");
		Session session = getSession();
		try {
			Query query = session.getNamedQuery("verifyLogin");
			query.setString("login", login);
			query.setString("pwd", pwd);
			List ret = query.list();
			for (Object object : ret) {
				IpLogin loginObj = (IpLogin) object;
				Hibernate.initialize(loginObj.getIpUser());
			}
			session.close();
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List fetchLogin(String login) {
		log.debug("Fetching Login By Query :: fetch Login");
		Session session = getSession();
		try {
			Query query = session.getNamedQuery("fetchLogin");
			query.setString("login", login);
			List ret = query.list();
			for (Object object : ret) {
				IpLogin loginObj = (IpLogin) object;
				Hibernate.initialize(loginObj.getIpUser());
			}
			session.close();
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void updatePassword(String login, String pwd) {
		log.debug("Updating password");
		Session session = getSession();
		try {
			Query query = session.getNamedQuery("updatePassword");
			query.setString("login", login);
			query.setString("pwd", pwd);
			query.executeUpdate();
			session.close();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void updateSecurity(String login, String secq, String seca) {
		log.debug("Updating security");
		Session session = getSession();
		try {
			Query query = session.getNamedQuery("updateSecurity");
			query.setString("login", login);
			query.setString("secq", secq);
			query.setString("seca", seca);
			query.executeUpdate();
			session.close();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}