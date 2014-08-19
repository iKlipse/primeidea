package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

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
@SuppressWarnings({ "rawtypes" })
public class IpLoginDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(IpLoginDAO.class);
	// property constants
	public static final String LOGIN_NAME = "loginName";
	public static final String LOGIN_PWD = "loginPwd";
	public static final String LOGIN_SEC_A = "loginSecA";

	protected void initDao() {
		// do nothing
	}

	public void save(IpLogin transientInstance) {
		log.debug("saving IpLogin instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpLogin persistentInstance) {
		log.debug("deleting IpLogin instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpLogin findById(java.lang.Long id) {
		log.debug("getting IpLogin instance with id: " + id);
		try {
			IpLogin instance = (IpLogin) getHibernateTemplate().get("za.co.idea.ip.orm.bean.IpLogin", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IpLogin instance) {
		log.debug("finding IpLogin instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpLogin instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpLogin as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByLoginName(Object loginName) {
		return findByProperty(LOGIN_NAME, loginName);
	}

	public List findByLoginPwd(Object loginPwd) {
		return findByProperty(LOGIN_PWD, loginPwd);
	}

	public List findByLoginSecA(Object loginSecA) {
		return findByProperty(LOGIN_SEC_A, loginSecA);
	}

	public List findAll() {
		log.debug("finding all IpLogin instances");
		try {
			String queryString = "from IpLogin";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpLogin merge(IpLogin detachedInstance) {
		log.debug("merging IpLogin instance");
		try {
			IpLogin result = (IpLogin) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpLogin instance) {
		log.debug("attaching dirty IpLogin instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpLogin instance) {
		log.debug("attaching clean IpLogin instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List verifyLogin(String login, String pwd) {
		log.debug("Fetching Login By Query :: verify Login");
		try {
			Query query = getSession().getNamedQuery("verifyLogin");
			query.setString("login", login);
			query.setString("pwd", pwd);
			List ret = query.list();
			for (Object object : ret) {
				IpLogin loginObj = (IpLogin) object;
				Hibernate.initialize(loginObj.getIpUser());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List fetchLogin(String login) {
		log.debug("Fetching Login By Query :: fetch Login");
		try {
			Query query = getSession().getNamedQuery("fetchLogin");
			query.setString("login", login);
			List ret = query.list();
			for (Object object : ret) {
				IpLogin loginObj = (IpLogin) object;
				Hibernate.initialize(loginObj.getIpUser());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public IpLogin fetchLoginById(Long login) {
		log.debug("Fetching Login By Query :: fetch Login by id");
		try {
			Query query = getSession().getNamedQuery("fetchLoginById");
			query.setLong("login", login);
			List ret = query.list();
			for (Object object : ret) {
				IpLogin loginObj = (IpLogin) object;
				Hibernate.initialize(loginObj.getIpUser());
			}
			return (IpLogin) ret.get(0);
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void updatePassword(String login, String pwd) {
		log.debug("Updating password");
		try {
			Query query = getSession().getNamedQuery("updatePassword");
			query.setString("login", login);
			query.setString("pwd", pwd);
			query.executeUpdate();

		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void updateSecurity(String login, Integer secq, String seca) {
		log.debug("Updating security");
		try {
			Query query = getSession().getNamedQuery("updateSecurity");
			query.setString("login", login);
			query.setInteger("secq", secq);
			query.setString("seca", seca);
			query.executeUpdate();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpLoginDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpLoginDAO) ctx.getBean("IpLoginDAO");
	}
}