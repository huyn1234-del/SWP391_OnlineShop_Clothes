<%-- 
    Document   : login
    Created on : Feb 6, 2025, 11:45:31 AM
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
        <div class="container">
            <h2>Đăng nhập</h2>

            <%-- Hiển thị thông báo lỗi nếu có --%>
            <% String errorMessage = (String) request.getAttribute("loginError"); %>
            <% if (errorMessage != null) { %>
            <p class="error"><%= errorMessage %></p>
            <% } %>

            <form action="<%= request.getContextPath() %>/login" method="post">
                <label for="email">Email:</label>
                <input type="text" id="email" name="email" required>

                <label for="password">Mật khẩu:</label>
                <input type="password" id="password" name="password" required>

                <button type="submit">Đăng nhập</button>
            </form>

            <p>Chưa có tài khoản? <a href="<%= request.getContextPath() %>/account/register.jsp">Đăng ký ngay</a></p>
        </div>   
    </body>
</html>
