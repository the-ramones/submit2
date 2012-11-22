package dao;

import dao.generic.GenericDao;
import lazy.domain.Customer;

/**
 * Common interface for all kind o DAO layer data sources
 * @author the-ramones
 */
public interface CustomerDao extends GenericDao<Customer, Integer> {
}
