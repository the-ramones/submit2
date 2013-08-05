package enterprise.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 *
 * @author paul
 */
@Configuration
@EnableCaching
public class EhcacheConfig {

    public static final String EHCACHE_ENTERPRISE_CONFIG_XML = "/ehcache-enterprise.xml";
    public static final String EHCACHE_REGISTRY_CONFIG_XML = "/ehcache-registry.xml";

    @Bean
    public net.sf.ehcache.CacheManager enterpriseEhCacheManager() {
        net.sf.ehcache.CacheManager ecm = net.sf.ehcache.CacheManager.newInstance(
                Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(EHCACHE_ENTERPRISE_CONFIG_XML));
        return ecm;
    }

    @Bean
    public net.sf.ehcache.CacheManager registryEhCacheManager() {
        net.sf.ehcache.CacheManager rcm = net.sf.ehcache.CacheManager.newInstance(
                Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(EHCACHE_REGISTRY_CONFIG_XML));
        return rcm;
    }

    @Bean
    public CacheManager enterprisecacheManager() {
        EhCacheManagerFactoryBean efb = new EhCacheManagerFactoryBean();
        efb.setConfigLocation(new ClassPathResource(
                EHCACHE_ENTERPRISE_CONFIG_XML,
                Thread.currentThread().getContextClassLoader()));
        return (CacheManager) efb.getObject();
    }

    @Bean(destroyMethod = "destroy")
    public CacheManager registryCacheManager() {
        EhCacheManagerFactoryBean efb = new EhCacheManagerFactoryBean();
        efb.setConfigLocation(new ClassPathResource(
                EHCACHE_REGISTRY_CONFIG_XML,
                Thread.currentThread().getContextClassLoader()));
        return (CacheManager) efb.getObject();
    }
}
