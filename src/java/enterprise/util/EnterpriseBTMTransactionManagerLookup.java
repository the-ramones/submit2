package enterprise.util;

import java.lang.reflect.Method;
import org.hibernate.HibernateException;
import org.hibernate.transaction.BTMTransactionManagerLookup;

/**
 *
 * @author the-ramones Paul Kulitski
 */
public class EnterpriseBTMTransactionManagerLookup
        extends BTMTransactionManagerLookup {

    @Override
    public String getUserTransactionName() {
        try {
            Class transactionManagerServiceClass = 
                    Class.forName("bitronix.tm.TransactionManagerServices");
            Method getConfigurationMethod = 
                    transactionManagerServiceClass.getMethod("getConfiguration", (Class) null);
            Object configurationInstance = 
                    getConfigurationMethod.invoke(null, (Object) null);

            Class bitronixConfigurationClass = configurationInstance.getClass();
            Method getJndiUserTransactionNameMethod = 
                    bitronixConfigurationClass
                    .getMethod("getJndiUserTransactionName", (Class) null);
            String configuredJndiUserTransactionName = 
                    (String) getJndiUserTransactionNameMethod
                    .invoke(configurationInstance, (Object) null);
            if (configuredJndiUserTransactionName != null 
                    && configuredJndiUserTransactionName.trim().length() >= 0) {
                System.out.println(configuredJndiUserTransactionName + "!!!!!!!!!!!!");
                return configuredJndiUserTransactionName;
                
            }
            return "java:comp/UserTransaction";
        } catch (Exception e) {
            throw new HibernateException("Could not obtain BTM UserTransactionName ", e);
        }
    }
}
