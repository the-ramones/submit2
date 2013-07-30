package enterprise.web.filter;

import javax.servlet.Filter;
import java.io.IOException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.transaction.UserTransaction;
import registry.hibernate.RegistryHibernateUtil;
import enterprise.hibernate.EnterpriseHibernateUtil;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;

public class EnterpriseSessionRequestFilter implements Filter {

    //private static Log log = LogFactory.getLog(HibernateSessionRequestFilter.class);
    private static Logger log = Logger.getLogger(EnterpriseSessionRequestFilter.class.getName()); 
    private SessionFactory enterpriseSf;
    private SessionFactory registrySf;

    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        Session enterpriseS = null;
        Session registryS = null;
        UserTransaction ut = null;
        try {
            log.info("Starting a database transaction");
            Context initialContext = new InitialContext();
            ut = (UserTransaction) initialContext.lookup("java:comp/UserTransaction");

            ut.begin();

            enterpriseS = enterpriseSf.getCurrentSession();
            registryS = registrySf.getCurrentSession();

            request.setAttribute("enterpriseS", enterpriseS);
            request.setAttribute("registryS", registryS);

            // Call the next filter (continue request processing)
            chain.doFilter(request, response);

            // Commit and cleanup
            log.info("Committing the database transaction");
            ut.commit();
        } catch (StaleObjectStateException staleEx) {
            log.info("This interceptor does not implement optimistic concurrency control!");
            log.info("Your application will not work until you add compensation actions!");
            // Rollback, close everything, possibly compensate for any permanent changes
            // during the conversation, and finally restart business conversation. Maybe
            // give the user of the application a chance to merge some of his work with
            // fresh data... what you do here depends on your applications design.
            try {
                ut.rollback();
            } catch (Exception e) {
            }
            enterpriseS.close();
            registryS.close();
        } catch (Exception e) {}
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Obtaining SessionFactory from HibernateUtil");
        enterpriseSf = EnterpriseHibernateUtil.getSessionFactory();
        registrySf = RegistryHibernateUtil.getSessionFactory();
    }

    @Override
    public void destroy() {
    }
}
