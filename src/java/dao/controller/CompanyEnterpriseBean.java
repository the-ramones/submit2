package dao.controller;

import dao.CompanyDao;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lazy.domain.Company;
import lazy.domain.Customer;
import lazy.domain.Supplier;

/**
 * Enterprise Java Bean.
 * @author the-ramones
 */
@Stateless
public class CompanyEnterpriseBean {

    public CompanyEnterpriseBean() {
    }
    
    private String test = "Enterprise Company Bean";

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
    private Integer index = 995;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
    
    @PersistenceContext
    private EntityManager em;
    @EJB
    CompanyDao companyDao;

    /**
     * Approach #3. EJB-CMT tie CMT - container managed transaction, EJB 3.0
     * type
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void execute() {

        List<Customer> customers = companyDao.getCustomers(index);
        List<Supplier> suppliers = companyDao.getSuppliers(index);

        Company company = companyDao.findById(index, false);
    }
    /**
     * Or in ejb-jar.xml something like this (vs Annotations)
     *
     * <enterprise-beans> <session> <display-name>A Credit-Service
     * Bean</display-name> <ejb-name>CreditService</ejb-name>
     * <home>creditService.ejb.CreditServiceHome</home>
     * <remote>creditService.ejb.CreditServiceRemote</remote>
     * <ejb-class>creditService.ejb.CreditServiceBean</ejb-class>
     * <session-type>Stateless</session-type>
     * <transaction-type>Container</transaction-type> ... </session> ...
     * </enterprise-beans>
     *
     */
}
