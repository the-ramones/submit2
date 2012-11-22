package lazy.dao;

import java.util.Set;
import lazy.domain.Customer;

/**
 *
 * @author the-ramones
 */
public interface CustomerDao extends GenericDao<Integer, Customer> {

    @Override
    public Customer find(Integer id);

    @Override
    public Set<Customer> find();

    @Override
    public Integer save(Customer value);

    @Override
    public void delete(Customer value);

    @Override
    public void update(Customer value);
}
