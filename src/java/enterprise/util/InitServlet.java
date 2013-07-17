package enterprise.util;

import java.sql.SQLException;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

/**
 * Initialize datasource C3P0 pool
 *
 * @author Kulitski Paul
 */
@WebServlet(name = "InitServlet", urlPatterns = {"/InitServlet"})
public class InitServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(InitServlet.class.getName());
    public static final String ENTERPRISE_DS = "java:comp/env/jdbc/enterpriseDS";

    @Override
    public void init() {
        try {
            ((DataSource)(new InitialContext().lookup(ENTERPRISE_DS))).getConnection();
        } catch (SQLException ex) {
            LOG.info("Initialize datasource: " + ENTERPRISE_DS
                    + ". Check the datasource settings at the META_INF/context.xml");
        } catch (NamingException ex) {
            LOG.severe("Cannot initialize datasource: " + ENTERPRISE_DS
                    + ". Check the datasource settings at the META_INF/context.xml");
        }
    }
}
