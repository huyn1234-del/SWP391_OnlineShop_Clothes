<%-- 
    Document   : home
    Created on : Sep 15, 2024, 4:42:11 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Sliders"%>
<%@page import="model.Product"%>
<%@page import="model.Post"%>
<%@page import="java.util.*" %>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>

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
        <div class="container">
            <h4>Lọc Hiển Thị</h4>
            <form id="filterForm">
                <label><input type="checkbox" id="toggleSliderTitle" checked> Hiện Tiêu Đề Slider</label>
                <label><input type="checkbox" id="toggleSliderDesc" checked> Hiện Mô Tả Slider</label>
                <label><input type="checkbox" id="togglePostTitle" checked> Hiện Tiêu Đề Bài Viết</label>
                <label><input type="checkbox" id="togglePostDate" checked> Hiện Ngày Đăng</label>
                <br>
                <label for="postCount">Số lượng bài viết:</label>
                <input type="number" id="postCount" min="1" value="3">
                <span id="error-message" style="color: red; display: none;">Số bài viết phải lớn hơn 0</span>
                <br>
                <button type="button" id="applyFilter" class="btn btn-primary">Áp dụng</button>
            </form>
        </div>





        <!-- Hero Section Begin -->
        <section class="hero">
            <div class="hero__slider owl-carousel">
                <%
                List<Sliders> sList = (List<Sliders>)session.getAttribute("hsList");
                for (Sliders sliders : sList) {
                    if(sliders.getStatus()==1){
                %>
                <div class="hero__items set-bg" data-setbg="../<%=sliders.getImg()%>">
                    <div class="container">
                        <div class="row">
                            <div class="col-xl-5 col-lg-7 col-md-8">
                                <div class="hero__text">
                                    <h2 class="slider-title" style="color: white;"><%=sliders.getTitle()%></h2>
                                    <p class="slider-desc" style="color: white;"><%=sliders.getDescription()%></p>
                                    <a href="../homeproduct" class="primary-btn">Mua ngay <span class="arrow_right"></span></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <%
                 }
                }
                %>
            </div>
        </section>
        <!-- Hero Section End -->
        <!-- Latest Blog Section Begin -->
        <section class="latest spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title">
                            <span>Tin mới nhất</span>
                            <h2>Xu Hướng Mới Nhất</h2>
                        </div>
                    </div>
                </div>

               <div class="row" id="postContainer">
    <%
    List<Post> poList = (List<Post>) session.getAttribute("poList");
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    int postIndex = 0;
    for (Post post : poList) {
        if (post.getIs_active() == 1) {
            String date = sdf.format(post.getModified_at());
    %>
    <div class="col-lg-4 col-md-6 col-sm-6 post-item <%= postIndex >= 3 ? "hidden" : "" %>">
        <div class="blog__item">
            <div class="blog__item__text">
                                            <div class="blog__item__pic set-bg" data-setbg="../<%=post.getThumbnail()%>"></div>

                <span class="post-date"><img src="img/icon/calendar.png" alt=""> <%= date %></span>
                <h5 class="post-title"><%= post.getTitle() %></h5>
                <a href="../hpostdetail?bid=<%= post.getPost_id() %>">Đọc bài viết</a>
            </div>
        </div>
    </div>
    <%
            postIndex++;
        }
    }
    %>
</div>




            </div>
        </section>
        <!-- Latest Blog Section End -->

        <!-- Footer Section Begin -->
        <!-- Contact Section End -->
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

     <script>
    document.addEventListener("DOMContentLoaded", function () {
        let posts = document.querySelectorAll(".post-item");

        function updatePostVisibility(count) {
            posts.forEach((post, index) => {
                post.style.display = index < count ? "block" : "none";
            });
        }

        // Mặc định hiển thị 3 bài
        updatePostVisibility(3);

        document.getElementById("applyFilter").addEventListener("click", function () {
            let postCountInput = document.getElementById("postCount");
            let postCount = parseInt(postCountInput.value) || 1;

            // Kiểm tra nếu số bài viết nhỏ hơn 1
            if (postCount < 1) {
                document.getElementById("error-message").style.display = "block";
                postCountInput.value = 1;
                return;
            } else {
                document.getElementById("error-message").style.display = "none";
            }

            // Cập nhật số lượng bài viết hiển thị
            updatePostVisibility(postCount);

            // Kiểm tra trạng thái checkbox khi nhấn "Áp dụng"
            let showSliderTitle = document.getElementById("toggleSliderTitle").checked;
            let showSliderDesc = document.getElementById("toggleSliderDesc").checked;
            let showPostTitle = document.getElementById("togglePostTitle").checked;
            let showPostDate = document.getElementById("togglePostDate").checked;

            document.querySelectorAll(".slider-title").forEach(el => el.style.display = showSliderTitle ? "block" : "none");
            document.querySelectorAll(".slider-desc").forEach(el => el.style.display = showSliderDesc ? "block" : "none");
            document.querySelectorAll(".post-title").forEach(el => el.style.display = showPostTitle ? "block" : "none");
            document.querySelectorAll(".post-date").forEach(el => el.style.display = showPostDate ? "block" : "none");
        });
    });
</script>

    </body>

</html>
