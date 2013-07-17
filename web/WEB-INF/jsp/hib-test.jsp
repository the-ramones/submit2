<%-- 
    Document   : hib-test
    Created on : Jul 17, 2013, 12:01:17 AM
    Author     : the-ramones
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hibernate Configuration Test Page</title>
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
                        final String SELECT_ALL =
                                "SELECT id, startDate, endDate, performer, activity FROM enterprise.reports LIMIT 0, ?";
                        final int LIMIT = 50;
                        Context env = (Context) new InitialContext().lookup("java:comp/env");
                        DataSource enterpriseDs = (DataSource) env.lookup("jdbc/enterpriseDS");
                        Connection conn = null;
                        PreparedStatement st = null;
                        ResultSet rs = null;
                        try {
                            conn = enterpriseDs.getConnection();
                            st = conn.prepareStatement(SELECT_ALL, ResultSet.FETCH_FORWARD, ResultSet.CONCUR_READ_ONLY);
                            st.setInt(1, LIMIT);
                            rs = st.executeQuery();
                            while (rs.next()) {
                    %>
                    <tr>
                        <td><%= rs.getLong("id")%></td>
                        <td><%= rs.getString("performer")%></td>
                        <td><%= rs.getDate("startDate")%></td>
                        <td><%= rs.getDate("endDate")%></td>
                        <td><%= rs.getString("activity")%></td>
                    </tr>
                    <%
                    }
                } catch (SQLException e) {}
                finally {
                        try {
                           rs.close();
                           st.close();
                           conn.close(); }
                        catch (Exception e) {}
                    }
                %>
                </tbody>
            </table>
        </div>
    </body>
</html>
