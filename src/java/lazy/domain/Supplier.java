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
 * Java Transfer Object. Hibernate annotation configuration 
 * @author the-ramones
 */
@Entity
@Table(name = "supplier", catalog = "lazy")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Supplier implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Integer supplierId;
    
    @Column(name = "name")
    private String name;
    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = lazy.domain.Supplier.class)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    public Supplier() {
    }

    public Supplier(Company company, String name) {
        this.company = company;
        this.name = name;
    }
    
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }    
}
