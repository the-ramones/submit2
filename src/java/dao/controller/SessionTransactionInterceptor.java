package dao.controller;

import lazy.util.HibernateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
import org.jboss.aop.joinpoint.Invocation;

/**
 * Aspect-oriented approach #4.
 *
 * @author the-ramones
 */
public class SessionTransactionInterceptor
        implements org.jboss.aop.advice.Interceptor {

    private static Log log = LogFactory.getLog(SessionTransactionInterceptor.class);
    private static SessionFactory sf = HibernateUtil.getSessionFactory();

    @Override
    public String getName() {
        return "SessionTransactionInterceptor";
    }

    @Override
    public Object invoke(Invocation invocation) throws Throwable {

        try {
            boolean isActive = sf.getCurrentSession().getTransaction().isActive();
            if (!isActive) {
                log.debug("Starting a database transaction");
                sf.getCurrentSession().beginTransaction();
            }

            log.debug("Invoking the aspectized service method");
            Object result = invocation.invokeNext();

            // Commit and cleanup
            if (!isActive) {
                log.debug("Committing the database transaction");
                sf.getCurrentSession().getTransaction().commit();
            }

            return result;

        } catch (StaleObjectStateException staleEx) {
            log.error("This interceptor does not implement optimistic concurrency control!");
            log.error("Your application will not work until you add compensation actions!");
            // Rollback, close everything, possibly compensate for any permanent changes
            // during the conversation, and finally restart business conversation. Maybe
            // give the user of the application a chance to merge some of his work with
            // fresh data... what you do here depends on your applications design.
            throw staleEx;
        } catch (Throwable ex) {
            // Rollback only
            try {
                log.warn("Trying to rollback database transaction after exception");
                sf.getCurrentSession().getTransaction().rollback();
            } catch (Throwable rbEx) {
                log.error("Could not rollback transaction after exception!", rbEx);
            }
            // Let others handle it... maybe another interceptor for exceptions?
            throw ex;
        }
    }
}