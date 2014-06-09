package za.co.idea.ip.orm.dao;

import org.hibernate.Session;

import za.co.idea.ip.orm.util.HibernateSessionFactory;

/**
 * Data access object (DAO) for domain model
 * 
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {
	private HibernateSessionFactory factory;

	public Session getSession() {
		return factory.getSession();
	}

	public HibernateSessionFactory getFactory() {
		return factory;
	}

	public void setFactory(HibernateSessionFactory factory) {
		this.factory = factory;
	}

}