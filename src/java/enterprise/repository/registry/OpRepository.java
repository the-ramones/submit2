package enterprise.repository.registry;

import enterprise.model.registry.Op;

/**
 * Public interface for repositories of {@link enterprise.model.registry.Op}
 * instances
 *
 * @author Paul Kulitski
 */
public interface OpRepository {

    /**
     *
     * @param id unique identifier of an operation
     * @return a found operation
     */
    public Op getOpById(Integer id);

    /**
     *
     * @param title a title of an operation
     * @return a found operation
     */
    public Op getOpByTitle(String title);

    /**
     *
     * @param op an operation to be saved
     * @return a saved operation
     */
    public Op saveOp(Op op);
}
