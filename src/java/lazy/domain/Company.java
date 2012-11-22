package lazy.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 * Java Transfer Object. Hibernate annotation configuration
 *
 * @author the-ramones
 */
@Entity
@Table(name = "company", catalog = "lazy")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "Company.findSuppliers",
    query = "select suppliers from Company comp WHERE comp.companyId = :id"),
    @NamedQuery(name = "Company.findCustomers",
    query = "select customers from Company comp WHERE comp.companyId = :id")
})
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Integer companyId;
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL,
    fetch = FetchType.LAZY,
    mappedBy = "company",
    targetEntity = lazy.domain.Supplier.class)
    @XmlElementWrapper // Let's wrap collection in additional tag 
    @XmlElement(name = "supplier")
    private Set<Supplier> suppliers = new HashSet<Supplier>(0);
    @OneToMany(cascade = CascadeType.ALL,
    fetch = FetchType.LAZY,
    mappedBy = "company",
    targetEntity = lazy.domain.Customer.class)
    @XmlElementWrapper // Let's wrap collection in additional tag 
    @XmlElement(name = "customer")
    private Set<Customer> customers = new HashSet<Customer>();

    public Company() {
    }

    public Company(String name) {
        this.name = name;
        this.customers = new HashSet<Customer>(0);
        this.suppliers = new HashSet<Supplier>(0);
    }

    public Company(String name, Set<Customer> customers, Set<Supplier> suppliers) {
        this.name = name;
        this.customers = customers;
        this.suppliers = suppliers;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
/**
 * OneToOne: Stock ~ StockDetails
 *
 * @OneToOne(fetch = FetchType.LAZY, mappedBy = "stock", cascade =
 * CascadeType.ALL)
 *
 * @OneToOne(fetch = FetchType.LAZY)
 * @PrimaryKeyJoinColumn
 *
 * OneToMany: Stock -< StockDailyRecord
 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "stock")
 *
 * @ManyToOne(fetch = FetchType.LAZY)
 * @JoinColumn(name = "STOCK_ID", nullable = false)
 *
 * ManyToMany: Stock -< StockCaterogies >- Categories
 * @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
 * @JoinTable(name = "stock_category", catalog = "mkyongdb", joinColumns = {
 * @JoinColumn(name = "STOCK_ID", nullable = false, updatable = false) },
 * inverseJoinColumns = {
 * @JoinColumn(name = "CATEGORY_ID", nullable = false, updatable = false) })
 *
 * @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
 *
 */
