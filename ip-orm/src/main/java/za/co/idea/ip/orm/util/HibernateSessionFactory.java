package za.co.idea.ip.orm.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Configures and provides access to Hibernate sessions, tied to the current
 * thread of execution. Follows the Thread Local Session pattern, see
 * {@link http://hibernate.org/42.html }.
 */
public class HibernateSessionFactory {

	/**
	 * Location of hibernate.cfg.xml file. Location should be on the classpath
	 * as Hibernate uses #resourceAsStream style lookup for its configuration
	 * file. The default classpath location of the hibernate config file is in
	 * the default package. Use #setConfigFile() to update the location of the
	 * configuration file for the current session.
	 */
	private org.hibernate.SessionFactory sessionFactory;
	private Session session;

	/**
	 * Returns the ThreadLocal Session instance. Lazy initialize the
	 * <code>SessionFactory</code> if needed.
	 * 
	 * @return Session
	 * @throws HibernateException
	 */
	public Session getSession() throws HibernateException {
		if (session == null || !session.isOpen()) {
			session = (sessionFactory != null) ? sessionFactory.openSession() : null;
		}
		return session;
	}

	/**
	 * return session factory
	 * 
	 */
	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}