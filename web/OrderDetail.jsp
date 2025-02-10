<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Order, java.util.List, model.OrderDetail" %>

<%
    Order order = (Order) request.getAttribute("order");
    List<OrderDetail> orderDetails = (List<OrderDetail>) request.getAttribute("orderDetails");
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chi tiết đơn hàng</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .section-header {
            background-color: #343a40;
            color: white;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 5px;
        }
        .table th, .table td {
            text-align: center;
            vertical-align: middle;
        }
        .note-section textarea {
            resize: none;
        }
        .btn-save-note {
            background-color: #007bff;
            color: white;
        }
    </style>
</head>
<body>
    <div class="container mt-4">
        <button class="btn btn-secondary mb-4" onclick="window.history.back()">&laquo; Quay lại</button>
        <h2 class="mb-4">Chi tiết đơn hàng</h2>

<%
    if (order == null) {
%>
        <div class="alert alert-danger">Đơn hàng không tồn tại hoặc không tìm thấy!</div>
<%
    } else {
%>
        <!-- Nội dung hiển thị thông tin đơn hàng -->
        <div class="row mb-4">
            <div class="col-md-6">
                <div class="section-header">Thông tin đơn hàng</div>
                <p>Ngày đặt hàng: <strong><%= order.getOrderedDate() %></strong></p>
                <p>Ngày nhận hàng (dự kiến): <strong><%= order.getReceiveDate() != null ? order.getReceiveDate() : "Không có" %></strong></p>
                <p>Phương thức thanh toán: <span class="text-primary"><%= order.getPaymentMethodName() %></span></p>
                <p>Trạng thái thanh toán: <span class="<%= order.getPaymentStatusId() == 1 ? "text-success" : "text-warning" %>">
                    <%= order.getPaymentStatusName() %>
                </span></p>
                <p>Tổng tiền: <span class="text-danger"><%= String.format("%,d", order.getTotalPrice()) %> đ</span></p>
                <p>Trạng thái đơn hàng: <span class="<%= order.getOrderStatusId() == 4 ? "text-danger" : "text-primary" %>">
                    <%= order.getOrderStatusName() %>
                </span></p>
            </div>
            <div class="col-md-6">
                <div class="section-header">Thông tin khách hàng</div>
                <p>Tên người nhận: <strong><%= order.getReceiverName() %></strong></p>
                <p>Số điện thoại: <strong><%= order.getPhone() %></strong></p>
                <p>Email: <strong><%= order.getEmail() %></strong></p>
                <p>Địa chỉ: <strong><%= order.getAddress() %></strong></p>
            </div>
        </div>
<%
    }
%>


        <!-- Danh sách sản phẩm -->
        <div class="section-header">Sản phẩm</div>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>STT</th>
                    <th>Sản phẩm</th>
                    <th>Kích cỡ</th>
                    <th>Số lượng</th>
                    <th>Giá</th>
                    <th>Tổng tiền</th>
                </tr>
            </thead>
            <tbody>
<% if (orderDetails == null || orderDetails.isEmpty()) { %>
    <tr>
        <td colspan="6" class="text-center text-danger">Không có sản phẩm nào trong đơn hàng này</td>
    </tr>
<% } else {
    int stt = 1;
    for (OrderDetail detail : orderDetails) {
%>
<tr>
    <td><%= stt++ %></td>
    <td><%= detail.getProductName() %></td>
    <td><%= detail.getSizeName() %></td>
    <td><%= detail.getQuantity() %></td>
    <td><%= String.format("%,d", detail.getUnitPrice()) %> đ</td>
    <td><%= String.format("%,d", detail.getTotalPrice()) %> đ</td>
</tr>
<% }} %>

            </tbody>
        </table>

        <!-- Ghi chú -->
        <div class="note-section mt-4">
            <div class="section-header">Ghi chú</div>
            <textarea class="form-control" rows="4" id="note" placeholder="Thêm ghi chú cho Sale..."></textarea>
            <button class="btn btn-save-note mt-3" onclick="saveNote()">Lưu ghi chú</button>
        </div>
    </div>

    <!-- Script lưu ghi chú -->
    <script>
        function saveNote() {
            const note = document.getElementById('note').value;
            if (note.trim() === "") {
                alert("Vui lòng nhập ghi chú trước khi lưu.");
                return;
            }
            alert("Ghi chú đã được lưu: " + note);
        }
    </script>
</body>
</html>
