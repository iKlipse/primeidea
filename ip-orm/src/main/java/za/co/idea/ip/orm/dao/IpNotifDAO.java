package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
@SuppressWarnings("rawtypes")
public class IpNotifDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpNotifDAO.class);
	// property constants
	public static final String NOTIF_ENTITY_ID = "notifEntityId";
	public static final String NOTIF_ENTITY_TBL_NAME = "notifEntityTblName";
	public static final String NOTIF_SUBJECT = "notifSubject";
	public static final String NOTIF_BODY = "notifBody";
	public static final String NOTIF_ATTACH_ID = "notifAttachId";
	public static final String NOTIF_STATUS = "notifStatus";
	public static final String NOTIF_CRTD_DATE = "notifCrtdDate";

	public void save(IpNotif transientInstance) {
		log.debug("saving IpNotif instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(transientInstance);
			transaction.commit();session.close();
			
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			transaction.rollback();session.close();
			
			throw re;
		}
	}

	public void delete(IpNotif persistentInstance) {
		log.debug("deleting IpNotif instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(persistentInstance);
			transaction.commit();session.close();
			
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			transaction.rollback();session.close();
			
			throw re;
		}
	}

	public IpNotif findById(java.lang.String id) {
		log.debug("getting IpNotif instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpNotif instance = (IpNotif) session.get("za.co.idea.ip.orm.bean.IpNotif", id);
			transaction.commit();session.close();
			
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			transaction.rollback();session.close();
			
			throw re;
		}
	}

	public List findByExample(IpNotif instance) {
		log.debug("finding IpNotif instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpNotif").add(Example.create(instance)).list();
			transaction.commit();session.close();
			
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			transaction.rollback();session.close();
			
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpNotif instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpNotif as model where model." + propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			List results = queryObject.list();
			transaction.commit();session.close();
			
			return results;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			transaction.rollback();session.close();
			
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

	public List findByNotifAttachId(Object notifAttachId) {
		return findByProperty(NOTIF_ATTACH_ID, notifAttachId);
	}

	public List findByNotifStatus(Object notifStatus) {
		return findByProperty(NOTIF_STATUS, notifStatus);
	}

	public List findByNotifCrtdDate(Object notifCrtdDate) {
		return findByProperty(NOTIF_CRTD_DATE, notifCrtdDate);
	}

	public List findAll() {
		log.debug("finding all IpNotif instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpNotif";
			Query queryObject = session.createQuery(queryString);
			List results = queryObject.list();
			transaction.commit();session.close();
			
			return results;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			transaction.rollback();session.close();
			
			throw re;
		}

	}

	public IpNotif merge(IpNotif detachedInstance) {
		log.debug("merging IpNotif instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpNotif result = (IpNotif) session.merge(detachedInstance);
			transaction.commit();session.close();
			
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			transaction.rollback();session.close();
			
			throw re;
		}
	}

	public void attachDirty(IpNotif instance) {
		log.debug("attaching dirty IpNotif instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(instance);
			transaction.commit();session.close();
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			transaction.rollback();session.close();
			
			throw re;
		}
	}
	public void deleteByNotifId(String id) {
		log.debug("Deleting Notification By Notif Id : " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.getNamedQuery("deleteNotifById");
			query.setString("id", id);
			query.executeUpdate();
			transaction.commit();session.close();
			
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			transaction.rollback();session.close();
			throw re;
		}
	}

}