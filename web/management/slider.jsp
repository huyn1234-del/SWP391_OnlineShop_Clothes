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
                    window.location = "${pageContext.request.contextPath}/deleteSlider?id=" + id;

                }
            }
        </script>

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

            <center>
                <!-- Form tìm kiếm -->
                <form action="searchSlider" method="GET" style="margin-bottom: 10px;">
                    <input type="text" name="search" placeholder="Search title..." value="${param.slider}" />
                    <button type="submit">Search</button> 
                </form> 
                <table border="1px" width="60%">

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
                                    <img src="${pageContext.request.contextPath}/${s.image_url}" alt="Slider Image" width="100" height="50"/>
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
                <h3><a href="addSlider.jsp">add</a></h3>
            </center>
        </div>
    </body>
</html>


