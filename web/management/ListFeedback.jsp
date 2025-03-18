<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%-- L?y d? li?u t? session --%>
<c:set var="rowsPerPage" value="${sessionScope.rowsPerPage ne null ? sessionScope.rowsPerPage : 2}" />
<c:set var="selectedColumns" value="${sessionScope.selectedColumns ne null ? sessionScope.selectedColumns : '1,2,3,4,5,6,7,8'}" />

<%-- L?y danh sách feedback --%>
<c:set var="feedbackList" value="${requestScope.Feedback}" />
<c:set var="totalFeedback" value="${fn:length(feedbackList)}" />

<%-- Tính t?ng s? trang --%>
<c:set var="totalPages" value="${(totalFeedback / rowsPerPage) + ((totalFeedback % rowsPerPage > 0) ? 1 : 0)}" />

<%-- Xác ??nh trang hi?n t?i --%>
<c:set var="currentPage" value="${param.page ne null and param.page.matches('[0-9]+') ? param.page : 1}" />
<c:set var="currentPage" value="${currentPage lt 1 ? 1 : currentPage}" />

<%-- Xác ??nh v? trí b?t ??u và k?t thúc trên trang hi?n t?i --%>
<c:set var="startIndex" value="${(currentPage - 1) * rowsPerPage}" />
<c:set var="endIndex" value="${startIndex + rowsPerPage > totalFeedback ? totalFeedback : startIndex + rowsPerPage}" />

<style>
    .checkbox-group {
        display: flex;
        flex-wrap: wrap;
        gap: 10px;
    }
    .checkbox-group label {
        display: flex;
        align-items: center;
        padding: 8px;
        border: 2px solid #007bff;
        background: #e7f1ff;
        font-weight: bold;
        cursor: pointer;
        transition: 0.3s;
    }
    .checkbox-group label:hover {
        background: #cce5ff;
    }
</style>

<%-- Form tìm ki?m --%>
<form action="searchFeedback" method="GET">
    <a href="mktdashboard">BACK TO DASHBOARD</a> &nbsp;&nbsp;
    <input type="text" name="search" placeholder="Search..." value="${param.Feedback}" />
    <button type="submit">Search</button>

</form>

<%-- Form tùy ch?nh s? hàng và c?t hi?n th? --%>
<form action="feedback" method="POST">
    <label>Chon so hang trong 1 trang:</label>
    <input type="number" name="rows" min="1" value="${rowsPerPage}" /><br/><br/>

    <label>Chon cac cot muon hien thi:</label><br/>
    <div class="checkbox-group">
        <label><input type="checkbox" name="columns" value="1" ${fn:contains(selectedColumns, '1') ? 'checked' : ''} /> Feedback ID</label>
        <label><input type="checkbox" name="columns" value="2" ${fn:contains(selectedColumns, '2') ? 'checked' : ''} /> Customer ID</label>
        <label><input type="checkbox" name="columns" value="3" ${fn:contains(selectedColumns, '3') ? 'checked' : ''} /> Product ID</label>
        <label><input type="checkbox" name="columns" value="4" ${fn:contains(selectedColumns, '4') ? 'checked' : ''} /> Customer Name</label>
        <label><input type="checkbox" name="columns" value="5" ${fn:contains(selectedColumns, '5') ? 'checked' : ''} /> Product Name</label>
        <label><input type="checkbox" name="columns" value="6" ${fn:contains(selectedColumns, '6') ? 'checked' : ''} /> Review</label>
        <label><input type="checkbox" name="columns" value="7" ${fn:contains(selectedColumns, '7') ? 'checked' : ''} /> Rating</label>
        <label><input type="checkbox" name="columns" value="8" ${fn:contains(selectedColumns, '8') ? 'checked' : ''} /> Status</label>
    </div><br/>
    <button type="submit">SAVE</button>
</form> 


<%-- B?ng d? li?u Feedback --%>
<table border="1">
    <thead>
        <tr>
            <c:forEach var="col" items="${fn:split(selectedColumns, ',')}">
                <th>
                    <c:choose>
                        <c:when test="${col == '1'}">Feedback ID</c:when>
                        <c:when test="${col == '2'}">Customer ID</c:when>
                        <c:when test="${col == '3'}">Product ID</c:when>
                        <c:when test="${col == '4'}">
                            <a href="?orderBy=customer_name&orderDir=${orderBy == 'customer_name' and orderDir == 'ASC' ? 'DESC' : 'ASC'}">Customer Name</a>
                        </c:when>
                        <c:when test="${col == '5'}">
                            <a href="?orderBy=product_name&orderDir=${orderBy == 'product_name' and orderDir == 'ASC' ? 'DESC' : 'ASC'}">Product Name</a>
                        </c:when>
                        <c:when test="${col == '6'}">Review</c:when>
                        <c:when test="${col == '7'}">
                            <a href="?orderBy=rating&orderDir=${orderBy == 'rating' and orderDir == 'ASC' ? 'DESC' : 'ASC'}">Rate Star</a>
                        </c:when>
                        <c:when test="${col == '8'}">
                            <a href="?orderBy=is_active&orderDir=${orderBy == 'is_active' and orderDir == 'ASC' ? 'DESC' : 'ASC'}">Status</a>
                        </c:when>
                    </c:choose>
                </th>
            </c:forEach>
        </tr>
    </thead>
    <tbody>
        <c:if test="${totalFeedback > 0 and startIndex < totalFeedback}">
            <c:forEach var="i" begin="${startIndex}" end="${endIndex - 1}">
                <c:set var="f" value="${feedbackList[i]}" />
                <tr>
                    <c:forEach var="col" items="${fn:split(selectedColumns, ',')}">
                        <td>${col == '1' ? f.feedback_id : col == '2' ? f.customer_id : col == '3' ? f.product_id : col == '4' ? f.customer_name : col == '5' ? f.product_name : col == '6' ? f.review : col == '7' ? f.rating : f.is_active}</td>
                    </c:forEach>
                    <td>
                        <a href="detailFeedback?id=${f.feedback_id}">Detail</a> |
                        <a href="updateFeedback?id=${f.feedback_id}">Edit</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>

    </tbody>
</table>

<%-- Phân trang --%>
<div>
    <c:if test="${currentPage > 1}">
        <a href="?page=1">First</a>
        <a href="?page=${currentPage - 1}">Previous</a>
    </c:if>
    <c:forEach var="i" begin="1" end="${totalPages}">
        <a href="?page=${i}" ${i == currentPage ? 'style="font-weight:bold;"' : ''}>${i}</a>
    </c:forEach>
    <c:if test="${currentPage < totalPages}">
        <a href="?page=${currentPage + 1}">Next</a>
        <a href="?page=${totalPages}">Last</a>
    </c:if>
</div>
