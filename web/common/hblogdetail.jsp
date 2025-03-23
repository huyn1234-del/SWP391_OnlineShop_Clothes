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
                            <div class="blog__details__comment">
                                <h4>Để lại bình luận</h4>
                                <form action="../customerpostcomment" method="post" enctype="multipart/form-data">
                                    <div class="row">
                                        <div class="col-lg-12 text-center">
                                            <textarea maxlength="500" required name="postcomment" placeholder="Comment"></textarea>
                                            <input type="file" name="image" accept="image/*">
                                            <input type="file" name="video" accept="video/*">
                                            <button type="submit" class="site-btn">Bình luận</button>
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <%
                                int cpfpage = 0;
                                if (session.getAttribute("pofpage") != null) {
                                    cpfpage = Integer.parseInt(session.getAttribute("pofpage") + "");
                                }
                            %>

                            <!-- DANH SÁCH BÌNH LUẬN -->
                            <div class="row" style="margin-top: 24px;">
                                <div class="blog__details__option" style="width: 100%;">
                                    <div class="row">
                                        <% 
                                            for (PostFeedback postFeedback : top3postfblist) {
                                                date = sdf.format(postFeedback.getModified_at());
                    
                                                // Lấy ảnh đại diện
                                                String profilePictureUrl = postFeedback.getProfile_picture_url();
                                                String profileImgSrc = (profilePictureUrl != null && (profilePictureUrl.startsWith("http://") || profilePictureUrl.startsWith("https://")))
                                                    ? profilePictureUrl
                                                    : request.getContextPath() + "/" + profilePictureUrl;
                    
                                                // Lấy hình ảnh & video của bình luận
                                                String imageUrl = postFeedback.getImage_url();
                                                String videoUrl = postFeedback.getVideo_url();
                                        %>
                                        <div class="col-md-12 row" style="margin: 5px 0;">
                                            <div class="blog__details__author col-md-12 row">
                                                <div class="blog__details__author__pic col-md-2">
                                                    <img src="<%=profileImgSrc%>" alt="">
                                                </div>
                                                <div class="blog__details__author__text col-md-9">
                                                    <h5><%=postFeedback.getUsername()%></h5>
                                                    <p><%=postFeedback.getReview()%></p>
                                                    <p style="color: #ddd; font-style: italic;"><%=date%></p>

                                                    <% if (imageUrl != null && !imageUrl.isEmpty()) { %>
                                                    <img src="<%= request.getContextPath() + "/" + imageUrl %>" alt="Feedback Image" style="max-width: 100%; margin-top: 10px;">
                                                    <% } %>

                                                    <% if (videoUrl != null && !videoUrl.isEmpty()) { %>
                                                    <video controls style="max-width: 100%; margin-top: 10px;">
                                                        <source src="<%= request.getContextPath() + "/" + videoUrl %>" type="video/mp4">
                                                        Trình duyệt của bạn không hỗ trợ video.
                                                    </video>
                                                    <% } %>
                                                </div>
                                            </div>
                                        </div>
                                        <% } %>
                                    </div>

                                    <!-- PHÂN TRANG -->
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="product__pagination">
                                                <%
                                                    int totalFeedbacks = allpostfblist.size();
                                                    int feedbacksPerPage = 3;
                                                    int npage = (totalFeedbacks % feedbacksPerPage == 0) ? (totalFeedbacks / feedbacksPerPage) : (totalFeedbacks / feedbacksPerPage + 1);

                                                    for (int i = 0; i < npage; i++) {
                                                %>
                                                <a class="<%= (i == cpfpage) ? "active" : "" %>" href="../postfbpagination?cpage=<%=i%>"><%= i + 1 %></a>
                                                <% } %>
                                            </div>
                                        </div>
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
