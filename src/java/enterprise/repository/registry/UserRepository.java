package enterprise.repository.registry;

import enterprise.model.registry.User;

/**
 * Public interface for a repository of {@link enterprise.model.registry.User}
 * instances
 *
 * @author Paul Kulitski
 */
public interface UserRepository {

    /**
     *
     * @param id unique identifier of an user
     * @return a found user
     */
    public User getUserById(Integer id);

    /**
     *
     * @param fullname a fullname of an user
     * @return a found user
     */
    public User getUserByName(String fullname);

    /**
     *
     * @param user an user to be saved
     * @return an saved user
     */
    public User saveUser(User user);
}
