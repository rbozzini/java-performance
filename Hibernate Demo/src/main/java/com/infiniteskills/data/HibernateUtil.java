package com.infiniteskills.data;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final String HIBERNATE_CFG_XML = "hibernate.cfg.xml";
	private static SessionFactory sessionFactory = buidlSessionFactory();

	private static SessionFactory buidlSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure(HIBERNATE_CFG_XML);

			return configuration.buildSessionFactory(
					new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Errors while building session factory");
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
