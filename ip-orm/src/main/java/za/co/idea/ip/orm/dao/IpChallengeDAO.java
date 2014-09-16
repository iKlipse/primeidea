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
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpChallengeDAO {
	private static final Logger log = Logger.getLogger(IpChallengeDAO.class);
	// property constants
	public static final String CHAL_TITLE = "chalTitle";
	public static final String CHAL_DESC = "chalDesc";
	public static final String CHAL_HOVER_TXT = "chalHoverTxt";
	public static final String CHAL_TAGS = "chalTags";
	public static final String CHAL_REVIEW_CNT = "chalReviewCnt";

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

	public void save(IpChallenge transientInstance) {
		log.debug("saving IpChallenge instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpChallenge persistentInstance) {
		log.debug("deleting IpChallenge instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpChallenge findById(java.lang.Long id) {
		log.debug("getting IpChallenge instance with id: " + id);
		try {
			IpChallenge instance = (IpChallenge) getCurrentSession().get("za.co.idea.ip.orm.bean.IpChallenge", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpChallenge> findByExample(IpChallenge instance) {
		log.debug("finding IpChallenge instance by example");
		try {
			List<IpChallenge> results = (List<IpChallenge>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpChallenge").add(create(instance)).list();
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
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpChallenge> findByChalTitle(Object chalTitle) {
		return findByProperty(CHAL_TITLE, chalTitle);
	}

	public List<IpChallenge> findByChalDesc(Object chalDesc) {
		return findByProperty(CHAL_DESC, chalDesc);
	}

	public List<IpChallenge> findByChalHoverTxt(Object chalHoverTxt) {
		return findByProperty(CHAL_HOVER_TXT, chalHoverTxt);
	}

	public List<IpChallenge> findByChalTags(Object chalTags) {
		return findByProperty(CHAL_TAGS, chalTags);
	}

	public List<IpChallenge> findByChalReviewCnt(Object chalReviewCnt) {
		return findByProperty(CHAL_REVIEW_CNT, chalReviewCnt);
	}

	public List findAll() {
		log.debug("finding all IpChallenge instances");
		try {
			String queryString = "from IpChallenge";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpChallenge merge(IpChallenge detachedInstance) {
		log.debug("merging IpChallenge instance");
		try {
			IpChallenge result = (IpChallenge) getCurrentSession().merge(detachedInstance);
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
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpChallenge instance) {
		log.debug("attaching clean IpChallenge instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByUserId(Long id) {
		log.debug("Fetching Challenge by Query :: getChallengeByUser");
		try {
			Query query = getCurrentSession().getNamedQuery("getChallengeByUser");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpChallenge chal = (IpChallenge) object;
				Hibernate.initialize(chal.getIpChallengeCat());
				Hibernate.initialize(chal.getIpChallengeStatus());
				Hibernate.initialize(chal.getIpUser());
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
			Query query = getCurrentSession().getNamedQuery("getChallengeCreatedByUser");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpChallenge chal = (IpChallenge) object;
				Hibernate.initialize(chal.getIpChallengeCat());
				Hibernate.initialize(chal.getIpChallengeStatus());
				Hibernate.initialize(chal.getIpUser());
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
			Query query = getCurrentSession().getNamedQuery("getChallengeByStatus");
			query.setInteger("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpChallenge chal = (IpChallenge) object;
				Hibernate.initialize(chal.getIpChallengeCat());
				Hibernate.initialize(chal.getIpChallengeStatus());
				Hibernate.initialize(chal.getIpUser());
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
			Query query = getCurrentSession().getNamedQuery("getChallengeByStatusUser");
			query.setInteger("sid", sid);
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpChallenge chal = (IpChallenge) object;
				Hibernate.initialize(chal.getIpChallengeCat());
				Hibernate.initialize(chal.getIpChallengeStatus());
				Hibernate.initialize(chal.getIpUser());
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
			Query query = getCurrentSession().getNamedQuery("updateStatusOnExpiry");
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
			Query query = getCurrentSession().getNamedQuery("getReviewChallengesByUser");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpChallenge chal = (IpChallenge) object;
				Hibernate.initialize(chal.getIpChallengeCat());
				Hibernate.initialize(chal.getIpChallengeStatus());
				Hibernate.initialize(chal.getIpUser());
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