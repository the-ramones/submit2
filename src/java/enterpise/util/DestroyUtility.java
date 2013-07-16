package enterpise.util;

import com.mchange.v2.c3p0.C3P0Registry;
import com.mchange.v2.c3p0.PooledDataSource;
import com.mchange.v2.c3p0.management.C3P0RegistryManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author paul
 */
public class DestroyUtility {
    
    private static final Logger LOG = Logger.getLogger(DestroyUtility.class.getName());
    private static final String JNDI_DS = "java:comp/env/jdbc/enterpiseDS";
    
    public void cleanC3P0() {
        try {
            PooledDataSource ds = ((PooledDataSource) new InitialContext().lookup(JNDI_DS));
            ds.close();
            C3P0Registry.markClosed(ds);
            Mb        
        } catch (SQLException e) {
            LOG.warning("Cannot release C3P0 linked resources. "
                    + "It can lead to memory leak");
        } catch (NamingException e) {
            LOG.warning("Cannot get datasouce under JNDI name - " + JNDI_DS +
                    "Resources linked with C3P0 cache may cause memory leaks.");
        }
        
    }
}
