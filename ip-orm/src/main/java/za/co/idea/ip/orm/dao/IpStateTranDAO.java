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

import za.co.idea.ip.orm.bean.IpStateTran;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpStateTran entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpStateTran
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpStateTranDAO {
	private static final Logger log = Logger.getLogger(IpStateTranDAO.class);
	// property constants
	public static final String TRAN_ENTITY = "tranEntity";
	public static final String TRAN_CURR_STATE = "tranCurrState";
	public static final String TRAN_NEXT_STATE = "tranNextState";
	public static final String TRAN_IS_UI = "tranIsUi";

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

	public void save(IpStateTran transientInstance) {
		log.debug("saving IpStateTran instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpStateTran persistentInstance) {
		log.debug("deleting IpStateTran instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpStateTran findById(java.lang.Integer id) {
		log.debug("getting IpStateTran instance with id: " + id);
		try {
			IpStateTran instance = (IpStateTran) getCurrentSession().get("za.co.idea.ip.orm.bean.IpStateTran", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpStateTran> findByExample(IpStateTran instance) {
		log.debug("finding IpStateTran instance by example");
		try {
			List<IpStateTran> results = (List<IpStateTran>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpStateTran").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpStateTran instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpStateTran as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpStateTran> findByTranEntity(Object tranEntity) {
		return findByProperty(TRAN_ENTITY, tranEntity);
	}

	public List<IpStateTran> findByTranCurrState(Object tranCurrState) {
		return findByProperty(TRAN_CURR_STATE, tranCurrState);
	}

	public List<IpStateTran> findByTranNextState(Object tranNextState) {
		return findByProperty(TRAN_NEXT_STATE, tranNextState);
	}

	public List<IpStateTran> findByTranIsUi(Object tranIsUi) {
		return findByProperty(TRAN_IS_UI, tranIsUi);
	}

	public List findAll() {
		log.debug("finding all IpStateTran instances");
		try {
			String queryString = "from IpStateTran";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpStateTran merge(IpStateTran detachedInstance) {
		log.debug("merging IpStateTran instance");
		try {
			IpStateTran result = (IpStateTran) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpStateTran instance) {
		log.debug("attaching dirty IpStateTran instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpStateTran instance) {
		log.debug("attaching clean IpStateTran instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpStateTranDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpStateTranDAO) ctx.getBean("IpStateTranDAO");
	}
}