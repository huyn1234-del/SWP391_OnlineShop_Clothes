<%-- 
    Document   : success
    Created on : Feb 16, 2025, 11:42:53 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Chào mừng bạn</h1>
        <p>Chúc bạn một ngày tốt lành.</p>
        <p>Quên mật khẩu? <a href="<%= request.getContextPath() %>/changePassword.jsp">Đổi mật khẩu ngay</a></p>
        <a href="logout">Đăng xuất</a>
    </body>
</html>
