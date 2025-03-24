<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Tee & Jean</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
              rel="stylesheet">

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
        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>
        <jsp:include page="../common/header.jsp" />

        <!-- Breadcrumb Section Begin -->
        <section class="breadcrumb-option">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="breadcrumb__text">
                            <h4>Giỏ hàng</h4>
                            <div class="breadcrumb__links">
                                <a>Trang chủ</a>
                                <a>Sản phẩm</a>
                                <span>Giỏ hàng</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Breadcrumb Section End -->

        <!-- Start your project here -->

        <section style="margin: 50px 0px">
            <div class="container">
                <c:if test="${not empty sessionScope.cartQuantityError}">
                    <div class="text-danger row" style="margin-bottom: 50px;">
                        ${sessionScope.cartQuantityError}
                    </div>
                    <c:remove var="cartQuantityError" scope="session"/>
                </c:if>

                <div class="row" style="margin-top: 30px">
                    <div class="col-lg-8">
                        <form method="GET" action="updateCart">
                            <div class="shopping__cart__table">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>ID</th> 
                                            <th style="padding: 0 16px;">Product Image</th>
                                            <th style="padding: 0 16px;">Title</th>
                                            <th style="padding: 0 16px;">Price</th>
                                            <th style="padding: 0 16px;">Quantity</th>
                                            <th style="padding: 0 16px;">Total Cost</th>
                                            <th style="padding: 0 16px;"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="subtotal" value="${0}" />
                                        <c:forEach var="o" items="${cart}" varStatus="status">
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
                                                    <input style="width: 60px;text-align: center" id="quantity_${status.index}" name="quantity_${status.index}" type="number" min="1" value="${o.quantity}">

                                                    <!-- Hidden fields to track product and size -->
                                                    <input type="hidden" name="pid_${status.index}" value="${o.product.product_id}">
                                                    <input type="hidden" name="sid_${status.index}" value="${o.size.size_id}">
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

                            </div>
                            <div class="row">
                                <div class="col-lg-6 col-md-6 col-sm-6">
                                    <div class="continue__btn update__btn ">
                                        <button onclick="window.location.href = '${pageContext.request.contextPath}/removeAll'" style="width: 80%;margin-right:auto" class="btn btn-block btn-dark btn-lg" type="button">Xóa tất cả</button>
                                    </div>
                                </div>
                                <div class="col-lg-6 col-md-6 col-sm-6">
                                    <div class="continue__btn update__btn " style="margin: auto">
                                        <button style="width: 80%;margin-left:auto" class="btn btn-block btn-dark btn-lg" type="submit"> Cập nhật giỏ hàng</button>
                                    </div>
                                </div>
                            </div>
                            <div class="row" style="margin-top: 30px">
                                <div class="col-lg-12 col-md-12 col-sm-12">
                                    <div class="continue__btn text-center">
                                        <a style="width: 100%" href="${pageContext.request.contextPath}/homeproduct">Tiếp tục mua sắm</a>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>

                    <div class="col-lg-4">
                        <div class="cart__total">
                            <h4>Tổng giỏ hàng</h4>
                            <hr>
                            <ul>
                                <li style="font-weight: normal ">Tổng phụ <span class="text-reset"><fmt:formatNumber value="${subtotal}" type="currency" currencySymbol="₫" groupingUsed="true" /></span></li>
                                <li style="font-size: 21px;font-weight: bold">Tổng <span><fmt:formatNumber value="${subtotal}" type="currency" currencySymbol="₫" groupingUsed="true" /></span></li>
                            </ul>
                            <a href="checkout" class="primary-btn">Xác nhận đơn hàng</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End your project here -->

        <jsp:include page="../common/footer.jsp" />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <jsp:include page="../common/js.jsp" />

        <c:if test="${not empty sessionScope.cartError}">
            <script>
                                            alert('${sessionScope.cartError}');
            </script>
            <c:remove var="cartError" scope="session"/>
        </c:if>

        <c:if test="${not empty sessionScope.noAddressError}">
            <script>
                alert('${sessionScope.noAddressError}');
            </script>
            <c:remove var="noAddressError" scope="session"/>
        </c:if>
    </body>
</html>
