package io.github.bogdanpredescu.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import io.github.bogdanpredescu.restsample.models.User;

public class DBService {
	
	private static Session dbSession = null;
    
    public static Session getDbSession() {
		return dbSession;
	}

	private static SessionFactory dbSessionFactory = null;
    
    public static void start()
    {
    	dbSessionFactory = new Configuration()
				.configure()
				.addAnnotatedClass(User.class)
				.buildSessionFactory();
        dbSession = dbSessionFactory.openSession();
    }

    public static void stop()
    {
    	if (dbSession != null)
    	{
    		dbSession.close();
    	}
    	if (dbSessionFactory != null)
    	{
    		dbSessionFactory.close();
    	}
    }
    
}
