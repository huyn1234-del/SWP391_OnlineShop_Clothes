<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Product"%>
<%@page import="model.ProductImg"%>
<%@page import="model.ProductSize"%>
<%@page import="model.ProductFeedback"%>
<%@page import="java.util.*" %>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.NumberFormat"%>
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
        <!-- Page Preloder -->
        <jsp:include page="../common/header.jsp" />
        <!-- Header Section End -->

        <!-- Shop Details Section Begin -->
        <section class="shop-details">
            <div class="product__details__pic">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="product__details__breadcrumb">
                                <a>Trang chủ</a>
                                <a>Sản phẩm</a>
                                <span>Thông tin chi tiết</span>
                            </div>
                        </div>
                    </div>
                    <%
                     Product product = (Product)session.getAttribute("dproduct");
                     List<ProductImg> piList = (List<ProductImg>)session.getAttribute("dpiList");
                     List<ProductFeedback> pfList = (List<ProductFeedback>)session.getAttribute("pf2List");
                     List<ProductFeedback> alldpfList = (List<ProductFeedback>)session.getAttribute("alldpfList");
                     List<ProductSize> psList = (List<ProductSize>)session.getAttribute("dpsList");
        
                     Locale locale = new Locale("vi", "VN");
                    Currency currency = Currency.getInstance("VND");
                    DecimalFormatSymbols df = DecimalFormatSymbols.getInstance(locale);
                    df.setCurrency(currency);
                    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
                    numberFormat.setCurrency(currency);
                    
                    int price =product.getPrice();
                    String cmoney = numberFormat.format(price);
                    String omoney = numberFormat.format(product.getPrice());
                    %>
                    <div class="row">
                        <div class="col-lg-3 col-md-3">
                            <ul class="nav nav-tabs" role="tablist">
                                <%
                                int i = 1;
                                for (ProductImg productImg : piList){
                                     if(productImg.getIs_active()==1){
                                     
                                %>
                                <li class="nav-item">
                                    <a class="nav-link <%=i==1?"active":""%>" data-toggle="tab" href="#tabs-<%=i%>" role="tab">
                                        <div class="product__thumb__pic set-bg" data-setbg="../<%=productImg.getImage_url()%>">
                                        </div>
                                    </a>
                                </li>
                                <%
                                    i++;
                                 }
                                     }
                                %>


                            </ul>
                        </div>
                        <div class="col-lg-6 col-md-9">
                            <div class="tab-content">
                                <%
                                i = 1;
                                for (ProductImg productImg : piList){
                                
                                     if(productImg.getIs_active()==1){
                                %>
                                <div class="tab-pane <%=i==1?"active":""%>" id="tabs-<%=i%>" role="tabpanel">
                                    <div class="product__details__pic__item">
                                        <img src="../<%=productImg.getImage_url()%>" alt="">
                                    </div>
                                </div>
                                <%
                                    i++;
                                 }
                                     }
                                %>


                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="product__details__content">
                <div class="container">
                    <div class="row d-flex justify-content-center">
                        <div class="col-lg-8">
                            <div class="product__details__text">
                                <h4><%=product.getProduct_name()%></h4>
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
                                    <span> - <%=alldpfList.size()%> Đánh giá</span>
                                </div>
                               

                                <p><%=product.getDescription()%></p>
                                <div class="product__details__option">

                                    <div class="product__details__option__size">
                                        <span>Kích cỡ:</span>

                                        <div class="shop__sidebar__tags">
                                            <%
                                     int dsize = Integer.parseInt(session.getAttribute("dsize")+"");
                                     int dquantity = Integer.parseInt(session.getAttribute("dquantity")+"");
                                    for (ProductSize productSize : psList) {
                                            %>

                                            <a style="<%=dsize==productSize.getSize_id()?
                                                    "background-color: black; color: white;":
                                                    "color: #b7b7b7;"%>" href="../dsetquantity?sid=<%=productSize.getSize_id()%>"><%=productSize.getSize_name()%></a>
                                            <%
                                                }
                                            %>


                                        </div>

                                    </div>

                                </div>
                                <div class="product__details__cart__option">
                                    <form action="../addToCart" method="get">
                                        <span style="margin-right: 12px;  font-weight: bold;">Kho: <%=dquantity%></span>
                                        <div class="quantity">
                                            <div class="">
                                                <input name="quantity" value="1" type="number" min="1" max="<%=dquantity%>">
                                            </div>
                                        </div>
                                        <input type="hidden" name="pid" value="<%=product.getProduct_id()%>">
                                        <input type="hidden" name="sid" value="<%=dsize%>">
                                        <button type="submit" class="primary-btn">thêm giỏ hàng</button>
                                    </form>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>
                <% String dcontent = session.getAttribute("dcontent")+"";%>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="product__details__tab">
                           
                            <div class="tab-content" style="margin: 0px 15%;">
                                <div class="tab-pane <%=dcontent.equals("pdescription")?"active":""%>" id="tabs-5" role="tabpanel">
                                    <div class="product__details__tab__content">
                                        <div class="product__details__tab__content__item">
                                            <h5>Thông tin sản phẩm</h5>
                                            <p><p><%=product.getDescription()%></p></p>

                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane <%=dcontent.equals("pfeedback")?"active":""%>" id="tabs-6" role="tabpanel">
                                    <%
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                                    for (ProductFeedback productFeedback : pfList) {
                                    String date = sdf.format(productFeedback.getUpdate_at());
        
                                    %>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="product__item row" style="margin: 24px 0;">

                                                <div style="height: 120px" class="product__item__pic col-md-2">
                                                    <img style="width: 100%;height: 100%;" src="${pageContext.request.contextPath}/<%=productFeedback.getThumbnail()%>" alt="img"/>
                                                </div>

                                                <div class="col-md-10">
                                                    <div style="padding: 0" class="blog__details__author__pic col-md-12">
                                                        <%
                                                        String profilePictureUrl = (String) productFeedback.getCustomer_img();
                                    String profileImgSrc = profilePictureUrl != null && (profilePictureUrl.startsWith("http://") || profilePictureUrl.startsWith("https://")) 
                                                            ? profilePictureUrl 
                                                            : request.getContextPath() + "/" + profilePictureUrl;
                                                        %>
                                                        <img style="margin: 0px 10px 10px 0px;" src="<%=profileImgSrc%>" alt="">
                                                        <strong><%=productFeedback.getCustomer_name()%></strong>
                                                    </div>
                                                    <div style="    margin-left: 12px;">
                                                        <h6><%=productFeedback.getReview()%></h6>

                                                        <div class="rating">
                                                            <%if(productFeedback.getRating()<=0){
                                                            %>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <% } else if(productFeedback.getRating()<=1){%>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <% } else if(productFeedback.getRating()<=2){  %>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <%} else if(productFeedback.getRating()<=3){%>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <i class="fa fa-star-o"></i>
                                                            <%} else if(productFeedback.getRating()<=4){%>
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
                                                        <p style="color: #ccc; font-style: italic;"><%=date%></p>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <%
                                        }
                                    %>


                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="product__pagination">
                                                <%
                                                    int numberOfPage = alldpfList.size()/2+1;
                                                int dfeedbackpage = Integer.parseInt(session.getAttribute("dfeedbackpage")+"");
                                                for (int j = 0; j < numberOfPage; j++) {
                                                %>
                                                <a class="<%=dfeedbackpage==j?"active":""%>" href="../fbpagination?cpage=<%=j%>"><%=j+1%> </a>
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
                </div>
            </div>
        </div>
    </section>
    <!-- Shop Details Section End -->


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
