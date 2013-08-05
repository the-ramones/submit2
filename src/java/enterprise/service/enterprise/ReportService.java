package enterprise.service.enterprise;

import enterprise.model.enterprise.Report;
import java.util.Date;
import java.util.List;

/**
 * Public interface for {@link enterprise.model.enterprise.Report} services
 *
 * @author Paul Kulitski
 */
public interface ReportService {

    public List<Report> getReportsBy(Date startDate, Date endDate, String performer);

    public Report insertReport(Report report);
}
