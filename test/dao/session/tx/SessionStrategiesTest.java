package dao.session.tx;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import lazy.domain.Company;
import lazy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Different session handling strategies
 *
 * @author the-ramones
 */
public class SessionStrategiesTest {

    /*
     * Use BMT transaction demarcation from UserTransaction#begin()
     * and UserTransaction#commit()
     */
    @Test
    public void currentSessionPerRequestTest() throws Exception {
        UserTransaction tx = null;
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try {
            tx = (UserTransaction) new InitialContext()
                    .lookup("java:comp/UserTransaction");

            tx.begin();

            // Do some work
            factory.getCurrentSession().load(lazy.domain.Company.class, new Integer(1));
            Company company = new Company();
            factory.getCurrentSession().persist(company);
            factory.getCurrentSession().delete(company);
            tx.commit();

        } catch (NamingException e) {
            System.err.println("Coudn't find user transaction");
            System.err.println(e);
        } catch (NotSupportedException ex) {
            System.err.println(ex);
        } catch (SystemException ex) {
            Logger.getLogger(SessionStrategiesTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            tx.rollback();
            throw e; // or display error message
        }
    }

    @Test
    public void openSessionPerRequestTest() throws Exception {
        /*
         * InitialContext supported by J2EE server.
         * Binded static system settings into runtime
         * objects
         */
        UserTransaction tx = (UserTransaction) new InitialContext()
                .lookup("java:comp/UserTransaction");
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        try {
            tx.begin();

            // Do some work
            factory.getCurrentSession().load(lazy.domain.Company.class, new Integer(1));
            Company company = new Company();
            factory.getCurrentSession().persist(company);
            factory.getCurrentSession().delete(company);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e; // or display error message
        } finally {
            session.close();
        }
    }

    /*
     * Plain JDBC approach for transation demarcation
     */
    /*
     try {
     factory.getCurrentSession().beginTransaction();
 
     // Do some work
     factory.getCurrentSession().load(...);
     factory.getCurrentSession().persist(...);
 
     factory.getCurrentSession().getTransaction().commit();
     }
     catch (RuntimeException e) {
     factory.getCurrentSession().getTransaction().rollback();
     throw e; // or display error
     */
    @Test
    public void test() {
    }

    public SessionStrategiesTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
