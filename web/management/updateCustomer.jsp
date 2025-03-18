<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.ProductFeedback" %>
<%
    ProductFeedback feedback = (ProductFeedback) request.getAttribute("feedback");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Feedback Form</title>
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
            <form action="updateFeedback" method="post">
                <h2>Feedback ID: <%= feedback.getFeedback_id() %></h2>
                <input type="hidden" name="feedback_id" value="<%= feedback.getFeedback_id() %>">

                <label>Customer Name:</label>
                <input type="text" value="<%= feedback.getCustomer_name() %>" readonly>

                <label>Product Name:</label>
                <input type="text" value="<%= feedback.getProduct_name() %>" readonly>

                <label>Review:</label>
                <textarea name="review" required><%= feedback.getReview() %></textarea>

                <label>Rating:</label>
                <input type="number" name="rating" value="<%= feedback.getRating() %>" min="1" max="5" required>

                <label>Status:</label>
                <select name="is_active">
                    <option value="1" <%= feedback.getIs_active() == 1 ? "selected" : "" %>>Active</option>
                    <option value="0" <%= feedback.getIs_active() == 0 ? "selected" : "" %>>Inactive</option>
                </select>

                <button type="submit" class="btn">Save</button>
            </form>
        </div>
    </body>
</html>
