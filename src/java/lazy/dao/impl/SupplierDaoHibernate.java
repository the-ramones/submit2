package lazy.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lazy.dao.SupplierDao;
import lazy.domain.Supplier;
import lazy.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * RDBMS MySQL + Hibernate framework CompanyDao implementation
 *
 * @see lazy.dao.SupplierDao
 * @author the-ramones
 */
public class SupplierDaoHibernate implements SupplierDao {

    SessionFactory sf = HibernateUtil.getSessionFactory();
    Class<Supplier> aClass = Supplier.class;

    public SupplierDaoHibernate() {
    }

    @Override
    public Supplier find(Integer id) {
        Supplier supplier = null;
        Session session = sf.openSession();
        try {
            session.load(Supplier.class, id);
        } catch (HibernateException e) {
        }
        return supplier;
    }

    @Override
    public Set<Supplier> find() {
        List<Supplier> suppliers = null;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select s from Supplier s");
            suppliers = (List<Supplier>) query.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        return new HashSet(suppliers);
    }

    @Override
    public Integer save(Supplier value) {
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
    public void delete(Supplier value) {
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
    public void update(Supplier value) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.update(value);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
    }
}
