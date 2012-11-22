package lazy.dao;

import java.util.Set;
import lazy.domain.Company;

/**
 *
 * @author the-ramones
 */
public interface CompanyDao extends GenericDao<Integer, Company> {

    @Override
    public Company find(Integer id);

    @Override
    public Set<Company> find();

    @Override
    public Integer save(Company value);

    @Override
    public void delete(Company value);

    @Override
    public void update(Company value);
}
