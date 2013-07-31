package enterprise.spring.config;

/**
 * Spring Java configuration of Bitronix transaction manager
 * @author the-ramones
 */
import bitronix.tm.BitronixTransactionManager;
import bitronix.tm.TransactionManagerServices;
import bitronix.tm.resource.jdbc.PoolingDataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import java.util.Properties;
import java.io.IOException;

@Configuration
public class BitronixJtaConfiguration {

    private static final int MIN_POOL_SIZE = 4;
    private static final int MAX_POOL_SIZE = 32;
    private static final String TEST_QUERY_ENTERPRISE_DS = "SELECT 1 FROM reports";
    private static final String TEST_QUERY_REGISTRY_DS = "SELECT 1 FROM registers";
    public static final String SERVER_JVM_UNIQUE_ID = "tomcat-btm-node0";
    
    @Autowired
    private Environment environment;

    @Bean(initMethod = "init", destroyMethod = "close")
    public PoolingDataSource enterpriseDataSource() {
        PoolingDataSource enterpriseDS = new PoolingDataSource();
        enterpriseDS.setClassName(
                com.mysql.jdbc.jdbc2.optional.MysqlXADataSource.class.getName());
        enterpriseDS.setUniqueName("jdbc/enterpriseDS");
        enterpriseDS.setMinPoolSize(MIN_POOL_SIZE);
        enterpriseDS.setMaxPoolSize(MAX_POOL_SIZE);
        enterpriseDS.setTestQuery(TEST_QUERY_ENTERPRISE_DS);
        try {
            Properties props = new Properties();
            props.load(getClass().getResourceAsStream("enterpriseds-driver.properties"));
            enterpriseDS.setDriverProperties(props);
        } catch (IOException e) {
        }
        return enterpriseDS;
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public PoolingDataSource registryDataSource() {
        PoolingDataSource registryDS = new PoolingDataSource();
        registryDS.setClassName(
                com.mysql.jdbc.jdbc2.optional.MysqlXADataSource.class.getName());
        registryDS.setUniqueName("jdbc/registryDS");
        registryDS.setMinPoolSize(MIN_POOL_SIZE);
        registryDS.setMaxPoolSize(MAX_POOL_SIZE);
        registryDS.setTestQuery(TEST_QUERY_REGISTRY_DS);
        try {
            Properties props = new Properties();
            props.load(getClass().getResourceAsStream("registry-driver.properties"));
            registryDS.setDriverProperties(props);
        } catch (IOException e) {
        }
        return registryDS;
    }

    @Bean
    public bitronix.tm.Configuration btmConfig() {
        bitronix.tm.Configuration tmConfiguration =
                TransactionManagerServices.getConfiguration();
        tmConfiguration.setServerId(SERVER_JVM_UNIQUE_ID);
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

}
