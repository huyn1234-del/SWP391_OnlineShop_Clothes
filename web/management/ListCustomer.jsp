<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="vi">
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
        </style>
</head>
<body>
<div class="row">
            <jsp:include page="../common/headermanage.jsp" />
            <!-- START menu -->
            <jsp:include page="marketing_header.jsp"/>
            <!-- END menu -->

<%-- Số dòng tối đa mỗi trang --%>
<c:set var="rowsPerPage" value="5" />

<%-- Lấy danh sách khách hàng từ request --%>
<c:set var="customerList" value="${requestScope.Customer}" />

<%-- Tổng số khách hàng --%>
<c:set var="totalCustomers" value="${fn:length(customerList)}" />

<%-- Tính tổng số trang --%>
<c:set var="totalPages" value="${(totalCustomers / rowsPerPage) + ((totalCustomers % rowsPerPage > 0) ? 1 : 0)}" />

<%-- Đảm bảo currentPage luôn có giá trị số hợp lệ --%>
<c:set var="currentPage" value="${param.page ne null and param.page.matches('[0-9]+') ? param.page : 1}" />
<c:set var="currentPage" value="${currentPage lt 1 ? 1 : currentPage}" />

<%-- Xác định vị trí bắt đầu và kết thúc của dữ liệu trong trang hiện tại --%>
<c:set var="startIndex" value="${(currentPage - 1) * rowsPerPage}" />
<c:set var="endIndex" value="${startIndex + rowsPerPage}" />

<%-- Kiểm tra endIndex không vượt quá tổng số khách hàng --%>
<c:if test="${endIndex > totalCustomers}">
    <c:set var="endIndex" value="${totalCustomers}" />
</c:if>

<%-- Hi?n th? b?ng danh sách khách hàng --%>
<center>
    <form action="searchCustomer" method="GET" style="margin-bottom: 10px;">
            <input type="text" name="search" placeholder="Search..." value="${param.search}" />
            <button type="submit">Search</button> 
        </form> 
<table border="1">
    
    <thead>
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Gender</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Status</th>
            
        </tr>
    </thead>
    <tbody>
        <c:forEach var="i" begin="${startIndex}" end="${endIndex - 1}">
            <c:set var="c" value="${customerList[i]}" />
            <tr>
                <td>${c.user_id}</td>
                <td>${c.first_name}</td>
                <td>${c.last_name}</td>
                <td>
                    <c:choose>
                        <c:when test="${c.gender == true}">Male</c:when>
                        <c:otherwise>Female</c:otherwise>
                    </c:choose>
                </td>
                <td>${c.email}</td>
                <td>${c.phone}</td>
                <td>${c.is_active}</td>
                <td>
                    <a href="detailCustomer?id=${c.user_id}">Detail</a> |
                    <a href="updateCustomer?id=${c.user_id}">Edit</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<%--phân trang --%>
<div>
    <a href="mktdashboard">Back to Dashboard</a> &nbsp;<a>|</a> &nbsp; 
    <c:if test="${currentPage > 1}">
        <a href="?page=1">First</a> &nbsp;
        <a href="?page=${currentPage - 1}">Previous</a>&nbsp;
    </c:if>

    <c:forEach var="i" begin="1" end="${totalPages}">
        <c:choose>
            <c:when test="${i == currentPage}">
                <strong>${i}</strong>
            </c:when>
            <c:otherwise>
                <a href="?page=${i}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${currentPage < totalPages}">
        <a href="?page=${currentPage + 1}">Next</a> &nbsp;
        <a href="?page=${totalPages}">Last</a>
    </c:if>
<!--       &nbsp;<a>|</a> &nbsp;  <a href="r">add</a>-->
</div>
</center>
</div>
</body>
</html>