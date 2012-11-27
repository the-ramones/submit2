<%-- 
    Document   : newjsp
    Created on : Nov 27, 2012, 1:06:37 AM
    Author     : the-ramones
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Composite-design JSP Page</title>
    </head>
    <body>
        <jsp:include page="../WEB-INF/jspf/header.jspf" flush="false"/>
        <table>
            <tr>
                <td><jsp:include page="../WEB-INF/jspf/menu.jspf" flush="false"/></td>
                <td><jsp:include page="<%= "../WEB-INF/jspf/" + request.getParameter("to-render") %>" flush="true" /></td>                
            </tr>
        </table>        
        <jsp:include page="../WEB-INF/jspf/footer.jspf" flush="false" />
    </body>
</html>
