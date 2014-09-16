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

import za.co.idea.ip.orm.bean.IpNews;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpNews entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpNews
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpNewsDAO {
	private static final Logger log = Logger.getLogger(IpNewsDAO.class);
	// property constants
	public static final String NEWS_TITLE = "newsTitle";
	public static final String NEWS_CONTENT = "newsContent";

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

	public void save(IpNews transientInstance) {
		log.debug("saving IpNews instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpNews persistentInstance) {
		log.debug("deleting IpNews instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpNews findById(java.lang.Long id) {
		log.debug("getting IpNews instance with id: " + id);
		try {
			IpNews instance = (IpNews) getCurrentSession().get("za.co.idea.ip.orm.bean.IpNews", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpNews> findByExample(IpNews instance) {
		log.debug("finding IpNews instance by example");
		try {
			List<IpNews> results = (List<IpNews>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpNews").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpNews instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpNews as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpNews> findByNewsTitle(Object newsTitle) {
		return findByProperty(NEWS_TITLE, newsTitle);
	}

	public List<IpNews> findByNewsContent(Object newsContent) {
		return findByProperty(NEWS_CONTENT, newsContent);
	}

	public List findAll() {
		log.debug("finding all IpNews instances");
		try {
			String queryString = "from IpNews";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpNews merge(IpNews detachedInstance) {
		log.debug("merging IpNews instance");
		try {
			IpNews result = (IpNews) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpNews instance) {
		log.debug("attaching dirty IpNews instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpNews instance) {
		log.debug("attaching clean IpNews instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpNewsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpNewsDAO) ctx.getBean("IpNewsDAO");
	}
}