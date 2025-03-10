<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
    <link rel="stylesheet" href="./css/login_style.css">
</head>
<body>
    <div class="container">
        <h2>Đăng nhập</h2>
        
        <% if (request.getAttribute("loginError") != null) { %>
            <p class="error"><%= request.getAttribute("loginError") %></p>
        <% } %>

        <form action="<%= request.getContextPath() %>/login" method="post" >
            <label>Email</label>
            <input type="text" name="email" required>

            <label>Mật khẩu</label>
            <input type="password" name="password" required>

            <button type="submit">Đăng nhập</button>
        </form>

        <p>Chưa có tài khoản? <a href="<%= request.getContextPath() %>/register.jsp">Đăng ký ngay</a></p> <br>

    </div>
</body>
</html>
