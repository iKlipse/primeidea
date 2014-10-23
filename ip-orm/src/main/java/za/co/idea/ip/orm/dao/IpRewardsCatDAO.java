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

import za.co.idea.ip.orm.bean.IpRewardsCat;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpRewardsCat entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpRewardsCat
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpRewardsCatDAO {
	private static final Logger log = Logger.getLogger(IpRewardsCatDAO.class);
	// property constants
	public static final String RC_DESC = "rcDesc";

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

	public void save(IpRewardsCat transientInstance) {
		log.debug("saving IpRewardsCat instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpRewardsCat persistentInstance) {
		log.debug("deleting IpRewardsCat instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpRewardsCat findById(java.lang.Integer id) {
		log.debug("getting IpRewardsCat instance with id: " + id);
		try {
			IpRewardsCat instance = (IpRewardsCat) getCurrentSession().get("za.co.idea.ip.orm.bean.IpRewardsCat", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpRewardsCat> findByExample(IpRewardsCat instance) {
		log.debug("finding IpRewardsCat instance by example");
		try {
			List<IpRewardsCat> results = (List<IpRewardsCat>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpRewardsCat").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpRewardsCat instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpRewardsCat as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpRewardsCat> findByRcDesc(Object rcDesc) {
		return findByProperty(RC_DESC, rcDesc);
	}

	public List findAll() {
		log.debug("finding all IpRewardsCat instances");
		try {
			String queryString = "from IpRewardsCat";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpRewardsCat merge(IpRewardsCat detachedInstance) {
		log.debug("merging IpRewardsCat instance");
		try {
			IpRewardsCat result = (IpRewardsCat) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpRewardsCat instance) {
		log.debug("attaching dirty IpRewardsCat instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpRewardsCat instance) {
		log.debug("attaching clean IpRewardsCat instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List listDependentCat() {
		log.debug("checking category dependency");
		try {
			Query query = getCurrentSession().getNamedQuery("checkRwDependency");
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("checking category", re);
			throw re;
		}
	}

	public static IpRewardsCatDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpRewardsCatDAO) ctx.getBean("IpRewardsCatDAO");
	}
}