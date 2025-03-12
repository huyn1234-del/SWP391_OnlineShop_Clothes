<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Xác thực OTP</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="./account/css/verify_style.css">

    <style>
        .otp-container {
            max-width: 400px;
            margin: 50px auto;
        }
        .otp-input {
            width: 50px;
            height: 50px;
            font-size: 24px;
            text-align: center;
            margin: 0 5px;
        }
    </style>
</head>
<body>
    <div class="container text-center">
        <div class="otp-container">
            <h2>Nhập OTP 6 chữ số</h2>
            
            <!-- Hiển thị thông báo lỗi nếu có -->
            <c:if test="${error != null}">
                <div class="alert alert-danger mt-3">${error}</div>
            </c:if>

            <form action="./verify" method="POST" id="otpForm">
                <div class="d-flex justify-content-center mt-4">
                    <c:forEach var="i" begin="1" end="6">
                        <input type="text" class="form-control otp-input" maxlength="1" required>
                    </c:forEach>
                </div>

                <input type="hidden" name="otp" id="otpHiddenInput">
                <input type="hidden" name="userId" value="${requestScope.userId}">

                <div class="d-flex justify-content-center mt-4 gap-3">
                    <button type="button" class="btn btn-outline-primary" id="resendOtpButton">Gửi lại OTP</button>
                    <button type="submit" class="btn btn-primary">Xác thực</button>
                </div>
            </form>
        </div>
    </div>

    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const otpInputs = document.querySelectorAll(".otp-input");

            otpInputs.forEach((input, index) => {
                input.addEventListener("input", (event) => {
                    const value = event.target.value;
                    
                    if (value.match(/^\d$/)) {
                        if (index < otpInputs.length - 1) {
                            otpInputs[index + 1].focus();
                        }
                    } else {
                        event.target.value = "";
                    }
                });

                input.addEventListener("keydown", (event) => {
                    if (event.key === "Backspace" && !input.value && index > 0) {
                        otpInputs[index - 1].focus();
                    }
                });
            });

            // Gửi giá trị OTP khi submit
            document.getElementById("otpForm").addEventListener("submit", function () {
                let otpValue = "";
                otpInputs.forEach(input => otpValue += input.value);
                document.getElementById("otpHiddenInput").value = otpValue;
            });

            // Xử lý gửi lại OTP
            document.getElementById("resendOtpButton").addEventListener("click", function () {
                const userId = document.querySelector('input[name="userId"]').value;

                fetch(`resendOtp?userId=${userId}`)
                    .then(response => response.json())
                    .then(data => alert(data.success ? "Gửi lại OTP thành công!" : "Gửi lại OTP thất bại."))
                    .catch(() => alert("Đã xảy ra lỗi. Vui lòng thử lại sau."));
            });
        });
    </script>
</body>
</html>