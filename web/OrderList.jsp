<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, model.Order" %>

<%
    List<Order> orders = (List<Order>) request.getAttribute("orders");
    int currentPage = (int) request.getAttribute("currentPage");
    int totalPages = (int) request.getAttribute("totalPages");
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách đơn hàng</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .sidebar {
            height: 100vh;
            width: 200px;
            position: fixed;
            top: 0;
            left: 0;
            background-color: #343a40;
            padding-top: 20px;
        }
        .sidebar a {
            padding: 10px;
            text-decoration: none;
            font-size: 18px;
            color: white;
            display: block;
        }
        .sidebar a:hover {
            background-color: #575d63;
        }
        .content {
            margin-left: 220px;
            padding: 20px;
        }
        .pagination {
            justify-content: center;
        }
        .table th, .table td {
            text-align: center;
            vertical-align: middle;
        }
        .filter-section {
            background-color: #f8f9fa;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <!-- Sidebar -->
    <div class="sidebar">
        <a href="SaleDashboard.jsp">Trang Chủ</a>
        <a href="OrderList" class="active">Danh sách đơn hàng</a>
    </div>
    
    <!-- Content -->
    <div class="content">
        <h2>Danh sách đơn hàng</h2>

        <!-- Bộ lọc -->
        <div class="filter-section">
<form method="GET" action="OrderList">
    <div class="row">
        <div class="col-md-5">
            <label for="startDate">Từ ngày:</label>
            <input type="date" id="startDate" name="startDate" value="<%= request.getAttribute("startDate") != null ? request.getAttribute("startDate") : "" %>" class="form-control">
        </div>
        <div class="col-md-5">
            <label for="endDate">Đến ngày:</label>
            <input type="date" id="endDate" name="endDate" value="<%= request.getAttribute("endDate") != null ? request.getAttribute("endDate") : "" %>" class="form-control">
        </div>
        <div class="col-md-2 d-flex align-items-end">
            <button type="submit" class="btn btn-primary w-100">Tìm kiếm</button>
        </div>
    </div>
</form>


        </div>

        <!-- Bảng danh sách đơn hàng -->
        <table class="table table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>STT</th>
                    <th>Ngày đặt hàng</th>
                    <th>Tổng giá tiền (đ)</th>
                    <th>Phương thức thanh toán</th>
                    <th>Trạng thái thanh toán</th>
                    <th>Trạng thái đơn hàng</th>
                    <th>Chi tiết</th>
                </tr>
            </thead>
<tbody>
    <% if (orders == null || orders.isEmpty()) { %>
        <tr>
            <td colspan="7" class="text-center text-danger">Không có đơn hàng nào</td>
        </tr>
    <% } else {
        int stt = 1 + (currentPage - 1) * 10; // Tính STT theo từng trang
        for (Order order : orders) {
            // Ánh xạ phương thức thanh toán
            String paymentMethod = "Thanh toán khi nhận hàng";
            if ("Thanh toán khi nhận hàng".equals(order.getPaymentMethodName())) {
                paymentMethod = "Thanh toán khi nhận hàng";
            } else if ("Thanh toán online".equals(order.getPaymentMethodName())) {
                paymentMethod = "Thanh toán online";
            }

            // Ánh xạ trạng thái thanh toán
            String paymentStatus = "Không xác định";
            String paymentStatusClass = "";
            if ("1".equals(order.getPaymentStatusName())) {
                paymentStatus = "Đã thanh toán";
                paymentStatusClass = "text-success";
            } else if ("2".equals(order.getPaymentStatusName())) {
                paymentStatus = "Chờ thanh toán";
                paymentStatusClass = "text-warning";
            }

            // Ánh xạ trạng thái đơn hàng
            String orderStatus = "Không xác định";
            String orderStatusClass = "";
            if ("4".equals(order.getOrderStatusName())) {
                orderStatus = "Đã hủy";
                orderStatusClass = "text-danger";
            } else if ("5".equals(order.getOrderStatusName())) {
                orderStatus = "Đang xử lý";
                orderStatusClass = "text-primary";
            } else if ("6".equals(order.getOrderStatusName())) {
                orderStatus = "Đã hoàn tất";
                orderStatusClass = "text-success";
            }
    %>
    <tr>
        <td><%= stt++ %></td>
        <td><%= order.getOrderedDate() %></td>
        <td><%= String.format("%,d", order.getTotalPrice()) %> đ</td>
        <td><%= paymentMethod %></td>
        <td class="<%= paymentStatusClass %>"><%= paymentStatus %></td>
        <td class="<%= orderStatusClass %>"><%= orderStatus %></td>
        <td>
            <a href="OrderDetail?orderId=<%= order.getOrderId() %>" class="btn btn-info btn-sm">Xem</a>
        </td>
    </tr>
    <% }} %>
</tbody>

        </table>

        <!-- Phân trang -->
<nav>
    <ul class="pagination justify-content-center">
        <% if (currentPage > 1) { %>
            <li class="page-item">
                <a class="page-link" href="?page=<%= currentPage - 1 %>&startDate=<%= request.getAttribute("startDate") %>&endDate=<%= request.getAttribute("endDate") %>">&laquo; Trước</a>
            </li>
        <% } %>
        <% for (int i = 1; i <= totalPages; i++) { %>
            <li class="page-item <%= (i == currentPage) ? "active" : "" %>">
                <a class="page-link" href="?page=<%= i %>&startDate=<%= request.getAttribute("startDate") %>&endDate=<%= request.getAttribute("endDate") %>"><%= i %></a>
            </li>
        <% } %>
        <% if (currentPage < totalPages) { %>
            <li class="page-item">
                <a class="page-link" href="?page=<%= currentPage + 1 %>&startDate=<%= request.getAttribute("startDate") %>&endDate=<%= request.getAttribute("endDate") %>">Sau &raquo;</a>
            </li>
        <% } %>
    </ul>
</nav>

    </div>
</body>
</html>
