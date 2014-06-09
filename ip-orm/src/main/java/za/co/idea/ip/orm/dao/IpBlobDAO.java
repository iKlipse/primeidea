package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.idea.ip.orm.bean.IpBlob;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpBlob entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpBlob
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class IpBlobDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpBlobDAO.class);
	// property constants
	public static final String BLOB_NAME = "blobName";
	public static final String BLOB_CONTENT_TYPE = "blobContentType";
	public static final String BLOB_CONTENT = "blobContent";
	public static final String BLOB_ENTITY_ID = "blobEntityId";
	public static final String BLOB_ENTITY_TBL_NM = "blobEntityTblNm";

	public void save(IpBlob transientInstance) {
		log.debug("saving IpBlob instance");
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

	public void delete(IpBlob persistentInstance) {
		log.debug("deleting IpBlob instance");
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

	public IpBlob findById(java.lang.Long id) {
		log.debug("getting IpBlob instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpBlob instance = (IpBlob) session.get("za.co.idea.ip.orm.bean.IpBlob", id);
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

	public List findByExample(IpBlob instance) {
		log.debug("finding IpBlob instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpBlob").add(Example.create(instance)).list();
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
		log.debug("finding IpBlob instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpBlob as model where model." + propertyName + "= ?";
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

	public List findByBlobName(Object blobName) {
		return findByProperty(BLOB_NAME, blobName);
	}

	public List findByBlobContentType(Object blobContentType) {
		return findByProperty(BLOB_CONTENT_TYPE, blobContentType);
	}

	public List findByBlobContent(Object blobContent) {
		return findByProperty(BLOB_CONTENT, blobContent);
	}

	public List findByBlobEntityId(Object blobEntityId) {
		return findByProperty(BLOB_ENTITY_ID, blobEntityId);
	}

	public List findByBlobEntityTblNm(Object blobEntityTblNm) {
		return findByProperty(BLOB_ENTITY_TBL_NM, blobEntityTblNm);
	}

	public List findAll() {
		log.debug("finding all IpBlob instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpBlob";
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

	public IpBlob merge(IpBlob detachedInstance) {
		log.debug("merging IpBlob instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpBlob result = (IpBlob) session.merge(detachedInstance);
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

	public void attachDirty(IpBlob instance) {
		log.debug("attaching dirty IpBlob instance");
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

	public Long getBlobIdByEntity(long id, String tblNm) {
		log.debug("finding all blobs by entity id");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.getNamedQuery("getBlobIdByEntity");
			query.setLong("id", id);
			query.setString("tblNm", tblNm);
			Long ret = -999l;
			List obj = query.list();
			if (obj != null && obj.size() > 0)
				ret = (Long) obj.get(0);
			transaction.commit();
			session.close();
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public IpBlob getBlobByEntity(long id, String tblNm) {
		log.debug("finding all blobs by entity id");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.getNamedQuery("getBlobByEntity");
			query.setLong("id", id);
			query.setString("tblNm", tblNm);
			IpBlob ret = null;
			List obj = query.list();
			if (obj != null && obj.size() > 0)
				ret = (IpBlob) obj.get(0);
			transaction.commit();
			session.close();
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}
}