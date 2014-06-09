package za.co.idea.ip.orm.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.persister.entity.AbstractEntityPersister;

@SuppressWarnings("rawtypes")
public class IpNativeSQLDAO extends BaseHibernateDAO {
	public Long getNextId(Class clazz) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		Long ret = -1l;
		try {
			AbstractEntityPersister persister = (AbstractEntityPersister) getFactory().getSessionFactory().getClassMetadata(clazz);
			String sql = "select ifnull(max(" + persister.getIdentifierColumnNames()[0] + "),0)+1 from " + persister.getTableName();
			SQLQuery query = session.createSQLQuery(sql);
			List res = query.list();
			if (res != null && res.size() > 0)
				ret = Long.valueOf(res.get(0).toString());
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
			throw new RuntimeException(e);
		}
		return ret;
	}

}
