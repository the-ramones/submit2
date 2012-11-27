package dao.factory;

import hb.tools.annotation.AnnotationHibernateUtil;
import hb.tools.annotation.Company;
import hb.tools.annotation.Customer;
import java.io.Serializable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Annotation configuration
 *
 * @author the-ramones
 */
public class AnnotationConfigurationTest {
    
    private Log log = LogFactory.getLog(AnnotationConfigurationTest.class);

    private static final String TEST_ANNOTATION_COSTOMER_NAME = "testAnnotation";

    @Test
    public void testAnnotationCOnfiguration() {
        Transaction tx = null;
        Session se = null;
        Customer newCustomer;
        Company newCompany;
        Integer companyIndex = null;
        Integer customerIndex = null;
        try {
            se = AnnotationHibernateUtil.getSessionFactory().getCurrentSession();
            tx = se.beginTransaction();
            newCompany = new Company(TEST_ANNOTATION_COSTOMER_NAME);
            companyIndex = (Integer) se.save(newCompany);
            newCustomer = new Customer(newCompany, TEST_ANNOTATION_COSTOMER_NAME);
            customerIndex = (Integer) se.save(newCustomer);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            try { 
                se = AnnotationHibernateUtil.getSessionFactory().getCurrentSession();
                se.beginTransaction();
                se.delete(se.load(Customer.class, (Serializable) customerIndex, LockMode.FORCE));
                se.delete(se.load(Company.class, (Serializable) companyIndex, LockMode.FORCE));
                se.getTransaction().commit();
            } catch (Exception e) {
                log.warn("'testAnnotation' instances cannot be remobed from database: ", e);
            }
        }
    }

    public AnnotationConfigurationTest() {
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
}
