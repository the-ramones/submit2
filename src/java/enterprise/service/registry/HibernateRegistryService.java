package enterprise.service.registry;

import enterprise.model.registry.Op;
import enterprise.model.registry.Register;
import enterprise.model.registry.User;
import enterprise.repository.registry.RegisterRepository;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link org.springframework.stereotype.Service} class implements
 * {@link enterprise.service.registry.RegistryService}
 *
 * @author Paul Kulitski
 */
@Service
public class HibernateRegistryService implements RegistryService {

    @Inject
    private RegisterRepository registerRepository;

    /**
     * Retrieve {@link enterprise.model.registry.Register} objects from the
     * persistent store on the basis of search criteria
     *
     * @param user an user as search criterion. If null, won't include into
     * search
     * @param op an operation as search criterion. If null, won't include into
     * search
     * @param startDate start date
     * @param endDate end date
     * @return a collection of found registers
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,
            isolation = Isolation.READ_COMMITTED,
            readOnly = true)
    public List<Register> getRegistryBy(User user, Op op, Date startDate, Date endDate) {
        if (user == null || op == null) {
            return registerRepository.getRegisterByPeriod(startDate, endDate);
        }
        return registerRepository.getRegister(user, op, startDate, endDate);
    }
}
