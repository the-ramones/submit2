<%-- 
    Document   : index
    Created on : Nov 13, 2012, 8:51:59 PM
    Author     : the-ramones    
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hibernate patch-to-patch</title>
    </head>
    <body>
        <h1>Goals</h1>
    <li>
        <ul>Make Hibernate work -> true</ul>
        <ul>Hibernate, 3 option of configuration:
            <ul>simple XML *.hbm mapping configuration (hibernate-tool.cfg, hb.tools.* domain classes)</ul>
            <ul>simple Annotation based configuration (hibernate2.cfg (in the root folder!), hb.tools.annotation.* domain classes)</ul>
            <ul>default configuration, annotation based, binded with MySQL database (hibernate.cfg, lazy.domain.* domain classes)</ul>
        </ul>
        <ul>Dao architecture:
            <ul>dao.* classes: interfaces for all data sources dao objects</ul>
            <ul>dao.generic.* and dao.generic.impl.* classes: interface and implementation for common entity for all dao classes</ul>
            <ul>dao.impl.* classes: implementation under different data sources, currently only Hibernate ORM platform</ul>            
            <ul>dao.factory.* classes: abstract factory J2EE pattern; DaoFactory.class - abstract factory, HibernateDaoFactory - one implementation under Hibernate ORM</ul>
            <ul>lazy.db SQL-files: SQL scripts for database creation, replacement and data insertion
                <ul>last lazy.* packages: duplicated functionality for HibernateLazyInitializationException project</ul>
                <ul>lazy.* contains @FetchProfile functionality, package-info.java classes, 2-layer support: Hibernate, plain XML</ul>    
            </ul>
            <ul>RESTful web-services configuration:
                <ul></ul>
                <ul></ul>
                <ul></ul>
            </ul>        
    </li>        
    <form method="GET" action="/filter">
        <label>Redirect to "Servlet vs. Filter" </label>
        <input type="button" title="Go!"/>
    </form>
    </form>
</body>
</html>
