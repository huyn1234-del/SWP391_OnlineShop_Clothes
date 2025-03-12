<%-- 
    Document   : MKTdashboard
    Created on : Feb 5, 2025, 5:00:07 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard</title>
        <script src="https://cdn.tailwindcss.com"></script>
    </head>
    <body class="flex bg-gray-100 h-screen">

        <!-- Sidebar Left -->
        <div class="w-40 bg-gray-800 text-white h-full flex flex-col p-4 shadow-lg">

            <!-- Logo -->
            <div class="text-lg font-bold mb-5 flex items-center">
                <a href="mktdashboard">
                    <span class="text-xl">DASHBOARD</span>
                </a>
            </div>


            <!-- Menu List -->
            <nav class="space-y-4 text-sm">
                <a href="slider" class="flex items-center space-x-2 text-gray-300 hover:text-white">
                    <span></span> <span>Slider</span>
                </a>
                <a href="CustomerServlet" class="flex items-center space-x-2 text-gray-300 hover:text-white">
                    <span></span> <span>Customer</span>
                </a>
                <a href="feedback" class="flex items-center space-x-2 text-gray-300 hover:text-white">
                    <span></span> <span>Feedback</span>
                </a>
                <a href="#" class="flex items-center space-x-2 text-gray-300 hover:text-white">
                    <span></span> <span>...</span>
                </a>
                <a href="#" class="flex items-center space-x-2 text-gray-300 hover:text-white">
                    <span></span> <span>...</span>
                </a>
            </nav>

        </div>

        <!-- Main Content -->
        <div class="flex-1 flex flex-col items-center p-5">

            <div class="grid grid-cols-4 gap-4 w-full max-w-md mb-5">
                <div class="bg-gray-300 p-3 text-center rounded-lg">
                    <p class="text-sm font-medium">Posts</p>
                    <p class="text-xl font-bold"><%= request.getAttribute("postCountpost") %></p>
                </div>
                <div class="bg-gray-300 p-3 text-center rounded-lg">
                    <p class="text-sm font-medium">Products</p>
                    <p class="text-xl font-bold"><%= request.getAttribute("postCountproduct") %></p>
                </div>
                <div class="bg-gray-300 p-3 text-center rounded-lg">
                    <p class="text-sm font-medium">Customers</p>
                    <p class="text-xl font-bold"><%= request.getAttribute("postCountcustomer") %></p>
                </div>
                <div class="bg-gray-300 p-3 text-center rounded-lg">
                    <p class="text-sm font-medium">Feedback</p>
                    <p class="text-xl font-bold"><%= request.getAttribute("postCountfeedback") %></p>
                </div>
            </div>

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
    </body>
</html>

