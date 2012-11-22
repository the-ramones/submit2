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
 * Controller code EJB3 test-controller
 *
 * @Stateless managed bean, controller
 * @author the-ramones
 */
@Stateless
/*
 * @TransactionManagement(value=TransactionManagementType.CONTAINER)
 * Maybe, when we use CMT 
 */
public class CompanyController {

    private Integer VALID_COMPANY_INDEX = 1;
    @PersistenceContext
    private EntityManager em;
    @EJB
    CompanyDao companyDao;

    /*
     * Approach #3. EJB-CMT tie
     * CMT - container managed transaction, EJB 3.0 type
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void execute() {

        List<Customer> customers = companyDao.getCustomers(VALID_COMPANY_INDEX);
        List<Supplier> suppliers = companyDao.getSuppliers(VALID_COMPANY_INDEX);

        Company company = companyDao.findById(VALID_COMPANY_INDEX, false);
    }
    /*
     * Or in ejb-jar.xml something like this (vs Annotations)
     * 
     <enterprise-beans>
        <session>
            <display-name>A Credit-Service Bean</display-name>
            <ejb-name>CreditService</ejb-name>
            <home>creditService.ejb.CreditServiceHome</home>
            <remote>creditService.ejb.CreditServiceRemote</remote>
            <ejb-class>creditService.ejb.CreditServiceBean</ejb-class>
            <session-type>Stateless</session-type>
            <transaction-type>Container</transaction-type>
            ...
        </session>
        ...
     </enterprise-beans>
     * 
     */
}
