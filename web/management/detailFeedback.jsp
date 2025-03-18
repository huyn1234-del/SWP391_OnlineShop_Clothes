<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.ProductFeedback"%>
<jsp:useBean id="feedback" class="model.ProductFeedback" scope="request" />

<!DOCTYPE html>
<html>
    <head>
        <title>Feedback Detail</title>
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
            input {
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
            <h2>Feedback ID: <%= feedback.getFeedback_id() %></h2>
            <label>Customer Name:</label>
            <input type="text" value="<%= feedback.getCustomer_name() %>" readonly />

            <label>Product Name:</label>
            <input type="text" value="<%= feedback.getProduct_name() %>" readonly />

            <label>Review:</label>
            <input type="text" value="<%= feedback.getReview() %>" readonly />

            <label>Rating:</label>
            <input type="text" value="<%= feedback.getRating() %>/5" readonly />

            <label>Status:</label>
            <input type="text" value="<%= feedback.getIs_active() == 1 ? "Active" : "Non active" %>" readonly />

            <a href="feedback">Back</a> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="updateFeedback?id=<%= feedback.getFeedback_id() %>">Edit</a> 
        </div>
    </body>
</html>