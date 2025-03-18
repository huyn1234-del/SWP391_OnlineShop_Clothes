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
                            <li class="active"><a style="color: <%=tab.equals("new")?"black":"#ccc"%>;" href="../homefilter?tab=new">New Arrivals</a></li>
                            <li class=""><a style="color: <%=tab.equals("sale")?"black":"#ccc"%>;" href="../homefilter?tab=sale">Hot Sales</a></li>
                        </ul>
                    </div>
                </div>

                <div class="row product__filter">

                    <%
                    for (Product product : pList) {
                    int price =product.getPrice() ;
                    String omoney = numberFormat.format(product.getPrice());
                    String cmoney = numberFormat.format(price);
                    if(product.isIs_active()){
                    %>
                    <div class="col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals">


                        <div class="product__item">
                            <div class="product__item__pic set-bg" data-setbg="../<%=product.getThumbnail()%>">                        
                                
                                <ul class="product__hover">

                                    <li><a href="../hproductdetail?proid=<%=product.getProduct_id()%>"><img src="img/icon/search.png" alt=""></a></li>
                                </ul>
                            </div>
                            <div class="product__item__text">
                                <h5 style="cursor: pointer" onclick="window.location.href = '../hproductdetail?proid=<%=product.getProduct_id()%>'"><%=product.getProduct_name()%></h5>
                                
                                <div class="rating">
                                    <%if(product.getRated_star()<=0){
                                    %>
                                    <i class="fa fa-star-o"></i>
                                    <i class="fa fa-star-o"></i>
                                    <i class="fa fa-star-o"></i>
                                    <i class="fa fa-star-o"></i>
                                    <i class="fa fa-star-o"></i>
                                    <% } else if(product.getRated_star()<=1){%>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star-o"></i>
                                    <i class="fa fa-star-o"></i>
                                    <i class="fa fa-star-o"></i>
                                    <i class="fa fa-star-o"></i>
                                    <% } else if(product.getRated_star()<=2){  %>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star-o"></i>
                                    <i class="fa fa-star-o"></i>
                                    <i class="fa fa-star-o"></i>
                                    <%} else if(product.getRated_star()<=3){%>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star-o"></i>
                                    <i class="fa fa-star-o"></i>
                                    <%} else if(product.getRated_star()<=4){%>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star-o"></i>
                                    <%} else { %>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <i class="fa fa-star"></i>
                                    <%}%>
                                </div>
                                <h5>
                                <%=cmoney%>

                                </h5>
                                
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
                            <span>Tin mới nhất</span>
                            <h2>Xu Hướng Mới Nhất</h2>
                        </div>
                    </div>
                </div>

                <%
                List<Post> poList = (List<Post>)session.getAttribute("poList");
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                 int i = 0;
                %>
                <div class="row">

                    <%
                    for (Post post : poList) {
                    if(post.getIs_active()==1){
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

   

    </body>

</html>
