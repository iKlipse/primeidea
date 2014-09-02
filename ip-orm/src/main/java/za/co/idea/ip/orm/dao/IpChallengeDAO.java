package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import za.co.idea.ip.orm.bean.IpChallenge;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpChallenge entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpChallenge
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings({ "rawtypes" })
public class IpChallengeDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(IpChallengeDAO.class);
	// property constants
	public static final String CHAL_TITLE = "chalTitle";
	public static final String CHAL_DESC = "chalDesc";
	public static final String CHAL_HOVER_TXT = "chalHoverTxt";
	public static final String CHAL_TAGS = "chalTags";

	protected void initDao() {
		// do nothing
	}

	public void save(IpChallenge transientInstance) {
		log.debug("saving IpChallenge instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpChallenge persistentInstance) {
		log.debug("deleting IpChallenge instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpChallenge findById(java.lang.Long id) {
		log.debug("getting IpChallenge instance with id: " + id);
		try {
			IpChallenge instance = (IpChallenge) getHibernateTemplate().get("za.co.idea.ip.orm.bean.IpChallenge", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(IpChallenge instance) {
		log.debug("finding IpChallenge instance by example");
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
		log.debug("finding IpChallenge instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpChallenge as model where model." + propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByChalTitle(Object chalTitle) {
		return findByProperty(CHAL_TITLE, chalTitle);
	}

	public List findByChalDesc(Object chalDesc) {
		return findByProperty(CHAL_DESC, chalDesc);
	}

	public List findByChalHoverTxt(Object chalHoverTxt) {
		return findByProperty(CHAL_HOVER_TXT, chalHoverTxt);
	}

	public List findByChalTags(Object chalTags) {
		return findByProperty(CHAL_TAGS, chalTags);
	}

	public List findAll() {
		log.debug("finding all IpChallenge instances");
		try {
			String queryString = "from IpChallenge";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpChallenge merge(IpChallenge detachedInstance) {
		log.debug("merging IpChallenge instance");
		try {
			IpChallenge result = (IpChallenge) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpChallenge instance) {
		log.debug("attaching dirty IpChallenge instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpChallenge instance) {
		log.debug("attaching clean IpChallenge instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByUserId(Long id) {
		log.debug("Fetching Challenge by Query :: getChallengeByUser");
		try {
			Query query = getSession().getNamedQuery("getChallengeByUser");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpChallenge chal = (IpChallenge) object;
				Hibernate.initialize(chal.getIpChallengeCat());
				Hibernate.initialize(chal.getIpChallengeStatus());
				Hibernate.initialize(chal.getIpUserByChalCrtdBy());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findCreatedByUserId(Long id) {
		log.debug("Fetching Challenge by Query :: getChallengeCreatedByUser");
		try {
			Query query = getSession().getNamedQuery("getChallengeCreatedByUser");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpChallenge chal = (IpChallenge) object;
				Hibernate.initialize(chal.getIpChallengeCat());
				Hibernate.initialize(chal.getIpChallengeStatus());
				Hibernate.initialize(chal.getIpUserByChalCrtdBy());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByStatusId(Integer id) {
		log.debug("Fetching Challenge by Query :: getChallengeByStatus");
		try {
			Query query = getSession().getNamedQuery("getChallengeByStatus");
			query.setInteger("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpChallenge chal = (IpChallenge) object;
				Hibernate.initialize(chal.getIpChallengeCat());
				Hibernate.initialize(chal.getIpChallengeStatus());
				Hibernate.initialize(chal.getIpUserByChalCrtdBy());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByStatusIdUserId(Integer sid, Long id) {
		log.debug("Fetching Challenge by Query :: getChallengeByStatusUser");
		try {
			Query query = getSession().getNamedQuery("getChallengeByStatusUser");
			query.setInteger("sid", sid);
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpChallenge chal = (IpChallenge) object;
				Hibernate.initialize(chal.getIpChallengeCat());
				Hibernate.initialize(chal.getIpChallengeStatus());
				Hibernate.initialize(chal.getIpUserByChalCrtdBy());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public int updateStatusOnExpiry() {
		log.debug("Updating Challenge by Query :: updateStatusOnExpiry");
		try {
			Query query = getSession().getNamedQuery("updateStatusOnExpiry");
			int ret = query.executeUpdate();
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findReviewChalsByUserId(Long id) {
		log.debug("Fetching Challenge by Query :: getReviewChallengesByUser");
		try {
			Query query = getSession().getNamedQuery("getReviewChallengesByUser");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpChallenge chal = (IpChallenge) object;
				Hibernate.initialize(chal.getIpChallengeCat());
				Hibernate.initialize(chal.getIpChallengeStatus());
				Hibernate.initialize(chal.getIpUserByChalCrtdBy());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpChallengeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpChallengeDAO) ctx.getBean("IpChallengeDAO");
	}
}