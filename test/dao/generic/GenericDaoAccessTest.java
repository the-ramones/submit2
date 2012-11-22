package dao.generic;

import dao.*;
import dao.factory.DaoFactory;
import dao.impl.*;
import java.util.List;
import lazy.domain.*;
import lazy.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test for all generic Dao access Approaches from
 * https://community.jboss.org/wiki/GenericDataAccessObjects
 *
 * @since 16.11.2012
 * @author the-ramones
 */
public class GenericDaoAccessTest {

    public static final int VALID_CUSTOMER_LIST_INDEX = 1;
    public static final int VALID_SUPPLIER_LIST_INDEX = 2;
    public static final int VALID_ENTITY_INDEX = 1;

    /**
     * Generic Hibernate framework database access. Setter style dependency
     * injection in {@link dao.generic.impl.GenericHibernateDao} by
     * {@link dao.factory.HibernateDaoFactory}
     */
    @Test
    public void hibernateDaoAccessTest() {
        // JTA: UserTransaction utx = jndiContext.lookup("UserTransaction");
        // JTA: utx.begin();

        Transaction tx = null;
        try {
            // Plain JDBC transaction handling
            tx = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
            DaoFactory factory = DaoFactory.instance(DaoFactory.HIBERNATE);

            CustomerDao customerDao = factory.getCustomerDao();
            CompanyDao companyDao = factory.getCompanyDao();
            SupplierDao supplierDao = factory.getSupplierDao();

            Company newCompany = new Company("GenericDaoAccessTest");
            Customer newCustomer = new Customer(newCompany, "GenericDaoAccessTest");
            Supplier newSupplier = new Supplier(newCompany, "GenericDaoAccessTest");

            newCompany = companyDao.makePersistent(newCompany);
            newCustomer = customerDao.makePersistent(newCustomer);
            newSupplier = supplierDao.makePersistent(newSupplier);

            assertNotNull("dao.CompanyDao doesn't access to database", newCompany);
            assertNotNull("dao.CustomerDao doesn't access to database", newCustomer);
            assertNotNull("dao.SupplierDao doesn't access to database", newSupplier);

            supplierDao.makeTransient(newSupplier);
            customerDao.makeTransient(newCustomer);
            companyDao.makeTransient(newCompany);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        }
        // JTA: utx.commit(); // Don't forget exception handling
    }

    @Test
    public void storedHibernateProcedureCompanyEntityTest() {

        Transaction tx = null;
        try {
            // Plain JDBC transaction handling
            tx = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
            DaoFactory factory = DaoFactory.instance(DaoFactory.HIBERNATE);

            List<Customer> customers = factory.getCompanyDao()
                    .getCustomers(VALID_CUSTOMER_LIST_INDEX);
            List<Supplier> suppliers = factory.getCompanyDao()
                    .getSuppliers(VALID_SUPPLIER_LIST_INDEX);

            assertNotNull(customers);
            assertNotNull(suppliers);
            assertTrue(customers.size() > 0);
            assertTrue(suppliers.size() > 0);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    @Test
    public void manualHibernateSessionDependencyInjectionTest() {

        Transaction tx = null;
        try {
            // Plain JDBC transaction handling
            tx = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();

            CompanyHibernateDao companyDao = new CompanyHibernateDao();
            CustomerHibernateDao customerDao = new CustomerHibernateDao();
            SupplierHibernateDao supplierDao = new SupplierHibernateDao();

            companyDao.setSession(session);
            customerDao.setSession(session);
            supplierDao.setSession(session);

            Company company = companyDao.findById(VALID_ENTITY_INDEX, false);
            Customer customer = customerDao.findById(VALID_ENTITY_INDEX, false);
            Supplier supplier = supplierDao.findById(VALID_ENTITY_INDEX, false);

            assertNotNull(company);
            assertNotNull(customer);
            assertNotNull(supplier);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public GenericDaoAccessTest() {
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
