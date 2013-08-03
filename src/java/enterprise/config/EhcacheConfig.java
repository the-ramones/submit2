package enterprise.config;

import net.sf.ehcache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author paul
 */
//@Configuration
public class EhcacheConfig {
    
    public static final String EHCACHE_ENTERPRISE_CONFIG_XML = "/ehcache-enterprise.xml";
    public static final String EHCACHE_REGISTRY_CONFIG_XML = "/ehcache-registry.xml";

       
    @Bean
    public CacheManager enterpriseCacheManager() {
        CacheManager ecm = CacheManager.newInstance(
                Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(EHCACHE_ENTERPRISE_CONFIG_XML));
        return ecm;
    }

    @Bean
    public CacheManager registryCacheManager() {
        CacheManager rcm = CacheManager.newInstance(
                Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(EHCACHE_REGISTRY_CONFIG_XML));
        return rcm;
    }  
    
}
