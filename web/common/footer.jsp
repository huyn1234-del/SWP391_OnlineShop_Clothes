<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../common/css.jsp" />
<footer class="footer" style="background: #212529">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-md-6 col-sm-6">
                <div class="footer__about">
                    <iframe style="width: 100%; height: 200px" src="" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                </div>
            </div>
            <div class="col-lg-3 offset-lg-1 col-md-3 col-sm-6">
                <div class="footer__widget">
                    <h6>Địa chỉ liên hệ</h6>
                    <ul>
                        <li style="color: white;">Ngọc Trục,Đại Mỗ,Nam Từ Liêm, Hà Nôi</li>

                    </ul>
                </div>
            </div>

            <div class="col-lg-3 offset-lg-1 col-md-6 col-sm-6">
                <div class="footer__widget">
                    <h6>Hỗ trợ</h6>
                    <div class="footer__newslatter">
                        <p style="color: white;">Hãy cho chúng tôi biết nếu bạn có bất kì thắc mắc hoặc yêu cầu hỗ trợ nào!</p>
                        <ul>
                            <li><a style="padding: 10px;
                                border: 1px solid white;
                                color: white;" href="${pageContext.request.contextPath}/contact">Liên hệ</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

    </div>
</footer>