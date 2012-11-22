package dao.factory;

import dao.CompanyDao;
import dao.CustomerDao;
import dao.SupplierDao;
import lazy.domain.Company;
import lazy.domain.Customer;
import lazy.domain.Supplier;
import lazy.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test case, transaction handling
 *
 * @author the-ramones
 */
public class DaoFactoryTest {

    private static final String OHAIO__DEVELOPMENT_AG = "Ohaio Development AG";

    /**
     * Test of getCustomerDao method, of class DaoFactory.
     */
    @Test
    public void testGetCustomerDao() throws Exception {
        //EJB3 CMT: @TransactionAttribute(TransactionAttributeType.REQUIRED)

        // public void execute() {

        // JTA: UserTransaction utx = jndiContext.lookup("UserTransaction");
        // JTA: utx.begin();
        Transaction tx = null;
        Session se = null;
        try {
            // Plain JDBC: 
            se = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = se.beginTransaction();

            DaoFactory factory = DaoFactory.instance(DaoFactory.HIBERNATE);
            CustomerDao customerDao = factory.getCustomerDao();
            SupplierDao supplierDao = factory.getSupplierDao();
            CompanyDao companyDao = factory.getCompanyDao();

            Customer customer = customerDao.findById(1, false);
            Supplier supplier = supplierDao.findById(1, false);
            Company company = companyDao.findById(1, false);

            Customer newCustomer = new Customer();
            newCustomer.setCompany(company);
            newCustomer.setName(OHAIO__DEVELOPMENT_AG);
            customerDao.makePersistent(newCustomer);

            //  JTA: utx.commit();
            //  Plain JDBC: HibernateUtil.getCurrentSession().getTransaction().commit(); 
            tx.commit();
            //HibernateUtil.getSessionFactory().getCurrentSession().flush();
        } catch (HibernateException e) {
            // Don't forget exception handling
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            try {
                if (se != null) {
                    se.close();
                }
            } catch (Exception e) {
            }
        }
    }

    @Test
    public void testDeleteRecentEntities() {
        Transaction tx = null;
        try {
            tx = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
            DaoFactory factory = DaoFactory.instance(DaoFactory.HIBERNATE);
            CustomerDao customerDao = factory.getCustomerDao();
            CompanyDao companyDao = factory.getCompanyDao();

            customerDao.makeTransient(
                    customerDao.findByExample(
                    new Customer(companyDao.findById(1, false), OHAIO__DEVELOPMENT_AG)).get(0));

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public DaoFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
}
