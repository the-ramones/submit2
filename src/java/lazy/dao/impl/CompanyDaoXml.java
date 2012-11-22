package lazy.dao.impl;

import java.util.Set;
import lazy.dao.CompanyDao;
import lazy.domain.Company;

/**
 *
 * @author the-ramones
 */
public class CompanyDaoXml implements CompanyDao {

    public CompanyDaoXml() {
    }

    @Override
    public Company find(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Company> find() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer save(Company value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Company value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Company value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
