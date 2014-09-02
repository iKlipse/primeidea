package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import za.co.idea.ip.orm.bean.IpIdea;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpIdea entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpIdea
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings({ "rawtypes" })
public class IpIdeaDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(IpIdeaDAO.class);
	// property constants
	public static final String IDEA_TITLE = "ideaTitle";
	public static final String IDEA_DESC = "ideaDesc";
	public static final String IDEA_BA = "ideaBa";
	public static final String IDEA_TAG = "ideaTag";

	protected void initDao() {
		// do nothing
	}

	public void save(IpIdea transientInstance) {
		log.debug("saving IpIdea instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpIdea persistentInstance) {
		log.debug("deleting IpIdea instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpIdea findById(java.lang.Long id) {
		log.debug("getting IpIdea instance with id: " + id);
		try {
			IpIdea instance = (IpIdea) getHibernateTemplate().get("za.co.idea.ip.orm.bean.IpIdea", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IpIdea instance) {
		log.debug("finding IpIdea instance by example");
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
		log.debug("finding IpIdea instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpIdea as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdeaTitle(Object ideaTitle) {
		return findByProperty(IDEA_TITLE, ideaTitle);
	}

	public List findByIdeaDesc(Object ideaDesc) {
		return findByProperty(IDEA_DESC, ideaDesc);
	}

	public List findByIdeaBa(Object ideaBa) {
		return findByProperty(IDEA_BA, ideaBa);
	}

	public List findByIdeaTag(Object ideaTag) {
		return findByProperty(IDEA_TAG, ideaTag);
	}

	public List findAll() {
		log.debug("finding all IpIdea instances");
		try {
			String queryString = "from IpIdea";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpIdea merge(IpIdea detachedInstance) {
		log.debug("merging IpIdea instance");
		try {
			IpIdea result = (IpIdea) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpIdea instance) {
		log.debug("attaching dirty IpIdea instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpIdea instance) {
		log.debug("attaching clean IpIdea instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByUserId(Long id) {
		log.debug("finding IpIdea instances by user id :: " + id);
		try {
			Query query = getSession().getNamedQuery("getIdeaByUser");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpIdea idea = (IpIdea) object;
				Hibernate.initialize(idea.getIpIdeaCat());
				Hibernate.initialize(idea.getIpIdeaStatus());
				Hibernate.initialize(idea.getIpUserByIdeaUserId());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findCreatedByUserId(Long id) {
		log.debug("finding IpIdea instances by user id :: " + id);
		try {
			Query query = getSession().getNamedQuery("getIdeaCreatedByUser");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpIdea idea = (IpIdea) object;
				Hibernate.initialize(idea.getIpIdeaCat());
				Hibernate.initialize(idea.getIpIdeaStatus());
				Hibernate.initialize(idea.getIpUserByIdeaUserId());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findByStatusId(Integer id) {
		log.debug("finding IpIdea instances by status id :: " + id);
		try {
			Query query = getSession().getNamedQuery("getIdeaByStatus");
			query.setInteger("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpIdea idea = (IpIdea) object;
				Hibernate.initialize(idea.getIpIdeaCat());
				Hibernate.initialize(idea.getIpIdeaStatus());
				Hibernate.initialize(idea.getIpUserByIdeaUserId());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findByStatusIdUserId(Integer sid, Long id) {
		log.debug("finding IpIdea instances by status id :: " + sid + " :: user id :: " + id);
		try {
			Query query = getSession().getNamedQuery("getIdeaByStatusUser");
			query.setLong("id", id);
			query.setInteger("sid", sid);
			List ret = query.list();
			for (Object object : ret) {
				IpIdea idea = (IpIdea) object;
				Hibernate.initialize(idea.getIpIdeaCat());
				Hibernate.initialize(idea.getIpIdeaStatus());
				Hibernate.initialize(idea.getIpUserByIdeaUserId());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findReviewIdeasByUserId(Long id) {
		log.debug("finding Review Status IpIdea instances by user id :: " + id);
		try {
			Query query = getSession().getNamedQuery("getReviewsIdeasByUser");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpIdea idea = (IpIdea) object;
				Hibernate.initialize(idea.getIpIdeaCat());
				Hibernate.initialize(idea.getIpIdeaStatus());
				Hibernate.initialize(idea.getIpUserByIdeaUserId());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public static IpIdeaDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpIdeaDAO) ctx.getBean("IpIdeaDAO");
	}
}