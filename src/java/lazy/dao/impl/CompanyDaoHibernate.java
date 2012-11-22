package lazy.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lazy.dao.CompanyDao;
import lazy.domain.Company;
import lazy.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * RDBMS MySQL + Hibernate framework CompanyDao implementation
 * @see lazy.dao.CompanyDao
 * @author the-ramones
 */
public class CompanyDaoHibernate implements CompanyDao {
    
    SessionFactory sf = HibernateUtil.getSessionFactory();
    final Class<Company> aClass = Company.class;
        
    public CompanyDaoHibernate() {
    }

    @Override
    public Company find(Integer id) {
        Company company = null;
        Session session = sf.openSession();
        try { 
            company = (Company) session.load(aClass, id);
        } catch(HibernateException e) {            
        }
        return company;
    }

    @Override
    public Set<Company> find() {
        List<Company> companies = null;
        Session session = sf.openSession();
        try { 
            session.beginTransaction();
            Query query = session.createQuery("select c from Company c");
            companies = (List<Company>) query.list();
            session.getTransaction().commit();
        } catch(HibernateException e) { 
            session.getTransaction().rollback();
        }
        return new HashSet<Company>(companies);
    }
    
    public Set<Company> findEagerly(String fetchProfile) {
        List<Company> companies = null;
        Session session = sf.openSession();
        try { 
            //session.enableFetchProfile(fetchProfile);
            session.beginTransaction();
            Query query = session.createQuery("select c from Company c");
            companies = (List<Company>) query.list();
            session.getTransaction().commit();
            //session.disableFetchProfile(fetchProfile);
        } catch(HibernateException e) { 
            session.getTransaction().rollback();
        }
        return new HashSet<Company>(companies);
    }

    @Override
    public Integer save(Company value) {
        Integer id = null;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            id = (Integer) session.save(value);
            session.getTransaction().commit();
        } catch(HibernateException e) {  
            session.getTransaction().rollback();
        }
        return id;
    }

    @Override
    public void delete(Company value) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.delete(value);
            session.getTransaction().commit();
        } catch(HibernateException e) {            
            session.getTransaction().rollback();
        }
    }

    @Override
    public void update(Company value) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.merge(value);
            session.getTransaction().commit();
        } catch(HibernateException e) {  
            session.getTransaction().rollback();
        }
    }
}
