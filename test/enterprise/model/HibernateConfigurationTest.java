package enterprise.model;

import enterprise.model.enterprise.Report;
import enterprise.model.enterprise.EnterpriseHibernateUtil;
import org.junit.Test;
import static org.junit.Assert.*;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import enterprise.model.registry.Register;
import enterprise.model.registry.RegistryHibernateUtil;

/**
 *
 * @author paul
 */
public class HibernateConfigurationTest {

    public HibernateConfigurationTest() {
    }

    /**
     * Test if a 'enterprise' hibernate session factory is configured properly
     */
    @Test
    public void enterpriseHibernateSessionFactoryTest() {
        SessionFactory sessionFactory = EnterpriseHibernateUtil.getSessionFactory();

        assertNotNull(sessionFactory);

        Session currentSession = sessionFactory.openSession();

        assertNotNull(currentSession);

        Long id = Long.valueOf(1L);
        Report report = (Report) currentSession.get(Report.class, id);

        assertNotNull(report);
        assertEquals(report.getId(), id);

        String expPerformer = "John Travolta";
        assertEquals(report.getPerformer(), expPerformer);
        
        currentSession.close();
        sessionFactory.close();
    }

   /**
     * Test if a 'enterprise' hibernate session factory is configured properly
     */
    @Test
    public void registryHibernateSessionFactoryTest() {
        SessionFactory sessionFactory = RegistryHibernateUtil.getSessionFactory();

        assertNotNull(sessionFactory);

        Session currentSession = sessionFactory.openSession();

        assertNotNull(currentSession);

        Long id = Long.valueOf(1L);
        Register register = (Register) currentSession.get(Register.class, id);

        String expOpTitle = "INSERT";
        assertEquals(register.getOp().getTitle(), expOpTitle);

        assertNotNull(register);
        assertEquals(register.getId(), id);

        currentSession.close();
        sessionFactory.close();

    }
}
