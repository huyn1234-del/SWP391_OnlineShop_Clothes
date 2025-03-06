<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Slider"%>
<jsp:useBean id="slider" class="model.Slider" scope="request" />

<!DOCTYPE html>
<html>
    <head>
        <title>Slider Detail</title>
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
            input, textarea {
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
            <h2>Slider ID: <%= slider.getId() %></h2>
            <label>Title:</label>
            <input type="text" value="<%= slider.getTittle() %>" readonly />

            <label>Description:</label>
            <textarea readonly><%= slider.getDescription() %></textarea>

            <label>Image:</label>
            <br>
            <img src="<%= slider.getImage_url() %>" alt="Slider Image" width="100" height="50" />
            <br>
            
            <label>Status:</label>
            <input type="text" value="<%= slider.getIs_active().equals("True") ? "Active" : "Inactive" %>" readonly />

            <a href="slider">Back</a> 

        </div>
    </body>
</html>
