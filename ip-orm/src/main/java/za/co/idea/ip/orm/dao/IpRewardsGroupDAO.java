package za.co.idea.ip.orm.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.idea.ip.orm.bean.IpRewardsGroup;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpRewardsGroup entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpRewardsGroup
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpRewardsGroupDAO {
	private static final Logger log = Logger.getLogger(IpRewardsGroupDAO.class);
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

	public void save(IpRewardsGroup transientInstance) {
		log.debug("saving IpRewardsGroup instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpRewardsGroup persistentInstance) {
		log.debug("deleting IpRewardsGroup instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpRewardsGroup findById(java.lang.Long id) {
		log.debug("getting IpRewardsGroup instance with id: " + id);
		try {
			IpRewardsGroup instance = (IpRewardsGroup) getCurrentSession().get("za.co.idea.ip.orm.bean.IpRewardsGroup", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpRewardsGroup> findByExample(IpRewardsGroup instance) {
		log.debug("finding IpRewardsGroup instance by example");
		try {
			List<IpRewardsGroup> results = (List<IpRewardsGroup>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpRewardsGroup").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpRewardsGroup instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpRewardsGroup as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all IpRewardsGroup instances");
		try {
			String queryString = "from IpRewardsGroup";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpRewardsGroup merge(IpRewardsGroup detachedInstance) {
		log.debug("merging IpRewardsGroup instance");
		try {
			IpRewardsGroup result = (IpRewardsGroup) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpRewardsGroup instance) {
		log.debug("attaching dirty IpRewardsGroup instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpRewardsGroup instance) {
		log.debug("attaching clean IpRewardsGroup instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteByRewardsId(Long id) {
		log.debug("Deleting Rewards Groups By Id : " + id);
		try {
			Query query = getCurrentSession().getNamedQuery("deleteRGByRwId");
			query.setLong("id", id);
			query.executeUpdate();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List fetchByRewardsId(Long id) {
		log.debug("Fetching Group Users By Id : " + id);
		try {
			Query query = getCurrentSession().getNamedQuery("fetchRGByRwId");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpRewardsGroup fg = (IpRewardsGroup) object;
				Hibernate.initialize(fg.getIpGroup());
				Hibernate.initialize(fg.getIpRewards());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List fetchByGroupId(Long id) {
		log.debug("Fetching Group Users By Id : " + id);
		try {
			Query query = getCurrentSession().getNamedQuery("fetchRGByGroupId");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpRewardsGroup fg = (IpRewardsGroup) object;
				Hibernate.initialize(fg.getIpGroup());
				Hibernate.initialize(fg.getIpRewards());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpRewardsGroupDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpRewardsGroupDAO) ctx.getBean("IpRewardsGroupDAO");
	}
}