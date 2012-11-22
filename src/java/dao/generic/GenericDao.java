package dao.generic;

import java.io.Serializable;
import java.util.List;

/**
 * Generic DAO pattern by JBoss.org
 *
 * @author the-ramones
 */
public interface GenericDao<T, ID extends Serializable> {

    /**
     *
     * @param id
     * @param lock
     * @return
     */
    T findById(ID id, boolean lock);

    List<T> findAll();

    List<T> findByExample(T exampleInstance);

    /**
     *
     * @param entity
     * @return
     */
    T makePersistent(T entity);

    void makeTransient(T entity);
}
