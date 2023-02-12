package utils;

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
                Configuration configuration = new Configuration().configure();

                /*configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Brand.class);
                configuration.addAnnotatedClass(Model.class);
                configuration.addAnnotatedClass(Client.class);
                configuration.addAnnotatedClass(Car.class);
                configuration.addAnnotatedClass(Worker.class);
                configuration.addAnnotatedClass(Work.class);*/

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
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
