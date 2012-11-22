package lazy.dao;

import java.util.Set;
import lazy.domain.Supplier;

/**
 *
 * @author the-ramones
 */
public interface SupplierDao extends GenericDao<Integer, Supplier> {

    @Override
    public Supplier find(Integer id);

    @Override
    public Set<Supplier> find();

    @Override
    public Integer save(Supplier value);

    @Override
    public void delete(Supplier value);

    @Override
    public void update(Supplier value);
}
