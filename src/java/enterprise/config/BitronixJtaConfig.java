package enterprise.config;

/**
 * Spring Java configuration of Bitronix transaction manager
 *
 * @author Paul Kulitski
 */
import bitronix.tm.BitronixTransactionManager;
import bitronix.tm.TransactionManagerServices;
import bitronix.tm.resource.jdbc.PoolingDataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import java.util.Properties;
import java.io.IOException;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class BitronixJtaConfig {

    @Inject
    private Environment environment;
    private static final Logger logger =
            LoggerFactory.getLogger(BitronixJtaConfig.class);
    private static final String ENTERPRISE_DS_UNIQUE_NAME = "jdbc/enterpriseDS";
    private static final String ENTERPRISEDS_DRIVER_PROPERTIES_FILE = "/enterpriseds-driver.properties";
    private static final String REGISTRY_DS_UNIQUE_NAME = "jdbc/registryDS";
    private static final String REGISTRYDS_DRIVER_PROPERTIES_FILE = "/registryds-driver.properties";
    private static final String ENTERPRISE_HIBERNATE_CONFIG_FILE = "/enterprise/model/enterprise/enterprise.cfg.xml";
    private static final String REGISTRY_HIBERNATE_CONFIG_FILE = "/enterprise/model/registry/registry.cfg.xml";
    private static final int MIN_POOL_SIZE = 4;
    private static final int MAX_POOL_SIZE = 32;
    private static final String TEST_QUERY_ENTERPRISE_DS = "SELECT 1 FROM reports";
    private static final String TEST_QUERY_REGISTRY_DS = "SELECT 1 FROM registers";
    private static final String JTA_JVM_UNIQUE_ID = "spring-btm-node0";

    public BitronixJtaConfig() {
    }

    //@Autowired
    //private Environment environment;
    @Bean(initMethod = "init", destroyMethod = "close")
    public PoolingDataSource enterpriseDataSource() {
        PoolingDataSource enterpriseDS = new PoolingDataSource();
        enterpriseDS.setClassName(
                com.mysql.jdbc.jdbc2.optional.MysqlXADataSource.class.getName());
        enterpriseDS.setUniqueName(ENTERPRISE_DS_UNIQUE_NAME);
        enterpriseDS.setMinPoolSize(MIN_POOL_SIZE);
        enterpriseDS.setMaxPoolSize(MAX_POOL_SIZE);
        enterpriseDS.setTestQuery(TEST_QUERY_ENTERPRISE_DS);
        try {
            Properties props = new Properties();
            props.load(
                    /* 
                     * Classloader issue in Tomcat and likely another containers.
                     * Use the code snippet below instead of this:
                     * getClass().getResourceAsStream("/enterpriseds-driver.properties")
                     */
                    Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(ENTERPRISEDS_DRIVER_PROPERTIES_FILE));
            enterpriseDS.setDriverProperties(props);
            enterpriseDS.init();
        } catch (IOException e) {
            logger.error("Cannot load properties file for a datasource driver initialization", e);
        }
        return enterpriseDS;
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public PoolingDataSource registryDataSource() {
        PoolingDataSource registryDS = new PoolingDataSource();
        registryDS.setClassName(
                com.mysql.jdbc.jdbc2.optional.MysqlXADataSource.class.getName());
        registryDS.setUniqueName(REGISTRY_DS_UNIQUE_NAME);
        registryDS.setMinPoolSize(MIN_POOL_SIZE);
        registryDS.setMaxPoolSize(MAX_POOL_SIZE);
        registryDS.setTestQuery(TEST_QUERY_REGISTRY_DS);
        try {
            Properties props = new Properties();
            props.load(
                    Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(REGISTRYDS_DRIVER_PROPERTIES_FILE));
            registryDS.setDriverProperties(props);
        } catch (IOException e) {
            logger.error("Cannot load properties file for a datasource driver initialization", e);
        }
        return registryDS;
    }

    @Bean
    public bitronix.tm.Configuration btmConfig() {
        bitronix.tm.Configuration tmConfiguration =
                TransactionManagerServices.getConfiguration();
        tmConfiguration.setServerId(JTA_JVM_UNIQUE_ID);
        return tmConfiguration;
    }

    @Bean(destroyMethod = "shutdown")
    @DependsOn("btmConfig")
    public BitronixTransactionManager bitronixTransactionManager() {
        BitronixTransactionManager btmTransactionManager =
                TransactionManagerServices.getTransactionManager();
        return btmTransactionManager;
    }

    @Bean
    @DependsOn("btmConfig")
    public TransactionManager transactionManager() {
        return TransactionManagerServices.getTransactionManager();
    }

    @Bean
    @DependsOn("btmConfig")
    public UserTransaction userTransaction() {
        return TransactionManagerServices.getTransactionManager();
    }

    @Bean
    @DependsOn("bitronixTransactionManager")
    public JtaTransactionManager jtaTransactionManager() {
        JtaTransactionManager tm = new JtaTransactionManager();
        tm.setTransactionManager(bitronixTransactionManager());
        tm.setUserTransaction(bitronixTransactionManager());
        return tm;
    }

    @Bean
    @DependsOn("enterpriseDataSource")
    public AnnotationSessionFactoryBean enterpriseSessionFactory() {
        AnnotationSessionFactoryBean esf = new AnnotationSessionFactoryBean();
        esf.setDataSource(enterpriseDataSource());
        esf.setJtaTransactionManager(transactionManager());
        esf.setConfigLocation(new ClassPathResource(ENTERPRISE_HIBERNATE_CONFIG_FILE));
        return esf;
    }

    @Bean
    @DependsOn("registryDataSource")
    public AnnotationSessionFactoryBean registrySessionFactory() {
        AnnotationSessionFactoryBean rsf = new AnnotationSessionFactoryBean();
        rsf.setDataSource(registryDataSource());
        rsf.setJtaTransactionManager(transactionManager());
        rsf.setConfigLocation(new ClassPathResource(REGISTRY_HIBERNATE_CONFIG_FILE));
        return rsf;
    }
}
