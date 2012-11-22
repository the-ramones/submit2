package dao.factory;

import hb.tools.Company;
import hb.tools.Customer;
import hb.tools.ToolsHibernateUtil;
import java.io.Serializable;
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
 *
 * @author the-ramones
 */
public class XmlMappingConfiguration {

    public static final String TEST_MAPPING = "testMapping";

    /**
     * Test hb.tools packages hibernate configuration
     */
    @Test
    public void hbToolsConfigurationTest() {
        Transaction tx = null;
        Session se = null;
        Customer newCustomer;
        Company newCompany;
        Integer companyIndex = null;
        Integer customerIndex = null;
        try {
            se = ToolsHibernateUtil.getSessionFactory().getCurrentSession();
            tx = se.beginTransaction();
            newCompany = new Company(TEST_MAPPING);
            companyIndex = (Integer) se.save(newCompany);
            newCustomer = new Customer(newCompany, TEST_MAPPING);
            customerIndex = (Integer) se.save(newCustomer);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            try {
                se.delete(se.load(Customer.class, (Serializable) customerIndex, LockMode.FORCE));
                se.delete(se.load(Company.class, (Serializable) companyIndex, LockMode.FORCE));
                if (se != null) {
                    se.close();
                }
            } catch (Exception e) {
            }
        }

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}
