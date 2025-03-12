<%-- 
    Document   : post
    Created on : Sep 23, 2024, 10:49:55 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Post"%>
<%@page import="model.PostCategory"%>
<%@page import="java.util.*" %>
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
        List<Post> top6post = (List<Post>)session.getAttribute("top6post");
            List<Post> allpostlist = (List<Post>)session.getAttribute("allpostlist");
             
        
        %>




        <!-- Blog Section Begin -->
        <section class="blog spad">

            <%
            String begin = session.getAttribute("pobegin")+"";
        String end = session.getAttribute("poend")+"";
        String author = session.getAttribute("author")+"";
        String title = session.getAttribute("title")+"";
        if(begin.equals("null")) begin = "";
        if(end.equals("null")) end = "";
        if(author.equals("null")) author = "";
        if(title.equals("null")) title = "";
        
            %>
            <div class="container">
                <form action="../hpostfilter">
                    <div class="shop__product__option">
                    <div class="row">
                        
                             <div class="col-lg-6 col-md-6 col-sm-6">
                            
                                <div class="row" style="margin-top: 14px;">
                                    <div class="col-md-6">
                                        <div class="input-group mb-3">
                                            <span class="input-group-text" id="inputGroup-sizing-default">Từ</span>
                                            <input value="<%=begin%>" name="begin" type="date" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                                        </div>
                                        <div class="input-group mb-3">
                                            <span class="input-group-text" id="inputGroup-sizing-default">Tên tác giả</span>
                                            <input maxlength="500" value="<%=author%>" name="author" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                                        </div>
                                    </div>
                                    <div class="col-md-1">
                                        --
                                    </div>
                                    <div class="col-md-5">
                                        <div class="input-group mb-3">
                                            <span class="input-group-text" id="inputGroup-sizing-default">Đến</span>
                                            <input value="<%=end%>" name="end" type="date" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                                        </div>
                                        <div class="input-group mb-3">
                                            <span class="input-group-text" id="inputGroup-sizing-default">Tiêu đề</span>
                                            <input maxlength="500" value="<%=title%>" name="title" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                                        </div>
                                    </div>
                                    <div class="col-md-3">

                                    </div>
                                </div>
                               
                        </div>
                        <div class="col-lg-6 col-md-6 col-sm-6">
                            <div class="shop__product__option__right" style="margin-top: 15px; padding: 10px 0;">
                                <%
                                 List<PostCategory> postcategorylist = (List<PostCategory>)session.getAttribute("postcategorylist");
                                
                                String sortValue = session.getAttribute("sortPostValue")+"";
                                if(sortValue.equals("null")) sortValue = "";
                                %>
                                
                                    <p>Sắp xếp theo:</p>
                                    <select name="sortValue">
                                        <option <%=sortValue.equals("new")?"selected":""%> value="new">Mới nhất</option>
                                        <option <%=sortValue.equals("old")?"selected":""%> value="old">Cũ nhất</option>
                                        <%
                                            for (PostCategory postCategory : postcategorylist) {

                                
                                        %>
                                        <option <%=sortValue.equals(postCategory.getPost_category_id()+"")?"selected":""%> value="<%=postCategory.getPost_category_id()%>">Tag: <%=postCategory.getPost_category_name()%></option>
                                        <%
                                            }
                                        %>

                                    </select>

                                

                            </div>
                            <button  style="width: 100%;
                                     margin-top: 8px;" type="submit" class="btn btn-outline-dark">Áp dụng</button>
                        <%
                        String loi = session.getAttribute("ploi")+"";
                        String postsql = session.getAttribute("postsql")+"";
                        if(loi.equals("null")) loi = "";
                        %>
                        <div style="color: red;">
                            <%=loi%>
                        </div>
                        </div>
                       
                       <!--<%=postsql%>-->
                    </div>
                </div>
                </form>
                
                <div class="row">
                    <%
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    
                    for (Post post : top6post) {
                    String date = sdf.format(post.getModified_at());
                    %>
                    <div class="col-lg-4 col-md-6 col-sm-6">
                        <div class="blog__item">
                            <div class="blog__item__pic set-bg" data-setbg="../<%=post.getThumbnail()%>"></div>
                            <div class="blog__item__text">
                                <span><img src="img/icon/calendar.png" alt=""> <%=date%></span>
                                <h5><%=post.getTitle()%></h5>
                                <a href="../hpostdetail?bid=<%=post.getPost_id()%>">Đọc bài viết</a>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    %>


                </div>
                <%
                    int npage = allpostlist.size()/6+1;
                int cpostpage = 0;
            if(session.getAttribute("cpostpage")!=null) cpostpage = Integer.parseInt(session.getAttribute("cpostpage")+"");
                %>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="product__pagination">
                            <%
                            for (int i = 0; i < npage; i++) {
                            %>
                            <a class="<%=i==cpostpage?"active":""%>" href="../postpagination?cpage=<%=i%>"><%=i+1%> </a>
                            <%
                                }
                            %>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Blog Section End -->

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
