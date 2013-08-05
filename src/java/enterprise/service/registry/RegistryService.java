package enterprise.service.registry;

import enterprise.model.registry.Op;
import enterprise.model.registry.Register;
import enterprise.model.registry.User;
import java.util.Date;
import java.util.List;

/**
 * Public interface for {@link enterprise.model.registry.Register} services
 *
 * @author Paul Kulitski
 */
public interface RegistryService {

    public List<Register> getRegistryBy(User user, Op op, Date startDate, Date endDate);
}
