package com.utility;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {
    private static Configuration configuration;
    private static SessionFactory sessionFactory;
    private static StandardServiceRegistry standardServiceRegistry;
    
    static {
        try {
           configuration = new Configuration().configure("/hibernate/hibernate.cfg.xml");
           StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
           standardServiceRegistryBuilder.applySettings(configuration.getProperties());
           standardServiceRegistry = standardServiceRegistryBuilder.build();
           sessionFactory = configuration.buildSessionFactory(standardServiceRegistry);
        } catch (Exception e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    } 
}
