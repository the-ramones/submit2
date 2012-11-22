package dao.impl;

import dao.SupplierDao;
import dao.generic.impl.GenericHibernateDao;
import lazy.domain.Supplier;

/**
 *
 * @author the-ramones
 */
public class SupplierHibernateDao
        extends GenericHibernateDao<Supplier, Integer> implements SupplierDao {
}
