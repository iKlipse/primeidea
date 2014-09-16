package za.co.idea.ip.orm.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import za.co.idea.ip.orm.bean.IpNotifGroup;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpNotifGroup entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpNotifGroup
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpNotifGroupDAO {
	private static final Logger log = Logger.getLogger(IpNotifGroupDAO.class);
	// property constants
	public static final String ING_NOTIF_ID = "ingNotifId";
	public static final String ING_GRP_ID = "ingGrpId";

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

	public void save(IpNotifGroup transientInstance) {
		log.debug("saving IpNotifGroup instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpNotifGroup persistentInstance) {
		log.debug("deleting IpNotifGroup instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpNotifGroup findById(java.lang.Long id) {
		log.debug("getting IpNotifGroup instance with id: " + id);
		try {
			IpNotifGroup instance = (IpNotifGroup) getCurrentSession().get("za.co.idea.ip.orm.bean.IpNotifGroup", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpNotifGroup> findByExample(IpNotifGroup instance) {
		log.debug("finding IpNotifGroup instance by example");
		try {
			List<IpNotifGroup> results = (List<IpNotifGroup>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpNotifGroup").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpNotifGroup instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpNotifGroup as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpNotifGroup> findByIngNotifId(Object ingNotifId) {
		return findByProperty(ING_NOTIF_ID, ingNotifId);
	}

	public List<IpNotifGroup> findByIngGrpId(Object ingGrpId) {
		return findByProperty(ING_GRP_ID, ingGrpId);
	}

	public List findAll() {
		log.debug("finding all IpNotifGroup instances");
		try {
			String queryString = "from IpNotifGroup";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpNotifGroup merge(IpNotifGroup detachedInstance) {
		log.debug("merging IpNotifGroup instance");
		try {
			IpNotifGroup result = (IpNotifGroup) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpNotifGroup instance) {
		log.debug("attaching dirty IpNotifGroup instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpNotifGroup instance) {
		log.debug("attaching clean IpNotifGroup instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void deleteByNotifId(String id) {
		log.debug("Deleting Notification Groups By Notif Id : " + id);
		try {
			Query query = getCurrentSession().getNamedQuery("deleteNGByNotifId");
			query.setString("id", id);
			query.executeUpdate();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List fetchByNotifId(String id) {
		log.debug("Fetching Notification Group Users By Notif Id : " + id);
		try {
			Query query = getCurrentSession().getNamedQuery("fetchNGByNotifId");
			query.setString("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpNotifGroupDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpNotifGroupDAO) ctx.getBean("IpNotifGroupDAO");
	}
}