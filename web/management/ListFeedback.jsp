
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
            <jsp:include page="../common/headermanage.jsp"/>
            <!-- START menu -->
            <jsp:include page="marketing_header.jsp"/>
            <!-- END menu -->

            <div class="content-wrapper">
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

                    .content-wrapper {
                        margin-left: 450px; /* Cách lề trái 250px */
                        margin-right: auto; /* Bên phải tự co lại */
                        max-width: calc(100% - 450px); /* Đảm bảo không vượt quá trang */
                        overflow-x: auto; /* Nếu nhỏ quá thì bảng vẫn cuộn được */
                    }

                    .table-responsive-fix table {
                        width: 100%;
                        min-width: 800px;
                        border-collapse: collapse;
                    }

                    .table-responsive-fix th, .table-responsive-fix td {
                        white-space: nowrap;
                        padding: 8px;
                    }

                    .table-responsive-fix td.product-name,
                    .table-responsive-fix td.review {
                        white-space: normal;
                        word-break: break-word;
                        max-width: 200px; /* Giới hạn chiều rộng xuống dòng */
                    }

                </style>

                <%-- Form tìm ki?m --%>
                <form action="searchFeedback" method="GET">                   
                    <input type="text" name="search" placeholder="Search..." value="${param.Feedback}" />
                    <button type="submit">Search</button>
                </form><br/>

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
                </form> <br/>


                <%-- B?ng d? li?u Feedback --%>
                <div class="table-responsive-fix">
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
                                            <c:choose>
                                                <c:when test="${col == '5'}">
                                                    <td class="product-name">${f.product_name}</td>
                                                </c:when>
                                                <c:when test="${col == '6'}">
                                                    <td class="review">${f.review}</td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td>${col == '1' ? f.feedback_id : col == '2' ? f.customer_id : col == '3' ? f.product_id : col == '4' ? f.customer_name : col == '7' ? f.rating : f.is_active}</td>
                                                </c:otherwise>
                                            </c:choose>

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
                </div>
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
            </div>
        </div>
    </body>
</html>
