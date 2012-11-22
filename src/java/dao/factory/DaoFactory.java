package dao.factory;

import dao.CompanyDao;
import dao.CustomerDao;
import dao.SupplierDao;

/**
 *
 * @author the-ramones
 */
public abstract class DaoFactory {

    /**
     * Creates a standalone DAOFactory that returns unmanaged DAO
     * beans for use in any environment Hibernate has been configured
     * for. Uses HibernateUtil/SessionFactory and Hibernate context
     * propagation (CurrentSessionContext), thread-bound or transaction-bound,
     * and transaction scoped.
     */
    
    public static final Class HIBERNATE = dao.factory.HibernateDaoFactory.class;

    /**
     * Factory method for instantiation of concrete factories.
     */
    public static DaoFactory instance(Class factory) {
        try {
            return (DaoFactory) factory.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("Couldn't create DAOFactory: " + factory);
        }
    }

    // Add your DAO interfaces here
    public abstract CompanyDao getCompanyDao();

    public abstract CustomerDao getCustomerDao();

    public abstract SupplierDao getSupplierDao();
}
