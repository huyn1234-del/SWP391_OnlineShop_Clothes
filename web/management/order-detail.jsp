<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <jsp:include page="../common/css.jsp" />

        <style>
            body {
                background-color: #f8f9fa;
            }
            .card {
                border: none;
                box-shadow: 0 0.125rem 0.25rem rgba(0, 0, 0, 0.075);
            }
            .card-header {
                background-color: #f8f9fa;
                border-bottom: 1px solid #e9ecef;
            }
            .badge-success {
                background-color: #28a745;
            }
            .badge-pink {
                background-color: #fce4ec;
                color: #d81b60;
            }
            .table-gray {
                background-color: lightgrey;
            }
            .btn-outline-primary {
                color: #007bff;
                border-color: #007bff;
            }
            .btn-outline-primary:hover {
                background-color: #007bff;
                color: white;
            }
            .card-header{
                background: black;

            }
            .table td, .table th {
                vertical-align: middle !important;
            }
        </style>
    </head>
    <body>
       <jsp:include page="../common/headermanage.jsp"/>
       
        <div class="container mt-5">
            <button class="btn btn-dark" onclick="goBack()">
                <i class="fa fa-arrow-left me-2"></i> Back
            </button>
        </div>
        <div class="container mt-5" style="margin-bottom: 70px;padding-bottom: 100px">

            <h2 class="mb-4">Chi tiết đơn hàng</h2>

            <div class="row">
                <div class="col-md-6 mb-4">
                    <div class="card">
                        <div class="card-header">
                            <h5 class="mb-0 text-white">Thông tin đơn hàng</h5>
                        </div>
                        <div class="card-body">     

                            <fmt:parseDate value="${order.orderedDate}" pattern="yyyy-MM-dd HH:mm:ss" var="orderDate" />
                            <p><strong>Ngày đặt hàng:</strong> <fmt:formatDate value="${orderDate}" pattern="dd/MM/yyyy HH:mm:ss" /> </p>
                            <fmt:parseDate value="${order.receiveDate}" pattern="yyyy-MM-dd HH:mm:ss" var="receiveDate" />
                            <p><strong>Ngày nhận hàng (dự kiến):</strong> 
                                <c:if test="${order.receiveDate != null}"><fmt:formatDate value="${receiveDate}" pattern="dd/MM/yyyy HH:mm:ss" /></c:if>
                                <c:if test="${order.receiveDate == null}"><span class="text-muted">Không có</span></c:if></p>
                                <p><strong>Phương thức thanh toán:</strong> <span 
                                    <c:if test="${order.paymentMethodId == 2}">class="badge-primary badge font-weight-bold"</c:if>
                                    <c:if test="${order.paymentMethodId == 1}">class="badge-info badge font-weight-bold"</c:if>
                                    > ${order.paymentMethodName}</span></p>
                            <p><strong>Trạng thái thanh toán:</strong> <span  <c:if test="${order.paymentStatusId == 1}">class="badge-warning badge font-weight-bold"</c:if>
                                                                                                                         <c:if test="${order.paymentStatusId == 2}">class="badge-success badge font-weight-bold"</c:if>
                                                                                                                         <c:if test="${order.paymentStatusId == 3}">class="badge-danger badge font-weight-bold"</c:if>
                                                                                                                         s                                          <c:if test="${order.paymentStatusId == 4}">class="badge-warning badge font-weight-bold"</c:if>
                                                                                                                         <c:if test="${order.paymentStatusId == 5}">class="badge-info badge font-weight-bold"</c:if>
                                                                                                                         <c:if test="${order.paymentStatusId == 5}">class="badge-success badge font-weight-bold"</c:if>
                                                                                                                         >${order.paymentStatusName}</span></p>
                            <p ><strong>Mã giao dịch VNPAY:</strong> ${order.vnpTxnRef != null ? order.vnpTxnRef: '<span class="text-muted">Không có</span>'}</p>
                            <p ><strong>Mã vận đơn:</strong> ${order.shippingCode != null ? order.shippingCode: '<span class="text-muted">Không có</span>'} </p>
                            <p><strong>Tổng tiền:</strong> <span class="badge badge-pink"><fmt:formatNumber value="${order.totalAmount}" type="currency" currencySymbol="₫" groupingUsed="true" /></span></p>
                            <p><strong>Trạng thái đơn hàng:</strong> <span <c:if test="${order.orderStatusId == 1}">class="badge-warning badge font-weight-bold"</c:if>
                                                                                                                    <c:if test="${order.orderStatusId == 2}">class="badge-primary badge font-weight-bold"</c:if>
                                                                                                                    <c:if test="${order.orderStatusId == 3}">class="badge-info badge font-weight-bold"</c:if>
                                                                                                                    <c:if test="${order.orderStatusId == 4}">class="badge-success badge font-weight-bold"</c:if>
                                                                                                                    <c:if test="${order.orderStatusId == 5}">class="badge-danger badge font-weight-bold"</c:if>
                                                                                                                    <c:if test="${order.orderStatusId == 6}">class="badge-success badge font-weight-bold"</c:if>
                                                                                                                    >${order.orderStatusName}</span></p>
                        </div>
                    </div>
                </div>

                <div class="col-md-6 mb-4">
                    <div class="card">
                        <div class="card-header">
                            <h5 class="mb-0 text-white">Thông tin khách hàng</h5>
                        </div>
                        <div class="card-body">
                            <p><strong>Tên người nhận:</strong> ${order.receiverName}</p>
                            <p><strong>Số điện thoại:</strong> ${order.phone}</p>
                            <p><strong>Email:</strong> ${order.email}</p>
                            <p><strong>Địa chỉ:</strong> ${order.address}, ${order.wardName}, ${order.districtName}, ${order.provinceName}</p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="card mt-4">
                <div class="card-header">
                    <h5 class="mb-0 text-white">Sản phẩm</h5>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead class="table-gray">
                                <tr>
                                    <th class="text-center">STT</th>
                                    <th>Sản phẩm</th>
                                    <th class="text-center">Kích cỡ</th>
                                    <th class="text-center">Số lượng</th>
                                    <th>Giá</th>
                                    <th>Tổng tiền</th>

                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="subtotal" value="0"></c:set>
                                <c:forEach var="o" items="${orderDetails}" varStatus="status">
                                    <tr>
                                        <td class="text-center">${status.index + 1}</td>
                                        <td>
                                            <img src="${o.thumbnail}" alt="Sản phẩm" class="img-thumbnail me-2" style="width: 50px;height: 50px">
                                            ${o.productName}
                                        </td>
                                        <td class="text-center">${o.sizeName}</td>
                                        <td class="text-center">${o.quantity}</td>
                                        <td><fmt:formatNumber value="${o.unitPrice}" type="currency" currencySymbol="₫" groupingUsed="true" /></td>
                                        <td><fmt:formatNumber value="${o.totalPrice}" type="currency" currencySymbol="₫" groupingUsed="true" /></td>


                                        <c:set var="subtotal" value="${subtotal + o.totalPrice}"/>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="5" class="text-end"><strong>Tạm tính</strong></td>
                                    <td colspan="1"><fmt:formatNumber value="${subtotal}" type="currency" currencySymbol="₫" groupingUsed="true" /></td>
                                </tr>

                                <tr>
                                    <td colspan="5" class="text-end"><strong>Sử dụng voucher</strong></td>
                                    <td colspan="1" class="text-danger">-<fmt:formatNumber value="${subtotal*order.voucherPercent/100}" type="currency" currencySymbol="₫" groupingUsed="true" /></td>
                                </tr>
                                <tr>
                                    <td colspan="5" class="text-end"><strong>Phí vận chuyển</strong></td>
                                    <td colspan="1"><fmt:formatNumber value="${order.shippingFee}" type="currency" currencySymbol="₫" groupingUsed="true" /></td>
                                </tr>

                                <tr>
                                    <td colspan="5" class="text-end"><strong>Thành tiền</strong></td>
                                    <td colspan="1" class="text-success"><fmt:formatNumber value="${order.totalAmount}" type="currency" currencySymbol="₫" groupingUsed="true" /></td>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
            <div class="text-right mt-4 mb-5 d-flex justify-content-end">

                <c:if test="${order.orderStatusId == 3}">

                    <form action="${pageContext.request.contextPath}/doneorder" method="post" style="margin-left: 20px">
                        <input type="hidden" name="orderId" value="${order.orderId}">                      
                        <button type="submit" class="btn btn-success btn-lg" >
                            Đã nhận hàng
                        </button>
                    </form>

                </c:if>

                <c:if test="${order.orderStatusId == 6}">

                    <form action="${pageContext.request.contextPath}/createorderghn" method="post" style="margin-left: 20px">
                        <input type="hidden" name="orderId" value="${order.orderId}">                      
                        <button type="submit" class="btn btn-info btn-lg" >
                            Tạo đơn vận chuyển GHN
                        </button>
                    </form>

                </c:if>

                <c:if test="${order.orderStatusId == 2}">

                    <form action="${pageContext.request.contextPath}/packagedone" method="post" style="margin-left: 20px">
                        <input type="hidden" name="orderId" value="${order.orderId}">                      
                        <button type="submit" class="btn btn-info btn-lg" >
                            Đóng gói thành công
                        </button>
                    </form>

                </c:if>
                <c:if test="${order.orderStatusId == 1 || order.orderStatusId == 2 || order.orderStatusId == 6 ||order.orderStatusId == 3}">
                    <button id="cancelButton" style="margin-left: 20px" onclick="cancelOrder(${order.orderId},${order.paymentMethodId},${order.paymentStatusId})" type="button" class="btn btn-danger btn-lg me-2" >
                        Hủy đơn hàng
                    </button>
                </c:if>
                <c:if test="${order.orderStatusId == 5 && order.paymentStatusId == 4}">
                    <form style="margin-left: 20px" action="${pageContext.request.contextPath}/refundpayment" method="post" class="me-2"> 
                        <input type="hidden" name="vnp_TxnRef" value="${order.vnpTxnRef}">
                        <input type="hidden" name="amount" value="${order.totalAmount}">
                        <input type="hidden" name="vnp_CreateDate" value="${order.vnpCreateDate}">
                        <input type="hidden" name="vnp_CreateBy" value="${sessionScope.account.username}">
                        <input type="hidden" name="orderId" value="${order.orderId}">
                        <button  type="submit" class="btn btn-warning btn-lg" >
                            Hoàn tiền
                        </button>
                    </form>
                </c:if>


            </div>
        </div>
        <!-- Bootstrap JS with Popper.js -->

        <jsp:include page="../common/js.jsp" />


        <script>
            function cancelOrder(orderId, methodId, payStatus) {
                document.getElementById('cancelButton').disabled = true;
                const xhr = new XMLHttpRequest();
                xhr.open("POST", "cancelorder", true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhr.send("orderId=" + orderId + "&methodId=" + methodId + "&payStatus=" + payStatus);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        alert("Hủy đơn hàng thành công !");
                        location.reload();
                    }
                };
            }

            function goBack() {
                window.location.href = "${pageContext.request.contextPath}/orderlist";
            }
        </script>

        <c:if test="${not empty sessionScope.notify}">
            <script>
                alert('${sessionScope.notify}');
            </script>
            <%session.removeAttribute("notify");%>
        </c:if>

        <c:if test="${not empty sessionScope.refundMsg}">
            <script>
                alert('${sessionScope.refundMsg}');
            </script>
            <%session.removeAttribute("refundMsg");%>
        </c:if>


    </body>
</html>