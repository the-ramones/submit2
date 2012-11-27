<%-- 
    Document   : filter-view
    Created on : Nov 23, 2012, 1:36:49 AM
    Author     : the-ramones
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control" content="no-cache" />
        <title>Filter application</title>
    </head>
    <body>
        <h3>Servlet-vs-Filters</h3>
        <h4>Choose action:</h4>
        <form method="GET" action="./controller">     
            <input type="submit" value="per-request" name="submit"/>            
        </form>
        <form method="GET" action="./controller"> 
            <input type="submit" value="per-conversation" name="submit"/>
        </form>
        <div id="result">
            <table border="0">
                <tr>
                    <td>                        
                        <img src="taylor.jpg" width="800" height="534" alt="taylor"/><br />
                        <center><a href="http://http://www.taylorswift.com/" target="_blank">Taylor Swift Official Web site</a></center>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>
