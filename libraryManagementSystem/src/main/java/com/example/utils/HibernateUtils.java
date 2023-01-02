package com.example.utils;

import com.example.entity.Member;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.entity.Book;

public class HibernateUtils {

    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {
        try {
            if (sessionFactory == null) {
                // create configuration
                Configuration configuration = new Configuration();
                configuration.configure("hibernate.cfg.xml");
                configuration.addAnnotatedClass(Book.class);
                configuration.addAnnotatedClass(Member.class);

                // create session factory
                sessionFactory = configuration.buildSessionFactory();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println("SessionFactory object not created...");
        }
        return sessionFactory;
    }
}
