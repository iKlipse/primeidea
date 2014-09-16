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
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpBlobDAO {
	private static final Logger log = Logger.getLogger(IpBlobDAO.class);
	// property constants
	public static final String BLOB_NAME = "blobName";
	public static final String BLOB_CONTENT_TYPE = "blobContentType";
	public static final String BLOB_CONTENT = "blobContent";
	public static final String BLOB_ENTITY_ID = "blobEntityId";
	public static final String BLOB_ENTITY_TBL_NM = "blobEntityTblNm";
	public static final String BLOB_SIZE = "blobSize";

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

	public void save(IpBlob transientInstance) {
		log.debug("saving IpBlob instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpBlob persistentInstance) {
		log.debug("deleting IpBlob instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpBlob findById(java.lang.Long id) {
		log.debug("getting IpBlob instance with id: " + id);
		try {
			IpBlob instance = (IpBlob) getCurrentSession().get("za.co.idea.ip.orm.bean.IpBlob", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpBlob> findByExample(IpBlob instance) {
		log.debug("finding IpBlob instance by example");
		try {
			List<IpBlob> results = (List<IpBlob>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpBlob").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpBlob instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpBlob as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpBlob> findByBlobName(Object blobName) {
		return findByProperty(BLOB_NAME, blobName);
	}

	public List<IpBlob> findByBlobContentType(Object blobContentType) {
		return findByProperty(BLOB_CONTENT_TYPE, blobContentType);
	}

	public List<IpBlob> findByBlobContent(Object blobContent) {
		return findByProperty(BLOB_CONTENT, blobContent);
	}

	public List<IpBlob> findByBlobEntityId(Object blobEntityId) {
		return findByProperty(BLOB_ENTITY_ID, blobEntityId);
	}

	public List<IpBlob> findByBlobEntityTblNm(Object blobEntityTblNm) {
		return findByProperty(BLOB_ENTITY_TBL_NM, blobEntityTblNm);
	}

	public List<IpBlob> findByBlobSize(Object blobSize) {
		return findByProperty(BLOB_SIZE, blobSize);
	}

	public List findAll() {
		log.debug("finding all IpBlob instances");
		try {
			String queryString = "from IpBlob";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpBlob merge(IpBlob detachedInstance) {
		log.debug("merging IpBlob instance");
		try {
			IpBlob result = (IpBlob) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpBlob instance) {
		log.debug("attaching dirty IpBlob instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpBlob instance) {
		log.debug("attaching clean IpBlob instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public Long getBlobIdByEntity(long id, String tblNm) {
		log.debug("finding all blobs by entity id");
		try {
			Query query = getCurrentSession().getNamedQuery("getBlobIdByEntity");
			query.setLong("id", id);
			query.setString("tblNm", tblNm);
			Long ret = -999l;
			List obj = query.list();
			if (obj != null && obj.size() > 0)
				ret = ((IpBlob) obj.get(0)).getBlobId();
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpBlob getBlobByEntity(long id, String tblNm) {
		log.debug("finding all blobs by entity id");
		try {
			Query query = getCurrentSession().getNamedQuery("getBlobByEntity");
			query.setLong("id", id);
			query.setString("tblNm", tblNm);
			IpBlob ret = null;
			List obj = query.list();
			if (obj != null && obj.size() > 0)
				ret = (IpBlob) obj.get(0);
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void deleteBlobByEntity(long id, String tblNm) {
		log.debug("finding all blobs by entity id");
		try {
			Query query = getCurrentSession().getNamedQuery("deleteBlobByEntity");
			query.setLong("id", id);
			query.setString("tblNm", tblNm);
			query.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List listBlobByEntity(long id, String tblNm) {
		log.debug("finding all blobs by entity id");
		try {
			Query query = getCurrentSession().getNamedQuery("getBlobByEntity");
			query.setLong("id", id);
			query.setString("tblNm", tblNm);
			return query.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public static IpBlobDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpBlobDAO) ctx.getBean("IpBlobDAO");
	}
}