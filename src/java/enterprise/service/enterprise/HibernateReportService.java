package enterprise.service.enterprise;

import enterprise.model.enterprise.Report;
import enterprise.model.registry.Register;
import enterprise.repository.enterprise.ReportRepository;
import enterprise.repository.registry.OpRepository;
import enterprise.repository.registry.RegisterRepository;
import enterprise.repository.registry.UserRepository;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link org.springframework.stereotype.Service} class implements
 * {@link enterprise.service.registry.ReportService}
 *
 * @author Paul Kulitski
 */
@Service
public class HibernateReportService implements ReportService {

    @Inject
    private ReportRepository reportRepository;
    @Inject
    private RegisterRepository registerRepository;
    @Inject
    private OpRepository opRepository;
    @Inject
    private UserRepository userRepository;

    /**
     * Retrieve a collection of reports by criteria
     *
     * @param startDate start date
     * @param endDate end date
     * @param performer a performer as search criterion
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.READ_COMMITTED,
            readOnly = true)
    public List<Report> getReportsBy(Date startDate, Date endDate, String performer) {
        Register register = new Register();
        register.setOp(opRepository.getOpByTitle("READ"));
        /*
         * TODO: implement user authentication service
         * @Inject authenticationService authService
         * User currentUser = authService.getCurrentUser();
         * register.setUser(currentUser)
         */
        register.setUser(userRepository.getUserById(Integer.valueOf(1)));
        register.setRecordTime(new Date());
        registerRepository.saveRegister(register);
        return reportRepository.getReports(startDate, endDate, performer);
    }

    /**
     * Saves a report into a persistent store and registers an 'INSERT'
     * operation into a registry
     *
     * @param report a report to be saved
     * @return a saved report
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.SERIALIZABLE)
    public Report insertReport(Report report) {
        Register register = new Register();
        register.setOp(opRepository.getOpByTitle("INSERT"));
        /*
         * TODO: implement user authentication service
         * @Inject authenticationService authService
         * User currentUser = authService.getCurrentUser();
         * register.setUser(currentUser)
         */
        register.setUser(userRepository.getUserById(Integer.valueOf(1)));
        register.setRecordTime(new Date());
        registerRepository.saveRegister(register);
        return reportRepository.saveReport(report);
    }
}
