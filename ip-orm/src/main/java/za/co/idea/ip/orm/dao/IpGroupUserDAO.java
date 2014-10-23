package za.co.idea.ip.orm.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.idea.ip.orm.bean.IpGroupUser;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpGroupUser entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpGroupUser
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpGroupUserDAO {
	private static final Logger log = Logger.getLogger(IpGroupUserDAO.class);
	// property constants

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(IpGroupUser transientInstance) {
		log.debug("saving IpGroupUser instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpGroupUser persistentInstance) {
		log.debug("deleting IpGroupUser instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpGroupUser findById(java.lang.Long id) {
		log.debug("getting IpGroupUser instance with id: " + id);
		try {
			IpGroupUser instance = (IpGroupUser) getCurrentSession().get("za.co.idea.ip.orm.bean.IpGroupUser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpGroupUser> findByExample(IpGroupUser instance) {
		log.debug("finding IpGroupUser instance by example");
		try {
			List<IpGroupUser> results = (List<IpGroupUser>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpGroupUser").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpGroupUser instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpGroupUser as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all IpGroupUser instances");
		try {
			String queryString = "from IpGroupUser";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpGroupUser merge(IpGroupUser detachedInstance) {
		log.debug("merging IpGroupUser instance");
		try {
			IpGroupUser result = (IpGroupUser) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpGroupUser instance) {
		log.debug("attaching dirty IpGroupUser instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpGroupUser instance) {
		log.debug("attaching clean IpGroupUser instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteByGroupId(Long id) {
		log.debug("Deleting Group Users By Id : " + id);
		try {
			Query query = getCurrentSession().getNamedQuery("deleteGUByGroupId");
			query.setLong("id", id);
			query.executeUpdate();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List fetchByGroupId(Long id) {
		try {
			Query query = getCurrentSession().getNamedQuery("fetchGUByGroupId");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpGroupUser gu = (IpGroupUser) object;
				Hibernate.initialize(gu.getIpGroup());
				Hibernate.initialize(gu.getIpUser());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List fetchByUserId(Long id) {
		try {
			Query query = getCurrentSession().getNamedQuery("fetchGUByUserId");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpGroupUser gu = (IpGroupUser) object;
				Hibernate.initialize(gu.getIpGroup());
				Hibernate.initialize(gu.getIpUser());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpGroupUserDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpGroupUserDAO) ctx.getBean("IpGroupUserDAO");
	}
}