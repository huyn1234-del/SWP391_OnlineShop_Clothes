<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Order"%>
<%@page import="java.util.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý đơn hàng</title>
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
        </style>
    </head>
    <body>
        <div class="row">
            <jsp:include page="../common/headermanage.jsp" />
            <!-- START menu -->
            <jsp:include page="sale-panel.jsp"/>
            <!-- END menu -->

            <%
                String begin = session.getAttribute("begin_date_o")+"";
                String end = session.getAttribute("end_date_o")+"";
                if(begin.equals("null")) begin = "";
                if(end.equals("null")) end = "";
            %>

            <div class="col-md-10" style="padding: 40px;">

                <div class="product">
                    <div class="container products" >
                        <div>
                            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                                <div class="container-fluid">
                                    <h5 class="navbar-brand" href="#">Quản lý đơn hàng</h5>
                                    <div class="" id="navbarSupportedContent">
                                        <form action="../orderlist" class="d-flex" role="search">
                                            <h5 style="font-weight: bold;" class="navbar-brand" href="#">Từ:</h5>
                                            <input value="<%=begin%>" name="begindate" class="form-control me-2" type="date" aria-label="Search">
                                            <h5 style="font-weight: bold;" class="navbar-brand" href="#">Đến:</h5>
                                            <input value="<%=end%>" name="enddate" class="form-control me-2" type="date" aria-label="Search">
                                            <button class="btn btn-outline-success" type="submit">Search</button>
                                        </form>
                                        <c:if test="${not empty error_dmy}">
                                            ${error_dmy}
                                        </c:if>
                                    </div> 
                                </div>
                            </nav>
                        </div>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">STT</th>
                                    <th scope="col">Ngày đặt hàng</th>                                   
                                    <th scope="col">Tổng giá tiền(₫)</th>
                                    <th scope="col">Trạng thái đơn hàng</th>
                                    <th scope="col">Xem</th>
                                </tr>
                            </thead>
                            <tbody>


                                <!-- START Order item -->
                                <c:forEach items="${sessionScope.order_list}" var="o" varStatus="status">
                                    <tr>
                                        <td>${status.index +1}</td>
                                        
                                        <fmt:parseDate value="${o.orderedDate}" pattern="yyyy-MM-dd" var="parsedDate" />
                                        <td><fmt:formatDate value="${parsedDate}" pattern="dd/MM/yyyy" /></td>

                                        <fmt:setLocale value="vi_VN" />
                                        <td><fmt:formatNumber value="${o.totalAmount}"/></td>


                                        <td
                                            <c:if test="${o.orderStatusId == 1}">class="text-warning  font-weight-bold"</c:if>
                                            <c:if test="${o.orderStatusId == 2}">class="text-primary  font-weight-bold"</c:if>
                                            <c:if test="${o.orderStatusId == 3}">class="text-info  font-weight-bold"</c:if>
                                            <c:if test="${o.orderStatusId == 4}">class="text-success  font-weight-bold"</c:if>
                                            <c:if test="${o.orderStatusId == 5}">class="text-danger font-weight-bold"</c:if>
                                            <c:if test="${o.orderStatusId == 6}">class="text-success font-weight-bold"</c:if>
                                            >${o.orderStatusName}</td>
                                        <td>
                                            <form action="${pageContext.request.contextPath}/orderdetail" method="get">
                                                <input type="hidden" name="orderId" value="${o.orderId}">
                                                <button class="btn btn-sm btn-dark"><span class="fa fa-eye"></span></button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <!-- END Order item -->

                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

