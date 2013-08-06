package enterprise.config;

import net.sf.ehcache.transaction.manager.DefaultTransactionManagerLookup;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

/**
 * Caching configuration
 *
 * @author Paul Kulitski
 */
//@Configuration
//@EnableCaching
public class EhcacheConfig {

    public static final String EHCACHE_ENTERPRISE_CONFIG_XML = "/ehcache-enterprise.xml";
    public static final String EHCACHE_REGISTRY_CONFIG_XML = "/ehcache-registry.xml";

    /**
     * Native Ehcache manager bean configuration for 'enterprise' db
     *
     * @return native EHcache manager
     */
    @Bean(destroyMethod = "shtdown")
    public net.sf.ehcache.CacheManager enterpriseEhCacheManager() {
        net.sf.ehcache.CacheManager ecm = net.sf.ehcache.CacheManager.newInstance(
                Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(EHCACHE_ENTERPRISE_CONFIG_XML));
        return ecm;
    }

    /**
     * Native Ehcache manager bean configuration for 'registry' db
     *
     * @return native EHcache manager
     */
    @Bean(destroyMethod = "shutdown")
    public net.sf.ehcache.CacheManager registryEhCacheManager() {
        net.sf.ehcache.CacheManager rcm = net.sf.ehcache.CacheManager.newInstance(
                Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(EHCACHE_REGISTRY_CONFIG_XML));
        return rcm;
    }

    /**
     * Spring-based EhCache manager
     *
     * @return Spring-based EhCache manager
     */
    @Bean(destroyMethod = "destroy")
    public CacheManager enterprisecacheManager() {
        EhCacheManagerFactoryBean efb = new EhCacheManagerFactoryBean();
        efb.setConfigLocation(new ClassPathResource(
                EHCACHE_ENTERPRISE_CONFIG_XML,
                Thread.currentThread().getContextClassLoader()));
        return (CacheManager) efb.getObject();
    }

    /**
     * Spring-based EhCache manager
     *
     * @return Spring-based EhCache manager
     */
    @Bean(destroyMethod = "destroy")
    public CacheManager registryCacheManager() {
        EhCacheManagerFactoryBean efb = new EhCacheManagerFactoryBean();
        efb.setConfigLocation(new ClassPathResource(
                EHCACHE_REGISTRY_CONFIG_XML,
                Thread.currentThread().getContextClassLoader()));
        return (CacheManager) efb.getObject();
    }
}
