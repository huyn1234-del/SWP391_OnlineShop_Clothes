<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Customer"%>
<jsp:useBean id="customer" class="model.Customer" scope="request" />

<!DOCTYPE html>
<html>
    <head>
        <title>Customer Detail</title>
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
            <h2>User ID: <%= customer.getUser_id() %></h2>
            <label>First Name:</label>
            <input type="text" value="<%= customer.getFirst_name() %>" readonly />

            <label>Last Name:</label>
            <input type="text" value="<%= customer.getLast_name() %>" readonly />

            <label>Gender:</label>
            <input type="text" value="<%= customer.isGender() ? "Male" : "Female" %>" readonly />
            
            <label>Date of Birth:</label>
            <input type="text" value="<%= customer.getDob() %>" readonly />

            <label>Email:</label>
            <input type="text" value="<%= customer.getEmail() %>" readonly />

            <label>Điện thoại:</label>
            <input type="text" value="<%= customer.getPhone() %>" readonly />

            <label>Status:</label>
            <input type="text" value="<%= customer.isIs_active() ? "Active" : "Inactive" %>" readonly />

            <a href="CustomerServlet">Back</a> &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="updateCustomer?id=${customer.getUser_id()}">Edit</a> 
        </div>
    </body>
</html>
