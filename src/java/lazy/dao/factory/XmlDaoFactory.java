package lazy.dao.factory;

import lazy.dao.CompanyDao;
import lazy.dao.CustomerDao;
import lazy.dao.SupplierDao;
import lazy.dao.impl.CompanyDaoXml;
import lazy.dao.impl.CustomerDaoXml;
import lazy.dao.impl.SupplierDaoXml;

/**
 * Xml API Data Access provider
 * 
 * @author the-ramones
 */
public class XmlDaoFactory extends DaoFactory {

    private CompanyDao companyDao;
    private CustomerDao customerDao;
    private SupplierDao supplierDao;

    protected XmlDaoFactory() {
        this.companyDao = new CompanyDaoXml();
        this.customerDao = new CustomerDaoXml();
        this.supplierDao = new SupplierDaoXml();
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
