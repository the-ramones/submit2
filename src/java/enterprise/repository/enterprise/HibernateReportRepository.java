package enterprise.repository.enterprise;

import enterprise.model.enterprise.Report;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;

/**
 * {@link org.springframework.stereotype.Repository} class implements
 * {@link enterprise.repository.registry.ReportRepository} and is backed by
 * Hibernate library
 *
 * @author Paul Kulitski
 */
@Repository
public class HibernateReportRepository implements ReportRepository {

    @Inject
    @Named("enterpriseSessionFactory")
    SessionFactory enterpriseSessionFactory;

    /**
     *
     * @return current Hibernate session instance
     */
    private Session currentSession() {
        return enterpriseSessionFactory.getCurrentSession();
    }

    /**
     *
     * @param id unique identifier of a report
     * @return a found report
     */
    @Override
    public Report getReportById(Long id) {
        return (Report) currentSession().get(Report.class, id);
    }

    /**
     *
     * @param startDate start date
     * @param endDate end date
     * @param performer a performer name as search criterion
     * @return a collection of found reports
     */
    @Override
    public List<Report> getReports(Date startDate, Date endDate, String performer) {
        Query query = currentSession().getNamedQuery("Report.find");
        query.setDate("startDate", startDate)
                .setDate("endDate", endDate)
                .setString("performer", performer);
        return query.list();
    }

    /**
     *
     * @param report a report object to be saved
     * @return a saved report object
     */
    @Override
    public Report saveReport(Report report) {
        Serializable id = currentSession().save(report);
        return (Report) currentSession().get(Report.class, id);
    }
}
