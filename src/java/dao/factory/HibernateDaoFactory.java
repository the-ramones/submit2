package dao.factory;

import dao.CompanyDao;
import dao.CustomerDao;
import dao.SupplierDao;
import dao.generic.GenericHibernateDao;
import dao.impl.CompanyHibernateDao;
import dao.impl.CustomerHibernateDao;
import dao.impl.SupplierHibernateDao;
import lazy.util.HibernateUtil;
import org.hibernate.Session;

/**
 * Factory pattern, non-abstract Factory pattern
 *
 * @author the-ramones
 */
public class HibernateDaoFactory extends DaoFactory {

    /*
     * Generic DAO object instantiator
     */
    private GenericHibernateDao instantiateDao(Class daoClass) {
        try {
            GenericHibernateDao dao = (GenericHibernateDao) daoClass.newInstance();
            // setter org.hibernate.Session dependency injection
            dao.setSession(getCurrentSession());
            return dao;
        } catch (Exception ex) {
            throw new RuntimeException("Could't instantiate DAO object: " + daoClass, ex);
        }
    }

    /*
     * You could override this method if you don't want to use HibernateUtil
     */
    protected Session getCurrentSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

    @Override
    public CompanyDao getCompanyDao() {
        return (CompanyDao) instantiateDao(CompanyHibernateDao.class);
    }

    @Override
    public CustomerDao getCustomerDao() {
        return (CustomerDao) instantiateDao(CustomerHibernateDao.class);
    }

    @Override
    public SupplierDao getSupplierDao() {
        return (SupplierDao) instantiateDao(SupplierHibernateDao.class);
    }
    Comparable c = null;
    /*
     * Inline concrete DAO implementations with no business-related data access methods.
     * If we use public static nested classes, we can centralize all of them in one source file.
     */
//    public static class SomeDaoHibernate
//            extends GenericHibernateDao<Some, Long>
//            implements SomeDao {
//    }
//
//    public static class AnotherDaoHibernate
//            extends GenericHibernateDao<Another, Long>
//            implements AnotherDao {
//    }
    /*
     * Constroller code snippet
     */
}
