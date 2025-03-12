<%-- 
    Document   : hblogdetail
    Created on : Sep 25, 2024, 10:44:12 AM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@page import="model.Post"%>
<%@page import="model.PostCategory"%>
<%@page import="model.User"%>
<%@page import="model.PostFeedback"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html lang="zxx">

    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Male_Fashion Template">
        <meta name="keywords" content="Male_Fashion, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Tee & Jean</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
              rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css/magnific-popup.css" type="text/css">
        <link rel="stylesheet" href="css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">
    </head>

    <body>
        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>
        <jsp:include page="../common/header.jsp" />
        <!-- Header Section End -->

        <%
        Post ppostdetail = (Post)session.getAttribute("ppostdetail");
        Post afterPost = (Post)session.getAttribute("afterPost");
        Post beforePost = (Post)session.getAttribute("beforePost");
            PostCategory ppostcategory = (PostCategory)session.getAttribute("ppostcategory");
            User ppostauthor = (User)session.getAttribute("ppostauthor");
            
        List<PostFeedback> top3postfblist = (List<PostFeedback>)session.getAttribute("top3postfblist");
        List<PostFeedback> allpostfblist = (List<PostFeedback>)session.getAttribute("allpostfblist");
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(ppostdetail.getModified_at());
        %>
        <!-- Blog Details Hero Begin -->
        <section class="blog-hero spad">
            <div class="container">
                <div class="row d-flex justify-content-center">
                    <div class="col-lg-9 text-center">
                        <div class="blog__hero__text">
                            <h2><%=ppostdetail.getTitle()%></h2>
                            <ul>
                                <li>By <%=ppostauthor.getFirst_name()%> <%=ppostauthor.getLast_name()%></li>
                                <li><%=date%></li>
                                <li>Chủ đề: <%=ppostcategory.getPost_category_name()%></li>

                                
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Blog Details Hero End -->

        <!-- Blog Details Section Begin -->
        <section class="blog-details spad">
            <div class="container">
                <div class="row d-flex justify-content-center">
                    <div class="col-lg-12">
                        <div class="blog__details__pic">
                            <img src="../<%=ppostdetail.getThumbnail()%>" alt="">
                        </div>
                    </div>
                    <div class="col-lg-8">
                        <div class="blog__details__content">
                          
                            <div class="blog__details__text">
                                <p><%=ppostdetail.getContent()%></p>

                            </div>

                            <div class="blog__details__option">
                                <div class="row">
                                    <div class="col-lg-6 col-md-6 col-sm-6">
                                        <div class="blog__details__author">
                                            <div class="blog__details__author__pic">
                                                <%
                                                if(ppostauthor.getProfile_picture_url()!=null){
                                                %>
                                                <img src="../<%=ppostauthor.getProfile_picture_url()%>" alt="">
                                                <%
                                                    } else {
                                                %>
                                                <img src="img/blog/details/blog-author.jpg" alt="">
                                                <%
                                                    }
                                                %>
                                            </div>
                                            <div class="blog__details__author__text">
                                                <h5><%=ppostauthor.getFirst_name()%> <%=ppostauthor.getLast_name()%></h5>
                                            </div>
                                        </div>
                                    </div>

                                    <%
                                    List<PostCategory> postcategorylist = (List<PostCategory>)session.getAttribute("postcategorylist");
                                    %>
                                    <div class="col-lg-6 col-md-6 col-sm-6">
                                        <div class="blog__details__tags">
                                            <%
                                            for (PostCategory postCategory : postcategorylist) {
            
        
                                            %>
                                            <a style="color: <%=ppostcategory.getPost_category_id()==postCategory.getPost_category_id()?"black":
                                                "#b7b7b7;"%>" 
                                               href="../filterpostbycategory?cid=<%=postCategory.getPost_category_id()%>">
                                                #<%=postCategory.getPost_category_name()%></a>
                                                <%
                                                    }
                                                %>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="blog__details__btns">
                                <div class="row">                                  
                                    <div class="col-lg-6 col-md-6 col-sm-6">
                                        <%
                                        if(beforePost!=null) {
                                        %>
                                        <a href="../hpostdetail?bid=<%=beforePost.getPost_id()%>" class="blog__details__btns__item">
                                            <p><span class="arrow_left"></span> Bài viết trước</p>
                                            <h5><%=beforePost.getTitle()%></h5>
                                        </a>
                                        <%
                                            }
                                        %>

                                    </div>
                                    <div class="col-lg-6 col-md-6 col-sm-6">
                                        <%
                                        if(afterPost!=null){
                                        %>
                                        <a href="../hpostdetail?bid=<%=afterPost.getPost_id()%>" class="blog__details__btns__item blog__details__btns__item--next">
                                            <p>Bài viết tiếp theo <span class="arrow_right"></span></p>
                                            <h5><%=afterPost.getTitle()%></h5>
                                        </a>
                                        <%
                                            }
                                        %>

                                    </div>
                                </div>
                            </div>
                            
                                </div>
                            </div>
                        </div>
                    </div>  

                                                 
        </section>
        <!-- Blog Details Section End -->



        <!-- Footer Section Begin -->
        <jsp:include page="../common/footer.jsp" />
        <!-- Footer Section End -->

        <!-- Search Begin -->
        <div class="search-model">
            <div class="h-100 d-flex align-items-center justify-content-center">
                <div class="search-close-switch">+</div>
                <form class="search-model-form">
                    <input type="text" id="search-input" placeholder="Search here.....">
                </form>
            </div>
        </div>
        <!-- Search End -->

        <!-- Js Plugins -->
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.nice-select.min.js"></script>
        <script src="js/jquery.nicescroll.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/jquery.countdown.min.js"></script>
        <script src="js/jquery.slicknav.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>
    </body>

</html>
