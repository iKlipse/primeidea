package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import za.co.idea.ip.orm.bean.IpNotif;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpNotif entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpNotif
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings({ "rawtypes" })
public class IpNotifDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(IpNotifDAO.class);
	// property constants
	public static final String NOTIF_ENTITY_ID = "notifEntityId";
	public static final String NOTIF_ENTITY_TBL_NAME = "notifEntityTblName";
	public static final String NOTIF_SUBJECT = "notifSubject";
	public static final String NOTIF_BODY = "notifBody";
	public static final String NOTIF_ATTACH = "notifAttach";
	public static final String NOTIF_STATUS = "notifStatus";
	public static final String NOTIF_CRTD_DATE = "notifCrtdDate";
	public static final String NOTIF_LIST = "notifList";

	protected void initDao() {
		// do nothing
	}

	public void save(IpNotif transientInstance) {
		log.debug("saving IpNotif instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpNotif persistentInstance) {
		log.debug("deleting IpNotif instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpNotif findById(java.lang.String id) {
		log.debug("getting IpNotif instance with id: " + id);
		try {
			IpNotif instance = (IpNotif) getHibernateTemplate().get("za.co.idea.ip.orm.bean.IpNotif", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IpNotif instance) {
		log.debug("finding IpNotif instance by example");
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
		log.debug("finding IpNotif instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpNotif as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNotifEntityId(Object notifEntityId) {
		return findByProperty(NOTIF_ENTITY_ID, notifEntityId);
	}

	public List findByNotifEntityTblName(Object notifEntityTblName) {
		return findByProperty(NOTIF_ENTITY_TBL_NAME, notifEntityTblName);
	}

	public List findByNotifSubject(Object notifSubject) {
		return findByProperty(NOTIF_SUBJECT, notifSubject);
	}

	public List findByNotifBody(Object notifBody) {
		return findByProperty(NOTIF_BODY, notifBody);
	}

	public List findByNotifAttach(Object notifAttach) {
		return findByProperty(NOTIF_ATTACH, notifAttach);
	}

	public List findByNotifStatus(Object notifStatus) {
		return findByProperty(NOTIF_STATUS, notifStatus);
	}

	public List findByNotifCrtdDate(Object notifCrtdDate) {
		return findByProperty(NOTIF_CRTD_DATE, notifCrtdDate);
	}

	public List findByNotifList(Object notifList) {
		return findByProperty(NOTIF_LIST, notifList);
	}

	public List findAll() {
		log.debug("finding all IpNotif instances");
		try {
			String queryString = "from IpNotif";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpNotif merge(IpNotif detachedInstance) {
		log.debug("merging IpNotif instance");
		try {
			IpNotif result = (IpNotif) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpNotif instance) {
		log.debug("attaching dirty IpNotif instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpNotif instance) {
		log.debug("attaching clean IpNotif instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void deleteByNotifId(String id) {
		log.debug("Deleting Notification By Notif Id : " + id);
		try {
			Query query = getSession().getNamedQuery("deleteNotifById");
			query.setString("id", id);
			query.executeUpdate();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpNotifDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpNotifDAO) ctx.getBean("IpNotifDAO");
	}
}