<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.ProductFeedback" %>

<%
    ProductFeedback feedback = (ProductFeedback) request.getAttribute("feedback");
    if (feedback == null) {
%>
        <p>Không tìm thấy phản hồi!</p>
<%
        return;
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Cập nhật phản hồi</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #fef6ff;
            }
            .container {
                width: 50%;
                margin: auto;
                padding: 20px;
                background: #fff;
                border-radius: 10px;
            }
            input, select {
                width: 100%;
                padding: 8px;
                margin: 5px 0;
                border-radius: 5px;
                border: 1px solid #ccc;
            }
            .btn {
                background-color: gray;
                color: white;
                padding: 8px 16px;
                border: none;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Cập nhật phản hồi</h2>
            <form action="updateFeedback" method="post">
                <input type="hidden" name="feedback_id" value="<%= feedback.getFeedback_id() %>">

                <label>Tên khách hàng:</label>
                <input type="text" value="<%= feedback.getCustomer_name() %>" readonly>

                <label>Sản phẩm:</label>
                <input type="text" value="<%= feedback.getProduct_name() %>" readonly>

                <label>Đánh giá:</label>
                <select name="rating">
                    <option value="1" <%= feedback.getRating() == 1 ? "selected" : "" %>>1</option>
                    <option value="2" <%= feedback.getRating() == 2 ? "selected" : "" %>>2</option>
                    <option value="3" <%= feedback.getRating() == 3 ? "selected" : "" %>>3</option>
                    <option value="4" <%= feedback.getRating() == 4 ? "selected" : "" %>>4</option>
                    <option value="5" <%= feedback.getRating() == 5 ? "selected" : "" %>>5</option>
                </select>

                <label>Rate star:</label>
                <textarea name="review" required><%= feedback.getReview() %></textarea>

                <label>Status:</label>
                <select name="is_active">
                    <option value="1" <%= feedback.getIs_active() == 1 ? "selected" : "" %>>Active</option>
                    <option value="0" <%= feedback.getIs_active() == 0 ? "selected" : "" %>>Non active</option>
                </select>

                <button type="submit" class="btn">SAVE</button>
            </form>
        </div>
    </body>
</html>
