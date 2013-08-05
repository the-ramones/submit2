package enterprise.repository.registry;

import enterprise.model.registry.Op;
import enterprise.model.registry.Register;
import enterprise.model.registry.RegisterId;
import enterprise.model.registry.User;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * {@link org.springframework.stereotype.Repository} class implements
 * {@link enterprise.repository.registry.RegistryRepository} and is backed by
 * Hibernate library
 *
 * @author Paul Kulitski
 */
@Repository
public class HibernateRegistryRepository implements RegistryRepository {

    @Inject
    @Named("registrySessionFactory")
    SessionFactory registrySessionFactory;

    /**
     *
     * @return current Hibernate session instance
     */
    private Session currentSession() {
        return registrySessionFactory.getCurrentSession();
    }

    /**
     *
     * @param id unique identifier of a register
     * @return
     */
    @Override
    public Register getRegisterById(RegisterId id) {
        return (Register) currentSession().get(Register.class, id);
    }

    /**
     *
     * @param op an operation as search criterion
     * @return a collection of found registers
     */
    @Override
    public List<Register> getRegistersByOp(Op op) {
        Op persistedOp = (Op) currentSession().get(Op.class, op.getId());
        /*
         * Due to @OneToMany association between Registry and Op entities
         */
        return persistedOp.getRegisters();
    }

    /**
     *
     * @param user an user as search criterion
     * @return a collection of found registers
     */
    @Override
    public List<Register> getRegistersByUser(User user) {
        User persistedUser = (User) currentSession().get(User.class, user);
        /*
         * Due to @OneToMany association between Registry and User entities
         */
        return persistedUser.getRegisters();
    }

    /**
     *
     * @param startDate
     * @param endDate
     * @return a collection of found registers
     */
    @Override
    public List<Register> getRegisterByPeriod(Date startDate, Date endDate) {
        Query query = currentSession().getNamedQuery("Register.findByPeriod");
        query.setDate("startDate", startDate).setDate("endDate", endDate);
        return query.list();
    }

    /**
     *
     * @param user a user as search criterion
     * @param op a operation as search criterion
     * @param startDate start date of search
     * @param endDate end date of search
     * @return a collection of found registers
     */
    @Override
    public List<Register> getRegister(User user, Op op, Date startDate, Date endDate) {
        Query query = currentSession().getNamedQuery("Register.find");
        query.setEntity("op", op)
                .setEntity("user", user)
                .setDate("startDate", startDate)
                .setDate("endDate", endDate);
        return query.list();
    }

    /**
     *
     * @param register a register to be saved
     * @return a saved registry
     */
    @Override
    public Register saveRegister(Register register) {
        Serializable id = currentSession().save(register);
        return (Register) currentSession().get(Register.class, id);
    }
}
