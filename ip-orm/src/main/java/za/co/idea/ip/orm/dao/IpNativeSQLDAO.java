package za.co.idea.ip.orm.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

@SuppressWarnings("rawtypes")
public class IpNativeSQLDAO extends HibernateDaoSupport {
	private static final Logger logger = Logger.getLogger(IpNativeSQLDAO.class);

	public Long getNextId(Class clazz) {
		Long ret = -1l;
		try {
			AbstractEntityPersister persister = (AbstractEntityPersister) getSessionFactory().getClassMetadata(clazz);
			String sql = "select ifnull(max(" + persister.getIdentifierColumnNames()[0] + "),0)+1 from " + persister.getTableName();
			SQLQuery query = getSession().createSQLQuery(sql);
			List res = query.list();
			if (res != null && res.size() > 0)
				ret = Long.valueOf(res.get(0).toString());
		} catch (Exception e) {
			logger.error(e, e);
			throw new RuntimeException(e);
		}
		return ret;
	}

	public Long[] getNextIds(Class clazz, int num) {
		Long[] ret = new Long[num];
		try {
			AbstractEntityPersister persister = (AbstractEntityPersister) getSessionFactory().getClassMetadata(clazz);
			String sql = "select ifnull(max(" + persister.getIdentifierColumnNames()[0] + "),0)+1 from " + persister.getTableName();
			SQLQuery query = getSession().createSQLQuery(sql);
			List res = query.list();
			if (res != null && res.size() > 0)
				ret[0] = Long.valueOf(res.get(0).toString());
			for (int i = 1; i < num; i++)
				ret[i] = ret[0] + i;
		} catch (Exception e) {
			logger.error(e, e);
			throw new RuntimeException(e);
		}
		return ret;
	}

}
