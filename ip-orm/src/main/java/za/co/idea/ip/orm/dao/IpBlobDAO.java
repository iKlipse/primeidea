package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

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
@SuppressWarnings({ "rawtypes" })
public class IpBlobDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(IpBlobDAO.class);
	// property constants
	public static final String BLOB_NAME = "blobName";
	public static final String BLOB_CONTENT_TYPE = "blobContentType";
	public static final String BLOB_CONTENT = "blobContent";
	public static final String BLOB_ENTITY_ID = "blobEntityId";
	public static final String BLOB_ENTITY_TBL_NM = "blobEntityTblNm";
	public static final String BLOB_SIZE = "blobSize";

	protected void initDao() {
		// do nothing
	}

	public void save(IpBlob transientInstance) {
		log.debug("saving IpBlob instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpBlob persistentInstance) {
		log.debug("deleting IpBlob instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpBlob findById(java.lang.Long id) {
		log.debug("getting IpBlob instance with id: " + id);
		try {
			IpBlob instance = (IpBlob) getHibernateTemplate().get("za.co.idea.ip.orm.bean.IpBlob", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IpBlob instance) {
		log.debug("finding IpBlob instance by example");
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
		log.debug("finding IpBlob instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpBlob as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
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

	public List findByBlobSize(Object blobSize) {
		return findByProperty(BLOB_SIZE, blobSize);
	}

	public List findAll() {
		log.debug("finding all IpBlob instances");
		try {
			String queryString = "from IpBlob";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpBlob merge(IpBlob detachedInstance) {
		log.debug("merging IpBlob instance");
		try {
			IpBlob result = (IpBlob) getHibernateTemplate().merge(detachedInstance);
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
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpBlob instance) {
		log.debug("attaching clean IpBlob instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public Long getBlobIdByEntity(long id, String tblNm) {
		log.debug("finding all blobs by entity id");
		try {
			Query query = getSession().getNamedQuery("getBlobIdByEntity");
			query.setLong("id", id);
			query.setString("tblNm", tblNm);
			Long ret = -999l;
			List obj = query.list();
			if (obj != null && obj.size() > 0)
				ret = (Long) obj.get(0);
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpBlob getBlobByEntity(long id, String tblNm) {
		log.debug("finding all blobs by entity id");
		try {
			Query query = getSession().getNamedQuery("getBlobByEntity");
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

	public static IpBlobDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpBlobDAO) ctx.getBean("IpBlobDAO");
	}
}