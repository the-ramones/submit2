package enterprise.hibernate;
// Generated Jul 14, 2013 6:56:46 PM by Hibernate Tools 3.2.1.GA

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//import javax.persistence.Cacheable;

/**
 * Reports generated by hbm2java
 */
@Entity
@Table(name = "reports", catalog = "enterprise")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Report implements java.io.Serializable {

    private Long id;
    private Date startDate;
    private Date endDate;
    private String performer;
    private String activity; com.mysql.jdbc.jdbc2.optional.MysqlXADataSource
    
    public Report() {
    }

    public Report(Date startDate, String performer, String activity) {
        this.startDate = startDate;
        this.performer = performer;
        this.activity = activity;
    }

    public Report(Date startDate, Date endDate, String performer, String activity) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.performer = performer;
        this.activity = activity;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "startDate", nullable = false, length = 10)
    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "endDate", length = 10)
    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "performer", nullable = false)
    public String getPerformer() {
        return this.performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    @Column(name = "activity", nullable = false)
    public String getActivity() {
        return this.activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
