package enterprise.repository.registry;

import enterprise.model.registry.Op;
import enterprise.model.registry.Register;
import enterprise.model.registry.RegisterId;
import enterprise.model.registry.User;
import java.util.Date;
import java.util.List;

/**
 * Public interface for repositories of
 * {@link enterprise.model.registry.Register} instances
 *
 * @author Paul Kulitski
 */
public interface RegistryRepository {

    public Register getRegisterById(RegisterId id);
    
    public List<Register> getRegistersByOp(Op op);
    
    public List<Register> getRegistersByUser(User user);

    public List<Register> getRegisterByPeriod(Date startDate, Date endDate);
    
    public List<Register> getRegister(User user, Op op, Date startDate, Date endDate);
            
    public Register saveRegister(Register register);
   
}
