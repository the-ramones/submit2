<%-- 
    Document   : hib-jta-test
    Created on : Jul 29, 2013, 9:39:22 PM
    Author     : the-ramones
--%>

<%@page import="registry.hibernate.RegistersId"%>
<%@page import="registry.hibernate.Registers"%>
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
                <%  UserTransaction ut = null;
                    try {
                        Context initialContext = new InitialContext();
                        SessionFactory enterpriseSf = EnterpriseHibernateUtil.getSessionFactory();
                        SessionFactory registrySf = RegistryHibernateUtil.getSessionFactory();
                        ut = (UserTransaction) initialContext.lookup("java:comp/UserTransaction");
                        Session enterpriseS = enterpriseSf.openSession();
                        Session registryS = registrySf.openSession();
                        
                        ut.begin();
                        
                        Report report = new Report(Date.valueOf("2013-08-01"), Date.valueOf("2013-08-02"), "Michael Douglas", "acting");
                        Registers registry = new Registers();
                        registry.setId(new RegistersId(11, 1, 1));
                        enterpriseS.save(report);
                        registryS.save(registry);
                        
                        List enterpriseL = enterpriseS.createQuery("from Report").list();
                        List registryL = registryS.createQuery("from Registers").list();
                        
                        request.setAttribute("modelEnterprise", enterpriseL);
                        request.setAttribute("modelRegistry", registryL);
                        
                        ut.commit();
                        } catch (Exception e) {
                            System.err.println(e);
                            try {
                                ut.rollback();
                            } catch (Exception roll) {
                                System.err.println("Cannot rollback transaction: " + roll);
                            }
                        }
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
                        <td>${registers.users}</td>
                        <td>${registers.ops}</td>
                        <td>${registers.recordTime}</td>
                        <td></td>
                    </tr>
                </c:forEach>                           
                </tbody>
            </table>
        </body>
    </html>
