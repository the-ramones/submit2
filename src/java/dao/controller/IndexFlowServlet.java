package dao.controller;

import dao.bean.ProcessBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author the-ramones
 */
public class IndexFlowServlet extends HttpServlet {

    Log log = LogFactory.getLog(IndexFlowServlet.class);

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processEjbJndiRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet JndiServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Servlet IndexFlowServlet at " + request.getContextPath() + "</h3>");
            out.println("<h4>CompanyController EJB properties:</h4>");
            out.println("" + companyBeanRequest());
            out.println("<h4>CompanyBean execute(): true</h4>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    private void processJavaBeanJndiRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        System.out.println(session.toString());
        Cookie cookie = new Cookie("id", String.valueOf(new Random(10424324L).nextInt()));
        response.addCookie(cookie);        
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>IndexFlow controller</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Servlet IndexFlowServlet at " + request.getContextPath() + "</h3>");
            out.println("<h4>ProcessBean properties:</h4>");
            out.println("" + processBeanRequest());            
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }

    }

    /**
     *
     * @return static JavaBean properties, null, if lookup wasn't lucky
     */
    private String processBeanRequest() {
        try {
            Context ctx = new InitialContext();
            Context env = (Context) ctx.lookup("java:comp/env");
            ProcessBean process = (ProcessBean) env.lookup("bean/ProcessBeanFactory");
            return (process.getDescription() + process.getId());
        } catch (NamingException e) {
            log.debug("Cannot lookup JavaBean from JNDI environment", e);
            return "";
        } catch (NullPointerException e) {
            log.error("Cannot find bean through JNDI or object was null", e);
            return "";
        }
    }

    /**
     *
     * @return static EJB entity properties, null, if lookup wasn't lucky
     */
    private String companyBeanRequest() {
        try {
            Context ctx = new InitialContext();
            Context env = (Context) ctx.lookup("java:comp/env");
            CompanyEnterpriseBean controller = (CompanyEnterpriseBean) env.lookup("bean/CompanyEnterpriseBeanFactory");
            controller.execute();
            return (controller.getTest() + controller.getIndex());

        } catch (NamingException e) {
            log.error("Cannot find bean through JNDI", e);
            return "";
        } catch (NullPointerException e) {
            log.error("Cannot find bean through JNDI or object was null", e);
            return "";
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String option = request.getParameter("submit");
        if (option.equals("EJB test")) {
            processEjbJndiRequest(request, response);
        } else if (option.equals("Bean test")) {
            processJavaBeanJndiRequest(request, response);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String option = request.getParameter("submit");
        if (option.equals("EJB test")) {
            processEjbJndiRequest(request, response);
        } else if (option.equals("Bean test")) {
            processJavaBeanJndiRequest(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
