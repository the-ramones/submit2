<%-- 
    Document   : custom-dbcp
    Created on : Dec 4, 2012, 9:31:58 PM
    Author     : the-ramones
--%>


<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource" %>
<%@page import="javax.naming.InitialContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Custom Database connection pool</title>
    </head>
    <body>
        <h4>Custom Database connection pool</h4><br/>        
        <ul>Library: Apache Common DBCP</ul>
        <ul>Database: http://localhost:3306/lazy</ul>
        <ul>connection to "lazy.customer" table, list of customers:
            <% 
                InitialContext ctx = new InitialContext();
                DataSource source = (DataSource) ctx.lookup("jdbc/lazy-dbcp");
                Connection con = source.getConnection();
                Statement st = con.createStatement();
                ResultSet result = st.executeQuery("SELECT * FROM lazy.customer");
                while (result.next()) {
                    out.write("<ul>" + result.getString("customer_id")
                        + result.getString("name") 
                        + result.getString("") + "</ul>");
                }
            %>             
            
        </ul>
    </body>
</html>
