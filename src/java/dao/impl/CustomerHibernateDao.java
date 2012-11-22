package dao.impl;

import dao.CustomerDao;
import dao.generic.GenericHibernateDao;
import lazy.domain.Customer;

/**
 *
 * @author the-ramones
 */
public class CustomerHibernateDao
        extends GenericHibernateDao<Customer, Integer> implements CustomerDao {
}
