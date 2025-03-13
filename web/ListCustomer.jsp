<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách khách hàng</title>
    <style>
        table {
            width: 80%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        div {
            margin-top: 10px;
        }
    </style>
</head>
<body>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="rowsPerPage" value="5" />
<c:set var="customerList" value="${requestScope.Customer}" />
<c:set var="totalCustomers" value="${fn:length(customerList)}" />
<c:set var="totalPages" value="${(totalCustomers / rowsPerPage) + ((totalCustomers % rowsPerPage > 0) ? 1 : 0)}" />
<c:set var="currentPage" value="${param.page ne null and param.page.matches('[0-9]+') ? param.page : 1}" />
<c:set var="currentPage" value="${currentPage lt 1 ? 1 : currentPage}" />
<c:set var="startIndex" value="${(currentPage - 1) * rowsPerPage}" />
<c:set var="endIndex" value="${startIndex + rowsPerPage}" />

<c:if test="${endIndex > totalCustomers}">
    <c:set var="endIndex" value="${totalCustomers}" />
</c:if>

<center>
    <form action="searchCustomer" method="GET" style="margin-bottom: 10px;">
        <input type="text" name="search" placeholder="Tìm kiếm..." value="${param.search}" />
        <button type="submit">Tìm kiếm</button> 
    </form> 
    
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Họ</th>
                <th>Tên</th>
                <th>Giới tính</th>
                <th>Email</th>
                <th>Số điện thoại</th>
                <th>Trạng thái</th>
                <th>Hành động</th>
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
                            <c:when test="${c.gender == true}">Nam</c:when>
                            <c:otherwise>Nữ</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${c.email}</td>
                    <td>${c.phone}</td>
                    <td>${c.is_active}</td>
                    <td>
                        <a href="detailCustomer?id=${c.user_id}">Chi tiết</a> |
                        <a href="updateCustomer?id=${c.user_id}">Chỉnh sửa</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div>
        <a href="mktdashboard">Quay lại Bảng điều khiển</a> &nbsp;|&nbsp;
        <c:if test="${currentPage > 1}">
            <a href="?page=1">Đầu</a> &nbsp;
            <a href="?page=${currentPage - 1}">Trước</a>&nbsp;
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
            <a href="?page=${currentPage + 1}">Tiếp</a> &nbsp;
            <a href="?page=${totalPages}">Cuối</a>
        </c:if>
        &nbsp;|&nbsp; <a href="#">Thêm mới</a>
    </div>
</center>

</body>
</html>
