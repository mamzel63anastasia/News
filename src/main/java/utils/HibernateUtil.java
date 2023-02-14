package utils;

import models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = buildSessionFactory();
        } catch (Throwable t) {
            throw t;
        }
    }


    private static SessionFactory buildSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration conf = new Configuration().configure();
                conf.addAnnotatedClass(User.class);
                conf.addAnnotatedClass(Producer.class);
                conf.addAnnotatedClass(Substance.class);
                conf.addAnnotatedClass(Medicament.class);
                conf.addAnnotatedClass(Order.class);



                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(conf.getProperties()).build();

                sessionFactory = conf.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
