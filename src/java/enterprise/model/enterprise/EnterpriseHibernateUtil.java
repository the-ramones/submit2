package enterprise.model.enterprise;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author paul
 */
public class EnterpriseHibernateUtil {

    private static final SessionFactory sessionFactory;

    public static final String HIBERNATE_CONFIGURATION_PATH = "/enterprise/hibernate/enterprise.cfg.xml";

    static {
        try {
            sessionFactory = new AnnotationConfiguration().configure(HIBERNATE_CONFIGURATION_PATH).buildSessionFactory();
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
