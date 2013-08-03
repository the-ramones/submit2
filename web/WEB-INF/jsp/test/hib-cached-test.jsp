<%-- 
    Document   : hib-test
    Created on : Jul 18, 2013, 1:31:07 AM
    Author     : the-ramones
--%>

<%@page import="org.hibernate.HibernateException"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="enterprise.model.enterprise.Report"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hibernate Cached Configuration Test Page</title>
    </head>
    <body>
        <div>
            <h3>The first 50 entries in datasource 'enterpriseDS'</h3>
            <table border='1px solid black'>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>performer</th>
                        <th>startDate</th>
                        <th>endDate</th>
                        <th>activity</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        SessionFactory sf = null;
                        Session s = null;
                        try {
                            final int LIMIT = 50; 
                            sf = enterprise.model.enterprise.EnterpriseHibernateUtil.getSessionFactory();
                            s = sf.openSession();
                            Criteria criteria = s.createCriteria(Report.class);
                            // criteria.setCacheable(true);    
                            List<Report> l = criteria.setFetchSize(LIMIT).list();
                            for (Report r : l) {
                            %>
                            <tr>
                                <td><%= r.getId()%></td>
                                <td><%= r.getPerformer()%></td>
                                <td><%= r.getStartDate()%></td>
                                <td><%= r.getEndDate()%></td>
                                <td><%= r.getActivity()%></td>
                            </tr>
                            <%
                    }
                } catch (HibernateException e) {
                } finally {
                    if (s != null) {
                        s.close();
                    }
                }
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>
