<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%-- S? dòng t?i ?a m?i trang --%>
<c:set var="rowsPerPage" value="5" />

<%-- L?y danh sách khách hàng t? request --%>
<c:set var="customerList" value="${requestScope.Customer}" />

<%-- T?ng s? khách hàng --%>
<c:set var="totalCustomers" value="${fn:length(customerList)}" />

<%-- Tính t?ng s? trang --%>
<c:set var="totalPages" value="${(totalCustomers / rowsPerPage) + ((totalCustomers % rowsPerPage > 0) ? 1 : 0)}" />

<%-- ??m b?o currentPage luôn có giá tr? s? h?p l? --%>
<c:set var="currentPage" value="${param.page ne null and param.page.matches('[0-9]+') ? param.page : 1}" />
<c:set var="currentPage" value="${currentPage lt 1 ? 1 : currentPage}" />

<%-- Xác ??nh v? trí b?t ??u và k?t thúc c?a d? li?u trong trang hi?n t?i --%>
<c:set var="startIndex" value="${(currentPage - 1) * rowsPerPage}" />
<c:set var="endIndex" value="${startIndex + rowsPerPage}" />

<%-- Ki?m tra endIndex không v??t quá t?ng s? khách hàng --%>
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
       &nbsp;<a>|</a> &nbsp;  <a href="#">add</a>
</div>
</center>