package lazy.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lazy.dao.CustomerDao;
import lazy.domain.Customer;
import lazy.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * RDBMS MySQL + Hibernate framework CompanyDao implementation
 *
 * @see lazy.dao.CustomerDao
 * @author the-ramones
 */
public class CustomerDaoHibernate implements CustomerDao {

    SessionFactory sf = HibernateUtil.getSessionFactory();
    final Class<Customer> aClass = Customer.class;

    public CustomerDaoHibernate() {
    }

    @Override
    public Customer find(Integer id) {
        Customer customer = null;
        Session session = sf.openSession();
        try {
            session.load(aClass, id);
        } catch (HibernateException e) {
        }
        return customer;
    }

    @Override
    public Set<Customer> find() {
        List<Customer> customers = null;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select c from Customer c");
            customers = (List<Customer>) query.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        return new HashSet(customers);
    }

    @Override
    public Integer save(Customer value) {
        Integer id = null;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            id = (Integer) session.save(value);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        return id;
    }

    @Override
    public void delete(Customer value) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.delete(value);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public void update(Customer value) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.merge(value);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
    }
}
