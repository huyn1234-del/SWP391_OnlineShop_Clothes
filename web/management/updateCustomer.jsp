<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Customer" %>
<%
    Customer customer = (Customer) request.getAttribute("customer");
    String errorMessage = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Cập nhật thông tin khách hàng</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }
        .container {
            width: 50%;
            margin: auto;
            background: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            margin-top: 50px;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        label {
            font-weight: bold;
            margin-top: 10px;
            display: block;
        }
        input, select {
            width: 100%;
            padding: 8px;
            margin: 5px 0;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        .btn {
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            border: none;
            width: 100%;
            margin-top: 10px;
            cursor: pointer;
            font-size: 16px;
        }
        .btn:hover {
            background-color: #45a049;
        }
        .error {
            color: red;
            text-align: center;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Cập nhật thông tin khách hàng</h2>

        <% if (errorMessage != null) { %>
            <p class="error"><%= errorMessage %></p>
        <% } %>

        <form action="updateCustomer" method="post">
            <input type="hidden" name="id" value="<%= customer.getUser_id() %>">
            
            
            <label>First Name:</label>
            <input type="text" name="firstName" value="<%= customer.getFirst_name() %>" required>

            <label>Last Name:</label>
            <input type="text" name="lastName" value="<%= customer.getLast_name() %>" required>

            <label>Email:</label>
            <input type="email" name="email" value="<%= customer.getEmail() %>" required>

            <label>Số điện thoại:</label>
            <input type="text" name="phone" value="<%= customer.getPhone() %>" required>

            <label>Ngày sinh:</label>
            <input type="date" name="dob" value="<%= customer.getDob() %>">

            <label>Giới tính:</label>
            <select name="gender">
                <option value="male" <%= customer.isGender() ? "selected" : "" %>>Male</option>
                <option value="female" <%= !customer.isGender() ? "selected" : "" %>>Female</option>
            </select>

            <label>Trạng thái:</label>
            <select name="status">
                <option value="1" <%= customer.isIs_active() ? "selected" : "" %>>Active</option>
                <option value="0" <%= !customer.isIs_active() ? "selected" : "" %>>Inactive</option>
            </select>

            <button type="submit" class="btn">SAVE</button>
        </form>
    </div>
</body>
</html>


