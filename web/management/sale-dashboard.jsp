<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <title>Dashboard</title>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&amp;display=swap" rel="stylesheet">
        <link class="js-stylesheet" href="css/light.css" rel="stylesheet">
        <!-- Font awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" 
              integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" 
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style>body { opacity: 0; }</style>
        <!-- END SETTINGS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
              crossorigin="anonymous">
        <jsp:include page="../common/css.jsp" />
    </head>
   <jsp:include page="../common/headermanage.jsp"/>
    <body>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
       
            
          

        
        
        
        <div class="row">
            <jsp:include page="marketing_header.jsp"/>
            
            
   
            
            <div class="row col-md-10" style="padding-top: 30px;padding-left: 30px">
                
<!--                <div class="col-md-6">
               
    <button type="button" class="btn btn-primary btn-lg mx-2">Primary</button>
    <button type="button" class="btn btn-success btn-lg mx-2">Success</button>
    <button type="button" class="btn btn-info btn-lg mx-2">Info</button>
</div>              
                <!-- Biểu đồ đầu tiên -->
                
              
                
                <div class="col-md-12">

                    <form action="../mktdashboardfilter" >     <div class="row" >
                                                      
                                                        <div class="col-md-2">
                                                            <div class="input-group mb-2">
                                                                <span class="input-group-text" id="inputGroup-sizing-default">From</span>
                                                                <input  value="" name="begin" type="date" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-1">
                                                            --
                                                        </div>
                                                        <div class="col-md-2">
                                                            <div class="input-group mb-2">
                                                                <span class="input-group-text" id="inputGroup-sizing-default">To</span>
                                                                <input value="" name="end" type="date" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                                                            </div>
                                                          
                                                            
                                                        </div>
                                                         <div class="col-md-2">
                                                            <button  style="width: 80%" type="submit" class="btn btn-outline-dark">Apply</button>
                                                            <p>${sessionScope.mkt_de} </p>
                                                         </div>
                                                            
                                                    </div>
                  
                    
                    <div class="row">
                        
                        <div class="col-md-3">
                                            <select name="filter">
                                               
                                                <option value="post" selected >Bài đăng</option>
                                              <option value="product"<c:if test="${sessionScope.filter=='product'}"> selected </c:if>>Sản phẩm</option>
                                             <option value="customer"<c:if test="${sessionScope.filter=='customer'}"> selected </c:if>>Khách hàng</option> 
                                           
                                        </select>

                                        </div>
                        
                    </div>
                     </form>
                    
                    
<!--                    <button type="button" class="btn btn-primary btn-lg mx-2 col-md-2" style="width: 126px;height:44px">Bài đăng</button>
    <button type="button" class="btn btn-success btn-lg mx-2 col-md-2" style="width: 126px;height:44px" >Sản phẩm</button>
    <button type="button" class="btn btn-info btn-lg mx-2 col-md-2" style="width: 146px;height:44px">Khách hàng</button>-->
                    </div>
                
                <div class="col-md-6">
                    <canvas id="totalPostchart"></canvas>
                </div>
                
                <!-- Biểu đồ thứ hai -->
                <div class="col-md-6">
                    <canvas id="totalPostchart2"></canvas>
                </div>
                                             <c:if test="${sessionScope.filter!='customer'}">
                <!-- Biểu đồ thứ ba -->
                <div class="col-md-6 mt-5">
                    <canvas id="totalPostchart3"></canvas>
                </div>
                
                <!-- Biểu đồ thứ tư -->
                <div class="col-md-6 mt-5">
                    <canvas id="totalPostchart4"></canvas>
                </div>
                </c:if>
            </div>
        </div>
        
        <!-- Mã biểu đồ -->
        <script>
            const ctx1 = document.getElementById("totalPostchart").getContext('2d');
            const labels1 = [<c:forEach items="${sessionScope.chart1}" var="c"> '${c.label}',    </c:forEach>];
            const data1 = {
                labels: labels1,
                datasets: [{
                    label: '${sessionScope.chart1name} ',
                    data: [<c:forEach items="${sessionScope.chart1}" var="c"> ${c.value},    </c:forEach>],
                    fill: false,
                    borderColor: 'rgb(75, 192, 192)',
                    tension: 0.1
                }]
            };
            const config1 = {
                type: 'line',
                data: data1,
            };
            new Chart(ctx1, config1);
        </script>
        
        <script>
            const ctx2 = document.getElementById("totalPostchart2").getContext('2d');
            const labels2 = [<c:forEach items="${sessionScope.chart2}" var="c"> '${c.label}',    </c:forEach>];
            const data2 = {
                labels: labels2,
                datasets: [{
                    label: '${sessionScope.chart2name}',
                    data: [<c:forEach items="${sessionScope.chart2}" var="c"> ${c.value},    </c:forEach>],
                    fill: false,
                    borderColor: 'rgb(153, 102, 255)',
                    tension: 0.1
                }]
            };
            const config2 = {
                type: 'line',
                data: data2,
                
                 options: {
            scales: {
                y: {
                    ticks: {
                        stepSize: 1 
                    }
                }
            }
        }
            };
            new Chart(ctx2, config2);
        </script>
 
        <script>
            const ctx3 = document.getElementById("totalPostchart3").getContext('2d');
            const labels3 = [<c:forEach items="${sessionScope.chart3}" var="c"> '${c.label}',    </c:forEach>];
            const data3 = {
                labels: labels3,
                datasets: [{
                    label: '${sessionScope.chart3name}',
                    data: [<c:forEach items="${sessionScope.chart3}" var="c"> ${c.value},    </c:forEach>],
                    fill: false,
                    borderColor: 'rgb(255, 99, 132)',
                    tension: 0.1
                }]
            };
            const config3 = {
                type: 'line',
                data: data3,
                     options: {
            scales: {
                y: {
                    ticks: {
                        stepSize: 1 
                    }
                }
            }
        }
            };
            new Chart(ctx3, config3);
        </script>

        <script>
            const ctx4 = document.getElementById("totalPostchart4").getContext('2d');
            const labels4 = [<c:forEach items="${sessionScope.chart4}" var="c"> '${c.label}',    </c:forEach>];
            const data4 = {
                labels: labels4,
                datasets: [{
                    label: '${sessionScope.chart4name}',
                    data: [<c:forEach items="${sessionScope.chart4}" var="c"> ${c.value},    </c:forEach>],
                    fill: false,
                    borderColor: 'rgb(54, 162, 235)',
                    tension: 0.1
                }]
            };
            const config4 = {
                type: 'line',
                data: data4,
                
            };
            new Chart(ctx4, config4);
        </script>
    </body>
</html>
