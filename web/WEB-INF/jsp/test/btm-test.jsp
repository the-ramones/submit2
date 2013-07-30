<%-- 
    Document   : btm-test
    Created on : Jul 30, 2013, 5:25:20 PM
    Author     : the-ramones
--%>

<%@page import="javax.transaction.TransactionManager"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.naming.InitialContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BTM Test</title>
    </head>
    <body>
        <h3>Bitronix JTA Transaction Manager</h3>
        <table>
            <% InitialContext ctx = null; try {ctx = new InitialContext();} catch(NamingException e) {} %>
            <tr>DataSource 'enterprise'<td><%= ctx.lookup("java:comp/env/jdbc/enterpriseDS") %></td></tr>
            <tr>DataSource 'registry'<td><%= ctx.lookup("java:comp/env/jdbc/registryDS") %></td></tr>
            <tr>UserTransaction JNDI<td><%= ctx.lookup("java:comp/UserTransaction") %></td></tr>
            <tr>Transaction manager JNDI<td><% 
                    TransactionManager tm = null; 
                    TransactionManager btm = null;
                    try { 
                        tm =  (TransactionManager) ctx.lookup("java:/TransactionManager");
                        btm = (TransactionManager) ctx.lookup("bitronix.tm.TransactionManagerServices");
                    } catch (Exception e){} 
            %><%= tm %></td></tr>
            <tr>Bitronix Transaction manager JNDI<td><!--< btm = (TransactionManager) ctx.lookup("btmUserTransaction");  >--><%= btm%></td></tr>           
        </table>
        
        
    </body>
</html>
