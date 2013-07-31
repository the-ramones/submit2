package enterprise.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * Spring Java configuration of Bitronix-in-Tomcat transaction manager.
 * Use this Spring configuration when Bitronix transaction manager has been
 * configured in Apache Tomcat server. Normally 
 * {@link javax.transaction.TransactionManager} and  
 * {@link javax.transaction.UserTransaction} instances is exposed through
 * JNDI, both under 'java:comp/UserTransaction' JNDI-name 
 * @author the-ramones
 */

/*
 * @Configuration : uncomment this for use
 */
public class BitronixInTomcatJtaConfiguration {

    
    @Autowired
    private Environment environment;
//    
//    @Bean
//    public 
/*
 * 
 * <tx:jta-transaction-manager id="transactionManager" />
 * 
 * <bean id="jtaTransactionManager"
 * factory-bean="transactionManager" factory-method="getTransactionManager"/>
 * <jee:jndi-lookup id="xaDataSource" jndi-name="java:comp/env/jdbc/XADataSource"
 * expected-type="javax.sql.DataSource"/>
 * 
 */ 
}
