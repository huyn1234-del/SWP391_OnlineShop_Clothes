
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="model.User"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="preconnect" href="https://fonts.gstatic.com">



        <title>Dashboard</title>

        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&amp;display=swap" rel="stylesheet">
        <link class="js-stylesheet" href="css/light.css" rel="stylesheet">
        <!-- Font awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />


        <style>body {
                opacity: 0;
            }
        </style>
        <!-- END SETTINGS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
              crossorigin="anonymous">

        <jsp:include page="../common/css.jsp" />



    </head>
    <jsp:include page="../common/headermanage.jsp" />
    <body >
        <div class="row">
            <jsp:include page="sale-panel.jsp"/>

            <div class="col-md-10" style="padding: 40px;">

                <div class="wrapper">

                    <div class="main">
                        <main class="content">
                            <div class="container-fluid p-0">
                                <div class="row">

                                    <form action="../saledashboardfilter">
                                        <div class="shop__product__option">
                                            <div class="row">

                                                <%
                                    String begin = session.getAttribute("sadbegin")+"";
                                String end = session.getAttribute("sadend")+"";

                                if(begin.equals("null")) begin = "";
                                if(end.equals("null")) end = "";
        
                                                %>
                                                <div class="col-lg-6 col-md-6 col-sm-6">

                                                    <div class="row" >
                                                        <div class="col-md-6">
                                                            <div class="input-group mb-3">
                                                                <span class="input-group-text" id="inputGroup-sizing-default">Từ</span>
                                                                <input value="<%=begin%>" name="begin" type="date" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-1">
                                                            --
                                                        </div>
                                                        <div class="col-md-5">
                                                            <div class="input-group mb-3">
                                                                <span class="input-group-text" id="inputGroup-sizing-default">Đến</span>
                                                                <input value="<%=end%>" name="end" type="date" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                                                            </div>

                                                        </div>
                                                        

                                                    </div>
                                                             <%
                                                    String loi = session.getAttribute("sdloi")+""; 
                                                    if(loi.equals("null")) loi = "";
                                                    %>
                                                    <div style="color: red;">
                                                        <%=loi%>
                                                    </div>

                                                </div>
                                                    
                                                <div class="col-lg-6 col-md-6 col-sm-6">
                                                    <div class="shop__product__option__right row">
                                                        <div class="col-md-3">
                                                            <button  style="width: 80%" type="submit" class="btn btn-outline-dark">Áp dụng</button>

                                                        </div>
                                                        <div class="col-md-6">
                                                            <!-- Example single danger button -->
<!--                                                            <div class="btn-group" style="min-width: 85px;">
                                                                <%
                                                                int sid = 0;
                                                                String sdsaler = session.getAttribute("sdsaler")+""; 
                                                                if(sdsaler.equals("null")==false) sid = Integer.parseInt(session.getAttribute("sdsaler")+"");
                                                                
                                                                String s = "Tất cả đơn hàng";
                                                                List<User> uList = (List<User>)session.getAttribute("dsalerList");
                                                                for (User user : uList) {
                                                                   if(user.getUser_id()==sid){
                                                                       s = user.getFirst_name()+" "+user.getLast_name();
                                                                   }
                                                                }
                                                                %>
                                                                <button type="button" class="btn btn-primary" data-bs-toggle="dropdown" aria-expanded="false">
                                                                    <%=s%>
                                                                    
                                                                    <i style="margin-left: 5px;" class="fa-solid fa-caret-down"></i>
                                                                </button>
                                                                <ul class="dropdown-menu">
                                                                    <li><a class="dropdown-item" href="../saledashboardfilterbysaler?sid=0">Tất cả đơn hàng</a></li>
                                                                    <c:forEach items="${dsalerList}" var="sale">
                                                                    
                                                                    <li><a class="dropdown-item" href="../saledashboardfilterbysaler?sid=${sale.getUser_id()}">${sale.getFirst_name()} ${sale.getLast_name()}</a></li>
                                                                    </c:forEach>
                                                                    

                                                                </ul>
                                                            </div>-->
                                                        </div>



                                                    </div>

                                                   
                                                </div>

                    
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <h1 class="h3 mb-3">Thống kê đơn hàng</h1>

                                <div class="row">


                                    <div class="col-12 col-lg-6">
                                        <div class="card">
                                            <div class="card-header">
                                                <h2 style="font-size: 24px;" class="card-title">Đơn hàng trong từng ngày</h2>

                                            </div>
                                            <div class="card-body">
                                                <div class="chart">
                                                    <canvas id="chartjs-bar"></canvas>
                                                </div>
                                            </div>
                                        </div>
                                    </div>



                                    <%
                                    int total = Integer.parseInt(session.getAttribute("ctotalOrder")+"");
                                    %>
                                    <div class="col-12 col-lg-6">
                                        <div class="card">
                                            <div class="card-header">
                                                <h2 style="font-size: 24px;" class="card-title">Tổng số <%=total%> đơn đặt hàng </h2>
                                            </div>
                                            <div class="card-body">
                                                <div class="chart">
                                                    <canvas id="chartjs-polar-area"></canvas>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="col-12 col-lg-6">
                                        <div class="card">
                                            <div class="card-header">
                                                <h2 style="font-size: 24px;" class="card-title">Doanh thu trong từng ngày</h2>

                                            </div>
                                            <div class="card-body">
                                                <div class="chart">
                                                    <canvas id="chartjs-bar2"></canvas>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-12 col-lg-6">
                                        <div class="card flex-fill w-100">
                                            <div class="card-header">
                                                <h5 style="font-size: 24px;" class="card-title">Tổng doanh thu</h5>
                                            </div>
                                            <div class="card-body">
                                                <div class="chart">
                                                    <canvas id="chartjs-line"></canvas>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </main>


                    </div>
                </div> 
            </div>
        </div>



        <script src="js/app.js"></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

        <script src="js/scripts.js"></script>
        <!-- Line Chart -->
        <script>

            document.addEventListener("DOMContentLoaded", function () {
            // Line chart
            new Chart(document.getElementById("chartjs-line"), {
            type: "line",
                    data: {
                    labels: [<c:forEach items="${revenueAccumulateByDayList}" var="chart">"${chart.getLabel()}",</c:forEach>],
                            datasets: [{
                            label: "Doanh thu(VND)",
                                    fill: true,
                                    backgroundColor: "transparent",
                                    borderColor: window.theme.primary,
                                    data: [<c:forEach items="${revenueAccumulateByDayList}" var="chart">"${chart.getValue()}",</c:forEach>]
                            }]
                    },
                    options: {
                    maintainAspectRatio: false,
                            legend: {
                            display: false
                            },
                            tooltips: {
                            intersect: false
                            },
                            hover: {
                            intersect: true
                            },
                            plugins: {
                            filler: {
                            propagate: false
                            }
                            },
                            scales: {
                            xAxes: [{
                            reverse: true,
                                    gridLines: {
                                    color: "rgba(0,0,0,0.05)"
                                    },
                                    ticks: {
                                        min:0,
                                        maxTicksLimit:10
                                    }
                            }],
                                    yAxes: [{
                                    ticks: {
                                        min:0,
                                        maxTicksLimit:6,
                                    stepSize: 500
                                    },
                                            display: true,
                                            borderDash: [5, 5],
                                            gridLines: {
                                            color: "rgba(0,0,0,0)",
                                                    fontColor: "#fff"
                                            }
                                    }]
                            }
                    }
            });
            });
        </script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
            // Bar chart
            new Chart(document.getElementById("chartjs-bar"), {
            type: "bar",
                    data: {
                    labels: [<c:forEach items="${orderByDayList}" var="chart">"${chart.getLabel()}",</c:forEach>],
                            datasets: [{
                            label: "Số lượng đơn hàng",
                            backgroundColor: window.theme.primary,
                                    borderColor: window.theme.primary,
                                    hoverBackgroundColor: window.theme.primary,
                                    hoverBorderColor: window.theme.primary,
                                    data: [<c:forEach items="${orderByDayList}" var="chart">"${chart.getValue()}",</c:forEach>],
                                    barPercentage: 1,
                                    categoryPercentage: .5
                            }]
                    },
                    options: {
                    maintainAspectRatio: false,
                            legend: {
                            display: false
                            },
                            scales: {
                            yAxes: [{
                            gridLines: {
                            display: false
                            },
                                    stacked: false,
                                    ticks: {
                                    min: 0,
                                    stepSize: 40,
                                    maxTicksLimit: 6
                                    }
                            }],
                                    xAxes: [{
                                    stacked: false,
                                            gridLines: {
                                            color: "transparent"
                                            },
                                     ticks: {
                                        min:0,
                                        maxTicksLimit:10
                                    }
                                    }]
                            }
                    }
            });
            });
            </script>
            
            
            <script>
            document.addEventListener("DOMContentLoaded", function () {
            // Bar chart
            new Chart(document.getElementById("chartjs-bar2"), {
            type: "bar",
                    data: {
                    labels: [<c:forEach items="${revenueByDayList}" var="chart">"${chart.getLabel()}",</c:forEach>],
                            datasets: [{
                            label: "Doanh thu(VND)",
                            backgroundColor: window.theme.primary,
                                    borderColor: window.theme.primary,
                                    hoverBackgroundColor: window.theme.primary,
                                    hoverBorderColor: window.theme.primary,
                                    data: [<c:forEach items="${revenueByDayList}" var="chart">"${chart.getValue()}",</c:forEach>],
                                    barPercentage: 1,
                                    categoryPercentage: .5
                            }]
                    },
                    options: {
                    maintainAspectRatio: false,
                            legend: {
                            display: false
                            },
                            scales: {
                            yAxes: [{
                            gridLines: {
                            display: false
                            },
                                    stacked: false,
                                    ticks: {
                                    min: 0,
                                    stepSize: 40,
                                    maxTicksLimit: 6
                                    }
                            }],
                                    xAxes: [{
                                    stacked: false,
                                            gridLines: {
                                            color: "transparent"
                                            },
                                            ticks: {
                                        min:0,
                                        maxTicksLimit:10
                                    }
                                    }]
                            }
                    }
            });
            });
            </script>
            
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                // Polar Area chart
                new Chart(document.getElementById("chartjs-polar-area"), {
                type: "polarArea",
                        data: {
                        labels: [<c:forEach items="${sotoChart}" var="chart">"${chart.getLabel()}",</c:forEach>],
                                datasets: [{
                                label: "Model S",
                                        data: [<c:forEach items="${sotoChart}" var="chart">"${chart.getValue()}",</c:forEach>, ],
                                        backgroundColor: [
                                                window.theme.primary,
                                                window.theme.success,
                                                window.theme.warning,
                                                window.theme.danger,
                                                window.theme.info
                                        ]
                                }]
                        },
                        options: {
                        maintainAspectRatio: false
                        }
                });
                });
        </script>
        

    </body>



</html>

