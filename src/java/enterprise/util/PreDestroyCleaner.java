package enterprise.util;

import com.mchange.v2.c3p0.DataSources;
import com.mchange.v2.c3p0.PooledDataSource;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *  Destroy C3P0 pool and release resources
 * 
 * @author the-ramones
 */
public class PreDestroyCleaner {

    private static final Logger LOG = Logger.getLogger(InitServlet.class.getName());
    public static final String ENTERPRISE_DS = "java:comp/env/jdbc/enterpriseDS";

    public void cleanupC3P0() {
        try {
            PooledDataSource ds = (PooledDataSource) new InitialContext().lookup(ENTERPRISE_DS);
            ds.close();
            DataSources.destroy(ds);
        } catch (NamingException e) {
            LOG.severe("Cannot initialize datasource: " + ENTERPRISE_DS
                    + ". Check the datasource settings at the META_INF/context.xml");
        } catch (SQLException e) {
            LOG.severe("Cannot desproy C3P0 pooled datasource " + ENTERPRISE_DS
                    + "It may cause memory leaks");
        }
    }
}
