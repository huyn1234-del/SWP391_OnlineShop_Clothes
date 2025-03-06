<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../common/css.jsp" />
<footer class="footer" style="background: #212529">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-md-6 col-sm-6">
                <div class="footer__about">
                    <iframe style="width: 100%; height: 200px" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d10971.851007145786!2d105.56398356479062!3d21.086312384866847!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3134584fa63b2f87%3A0xe32074883f9b0849!2zVHLGsOG7m2MgTmfDoyA0IE5n4buNYyBMw6J1IDIwME0gKFjDoyDEkOG6oWkgxJDhu5NuZyAtIEh1eeG7h24gVGjhuqFjaCBUaOG6pXQpIC0gxJDGsOG7nW5nIFThu4luaCBM4buZIDQxOQ!5e0!3m2!1svi!2s!4v1729960752312!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                </div>
            </div>
            <div class="col-lg-3 offset-lg-1 col-md-3 col-sm-6">
                <div class="footer__widget">
                    <h6>Địa chỉ liên hệ</h6>
                    <ul>
                        <li style="color: white;">Số nhà 43, thôn 3, xã Đại Đồng, Huyện Thạch Thất, Thành phố Hà Nội</li>

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