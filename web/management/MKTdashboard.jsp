<%-- 
    Document   : MKTdashboard
    Created on : Feb 5, 2025, 5:00:07 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý sản phẩm</title>
        <link rel="icon" href="img/webLogo.jpg" type="image/x-icon" />
        <!-- CSS Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
              crossorigin="anonymous">
        <!-- Script Link Bootstrap -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

        <!-- Font awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <jsp:include page="../common/css.jsp" />
        <style>
            .criteria{
                border: 1px solid #bb9797;
                border-radius: 6px;
                padding: 10px;
                box-shadow: 1px 1px 1px 1px #eeafaf;
            }

            .icon{
                justify-content: left;
                display: flex;
                align-items: center;
                font-size: 30px;
            }

            .content{
                text-align: right;
                padding: 0;
            }

            .content p{
                margin: 0;
            }

            .list2{
                margin-bottom: 22px;
            }

            .products{
                border: 1px solid gray;
                border-radius: 12px;
                box-shadow: 1px 1px 4px gray;
            }

            .edit{
                display: inline-block;
                background-color: yellow;
                padding: 6px 8px;
                border-radius: 4px;
                cursor: pointer;
                box-shadow: 1px 1px 6px gray;
            }

            .remove{
                color: white;
                display: inline-block;
                background-color: red;
                padding: 6px 8px;
                border-radius: 4px;
                cursor: pointer;
                margin-left: 12px;
                box-shadow: 1px 1px 6px gray;
            }

            .edit:hover,.remove:hover,.add:hover{
                opacity: 0.8;
            }

            .add{
                background-color: #c5c511;
                padding: 8px 10px;
                border-radius: 4px;
                color: white;
                cursor: pointer;
                box-shadow: 1px 1px 6px gray;
            }

            .product-img{
                width: 25%;
            }

            .product-img img{
                width: 60%;
            }

            .dropdown-toggle::after{
                color: white;
            }

            label{
                color: #8e7c7c;
            }

            select{
                padding: 16px;
            }

            .input{
                margin: 10px 0;
            }

            .search{
                width: 100%;
                margin-top: 10px;
                padding: 16px 0;
            }
            .stat-card {
                background-color: #d1d5db; 
                color: white;
                padding: 20px;
                text-align: center;
                border-radius: 12px;
                box-shadow: 2px 2px 8px rgba(0, 0, 0, 0.3);
            }

            .stat-card p:first-child {
                font-size: 16px;
                font-weight: 500;
                margin-bottom: 10px;
            }

            .stat-card p:last-child {
                font-size: 28px;
                font-weight: bold;
            }

            .grid-custom {
                display: grid;
                grid-template-columns: repeat(2, 1fr); /* 2 ô 1 dòng */
                gap: 30px;
                max-width: 500px;
                width: 100%;
                margin-bottom: 30px;
            }

        </style>
    </head>
    <body>
        <div class="row">
            <jsp:include page="../common/headermanage.jsp" />
            <!-- START menu -->
            <jsp:include page="marketing_header.jsp"/>
            <!-- END menu -->

            <!-- Main Content -->
            <div class="flex-1 flex flex-col items-center p-5">
                <center>
                <div class="grid-custom">
                    <div class="stat-card">
                        <p>Posts</p>
                        <p><%= request.getAttribute("postCountpost") %></p>
                    </div>
                    <div class="stat-card">
                        <p>Products</p>
                        <p><%= request.getAttribute("postCountproduct") %></p>
                    </div>
                    <div class="stat-card">
                        <p>Customers</p>
                        <p><%= request.getAttribute("postCountcustomer") %></p>
                    </div>
                    <div class="stat-card">
                        <p>Feedback</p>
                        <p><%= request.getAttribute("postCountfeedback") %></p>
                    </div>
                </div>
                    </center>

                <!-- Date Range Selection -->
                <div class="w-full max-w-md border-t border-black py-4 text-center">
                    <label class="text-sm font-medium">New customers From</label>
                    <input type="date" id="startDate" class="bg-gray-300 px-2 py-1 rounded-full mx-1 text-xs" value="2025-01-15">
                    <label class="text-sm font-medium">To</label>
                    <input type="date" id="endDate" class="bg-gray-300 px-2 py-1 rounded-full mx-1 text-xs" value="2025-01-22">
                    <button onclick="updateChart()" class="bg-blue-500 text-white px-3 py-1 rounded-full text-xs ml-1">Update</button>
                </div>

                <!-- Chart Placeholder -->
                <div class="bg-gray-300 w-full max-w-md h-40 flex justify-center items-center mt-5 rounded-lg">
                    <canvas id="barChart" class="w-full h-full"></canvas>
                </div>

            </div>
        </div>
    </body>
</html>

