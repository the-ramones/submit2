package lazy.dao.impl;

import java.util.Set;
import lazy.dao.CustomerDao;
import lazy.domain.Customer;

/**
 *
 * @author the-ramones
 */
public class CustomerDaoXml implements CustomerDao {

    public CustomerDaoXml() {
    }

    @Override
    public Customer find(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Customer> find() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer save(Customer value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Customer value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Customer value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
