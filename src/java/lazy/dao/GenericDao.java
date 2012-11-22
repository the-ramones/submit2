package lazy.dao;

import java.util.Set;

/**
 * Common DAO interface used JDK 5.0 generics
 * 
 * @author the-ramones
 */
public interface GenericDao<K extends java.io.Serializable, T> {

    public T find(K id);

    public Set<T> find();

    public K save(T value);

    public void delete(T value);

    public void update(T value);
}
