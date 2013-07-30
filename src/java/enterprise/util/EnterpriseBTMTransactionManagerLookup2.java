package enterprise.util;

import org.hibernate.transaction.JNDITransactionManagerLookup;

/**
 * Implementation of {@link org.hibernate.transaction.TransactionManagerLookup}
 * for the Bitronix JTA provider. 
 * @author the-ramones Paul Kulitski
 */
public class EnterpriseBTMTransactionManagerLookup2
        extends JNDITransactionManagerLookup {

    /**
     * JNDI name of a Bitronix {@link javax.transaction.TransactionManager}
     * instance
     */
    private final String BTM_TRANSACTION_MANAGER_JNDI_NAME 
            = "java:/comp/UserTransaction";
    
    /**
     * JNDI name of a Bitronix {@link javax.transaction.UserTransaction} 
     * instance
     */
    private final String BTM_USER_TRANSACTION_JNDI_NAME 
            = "java:/comp/UserTransaction";

    @Override
    protected String getName() {
        return BTM_TRANSACTION_MANAGER_JNDI_NAME;
    }

    @Override
    public String getUserTransactionName() {
        return BTM_USER_TRANSACTION_JNDI_NAME;
    }
}
