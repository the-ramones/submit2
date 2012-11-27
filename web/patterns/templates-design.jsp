<%-- 
    Document   : templates-design
    Created on : Nov 27, 2012, 5:10:01 PM
    Author     : the-ramones
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${requestScope.css}" rel="stylesheet" type="text/css" />
        <title>Tempaltes design JSP Page</title>
    </head>
    <body>
        <jsp:include page="${requestScope.header}" flush="false" />
        <table>
            <tr>
                <td><jsp:include page="${requestScope.menu}" flush="true" /></td>                
                <td><jsp:include page="${requestScope.content}" flush="true" /></td>                   
            </tr>
        </table>            
        <jsp:include page="${requestScope.footer}" flush="false" />
    </body>
</html>
