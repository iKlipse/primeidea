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

import za.co.idea.ip.orm.bean.IpSecqList;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpSecqList entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpSecqList
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpSecqListDAO {
	private static final Logger log = Logger.getLogger(IpSecqListDAO.class);
	// property constants
	public static final String ISL_DESC = "islDesc";

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

	public void save(IpSecqList transientInstance) {
		log.debug("saving IpSecqList instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpSecqList persistentInstance) {
		log.debug("deleting IpSecqList instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpSecqList findById(java.lang.Integer id) {
		log.debug("getting IpSecqList instance with id: " + id);
		try {
			IpSecqList instance = (IpSecqList) getCurrentSession().get("za.co.idea.ip.orm.bean.IpSecqList", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpSecqList> findByExample(IpSecqList instance) {
		log.debug("finding IpSecqList instance by example");
		try {
			List<IpSecqList> results = (List<IpSecqList>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpSecqList").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpSecqList instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpSecqList as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpSecqList> findByIslDesc(Object islDesc) {
		return findByProperty(ISL_DESC, islDesc);
	}

	public List findAll() {
		log.debug("finding all IpSecqList instances");
		try {
			String queryString = "from IpSecqList";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpSecqList merge(IpSecqList detachedInstance) {
		log.debug("merging IpSecqList instance");
		try {
			IpSecqList result = (IpSecqList) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpSecqList instance) {
		log.debug("attaching dirty IpSecqList instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpSecqList instance) {
		log.debug("attaching clean IpSecqList instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpSecqListDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpSecqListDAO) ctx.getBean("IpSecqListDAO");
	}
}