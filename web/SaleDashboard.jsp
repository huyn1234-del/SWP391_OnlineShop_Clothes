<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sale Dashboard</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
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
        .small-chart-container {
            width: 50%;
            margin: auto;
        }
    </style>
</head>
<body>
    <div class="sidebar">
        <a href="SaleDashboard.jsp">Trang Chủ</a>
        <a href="OrderList">Danh Sách Đơn Hàng</a>
    </div>
    
    <div class="content">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Sale Dashboard</h2>
            <button class="btn btn-danger">Logout</button>
        </div>
        
        <!-- Bộ lọc -->
        <div class="row mb-4">
            <div class="col-md-5">
                <label>Từ ngày:</label>
                <input type="date" id="startDate" class="form-control">
            </div>
            <div class="col-md-5">
                <label>Đến ngày:</label>
                <input type="date" id="endDate" class="form-control">
            </div>
            <div class="col-md-2">
                <label>Người bán:</label>
                <select id="filterSeller" class="form-control">
                    <option value="all">Tất cả</option>
                    <option value="seller1">Người bán 1</option>
                    <option value="seller2">Người bán 2</option>
                </select>
            </div>
        </div>
        
        <!-- Biểu đồ -->
        <div class="row">
            <div class="col-md-6">
                <canvas id="revenueChart"></canvas>
            </div>
            <div class="col-md-6">
                <canvas id="topProductChart"></canvas>
            </div>
        </div>
        
        <div class="row mt-4">
            <div class="col-md-12 d-flex justify-content-center">
                <div class="small-chart-container">
                    <canvas id="successRateChart"></canvas>
                </div>
            </div>
        </div>
    </div>

    <script>
        // Biểu đồ Doanh thu
        var revenueCtx = document.getElementById('revenueChart').getContext('2d');
        var revenueChart = new Chart(revenueCtx, {
            type: 'line',
            data: {
                labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May'],
                datasets: [{
                    label: 'Tổng Doanh Thu',
                    data: [5000, 7000, 8000, 6000, 9000],
                    borderColor: 'blue',
                    fill: false
                }]
            }
        });

        // Biểu đồ Sản phẩm bán chạy
        var topProductCtx = document.getElementById('topProductChart').getContext('2d');
        var topProductChart = new Chart(topProductCtx, {
            type: 'bar',
            data: {
                labels: ['SP1', 'SP2', 'SP3', 'SP4'],
                datasets: [{
                    label: 'Sản phẩm bán chạy',
                    data: [200, 180, 150, 100],
                    backgroundColor: 'green'
                }]
            }
        });

        // Biểu đồ Tỷ lệ giao hàng thành công
        var successRateCtx = document.getElementById('successRateChart').getContext('2d');
        var successRateChart = new Chart(successRateCtx, {
            type: 'pie',
            data: {
                labels: ['Thành công', 'Thất bại'],
                datasets: [{
                    data: [85, 15],
                    backgroundColor: ['green', 'red']
                }]
            }
        });
    </script>
</body>
</html>
