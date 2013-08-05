package enterprise.repository.registry;

import enterprise.model.registry.User;
import java.io.Serializable;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * {@link org.springframework.stereotype.Repository} class implements
 * {@link enterprise.repository.registry.UserRepository} and is backed by
 * Hibernate library
 *
 * @author Paul Kulitski
 */
@Repository
public class HibernateUserRepository implements UserRepository {

    @Inject
    @Named("registrySessionfactory")
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
     * @param id unique identifier of an user
     * @return a found user
     */
    @Override
    public User getUserById(Integer id) {
        return (User) currentSession().get(User.class, id);
    }

    /**
     *
     * @param fullname a fullname of an user
     * @return a found user
     */
    @Override
    public User getUserByName(String fullname) {
        Query query = currentSession().getNamedQuery("User.findByFullname");
        /*
         * Fullname field marked as a unique key in database, so result will be 
         * 0-st object or null
         */
        return (User) query.setString("fullname", fullname).list().get(0);
    }

    /**
     *
     * @param user an user to be saved
     * @return an saved user
     */
    @Override
    public User saveUser(User user) {
        Serializable id = currentSession().save(user);
        return (User) currentSession().get(User.class, id);
    }
}
