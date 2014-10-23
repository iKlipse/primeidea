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

import za.co.idea.ip.orm.bean.IpRewards;

/**
 * A data access object (DAO) providing persistence and search support for
 * IpRewards entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.idea.ip.orm.bean.IpRewards
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
public class IpRewardsDAO {
	private static final Logger log = Logger.getLogger(IpRewardsDAO.class);
	// property constants
	public static final String RW_TITLE = "rwTitle";
	public static final String RW_DESC = "rwDesc";
	public static final String RW_VALUE = "rwValue";
	public static final String RW_STOCK_CODE_NUM = "rwStockCodeNum";
	public static final String RW_HOVER_TEXT = "rwHoverText";
	public static final String RW_TAG = "rwTag";
	public static final String RW_PRICE = "rwPrice";
	public static final String RW_QUANTITY = "rwQuantity";

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

	public void save(IpRewards transientInstance) {
		log.debug("saving IpRewards instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IpRewards persistentInstance) {
		log.debug("deleting IpRewards instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IpRewards findById(java.lang.Long id) {
		log.debug("getting IpRewards instance with id: " + id);
		try {
			IpRewards instance = (IpRewards) getCurrentSession().get("za.co.idea.ip.orm.bean.IpRewards", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IpRewards> findByExample(IpRewards instance) {
		log.debug("finding IpRewards instance by example");
		try {
			List<IpRewards> results = (List<IpRewards>) getCurrentSession().createCriteria("za.co.idea.ip.orm.bean.IpRewards").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpRewards instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IpRewards as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IpRewards> findByRwTitle(Object rwTitle) {
		return findByProperty(RW_TITLE, rwTitle);
	}

	public List<IpRewards> findByRwDesc(Object rwDesc) {
		return findByProperty(RW_DESC, rwDesc);
	}

	public List<IpRewards> findByRwValue(Object rwValue) {
		return findByProperty(RW_VALUE, rwValue);
	}

	public List<IpRewards> findByRwStockCodeNum(Object rwStockCodeNum) {
		return findByProperty(RW_STOCK_CODE_NUM, rwStockCodeNum);
	}

	public List<IpRewards> findByRwHoverText(Object rwHoverText) {
		return findByProperty(RW_HOVER_TEXT, rwHoverText);
	}

	public List<IpRewards> findByRwTag(Object rwTag) {
		return findByProperty(RW_TAG, rwTag);
	}

	public List<IpRewards> findByRwPrice(Object rwPrice) {
		return findByProperty(RW_PRICE, rwPrice);
	}

	public List<IpRewards> findByRwQuantity(Object rwQuantity) {
		return findByProperty(RW_QUANTITY, rwQuantity);
	}

	public List findAll() {
		log.debug("finding all IpRewards instances");
		try {
			String queryString = "from IpRewards";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IpRewards merge(IpRewards detachedInstance) {
		log.debug("merging IpRewards instance");
		try {
			IpRewards result = (IpRewards) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IpRewards instance) {
		log.debug("attaching dirty IpRewards instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IpRewards instance) {
		log.debug("attaching clean IpRewards instance");
		try {
			getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByAvail() {
		log.debug("Fetching Challenge by Query :: getRewardsByAvail");
		try {
			Query query = getCurrentSession().getNamedQuery("getRewardsByAvail");
			List ret = query.list();
			for (Object object : ret) {
				IpRewards rw = (IpRewards) object;
				Hibernate.initialize(rw.getIpRewardsCat());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByUserId(Long id) {
		log.debug("Fetching Challenge by Query :: getRewardsByUser");
		try {
			Query query = getCurrentSession().getNamedQuery("getRewardsByUser");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpRewards rw = (IpRewards) object;
				Hibernate.initialize(rw.getIpRewardsCat());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByCatId(Integer id) {
		log.debug("Fetching Rewards by Query :: getRewardsByCatId");
		try {
			Query query = getCurrentSession().getNamedQuery("getRewardsByCatId");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpRewards rw = (IpRewards) object;
				Hibernate.initialize(rw.getIpRewardsCat());
			}
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IpRewardsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IpRewardsDAO) ctx.getBean("IpRewardsDAO");
	}
}