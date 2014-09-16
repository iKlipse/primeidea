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
import org.springframework.transaction.annotation.Transactional;

import za.co.idea.ip.orm.bean.IpIdeaGroup;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpIdeaGroup entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpIdeaGroup
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpIdeaGroupDAO {
	private static final Logger log = Logger.getLogger(IpIdeaGroupDAO.class);
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

	public void save(IpIdeaGroup transientInstance) {
		log.debug("saving IpIdeaGroup instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpIdeaGroup persistentInstance) {
		log.debug("deleting IpIdeaGroup instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpIdeaGroup findById(java.lang.Long id) {
		log.debug("getting IpIdeaGroup instance with id: " + id);
		try {
			IpIdeaGroup instance = (IpIdeaGroup) getCurrentSession().get("za.co.idea.ip.orm.bean.IpIdeaGroup", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpIdeaGroup> findByExample(IpIdeaGroup instance) {
		log.debug("finding IpIdeaGroup instance by example");
		try {
			List<IpIdeaGroup> results = (List<IpIdeaGroup>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpIdeaGroup").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpIdeaGroup instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpIdeaGroup as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all IpIdeaGroup instances");
		try {
			String queryString = "from IpIdeaGroup";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpIdeaGroup merge(IpIdeaGroup detachedInstance) {
		log.debug("merging IpIdeaGroup instance");
		try {
			IpIdeaGroup result = (IpIdeaGroup) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpIdeaGroup instance) {
		log.debug("attaching dirty IpIdeaGroup instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpIdeaGroup instance) {
		log.debug("attaching clean IpIdeaGroup instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void deleteByIdeaId(Long id) {
		log.debug("Deleting Idea Groups By Id : " + id);
		try {
			Query query = getCurrentSession().getNamedQuery("deleteIGByIdeaId");
			query.setLong("id", id);
			query.executeUpdate();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List fetchByIdeaId(Long id) {
		log.debug("Fetching Group Users By Id : " + id);
		try {
			Query query = getCurrentSession().getNamedQuery("fetchIGByIdeaId");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpIdeaGroup fg = (IpIdeaGroup) object;
				Hibernate.initialize(fg.getIpGroup());
				Hibernate.initialize(fg.getIpIdea());
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
			Query query = getCurrentSession().getNamedQuery("fetchIGByGroupId");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpIdeaGroup fg = (IpIdeaGroup) object;
				Hibernate.initialize(fg.getIpGroup());
				Hibernate.initialize(fg.getIpIdea());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpIdeaGroupDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpIdeaGroupDAO) ctx.getBean("IpIdeaGroupDAO");
	}
}