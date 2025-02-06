<%-- 
    Document   : register
    Created on : Jan 20, 2025, 3:28:24 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Register</title>
        <link rel="stylesheet" href="./account/css/register_style.css"/>

    </head>
   <body>
        <!-- Bắt đầu dự án của bạn ở đây -->
        <section style="margin: 40px 0" class="full-height">
            <div class="container">
                <div class="row d-flex justify-content-center">
                    <div class="col-md-10 col-lg-8">
                        <div class="card shadow-lg p-4">
                            <div class="card-body">
                                <!-- Tiêu đề Đăng ký -->
                                <h2 class="text-center mb-4">Đăng Ký</h2>
                                <c:if test="${registerError != null}">
                                    <div class="alert alert-danger">
                                        ${registerError}
                                    </div>
                                </c:if>
                                <!-- Form -->
                                <form action="./register" method="POST">

                                    <div class="row mb-4">
                                        <div class="col-md-12">
                                            <div class="form-floating">
                                                <label for="username">Tên người dùng</label>
                                                <input type="text" name="username" class="form-control" id="username" value="${param.username}" placeholder="Tên người dùng" required maxlength="50">
                                                
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mb-4">
                                        <div class="col-md-6">
                                            <div class="form-floating">
                                                <label for="password">Mật khẩu</label>
                                                <input type="password" name="password" class="form-control" id="password" placeholder="Mật khẩu" required maxlength="50">
                                                
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-floating">       
                                                <label for="confirmPassword">Xác nhận mật khẩu</label>
                                                <input type="password" name="confirmPassword" class="form-control" id="confirmPassword" placeholder="Xác nhận mật khẩu" required maxlength="50">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="mb-4">
                                        <div class="form-floating">
                                            <label for="email">Địa chỉ email</label>
                                            <input type="email" name="email" class="form-control" id="email" value="${param.email}" placeholder="Địa chỉ email" required maxlength="255">
                                            
                                        </div>
                                    </div>
                                    <!-- Họ và Tên -->
                                    <div class="row mb-4">
                                        <div class="col-md-6">
                                            <div class="form-floating">
                                                <label for="firstName">Tên</label>
                                                <input type="text" name="firstname" class="form-control" value="${param.firstname}"  id="firstName" placeholder="Tên" maxlength="50">
                                                
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-floating">
                                                <label for="lastName">Họ</label>
                                                <input type="text" name="lastname" class="form-control" value="${param.lastname}" id="lastName" placeholder="Họ" maxlength="50">
                                                
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Giới tính và Ngày sinh -->
                                    <div class="row mb-4">
                                        <div class="col-md-6 d-flex align-items-center">
                                            <label class="form-label me-3">Giới tính:</label>
                                            <div class="form-check form-check-inline">                                            
                                                <label class="form-check-label" for="male">Nam</label>
                                                <input class="form-check-input" type="radio" name="gender" id="male" value="true" required checked="">
                                            </div>
                                            <div class="form-check form-check-inline">  
                                                <label class="form-check-label" for="female">Nữ</label>
                                                <input class="form-check-input" type="radio" name="gender" id="female" ${param.gender == false ? 'checked':''} value="false" required>
                                            </div>
                                        </div>

                                        <div class="col-md-6">
                                            <div class="form-floating"
                                                 <label for="dob">Ngày sinh</label>
                                                <input type="date" name="dob" class="form-control" value="${param.dob}" id="dob">
                                                
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Số điện thoại -->
                                    <div class="row mb-4">
                                        <div>
                                            <div class="form-floating">
                                                <label for="phone">Số điện thoại</label>
                                                <input type="tel" name="phone" pattern="\d{10}" value="${param.phone}" title="Số điện thoại phải có đúng 10 chữ số" class="form-control" id="phone" placeholder="Số điện thoại" required maxlength="15">
                                                
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Nút gửi trung tâm -->
                                    <div class="d-grid mb-3">
                                        <button type="submit" class="btn btn-primary btn-lg">Đăng Ký</button>
                                    </div>

                                    <!-- Liên kết đăng nhập -->
                                    <p class="text-center">Bạn đã có tài khoản?
                                        <a href="login" class="custom-link">Đăng nhập ở đây</a>
                                    </p>

                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Kết thúc dự án của bạn -->

    </body>

</html>

