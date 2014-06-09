package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
@SuppressWarnings("rawtypes")
public class IpRewardsDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(IpRewardsDAO.class);
	// property constants
	public static final String RW_TITLE = "rwTitle";
	public static final String RW_DESC = "rwDesc";
	public static final String RW_VALUE = "rwValue";
	public static final String RW_STOCK_CODE_NUM = "rwStockCodeNum";
	public static final String RW_HOVER_TEXT = "rwHoverText";
	public static final String RW_TAG = "rwTag";

	public void save(IpRewards transientInstance) {
		log.debug("saving IpRewards instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(transientInstance);
			transaction.commit();
			session.close();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public void delete(IpRewards persistentInstance) {
		log.debug("deleting IpRewards instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.delete(persistentInstance);
			transaction.commit();
			session.close();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public IpRewards findById(java.lang.Long id) {
		log.debug("getting IpRewards instance with id: " + id);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpRewards instance = (IpRewards) session.get("za.co.idea.ip.orm.bean.IpRewards", id);
			transaction.commit();
			session.close();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public List findByExample(IpRewards instance) {
		log.debug("finding IpRewards instance by example");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			List results = session.createCriteria("za.co.idea.ip.orm.bean.IpRewards").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			transaction.commit();
			session.close();
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IpRewards instance with property: " + propertyName + ", value: " + value);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpRewards as model where model." + propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			List results = queryObject.list();
			transaction.commit();
			session.close();
			return results;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public List findByRwTitle(Object rwTitle) {
		return findByProperty(RW_TITLE, rwTitle);
	}

	public List findByRwDesc(Object rwDesc) {
		return findByProperty(RW_DESC, rwDesc);
	}

	public List findByRwValue(Object rwValue) {
		return findByProperty(RW_VALUE, rwValue);
	}

	public List findByRwStockCodeNum(Object rwStockCodeNum) {
		return findByProperty(RW_STOCK_CODE_NUM, rwStockCodeNum);
	}

	public List findByRwHoverText(Object rwHoverText) {
		return findByProperty(RW_HOVER_TEXT, rwHoverText);
	}

	public List findByRwTag(Object rwTag) {
		return findByProperty(RW_TAG, rwTag);
	}

	public List findAll() {
		log.debug("finding all IpRewards instances");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			String queryString = "from IpRewards";
			Query queryObject = session.createQuery(queryString);
			List results = queryObject.list();
			transaction.commit();
			session.close();
			return results;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public IpRewards merge(IpRewards detachedInstance) {
		log.debug("merging IpRewards instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			IpRewards result = (IpRewards) session.merge(detachedInstance);
			log.debug("merge successful");
			transaction.commit();
			session.close();
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public void attachDirty(IpRewards instance) {
		log.debug("attaching dirty IpRewards instance");
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(instance);
			transaction.commit();
			session.close();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			transaction.rollback();
			session.close();
			throw re;
		}
	}

	public List findByStatusId(Integer id) {
		log.debug("Fetching Challenge by Query :: getRewardsByStatus");
		Session session = getSession();
		try {
			Query query = session.getNamedQuery("getRewardsByStatus");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpRewards rw = (IpRewards) object;
				Hibernate.initialize(rw.getIpRewardsCat());
				Hibernate.initialize(rw.getIpRewardsStatus());
			}
			session.close();
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByUserId(Long id) {
		log.debug("Fetching Challenge by Query :: getRewardsByUser");
		Session session = getSession();
		try {
			Query query = session.getNamedQuery("getRewardsByUser");
			query.setLong("id", id);
			List ret = query.list();
			for (Object object : ret) {
				IpRewards rw = (IpRewards) object;
				Hibernate.initialize(rw.getIpRewardsCat());
				Hibernate.initialize(rw.getIpRewardsStatus());
			}
			session.close();
			return ret;
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}