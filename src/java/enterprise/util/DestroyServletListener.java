package enterprise.util;

import com.mchange.v2.c3p0.DataSources;
import com.mchange.v2.c3p0.PooledDataSource;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import net.sf.ehcache.CacheManager;

/**
 * Destroy C3P0 pool and release resources
 *
 * @author the-ramones
 */
public class DestroyServletListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(InitServlet.class.getName());
    public static final String ENTERPRISE_DS = "java:comp/env/jdbc/enterpriseDS-C3P0";
    public static final String ENTERPRISE_CACHE_MANAGER = "enterpriseCacheManager";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        cleanupC3P0();
//        cleanupEhcache();
    }

    private void cleanupC3P0() {
        try {
            PooledDataSource ds = (PooledDataSource) new InitialContext().lookup(ENTERPRISE_DS);
            synchronized (ds) {
                ds.close();
                DataSources.destroy(ds);
                java.sql.Driver mySqlDriver = DriverManager.getDriver("jdbc:mysql://localhost:3306/");
                DriverManager.deregisterDriver(mySqlDriver);
            }
        } catch (NamingException e) {
            LOG.severe("Cannot initialize datasource: " + ENTERPRISE_DS
                    + ". Check the datasource settings at the META_INF/context.xml");
        } catch (SQLException e) {
            LOG.severe("Cannot desproy C3P0 pooled datasource " + ENTERPRISE_DS
                    + "It may cause memory leaks");
        }
    }

    private void cleanupEhcache() {
        LOG.info("Cleaning of Ehcache '" + ENTERPRISE_CACHE_MANAGER
                + "' CacheManager resources");
        try {
            CacheManager cacheManager =
                    CacheManager.getCacheManager(ENTERPRISE_CACHE_MANAGER);
            cacheManager.clearAll();
            cacheManager.shutdown();
            
        } catch (Exception e) {
            LOG.severe("Cannot cleanup '" + ENTERPRISE_CACHE_MANAGER
                    + "' Ehcache CacheManager resources caused by "
                    + e.getMessage());
        }

    }
}
