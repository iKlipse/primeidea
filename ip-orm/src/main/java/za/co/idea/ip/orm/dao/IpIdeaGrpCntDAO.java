package za.co.idea.ip.orm.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import za.co.idea.ip.orm.bean.IpIdeaGrpCnt;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpIdeaGrpCnt entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpIdeaGrpCnt
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IpIdeaGrpCntDAO {
	private static final Logger log = Logger.getLogger(IpIdeaGrpCntDAO.class);
	// property constants

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

	public void save(IpIdeaGrpCnt transientInstance) {
		log.debug("saving IpIdeaGrpCnt instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpIdeaGrpCnt persistentInstance) {
		log.debug("deleting IpIdeaGrpCnt instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpIdeaGrpCnt findById(za.co.idea.ip.orm.bean.IpIdeaGrpCntId id) {
		log.debug("getting IpIdeaGrpCnt instance with id: " + id);
		try {
			IpIdeaGrpCnt instance = (IpIdeaGrpCnt) getCurrentSession().get(
					"za.co.idea.ip.orm.bean.IpIdeaGrpCnt", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpIdeaGrpCnt> findByExample(IpIdeaGrpCnt instance) {
		log.debug("finding IpIdeaGrpCnt instance by example");
		try {
			List<IpIdeaGrpCnt> results = (List<IpIdeaGrpCnt>) getCurrentSession()
					.createCriteria("za.co.idea.ip.orm.bean.IpIdeaGrpCnt")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpIdeaGrpCnt instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from IpIdeaGrpCnt as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all IpIdeaGrpCnt instances");
		try {
			String queryString = "from IpIdeaGrpCnt";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpIdeaGrpCnt merge(IpIdeaGrpCnt detachedInstance) {
		log.debug("merging IpIdeaGrpCnt instance");
		try {
			IpIdeaGrpCnt result = (IpIdeaGrpCnt) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpIdeaGrpCnt instance) {
		log.debug("attaching dirty IpIdeaGrpCnt instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpIdeaGrpCnt instance) {
		log.debug("attaching clean IpIdeaGrpCnt instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpIdeaGrpCntDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IpIdeaGrpCntDAO) ctx.getBean("IpIdeaGrpCntDAO");
	}
}