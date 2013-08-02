package enterprise.model.registry;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Paul Kulitski
 */
public class RegistryHibernateUtil {

    private static final SessionFactory sessionFactory;
    private static final String REGISTRY_HIBERNATE_CONFIGIRATION = "/registry/hibernate/registry.cfg.xml";

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure(REGISTRY_HIBERNATE_CONFIGIRATION).buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}