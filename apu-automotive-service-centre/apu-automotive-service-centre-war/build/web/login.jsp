<%-- 
    Document   : login
    Created on : Feb 23, 2026, 11:05:54 AM
    Author     : guan.kiat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <a href="register.jsp">New User Registration</a>
        <br><br><br>
        <form action="Login" method="POST">
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="x" size="20"></td>
                </tr>
                <tr>
                    <td>Password: </td>
                    <td><input type="text" name="y" size="20"></td>
                </tr>
            </table>
            <p><input type="submit" value="Login"></p>
        </form>
    </body>
</html>
