package za.co.idea.ip.orm.dao;

import static org.hibernate.criterion.Example.create;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import za.co.idea.ip.orm.bean.IpUser;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpUser entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpUser
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpUserDAO {
	private static final Logger log = Logger.getLogger(IpUserDAO.class);
	// property constants
	public static final String USER_FNAME = "userFName";
	public static final String USER_LNAME = "userLName";
	public static final String USER_MNAME = "userMName";
	public static final String USER_ID_NUM = "userIdNum";
	public static final String USER_SCREEN_NAME = "userScreenName";
	public static final String USER_EMAIL = "userEmail";
	public static final String USER_CONTACT = "userContact";
	public static final String USER_SKILLS = "userSkills";
	public static final String USER_BIO = "userBio";
	public static final String USER_FB_HANDLE = "userFbHandle";
	public static final String USER_TW_HANDLE = "userTwHandle";
	public static final String USER_STATUS = "userStatus";
	public static final String USER_EMPLOYEE_ID = "userEmployeeId";

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

	public void save(IpUser transientInstance) {
		log.debug("saving IpUser instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpUser persistentInstance) {
		log.debug("deleting IpUser instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpUser findById(java.lang.Long id) {
		log.debug("getting IpUser instance with id: " + id);
		try {
			IpUser instance = (IpUser) getCurrentSession().get("za.co.idea.ip.orm.bean.IpUser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpUser> findByExample(IpUser instance) {
		log.debug("finding IpUser instance by example");
		try {
			List<IpUser> results = (List<IpUser>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpUser").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpUser instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpUser as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpUser> findByUserFName(Object userFName) {
		return findByProperty(USER_FNAME, userFName);
	}

	public List<IpUser> findByUserLName(Object userLName) {
		return findByProperty(USER_LNAME, userLName);
	}

	public List<IpUser> findByUserMName(Object userMName) {
		return findByProperty(USER_MNAME, userMName);
	}

	public List<IpUser> findByUserIdNum(Object userIdNum) {
		return findByProperty(USER_ID_NUM, userIdNum);
	}

	public List<IpUser> findByUserScreenName(Object userScreenName) {
		return findByProperty(USER_SCREEN_NAME, userScreenName);
	}

	public List<IpUser> findByUserEmail(Object userEmail) {
		return findByProperty(USER_EMAIL, userEmail);
	}

	public List<IpUser> findByUserContact(Object userContact) {
		return findByProperty(USER_CONTACT, userContact);
	}

	public List<IpUser> findByUserSkills(Object userSkills) {
		return findByProperty(USER_SKILLS, userSkills);
	}

	public List<IpUser> findByUserBio(Object userBio) {
		return findByProperty(USER_BIO, userBio);
	}

	public List<IpUser> findByUserFbHandle(Object userFbHandle) {
		return findByProperty(USER_FB_HANDLE, userFbHandle);
	}

	public List<IpUser> findByUserTwHandle(Object userTwHandle) {
		return findByProperty(USER_TW_HANDLE, userTwHandle);
	}

	public List<IpUser> findByUserStatus(Object userStatus) {
		return findByProperty(USER_STATUS, userStatus);
	}

	public List<IpUser> findByUserEmployeeId(Object userEmployeeId) {
		return findByProperty(USER_EMPLOYEE_ID, userEmployeeId);
	}

	public List findAll() {
		log.debug("finding all IpUser instances");
		try {
			String queryString = "from IpUser";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpUser merge(IpUser detachedInstance) {
		log.debug("merging IpUser instance");
		try {
			IpUser result = (IpUser) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpUser instance) {
		log.debug("attaching dirty IpUser instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpUser instance) {
		log.debug("attaching clean IpUser instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List fetchActiveUsers() {
		log.debug("Fetching all active Users by Query :: getUsersByStatus");
		try {
			Query query = getCurrentSession().getNamedQuery("getUsersByStatus");
			query.setString("status", "y");
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("find users failed", re);
			throw re;
		}
	}

	public List fetchInActiveUsers() {
		log.debug("Fetching all in active Users by Query :: getUsersByStatus");
		try {
			Query query = getCurrentSession().getNamedQuery("getUsersByStatus");
			query.setString("status", "n");
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("find users failed", re);
			throw re;
		}
	}

	public List fetchUsersByGroup(Long groupId) {
		log.debug("Fetching all Users by Query :: getUsersByGroup");
		try {
			Query query = getCurrentSession().getNamedQuery("getUsersByGroup");
			query.setLong("groupId", groupId);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("find users failed", re);
			throw re;
		}
	}

	public List fetchSortByPrimaryGroup() {
		log.debug("Fetching User by Query :: sortListByPrimaryGrp");
		try {
			Query query = getCurrentSession().getNamedQuery("sortListByPrimaryGrp");
			List ret = query.list();

			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void updateUserAsInactive(String mailID) {
		log.debug("Updating user status as inactive");
		try {
			Query query = getCurrentSession().getNamedQuery("updateUserAsInActive");
			query.setString("email", mailID);
			query.executeUpdate();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public Long findSolutionCount(Long userId) {
		Long solCount = 0l;
		log.debug("Fetching Solution coutn for user by Query :: getSolCountByUserId");
		try {
			Query query = getCurrentSession().getNamedQuery("getSolCountByUserId");
			query.setLong("id", userId);
			List sol = query.list();
			if (sol != null && sol.size() > 0)
				solCount = (long) sol.size();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
		}
		return solCount;
	}

	public Long findChallengeCount(Long userId) {
		Long chalCount = 0l;
		log.debug("Fetching Challenge count for user by Query :: getChalCountByUserId");
		try {
			Query query = getCurrentSession().getNamedQuery("getChalCountByUserId");
			query.setLong("id", userId);
			List chal = query.list();
			if (chal != null && chal.size() > 0)
				chalCount = (long) chal.size();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
		}
		return chalCount;
	}

	public Long findIdeasCount(Long userId) {
		Long ideaCount = 0l;
		log.debug("Fetching Ideas count for user by Query :: getIdeasCountByUserId");
		try {
			Query query = getCurrentSession().getNamedQuery("getIdeasCountByUserId");
			query.setLong("id", userId);
			List idea = query.list();
			if (idea != null && idea.size() > 0)
				ideaCount = (long) idea.size();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
		}
		return ideaCount;
	}

	public Long findWhishlistCount(Long userId) {
		Long whishListCount = 0l;
		log.debug("Fetching WhishList count for user by Query :: getWhishListCountByUserId");
		try {
			Query query = getCurrentSession().getNamedQuery("getWhishListCountByUserId");
			query.setLong("id", userId);
			List whishList = query.list();
			if (whishList != null && whishList.size() > 0)
				whishListCount = (long) whishList.size();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
		}
		return whishListCount;
	}

	public Long findPointsCount(Long userId) {
		Long points = 0l;
		log.debug("Fetching Points count for user by Query ");
		try {
			Query query = getCurrentSession().createSQLQuery("select coalesce(sum(point_value),0) from ip_points pts where pts.user_id = " + userId);
			List pointsList = query.list();
			if (pointsList != null && pointsList.size() > 0) {
				if (pointsList.get(0) != null)
					points = ((BigDecimal) pointsList.get(0)).longValue();
			}
		} catch (RuntimeException re) {
			log.error("attach failed", re);
		}
		return points;
	}

	public static IpUserDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpUserDAO) ctx.getBean("IpUserDAO");
	}
}