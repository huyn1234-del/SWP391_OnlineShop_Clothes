<%-- 
    Document   : listpostmarketing
    Created on : Sep 27, 2024, 9:37:00 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Post"%>
<%@page import="model.PostCategory"%>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
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

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" />
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
                background-color: #a0a1e0;
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
                width: 30%;
            }

            .product-img img{
                width: 80%;
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
            .post-title{
                width: 16%;

            }
        </style>

    </head>
    <body>
         <jsp:include page="../common/headermanage.jsp"/>
        <div class="row">
            <!-- START menu -->
            <jsp:include page="marketing_header.jsp"/>
            <!-- END menu -->


            <div class="col-md-10" style="padding: 40px;">
                <div class="container">
                    <main>
                        <div class="py-5 text-center" >
                            <img class="d-block mx-auto mb-4" src="img/user.png" alt="" width="72">
                            <h2>View Post Info</h2>
                        </div>

                        <div class="row g-5" style="justify-content: center;">

                            <%
                            Post editpostmkt = (Post)session.getAttribute("editpostmkt");
                            %>


                            <div class="col-md-8">
                                <form class="needs-validation" action="../listpostmarketing" method="post" enctype="multipart/form-data">
                                    <div class="row g-3">


                                        <div class="col-sm-12">
                                            <label for="posttitle" class="form-label">Post title</label>
                                            <input disabled="" value="<%=editpostmkt.getTitle()%>" name="posttitle" type="text" class="form-control" id="posttitle" required>

                                        </div>
                                            <div class="col-sm-12">
                                            <label for="image" class="form-label">Post Image</label>
                                            
                                            <img style="margin-top: 10px;" src="../<%=editpostmkt.getThumbnail()%>" alt="alt"/>
                                        </div>
                                        <div class="col-sm-12">
                                            <label for="postcontent" class="form-label">Post content</label>
                                            <textarea disabled="" style="min-height: 200px;" id="postcontent" required="" name="postcontent" class="form-control"><%=editpostmkt.getContent().trim()%></textarea>

                                        </div>

                                        <%
                                        List<PostCategory> postcategorylist = (List<PostCategory>)session.getAttribute("listpostcategorymkt");
                                        %>

                                        <div class="col-12">
                                            <label for="stationid" class="form-label">Post Category</label>
                                            <select name="postcategory" class="form-control" required="">
                                                <%
                                          for (PostCategory postCategory : postcategorylist) {
                                                if(postCategory.getPost_category_id()==editpostmkt.getPost_category_id()){
                                          %>
                                           <option value="<%=postCategory.getPost_category_id()%>"><%=postCategory.getPost_category_name()%></option>                                   
                                          <%
                                              }
                                            }
                                          %>
                                               


                                            </select>
                                        </div>
                                          
                                        <div class="my-3 col-sm-6">
                                            <label for="available" class="form-label">Status</label>
                                            <div class="form-check">
                                                <input <%=editpostmkt.getIs_active()==1?"checked":"disabled"%> value="1" id="available" name="is_active" type="radio" class="form-check-input">
                                                <label class="form-check-label" for="available">Show</label>
                                            </div>
                                            <div class="form-check">
                                                <input <%=editpostmkt.getIs_active()==0?"checked":"disabled"%> value="0" id="available1" name="is_active" type="radio" class="form-check-input">
                                                <label class="form-check-label" for="available1">Hide</label>
                                            </div>
                                        </div>


                                    </div>

                                    <hr class="my-4">
                                    <!-- Error check Username and Password -->
                                    <%
                                        String e = session.getAttribute("loiprofile")+"";
                                        if(e.equals("null")) e = "";                   
                                    %>
                                    <div style="text-align: left;
                                         color: red;
                                         margin-left: 5px;">
                                        <%=e%>
                                    </div>

                                    <button class="w-100 btn btn-primary btn-lg" type="submit">Back</button>
                                </form>
                            </div>
                        </div>
                    </main>

                    <footer class="my-5 pt-5 text-body-secondary text-center text-small">

                    </footer>
                </div>


            </div>
        </div>


    </body>
</html>
