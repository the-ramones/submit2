package enterprise.repository.enterprise;

import enterprise.model.enterprise.Report;
import java.util.Date;
import java.util.List;

/**
 * Public interface for repositories of
 * {@link enterprise.model.enterprise.Report} instances
 *
 * @author Paul Kulitski
 */
public interface ReportRepository {

    public Report getReportById (Long id);

    public List<Report> getReports(Date startDate, Date endDate, String performer);

    public Report saveReport(Report report);
}
