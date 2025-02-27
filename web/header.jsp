<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Header</title>
    <link rel="stylesheet" href="./css/header_style.css">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>
<body>
    <header>
        <div class="top-bar">
            <a href="login.jsp">ĐĂNG NHẬP</a>
            <a href="register.jsp">ĐĂNG KÝ</a>
        </div>
        <div class="nav-container">
            <div class="logo">
                <h1 style="color:blue;"><a href="index.jsp" style="text-decoration: none; color: blue;">Tee & Jean</a></h1>
            </div>
            <nav>
                <ul>
                    <li><a href="index.jsp">Trang chủ</a></li>
                    <li><a href="products.jsp">Sản phẩm</a></li>
                    <li><a href="blogs.jsp">Blogs</a></li>
                    <li><a href="contact.jsp">Liên hệ</a></li>
                </ul>
            </nav>
            <div class="search-cart">
                <input type="text" placeholder="Tên sản phẩm">
                <button class="search-btn">Tìm</button>
                <div class="cart">
                    <i class="fas fa-shopping-bag"></i>
                    <span class="cart-count">0</span>
                </div>
            </div>
        </div>
    </header>
</body>
</html>
