<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Tee & Jean</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
              rel="stylesheet">

        <jsp:include page="../common/css.jsp" />

        <link rel="stylesheet" href="./account/css/registerstyle.css"/>
        <!-- Css Styles -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css/magnific-popup.css" type="text/css">
        <link rel="stylesheet" href="css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">
    </head>

    <body>

        <jsp:include page="../common/header.jsp" />

        <!-- Breadcrumb Section Begin -->
        <section class="breadcrumb-option">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="breadcrumb__text">
                            <h4>Xác nhận đơn hàng</h4>
                            <div class="breadcrumb__links">
                                <a>Trang chủ</a>
                                <a>Sản phẩm</a>
                                <a>Giỏ hàng</a>
                                <span>Xác nhận</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Breadcrumb Section End -->
        <!-- Start your project here -->
        <section class="shopping-cart spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="shopping__cart__table">
                            <table>
                                <thead>
                                    <tr>
                                        <th>ID</th> 
                                        <th style="padding: 0 16px;">Product   Image</th>
                                        <th style="padding: 0 16px;">Title</th>
                                        <th style="padding: 0 16px;">Price</th>
                                        <th style="padding: 0 16px;">Quantity</th>
                                        <th style="padding: 0 16px;">Total Cost</th>
                                        <th style="padding: 0 16px;"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="subtotal" value="${0}" />
                                    <c:forEach var="o" items="${items}" varStatus="status">
                                        <fmt:setLocale value="vi_VN" />
                                        <c:set var="price" value="${o.product.price}" />

                                        <tr>
                                            <td>${o.product.product_id}</td>

                                            <td>
                                                <img src="${o.product.thumbnail}" alt="Product Image" style="width: 80px; height: 80px;">
                                            </td>

                                            <td>${o.product.product_name}</td>

                                            <td>
                                                <fmt:formatNumber value="${price}" type="currency" currencySymbol="₫" groupingUsed="true" />
                                            </td>

                                            <td style="width:150px">
                                                <input style="width: 60px;text-align: center "value="${o.quantity}" readonly="">                                               
                                            </td>

                                            <td>
                                                <fmt:formatNumber value="${price * o.quantity}" type="currency" currencySymbol="₫" groupingUsed="true" />
                                            </td>

                                            <td>
                                                <a class="btn btn-sm btn-outline-danger" href="removeOne?pid=${o.product.product_id}&sid=${o.size.size_id}"><i class="fa fa-trash"></i></a>
                                            </td>

                                            <c:set var="subtotal" value="${subtotal + (price * o.quantity)}" />
                                            <c:set var="maxIndex" value="${status.index}" />
                                        </tr>
                                    </c:forEach>

                                <input type="hidden" name="maxIndex" value="${maxIndex}">
                                </tbody>
                            </table>
                            <div class="row" style="margin-top: 30px">
                                <div class="col-lg-12 col-md-12 col-sm-12">
                                    <div class="continue__btn text-center">
                                        <a style="width: 100%" href="${pageContext.request.contextPath}/homeproduct">Tiếp tục mua sắm</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>               
                    <div class="col-lg-4">
                        <div class="checkout__order">
                          <!--  <div class="row">
                                <h4 class="mb-3 col-10" style="font-weight: bold">Địa chỉ giao hàng </h4>
                                <a href="address" class="btn btn-dark col-2" style="height: 40px"><span class="fa fa-pencil"></span></a>
                            </div>
                            <hr style="border: 0.1px solid black;">
                            <div class="mb-2">
                                <c:if test="${address != null}">
                                    <p class="mb-1"><strong>${address.receiver_name}</strong></p>
                                    <p class="mb-1">${address.address}, ${address.ward_name}, </p>
                                    <p class="mb-1">${address.district_name}, ${address.province_name}</p>
                                    <p class="mb-1">Số điện thoại: ${address.phone}</p>
                                </c:if>
                            </div>

                        </div>-->

                        <div >
                            <div class="checkout__order">
                                <h3 style="font-weight: bold;">Tổng đơn hàng</h3>
                                <hr style="border: 0.1px solid black;">
                                <ul class="checkout__total__all" style="border:none">
                                    <li style="font-weight: normal ">Tổng <span class="text-reset"><fmt:formatNumber value="${subtotal}" type="currency" currencySymbol="₫" groupingUsed="true" /></span></li>             
                                </ul>
                                <hr style="border: 0.1px solid black;">
                                <form action="checkout" method="post">
                                    <input type="hidden" name="totalPrice" value="${subtotal}">                                  
                                    <button type="submit" class="site-btn">Đặt hàng</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End your project here -->
        <jsp:include page="../common/footer.jsp" />
        <!-- Bootstrap JS with Popper.js -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script> 
        <jsp:include page="../common/js.jsp" />

        <script>
            function setPaymentMethod(id) {
                document.querySelector('#selectedPaymentMethod').value = id;
            }
        </script>


        <c:if test="${not empty sessionScope.totalAmountError}">
            <script>
                alert('${sessionScope.totalAmountError}');
            </script>

            <%session.removeAttribute("totalAmountError");%>
        </c:if>

        <c:if test="${not empty sessionScope.systemError}">
            <script>
                alert('Lỗi hệ thống. Vui lòng thử lại sau !');
            </script>

            <%session.removeAttribute("noAddressError");%>
        </c:if>

    </body>

</html>