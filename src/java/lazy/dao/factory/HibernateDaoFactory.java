package lazy.dao.factory;

import lazy.dao.CompanyDao;
import lazy.dao.CustomerDao;
import lazy.dao.SupplierDao;
import lazy.dao.impl.CompanyDaoHibernate;
import lazy.dao.impl.CustomerDaoHibernate;
import lazy.dao.impl.SupplierDaoHibernate;

/**
 * Hibernate Data Access provider
 *
 * @author the-ramones
 */
public class HibernateDaoFactory extends DaoFactory {

    private CompanyDaoHibernate companyDao;
    private CustomerDaoHibernate customerDao;
    private SupplierDaoHibernate supplierDao;

    protected HibernateDaoFactory() {
        this.companyDao = new CompanyDaoHibernate();
        this.customerDao = new CustomerDaoHibernate();
        this.supplierDao = new SupplierDaoHibernate();
    }

    @Override
    public CompanyDao getCompanyDao() {
        return companyDao;
    }

    @Override
    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    @Override
    public SupplierDao getSupplierDao() {
        return supplierDao;
    }
}
