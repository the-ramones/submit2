package lazy.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Data Transfer Object. Hibernate annotation configuration
 *
 * @author the-ramones
 */
@Entity
@Table(name = "customer", catalog = "lazy")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;
    
    @Column(name = "name")
    private String name;
    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = lazy.domain.Company.class)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    public Customer() {
    }

    public Customer(Company company, String name) {
        this.company = company;
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
