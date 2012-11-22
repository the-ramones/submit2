package lazy.dao.factory;

import lazy.dao.CompanyDao;
import lazy.dao.CustomerDao;
import lazy.dao.SupplierDao;

/**
 * Abstract factory pattern. Produce common methods for factories
 * 
 * @author the-ramones
 */
public abstract class DaoFactory {
    
    public static DaoFactory getDaoFactory(DaoFactoryType factoryType) {
        if (factoryType == DaoFactoryType.HibernateDaoFactory) {
            return new HibernateDaoFactory();
        } else if (factoryType == DaoFactoryType.XmlDaoFactory) {
            return new XmlDaoFactory();
        } else {       
            //TODO insert different types if factories here
            return null;
        }
    }    
    
    public abstract CompanyDao getCompanyDao();
    
    public abstract CustomerDao getCustomerDao();
    
    public abstract SupplierDao getSupplierDao();            
}
