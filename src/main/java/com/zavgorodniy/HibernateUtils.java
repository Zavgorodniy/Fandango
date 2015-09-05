package com.zavgorodniy;
import com.zavgorodniy.entity.Cinema;
import com.zavgorodniy.entity.Movie;
import com.zavgorodniy.entity.Shedule;
import com.zavgorodniy.entity.Ticket;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {
    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    static {
        try {

            Configuration configuration = new Configuration();
            configuration.configure();

            configuration.addAnnotatedClass(Cinema.class);
            configuration.addAnnotatedClass(Movie.class);
            configuration.addAnnotatedClass(Ticket.class);
            configuration.addAnnotatedClass(Shedule.class);

            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();

            factory = configuration.buildSessionFactory(serviceRegistry);

        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }
}

