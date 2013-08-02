<%-- 
    Document   : hib-jta-test
    Created on : Jul 29, 2013, 9:39:22 PM
    Author     : the-ramones
--%>

<%@page import="java.util.Map"%>
<%@page import="registry.hibernate.RegisterId"%>
<%@page import="registry.hibernate.Register"%>
<%@page import="enterprise.hibernate.Report"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Session"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.Context"%>
<%@page import="registry.hibernate.RegistryHibernateUtil"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="enterprise.hibernate.EnterpriseHibernateUtil"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.transaction.UserTransaction"%>
<%@page import="java.sql.Date"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="javax.naming.NamingException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bitronix JTA Hibernate Test</title>
    </head>
    <body>
        <h3>Test JTA transaction over Hibernate</h3>
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
                    Map<String, List> model = 
                            (new enterprise.jsp.HibernateJtaController())
                            .processRequest(
                                (Session) request.getAttribute("enterpriseS"),
                                (Session) request.getAttribute("registryS"));
                    request.setAttribute("modelEnterprise", model.get("modelEnterprise"));
                    request.setAttribute("modelRegistry", model.get("modelRegistry"));
                %>
                <c:forEach var="reports" items="${modelEnterprise}">
                    <tr>
                        <td>${reports.id}</td>
                        <td>${reports.startDate}</td>
                        <td>${reports.endDate}</td>
                        <td>${reports.performer}</td>
                        <td>${reports.activity}</td>
                    </tr>
                </c:forEach>
                <tr><td colspan="5"></td></tr>
                    <c:forEach var="registers" items="${modelRegistry}">
                    <tr>
                        <td>${registers.id}</td>
                        <td>${registers.user}</td>
                        <td>${registers.op}</td>
                        <td>${registers.recordTime}</td>
                        <td></td>
                    </tr>
                </c:forEach>                           
            </tbody>
        </table>
    </body>
</html>
