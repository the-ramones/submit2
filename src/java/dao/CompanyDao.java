package dao;

import dao.generic.GenericDao;
import java.util.List;
import lazy.domain.Company;
import lazy.domain.Customer;
import lazy.domain.Supplier;

/**
 *
 * @author the-ramones
 */
public interface CompanyDao extends GenericDao<Company, Integer> {
    
    public static final String QUERY_SUPPLIERS = "Company.findSuppliers";
    
    public static final String QUERY_CUSTOMERS = "Company.findCustomers";
    
    public List<Supplier> getSuppliers(Integer itemId);
    
    public List<Customer> getCustomers(Integer itemId);
}
