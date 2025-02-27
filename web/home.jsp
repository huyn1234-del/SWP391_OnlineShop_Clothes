<%-- 
    Document   : home
    Created on : Sep 15, 2024, 4:42:11 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Slider"%>
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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home</title>
        <link rel="stylesheet" href="./css/header_style.css">

    </head>

    <body>
        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>
        <jsp:include page="../header.jsp" />
        <!-- Header Section End -->

        <!-- Hero Section Begin -->
        <section class="hero">
            <div class="hero__slider owl-carousel">
                <%
                List<Slider> sList = (List<Slider>)session.getAttribute("hsList");
                for (Slider slider : sList) {
                    if(slider.getStatus()==1){
                %>
                <div class="hero__items set-bg" data-setbg="../<%=slider.getImg()%>">
                    <div class="container">
                        <div class="row">
                            <div class="col-xl-5 col-lg-7 col-md-8">
                                <div class="hero__text">                        
                                    <h2 style="color: white;"><%=slider.getTitle()%></h2>
                                    <p style="color: white;"><%=slider.getDescription()%></p>
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
                            <li class=""><a style="color: <%=tab.equals("hot")?"black":"#ccc"%>;" href="../homeslider">Best Sellers</a></li>
                            <li class="active"><a style="color: <%=tab.equals("new")?"black":"#ccc"%>;" href="../homefilter?tab=new">New Arrivals</a></li>
                            <li class=""><a style="color: <%=tab.equals("sale")?"black":"#ccc"%>;" href="../homefilter?tab=sale">Hot Sales</a></li>
                        </ul>
                    </div>
                </div>

                <div class="row product__filter">

                    <%
                    for (Product product : pList) {
                    int price =product.getPrice() - product.getPrice()*product.getDiscount()/100;
                    String omoney = numberFormat.format(product.getPrice());
                    String cmoney = numberFormat.format(price);
                    if(product.isIs_active()){
                    %>
                    <div class="col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals">


                        <div class="product__item">
                            <div class="product__item__pic set-bg" data-setbg="../<%=product.getThumbnail()%>">                        
                                <%
                                if(product.getDiscount()!=0){
                                %>
                                <span style="background-color: black; color: white;" class="label">-<%=product.getDiscount()%>%</span>
                                <% }%>
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
                                <h5><%
                                if(product.getDiscount()!=0){
                                    %>
                                    <del style="color: gray; font-size: 12px;"><%=omoney%></del>
                                    <% }%>
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

        <script type="text/javascript">
            (function (d, m) {
                var kommunicateSettings =
                        {"appId": "25bad573165478c1ff3ece638dcf09b54", "popupWidget": true, "automaticChatOpenOnNavigation": true};
                var s = document.createElement("script");
                s.type = "text/javascript";
                s.async = true;
                s.src = "https://widget.kommunicate.io/v2/kommunicate.app";
                var h = document.getElementsByTagName("head")[0];
                h.appendChild(s);
                window.kommunicate = m;
                m._globals = kommunicateSettings;
            })(document, window.kommunicate || {});
            /* NOTE : Use web server to view HTML files as real-time update will not work if you directly open the HTML file in the browser. */
        </script>
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
