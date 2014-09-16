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

import za.co.idea.ip.orm.bean.IpChallengeCat;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpChallengeCat entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpChallengeCat
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpChallengeCatDAO {
	private static final Logger log = Logger.getLogger(IpChallengeCatDAO.class);
	// property constants
	public static final String CC_DESC = "ccDesc";

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

	public void save(IpChallengeCat transientInstance) {
		log.debug("saving IpChallengeCat instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpChallengeCat persistentInstance) {
		log.debug("deleting IpChallengeCat instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpChallengeCat findById(java.lang.Integer id) {
		log.debug("getting IpChallengeCat instance with id: " + id);
		try {
			IpChallengeCat instance = (IpChallengeCat) getCurrentSession().get("za.co.idea.ip.orm.bean.IpChallengeCat", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpChallengeCat> findByExample(IpChallengeCat instance) {
		log.debug("finding IpChallengeCat instance by example");
		try {
			List<IpChallengeCat> results = (List<IpChallengeCat>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpChallengeCat").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpChallengeCat instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpChallengeCat as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpChallengeCat> findByCcDesc(Object ccDesc) {
		return findByProperty(CC_DESC, ccDesc);
	}

	public List findAll() {
		log.debug("finding all IpChallengeCat instances");
		try {
			String queryString = "from IpChallengeCat";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpChallengeCat merge(IpChallengeCat detachedInstance) {
		log.debug("merging IpChallengeCat instance");
		try {
			IpChallengeCat result = (IpChallengeCat) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpChallengeCat instance) {
		log.debug("attaching dirty IpChallengeCat instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpChallengeCat instance) {
		log.debug("attaching clean IpChallengeCat instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpChallengeCatDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpChallengeCatDAO) ctx.getBean("IpChallengeCatDAO");
	}
}