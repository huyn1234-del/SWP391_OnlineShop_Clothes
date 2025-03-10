<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Đổi Mật Khẩu</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <div class="col-md-6 mx-auto">
            <div class="card">
                <div class="card-header text-center">
                    <h4>Đổi Mật Khẩu</h4>
                </div>
                <div class="card-body">
                    <!-- Hiển thị thông báo lỗi hoặc thành công -->
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">${error}</div>
                    </c:if>
                    <c:if test="${not empty success}">
                        <div class="alert alert-success">${success}</div>
                    </c:if>

                    <!-- Kiểm tra nếu tài khoản có mật khẩu -->
                    <c:choose>
                        <c:when test="${not empty sessionScope.account.password}">
                            <form action="./changePassword" method="POST">
                                <div class="mb-3">
                                    <label for="currentPassword" class="form-label">Mật Khẩu Hiện Tại</label>
                                    <input type="password" id="currentPassword" name="currentPassword" class="form-control" placeholder="Nhập mật khẩu hiện tại" required>
                                </div>
                                <div class="mb-3">
                                    <label for="newPassword" class="form-label">Mật Khẩu Mới</label>
                                    <input type="password" id="newPassword" name="newPassword" class="form-control" placeholder="Nhập mật khẩu mới" required>
                                </div>
                                <div class="mb-3">
                                    <label for="confirmPassword" class="form-label">Xác Nhận Mật Khẩu</label>
                                    <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" placeholder="Xác nhận mật khẩu mới" required>
                                </div>
                                <button type="submit" class="btn btn-dark w-100">Lưu</button>
                            </form>
                        </c:when>
                      <%-- 
                        <c:otherwise>
                            <div class="alert alert-warning text-center">
                                <h6>Tài khoản của bạn hiện chưa có mật khẩu.</h6>
                                <a href="./setPasswordRequest" class="btn btn-primary mt-2">Nhận email đặt lại mật khẩu</a>
                            </div>
                        </c:otherwise>
                      --%>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
