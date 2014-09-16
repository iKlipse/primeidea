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
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpNotifDAO {
	private static final Logger log = Logger.getLogger(IpNotifDAO.class);
	// property constants
	public static final String NOTIF_ENTITY_ID = "notifEntityId";
	public static final String NOTIF_ENTITY_TBL_NAME = "notifEntityTblName";
	public static final String NOTIF_SUBJECT = "notifSubject";
	public static final String NOTIF_BODY = "notifBody";
	public static final String NOTIF_ATTACH = "notifAttach";
	public static final String NOTIF_STATUS = "notifStatus";
	public static final String NOTIF_LIST = "notifList";

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

	public void save(IpNotif transientInstance) {
		log.debug("saving IpNotif instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpNotif persistentInstance) {
		log.debug("deleting IpNotif instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpNotif findById(java.lang.String id) {
		log.debug("getting IpNotif instance with id: " + id);
		try {
			IpNotif instance = (IpNotif) getCurrentSession().get("za.co.idea.ip.orm.bean.IpNotif", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpNotif> findByExample(IpNotif instance) {
		log.debug("finding IpNotif instance by example");
		try {
			List<IpNotif> results = (List<IpNotif>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpNotif").add(create(instance)).list();
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
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpNotif> findByNotifEntityId(Object notifEntityId) {
		return findByProperty(NOTIF_ENTITY_ID, notifEntityId);
	}

	public List<IpNotif> findByNotifEntityTblName(Object notifEntityTblName) {
		return findByProperty(NOTIF_ENTITY_TBL_NAME, notifEntityTblName);
	}

	public List<IpNotif> findByNotifSubject(Object notifSubject) {
		return findByProperty(NOTIF_SUBJECT, notifSubject);
	}

	public List<IpNotif> findByNotifBody(Object notifBody) {
		return findByProperty(NOTIF_BODY, notifBody);
	}

	public List<IpNotif> findByNotifAttach(Object notifAttach) {
		return findByProperty(NOTIF_ATTACH, notifAttach);
	}

	public List<IpNotif> findByNotifStatus(Object notifStatus) {
		return findByProperty(NOTIF_STATUS, notifStatus);
	}

	public List<IpNotif> findByNotifList(Object notifList) {
		return findByProperty(NOTIF_LIST, notifList);
	}

	public List findAll() {
		log.debug("finding all IpNotif instances");
		try {
			String queryString = "from IpNotif";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpNotif merge(IpNotif detachedInstance) {
		log.debug("merging IpNotif instance");
		try {
			IpNotif result = (IpNotif) getCurrentSession().merge(detachedInstance);
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
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpNotif instance) {
		log.debug("attaching clean IpNotif instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void deleteByNotifId(String id) {
		log.debug("Deleting Notification By Notif Id : " + id);
		try {
			Query query = getCurrentSession().getNamedQuery("deleteNotifById");
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