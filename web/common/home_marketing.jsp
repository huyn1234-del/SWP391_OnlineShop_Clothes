

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Sliders"%>
<%@page import="model.Product"%>
<%@page import="model.Post"%>
<%@page import="java.util.*" %>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.NumberFormat"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="zxx">

    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Male_Fashion Template">
        <meta name="keywords" content="Male_Fashion, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Male-Fashion | Template</title>

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
                <div class="offcanvas-menu-overlay"></div>
<div class="offcanvas-menu-wrapper">
    <div class="offcanvas__option">
        <div class="offcanvas__links">
            <a href="#">Sign in</a>
            <a href="#">FAQs</a>
        </div>
        <div class="offcanvas__top__hover">
            <span>Usd <i class="arrow_carrot-down"></i></span>
            <ul>
                <li>USD</li>
                <li>EUR</li>
                <li>USD</li>
            </ul>
        </div>
    </div>
    <div class="offcanvas__nav__option">
        <a href="#" class="search-switch"><img src="./common/img/icon/search.png" alt=""></a>
        <a href="#"><img src="../common/img/icon/heart.png" alt=""></a>
        <a href="#"><img src="./common/img/icon/cart.png" alt=""> <span>0</span></a>
        <div class="price">$0.00</div>
    </div>
    <div id="mobile-menu-wrap"></div>

</div>
<!-- Offcanvas Menu End -->
<header class="header">
    <div class="header__top">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-7">

                </div>
                <%
                String s = request.getContextPath();;
                %>
                <div class="col-lg-6 col-md-5">
                    <div class="header__top__right">
                        <div class="header__top__links">
                            <c:if test="${sessionScope.account eq null}">
                                <a href="<%=s%>/login">Sign in</a>
                                <a href="<%=s%>/register">Sign up</a>                      
                            </c:if>
                            <c:if test="${sessionScope.account ne null}">
                                <a href="#">${sessionScope.account.username}</a>
                                <a href="<%=s%>/logout">Đăng xuất</a>                      
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-3">
                <div class="header__logo">
                    <a href="<%=s%>/marketinghome"><img src="<%=s%>/common/img/logo.png" alt=""></a>
                </div>
            </div>
            <%
            String mainpage = session.getAttribute("mainpage")+"";
            if(mainpage.equals("null")) mainpage = "home";
            %>
            <div class="col-lg-6 col-md-6">
                <nav class="header__menu mobile-menu">
                    <ul>
                        <li class="<%=mainpage.equals("home")?"active":""%>"><a href="<%=s%>/marketinghome">Trang chủ</a></li>
                       
                        <li><a href="#">Quản lý</a>
                            <ul class="dropdown">
                                <li><a href="../customerlist">Khách hàng</a></li>
                                <li><a href="../sliderlist">Sliders</a></li>
                                <li><a href="../listpostmarketing">Bài đăng</a></li>
                                <li><a href="../postfeedbacklist">Phản hồi bài đăng</a></li>
                                <li><a href="../productlist">Sản phẩm</a></li>
                                <li><a href="../productfeedbackpaging">Phản hồi sản phẩm</a></li>
                                <li><a href="../voucherlist">Phiếu giảm giá</a></li>
                            </ul>
                        </li>
                         <li><a href="../markertingdashboard">Thống kê</a></li>
                       
                        
                    </ul>
                </nav>
            </div>
            <%
        String pname = session.getAttribute("pname")+"";
        if(pname.equals("null")) pname= "";
            %>
            <div class="col-lg-3 col-md-3">
                <div class="header__nav__option row" style="padding: 22px 0;">
                    <div class="col-md-10">
                       

                    </div>



                </div>
            </div>
        </div>
        <div class="canvas__open"><i class="fa fa-bars"></i></div>
    </div>
</header>

        <!-- Header Section End -->

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
                                    <h2 style="color: white;"><%=sliders.getTitle()%></h2>
                                    <p style="color: white;"><%=sliders.getDescription()%></p>
                                    <a href="../sliderlist" class="primary-btn">Quản lý Slider <span class="arrow_right"></span></a>

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
                          
<div class="section-title">
                           
                           
                        </div>

        <!-- Product Section Begin -->
        <%
        String tab = session.getAttribute("tabfilter")+"";
        List<Product> pList = (List<Product>)session.getAttribute("hpList");
        
        Locale locale = new Locale("vi", "VN");
        Currency currency = Currency.getInstance("VND");
        DecimalFormatSymbols df = DecimalFormatSymbols.getInstance(locale);
        df.setCurrency(currency);
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        numberFormat.setCurrency(currency);
        
        %>
        <section class="product spad" style="
                 padding-top: 32px;">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                       <ul class="filter__controls">
    <li class="">
        <a class="button" href="../productlist">
            <button type="button" class="btn " style="width: 285px;height: 38px;background-color: black;color: white">Quản lý sản phẩm</button>

        </a>
    </li>
</ul>

<!-- CSS -->


                    </div>
                </div>

                <div class="row product__filter">

                    <%
                    for (Product product : pList) {
                    int price =product.getPrice() ;
                    String cmoney = numberFormat.format(price);
                    if(product.isIs_active()){
                    %>
                    <div class="col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals">


                        <div class="product__item">
                            <div class="product__item__pic set-bg" data-setbg="../<%=product.getThumbnail()%>">                        
                               
                                <ul class="product__hover">

                                    <li><img src="img/icon/search.png" alt=""></li>
                                </ul>
                            </div>
                            <div class="product__item__text">
                                <h6><%=product.getProduct_name()%></h6>
                               
                                                             
                                <div class="product__color__select">
                                    <label for="pc-1">
                                        <input type="radio" id="pc-1">
                                    </label>
                                    <label class="active black" for="pc-2">
                                        <input type="radio" id="pc-2">
                                    </label>
                                    <label class="grey" for="pc-3">
                                        <input type="radio" id="pc-3">
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>

                    <%
                        }
                    }
                    %>


                </div>
            </div>
        </section>
        <!-- Product Section End -->


        <!-- Latest Blog Section Begin -->
        <section class="latest spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title">
                            <a href="../listpostmarketing"> <h2>Manage Posts</h2> </a>  
                            <span>Latest News</span>
                            <h3>Fashion New Trends</h3>
                        </div>
                    </div>
                </div>

                <%
                List<Post> poList = (List<Post>)session.getAttribute("poList");
                 int i = 0;
                %>
                <div class="row">

                    <%
                    for (Post post : poList) {
                    if(post.getIs_active()==1){
                    %>
                    <div class="col-lg-4 col-md-6 col-sm-6">
                        <div class="blog__item">
                            <div class="blog__item__pic set-bg" data-setbg="../<%=post.getThumbnail()%>"></div>
                            <div class="blog__item__text">
                                <span><img src="img/icon/calendar.png" alt=""> <%=post.getModified_at()%></span>
                                <h5><%=post.getTitle()%></h5>
                            
                            </div>
                        </div>
                    </div>

                    <%
                    i++;
                    if(i==3) break;
                    }
                   }
                    %>


                </div>
            </div>
        </section>
        <!-- Latest Blog Section End -->

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

         <script src="https://www.gstatic.com/dialogflow-console/fast/messenger/bootstrap.js?v=1"></script>
<df-messenger
  intent="WELCOME"
  chat-title="Clothes_Test"
  agent-id="8aa23345-6a5f-4ef6-8580-b78f67115fcb"
  language-code="en"
></df-messenger>  
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
