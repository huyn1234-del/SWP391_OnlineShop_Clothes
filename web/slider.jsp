<%-- 
    Document   : slider
    Created on : Feb 4, 2025, 6:43:17 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            function Delete(id) {
                if (confirm("Bạn có chắc chắn muốn xóa ID = " + id)) {
                    window.location = "delete?id=" + id;
                }
            }
        </script>
    </head>

    <body>

    <center>
        <!-- Form tìm kiếm -->
        <form action="searchSlider" method="GET" style="margin-bottom: 10px;">
            <input type="text" name="search" placeholder="Search title..." value="${param.slider}" />
            <button type="submit">Search</button> 
        </form> 
        <table border="1px" width="60%">
            <h3><a href="addSlider.jsp">add</a> &nbsp;<a>|</a> &nbsp;<a href="mktdashboard">Back to Dashboard</a></h3>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tittle</th>
                    <th>Image</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.slider}" var="s">
                    <c:set var="id" value="${s.id}"/>
                    <tr>
                        <td>${id}</td>
                        <td>${s.tittle}</td>
                        <td>
                            <img src="${s.image_url}" alt="Slider Image" width="100" height="50"/>
                        </td>
                        <td>${s.is_active}</td>
                        <td><a href="detailSlider?id=${id}">detail</a></td>
                        <td><a href="hideSlider?id=${id}">Hide</a></td>
                        <td><a href="showSlider?id=${id}">Show</a></td>
                        <td><a href="#" onclick="Delete('${id}')">Delete</a></td>
                        <td><a href="updateSlider?id=${id}">Edit</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </center>
</body>
</html>
