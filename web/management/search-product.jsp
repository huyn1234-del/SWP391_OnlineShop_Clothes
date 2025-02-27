<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Product"%>
<%@page import="java.util.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Marketing Page</title>
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
                width: 40%;
            }

            .product-img img{
                width: 36%;
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
            <jsp:include page="../common/header.jsp" />
            
            <!-- START HEADER -->

            <jsp:include page="marketing_header.jsp" />

            <!-- END menu -->

            <div class="col-md-10" style="padding: 40px;">



                <!-- START products -->
                <div class="product">
                    <div class="container products" >
                        <div>
                            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                                <div class="container-fluid">
                                    <h5 class="navbar-brand" href="#">Manage Products</h5>

                                    <div class="" id="navbarSupportedContent">
                                        <form class="d-flex" role="search" action="../searchproduct" method="get">
                                            <input placeholder="Search products" name="search" class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                                            <button class="btn btn-outline-success" type="submit">Search</button>
                                        </form>
                                    </div>


                                    <div class="">
                                        <div class="d-flex add" role="search">
                                            <a href="add-product.jsp"><i style="color: white;" class="fa-solid fa-plus"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </nav>
                        </div>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Id</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Image</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Discount</th>
                                    <th scope="col">Rating</th>
                                    <th scope="col">Action</th>
                                </tr>
                            </thead>
                            <tbody>


                                <!-- START Product item -->
                                <%  List<Product> search = (ArrayList<Product>) session.getAttribute("search_product");
                                    if (search != null) {
                                        for (Product p : search) {
                                %>
                                <tr>

                                    <td><%= p.getProduct_id()%></td>

                                    <td><%= p.getProduct_name()%></td>

                                    <td class="product-img">
                                        <img src="../<%= p.getThumbnail()%>">
                                    </td>

                                    <td>
                                        <%
                                            if(p.isIs_active()==true) {   
                                        %>
                                        <p style="color: green">Active</p>
                                        <%
                                            } else {
                                        %>
                                        <p style="color: Red">Inactive</p> 
                                        <% 
                                            }
                                        %>   
                                    </td>

                                    <td><%= p.getPrice()%></td>
                                    
                                    <td><%= p.getDiscount()%></td>

                                    <td><%= p.getRated_star()%></td>

                                    <td>
                                        <%
                                            if(p.isIs_active()==true) {
                                        %>

                                        <div class="edit" style="background-color: red">
                                            <a href="../editproduct?pid=<%= p.getProduct_id()%>&button=hide" onclick="return confirm('Hide this product?')">
                                                <i style="color: black;" class="bi bi-eye-slash-fill"></i>
                                            </a>
                                        </div>

                                        <%
                                            } else {
                                        %>

                                        <div class="edit" style="background-color: greenyellow">
                                            <a href="../editproduct?pid=<%= p.getProduct_id()%>&button=show" onclick="return confirm('Show this product?')">
                                                <i style="color: black;" class="bi bi-eye-fill"></i>
                                            </a>
                                        </div>

                                        <% 
                                            }
                                        %> 

                                        <div class="edit">
                                            <a href="../editproduct?pid=<%= p.getProduct_id()%>&button=edit"><i style="color: black;" class="fa-solid fa-pen"></i></a>
                                        </div>
                                    </td>
                                    <%
                                            }
                                        }
                                    %>

                                </tr>
                                <!-- END Product item -->

                            </tbody>
                        </table>

                        <!-- START PAGE -->

                        <div style="display: flex;
                             justify-content: center;">

                            <nav aria-label="Page navigation example">
                                <ul class="pagination">

                                    
                                </ul>
                            </nav>

                        </div>
                        <!-- END PAGE -->

                    </div>
                </div>
                <!-- END products -->


            </div>
        </div>


    </body>
</html>
