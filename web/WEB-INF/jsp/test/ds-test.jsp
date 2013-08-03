<%-- 
    Document   : ds-test
    Created on : Aug 2, 2013, 6:41:04 PM
    Author     : the-ramones
--%>

<%@page import="javax.transaction.TransactionManager"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="org.slf4j.LoggerFactory"%>
<%@page import="org.slf4j.Logger"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="bitronix.tm.resource.jdbc.PoolingDataSource"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DataSources creation test</title>
    </head>
    <body>
        <h3>Tests:</h3>
        <hr />
        <%
            final Logger logger = LoggerFactory.getLogger(this.getClass());
            
            WebApplicationContext context = WebApplicationContextUtils
                    .getWebApplicationContext(application);
            DataSource enterpriseDS = 
                    context.getBean("enterpriseDataSource", PoolingDataSource.class);
            DataSource registryDS = 
                    context.getBean("registryDataSource", PoolingDataSource.class);

            Connection ec = null;
            Connection rc = null;
            try {
                ec = enterpriseDS.getConnection();
                rc = registryDS.getConnection();
            %><h4>enterpriseDS:<%= enterpriseDS %>:<%= ec.isValid(1000) %></h4>
            <h4>registryDS:<%= registryDS %>:<%= rc.isValid(1000) %></h4>
            <%
            } catch(Exception e) {
                logger.error("Cannot validate a connection to the datasource", e);
            } finally {
                try {
                    ec.close();
                    rc.close();
                } catch (Exception e) {}
            }
        %>
    </body>
</html>
