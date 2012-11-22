package dao.generic.impl;

import dao.generic.GenericDao;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lazy.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

/**
 * Generic Dao under Hibernate ORM. You can create a better typed GenericDao as
 * <MODEL extends IModel<ID>, ID extends Serializable>. This is your choice
 *
 * @author the-ramones
 */
// Op.2: Change plain class to EJB3 @Stateless jave bean
//@Stateless
public abstract class GenericHibernateDao<T, ID extends java.io.Serializable> implements GenericDao<T, ID> {

    // defines persistent type for ORM Hibernate
    private Class<T> persistentClass;
    // org.hibernate.Session field for dependency injection
    private Session session;
    // Op.2: Dependency injection feature @PersistenceContex
    //@PersistenceContext
    //private EntityManager em;

    public GenericHibernateDao() {
        this.persistentClass = (Class<T>) (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        // setSession((Session) em.getDelegate());
    }
    /*
     * Session object injection method. It may inject from cunstructor
     */

    @SuppressWarnings("unchecked")
    public void setSession(Session session) {
        this.session = session;
    }

    protected Session getSession() {
        if (session == null) {
            /* Approach #1. Manual set up session object. Used in sinple project 
             and integration tests.
             */
            /*
             throw new IllegalStateException("Session has not been set on DAO before usage");
             */
            /*
             * Approach #2. Standart HibernateUtil lookup
             */
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            /*
             * Approach #3. Managed EJB 3.0 component resolve
             */
            /* 
             * setSession((Session) em.getDelegate());
             */
        }
        return session;
    }

    public Class<T> getPersistenceClass() {
        return persistentClass;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T findById(ID id, boolean lock) {
        T entity;
        //if (lock) {
        entity = (T) getSession().load(persistentClass, id /*, LockOptions.UPGRADE */);
        //} else {
        //    entity = (T) getSession().load(persistentClass, id, LockOptions.READ);
        //}
        return entity;
    }

    /*
     * Convenient method to use in implementors/descendants
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(Criterion... criterion) {
        Criteria crit = getSession().createCriteria(getPersistenceClass());
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return (List<T>) crit.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return findByCriteria();
    }

    @Override
    //@SuppressWarnings("unchecked")
    public List<T> findByExample(T exampleInstance) {
        Criteria crit = getSession().createCriteria(getPersistenceClass());
        Example example = Example.create(exampleInstance);
        // example.excludeProperty(propertyName)
        crit.add(example);
        return (List<T>) crit.list();
    }

    @Override
    public T makePersistent(T entity) {
        getSession().saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void makeTransient(T entity) {
        getSession().delete(entity);
    }

    public void flush() {
        getSession().flush();
    }

    public void clear() {
        getSession().clear();
    }
}
