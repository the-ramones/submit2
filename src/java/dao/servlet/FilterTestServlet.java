package dao.servlet;

import dao.CompanyDao;
import dao.factory.DaoFactory;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lazy.domain.Company;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author the-ramones
 */
public class FilterTestServlet extends HttpServlet {

    private static Log log = LogFactory.getLog(FilterTestServlet.class);
    public static final String DAO_FACTORY_CLASS = "dao-factory-class";
    private DaoFactory daoFactory;
    public static final String ACTION = "action";
    public static final String ACTION_EVENT_PERSIST = "persist";
    public static final String ACTION_EVENT_FIND_BYNAME = "find";
    public static final String ACTION_EVENT_MODIFY_NAME = "modify";
    public static final String ACTION_EVENT_DELETE_ALL = "delete";
    public static final String PARAM_ENTITY_NAME = "name";
    public static final String PARAM_ENTITY_NEW_NAME = "newName";
    public static final String CONVERSATION_ENTITY = "entity";

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        CompanyDao dao = daoFactory.getCompanyDao();

        String action = request.getParameter(ACTION);
        String name = request.getParameter(PARAM_ENTITY_NAME);
        String newName = request.getParameter(PARAM_ENTITY_NEW_NAME);

        if (ACTION_EVENT_PERSIST.equals(action)) {

            // Store a new entity instance and set it in the HttpSession
            Company newEntity = new Company(name);
            dao.makePersistent(newEntity);
            request.getSession().setAttribute(CONVERSATION_ENTITY, newEntity);

        } else if (ACTION_EVENT_MODIFY_NAME.equals(action)) {

            Company old = (Company) request.getSession().getAttribute(CONVERSATION_ENTITY);
            old.setName(newName);
            request.getSession().setAttribute(CONVERSATION_ENTITY, old);

        } else if (ACTION_EVENT_FIND_BYNAME.equals(action)) {

            // Query for an entity instance by name, bypasses the persistence context
            Company example = new Company(name);
            List<Company> result = dao.findByExample(example);
            Company foundEntity = null;
            if (result.size() > 0) {
                foundEntity = result.get(0);
            }
            request.getSession().setAttribute(CONVERSATION_ENTITY, foundEntity);

        } else if (ACTION_EVENT_DELETE_ALL.equals(action)) {

            Company example = new Company(name);
            List<Company> result = dao.findAll();
            for (Company category : result) {
                dao.makeTransient(category);
            }
            request.getSession().setAttribute(CONVERSATION_ENTITY, null);
        }

    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        String daoFactoryName = servletConfig.getInitParameter(DAO_FACTORY_CLASS);
        try {
            if (daoFactoryName != null) {
                log.debug("Testing with DAOFactory: " + daoFactoryName);
                Class daoFactoryClass = Class.forName(daoFactoryName);
                daoFactory = DaoFactory.instance(daoFactoryClass);
            } else {
                throw new ServletException("Configure servlet with a daoFactoryClass parameter!");
            }
        } catch (Exception ex) {
            throw new ServletException("Can't find DAOFactory: " + daoFactoryName);
        }
    }
}
