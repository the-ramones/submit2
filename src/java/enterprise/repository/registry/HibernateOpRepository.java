package enterprise.repository.registry;

import enterprise.model.registry.Op;
import java.io.Serializable;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * {@link org.springframework.stereotype.Repository} class implements
 * {@link enterprise.repository.registry.OpRepository} and is backed by
 * Hibernate library
 *
 * @author Paul Kulitski
 */
@Repository
public class HibernateOpRepository implements OpRepository {

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
     * @param id unique identifier of an operation
     * @return a found operation
     */
    @Override
    public Op getOpById(Integer id) {
        return (Op) currentSession().get(Op.class, id);
    }

    /**
     *
     * @param title a title of an operation
     * @return a found operation
     */
    @Override
    public Op getOpByTitle(String title) {
        Query query = currentSession().getNamedQuery("Op.findByTitle");
        /*
         * Title field marked as a unique key in database, so result will be 
         * 0-st object or null
         */
        Op op = (Op) query.setString("title", title).list().get(0);
        return op;
    }

    /**
     *
     * @param op an operation to be saved
     * @return a saved operation
     */
    @Override
    public Op saveOp(Op op) {
        Serializable id = currentSession().save(op);
        return (Op) currentSession().get(Op.class, id);
    }
}
