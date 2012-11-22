package lazy.dao.factory;

/**
 * Enumeration for Data Access Objects factory types
 * 
 * @author the-ramones
 */
public enum DaoFactoryType {

    HibernateDaoFactory,
    
    SpringOrmDaoFactory,
    
    XmlDaoFactory,
    
    JsonDaoFactory,
    
    PlainJDBCDaoFactory    
}
