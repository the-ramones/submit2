package dao.impl;

import dao.CompanyDao;
import dao.generic.impl.GenericHibernateDao;
import java.util.List;
import lazy.domain.Company;
import lazy.domain.Customer;
import lazy.domain.Supplier;
import org.hibernate.Query;

/**
 *
 * @author the-ramones
 */
public class CompanyHibernateDao
        extends GenericHibernateDao<Company, Integer> implements CompanyDao {

    @Override
    public List<Supplier> getSuppliers(Integer itemId) {
        Query q = getSession().getNamedQuery(CompanyDao.QUERY_SUPPLIERS);
        q.setInteger("id", itemId);
        return q.list();
    }

    @Override
    public List<Customer> getCustomers(Integer itemId) {
        Query q = getSession().getNamedQuery(CompanyDao.QUERY_CUSTOMERS);
        q.setInteger("id", itemId);
        return q.list();
    }
}
