<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Ký</title>
    <link rel="stylesheet" href="./account/css/register_style.css">
</head>
<body>
    <section class="register-container">
        <div class="register-box">
            <h2 class="text-center">Đăng Ký</h2>

            <c:if test="${not empty registerError}">
                <div class="alert alert-danger">${registerError}</div>
            </c:if>

            <form action="${pageContext.request.contextPath}/register" method="POST" >
                <div class="form-group">
                    <label for="username">Tên người dùng</label>
                    <input type="text" name="username" id="username" value="${param.username}" placeholder="Nhập tên người dùng" required required maxlength="50">
                </div>

                <div class="form-group">
                    <label for="password">Mật khẩu</label>
                    <input type="password" name="password" id="password"placeholder="Nhập password" required maxlength="50">
                </div>

                <div class="form-group">
                    <label for="confirmPassword">Xác nhận mật khẩu</label>
                    <input type="password" name="confirmPassword" id="confirmPassword" placeholder="Xac thuc mat khau" required maxlength="50">
                </div>

                <div class="form-group">
                    <label for="email">Địa chỉ email</label>
                    <input type="email" name="email" id="email" value="${param.email}" placeholder="Nhập email"required maxlength="255">
                </div>

                <div class="form-group">
                    <label for="firstName">Tên</label>
                    <input type="text" name="firstname" id="firstName" value="${param.firstname}"placeholder="Nhập tên" maxlength="50">
                </div>

                <div class="form-group">
                    <label for="lastName">Họ</label>
                    <input type="text" name="lastname" id="lastName" value="${param.lastname}"placeholder="Nhập họ" maxlength="50">
                </div>

                <div class="form-group">
                    <label>Giới tính</label>
                    <div class="gender-group">
                        <input type="radio" name="gender" id="male" value="true" required checked>
                        <label for="male">Nam</label>

                        <input type="radio" name="gender" id="female" value="false" ${param.gender == false ? 'checked' : ''} required>
                        <label for="female">Nữ</label>
                    </div>
                </div>

                <div class="form-group">
                    <label for="dob">Ngày sinh</label>
                    <input type="date" name="dob" id="dob" value="${param.dob}">
                </div>

                <div class="form-group">
                    <label for="phone">Số điện thoại</label>
                    <input type="tel" name="phone" id="phone" pattern="\d{10}" value="${param.phone}" placeholder="Nhập số điện thoại"title="Số điện thoại phải có đúng 10 chữ số" required maxlength="15">
                </div>

                <button type="submit" class="btn btn-primary">Đăng Ký</button>
            </form>

            <p class="text-center">Bạn đã có tài khoản? <a href="login">Đăng nhập ở đây</a></p>
        </div>
    </section>
</body>
</html>
